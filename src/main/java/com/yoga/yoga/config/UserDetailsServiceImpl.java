package com.yoga.yoga.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yoga.yoga.Entities.User;
import com.yoga.yoga.dao.UserRepository;


public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // System.out.println(username);
        User userByUserName = this.userRepository.getUserByUserName(username);
        if(userByUserName==null) {
            throw new UsernameNotFoundException("Could not found user !!");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(userByUserName);
        // System.out.println(username+" <-> "+userByUserName);
        return customUserDetails;
    }
    
}
