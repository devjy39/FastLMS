package com.minicampus.fastlms.member.service;

import com.minicampus.fastlms.member.model.MemberInput;

public interface MemberService {
    boolean register(MemberInput memberInput);

    boolean emailAuth(String id, String uuid);
}
