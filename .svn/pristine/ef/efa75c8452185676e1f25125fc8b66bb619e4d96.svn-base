var vpermissions = new Vue({
    el: '#permissions',
    data: function () {
        return {
            permissionsPath: '/account/permissions',
            permissionsTypePath: '/account/permissionsType',
            permissionsModulePath: '/account/permissionsModule',
            nowData: [],//表格目前的参数
            componentSize: 'small',// 组件尺寸
            column: [
                {title: "请求名称", key: "name", minWidth: 200},
                // {title: "描述", key: "description", minWidth: 200},
                // {title: "请求路径", key: "url", minWidth: 200},
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
                },
                {title: "请求类型描述", key: "typeDescription", minWidth: 200},
            ], // 表头信息
            loading: true, selection: [],// 表格参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            SConditions: {moduleId: '', typeId: '', url: ''},//搜索信息
            permissions: {
                id: '', typeName: '', moduleName: '', name: '', description: '', remark: '', url: '', enable: '',
                permissionsModuleId: '', permissionsTypeId: '',
            },
            clonePermissions: {
                id: '', typeName: '', moduleName: '', name: '', description: '', remark: '', url: '', enable: '',
                permissionsModuleId: '', permissionsTypeId: '',
            },
            permissionsType: {
                typeName: '', description: '', remark: ''
            },
            permissionsModule: {
                moduleName: '', description: '', remark: ''
            },
            permissionTypesList: [],//请求类型集合
            permissionModulesList: [],//请求模块集合
            moduleList: [], typeList: [],
            //------------------------------------模态框
            removePermissionSelectModal: false,
            removePermissionModal: false,
            disablePermissionSelectModal: false,
            disablePermissionModal: false,
            addPermissionModal: false,
            editPermissionModal: false,
        }
    },
    components: {
        'layout-header': httpVueLoader(PROJECT_NAME + '/templates/layout/layout-header.vue'),
        'layout-sider': httpVueLoader(PROJECT_NAME + '/templates/layout/layout-sider.vue'),
        'layout-footer': httpVueLoader(PROJECT_NAME + '/templates/layout/layout-footer.vue'),
    },
    beforeCreate: function () {
        CheckPermissions();
    },
    mounted() {
        this.initPage();
        this.filter();
        //获取请求类型
        this.getTypeList()
    },
    methods: {
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
            //添加“描述”所在列信息
            this.column.push(HeadTooltip('description', '描述',false,250));
            //添加“请求路径”所在列信息
            this.column.push(HeadTooltip('url', '请求路径'));
            //添加“模块描述”所在列信息
            this.column.push(HeadTooltip('moduleDescription', '请求模块描述'));
            //添加“备注”所在列信息
            this.column.push(HeadTooltip('remark', '备注'));
            // 添加自定义slot-scope(操作栏)
            this.column.push(HeadActionSlot(true, 180));
            // 添加序号
            this.column.unshift(HeadIndex(true));
            // 添加多选
            this.column.unshift(HeadSelection(true));
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
         * 在多选模式下有效，只要选中项发生变化时就会触发
         *
         * @param selection
         *            已选项数据
         */
        onSelectionChange(selection) {
            this.selection = selection;
        },
        /**
         * 获取所有的类型对象集合
         */
        getTypeList() {
            //定时器，限定输入框输入100毫秒后显示查询数据结果
            setTimeout(() => {
                let url = this.permissionsTypePath + '/listType';
                CallAjaxGetNoParam(url, this.getTypeListSuc);
            }, 200);
        },
        getTypeListSuc(data) {
            this.typeList = data.obj;
        },
        /**
         * 获取选择框中的类型id值
         */
        getList(typeId) {
            //将选择的模块id赋值
            this.permissions.permissionsTypeId = typeId;
        },
        /**
         * 清除类型信息
         */
        clearType() {
            this.typeList = [];
            this.permissions.permissionsTypeId = '';
        },
        /**
         * 获取所有的模块对象集合
         */
        getModulesList(query) {
            if (IsNotEmpty(query)) {
                //定时器，限定输入框输入100毫秒后显示查询数据结果
                setTimeout(() => {
                    let url = this.permissionsModulePath + '/listModuleByName';
                    let data = {
                        query: query
                    };
                    CallAjaxPost(url, data, this.getModulesListSuc);
                }, 200);
            } else {
                this.clearModule();
            }
        },
        getModulesListSuc(data) {
            console.log(data);
            this.moduleList = data.obj;
        },
        /**
         * 获取选择框中的值
         */
        getModule(moduleId) {
            //将选择的模块id赋值
            this.permissions.permissionsModuleId = moduleId;
        },
        /**
         * 清除模块信息
         */
        clearModule() {
            this.moduleList = [];
            this.permissions.permissionsModuleId = '';
        },
        /**
         * 表格查询
         */
        filter() {
            let data = {
                moduleId: this.SConditions.moduleId,
                typeId: this.SConditions.typeId,
                url: this.SConditions.url,
                pageNum: this.pageNum,
                pageSize: this.pageSize,
            };
            let url = this.permissionsPath + '/listPageInfo';
            console.log("filter参数：", data)
            CallAjaxPost(url, data, this.filterSuc);
            // 显示加载
            this.loading = true;
        },
        filterSuc(data) {
            // 取消显示加载
            this.loading = false;
            this.nowData = data.obj.list;
            this.totalNum = data.obj.total;
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
            this.clearModule();
        },
        /**
         * 清空搜索条件
         */
        clearSConditions() {
            this.SConditions.moduleId = '';
            this.SConditions.typeId = '';
            this.SConditions.url = '';
            //重新查询表格数据
            this.filter();
        },
        /**
         * 清空信息
         */
        clearPermission() {
            this.permissions.id = '';
            this.permissions.typeName = '';
            this.permissions.moduleName = '';
            this.permissions.name = '';
            this.permissions.description = '';
            this.permissions.url = '';
            this.permissions.enable = '';
            this.permissions.remark = '';
        },
        /**
         * 校验是否为空
         */
        checkPermissions() {
            if (CheckEmpty(this.permissions.url, '请填写请求路径') ||
                CheckEmpty(this.permissions.permissionsTypeId, '请选择请求类型') ||
                CheckEmpty(this.permissions.permissionsModuleId, '请输入并选择请求所属模块') ||
                CheckEmpty(this.permissions.name, '请填写请求名称') ||
                CheckLength(this.permissions.name, '100', '请控制请求名称在100字以内')) {
                return true;
            }
        },
        /**
         * 新增请求权限
         */
        addPermission() {
            //新增请求权限前的对得到的数据进行校验
            if (this.checkPermissions()) {
                return;
            }
            let data = {
                permissionsTypeId: this.permissions.permissionsTypeId,
                permissionsModuleId: this.permissions.permissionsModuleId,
                name: this.permissions.name,
                description: this.permissions.description,
                remark: this.permissions.remark,
                url: this.permissions.url,
            };
            console.log(data)
            let url = this.permissionsPath + '/addPermission';
            CallAjaxPost(url, data, this.addPermissionSuc);
            // 显示加载
            this.loading = true;
        },
        addPermissionSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            // 关闭模态框
            this.addPermissionModal = false;
            MessageSuccess("新增成功");
            // 重新查询数据
            this.filter();
            // 清除信息
            this.clearPermission();
            this.clearModule();
        },
        /**
         * 取消新增请求权限
         */
        cancelAddPermission() {
            //关闭模态框
            this.addPermissionModal = false;
            //清除信息
            this.clearPermission();
            this.clearModule();
        },
        /**
         * 打开编辑请求权限模态框
         * @param index 索引
         */
        openEditPermissionModal(index) {
            this.permissions.id = this.clonePermissions.id = this.nowData[index].id;
            this.permissions.permissionsTypeId = this.clonePermissions.permissionsTypeId = this.nowData[index].permissionsTypeId;
            this.permissions.permissionsModuleId = this.clonePermissions.permissionsModuleId = this.nowData[index].permissionsModuleId;
            if (this.nowData[index].moduleName === null) {
                this.permissions.moduleName = this.clonePermissions.moduleName = '无所属模块';
            } else {
                this.permissions.moduleName = this.clonePermissions.moduleName = this.nowData[index].moduleName;
            }
            this.permissions.name = this.clonePermissions.name = this.nowData[index].name;
            this.permissions.description = this.clonePermissions.description = this.nowData[index].description;
            this.permissions.remark = this.clonePermissions.remark = this.nowData[index].remark;
            this.permissions.url = this.clonePermissions.url = this.nowData[index].url;
            this.permissions.enable = this.clonePermissions.enable = this.nowData[index].enable;
            this.showPermissionsType();
            // 打开模态框
            this.editPermissionModal = true;
        },
        /**
         * 根据当前的请求类型id查询出请求类型
         */
        showPermissionsType() {
            let data = {
                id: this.permissions.permissionsTypeId
            };
            let url = this.permissionsTypePath + '/showPermissionsType';
            CallAjaxPost(url, data, this.showPermissionsTypeSuc);
        },
        showPermissionsTypeSuc(data) {
            if (data.obj === null) {
                this.permissions.typeName = '无请求类型';
            } else {
                this.permissions.typeName = data.obj;
            }
        },
        /**
         * 编辑请求权限
         */
        editPermission() {
            //对数据进行校验
            if (this.permissions.permissionsTypeId === this.clonePermissions.permissionsTypeId &&
                this.permissions.permissionsModuleId === this.clonePermissions.permissionsModuleId &&
                this.permissions.moduleName === this.clonePermissions.moduleName &&
                this.permissions.description === this.clonePermissions.description &&
                this.permissions.remark === this.clonePermissions.remark &&
                this.permissions.url === this.clonePermissions.url &&
                this.permissions.enable === this.clonePermissions.enable &&
                this.permissions.name === this.clonePermissions.name) {
                MessageWarning('您未做任何修改!');
                return;
            }
            //对数据进行校验
            if (this.checkPermissions()) {
                return;
            }
            let data = {
                id: this.permissions.id,
                permissionsTypeId: this.permissions.permissionsTypeId,
                permissionsModuleId: this.permissions.permissionsModuleId,
                name: this.permissions.name,
                description: this.permissions.description,
                remark: this.permissions.remark,
                url: this.permissions.url,
                enable: this.permissions.enable,
            };
            let url = this.permissionsPath + '/editPermission';
            CallAjaxPost(url, data, this.editPermissionSuc);
            // 显示加载
            this.loading = true;
        },
        editPermissionSuc(data) {
            console.log(data);
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            // 关闭模态框
            this.editPermissionModal = false;
            MessageSuccess("修改成功");
            // 重新查询数据
            this.filter();
            //清除信息
            this.clearPermission();
            this.clearModule();
        },
        /**
         * 取消编辑请求权限
         */
        cancelEditPermission() {
            // 关闭模态框
            this.editPermissionModal = false;
            //清除信息
            this.clearPermission();
            this.clearModule();
        },
        /**
         * 打开批量删除模态框
         */
        openRemovePermissionSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                MessageWarning('请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removePermissionSelectModal = true;
        },
        /**
         * 批量删除
         */
        removePermissionSelect() {
            // 关闭模态框
            this.removePermissionSelectModal = false;
            //获取索引集合
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.permissionsPath + '/removePermissionSelect';
            CallAjaxPost(url, data, this.removePermissionSelectSuc);
            // 显示加载
            this.loading = true;
        },
        removePermissionSelectSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('成功删除 ' + this.selection.length + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },
        /**
         * 打开批量禁用模态框
         */
        openDisablePermissionSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                MessageWarning('请先勾选数据，再批量禁用');
                return;
            }
            // 打开模态框
            this.disablePermissionSelectModal = true;
        },
        /**
         * 批量禁用
         */
        disablePermissionSelect() {
            // 关闭模态框
            this.disablePermissionSelectModal = false;
            //获取索引集合
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.permissionsPath + '/disablePermissionSelect';
            CallAjaxPost(url, data, this.disablePermissionSelectSuc);
            // 显示加载
            this.loading = true;
        },
        disablePermissionSelectSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('成功禁用 ' + this.selection.length + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },
        /**
         * 打开单个删除模态框
         * @param index 索引
         */
        openRemovePermissionModal(index) {
            //获取索引值
            this.permissions.id = this.nowData[index].id;
            //打开模态框
            this.removePermissionModal = true;
        },
        /**
         * 单个删除
         */
        removePermission() {
            //关闭模态框
            this.removePermissionModal = false;
            let data = this.permissions.id;
            let url = this.permissionsPath + '/removePermission';
            CallAjaxPost(url, data, this.removePermissionSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },
        removePermissionSuc() {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('信息删除成功');
            // 重新查询数据
            this.filter();
        },
        /**
         * 打开查看请求类型详情模态框
         * @param index 索引
         */
        openViewTypeModal(index) {
            this.permissions.id = this.nowData[index].id;
            this.viewTypeByPermissionId();
            // 打开模态框
            this.viewTypeDetailsModal = true;
        },
        /**
         * 打开单个禁用模态框
         * @param index 索引
         */
        openDisablePermissionModal(index) {
            //获取索引值
            this.permissions.id = this.nowData[index].id;
            //打开模态框
            this.disablePermissionModal = true;
        },
        /**
         * 单个禁用
         */
        disablePermission() {
            //关闭模态框
            this.disablePermissionModal = false;
            let data = this.permissions.id;
            let url = this.permissionsPath + '/disablePermission';
            CallAjaxPost(url, data, this.disablePermissionSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },
        disablePermissionSuc() {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('禁用成功');
            // 重新查询数据
            this.filter();
        },

    }
});