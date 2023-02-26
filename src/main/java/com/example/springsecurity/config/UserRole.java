package com.example.springsecurity.config;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.springsecurity.config.UserPermission.*;

public enum UserRole {

    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,USER_READ,USER_WRITE));

    private final Set<UserPermission> userPermissions;

    UserRole(Set<UserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }

    public Set<UserPermission> getUserPermissions() {
        return userPermissions;
    }
}
