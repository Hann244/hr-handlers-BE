package com.hr_handlers.employee.repository.custom;

import com.hr_handlers.employee.dto.request.EmpUpdateRequestDto;
import com.hr_handlers.employee.dto.response.TeamDetailResponseDto;
import com.hr_handlers.employee.entity.ProfileImage;
import com.hr_handlers.employee.repository.ProfileImageRepository;
import com.hr_handlers.global.exception.ErrorCode;
import com.hr_handlers.global.exception.GlobalException;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hr_handlers.employee.entity.QEmployee.employee;
import static com.hr_handlers.employee.entity.QProfileImage.profileImage;

@Repository
@RequiredArgsConstructor
public class EmpCustomRepositoryImpl implements EmpCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final ProfileImageRepository profileImageRepository;

    @Override
    @Transactional
    public void updateEmp(String empNo, EmpUpdateRequestDto requestDto, String newProfileImageUrl) {
        // 프로필 이미지 변경 및 생성
        ProfileImage profileImage = null;
        if (newProfileImageUrl != null) {
            profileImage = ProfileImage.builder()
                    .profileImageUrl(newProfileImageUrl)
                    .build();
            profileImageRepository.save(profileImage);
        }

        JPAUpdateClause updateClause = jpaQueryFactory
                .update(employee)
                .where(employee.empNo.eq(empNo))
                .set(employee.email, requestDto.getEmail())
                .set(employee.phone, requestDto.getPhone())
                .set(employee.introduction, requestDto.getIntroduction());

        // 프로필 이미지가 있을 경우 업데이트
        if (profileImage != null) {
            updateClause.set(employee.profileImage, profileImage);
        }

        long updatedCount = updateClause.execute();

        entityManager.flush();
        entityManager.close();

        if (updatedCount == 0) {
            throw new GlobalException(ErrorCode.EMPLOYEE_NOT_FOUND);
        }
    }

    @Override
    public List<TeamDetailResponseDto> findTeamMembers(String empNo) {
        // 동일 부서의 모든 사원 정보 조회
        return jpaQueryFactory
                .select(Projections.constructor(
                        TeamDetailResponseDto.class,
                        employee.profileImage.profileImageUrl, // 프로필 이미지 URL
                        employee.position,                    // 직급
                        employee.name                         // 이름
                ))
                .from(employee)
                .leftJoin(employee.profileImage, profileImage) // 프로필 이미지 없는 사원 조회 가능
                .where(employee.department.id.eq(
                        JPAExpressions
                                .select(employee.department.id)
                                .from(employee)
                                .where(employee.empNo.eq(empNo))
                ))
                .fetch();
    }
}