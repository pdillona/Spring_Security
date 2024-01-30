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

        //db에 uid의 중복에 대한 검증 과정 추가
        
        UserEntity data = new UserEntity();
        
        data.setUid(joinDTO.getUid());
        data.setPass(bCryptPasswordEncoder.encode(joinDTO.getPass())); // 암호화 하여 저장해야 하기 때문에 적용
        data.setRole("ROLE_USER");
        
        userRepository.save(data);        
    }
}
