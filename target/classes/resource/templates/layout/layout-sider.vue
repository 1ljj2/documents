<template>
    <div id="layout-sider" class="layout">
        <layout>
            <sider ref="menuSide" hide-trigger collapsible :collapsed-width="78" v-model="isCollapsed">
                <i-menu theme="dark" :active-name="activeName" :open-names="openMenu" @on-select="onSelectMenu"
                        width="auto" ref="sider_menu">
                    <!--如果二级菜单中只有一个页面，那么就只显示这个页面-->
                    <menu-item v-for="submenu in menuList" v-if="submenu.menuList.length==1"
                               :name="submenu.menuList[0].url"
                               :key="submenu.menuList[0].id">
                        <Icon :type="submenu.menuList[0].icon"></Icon>
                        {{submenu.menuList[0].name}}
                    </menu-item>
                    <!--目录+菜单-->
                    <submenu v-for="submenu in menuList" v-if="submenu.menuList.length>1" :name="submenu.url"
                             :key="submenu.id">
                        <template slot="title">
                            <Icon :type="submenu.icon"></Icon>
                            {{submenu.name}}
                        </template>
                        <menu-item v-for="menu in submenu.menuList" :name="menu.url" :key="menu.id">
                            <Icon :type="menu.icon"></Icon>
                            {{menu.name}}
                        </menu-item>
                    </submenu>
                </i-menu>
            </sider>
        </layout>
    </div>
</template>
<script type="text/javascript" src="/templates/layout/js/layout-sider.js"></script>