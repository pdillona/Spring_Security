package com.example.security_test.service;


import com.example.security_test.dto.CustomUserDetails;
import com.example.security_test.entity.UserEntity;
import com.example.security_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    /*
    * controller에서 loadUserByUsername 를 호출한적이 없는것 같은데 해당 클래스가 사용 된 것일까?
    * 
    *  답은 직접 구현하지는 않았지만 호출된다.
    *  "/login" 경로로 POST 요청이 오면 스프링 시큐리티 내부적으로 UsernamPasswordAuthenticationFilter가 동작을 하게되고 이때 
    *  AuthenticationProvider에 의해 CustomUserDetailsService의 loadUserByUsername을 호출하여 DB에 있는 유저를 조회하게 됩니다.
    *  해당 앞 부분의 과정은 직접 커스텀하지 않아도 내부적으로 자동 등록되어 동작하기 때문에 우리가 직접 구현할 필요는 없다.
    *  (시큐리티 관련 처리는 모두 시큐리티 필터에서 동작하기 때문에 컨트롤러(서블릿)을 등록하지 않아도 된다.)
    * 
    * */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*
        * loadUserByUsername() 의 username을 이용해서 들어오는 username 값을 db에서 조회해 줘야한다. 
        * (userRepository.findByUsername(username); 를 통해 데이터를 비교함)
        */
        UserEntity userData = userRepository.findByUsername(username);

        if (userData != null) {

            return new CustomUserDetails(userData);
        }

        return null;
    }
}
