package com.minicampus.fastlms.member.controller;

import com.minicampus.fastlms.member.service.MemberService;
import com.minicampus.fastlms.member.model.MemberInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(Model model, MemberInput memberInput) {
        System.out.println(memberInput.toString());
        model.addAttribute("result", memberService.register(memberInput));

        return "member/register_complete";
    }

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, String id, String key) {
        model.addAttribute("result", memberService.emailAuth(id, key));

        return "member/register_complete";
    }

}