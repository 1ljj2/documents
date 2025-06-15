package org.jit.sose.mapper;

import org.jit.sose.TestAppDocument;
import org.jit.sose.domain.entity.MenuBack;
import org.jit.sose.domain.vo.MenuBackTableTreeVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuBackMapperTest extends TestAppDocument {

	@Autowired
	MenuBackMapper menuMapper;

	@Test
	public void listAllMenu() {
//		List<MenuBack> dirList = menuMapper.listAllMenu();
		List<MenuBack> dirList = menuMapper.listMenuByRoleId(4);
		for (MenuBack dir : dirList) {
			System.out.println("目录：" + dir);
			for (MenuBack menu : dir.getMenuList()) {
				System.out.println("	菜单：" + menu);
				for (MenuBack button : menu.getMenuList()) {
					System.out.println("		按钮：" + button);
				}
			}
		}
	}

	@Test
	public void listMenuTableTree() {
		List<MenuBackTableTreeVo> dirList = menuMapper.listMenuTableTree();
		for (MenuBackTableTreeVo dir : dirList) {
			System.out.println("目录：" + dir);
			for (MenuBackTableTreeVo menu : dir.getChildren()) {
				System.out.println("	菜单：" + menu);
				for (MenuBackTableTreeVo button : menu.getChildren()) {
					System.out.println("		按钮：" + button);
				}
			}
		}
	}

}
