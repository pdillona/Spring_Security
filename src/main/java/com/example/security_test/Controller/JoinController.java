package com.example.security_test.Controller;

import com.example.security_test.dto.JoinDTO;
import com.example.security_test.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {
    
    @Autowired
    private JoinService joinService; // 필드 주입 방식이 아닌 생성자 주입 방식으로 의존성 주입을 추천함
    
    @GetMapping("/join")
    public String joinP() {
        
        return "join";
    }
    
    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {

        joinService.joinProcess(joinDTO);
        
        return "redirect:/login";
    }
    
    
}

/*

@Controller
public class JoinController {

    // 생성자 주입 방식으로 의존성 주입을한 모습
    private final JoinService joinService;  

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping("/join")
    public String joinP() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);
        return "redirect:/login";
    }
}

 */