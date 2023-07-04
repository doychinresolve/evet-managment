/**
 * 
 */
package com.afour.emgmt.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.afour.emgmt.entity.Organizer;

/**
 * 
 */
public class UserInfoUserDetails implements UserDetails{
	private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(Organizer userInfo) {
        name=userInfo.getUserName();
        password=userInfo.getPassword();
        authorities = List.of(new SimpleGrantedAuthority(userInfo.getRole().getRoleName()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
