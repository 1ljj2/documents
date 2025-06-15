var vproQuery = new Vue({
        el: '#proQuery',
        data: function () {
            return {
                //---------------------------------接口路径参数-------------------------
                processPath: '/process/process',
                processStepsPath: '/process/processSteps',
                rolePath: '/account/role',
                categoryPath: '/config/category',
                userPath: '/account/user',
                //---------------------------------其他参数-------------------------
                nowData: [],//表格目前的参数
                componentSize: 'medium',// 组件尺寸
                column: [
                    {title: "流程名称", key: "processName", minWidth: 200},
                    {title: "分类", key: "categoryName", minWidth: 150},
                    {title: "服务角色", key: "roleName", minWidth: 150},
                    {title: "更新时间", key: "stateTimeString", minWidth: 150},
                ], // 表头信息
                loading: true, selection: [],
                loadingMsg: '',// 加载提示
                totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
                sConditions: {processName: '', categoryId: '', roleId: ''},//搜索信息
                //--------------------------------------步骤条参数---------------------
                process: {
                    id: '',
                    processStepId: '',
                    processName: '',
                    processStepsList: [],
                },//流程参数
                processStepsList: [],
                //-------------------------------------下拉框参数---------------------
                categoryList: [],//分类对象集合
                //--------------------------------------流程模态框-------------------------
                viewProcessStepModal: false,
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
            //页面初始化
            this.initPage();
        },
        methods: {
            /**
             * 页面初始化加载项 表格表头
             */
            initPage() {
                // 设置表头
                this.setTableTitle();
                //表格过滤查询
                this.filter();
                //获取分类列表
                this.geCategoryList();
            },
            /**
             * 设置表头
             */
            setTableTitle() {
                //添加“备注”所在列信息
                this.column.push(HeadTooltip('remark', '备注'));
                // 添加自定义slot-scope(操作栏)
                this.column.push(HeadActionSlot(true, 200));
                // 添加序号
                this.column.unshift(HeadIndex(true));
                // 添加多选
                this.column.unshift(HeadSelection(true));
            },
            /**
             * 改变页码
             */
            onPageChange(pageNum) {
                this.pageNum = pageNum;
                this.filter();
            },
            /**
             * 切换每页条数
             */
            onPageSizeChange(pageSize) {
                this.pageSize = pageSize;
                this.filter();
            },
            geCategoryList() {
                setTimeout(() => {
                    let url = this.categoryPath + '/listProcessCategory';
                    CallAjaxGetNoParam(url, this.getCategoryListSuc);
                }, 200);
            },
            getCategoryListSuc(data) {
                console.log("geCategoryListSuc", data)
                this.categoryList = data.obj;
            },
            /**
             * TODO   表格查询
             */
            filter() {
                let data = {
                    processName: this.sConditions.processName,
                    roleId: this.sConditions.roleId,
                    categoryId: this.sConditions.categoryId,
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                };
                let url = this.processPath + '/listByCondition';
                console.log("filter参数：", data)
                CallAjaxPost(url, data, this.filterSuc);
                // 显示加载
                this.loading = true;
            },
            filterSuc(data) {
                // 取消显示加载
                this.loading = false;
                this.nowData = data.obj.list;
                console.log("filterSuc", this.nowData);
                this.totalNum = data.obj.total;
                // 再次设置当前页码,若查询记录为空，设为第一页
                this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
            },
            /**
             * 清空搜索条件
             */
            clearSConditions() {
                this.sConditions.categoryId = '';
                this.sConditions.roleId = '';
                this.sConditions.processName = '';
                //重新查询表格数据
                this.filter();
            },
            /**
             * 打开查看【流程步骤图】模态框
             */
            openViewStepModal(item) {
                console.log(item)
                this.process.id = item.id;
                this.process.processName = item.processName;
                this.searchStep = 1;
                this.viewProcessStep();
                // 打开模态框
                this.viewProcessStepModal = true;
            },
            /**
             * 根据processId查看【流程步骤图】
             */
            viewProcessStep() {
                let data = this.process.id;
                let url = this.processStepsPath + '/viewStepsByProcessId';
                //发同步请求，防止在点击【编辑】的时候出现还没有请求完成就显示出模态框
                CallAjaxPost(url, data, this.viewProcessStepSuc, null, null, false);
            },
            viewProcessStepSuc(data) {
                console.log("viewProcessStepSuc:", data.obj)
                // if (this.searchStep === 1) {
                //     //将步骤信息赋值给表格当前行的流程步骤信息
                //     this.nowData[this.index].processStepsList = data.obj;
                // }
                //将信息赋值给流程步骤条
                this.processStepsList = data.obj;
            },
            /**
             * 退出查看【流程步骤图】
             */
            exitViewStep() {
                //关闭模态框
                this.viewProcessStepModal = false;
            },
        }
    })
;