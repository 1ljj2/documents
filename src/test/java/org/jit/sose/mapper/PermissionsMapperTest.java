package org.jit.sose.mapper;

import org.jit.sose.TestAppDocument;
import org.jit.sose.domain.vo.PermissionsVo;
import org.jit.sose.enums.PermissionTypeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class PermissionsMapperTest extends TestAppDocument {

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Test
    public void listUrlByMenuUrl() {
//		String menuUrl = "/dir1/menu1";
        String menuUrl = "/dir2/menu3";
//		Set<String> urls = permissionsMapper.listUrlByMenuUrl(menuUrl);
//		urls.forEach(System.out::println);
    }

    @Test
    public void listUrlByType() {
        String[] urlList = permissionsMapper.listUrlByType(PermissionTypeEnum.Ignoring);
//		urlList.forEach(System.out::println);
        System.out.println(Arrays.toString(urlList));
    }

    @Test
    public void listByMenuButtonId() {
        int buttonId = 35;
        List<PermissionsVo> permissionsList = permissionsMapper.listByMenuButtonId(buttonId);
        permissionsList.forEach(System.out::println);
    }


}
