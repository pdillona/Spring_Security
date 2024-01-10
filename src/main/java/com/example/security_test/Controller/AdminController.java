package com.example.security_test.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
public class AdminController {
    
    @GetMapping("/admin") // admin경로로 권한없이 접근하면 403에러가 발생한다. 왜냐하면 config설정에 따라 filter에서 role을 검사 admin이 부여되지 않았기 때문이다.
    public String adminP(){
        
        return "admin";
    }
}
