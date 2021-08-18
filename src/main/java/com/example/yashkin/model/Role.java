package com.example.yashkin.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role implements GrantedAuthority, Serializable {
    USER(Set.of(Permission.USERS_READ)),
    AUTHOR(Set.of(Permission.USERS_READ, Permission.USERS_WRITE)),
    EXECUTOR(Set.of(Permission.USERS_READ, Permission.USERS_WRITE)),
    ADMIN(Set.of(Permission.USERS_READ, Permission.USERS_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }


    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
