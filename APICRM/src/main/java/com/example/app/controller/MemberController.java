package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.app.domain.Member;
import com.example.app.mapper.MemberMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    MemberMapper mapper;

    @GetMapping
    public List<Member> getAllMembers() {
        try {
            return mapper.selectAll();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Integer id) {
        try {
            return mapper.selectById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping
    public Member addMember(@RequestBody @Valid Member member,
            Errors errors) {
        System.out.println("<<<<< 会員の追加 >>>>>");
        System.out.println("氏名：" + member.getName());
        System.out.println("年齢：" + member.getAge());
        System.out.println("住所：" + member.getAddress());
        System.out.println("種別：" + member.getTypeId());
        if (errors.hasErrors()) {
            System.out.println("入力に不備あり");
            return null;
        }
        try {
            mapper.insert(member);
            return mapper.selectByLastInsertId();
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping
    public Member updateMember(@RequestBody @Valid Member member,
            Errors errors) {
        System.out.println("<<<<< 会員の更新 >>>>>");
        System.out.println("ID：" + member.getId());
        System.out.println("氏名：" + member.getName());
        System.out.println("年齢：" + member.getAge());
        System.out.println("住所：" + member.getAddress());
        System.out.println("種別：" + member.getTypeId());
        if (errors.hasErrors()) {
            System.out.println("入力に不備あり");
            return null;
        }
        try {
            mapper.update(member);
            return member;
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public Member deleteMember(@PathVariable Integer id) {
        try {
            Member member = mapper.selectById(id);
            System.out.println("<<<<< 削除する会員 >>>>>");
            System.out.println("ID：" + member.getId());
            System.out.println("氏名：" + member.getName());
            System.out.println("年齢：" + member.getAge());
            System.out.println("住所：" + member.getAddress());
            System.out.println("種別：" + member.getTypeId());
            mapper.delete(id);
            return member;
        } catch (Exception e) {
            return null;
        }
    }
}