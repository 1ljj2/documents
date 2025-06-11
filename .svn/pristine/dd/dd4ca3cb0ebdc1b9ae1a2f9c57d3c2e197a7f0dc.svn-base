package org.jit.sose.mapper;

import org.jit.sose.TestAppDocument;
import org.jit.sose.domain.entity.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class RoleMapperTest extends TestAppDocument {

    @Autowired
    RoleMapper roleMapper;

    @Test
    public void listRoleKeyByUserId() {
        int userId = 1;
        Set<String> roleKeys = roleMapper.listRoleKeyByUserId(userId);
        roleKeys.forEach(role -> {
            System.out.println(role);
        });
    }

    @Test
    public void listRoleByMenuBackId() {
        int menuBackId = 1;
        List<Role> roleList = roleMapper.listRoleByMenuBackId(menuBackId);
        roleList.forEach(role -> {
            System.out.println(role);
        });
    }

}
