package com.example.security_test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name="User2")
public class UserEntity {
    
   
    /*   @GeneratedValue(strategy = GenerationType.IDENTITY) // 아이디가 자동으로 생성되기 위한 어노테이션, IDENTITY 방식의 전략으로 생성
    private String id;*/
    @Id   // 필수로 필요한 아이디값
    private String uid;
    private String pass;
    
    private int age;
    private String hp;
    private String name;
    @CreationTimestamp
    private LocalDateTime regDate;
    
    private String role;
    
}
