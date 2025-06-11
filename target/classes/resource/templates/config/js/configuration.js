var vConfiguration = new Vue({
    el: '#configuration',
    data: function () {
        return {
        	configPath: '/config/configuration',// 请求一级路径
            nowData: [], loading: true, selection: [],
            componentSize: 'medium',// 组件尺寸
            column: [
                {title: '配置项名称', key: 'name', width: 200},
                {title: '描述', key: 'description', width: 300},
                {title: '配置项值', key: 'value', width: 150},
                {title: '是否启用', key: 'isEnable', width: 100,
                	render: (h, params) => {
                    	let dName = params.row.isEnable;
                    	if (dName=='1') {
                    		return h('span', {
                                style: {
                                    color: '#85ce61'
                                },order:'1'
                            },  '启用')
						};
						if (dName=='0') {
							return h('span', {
	                            style: {
	                                color: '#e6a23c'
	                            },order:'2'
	                        },  '禁用')
						};
                    }},
                {title: '更新日期', key: 'stateTimeString', width: 150},
            ],// 域表表头
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            config: {
                id: '', name: '', description: '', value: '', isEnable: '', state: '',createTime: '', stateTime: '',
            },// 实体类
            sConfig: {
            	description: '', isEnable: '', 
            },// 搜索信息
            isEnableList: [{
                value: '1',
                label: '启用'
              }, {
                value: '0',
                label: '禁用'
              }],
            addConfigModal: false,// 新增配置表信息模态框
            editConfigModal: false,// 编辑配置表信息模态框
            removeConfigModal: false,// 删除配置表信息模态框
            removeConfigSelectModal: false,// 批量删除配置表信息模态框
            disableConfigModal: false,// 禁用配置表项模态框
            isableConfigModal: false,//启用配置表项模态框
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
        console.log('===========');
    },
    methods: {
        /**
         * 页面初始化加载项 表格表头
         */
        initPage() {
            this.column.push(HeadTooltip('remark', '备注'));
            // 添加自定义slot-scope
            this.column.push(HeadActionSlot(true));
            // 添加多选
            this.column.unshift(HeadSelection(true));

            console.log("column数据：", this.column);
        },


        /**
         * 表格过滤查询
         */
        filter() {
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            console.log('描述：' + this.sConfig.description);
            console.log('状态：' + this.sConfig.isEnable);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                description: this.sConfig.description,
                isEnable: this.sConfig.isEnable,
            };
            let url = this.configPath + '/listConfigByCondition';
            CallAjaxPost(url, data, this.filterSuc);
            // 显示加载
            this.loading = true;
        },

        /**
         * 表格过滤查询回调函数
         *
         * @param data
         *            请求返回参数
         */
        filterSuc(data) {
            console.log(data);
            // 取消显示加载
            this.loading = false;
            this.nowData = data.obj.list;
            this.totalNum = data.obj.total;
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },

        /**
         * 清除搜索条件
         */
        clearSConfig() {
            this.sConfig.description = '';
            this.sConfig.isEnable = '';
            // 重新查询表格数据
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
         * 检查配置表信息数据格式
         *
         * @return {boolean} 若数据格式错误,返回true
         */
        checkConfig() {
            if (CheckEmpty(this.config.name, '配置项名称不能为空') ||
                CheckLength(this.config.name, '50', '配置项名称不能超过50个字符') ||
                CheckEmpty(this.config.description, '描述不能为空') ||
                CheckLength(this.config.description, '200', '描述不能超过200个字符') ||
                CheckLength(this.config.value, '20', '表不能超过20个字符')) {
                return true;
            }
        },

        /**
         * 新增配置表信息
         */
        addConfig() {
            // 检查数据格式
            if (this.checkConfig()) {
                return;
            }
            // 发送请求
            let data = {
            		name: this.config.name,
                    description: this.config.description,
                    value: this.config.value,
                    isEnable: this.config.isEnable,
            };
            let url = this.configPath + '/addConfig';
            CallAjaxPost(url, data, this.addConfigSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },

        /**
         * 新增配置表信息回调函数
         *
         * @param data
         *            请求返回参数
         */
        addConfigSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            console.log("返回数据：", data);
            if (data.obj === "exist") {
                MessageWarning('已存在相同记录');
                return;
            }
            MessageSuccess("新增信息成功");
            this.filter();
            this.addConfigModal = false;
        },
        /**
         * 取消新增域表
         */
        cancelAddConfig() {
            // 关闭模态框
            this.addConfigModal = false;
            // 清除域表信息
            this.clearConfig();
        },

        /**
         * 打开编辑配置表信息模态框
         *
         * @param index
         *            当前数据索引
         */

        openEditConfigModal(index){
        	this.config.id = this.nowData[index].id;
        	this.config.name = this.nowData[index].name;
        	this.config.description = this.nowData[index].description;
        	this.config.value = this.nowData[index].value;
        	this.config.isEnable = this.nowData[index].isEnable;
        	//打开编辑模态框
        	this.editConfigModal = true;
        },
        /**
         * 修改配置表信息
         */
        editConfig() {
            // 检查数据格式
            if (this.checkConfig()) {
                return;
            }
            /* debugger */
            let data = {
                id: this.config.id,
                name: this.config.name,
                description: this.config.description,
                value: this.config.value,
                isEnable: this.config.isEnable,
            };
            let url = this.configPath + '/editConfig';
            CallAjaxPost(url, data, this.editConfigSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },
        editConfigSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);

            // 关闭模态框
            this.editConfigModal = false;
            MessageSuccess("配置表信息修改成功");
            // 重新查询数据
            this.filter();
            // 清除域表信息
            this.clearConfig();
        },
        /**
         * 取消修改配置表信息
         */
        cancelEditConfig() {
            // 关闭模态框
            this.editConfigModal = false;
            // 清除域表信息
            this.clearConfig();
        },
        /**
         * 清除域表信息
         */
        clearConfig() {
            this.config.id = '';
            this.config.name = '';
            this.config.description = '';
            this.config.value = '';
            this.config.isEnable = '';
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

        /**
         * 打开删除数据模态框
         */
        openRemoveConfigSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                MessageWarning('请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeConfigSelectModal = true;
        },

        /**
         * 批量删除数据
         */
        removeConfigSelect() {
            // 关闭模态框
            this.removeConfigModal = false;
            var idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            let data = idList;
            let url = this.configPath + '/removeConfigSelect';
            CallAjaxPost(url, data, this.removeConfigSelectSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeConfigSelectSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('成功删除 ' + data.obj + ' 条记录！');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
         * 打开删除数据模态框
         *
         * @param index
         *            当前数据索引
         */
        openRemoveConfig(index) {
            this.config.id = this.nowData[index].id;
            this.removeConfigModal = true;
        },

        /**
         * 删除配置表项信息
         *
         * @param index
         */
        removeConfig() {
            this.removeConfigModal = false;
            let data = this.config.id;
            let url = this.configPath + '/removeConfig';
            CallAjaxPost(url, data, this.removeConfigSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },
        removeConfigSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('配置表项信息删除成功！');
            // 重新查询数据
            this.filter();
        },

        /**
         * 打开禁用数据模态框
         *
         * @param index
         *            当前数据索引
         */
        openDisableConfigModal(index) {
            this.config.id = this.nowData[index].id;
            this.disableConfigModal = true;
        },

        /**
         * 禁用配置表项信息
         *
         * @param index
         */
        disableConfig() {
            this.disableConfigModal = false;
            let data = this.config.id;
            let url = this.configPath + '/disableConfig';
            CallAjaxPost(url, data, this.disableConfigSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },
        disableConfigSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('配置表项信息禁用成功！');
            // 重新查询数据
            this.filter();
        },
        
        /**
         * 打开启用数据模态框
         *
         * @param index
         *            当前数据索引
         */
        openIsableConfigModal(index) {
            this.config.id = this.nowData[index].id;
            this.isableConfigModal = true;
        },

        /**
         * 启用配置表项信息
         *
         * @param index
         */
        isableConfig() {
            this.isableConfigModal = false;
            let data = this.config.id;
            let url = this.configPath + '/isableConfig';
            CallAjaxPost(url, data, this.isableConfigSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },
        isableConfigSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('配置表项信息启用成功！');
            // 重新查询数据
            this.filter();
        },

    }
});
