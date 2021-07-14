package com.soondae.camp.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/all")
    public String[] doAll(){
        return new String[]{"AAA","AAA","AAAA"};
    }

    @GetMapping("/member")
    public String[] doMember(){
        return new String[]{"BBB","BBB","BBB"};
    }

    @GetMapping("/admin")
    public String[] doAdmin(){
        return new String[]{"CCC","CCC","CCC"};
    }

    @GetMapping("/login")
    public String[] login(){
        return new String[]{"CCC","CCC","CCC"};
    }
}