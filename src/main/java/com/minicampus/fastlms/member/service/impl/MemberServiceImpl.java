package com.minicampus.fastlms.member.service.impl;

import com.minicampus.fastlms.member.components.MailComponent;
import com.minicampus.fastlms.member.entity.Member;
import com.minicampus.fastlms.member.model.MemberInput;
import com.minicampus.fastlms.member.repository.MemberRepository;
import com.minicampus.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MailComponent mailComponent;

    @Override
    public boolean register(MemberInput memberInput) {
        if (memberRepository.findById(memberInput.getUserId()).isPresent()) {
            return false;
        }

        String key = UUID.randomUUID().toString();

        memberRepository.save(Member.builder()
                .userId(memberInput.getUserId())
                .userName(memberInput.getName())
                .password(memberInput.getPassword())
                .phone(memberInput.getPhone())
                .emailAuthKey(key)
                .registeredAt(LocalDateTime.now())
                .build());

        String subject = "TEST) 회원가입 인증 메일";
        String text = "<p>회원가입 이메일 인증을 완료하려면 아래 이메일 인증을 클릭하세요.!</p>" +
                "<a href='http://localhost:8080/member/email-auth?id=" + memberInput.getUserId() +
                "&key=" + key + "' >이메일 인증</a>";

        return mailComponent.sendMail(memberInput.getUserId(), subject, text);
    }

    @Override
    public boolean emailAuth(String id, String key) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isEmpty()) {
            return false;
        }

        Member member = optionalMember.get();
        if (!member.getEmailAuthKey().equals(key)) {
            return false;
        }

        member.setEmailAuthYn(true);
        member.setEmailAuthAt(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }
}
