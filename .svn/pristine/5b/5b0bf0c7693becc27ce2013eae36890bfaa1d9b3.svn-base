var vMenu = new Vue({
        el: '#menu',
        data: function () {
            return {
                menuPath: '/account/menuback',
                permissionsPath: '/account/permissions',
                nowData: [],
                column: [
                    {title: "名称", key: "name", minWidth: 200, tree: true, resizable: true, fixed: 'left'},
                    {
                        title: "图标", key: "icon", minWidth: 150,
                        render: (h, params) => {
                            return h('Icon', {
                                props: {
                                    type: params.row.icon,
                                    size: 24
                                }
                            })
                        }
                    },
                    {
                        title: "类型", key: "type", minWidth: 150,
                        render: (h, params) => {
                            let tagValue;
                            let tagColor;
                            switch (params.row.type) {
                                case 'dir':
                                    tagValue = '目录';
                                    tagColor = 'blue';
                                    break;
                                case 'menu':
                                    tagValue = '菜单';
                                    tagColor = 'green';
                                    break;
                                case 'button':
                                    tagValue = '操作';
                                    tagColor = 'default';
                                    break;
                                default:
                                    break;
                            }
                            return h('span', [
                                h('Tag', {
                                    props: {
                                        color: tagColor
                                    },
                                }, tagValue)
                            ])
                        }
                    },
                    {title: "级别", key: "level", minWidth: 100},
                    {title: "排序号", key: "seq", minWidth: 100},
                    {title: "请求路径", key: "url", minWidth: 200},
                    {
                        title: "是否启用", key: "enable", minWidth: 100,
                        render: (h, params) => {
                            let color = params.row.enable ? '#00ccff' : '#515a6e';
                            return h('span', {
                                style: {
                                    color: color
                                }
                            }, params.row.enable ? '启用' : '禁用')
                        }
                    }
                ], // 表头信息
                loading: true, selection: [],// 表格参数
                loadingMsg: '',// 加载提示
                notice: '',// 提醒对象
                editPermissionsModal: false,// 编辑权限模态框

                permissionData: [],
                permissionColumn: [
                    // {
                    //     type: 'expand',
                    //     width: 50,
                    //     render: (h, params) => {
                    //         console.log(params);
                    //         return h('expandRow', {
                    //             props: {
                    //                 row: params.row
                    //             }
                    //         })
                    //     }
                    // },
                    {title: "路径名称", key: "name", minWidth: 150},
                    {title: "路径描述", key: "description", minWidth: 150},
                    {title: "请求路径", key: "url", minWidth: 250},
                    {title: "权限类型", key: "typeDescription", minWidth: 200},
                    {title: "权限模块", key: "moduleDescription", minWidth: 150},
                    {
                        title: "是否启用", key: "enable", minWidth: 80,
                        render: (h, param) => {
                            let color = param.row.enable ? '#00ccff' : '#515a6e';
                            return h('span', {
                                style: {
                                    color: color
                                }
                            }, param.row.enable ? '启用' : '禁用')
                        }
                    },
                    {title: '操作', slot: 'action', minWidth: 100}
                ],
                editPermission: {},
                permissionLoading: false,
                permissionSearch: '',// 编辑请求权限搜索内容
                permissionSearchColumn: [
                    {title: "路径名称", key: "name", minWidth: 150},
                    {title: "路径描述", key: "description", minWidth: 150},
                    {title: "请求路径", key: "url", minWidth: 250},
                    {title: "权限类型", key: "typeDescription", minWidth: 200},
                    {title: "权限模块", key: "moduleDescription", minWidth: 150},
                    {title: '操作', slot: 'action', minWidth: 100}
                ],
                permissionSearchLoading: false,
                permissionSearchData: [],
                menuBackPermissions: {
                    menuBackId: '', permissionId: ''
                },// 新增菜单权限对象

                menuTypeList: [
                    {name: '目录', value: 'dir'},
                    {name: '菜单', value: 'menu'},
                    {name: '操作', value: 'button'}
                ],// 菜单类型
                menu: {
                    id: '', icon: '', url: '', name: '', parentId: '', type: '', level: '', seq: 0, enable: true,
                },// 菜单
                addMenuModal: false,// 新增菜单模态框
                menuOption: [],


                // ---------------------------


                cloneMenuList: {
                    id: '', url: '', name: '', type: '', parentId: '0', seq: '', enable: '',
                },// 克隆实体类
                sMenuList: {
                    name: ''
                },// 详细搜索信息

                editMenuListModal: false,// 编辑菜单模态框
                removeMenuListModal: false,// 删除菜单模态框
                removeMenuListSelectModal: false,// 批量删除菜单模态框

                disableMenuListModal: false,// 禁用菜单模态框
                disableMenuListSelectModal: false,// 批量禁用菜单模态框
            }
        },
        components: {
            'layout-header': httpVueLoader(PROJECT_NAME + '/templates/layout/layout-header.vue'),
            'layout-sider': httpVueLoader(PROJECT_NAME + '/templates/layout/layout-sider.vue'),
            'layout-footer': httpVueLoader(PROJECT_NAME + '/templates/layout/layout-footer.vue'),
            // 'expandRow': httpVueLoader(PROJECT_NAME + '/templates/config/vue/table-expand-permissions.vue')
        },
        beforeCreate: function () {
            CheckPermissions();
        },
        mounted() {
            this.initPage();
            this.filter();
        },
        methods: {
            // 缩小菜单
            // collapsedSider() {
            //     this.$refs.menuSide.toggleCollapse();
            // },

            /**
             * 页面初始化加载项 表格表头
             */
            initPage() {
                // 设置表头
                this.setTableTitle();
            },

            /**
             * 设置表头
             */
            setTableTitle() {
                // 添加自定义slot-scope
                this.column.push(HeadActionSlot(true, 150));
                // 添加多选
                this.column.unshift(HeadSelection(true));
                console.log(this.column);
            },

            /**
             * 表格查询
             */
            filter() {
                let data = {};
                let url = this.menuPath + '/listMenuTableTree';
                CallAjaxPost(url, data, this.filterSuc);
                // 显示加载
                this.loading = true;
            },
            filterSuc(data) {
                // 取消显示加载
                this.loading = false;
                this.nowData = data.obj;
                // 设置'_showChildren'属性,默认展开
                this.nowData.forEach(data => {
                    data['_showChildren'] = true;
                });
                console.log(this.nowData);
            },


            /**
             * 打开编辑权限模态框
             * @param index
             */
            openEditPermissionsModal(row) {
                console.log('当前按钮数据', row);
                this.editPermissionsModal = true;
                // 设置菜单按钮id
                this.menuBackPermissions.menuBackId = row.id;
                this.getPermissionsData(row.id);
            },

            /**
             * 查询按钮对应的请求权限集合
             */
            getPermissionsData(data) {
                let url = this.permissionsPath + '/listByMenuButtonId';
                CallAjaxPost(url, data, this.getPermissionsDataSuc);
                this.permissionLoading = true;
            },
            getPermissionsDataSuc(data) {
                console.log(data);
                this.permissionLoading = false;
                this.permissionData = data.obj;
            },

            /**
             * 关闭编辑请求权限模态框
             */
            cancelEditPermissionsModal() {
                this.editPermissionsModal = false;
                this.menuBackPermissions.menuBackId = '';
                this.permissionData = [];
                this.permissionSearch = '';
                this.permissionSearchData = [];
            },

            /**
             * 查询搜索的url对应的请求权限
             */
            getPermissionSearchData(value) {
                console.log('搜索的内容为：' + value);
                let data = {
                    url: value
                };
                let url = this.permissionsPath + '/listByUrl';
                CallAjaxPost(url, data, this.getPermissionSearchDataSuc);
                this.permissionSearchLoading = true;
            },
            getPermissionSearchDataSuc(data) {
                this.permissionSearchLoading = false;
                this.permissionSearchData = data.obj;
            },

            /**
             * 添加按钮对应的请求权限
             */
            addPermissions(row) {
                console.log(row);
                this.menuBackPermissions.permissionId = row.id;
                let url = this.permissionsPath + '/insertMenuPermissions';
                CallAjaxPost(url, this.menuBackPermissions, this.addPermissionsSuc);
            },
            addPermissionsSuc(data) {
                MessageSuccess('添加请求权限成功');
                // 查询按钮的请求权限集合
                this.getPermissionsData(this.menuBackPermissions.menuBackId);
                this.permissionSearch = '';

                // 去掉已经添加过的
                // this.permissionSearchData = ;
            },

            /**
             * 删除菜单按钮对应的请求权限
             */
            removePermissions(row) {
                let data = {
                    "menuBackId": this.menuBackPermissions.menuBackId,
                    "permissionId": row.id
                };
                console.log('删除菜单按钮', row, data)
                let url = this.permissionsPath + '/deleteMenuPermissions';
                CallAjaxPost(url, data, this.removePermissionsSuc);
            },
            removePermissionsSuc(data) {
                MessageSuccess('删除请求权限成功');
                // 查询按钮的请求权限集合
                this.getPermissionsData(this.menuBackPermissions.menuBackId);
            },


            openAddMenuModal() {
                this.clearMenu();
                this.setMenuOption();
                this.menu.type = 'dir';
                this.addMenuModal = true;
            },

            /**
             * 将nowData转成级联选择格式的数据
             */
            setMenuOption() {
                this.menuOption = [];
                this.nowData.forEach(dir => {
                    let option = {value: '', label: '', children: []};
                    option.value = dir.id;
                    option.label = dir.name;
                    dir.children.forEach(menu => {
                        let menuOption = {value: '', label: ''};
                        menuOption.value = menu.id;
                        menuOption.label = menu.name;
                        option.children.push(menuOption);
                    });
                    this.menuOption.push(option);
                });
            },

            changeTypeRadio() {
                this.menu.parentId = '';
            },

            /**
             * 新增菜单项信息
             */
            addMenu() {
                console.log('新增参数', this.menu);
                if (this.checkMenu()) {
                    return
                }
                let data = {
                    name: this.menu.name,
                    icon: this.menu.icon,
                    url: this.menu.url,
                    type: this.menu.type,
                    parentId: this.menu.parentId,
                    seq: this.menu.seq
                };
                let url = this.menuPath + '/insert';
                CallAjaxPost(url, data, this.addMenuSuc);
                // 打开加载提示
                this.loadingMsg = MessageLoading();
            },
            addMenuSuc(data) {
                this.addMenuModal = false;
                CloseMessageLoading(this.loadingMsg);
                MessageSuccess('新增菜单项成功！');
                this.filter();
                this.clearMenu();
            },

            /**
             * 添加前检查合法性
             */
            checkMenu() {
                if (this.menu.type === 'dir') {
                    this.menu.parentId = 0;
                }
                if (this.menu.type === 'button') {
                    this.menu.parentId = this.menu.parentId[1];
                }
                if (this.menu.type !== 'button') {
                    if (CheckEmpty(this.menu.url, '菜单路径不能为空！') ||
                        CheckLength(this.menu.url, '100', '菜单路径不能超过100位')) {
                        return true;
                    }
                }
                if (CheckEmpty(this.menu.type, '请选择菜单类型') ||
                    CheckEmpty(this.menu.parentId, '请选择父级菜单') ||
                    CheckEmpty(this.menu.name, '菜单名不能为空！') ||
                    CheckLength(this.menu.name, '100', '菜单名不能超过100位')) {
                    return true;
                }
            },

            cancelAddMenu() {
                // 关闭模态框
                this.addMenuModal = false;
                // 清除菜单
                this.clearMenu();
            },

            /**
             * 清除菜单项信息
             */
            clearMenu() {
                this.menu.id = '';
                this.menu.url = '';
                this.menu.name = '';
                this.menu.parentId = '';
                this.menu.type = '';
                this.menu.level = '';
                this.menu.icon = '';
                this.menu.seq = 0;
                this.menu.enable = true;
            },
// --------------------------------------

            filterInMenuList() {
                console.log('搜索菜单：' + sMenuList.name, this.nowData);

            },

            /**
             * 清除搜索条件
             */
            clearSMenuList() {
                this.clearsMenuList();
                this.filter();
            },

            /**
             * 打开编辑菜单模态框
             *
             * @param index
             *            当前数据索引
             */
            openEditMenuListModal(row) {
                console.log('编辑', row);
                this.menu.id = row.id;
                this.menu.url = row.url;
                this.menu.name = row.name;
                this.menu.type = row.type;
                this.menu.parentId = row.parentId;
                this.menu.seq = row.seq;
                this.menu.icon = row.icon;
                this.menu.enable = row.enable;
                // 打开模态框
                this.editMenuListModal = true;
            },

            /**
             * 编辑前重复检查
             *
             */
            editCheck() {
                // if (CheckEmpty(this.menu.url, '菜单项不能为空！') ||
                //     CheckLength(this.menu.url, '32', '菜单标题不能超过32位')) {
                //     return;
                // }
                this.editMenuList();
            },

            /**
             * 修改菜单项信息
             */
            editMenuList() {
                // 关闭模态框
                this.editMenuListModal = false;

                let data = {
                    id: this.menu.id,
                    url: this.menu.url,
                    name: this.menu.name,
                    parentId: this.menu.parentId,
                    seq: this.menu.seq,
                    type: this.menu.type,
                    icon: this.menu.icon,
                    enable: this.menu.enable,
                };
                console.log('update', data);
                let url = this.menuPath + '/update';
                CallAjaxPost(url, data, this.editMenuListSuc);
                // 打开加载提示
                this.loadingMsg = MessageLoading();
            },

            editMenuListSuc(data) {
                // 关闭加载提示
                CloseMessageLoading(this.loadingMsg);
                MessageSuccess('修改菜单项成功！');
                // 重新查询数据
                this.filter();
                // 清除菜单项信息
                this.clearMenu();
            },

            /**
             * 取消修改菜单项信息
             */
            cancelEditMenuList() {
                // 关闭模态框
                this.editMenuListModal = false;
                // 清除菜单项信息
                this.clearMenu();
            },

            /**
             * 清除菜单项查找信息
             */
            clearsMenuList() {
                this.sMenuList.name = '';
            },

            /**
             * 在多选模式下有效，只要选中项发生变化时就会触发
             *
             * @param selection
             *            已选项数据
             */
            onSelectionChange(selection) {
                this.selection = selection;
                console.log('多选', this.selection);
            },

            /**
             * 打开删除菜单模态框
             */
            openRemoveMenuListSelectModal() {
                // 判断当前多选是否勾选
                if (this.selection.length === 0) {
                    MessageWarning('请先勾选数据');
                    return;
                }
                // 打开模态框
                this.removeMenuListSelectModal = true;
            },

            /**
             * 批量删除数据
             */
            removeMenuListSelect() {
                // 关闭模态框
                this.removeMenuListSelectModal = false;
                let idList = [];
                for (let i = 0; i < this.selection.length; i++) {
                    idList[i] = this.selection[i].id;
                }
                let url = this.menuPath + '/deleteList';
                CallAjaxPost(url, idList, this.removeMenuListSelectSuc);
                // 打开加载提示
                this.loadingMsg = MessageLoading();
            },

            /**
             * 批量删除数据成功回调函数
             */
            removeMenuListSelectSuc(data) {
                // 关闭加载提示
                CloseMessageLoading(this.loadingMsg);
                MessageSuccess('批量删除成功！');
                // 清除多选列表
                this.selection = [];
                // 加载表格数据
                this.filter();
            },

            /**
             * 打开删除菜单项信息模态框
             *
             * @param row 当前数据行
             */
            openRemoveMenuListModal(row) {
                console.log('删除', row.id);
                this.menu.id = row.id;
                this.removeMenuListModal = true;
            },

            /**
             * 删除菜单项信息
             *
             * @param index
             */
            removeMenuList(index) {
                this.removeMenuListModal = false;
                let data = this.menu.id;
                let url = this.menuPath + '/delete';
                CallAjaxPost(url, data, this.removeMenuListSuc);
                // 打开加载提示
                this.loadingMsg = MessageLoading();
            },
            /**
             * 删除菜单项回调函数
             */
            removeMenuListSuc(data) {
                // 关闭加载提示
                CloseMessageLoading(this.loadingMsg);
                MessageSuccess('菜单项删除成功！');
                // 重新查询数据
                this.filter();
            },

            openDisableMenuListSelectModal() {
                // 判断当前多选是否勾选
                if (this.selection.length === 0) {
                    MessageWarning('请先勾选数据');
                    return;
                }
                // 打开模态框
                this.disableMenuListSelectModal = true;
            },

            /**
             * 批量禁用数据
             */
            disableMenuListSelect() {
                // 关闭模态框
                this.disableMenuListSelectModal = false;
                let idList = [];
                for (let i = 0; i < this.selection.length; i++) {
                    idList[i] = this.selection[i].id;
                }
                let url = this.menuPath + '/disableList';
                CallAjaxPost(url, idList, this.disableMenuListSelectSuc);
                // 打开加载提示
                this.loadingMsg = MessageLoading();
            },

            /**
             * 批量禁用数据成功回调函数
             */
            disableMenuListSelectSuc(data) {
                // 关闭加载提示
                CloseMessageLoading(this.loadingMsg);
                MessageSuccess('批量禁用成功！');
                // 清除多选列表
                this.selection = [];
                // 加载表格数据
                this.filter();
            },

            /**
             * 打开禁用菜单项信息模态框
             *
             * @param row 当前数据行
             */
            openDisableMenuListModal(row) {
                console.log('禁用', row.id);
                this.menu.id = row.id;
                this.disableMenuListModal = true;
            },

            /**
             * 禁用菜单项信息
             *
             * @param index
             */
            disableMenuList(index) {
                this.disableMenuListModal = false;
                let data = this.menu.id;
                let url = this.menuPath + '/disable';
                CallAjaxPost(url, data, this.disableMenuListSuc);
                // 打开加载提示
                this.loadingMsg = MessageLoading();
            },
            /**
             * 禁用菜单项回调函数
             */
            disableMenuListSuc(data) {
                // 关闭加载提示
                CloseMessageLoading(this.loadingMsg);
                MessageSuccess('菜单项禁用成功！');
                // 重新查询数据
                this.filter();
            },

        }
    })
;