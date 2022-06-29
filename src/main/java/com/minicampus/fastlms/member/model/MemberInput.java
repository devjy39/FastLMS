package com.minicampus.fastlms.member.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberInput {
    private String userId;
    private String name;
    private String password;
    private String phone;
}