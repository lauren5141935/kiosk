package com.example.kiosk.manager.model;

import com.example.kiosk.manager.entity.Manager;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class SignupManager {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Request {
        @Email(message = "이메일 형식에 맞게 입력해주세요.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

        private Boolean partnerYn;

        public Manager toEntity() {
            return Manager.builder()
                    .email(this.email)
                    .password(this.password)
                    .partnerYn(this.partnerYn)
                    .build();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Response {
        private Long id;
        private String email;
        private Boolean partnerYn;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdDate;

        public static Response of(Manager manager) {
            return Response.builder()
                    .id(manager.getId())
                    .email(manager.getEmail())
                    .partnerYn(manager.getPartnerYn())
                    .createdDate(manager.getCreatedDate())
                    .build();
        }
    }
}