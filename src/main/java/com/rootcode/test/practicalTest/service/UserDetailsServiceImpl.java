package com.rootcode.test.practicalTest.service;

import com.rootcode.test.practicalTest.entity.LedgerUser;
import com.rootcode.test.practicalTest.entity.Role;
import com.rootcode.test.practicalTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        LedgerUser ledgerUser = userRepository.findByUsername(s);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : ledgerUser.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(ledgerUser.getUsername(), ledgerUser.getPassword(), grantedAuthorities);
    }
}