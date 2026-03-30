package com.green.greengram.configuration.model;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
//Spring Security가 인증처리를 할 때 사용하는 객체
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {
    private final JwtUser jwtUser;

    public long getSignedUserId() {
        return jwtUser.getSignedUserId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
