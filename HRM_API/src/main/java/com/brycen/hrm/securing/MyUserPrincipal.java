package com.brycen.hrm.securing;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;




public class MyUserPrincipal implements UserDetails {
	
    private User user;
    
    private List<GrantedAuthority> authorities;
    
    private String username;
    
    private String password;
    
    private boolean isAccountNonExpired;
    
    private boolean isAccountNonLocked;
    
    private boolean isCredentialsNonExpired;
    
    private boolean isEnable;
    
    
    public MyUserPrincipal(User user,String userName, 
    		String password,
    		List<GrantedAuthority> authorities,boolean isAccountNonExpired,boolean isAccountNonLocked,boolean isCredentialsNonExpired,boolean isEnable) {
        this.user = user;
        this.authorities = authorities;
        this.username = userName;
        this.password = password;
        this.isAccountNonExpired = isCredentialsNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnable = isEnable;
    }
    //...

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnable;
	}
}
