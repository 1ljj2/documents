var vprocess = new Vue({
        el: '#process',
        data: function () {
            return {
                //---------------------------------控制页面模块参数----------------------
                existRoleQuery: true,
                //---------------------------------接口路径参数-------------------------
                processPath: '/process/process',
                processStepsPath: '/process/processSteps',
                rolePath: '/account/role',
                categoryPath: '/config/category',
                userPath:'/account/user',
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
                //----------------------------------流程参数---------------------
                process: {
                    id: '',
                    processStepId: '',
                    processName: '',
                    remark: '',
                    enable: '',
                    roleId: '',
                    categoryId: '',
                    processStepsList: [],
                },//流程参数
                cloneProcess: {
                    id: '', processStepId: '', processName: '', remark: '', enable: '', roleId: '', categoryId: '',
                },//克隆后的流程参数，用于检验是否编辑流程项本身基本信息
                stepSelected: [],//新增流程时已增加的流程步骤的id和name对象集合
                newStepIdList: [],//新增流程时新增的step返回的主键集合
                index: '',
                //-----------------------------------流程步骤参数---------------------
                fatherStepId: '',//编辑流程时，新增的流程的pStepId
                changeRoleFlag: false,
                stepEditItems: [],//发请求编辑的流程步骤
                stepAddDisabled: false,
                searchStep: 0,
                editRowIndex: '-1',//需要编辑的行的索引值
                confirmEditRowIndex: '-1',//将【编辑】按钮变为【确认】按钮的标志
                processStep: {
                    id: '', stepName: '', pStepId: '', roleId: '', isSign: '', remark: '',
                },
                cloneProcessStep: {
                    id: '', stepName: '', pStepId: '', roleId: '', isSign: '', remark: '',
                },
                stepAdd: {
                    num: '',
                    stepName: '',
                    roleId: '',
                    isSign: '',
                    remark: '',
                    roleName: '',
                    role: '',
                    isSignName: ''
                },
                stepEdit: {
                    num: '',
                    stepName: '',
                    roleId: '',
                    isSign: '',
                    remark: '',
                    roleName: '',
                    role: '',
                    isSignName: ''
                },
                removeStepButtonFlag: false,//删除步骤按钮的控制
                lastStepId: 0,//最后一个流程步骤的id
                stepTableData: [{num: '', stepName: '', roleId: '', isSign: '1', remark: '',}],//流程流程表格整体数据
                //--------------------------------------步骤条参数---------------------
                processStepsList: [],
                //-------------------------------------下拉框参数---------------------
                categoryList: [],//分类对象集合
                roleList: [],//角色对象集合
                //--------------------------------------流程模态框-------------------------
                removeProcessSelectModal: false,
                disableProcessSelectModal: false,
                viewProcessStepModal: false,
                removeProcessModal: false,
                changeEnableProcessModal: false,
                addProcessModal: false,
                editProcessModal: false,
                addStepForProcessModal: false,
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
                this.column.push(HeadActionSlot(true, 400));
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
            /**
             * 在多选模式下有效，只要选中项发生变化时就会触发
             */
            onSelectionChange(selection) {
                this.selection = selection;
            },
            /**
             * TODO  获取所有的服务对象
             */
            getRoleList(roleName) {
                if (IsNotEmpty(roleName)) {
                    setTimeout(() => {
                        let url = this.rolePath + '/listRoleByName';
                        let data = {
                            roleName: roleName
                        };
                        CallAjaxPost(url, data, this.getRoleListSuc);
                    }, 200);
                }
            },
            getRoleListSuc(data) {
                this.roleList = data.obj;
            },
            // /**
            //  * 获取选择框中的角色的id值，并赋值给过滤参数
            //  */
            // getRoleId(roleId) {
            //     this.sConditions.roleId = roleId;
            //     this.process.roleId = roleId;
            //     this.processStep.roleId = roleId;
            // },
            /**
             * 当选择角色的搜索下拉框获得焦点，清空之前的根据name模糊查询的列表值
             */
            clearRoleList() {
                this.roleList = [];
                this.changeRoleFlag = true;
            },
            /**
             * 清除角色信息
             */
            clearRole() {
                this.roleList = [];
                this.processStep.roleId = '';
                this.stepAdd.stepId = '';
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
            // getCategoryId(categoryId) {
            //     this.$forceUpdate();
            //     this.process.categoryId = categoryId;
            //     this.sConditions.categoryId = categoryId;
            // },
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
                //清除历史记录
                this.clearRole();
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
             * 清空process信息
             */
            clearProcess() {
                this.process.id = '';
                this.process.processStepId = '';
                this.process.processName = '';
                this.process.remark = '';
                this.process.roleId = '';
                this.process.categoryId = '';
            },
            /**
             * 检查流程步骤数据格式
             */
            checkProcessStep() {
                if (CheckEmpty(this.stepAdd.stepName, '步骤名称不能为空') ||
                    CheckEmpty(this.stepAdd.role, '请选择一个审批对象') ||
                    CheckEmpty(this.stepAdd.isSign, '请选择是否签章') ||
                    CheckLength(this.stepAdd.stepName, '50', '请控制流程步骤名称在50字以内')) {
                    return true;
                }
            },
            getRoleItem(item) {
                console.log("getRoleItem", item)
                // this.stepAdd.roleName = item.roleName;
                this.stepEdit.roleName = item.roleName;
                this.stepEdit.roleId = item.id;
                this.stepEdit.role = item.roleName;
                console.log("stepEdit", this.stepEdit)
            },
            /**
             * 清除流程步骤新增值
             */
            clearStepAdd() {
                this.stepAdd.num = this.stepAdd.num + 1;
                this.stepAdd.stepName = '';
                this.stepAdd.role.roleName = '';
                this.stepAdd.isSignName = '';
                this.stepAdd.role.id = '';
                this.stepAdd.isSign = '';
                this.stepAdd.role = '';
                this.stepAdd.remark = '';
            },
            /**
             * 清除processSteps数据
             */
            clearProcessSteps() {
                this.stepAdd.num = '';
                this.stepAdd.stepName = '';
                this.stepAdd.role = '';
                this.stepAdd.isSign = '';
                this.stepAdd.remark = '';
                //清除stepTableData新增数据
                this.stepTableData = [{num: '占位', stepName: '占位', roleId: '占位', isSign: '占位', remark: '占位',}];
            },
            /**
             * 新增一个流程步骤
             */
            addStep() {
                console.log(this.stepTableData)
                //对数据进行校验格式
                if (this.checkProcessStep()) {
                    return;
                }
                this.stepAdd.isSignName = ((this.stepAdd.isSign == 0) ? '无需签章' : '需要签章');
                if (IsEmpty(this.stepAdd.remark)) {
                    this.stepAdd.remark = '无';
                }
                let data = {
                    num: this.stepAdd.num,
                    stepName: this.stepAdd.stepName,
                    roleName: this.stepAdd.role.roleName,
                    isSignName: this.stepAdd.isSignName,
                    roleId: this.stepAdd.role.id,
                    isSign: this.stepAdd.isSign,
                    remark: this.stepAdd.remark,
                };
                this.stepTableData.push(data);
                console.log("stepTableData:", this.stepTableData)
                //清除addStep数据
                this.clearStepAdd();
                this.clearRole();
            },
            /**
             * 校验是否为空以及长度限制
             */
            checkProcess() {
                if (CheckEmpty(this.process.processName, '请填写该流程的名称') ||
                    CheckEmpty(this.process.categoryId, '请选择一个分类') ||
                    CheckEmpty(this.process.roleId, '请选择一个服务对象') ||
                    CheckLength(this.process.processName, '50', '请控制流程名称在50字以内')) {
                    return true;
                }
            },
            /**
             * 打开【新增流程项】模态框
             */
            openAddProcessModal() {
                //打开模态框
                this.addProcessModal = true;
                //清除信息
                this.clearProcessSteps();
            },
            /**
             *  新增流程项
             */
            addProcess() {
                //新增前的对得到的数据进行校验
                if (this.checkProcess()) {
                    return;
                }
                if (this.stepTableData.length < 3) {
                    MessageWarning("请添加至少两个流程步骤");
                }
                var stepList = new Array();
                for (let i = 1; i <= this.stepTableData.length - 1; i++) {
                    stepList.push(this.stepTableData[i]);
                }
                console.log(stepList)
                let data = {
                    stepList: stepList,
                    processName: this.process.processName,
                    remark: this.process.remark,
                    roleId: this.process.roleId,
                    categoryId: this.process.categoryId,
                };
                console.log("addProcess参数：", data)
                let url = this.processPath + '/addProcess';
                CallAjaxPost(url, data, this.addProcessSuc);
            },
            addProcessSuc(data) {
                // 关闭模态框
                this.addProcessModal = false;
                MessageSuccess("新增成功");
                // 重新查询数据
                this.filter();
                // 清除信息
                this.clearProcess();
                this.clearRole();
            },
            /**
             * 取消新增流程项
             */
            cancelAddProcess() {
                //关闭模态框
                this.addProcessModal = false;
                //清除信息
                this.clearProcess();
                this.clearRole();
            },
            /**
             * 打开批量删除模态框
             */
            openRemoveProcessSelectModal() {
                // 判断当前多选是否勾选
                if (this.selection.length === 0) {
                    MessageWarning('请先勾选数据，再批量删除');
                    return;
                }
                // 打开模态框
                this.removeProcessSelectModal = true;
            },
            /**
             * 批量删除
             */
            removeProcessSelect() {
                // 关闭模态框
                this.removeProcessSelectModal = false;
                //获取索引集合
                let idList = [];
                for (let i = 0; i < this.selection.length; i++) {
                    idList[i] = this.selection[i].id;
                }
                let data = idList;
                let url = this.processPath + '/removeProcessSelect';
                CallAjaxPost(url, data, this.removeProcessSelectSuc);
            },
            removeProcessSelectSuc(data) {
                MessageSuccess('成功删除 ' + this.selection.length + ' 条记录');
                // 清除多选列表
                this.selection = [];
                // 加载表格数据
                this.filter();
            },
            /**
             * 打开批量禁用模态框
             */
            openDisableProcessSelectModal() {
                // 判断当前多选是否勾选
                if (this.selection.length === 0) {
                    MessageWarning('请先勾选数据，再批量禁用');
                    return;
                }
                // 打开模态框
                this.disableProcessSelectModal = true;
            },
            /**
             * 批量禁用
             */
            disableProcessSelect() {
                // 关闭模态框
                this.disableProcessSelectModal = false;
                //获取索引集合
                let idList = [];
                for (let i = 0; i < this.selection.length; i++) {
                    idList[i] = this.selection[i].id;
                }
                let data = idList;
                let url = this.processPath + '/disableProcessSelect';
                CallAjaxPost(url, data, this.disableProcessSelectSuc);
            },
            disableProcessSelectSuc(data) {
                MessageSuccess('成功禁用 ' + this.selection.length + ' 条记录');
                // 清除多选列表
                this.selection = [];
                // 加载表格数据
                this.filter();
            },
            /**
             * 打开单个删除模态框
             */
            openRemoveProcessModal(index) {
                //获取索引值
                this.process.id = this.nowData[index].id;
                //打开模态框
                this.removeProcessModal = true;
            },
            /**
             * 单个删除
             */
            removeProcess() {
                //关闭模态框
                this.removeProcessModal = false;
                let data = this.process.id;
                let url = this.processPath + '/removeProcess';
                CallAjaxPost(url, data, this.removeProcessSuc);
                // 打开加载提示
                this.loadingMsg = MessageLoading();
            },
            removeProcessSuc(data) {
                // 关闭加载提示
                CloseMessageLoading(this.loadingMsg);
                MessageSuccess('信息删除成功');
                // 重新查询数据
                this.filter();
            },
            /**
             * 打开查看【流程步骤图】模态框
             */
            openViewStepModal(index) {
                this.index = index;
                this.process.id = this.nowData[index].id;
                this.process.processName = this.nowData[index].processName;
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
                if (this.searchStep === 1) {
                    //将步骤信息赋值给表格当前行的流程步骤信息
                    this.nowData[this.index].processStepsList = data.obj;
                }
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
            /**
             * 打开【删除单个流程项步骤】的模态框
             */
            openRemoveStepModal(stepId) {
                //获取步骤的索引值
                this.processStep.id = stepId;
                //打开模态框
                this.removeStepModal = true;
            },
            /**
             * 删除单个流程步骤
             */
            removeStep() {
                //关闭模态框
                this.removeStepModal = false;
                let data = {
                    processId: this.process.id,
                    stepId: this.processStep.id,
                };
                let url = this.processStepsPath + '/removeStepByProcessId';
                CallAjaxPost(url, data, this.removeStepSuc);
                // 打开加载提示
                this.loadingMsg = MessageLoading();
            },
            removeStepSuc(data) {
                // 关闭加载提示
                CloseMessageLoading(this.loadingMsg);
                MessageSuccess('流程步骤删除成功');
                // 重新查询数据
                this.viewProcessStep();
            },
            /**
             * 打开单个禁用/启用的模态框
             */
            openChangeEnableProcessModal(index) {
                //获取索引值
                this.process.id = this.nowData[index].id;
                this.process.enable = this.nowData[index].enable;
                //打开模态框
                this.changeEnableProcessModal = true;
            },
            /**
             * 单个更改流程项启用禁用状态
             */
            changeEnableProcess() {
                //关闭模态框
                this.changeEnableProcessModal = false;
                let data = {
                    id: this.process.id,
                    enable: this.process.enable,
                };
                let url = this.processPath + '/changeEnableProcess';
                CallAjaxPost(url, data, this.changeEnableProcessSuc);
                // 打开加载提示
                this.loadingMsg = MessageLoading();
            },
            changeEnableProcessSuc() {
                // 关闭加载提示
                CloseMessageLoading(this.loadingMsg);
                MessageSuccess('设置成功');
                // 重新查询数据
                this.filter();
            },
            /**
             * 打开编辑流程项本身基本信息的模态框
             */
            openEditProcessModal(index) {
                console.log(this.stepAddDisabled)
                console.log("nowData:", this.nowData[index]);
                this.process.id = this.cloneProcess.id = this.nowData[index].id;
                this.process.processName = this.cloneProcess.processName = this.nowData[index].processName;
                this.process.remark = this.cloneProcess.remark = this.nowData[index].remark;
                this.process.categoryId = this.cloneProcess.categoryId = this.nowData[index].categoryId;
                this.cloneProcess.roleId = this.nowData[index].roleId;//存储roleId
                this.process.roleId = this.nowData[index].roleName;//服务对象的编辑下拉框用roleName赋值
                if (IsNotEmpty(this.nowData[index].processStepsList)) {
                    this.process.processStepsList = this.nowData[index].processStepsList;
                } else {
                    //发起请求，根据processId获取processStepsList
                    console.log(this.process.id)
                    this.viewProcessStep();
                    console.log(this.processStepsList)
                    this.process.processStepsList = this.processStepsList;
                }
                console.log(this.process.processStepsList)
                for (let i = 0; i < this.process.processStepsList.length; i++) {
                    this.process.processStepsList[i].num = i + 1;
                    this.stepTableData.push(this.process.processStepsList[i])
                }
                this.stepAdd.num = this.process.processStepsList.length + 1;
                //打开编辑模态框
                this.editProcessModal = true;
            },
            /**
             * 编辑【当前行】流程步骤
             */
            editRowStep(index, stepRowItem) {
                console.log(index, stepRowItem)
                this.stepAddDisabled = true;
                //给输入框、选择框、单选框。。。赋值
                this.editRowIndex = index;
                this.confirmEditRowIndex = index;
                this.stepEdit.stepName = stepRowItem.stepName;
                this.stepEdit.role = stepRowItem.roleName;
                this.stepEdit.isSign = stepRowItem.isSign;
                this.stepEdit.remark = stepRowItem.remark;
                console.log("是否签章：", this.stepEdit.isSign);
            },
            /**
             * 确认编辑【当前行】流程步骤
             */
            confirmEditRowStep(index, stepRowItem) {
                this.stepAddDisabled = false;
                //定义将要转给请求的数据对象
                var stepEditItem = {
                    id: '', isSign: '', remark: '', roleId: '', stepName: '',
                };
                console.log(index, stepRowItem)
                //console.log(this.stepTableData)//没有id---stepId
                // console.log("所有的流程步骤数据：", this.process.processStepsList)//有id---stepId
                //获取当前行的stepId
                let stepId = this.process.processStepsList[index - 1].id;
                // console.log("当前stepId原来的流程步骤信息：", this.process.processStepsList[index - 1])
                stepEditItem.id = this.process.processStepsList[index - 1].id;
                stepEditItem.stepName = this.stepEdit.stepName;
                stepEditItem.isSign = this.stepEdit.isSign;
                stepEditItem.remark = this.stepEdit.remark;
                // console.log("编辑后的流程步骤：", this.stepEdit)
                if (!this.changeRoleFlag) {
                    //说明没有改变角色，将原来的值赋过来
                    stepEditItem.roleId = this.process.processStepsList[index - 1].roleId;
                } else {
                    //改变了角色
                    // stepEditItem.roleId = this.stepEdit.role.id;
                    stepEditItem.roleId = this.stepEdit.roleId;
                    this.process.processStepsList[index - 1].roleId = this.stepEdit.roleId;
                    this.process.processStepsList[index - 1].roleName = this.stepEdit.roleName;
                    this.changeRoleFlag = false;
                }
                this.stepEditItems.push(stepEditItem)
                // console.log("所有的编辑后的值：", this.stepEditItems)
                //将修改后的值赋值给当前行
                this.process.processStepsList[index - 1].stepName = this.stepEdit.stepName;
                this.process.processStepsList[index - 1].isSign = (this.stepEdit.isSign == 0 ? "无需签章" : "需要签章");
                this.process.processStepsList[index - 1].remark = this.stepEdit.remark;
                //将【确认】按钮转化为【编辑】按钮
                this.confirmEditRowIndex = -1;
                this.editRowIndex = -1;
            },
            /**
             * 编辑模态框中的新增
             */
            addStepByEdit() {
                this.addStep();
                var stepEditItem = {
                    id: '', isSign: '', remark: '', roleId: '', stepName: '',
                };
                //将新增加的值，加进stepEditItems
                console.log("新增的：", this.stepTableData[this.stepTableData.length - 1]);
                stepEditItem.id = -1
                stepEditItem.isSign = this.stepTableData[this.stepTableData.length - 1].isSign
                stepEditItem.stepName = this.stepTableData[this.stepTableData.length - 1].stepName
                stepEditItem.roleId = this.stepTableData[this.stepTableData.length - 1].roleId
                stepEditItem.remark = this.stepTableData[this.stepTableData.length - 1].remark
                this.fatherStepId = this.stepTableData[this.stepTableData.length - 2].id
                this.stepEditItems.push(stepEditItem);
            },
            /**
             * 确认编辑【所有】流程步骤
             */
            // confirmEditSteps() {
            //     console.log("所有的编辑后的值：", this.stepEditItems)
            //     console.log("stepTableData", this.stepTableData)
            //     //发请求，编辑
            // },
            /**
             * 编辑流程项
             */
            editProcess() {
                //检验Process本身的数据没有更改
                // if (this.process.processName === this.cloneProcess.processName &&
                //     (this.process.roleId === this.cloneProcess.roleId || typeof (this.process.roleId) == "string") &&
                //     this.process.categoryId === this.cloneProcess.categoryId &&
                //     this.process.remark === this.cloneProcess.remark ) {
                //     MessageWarning('您未做任何修改!');
                //     return;
                // }
                //编辑前对数据进行校验
                if (this.checkProcess()) {
                    return;
                }
                if (this.process.categoryId === this.cloneProcess.categoryId) {
                    this.process.categoryId = null;
                }
                if (typeof (this.process.roleId) == "string") {
                    this.process.roleId = this.cloneProcess.roleId;
                }
                let data = {
                    fatherStepId: this.fatherStepId,
                    id: this.process.id,
                    processName: this.process.processName,
                    roleId: this.process.roleId,
                    categoryId: this.process.categoryId,
                    remark: this.process.remark,
                    stepList: this.stepEditItems,
                };
                console.log("所有的编辑后的值：", this.stepEditItems)
                console.log("edit的processDATA:", data)
                let url = this.processPath + '/editProcess';
                CallAjaxPost(url, data, this.editProcessSuc);
            },
            editProcessSuc(data) {
                console.log(data)
                // 关闭加载提示
                CloseMessageLoading(this.loadingMsg);
                // 关闭模态框
                this.editProcessModal = false;
                MessageSuccess("修改成功");
                // 重新查询数据
                this.filter();
                //清除信息
                this.clearProcess();
                this.clearRole();
                //清除stepTableData
                this.stepTableData = [{num: '', stepName: '', roleId: '', isSign: '', remark: '',}];
            },
            /**
             * 取消编辑流程项
             */
            cancelEditProcess() {
                //将stepTableData归为原样
                this.stepTableData = [{num: '', stepName: '', roleId: '', isSign: '', remark: '',}];
                // 关闭模态框
                this.editProcessModal = false;
                //清除信息
                this.clearProcess();
                this.clearRole();
            },
        }
    })
;