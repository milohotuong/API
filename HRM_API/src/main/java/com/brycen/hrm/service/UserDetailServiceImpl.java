package com.brycen.hrm.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.brycen.hrm.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.brycen.hrm.entity.UserRole;
import com.brycen.hrm.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService{
	 @Autowired
	    private UserRepository userRepository;
	 @Transactional(readOnly=true)
	 @Override
	    public UserDetails loadUserByUsername(String username) {
	        User user = userRepository.findByUsername(username);
	        List<GrantedAuthority> authorities =
                    buildUserAuthority(user.getUserRoles());
	        System.out.println("du ma" + authorities.toString());
	        return buildUserForAuthentication(user, authorities);    
	    }
	 
	  private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
		       		
		    }
	 
	 
	    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

	        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
	        
	        // add user's authorities
	        for (UserRole userRole : userRoles) {
	        		 setAuths.add(new SimpleGrantedAuthority(userRole.getRole().getRoleName()));
	        }

	        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

	        return Result;
	    }

}
