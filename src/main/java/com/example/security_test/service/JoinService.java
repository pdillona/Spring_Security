package com.example.security_test.service;

import com.example.security_test.dto.JoinDTO;
import com.example.security_test.entity.UserEntity;
import com.example.security_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
    
    public void joinProcess(JoinDTO joinDTO){

        //db에 username의 중복에 대한 검증 과정 추가
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        
        if(isUser){
            
            return;
        }
        /*
        *  - 회원가입
        *  프론트 단에서 httpXMLRequest 메소드를 통해 백엔드에 미리 구현해둔 API에 요청하여 username에 대한 중복 검사가 가능하지만, 
        *  강제로 post 요청을 하여 가입을 진행할 수도 있기 때문에 백엔드 로직은 필수적이다. 
        * 
        *  - 정규식 처리
        *  중복 검증 이후 아이디 가입시 빈문자 열이나 SQLinjection, admin으로의 가입등의 부정적 방법으로의 가입을 막기 위해 정규식 설정을
        *  필수적으로 진행해 주는 것이 좋다.
        */
        
        UserEntity data = new UserEntity();
        
        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword())); // 암호화 하여 저장해야 하기 때문에 적용
        data.setRole("ROLE_USER");
        //data.setRole("ROLE_ADMIN"); // 일단 어드민 페이지 접근을 위한 계정 생성을 위해 입력
        /*
        * db에 들어간 값은 "USER_ADMIN"이고 SecurityConfig에 설정한 내용은 ADMIN인데 스프링은 어떻게 동일한 값인지 확인할까?
        * 
        * ROLE_권한명 에서 선행되는 ROLE_ 의 경우 스프링 시큐리티에서 필수적으로 사용하는 접두사 입니다.
        * security config 설정시에는 권한명만 명시하면 자동으로 생성하며, 
        * DB 기반 커스텀 인증시 ROLE_이라는 접두사를 필수적으로 붙여야하기 때문에 DB 저장시 접두사를 붙여 저장을 합니다.
        * (DB에서 붙이지 않고 UserDetails에서 처리를 진행하셔도 되지만 편의상 DB에 접두사를 함께 저장합니다.)
        * 
        * 추가로 ROLE_ 접두사를 변경하시고 싶으면 아래 메소드를 @Bean으로 등록하시면 됩니다. (MYPREFIX_로 커스텀한 모습)
            @Bean
            static GrantedAuthorityDefaults grantedAuthorityDefaults() {
                return new GrantedAuthorityDefaults("MYPREFIX_");
            }
        * 
        *  https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/core/GrantedAuthorityDefaults.html
        */
        
        userRepository.save(data);        
    }
}
