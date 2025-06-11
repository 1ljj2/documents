var vCourse = new Vue({
    el: '#course',
    data: function () {
        return {
            firstPath: '/account/course',// 请求一级路径
            userPath: '/account/user',
            nowData: [], loading: true, selection: [],
            column: [
                {title: '课程编号', key: 'courseCode'},
                {title: '课程名', key: 'courseName'},
                {title: '备注', key: 'remark'},
                {title: '课程负责人', key: 'leaderUserName'}
            ],// 域表表头
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            course:{
                id:'',courseCode:'',courseName:'',remark:'',state:'',leaderUserName: '', leaderUserId: ''
            },
            sCourse: {
                courseCode:'',courseName:''
            },// 搜索信息
            addEecstateModal: false,// 新增课程信息模态框
            editEecstateModal: false,// 编辑课程信息模态框
            removeEecstateModal: false,// 删除课程信息模态框
            removeEecstateSelectModal: false,// 批量删除课程信息模态框
            addCourseList: false, //新增课程列表模态框
            //不同角色登陆显示不同按钮
            isShow: false,
            isAddCourse: false,
            leaderNum: '',
            allRole: [],

            sMess:{
                id:'',title:'',content:'',
                categoryName:'',categoryId:'',
                state:'',createTime:'',createTimeString:'',stateTime:'',stateTimeString:'',
                departmentId:[],departmentName:'',departmentParName:'',departmentNameStr:'',
                userId:'',userName:'',
                readUserId:[],readUserName:'',readUser:[],
                roleId:[],roleName:'',role:[],
                fileId:'',fileName:'',accessUrl:''
            },// 消息信息

            readUserList: [],//面向用户对象集合

            //课程列表信息

            courseList: {
                courseName: '', courseCode: '', remark: '',courseListId: [], allcourseList: []
            }
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
       // this.showByUserOrRole();
    },
    methods: {
        /**
         * 页面初始化加载项 表格表头
         */
        initPage() {
           // this.column.push(HeadTooltip('remark', '备注'));
            // 添加自定义slot-scope
            this.column.push(HeadActionSlot(true));
            this.column.push(HeadIndex(true))
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
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                courseCode: this.sCourse.courseCode,
                courseName: this.sCourse.courseName,
            };
            let url = '/account/course/queryAllCourse';    //-------》改
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
            this.nowData = data.obj.allCourse.obj.list;
            this.totalNum = data.obj.allCourse.obj.total;
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.allCourse.obj.pageNum === 0 ? 1 : data.obj.allCourse.obj.pageNum;
            this.leaderNum=data.obj.courseLeaderNum;
            this.allRole=data.obj.roleList;
            //consloe.log("")
            console.log("filter负责人",this.leaderNum)
            this.showByUserOrRole();
        },

        //不同用户或者角色登录显示
        showByUserOrRole(){
            console.log("show负责人",this.leaderNum)
            console.log("角色",this.allRole)
            if(this.leaderNum > 0){
                this.isShow=true
            }

            if(this.allRole.indexOf(13)){
                this.isAddCourse=true
            }

        },
        /**
         * 清除搜索条件
         */
        clearSEecstate() {
            this.sCourse.courseCode = '';
            this.sCourse.courseName= '';
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
         * 检查域表信息数据格式
         *
         * @return {boolean} 若数据格式错误,返回true
         */
        checkEecstate() {
            if (CheckEmpty(this.course.courseCode, '请输入课程编号') ||
                CheckLength(this.course.courseCode, '20', '课程编号不能超过20个字符') ||
                CheckEmpty(this.course.remark, '请输入备注') ||
                CheckLength(this.course.remark, '50', '备注不能超过50个字符')
            ){
                return true;
            }
        },

        /**
         * 新增域表信息
         */
        addEecstate() {
            // 检查数据格式
            if (this.checkEecstate()) {
                return;
            }
            // 发送请求
            let data = {
                courseName:this.course.courseName,
                courseCode:this.course.courseCode,
                remark:this.course.remark,
                state:this.course.state,
                leaderUserId: localStorage.getItem("userId") ,
                allUserId:this.sMess.readUserId,
            };
            let url = '/account/course/insertOneCourse';
            CallAjaxPost(url, data, this.addEecstateSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
            //console.log("用户id",this.sMess.readUserId)
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
            this.course.id=this.nowData[index].id;
            this.course.courseCode = this.nowData[index].courseCode;                            //----------》修改
            this.course.courseName = this.nowData[index].courseName;
            this.course.remark = this.nowData[index].remark;
            this.course.state = this.nowData[index].state;
            // 打开模态框
            this.editEecstateModal = true;
        },
        /**
         * 修改域表信息
         */
        editEecstate() {
            // 检查数据格式
            if (this.checkEecstate()) {
                return;
            }
            /* debugger */
            let data = {
                courseCode:this.course.courseCode,
                remark:this.course.remark,
                state:this.course.state,
                id: this.course.id
            };
            let url = '/account/course/updateOneCourse';
            CallAjaxPost(url, data, this.editEecstateSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },
        editEecstateSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);

            // 关闭模态框
            this.editEecstateModal = false;
            MessageSuccess("课程信息修改成功");
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
            this.course.courseName= '';
            this.course.courseCode= '';
            this.course.remark = '';
            //this.readUserList = [];
            this.sMess.readUserId= [];
            //this.clearReadUserList();
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
            let url = this.firstPath + '/deleteSelectedCourses';
            CallAjaxPost(url, data, this.removeEecstateSelectSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeEecstateSelectSuc(data) {
            console.log(data)
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('成功删除记录！');//' + data.length + '
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
            this.course.id = this.nowData[index].id;
            this.removeEecstateModal = true;
        },

        /**
         * 删除域表信息
         *
         * @param index
         */
        removeEecstate() {                        //////------>gai
            this.removeEecstateModal = false;
            let data = this.course.id;
            let url = this.firstPath + '/deleteOneCourse';
            CallAjaxPost(url, data, this.removeEecstateSuc);

            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },

        removeEecstateSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('该条信息删除成功！');
            // 重新查询数据
            this.filter();
        },


        //不同用户或者角色登录显示
        // showByUserOrRole(){
        //     console.log("show负责人",this.leaderNum)
        //     if(this.leaderNum > 0){
        //         this.isShow=true
        //     }
        //
        // },


        /*
        *
        * 获取所有的面向用户对象集合
        * */
        getReadUserList(readUserName) {
            if (IsNotEmpty(readUserName)) {
                setTimeout(() => {
                    let url = this.userPath + '/listUserByName';
                    let data = {
                        userName: readUserName
                    };
                    CallAjaxPost(url, data, this.getReadUserListSuc);
                }, 200);
            }
        },
        getReadUserListSuc(data) {
            this.readUserList = data.obj;
        },

        /**
         * 当选择面向用户的搜索下拉框获得焦点，清空之前的根据name模糊查询的列表值
         */
        clearReadUserList() {
            this.readUserList = [];
        },

        getReadUserIdList1(readUserIdList) {
            // debugger
            let stringReadUser = [];
            let intReadUser = [];
            //按name还是id给选择的分类
            for (let i = 0; i < readUserIdList.length; i++) {
                if (typeof this.sMess.readUserId[i] == "string") {
                    stringReadUser.push(this.sMess.readUserId[i]);
                } else {
                    intReadUser.push(this.sMess.readUserId[i]);
                }
            }
            console.log("stringReadUser:", stringReadUser, "  intReadUser:", intReadUser)
            //将stringReadUser转化为idList
            let data = {
                readUserNameList: stringReadUser,
            };
            let url = this.userPath + '/translateNameToId';
            CallAjaxPost(url, data, this.translateRUNameToIdSuc, null, null, false);
            console.log("translateRUNameToIdSuc:", this.readUserIdFromName)
            // debugger
            //将name转化后的id赋值给intReadUser
            if (IsNotEmpty(this.readUserIdFromName)) {
                for (let j = 0; j < this.readUserIdFromName.length; j++) {
                    intReadUser.push(this.readUserIdFromName[j]);
                }
            }
            //得到最终的radUserIdList
            this.sMess.readUserId = intReadUser;

           // console.log("用户id", this.sMess.readUserId);
        },
        translateRUNameToIdSuc(data) {
            this.readUserIdFromName = data.obj;
        },


        //关闭增加课程列表模态框
        cancelAddCourseList(){
            this.addCourseList=false;
            this.clearCourseListMes();
        },

        /**
         * 根据输入的courseName获得courseList
         */
        listCourseByName(name) {
            let data = {
                name: name,
            };
            let url = this.firstPath + '/listCourseByName';
            // console.log("listTermByName参数：", data)
            CallAjaxPost(url, data, this.listCourseByNameSuc);
        },
        listCourseByNameSuc(data) {
            console.log(data.obj);
            this.courseList.allcourseList = data.obj;
            // console.log("listCourseByNameSuc: ", this.courseList);
        },

        //选择变化之后 修改id
        getReadUserIdList(readUserIdList){
            let intReadUser = [];
            //按name还是id给选择的分类
            for (let i = 0; i < readUserIdList.length; i++) {
                    intReadUser.push(this.courseList.courseListId[i]);
            }
            this.courseList.courseListId=intReadUser;
            console.log("当前选择的id",this.courseList.courseListId)
        },
        //清除课程列表的信息
        clearCourseListMes(){
           this.courseList.courseName='';
            this.courseList.courseCode='';
            this.courseList.remark='';
            this.courseList.courseListId='';
        },

        //新增课程列表
       addCourselist() {
            let data = {
                courseCode: this.courseList.courseCode,
                courseName: this.courseList.courseName,
                remark: this.courseList.remark,
                parentId: 0,
                courseListId:  this.courseList.courseListId,
            };
            let url = this.firstPath + '/addCourseList';
            // console.log("listTermByName参数：", data)
            CallAjaxPost(url, data, this.addCourselistSuc);
        },
        addCourselistSuc(data) {
            //console.log(data.obj);
            //this.courseList.allcourseList = data.obj;
            // console.log("listCourseByNameSuc: ", this.courseList);
            this.clearCourseListMes();
            this.addCourseList=false;
        },
    }
});
