let vRole = new Vue({
    el: '#role',
    data: function () {
        return {
            rolePath: '/account/role',// 角色一级路径
            menuRolePath: '/account/menuback',// 角色菜单关联表一级路径
            nowData: [], loading: true, selection: [],// 表格参数
            column: [
                {title: '角色key', key: 'roleKey',minWidth: 200},
                {title: '角色名称', key: 'roleName',minWidth: 160},
                {
                    title: "是否启用", key: "enable", minWidth: 60,
                    render: (h, params) => {
                        let color = params.row.enable ? '#00ccff' : '#515a6e';
                        return h('span', {
                            style: {
                                color: color
                            }
                        }, params.row.enable ? '启用' : '禁用')
                    }
                }
            ],
            role: {
                id: '', roleKey: '', roleName: '', description: '', codeName: '', enable: ''
            },// 实体类
            sRole: {
                roleKey: '', roleName: ''
            },// 搜索信息
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            seeDataTreeModal: false,//查看权限树模态框
            editDataTreeModal: false,//编辑权限模态框
            addRoleModal: false,// 新增角色信息模态框
            removeRoleSelectModal: false,
            disableRoleSelectModal: false,
            editRoleModal: false,// 编辑角色信息模态框

            sUserMenu: {
                userId: ''
            },
            ownMenuTree: [],//角色已有的权限树集合
            allMenuTree: [],//所有权限树集合
            ownMenuTreeIdList: [],//具有的权限id集合
        }
    },
    components: {
        'layout-header': httpVueLoader(PROJECT_NAME + '/templates/layout/layout-header.vue'),
        'layout-sider': httpVueLoader(PROJECT_NAME + '/templates/layout/layout-sider.vue'),
        'layout-footer': httpVueLoader(PROJECT_NAME + '/templates/layout/layout-footer.vue')
    },
    beforeCreate: function () {
        CheckPermissions();
    },
    mounted() {
        this.initPage();
        this.filter();
    },
    methods: {
        /**
         * 页面初始化
         */
        initPage() {
            // 设置表头
            this.setTableTitle();
        },

        /**
         * 设置表头
         */
        setTableTitle() {
            //添加“备注”所在列信息
            this.column.push(HeadTooltip('description', '角色描述'));
            // 添加自定义slot-scope
            this.column.push(HeadActionSlot(false, 250));
            // 添加序号
            this.column.unshift(HeadIndex());
            // 添加多选
            this.column.unshift(HeadSelection());
        },

        /**
         * 过滤查询表格信息
         */
        filter() {
            if (CheckLength(this.sRole.roleKey, '64', '角色key不能超过64个字符') ||
                CheckLength(this.sRole.roleName, '64', '角色名称不能超过64个字符')) {
                return;
            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                roleKey: this.sRole.roleKey,
                roleName: this.sRole.roleName,
            };
            let url = this.rolePath + '/listPageInfo';
            CallAjaxPost(url, data, this.filterSuc);
            // 显示加载
            this.loading = true;
        },
        filterSuc(data) {
            console.log('表格数据', data.obj.list);
            // 取消显示加载
            this.loading = false;
            this.nowData = data.obj.list;
            this.totalNum = data.obj.total;
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },

        /**
         * 查看当前角色对应的权限树
         */
        getOwnMenuTree(index) {
            let url = this.menuRolePath + '/listOwnTree';
            let data = this.nowData[index].id;
            CallAjaxPost(url, data, this.getOwnMenuTreeSuc)
        },
        getOwnMenuTreeSuc(data) {
            console.log('当前角色对应的权限树', data);
            this.ownMenuTree = data.obj;
            this.seeDataTreeModal = true;
        },

        /**
         * 展示编辑权限树
         */
        getEditDataTree(index) {
            //获取权限树和已有的权限树
            let url = this.menuRolePath + '/listAllDirAndOwnTree';
            this.role.id = this.nowData[index].id;
            let data = this.role.id;
            console.log('查询的角色为' + this.nowData[index].roleKey + '，id为' + data);
            CallAjaxPost(url, data, this.getEditDataTreeSuc);
        },

        getEditDataTreeSuc(data) {
            console.log('编辑权限树', data);
            this.allMenuTree = data.obj.dirVoList;
            let tree = data.obj.ownDirVoList;
            // 先置空
            this.ownMenuTreeIdList = [];
            // 再根据已有权限设置勾中的子节点
            // TODO 可以使用递归来操作，这里先套个娃
            for (let i = 0; i < tree.length; i++) {
                for (let j = 0; j < tree[i].children.length; j++) {
                    if (tree[i].children[j].children.length === 0) {
                        // 菜单下没有操作权限，只添加菜单
                        this.ownMenuTreeIdList.push(tree[i].children[j].id);
                        continue;
                    }
                    // 有相关操作，添加操作按钮
                    for (let k = 0; k < tree[i].children[j].children.length; k++) {
                        this.ownMenuTreeIdList.push(tree[i].children[j].children[k].id);
                    }
                }
            }
            console.log(tree, this.ownMenuTreeIdList);
            this.editDataTreeModal = true;
        },
        /**
         * 提交子节点父节点id进行增加与删除
         */
        editOk() {
            // 目前半选中的节点的 roleKey 所组成的数组
            let halfCheckedKeys = this.$refs.editTree.getHalfCheckedKeys();
            // 目前被选中的节点的 roleKey 所组成的数组
            let checkedKeys = this.$refs.editTree.getCheckedKeys();
            let menuIdList = checkedKeys.concat(halfCheckedKeys);
            if (IsEmpty(menuIdList)) {
                MessageWarning('该用户应该至少有一条权限');
                return;
            }
            let data = {
                menuIdList: menuIdList,
                roleId: this.role.id
            };
            console.log(data);
            let url = this.menuRolePath + '/updateRoleMenu';
            CallAjaxPost(url, data, this.editOkSuc);
            this.loadingMsg = true
        },
        editOkSuc(data) {
            this.loadingMsg = false;
            MessageSuccess('编辑成功');
        },

        /**
         * 清除编辑对象
         */
        clearEdit() {
            this.role.id = '';
        },

        /**
         * 清除搜索条件
         */
        clearSRole() {
            this.sRole.roleKey = '';
            this.sRole.roleName = '';
        },

        /**
         * 清除角色信息
         */
        clearRole() {
            this.role.id = '';
            this.role.roleKey = '';
            this.role.roleName = '';
            this.role.description = '';
        },

        /**
         * 切换每页条数
         *
         * @param pageSize
         *            换后的每页条数
         */
        onPageSizeChange(pageSize) {
            this.pageSize = pageSize;
            this.filter();
        },
        /**
         * 改变页码
         *
         * @param pageNum
         *            改变后的页码
         */
        onPageChange(pageNum) {
            this.pageNum = pageNum;
            this.filter();
        },
        /**
         * 在多选模式下有效，只要选中项发生变化时就会触发
         *
         * @param selection
         *            已选项数据
         */
        onSelectionChange(selection) {
            this.selection = selection;
            console.log(this.selection);
        },

        openRemoveRoleSelectModal() {
            if (this.selection.length === 0) {
                MessageWarning('请先勾选数据');
                return;
            }
            this.removeRoleSelectModal = true;
        },

        removeRoleSelect() {
            this.removeRoleSelectModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            let data = idList;
            let url = this.rolePath + '/deleteList';
            CallAjaxPost(url, data, this.removeRoleSelectSuc);
            this.loadingMsg = MessageLoading();
        },

        removeRoleSelectSuc(data) {
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('成功删除 ' + data.obj + ' 个角色!');
            this.selection = [];
            this.filter();
        },

        openDisableRoleSelectModal() {
            if (this.selection.length === 0) {
                MessageWarning('请先勾选数据');
                return;
            }
            this.disableRoleSelectModal = true;
        },

        disableRoleSelect() {
            this.disableRoleSelectModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            let data = idList;
            let url = this.rolePath + '/disableList';
            CallAjaxPost(url, data, this.disableRoleSelectSuc);
            this.loadingMsg = MessageLoading();
        },

        disableRoleSelectSuc(data) {
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('成功禁用 ' + data.obj + ' 个角色!');
            this.selection = [];
            this.filter();
        },

        /**
         * 检查数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkRole() {
            if (CheckEmpty(this.role.roleKey, '角色key不能为空') ||
                CheckLength(this.role.roleKey, '30', '角色key不能超过30个字符') ||
                CheckEmpty(this.role.roleName, '角色名称不能为空') ||
                CheckLength(this.role.roleName, '30', '角色名称不能超过30个字符')) {
                return true;
            }
        },

        /**
         * 取消新增角色
         */
        cancelAddRole() {
            // 关闭模态框
            this.addRoleModal = false;
            // 清除角色信息
            this.clearRole();
        },

        /**
         * 新增角色信息
         */
        addRole() {
            // 检查数据格式
            if (this.checkRole()) {
                return;
            }
            // 发送请求
            let data = {
                roleKey: this.role.roleKey,
                roleName: this.role.roleName,
                description: this.role.description
            };
            let url = this.rolePath + '/insert';
            CallAjaxPost(url, data, this.addRoleSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();

        },

        /**
         * 新增角色信息回调函数
         * @param data 请求返回参数
         */
        addRoleSuc(data) {
            CloseMessageLoading(this.loadingMsg);
            this.addRoleModal = false;
            MessageSuccess("新增角色信息成功");
            this.filter();
            this.clearRole();
        },

        /**
         * 打开编辑角色信息模态框
         * @param index 当前数据索引
         */
        openEditRoleModal(index) {
            console.log(this.nowData[index]);
            this.role.id = this.nowData[index].id;
            this.role.roleKey = this.nowData[index].roleKey;
            this.role.roleName = this.nowData[index].roleName;
            this.role.description = this.nowData[index].description;
            this.role.enable = this.nowData[index].enable;
            this.editRoleModal = true;
        },

        /**
         * 取消修改角色信息
         */
        cancelEditRole() {
            // 关闭模态框
            this.editRoleModal = false;
            // 清除角色信息
            this.clearRole();
        },

        /**
         * 修改角色信息
         */
        editRole() {
            // 检查数据格式
            if (this.checkRole()) {
                return;
            }
            console.log(this.role);
            let data = {
                id: this.role.id,
                roleKey: this.role.roleKey,
                roleName: this.role.roleName,
                enable: this.role.enable,
                description: this.role.description
            };
            console.log(data);
            let url = this.rolePath + '/update';
            CallAjaxPost(url, data, this.editRoleSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },
        editRoleSuc(data) {
            CloseMessageLoading(this.loadingMsg);
            this.editRoleModal = false;
            MessageSuccess("角色信息修改成功");
            this.filter();
            this.clearRole();
        },

    }
});