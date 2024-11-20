//package com.hr_handlers.chat.entity;
//
//import jakarta.persistence.Embeddable;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//@Getter
//@Setter
//@Embeddable
//@NoArgsConstructor
//@AllArgsConstructor
//public class ChatId implements Serializable {
//    private Long chatRoomId;
//    private Long employeeId;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ChatId chatId = (ChatId) o;
//        return chatRoomId == chatId.chatRoomId && employeeId == chatId.employeeId;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(chatRoomId, employeeId);
//    }
//}