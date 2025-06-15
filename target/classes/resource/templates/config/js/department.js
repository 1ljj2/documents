var vDepartment = new Vue({
    el: '#department',
    data: function () {
        return {
            firstPath: '/account/department',// 请求一级路径
            nowData: [], loading: true, selection: [],
            componentSize: 'medium',// 组件尺寸
            column: [
                {title: '部门名称', key: 'departmentName'},
                {title: '父部门', key: 'parentName'},
                {title: '等级', key: 'level'},
                {title: '备注', key: 'remark'},
            ],// 域表表头
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            eecstate: {
                id: '', table: '', tableName: '', colm: '', colmName: '', code: '', codeName: '', seq: '',
                remark: ''
            },// 实体类
            department:{
                id: '',departmentName: '', parId: '', level: '', remark: ''
            },//实体类
            sDepartment: {
                id: '',departmentName: '', parId: '', level: '', remark: ''
            },// 搜索信息
            levelAdded:'',
            parIdList:[],//父部门的集合
            addEecstateModal: false,// 新增部门表信息模态框
            editEecstateModal: false,// 编辑部门表信息模态框
            removeEecstateModal: false,// 删除部门表信息模态框
            removeEecstateSelectModal: false,// 批量删除部门表信息模态框
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
        this.getParIdList();
        console.log('===========');
    },
    methods: {
        /**
         * 页面初始化加载项 表格表头
         */
        initPage() {
           // this.column.push(HeadTooltip('remark', '备注'));
            // 添加自定义slot-scope
            this.column.push(HeadActionSlot(true));
            // 添加多选
            this.column.unshift(HeadSelection(true));

            console.log("column数据：", this.column);
        },
        /**
         * 父部门集合查询
         */
        getParIdList() {
            setTimeout(() => {
                let data = {
                    id: this.department.id
                }
                let url = this.firstPath + '/getParIdList';
                CallAjaxPost(url, data, this.getParIdListSuc);
            }, 200);
        },
        getParIdListSuc(data) {
            console.log("getParIdListSuc", data)
            this.parIdList = data.obj;
            console.log("parIdList", this.parIdList)
        },
        // /**
        //  *  TODO  获取所有的部门对象集合
        //  */
        // getDepartmentList(departmentName) {
        //     if (IsNotEmpty(departmentName)) {
        //         setTimeout(() => {
        //             let url = this.firstPath + '/listDepartmentByName';
        //             let data = {
        //                 departmentName: departmentName
        //             };
        //             CallAjaxPost(url, data, this.getDepartmentListSuc);
        //         }, 200);
        //     }
        // },
        // getDepartmentListSuc(data) {
        //     console.log('listDepartmentByName返回：',data)
        //     this.parIdList = data.obj;
        // },
        // /**
        //  * 获取选择框中的部门的id值，并赋值给过滤参数
        //  */
        // getDepartmentId(id) {
        //     this.department.id.push(id);
        // },
        //
        /**
         * 清除部门信息
         */
        clearDepartment() {
            this.parIdList = [];
            this.department.id = '';
        },
        /**
         * 表格过滤查询
         */
        filter() {
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            console.log('部门名称：' + this.sDepartment.departmentName);
            console.log('等级：' + this.sDepartment.level);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                departmentName: this.sDepartment.departmentName,
                level: this.sDepartment.level,
            };
            let url = this.firstPath+'/selectDepartment';
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
            console.log("---------------------------11")
            console.log(data);
            // 取消显示加载
            this.loading = false;
            this.nowData = data.obj.list; //////////////////////////////////////////// ------data.obj.list
            this.totalNum = data.obj.total;
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;

            /* console.log('返回的表名：' + data.obj.list[0].tableName); */
        },

        /**
         * 清除搜索条件
         */
        clearSEecstate() {
            this.sDepartment.id = '';
            this.sDepartment.departmentName = '';
            this.sDepartment.parId = '';
            this.sDepartment.level = '';
            this.sDepartment.remark = '';
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
         * 检查部门信息数据格式
         *
         * @return {boolean} 若数据格式错误,返回true
         */
        checkEecstate() {
            if (CheckEmpty(this.department.departmentName, '请输入部门名称') ||
                CheckLength(this.department.departmentName, '20', '部门名称不能超过20个字符') ||
                // CheckEmpty(this.department.parId, '请输入子部门名称') ||
                // CheckLength(this.department.parId, '20', '子部门名称不能超过20个字符') ||
                // CheckEmpty(this.department.level, '请输入等级') ||
                // CheckLength(this.department.level, '2', '等级不能超过2个字符') ||
                CheckEmpty(this.department.remark, '请输入备注') ||
                CheckLength(this.department.remark, '50', '备注不能超过50个字符'))
                {
                return true;
            }
        },
        getDepartment(item){
            console.log(item)
            this.levelAdded = item.level;
        },
        /**
         * 新增部门信息
         */
        addEecstate() {
            // 检查数据格式
            if (this.checkEecstate()) {
                return;
            }
            // 发送请求
            let data = {
                id: this.department.id,
                departmentName: this.department.departmentName,
                // parId: this.parIdList.id,
                parId: this.department.parId,
                // level: this.parIdList.level,
                level: this.levelAdded,
                remark: this.department.remark,
            };
            let url =this.firstPath+'/insertOneDepartment';   // --------------------------------->
            CallAjaxPost(url, data, this.addEecstateSuc);
            console.log('insertOneDepartment参数：',data)
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },

        /**
         * 新增域表信息回调函数
         *
         * @param data
         *            请求返回参数
         */
        addEecstateSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            console.log("返回数据：", data);
            if (data.obj === "exist") {
                MessageWarning('已存在相同记录');
                return;
            }
            MessageSuccess("新增信息成功");

            this.filter();
            this.clearEecstate();
            this.addEecstateModal = false;
        },
        /**
         * 取消新增域表
         */
        cancelAddEecstate() {
            // 关闭模态框
            this.addEecstateModal = false;
            // 清除域表信息
            this.clearEecstate();
        },

        /**
         * 打开编辑域表信息模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditEecstateModal(index) {
            console.log('nowData:',this.nowData[index])
            this.department.id=this.nowData[index].id;
            this.department.departmentName = this.nowData[index].departmentName;
            this.department.parId = this.nowData[index].parId;
            this.department.level = this.nowData[index].level;
            this.department.remark = this.nowData[index].remark;
            console.log('this.department:',this.department)
            // 打开模态框
            this.editEecstateModal = true;
        },
        /**
         * 修改域表信息
         */
        editEecstate() {                      //-------------------------------------------------->
            // 检查数据格式
            if (this.checkEecstate()) {
                return;
            }
            /* debugger */
            let data = {
                id: this.department.id,
                departmentName: this.department.departmentName,
                parId: this.department.parId,
                level: this.department.level,
                remark: this.department.remark
            };
            let url = this.firstPath+'/edit';
            CallAjaxPost(url, data, this.editEecstateSuc);
            console.log('edit:',data)
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },
        editEecstateSuc(data) {
            console.log('edit返回:',data)
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);

            // 关闭模态框
            this.editEecstateModal = false;
            MessageSuccess("部门信息修改成功");
            // 重新查询数据
            this.filter();
            // 清除域表信息
            this.clearEecstate();
        },
        /**
         * 取消修改域表信息
         */
        cancelEditEecstate() {
            // 关闭模态框
            this.editEecstateModal = false;
            // 清除域表信息
            this.clearEecstate();
        },
        /**
         * 清除域表信息
         */
        clearEecstate() {
            this.department.id = '';
            this.department.departmentName = '';
            this.department.parId = '';
            this.department.level = '';
            this.department.remark = '';
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
        openRemoveEecstateSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                MessageWarning('请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeEecstateSelectModal = true;
        },

        /**
         * 批量删除数据
         */
        removeEecstateSelect() {
            // 关闭模态框
            this.removeEecstateModal = false;
            var idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            CallAjaxPost(url, data, this.removeEecstateSelectSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeEecstateSelectSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('成功删除记录！');
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
        openRemoveEecstate(index) {
            this.department.id = this.nowData[index].id;
            this.removeEecstateModal = true;
        },

        /**
         * 删除域表信息
         *
         * @param index
         */
        removeEecstate() {
            this.removeEecstateModal = false;
            let data = this.department.id;
            let url = this.firstPath + '/delete';
            CallAjaxPost(url, data, this.removeEecstateSuc);

            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },

        removeEecstateSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('域表信息删除成功！');
            // 重新查询数据
            this.filter();
        },


    }
});
