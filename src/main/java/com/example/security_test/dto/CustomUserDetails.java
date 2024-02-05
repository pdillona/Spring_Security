package com.example.security_test.dto;

import com.example.security_test.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// UserDetails interface implements 하여 구현 
public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {

        this.userEntity = userEntity;
    }
    /*
    * getAuthorities() : 계정이 가지고 있는 권한 목록 리턴
      getPassword() : 계정의 비밀번호 리턴
      getUsername() : 계정 이름 리턴
      isAccountNonExpired() : 계정이 만료됐는지 리턴 -> true는 완료되지 않음 의미
      isAccountNonLocked() : 계정이 잠겨있는지 리턴 -> true는 잠기지 않음
      isCredentialNonExpired() : 비밀번호가 만료됐는지 리턴 -> true는 만료X 의미
      isEnabled() : 계정이 활성화돼 있는지 리턴 -> true는 활성화 상태 의미
    * 
    */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            // GrantedAuthority  interface가 갖는 메서드
            @Override
            public String getAuthority() {

                return userEntity.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }


    // 사용자의 아이디에 관한 설정을 true로 설정하여 해당 값들을 유지 한다.
    // 사용자의 아이디가 만료 되었는지 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 사용자의 아이디가 잠겨있는지 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // 사용자의 아이디가 사용 가능한지 
    @Override
    public boolean isEnabled() {
        return true;
    }
}