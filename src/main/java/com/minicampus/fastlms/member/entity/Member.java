package com.minicampus.fastlms.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {
    @Id
    private String userId;

    private String userName;
    private String phone;
    private String password;

    private LocalDateTime registeredAt;

    private String emailAuthKey;
    private boolean emailAuthYn;
    private LocalDateTime emailAuthAt;
}