package com.yangteng.workbackstage.service;

import com.yangteng.workbackstage.entity.ua.Authority;
import com.yangteng.workbackstage.entity.ua.Role;

import java.util.List;

public interface UserAuthorityManagementService {
    List<Authority> getAuthorityList(List<Role> roles);

    List<Role> getRoleList(Object loginId);
}
