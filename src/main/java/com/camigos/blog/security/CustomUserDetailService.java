package com.camigos.blog.security;

import com.camigos.blog.entities.User;
import com.camigos.blog.exceptions.ResourceNotFoundException;
import com.camigos.blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService  {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loading user from database by username
        User user = userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("USER", "email Id : " + username, 0));
        return user;
    }
}
