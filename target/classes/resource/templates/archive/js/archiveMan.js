var varchiveMan = new Vue({
        el: '#archiveMan',
        data: function () {
            return {
                //---------------------------------接口路径参数-------------------------
                processPath: '/process/process',
                processStepsPath: '/process/processSteps',
                rolePath: '/account/role',
                categoryPath: '/config/category',
                userPath: '/account/user',
                yearTermPath: '/account/yearTerm',
                fileInfoPath: '/file/file',
                coursePath: '/account/course',
                archiveTemPath: '/archive/archiveTem',
                archiveTemEmpFilePath: '/archive/archiveTemEmpFile',
                archiveExamplePath: '/archive/archiveExample',
                //------------------------------------------------------------------------------------！！公有！！-------------------------
                categoryList: [], termList: [], courseList: [], userList: [], stateList: [], processList: [],courseLists: [],
                //------------------------------------------------------------------------------------！！档案模板管理参数！！-------------------------
                temConditions: {archiveTemName: '', termId: '', categoryId: ''},//搜索信息
                deleteFileId:'',//用于记录需要删除的文件的id
                archiveTemData: [],//card列表目前的参数
                componentSize: 'medium',// 组件尺寸
                loadingMsg: '',// 加载提示
                temTotalNum: 0, temPageNum: 1, temPageSize: 10,  // 分页参数
                archiveTem: {
                    id: '',
                    categoryId: '',
                    categoryName: '',
                    courseId: '',
                    courseName: '',
                    name: '',
                    number: '',
                    remark: '',
                    processId: '',
                    processName: '',
                    stateTimeString: '',
                    termId: '',
                    termName: '',
                    courseListId: ''
                },
                cloneArchiveTem: {
                    termId: '',
                    termName: '',
                    courseId: '',
                    courseName: '',
                    categoryId: '',
                    categoryName: '',
                    processId: '',
                    processName: '',
                    courseListId: '',
                },
                fileTemList: [],//档案所包含的文件池文件集合
                fileList: [],// 文件模板本地上传的列表
                fileTemToArchiveList: [],//给档案模板的文档模板集合，用于新增
                matchFileColumn: [
                    {title: "文档模板名称", key: "fileName", minWidth: 200},
                    {title: "编号", key: "number", minWidth: 120},
                ], // 表头信息
                matchFileTableData: [],//表格数据
                matchFileTotalNum: 0, matchFilePageNum: 1, matchFilePageSize: 5,  // 分页参数
                //--------------------------------------文件池----------------------------------
                filePoolData: [],
                filePoolTotalNum: 0, filePoolPageNum: 1, filePoolPageSize: 10,  // 分页参数
                nowArchiveName: '',//当前文件池所属档案模板的名称
                //---------------------------------------------------模态框---------------------------
                editArchiveTemModal: false,
                addArchiveTemModal: false,
                removeMatchFileTemModal: false,
                removeFilePoolTemModal: false,
                //--------------------------------------！！系统档案监管参数！！-------------------------
                archiveConditions:{
                	userArchiveNumber:'', userArchiveName:'', leaderId:'', userId:''
                },//搜索参数
                archiveTableData:[],//表格目前的参数
                archiveComponentSize: 'medium',// 组件尺寸
                archiveColumn:[
                	{title: "用户档案编号", key: "userArchiveNumber", minWidth: 150},
                	{title: "用户档案名称", key: "userArchiveName", minWidth: 150},
                	{title: "负责人", key: "leaderName", minWidth: 100},
                	{title: "创建者", key: "userName", minWidth: 100},
                	{title: "档案模板", key: "archiveTemplateName", minWidth: 200},
                	{title: "流程名称", key: "processName", minWidth: 200},
                	{title: "审核状态", key: "auditState", minWidth: 80,
	                	render: (h, params) => {
	                    	let dName = params.row.auditState;
	                    	if (dName=='A') {
	                    		return h('span', {
	                                style: {
	                                    color: '#838B8B'
	                                },order:'1'
	                            },  '未审核')
							};
							if (dName=='B') {
								return h('span', {
		                            style: {
		                                color: '#3A5FCD'
		                            },order:'2'
		                        },  '审核中')
							};
							if (dName=='C') {
	                    		return h('span', {
	                                style: {
	                                    color: '#00FF00'
	                                },order:'3'
	                            },  '已发布')
							};
							if (dName=='D') {
								return h('span', {
		                            style: {
		                                color: '#FF0000'
		                            },order:'4'
		                        },  '审核不通过')
							};
	                    }},
                	{title: "审核意见", key: "auditOpinion", minWidth: 200},
                	{title: "是否启用", key: "isEnable", minWidth: 80,
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
                	{title: "状态", key: "state", minWidth: 80,
	                	render: (h, params) => {
	                    	let dName = params.row.state;
	                    	if (dName=='A') {
	                    		return h('span', {
	                                style: {
	                                    color: '#85ce61'
	                                },order:'1'
	                            },  '在用')
							};
							if (dName=='X') {
								return h('span', {
		                            style: {
		                                color: '#e6a23c'
		                            },order:'2'
		                        },  '删除')
							};
	                    }},
                	{title: "更新日期", key: "stateTimeString", minWidth: 200},
                ],
                archiveLoading: true, selection: [],
                archiveTotalNum: 0, archivePageNum: 1, archivePageSize: 10,  // 分页参数
                archiveExample:{
                	id:'',userArchiveNumber:'',userArchiveName:'',leaderId:'',leaderName:'',
                	userId:'',userName:'',archiveTemplateId:'',archiveTemplateName:'',
                	processId:'',processName:'',auditState:'',auditOpinion:'',isEnable:'',
                	state:'',stateTimeString:'',stateTime:'',fileIds:[],fileId:'',fileName:'',
                },
                archiveFileTableData:[],//表格目前的参数
                archiveFileComponentSize: 'medium',// 组件尺寸
                archiveFileLoading: true, selection: [],
                archiveFileTotalNum: 0, archiveFilePageNum: 1, archiveFilePageSize: 10,  // 分页参数
                archiveFileColumn:[
                	{title: "文件名", key: "fileName", minWidth: 150},
                ],
                //----------------------模态框-----------------------------------
                archiveFileModal:false,
                //----------------------设置点击对应的id----------------------------
//                archiveFileId:'0',
                //添加自己的文件模板的次数
                addFileOfAudit: 0,
                //添加自己的文件模板的id集合
                addFileOfAuditList: [],

                //展示课程名
                allCourseNames:[],


                /*
          增加课程的模态框是否显示
          * */
                addSomeCourse:false,

                //选择的课程id 集合
                myCourseCheckList:[],

                /*课程列表*/
                MyCoursesListss:[],


                //-=----------------编辑
                editSomeCourse:false, //编辑课程标志
                myCourseCheckListtoedit:[],
                editAllcourse:[],

                editAllcourseList:[],
                editCatogeryId:'',

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
            //获取分类列表
            this.getCategoryList();
        },
        methods: {
            /**
             * 页面初始化加载项 表格表头
             */
            initPage() {
                //获取文档表格数据
                this.getArchiveTemData();
                //设置匹配文档模板给档案模板的表头
                this.setMatchingFileTableTitle();
                //设置文档监管表头
                this.setChargingArchiveTableTitle();
                //获取档案表格数据
                this.getChargingArchiveTableData();
                //获取文件表头信息
                this.setArchiveFileTableTitle();
            },
            //TODO--------------------------------------------------------------------！！公有！！--------------------------------
            /**
             * 根据输入的termName获得termList
             */
            listTermByName(name) {
                let data = {
                    name: name,
                };
                let url = this.yearTermPath + '/listTermByName';
                console.log("listTermByName参数：", data)
                CallAjaxPost(url, data, this.listTermByNameSuc);
            },
            listTermByNameSuc(data) {
                this.termList = data.obj;
                console.log("listTermByNameSuc: ", this.termList);
            },
            /**
             * 根据输入的courseName获得courseList
             */
            listCourseByName(name) {
                let data = {
                    name: name,
                };
                let url = this.coursePath + '/listCourseByName';
                console.log("listTermByName参数：", data)
                CallAjaxPost(url, data, this.listCourseByNameSuc);
            },
            listCourseByNameSuc(data) {
                this.courseList = data.obj;
                console.log("listCourseByNameSuc: ", this.courseList);
            },

            /**
             * 根据输入的courseName获得课程列表
             */
            listCoursesByName(name) {
                let data = {
                    name: name,
                };
                let url = this.coursePath + '/listCoursesByName';
                console.log("listTermByName参数：", data)
                CallAjaxPost(url, data, this.listCoursesByNameSuc);
            },
            listCoursesByNameSuc(data) {
                this.courseLists = data.obj;
                console.log("listCourseByNameSuc: ", this.courseLists);
            },

            /**
             * 获取档案分类列表
             */
            getCategoryList() {
                setTimeout(() => {
                    let url = this.categoryPath + '/listArchiveCategory';
                    CallAjaxGetNoParam(url, this.getCategoryListSuc);
                }, 200);
            },
            getCategoryListSuc(data) {
                console.log("getCategoryListSuc", data)
                this.categoryList = data.obj;
            },
            /**
             * 根据用户名查询用户列表
             */
            listUserByName(name) {
                let data = {
                    userName: name,
                };
                let url = this.userPath + '/listUserByName';
                console.log("listUserByName参数：", data)
                CallAjaxPost(url, data, this.listUserByNameSuc);
            },
            listUserByNameSuc(data) {
                this.userList = data.obj;
                console.log("listUserByNameSuc: ", this.courseList);
            },
            /**
             * 根据流程名称查询流程列表
             */
            listProcessByName(name) {
                let data = {
                    processName: name,
                };
                let url = this.processPath + '/listProcessByName';
                console.log("listProcessByName参数：", data)
                CallAjaxPost(url, data, this.listProcessByNameSuc);
            },
            listProcessByNameSuc(data) {
                this.processList = data.obj;
                console.log("listProcessByNameSuc: ", this.courseList);
            },
            /**
             * 清除搜索下拉框数据
             */
            clearList() {
                this.courseList = [];
                this.termList = [];
                this.userList = [];
                this.stateList = [];
                this.fileList= [];
            },
            //TODO---------------------------------------------------------------------------！！档案模板管理！！--------------------------------
            /**
             * 改变页码
             */
            onTemPageChange(pageNum) {
                this.temPageNum = pageNum;
                this.getArchiveTemData();
            },
            /**
             * 切换每页条数
             */
            onTemPageSizeChange(pageSize) {
                this.temPageSize = pageSize;
                this.getArchiveTemData();
            },
            /**
             * 设置表头
             */
            setFileTemTableTitle() {
                //添加“备注”所在列信息
                this.temColumn.push(HeadTooltip('remark', '备注'));
                // 添加自定义slot-scope(操作栏)
                this.temColumn.push(HeadActionSlot(true, 260));
                // 添加序号
                this.temColumn.unshift(HeadIndex(true));
            },
            /**
             * 获取档案模板
             */
            getArchiveTemData() {
                let data = {
                    archiveTemName: this.temConditions.archiveTemName,
                    termId: this.temConditions.termId,
                    categoryId: this.temConditions.categoryId,
                    pageNum: this.temPageNum,
                    pageSize: this.temPageSize,
                };
                let url = this.archiveTemPath + '/listArchiveTemByCondition';
                console.log("getArchiveTemData参数：", data)
                CallAjaxPost(url, data, this.getArchiveTemDataSuc);
                // 显示加载
                this.temLoading = true;
            },
            getArchiveTemDataSuc(data) {
                // 取消显示加载
                this.temLoading = false;
                this.archiveTemData = data.obj.list;
                //获取默认显示档案模板的文件池
                if (IsNotEmpty(this.archiveTemData)) {
                    this.archiveTem.id = data.obj.list[0].id;
                    this.nowArchiveName = data.obj.list[0].name;
                    this.getFilePool();
                }
                console.log(data)
                console.log("getArchiveTemDataSuc", this.archiveTemData);
                this.temTotalNum = data.obj.total;
                // 再次设置当前页码,若查询记录为空，设为第一页
                this.temPageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
                //清除历史记录
                this.clearList();
            },
            /**
             * 清除档案模板搜索条件
             */
            clearTemConditions() {
                this.temConditions.archiveTemName = null;
                this.temConditions.termId = null;
                this.temConditions.categoryId = null;
                this.getArchiveTemData();
            },
            /**
             * 清除【档案模板】信息
             */
            clearTemInfo() {
                this.archiveTem.id = '';
                this.archiveTem.categoryId = '';
                this.archiveTem.categoryName = '';
                this.archiveTem.courseId = '';
                this.archiveTem.courseName = '';
                this.archiveTem.name = '';
                this.archiveTem.number = '';
                this.archiveTem.processId = '';
                this.archiveTem.processName = '';
                this.archiveTem.remark = '';
                this.archiveTem.stateTimeString = '';
                this.archiveTem.termId = '';
                this.archiveTem.termName = '';
                this.archiveTem.courseListId= '';
            },
            /**
             * 检验档案模板数据有效性
             */
            checkArchiveTem() {
                if (CheckEmpty(this.archiveTem.name, '档案模板名称不能为空') ||
                    CheckEmpty(this.archiveTem.categoryId, '请选择该文档模板的分类') ||
                    CheckEmpty(this.archiveTem.termId, '请选择该文档模板所属的学年学期') ||
                    //CheckEmpty(this.archiveTem.courseId, '请选择该文档模板所属的课程') ||
                    CheckLength(this.archiveTem.name, '50', '请控制档案模板名称在50字以内')) {
                    return true;
                }
            },
            //--------------------------------------------------TODO  编辑档案模板----------


            /*
            *
            *编辑课程
            * */

            editCourse(){
                 this.listCourseByName()
                 this.myCoursesLists()
                // console.log("课程 课程列表：,",this.courseList)
                this.editSomeCourse=true;
            },

            //关闭编辑 选择课程
            cancelEditCourse(){
                this.myCourseCheckListtoedit=[];
                this.editSomeCourse=false;
            },

            //确认编辑 选择课程
            ensureEditCourse(){
                this.editAllcourse=[]
                console.log("编辑信息,",this.myCourseCheckListtoedit)
                this.editSomeCourse=false;

                for (let j = 0; j < this.myCourseCheckListtoedit.length; j++) {
                    for (let i = 0; i < this.courseList.length; i++) {
                        if (this.myCourseCheckListtoedit[j] === this.courseList[i].id) {
                            this.editAllcourse.push(this.courseList[i].name);
                            break;
                        }
                    }
                }
                for (let j = 0; j < this.myCourseCheckListtoedit.length; j++) {
                    for (let i = 0; i < this.MyCoursesListss.length; i++) {
                        if (this.myCourseCheckListtoedit[j] === this.MyCoursesListss[i].id ) {
                            this.editAllcourse.push(this.MyCoursesListss[i].name);
                            break;
                        }
                    }
                }
                console.log('被选中的值为:' + this.editAllcourse)

                console.log("被选中的值的 id",this.myCourseCheckListtoedit)






            },

            /**
             * 打开【编辑档案模板】模态框
             */
            openEditArchiveModal(item) {
                console.log(item)
                this.editAllcourse=item.allCourseNames;
                 this.editAllcourseList=item.allCoursesList;
                 this.editCatogeryId=item.categoryId;
                if(item.parentId == "0"){
                    this.archiveTem.courseListId=this.cloneArchiveTem.courseName = item.courseName;
                    this.cloneArchiveTem.courseListId = item.courseId;
                }
                else{
                    this.archiveTem.courseId = this.cloneArchiveTem.courseName = item.courseName;
                    this.cloneArchiveTem.courseId = item.courseId;
                }
                this.archiveTem.id = item.id;
                this.archiveTem.remark = item.remark;
                this.archiveTem.number = item.number;
                this.archiveTem.name = item.name;
                this.archiveTem.categoryId = this.cloneArchiveTem.categoryName = item.categoryName;
                this.cloneArchiveTem.categoryId = item.categoryId;

                this.archiveTem.processId = this.cloneArchiveTem.processName = item.processName;
                this.cloneArchiveTem.processId = item.processId;
                this.archiveTem.termId = this.cloneArchiveTem.termName = item.termName;
                this.cloneArchiveTem.termId = item.termId;
                //打开编辑模态框
                this.editArchiveTemModal = true;
            },
            /**
             * 取消【编辑档案模板】
             */
            cancelEditArchiveTem() {
                // 关闭模态框
                this.editArchiveTemModal = false;
                // 清除信息
                this.clearTemInfo()
                this.clearList();
            },
            /**
             * 编辑档案模板
             */
            editArchiveTem() {
                console.log(this.archiveTem)
                //编辑前对数据进行校验
                if (this.checkArchiveTem()) {
                    return;
                }
                if(this.editAllcourseList==this.myCourseCheckListtoedit){
                    console.log("没改1111111")
                    this.myCourseCheckListtoedit=[]
                }
                let data = {
                    id: this.archiveTem.id,
                    name: this.archiveTem.name,
                    number: this.archiveTem.number,
                    remark: this.archiveTem.remark,
                    editcourseLists:this.myCourseCheckListtoedit,
                    // categoryId:this.editCatogeryId,
                };
                //检验学期
                if (!(this.cloneArchiveTem.termId == this.archiveTem.termId || typeof (this.archiveTem.termId) == "string")) {
                    data["termId"] = this.archiveTem.termId;
                }
                //检验课程
                // if (!(this.cloneArchiveTem.courseId == this.archiveTem.courseId || typeof (this.archiveTem.courseId) == "string")) {
                //     data["courseId"] = this.archiveTem.courseId;
                // }
                //检验课程列表
                // if (!(this.cloneArchiveTem.courseListId == this.archiveTem.courseListId || typeof (this.archiveTem.courseListId) == "string")) {
                //     data["courseListId"] = this.archiveTem.courseListId;
                // }
                //检验分类
                // if (!(this.cloneArchiveTem.categoryId == this.archiveTem.categoryId || typeof (this.archiveTem.categoryId) == "string")) {
                //     data["categoryId"] = this.archiveTem.categoryId;
                // }
                //检验流程
                // if ((this.cloneArchiveTem.processId == this.archiveTem.processId || typeof (this.archiveTem.processId) == "string")) {
                //     data["processId"] = this.archiveTem.processId;
                // }
                //检验学期
                if (!(this.cloneArchiveTem.termId == this.archiveTem.termId || typeof (this.archiveTem.termId) == "string")) {
                    data["termId"] = this.archiveTem.termId;
                }
                //检验分类
                if (!(this.cloneArchiveTem.categoryId == this.archiveTem.categoryId || typeof (this.archiveTem.categoryId) == "string")) {
                    data["categoryId"] = this.archiveTem.categoryId;
                }
                console.log(this.cloneArchiveTem.processId + "----" + this.archiveTem.processId);
                //检验流程
                if (!(this.cloneArchiveTem.processId === this.archiveTem.processId || typeof (this.archiveTem.processId) == "string")) {
                    data["processId"] = this.archiveTem.processId;
                }
                console.log("editArchiveTem数据:", data)
                let url = this.archiveTemPath + '/editArchiveTem';
                CallAjaxPost(url, data, this.editArchiveTemSuc);
                // 打开加载提示
                this.loadingMsg = MessageLoading();
            },
            editArchiveTemSuc() {
                // 关闭加载提示
                CloseMessageLoading(this.loadingMsg);
                // 关闭模态框
                this.editArchiveTemModal = false;
                MessageSuccess("修改成功");
                // 重新查询数据
                this.getArchiveTemData();
                //清除信息
                this.clearTemInfo()
                this.clearList();
            },
            //---------------------------------------------------TODO  新增档案模板----------------

            /*
            *
            * 查询课程列表*/
            myCoursesLists(){
                let data = {
                    name: name,
                };
                let url = this.coursePath + '/listCoursesByName';
                console.log("listTermByName参数：", data)
                CallAjaxPost(url, data, this.myCoursesListsSuc);
            },
            myCoursesListsSuc(data){
                console.log(data)
                console.log(data.obj)
                this.MyCoursesListss=data.obj
                console.log("listCourseByNameSuc: 课程列表");
            },
            // /*
            // 增加课程的模态框是否显示
            // * */
            // addSomeCourse:false,
            /*
            *
            *新增档案模板 关联课程信息
            * */
            addCourse(){
                this.allCourseNames=[]
                this.listCourseByName()
                this.myCoursesLists()
              console.log("程信息,",this.courseList)
              this.addSomeCourse=true;

            },

            /*
            * 关闭增加课程 模态框
            * */
            cancelAddCourse(){
                this.myCourseCheckList=[];
                this.addSomeCourse=false;
                this.allCourseNames=[];
            },

            /*
            * 确认增加课程*/
            ensureAddCourse(){
                console.log("zkjsdnf")
                console.log(this.myCourseCheckList)
                this.addSomeCourse=false;
                for (let j = 0; j < this.myCourseCheckList.length; j++) {
                    for (let i = 0; i < this.courseList.length; i++) {
                        if (this.myCourseCheckList[j] === this.courseList[i].id) {
                            this.allCourseNames.push(this.courseList[i].name);
                            break;
                        }
                    }
                }
                for (let j = 0; j < this.myCourseCheckList.length; j++) {
                    for (let i = 0; i < this.MyCoursesListss.length; i++) {
                        if (this.myCourseCheckList[j] === this.MyCoursesListss[i].id ) {
                            this.allCourseNames.push(this.MyCoursesListss[i].name);
                            break;
                        }
                    }
                }
                console.log('被选中的值为:' + this.allCourseNames)
            },

            /**
             * 打开【新增档案模板】模态框
             */
            openAddArchiveTemModal() {
                //打开模态框
                this.addArchiveTemModal = true;
            },
            /**
             * 取消新增档案模板
             */
            cancelAddArchiveTem() {
                this.allCourseNames=[];
                // 关闭模态框
                this.addArchiveTemModal = false;
                // 清除信息
                this.clearTemInfo();
                this.clearList();
            },
            /**
             * 改变页码
             */
            onMatchFilePageChange(pageNum) {
                this.matchFilePageNum = pageNum;
                //获取匹配文档模板给档案
                this.listMatchFile();
            },
            /**
             * 设置表头
             */
            setMatchingFileTableTitle() {
                // 添加自定义slot-scope(操作栏)
                this.matchFileColumn.push(HeadActionSlot(true, 180));
                // 添加序号
                this.matchFileColumn.unshift(HeadIndex(true));
            },
            /**
             * 新增档案模板时：   学期下拉框获得值
             */
            termSelected() {
                this.listMatchFile();
            },
            /**
             * 新增档案模板时：   课程下拉框获得值
             */
            courseSelected() {
                this.listMatchFile();
            },
            /**
             *  课程和学期都选好时，获取匹配文档模板给档案模板参考
             */
            listMatchFile() {
                if (IsNotEmpty(this.archiveTem.termId) && IsNotEmpty(this.archiveTem.courseId)) {
                    let data = {
                        termId: this.archiveTem.termId,
                        courseId: this.archiveTem.courseId,
                        pageNum: this.matchFilePageNum,
                        pageSize: this.matchFilePageSize,
                    };
                    console.log("listMatchFile参数：", data)
                    let url = this.fileInfoPath + '/listMatchFile';
                    CallAjaxPost(url, data, this.listMatchFileSuc);
                }
            },
            listMatchFileSuc(data) {
                console.log("listMatchFileSuc:", data)
                this.matchFileTableData = data.obj.list;
                this.matchFileTotalNum = data.obj.total;
                this.matchFilePageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
            },
            /**
             * 打开下载【匹配文件给档案】的模态框
             */
            downMatchFileTem(index) {
                console.log(index)
                console.log("this.matchFileTableData",this.matchFileTableData)
                downloadFileEncrypt(this.matchFileTableData[index].fileId)
            },
            /**
             * 打开【删除匹配文件给档案】的模态框
             */
            openRemoveMatchFileTemModal(index) {
                //获取索引值
                // this.deleteFileId = this.matchFileTableData[index].fileId;
                // console.log("this.deleteFileId",this.deleteFileId)
                this.matchFileTableData.splice(index,1)

                //打开模态框
                // this.removeMatchFileTemModal = true;
                // console.log(index)
            },
            /**
             * 删除匹配文件给档案
             */
            removeMatchFileTem() {

            },
            /**
             * 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
             * @param file 当前文件
             * @param fileList 文件列表
             */
            handleChange(file, fileList) {
                this.fileList = fileList;
                // this.fileTem.fileName = this.fileList[0].raw.name;
                console.log("this.fileList", this.fileList)
            },
            /**
             * 文件列表移除文件时的钩子
             * @param file 移除的文件
             * @param fileList 文件列表
             */
            handleRemove(file, fileList) {
                this.fileList = fileList;
                // this.fileTem.fileName = '';
            },
            /**
             * 点击文件列表中已上传的文件时的钩子
             * @param file 当前文件
             */
            handlePreview(file) {
                console.log(file);
            },
            /**
             * 文件超出个数限制
             */
            handleExceedLimit() {
                MessageWarning('已超出最大文件个数');
            },
            /**
             * 新增【档案模板】
             */
            addArchiveTem() {
                // MessageSuccess("成功");
                // MessageLoading();
                // for(let i=0;i<this.fileList.length;i++){
                //     this.fileList[i]["termId"]=this.archiveTem.termId;
                //     this.fileList[i]["courseId"]=((this.archiveTem.courseId == '') ? this.archiveTem.courseListId : this.archiveTem.courseId);
                // }
                //this.fileList[props.$index].courseId= this.courseId='' ? this.archiveTem.courseListId : this.courseId
                //this.fileList[props.$index].termId=this.archiveTem.processId
                //console.log("当前文件",this.fileList)
                //this.fileList[0]["termId"]=this.archiveTem.termId;
                //this.fileList[0]["courseId"]=((this.archiveTem.courseId == '') ? this.archiveTem.courseListId : this.archiveTem.courseId);
                if (this.fileList.length > 0) {


                console.log("当前文件", this.fileList);
                SubmitFileList(this.fileList, this.addMyAuditFileTemSuc, "A", -1, this.archiveTem.termId, this.myCourseCheckList, -1,"flag")
            }
                else {


                    console.log("matchFileTableData",this.matchFileTableData);
                    for (let i=0;i<this.matchFileTableData.length;i++){
                        this.fileTemToArchiveList.push(this.matchFileTableData[i].fileId)
                    }
                    // 新增前对数据进行校验
                    if (this.checkArchiveTem()) {
                        return;
                    }
                    //检验给档案模板的文档模板是否为空
                    // if (this.fileTemToArchiveList.length<1) {
                    //     MessageWarning("请保证该档案模板中含有文档模板");
                    // }
                    let data = {
                        name: this.archiveTem.name,
                        number: this.archiveTem.number,
                        remark: this.archiveTem.remark,
                        termId: this.archiveTem.termId,
                        courseId: this.archiveTem.courseId,
                        courseListId: this.archiveTem.courseListId,
                        fileIdList: this.fileTemToArchiveList,
                        courseListS:this.myCourseCheckList,
                    };
                    //分类
                    if (!(this.cloneArchiveTem.categoryId == this.archiveTem.categoryId || typeof (this.archiveTem.categoryId) == "string")) {
                        data["categoryId"] = this.archiveTem.categoryId;
                    }
                    //流程
                    if (!(this.cloneArchiveTem.processId == this.archiveTem.processId || typeof (this.archiveTem.processId) == "string")) {
                        data["processId"] = this.archiveTem.processId;
                    }
                    console.log("addArchiveTem参数：",data)
                    let url = this.archiveTemPath + '/addArchiveTem';
                    CallAjaxPost(url, data, this.addArchiveTemSuc);
                }
            },
            // addArchiveTemSuc() {
            //     // 关闭模态框
            //     this.addArchiveTemModal = false;
            //     MessageSuccess("档案模板新增成功");
            //     // 重新查询数据
            //     this.getArchiveTemData();
            //     // 清除信息
            //     this.clearTemInfo()
            //     this.clearList();
            // },

            addMyAuditFileTemSuc(data1){
                console.log("增加文件之后：",data1)
                this.addFileOfAudit++;
                this.addFileOfAuditList.push(data1.obj)
                if(this.addFileOfAudit==this.fileList.length) {
                    console.log("增加文件的数量成功")
                    console.log("增加文件的数量成功返回的id集合",this.addFileOfAuditList)
                    console.log("matchFileTableData",this.matchFileTableData);
                    for (let i=0;i<this.matchFileTableData.length;i++){
                        this.fileTemToArchiveList.push(this.matchFileTableData[i].fileId)
                    }
                    for(let j=0;j<this.addFileOfAuditList.length;j++){
                        this.fileTemToArchiveList.push(this.addFileOfAuditList[j])
                    }
                    console.log("匹配到自己和原来的id集合",this.fileTemToArchiveList)
                    // 新增前对数据进行校验
                    if (this.checkArchiveTem()) {
                        return;
                    }
                    //检验给档案模板的文档模板是否为空
                    // if (this.fileTemToArchiveList.length<1) {
                    //     MessageWarning("请保证该档案模板中含有文档模板");
                    // }
                    let data = {
                        name: this.archiveTem.name,
                        number: this.archiveTem.number,
                        remark: this.archiveTem.remark,
                        termId: this.archiveTem.termId,
                        courseId: this.archiveTem.courseId,
                        courseListId: this.archiveTem.courseListId,
                        fileIdList: this.fileTemToArchiveList,
                        courseListS:this.myCourseCheckList,
                    };
                    //分类
                    if (!(this.cloneArchiveTem.categoryId == this.archiveTem.categoryId || typeof (this.archiveTem.categoryId) == "string")) {
                        data["categoryId"] = this.archiveTem.categoryId;
                    }
                    //流程
                    if (!(this.cloneArchiveTem.processId == this.archiveTem.processId || typeof (this.archiveTem.processId) == "string")) {
                        data["processId"] = this.archiveTem.processId;
                    }
                    console.log("addArchiveTem参数：",data)
                    let url = this.archiveTemPath + '/addArchiveTem';
                    CallAjaxPost(url, data, this.addArchiveTemSuc);
                }
            },
            addArchiveTemSuc() {
                // 关闭模态框
                this.addArchiveTemModal = false;
                // CloseMessageLoading();
                MessageSuccess("档案模板新增成功");
                // 重新查询数据
                this.getArchiveTemData();
                // 清除信息
                this.clearTemInfo()
                this.clearList();
            },
            //-----------------------------------------------------TODO  文件池-------------------------
            /**
             * 改变页码
             */
            onFilePoolPageChange(pageNum) {
                this.filePoolPageNum = pageNum;
                //获取匹配文档模板给档案
                this.getFilePool();
            },
            /**
             * 获取文档模板池
             */
            getFilePool(item) {
                console.log("档案信息：", item)
                if (IsNotEmpty(item)){
                    this.nowArchiveName = item.name;
                    this.archiveTem.id = item.id;
                }
                let data = {
                    archiveTemId: this.archiveTem.id,
                    pageNum: this.filePoolPageNum,
                    pageSize: this.filePoolPageSize,
                };
                console.log("getFilePool参数:", data)
                let url = this.archiveTemEmpFilePath + '/getFilePool';
                CallAjaxPost(url, data, this.getFilePoolSuc);
            },
            getFilePoolSuc(data) {
                console.log("getFilePoolSuc:", data)
                this.filePoolData = data.obj.list;
                this.filePoolTotalNum = data.obj.total;
                // 再次设置当前页码,若查询记录为空，设为第一页
                this.filePoolPageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;

            },
            unfoldFile() {
            },
            /**
             * 下载文件池中的档案文档模板
             */
            downFilePoolTem(fileId) {
                console.log("fileInfo中id：", fileId)
                downloadFileEncrypt(fileId);//下载文件
            },
            /**
             * 打开删除文件池中档案文档模态框
             */
            openRemoveFilePoolTemModal(fileId) {
                this.removeFilePoolTemModal = true;
                console.log("fileInfo中id：", fileId)
            },
            /**
             * 删除文件池中档案文档
             */
            removeFilePoolTem() {

            },
            /**
             * TODO--------------------------------------------------------------------！！系统文档监管！！--------------------------------
             */
            /**
             * 改变页码
             */
            onArchivePageChange(pageNum) {
                this.archivePageNum = pageNum;
                this.getChargingArchiveTableData();
            },
            /**
             * 切换每页条数
             */
            onTemPageSizeChange(pageSize) {
                this.archivePageSize = pageSize;
                this.getChargingArchiveTableData();
            },
            /**
             * 设置表头
             */
            setChargingArchiveTableTitle() {
                //添加“备注”所在列信息
                this.archiveColumn.push(HeadTooltip('remark', '备注'));
                // 添加自定义slot-scope(操作栏)
                this.archiveColumn.push(HeadActionSlot(true, 250));
                // 添加序号
                this.archiveColumn.unshift(HeadIndex(true));
            },
            /**
             * 处理点击tab事件
             * @param tab
             * @param event
             */
            handleClick(tab, event) {
                // console.log(tab, event);
                if (tab.index == 1) {
                    this.getChargingArchiveTableData()
                } else {

                }
            },
            /**
             * 获取档案模板
             */
            getChargingArchiveTableData(){
            	let data = {
            			userArchiveNumber: this.archiveConditions.userArchiveNumber,
            			userArchiveName: this.archiveConditions.userArchiveName,
            			leaderId: this.archiveConditions.leaderId,
            			userId: this.archiveConditions.userId,
            			pageNum: this.archivePageNum,
            			pageSize: this.archivePageSize,
            	};
            	let url = this.archiveExamplePath + '/listChargingArchiveByCondition';
            	console.log("listChargingArchiveByCondition参数：",data)
            	CallAjaxPost(url,data,this.getChargingArchiveTableDataSuc);
            	//显示加载
            	this.archiveLoading = true;
            },
            getChargingArchiveTableDataSuc(data){
            	console.log("getChargingArchiveTableDataSuc:",data)
            	//取消显示加载
            	this.archiveLoading = false;
            	this.archiveTableData = data.obj.list;
            	this.archiveTotalNum = data.obj.total;
            	this.archivePageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
            	//清除历史记录
            	this.clearList();
            },
            /**
             * 清除监察档案搜索条件
             */
            clearArchiveConditions(){
            	this.archiveConditions.userArchiveNumber = null;
    			this.archiveConditions.userArchiveName = null;
    			this.archiveConditions.leaderId = null;
    			this.archiveConditions.userId = null;
    			this.getChargingArchiveTableData();
            },
            /**
             * 禁用
             */
            changeArchiveEnable(id){
            	console.log("changeArchiveEnable:",id)
            	this.archiveExample.id = id;
            	let data = this.archiveExample.id;
            	let url = this.archiveExamplePath + '/disableArchiveExample';
            	console.log("disableArchiveExample参数：",data);
            	CallAjaxPost(url, data, this.disableArchiveExampleSuc);
            	//打开加载提示
            	this.loadingMsg = MessageLoading();
            },
            disableArchiveExampleSuc(data){
            	//关闭加载提示
            	CloseMessageLoading(this.loadingMsg);
            	MessageSuccess('禁用成功');
            	//重新查询数据
            	this.getChargingArchiveTableData();
            },
            /**
             * 改变页码
             */
            onArchiveFilePageChange(pageNum) {
                this.archiveFilePageNum = pageNum;
                this.openArchiveFileModal(this.archiveFileId);
            },
            /**
             * 切换每页条数
             */
            onArchiveFilePageSizeChange(pageSize) {
                this.archiveFilePageSize = pageSize;
                this.openArchiveFileModal(this.archiveFileId);
            },
            /**
             * 设置表头
             */
            setArchiveFileTableTitle() {
                //添加“备注”所在列信息
                this.archiveFileColumn.push(HeadTooltip('remark', '备注'));
                // 添加自定义slot-scope(操作栏)
                this.archiveFileColumn.push(HeadActionSlot(true, 100));
                // 添加序号
                this.archiveFileColumn.unshift(HeadIndex(true));
            },
            /**
             * 查看文档绑定的文件
             */
            openArchiveFileModal(id){
//            	this.archiveFileId = id;
//            	console.log("openArchiveFileModal参数",id)
            	this.archiveFileModal=true;
            	this.archiveFile(id);
            },
            archiveFile(id){
            	console.log("archiveFile参数",id)
            	let data = {
            			id: id,
            			pageNum: this.archiveFilePageNum,
            			pageSize: this.archiveFilePageSize,
            	};
            	let url = this.archiveExamplePath + '/listArchiveFileByCondition';
            	console.log("listArchiveFileByCondition参数：",data)
            	CallAjaxPost(url,data,this.listArchiveFileByConditionSuc);
            	//显示加载
            	this.archiveFileLoading = true;
            },
            listArchiveFileByConditionSuc(data){
            	console.log("listArchiveFileByCondition返回值：",data.obj)
            	//取消显示加载
            	this.archiveFileLoading = false;
            	this.archiveFileTableData = data.obj.list;
            	this.archiveFileTotalNum = data.obj.total;
            	this.archiveFilePageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
            },
            cancelArchiveFile(){
            	// 关闭模态框
            	this.archiveFileModal=false;
            },
            downloadArchiveFile(id) {
                console.log('fileId' , id)
                downloadFileEncrypt(id);
            },

        }
    })
;