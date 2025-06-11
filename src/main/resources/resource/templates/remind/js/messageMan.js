var vmessageMan = new Vue({
	el: '#messageMan',
	data: function(){
		return{
			 messPath:'/messageMan',
			 departmentPath:'/account/department',
			 userPath:'/account/user',
			 rolePath: '/account/role',
			 categoryPath: '/config/category',
			 componentSize: 'medium',// 组件尺寸
			 nowData: [], loading: true, selection: [],// 表格参数
			 column: [
				 	{title: '附件', key: 'fileName', minWidth: 200,
				 		render: (h, params) => {
	                    	let dName = params.row.fileName;
							if (dName!=null) {
	                    		return h('a', {
	                                style: {
	                                    color: '#3A5FCD',
	                                },on: {
	                                	click: () => {
	                                		this.downloadFile(params.row.fileId)
	                                		console.log('params.row.fileId',params.row.fileId)
	                                		}
	                                },order:'1'
	                            }, dName+'  (可下载)')
							};
	                    }},
				 	{title: '分类', key: 'categoryName', minWidth: 150},
				 	{title: '发布部门', key: 'departmentNameStr', minWidth: 200},
				 	{title: '发布人', key: 'userName', minWidth: 100},
	                {title: '发布时间', key: 'stateTimeString', minWidth: 200},
	                {title: '状态', key: 'state', minWidth: 100,
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
	                
             ],// 表头
             totalNum: 0, pageNum: 1, pageSize: 8,  // 消息分页参数
             sConditions:{ title:'',departmentId:'',userId:'',categoryId:'' },// 搜索信息参数
             loadingMsg: '',// 加载提示
             notice: '',// 提醒对象
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
             cloneMess: {
                 id: '', title:'',content:'',
                 categoryId:'',
                 roleId:[],
                 readUserId:[],
             },//克隆后的消息参数，用于检验是否编辑消息本身基本信息
             RoleIdList:[],
             ReadUserIdList:[],
             messRoleName:[],//消息面向的用户的姓名
             messReadUserName:[],//消息阅读用户的姓名
             // -------------------------------------下拉框参数---------------------
             departmentList:[],// 发布部门对象集合
             userList:[],// 发布用户对象集合
             categoryList: [],// 分类对象集合
             roleList: [],// 面向角色对象集合
             readUserList:[],// 面向用户对象集合
             // --------------------------------------模态框-------------------------
             removeMessModal:false,  //删除单个消息模态框
             removeMessSelectModal:false,  //删除选中消息模态框
             editMessModal:false,  //编辑消息
             addMessModal:false,  //新增消息
             //--------------------------------------附件上传参数---------------------------------
             fileList: [],// 文件列表
		}
	},
	components: {
		'layout-header': httpVueLoader(PROJECT_NAME+'/templates/layout/layout-header.vue'),
		'layout-sider': httpVueLoader(PROJECT_NAME+'/templates/layout/layout-sider.vue'),
		'layout-footer': httpVueLoader(PROJECT_NAME+'/templates/layout/layout-footer.vue'),
	},
    beforeCreate: function () {
        CheckPermissions();
    },
	mounted(){
		this.initPage();
		this.filter();
        //获取分类列表
        this.geCategoryList();
	},
	methods:{
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
            //添加“内容”所在列信息
              this.column.unshift(HeadTooltip('content', '内容'));
        	//添加“标题”所在列信息
            this.column.unshift(HeadTooltip('title', '标题'));
            // 添加自定义slot-scope(操作栏)
            this.column.push(HeadActionSlot(true, 200));
            // 添加序号
            this.column.unshift(HeadIndex(true));
            // 添加多选
            this.column.unshift(HeadSelection(true));
            console.log(this.column);
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
       //-------------------搜 索----------------------------
        /**
         *  TODO  获取所有的用户对象集合
         */
        getUserList(userName) {
            if (IsNotEmpty(userName)) {
                setTimeout(() => {
                    let url = this.userPath + '/listUserByName';
                    let data = {
                        userName: userName
                    };
                    CallAjaxPost(url, data, this.getUserListSuc);
                }, 200);
            }
        },
        getUserListSuc(data) {
            this.userList = data.obj;
        },
        /**
         * 获取选择框中的用户的id值，并赋值给过滤参数
         */
        getUserId(userId) {
            this.sMess.userId.push(userId);
        },
        /**
         * 清除用户信息
         */
        clearUser() {
            this.userList = [];
            this.sMess.userId = [];
        },
        /**
         *  TODO  获取所有的面向用户对象集合
         */
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
         * 获取选择框中的面向用户的id值，并赋值给过滤参数
         */
        getReadUserId(readUserId) {
            this.sMess.readUserId.push(readUserId);
        },
        getReadUserIdList(readUserIdList) {
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
        },
        translateRUNameToIdSuc(data) {
            this.readUserIdFromName = data.obj;
        },
        /**
         * 当选择面向用户的搜索下拉框获得焦点，清空之前的根据name模糊查询的列表值
         */
        clearReadUserList() {
            this.readUserList = [];
        },
        /**
         * 清除面向用户信息
         */
        clearReadUser() {
            this.readUserList = [];
            this.sMess.readUserId = [];
        },
        /**
         *  TODO  获取所有的角色对象集合
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
        /**
         * 获取选择框中的角色的id值，并赋值给过滤参数
         */
        getRoleId(roleId) {
            this.sMess.roleId.push(roleId);
        },
        getRoleIdList(roleIdList) {
            // debugger
            let stringRole = [];
            let intRole = [];
            //按name还是id给选择的分类
            for (let i = 0; i < roleIdList.length; i++) {
                if (typeof this.sMess.roleId[i] == "string") {
                    stringRole.push(this.sMess.roleId[i]);
                } else {
                    intRole.push(this.sMess.roleId[i]);
                }
            }
            console.log("stringRole:", stringRole, "  intRole:", intRole)
            //将stringRole转化为idList
            let data = {
                roleNameList: stringRole,
            };
            let url = this.rolePath + '/translateNameToId';
            CallAjaxPost(url, data, this.translateRNameToIdSuc, null, null, false);
            console.log("translateRNameToIdSuc:", this.roleIdFromName)
            // debugger
            //将name转化后的id赋值给intRole
            if (IsNotEmpty(this.roleIdFromName)) {
                for (let j = 0; j < this.roleIdFromName.length; j++) {
                    intRole.push(this.roleIdFromName[j]);
                }
            }
            //得到最终的roleIdList
            this.sMess.roleId = intRole;
        },
        translateRNameToIdSuc(data) {
            this.roleIdFromName = data.obj;
        },
        /**
         * 当选择角色的搜索下拉框获得焦点，清空之前的根据name模糊查询的列表值
         */
        clearRoleList() {
            this.roleList = [];
        },
        /**
         * 清除角色信息
         */
        clearRole() {
            this.roleList = [];
            this.sMess.roleId = [];
        },
        /**
         *  TODO  获取所有的部门对象集合
         */
        getDepartmentList(departmentName) {
            if (IsNotEmpty(departmentName)) {
                setTimeout(() => {
                    let url = this.departmentPath + '/listDepartmentByName';
                    let data = {
                    		departmentName: departmentName
                    };
                    CallAjaxPost(url, data, this.getDepartmentListSuc);
                }, 200);
            }
        },
        getDepartmentListSuc(data) {
            this.departmentList = data.obj;
        },
        /**
         * 获取选择框中的部门的id值，并赋值给过滤参数
         */
        getDepartmentId(departmentId) {
            this.sMess.departmentId.push(departmentId);
        },
        
        /**
         * 清除部门信息
         */
        clearDepartment() {
            this.departmentList = [];
            this.sMess.departmentId = [];
        },
        /**
         * 分类的查询
         */
        geCategoryList() {
            setTimeout(() => {
                let url = this.categoryPath + '/listMessCategory';
                CallAjaxGetNoParam(url, this.geCategoryListSuc);
            }, 200);
        },
        geCategoryListSuc(data) {
            console.log("geCategoryListSuc", data)
            this.categoryList = data.obj;
        },
       //-------------------查询表格--------------------------
        /**
		 * TODO 表格查询
		 */
        filter() {
            let data = {
            	title:this.sConditions.title,
            	departmentId:this.sConditions.departmentId,
            	userId:this.sConditions.userId,
            	categoryId:this.sConditions.categoryId,
                pageNum: this.pageNum,
                pageSize: this.pageSize,
            };
            let url = this.messPath + '/listMess';
            console.log("/listMess参数",data)
            CallAjaxPost(url, data, this.filterSuc);
            // 显示加载
            this.loading = true;
        },
        filterSuc(data) {
        	console.log("/listMess返回值",data.obj)
            // 取消显示加载
            this.loading = false;
        	//部门一二级展示
        	for(let i =0;i<data.obj.list.length;i++){
        		data.obj.list[i].departmentNameStr=data.obj.list[i].department.departmentParName+'  '+data.obj.list[i].department.departmentName;
	        }
            this.nowData = data.obj.list;
            this.totalNum = data.obj.total;
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },
        /**
		 * TODO 清空搜索条件-标题、发布部门、发布人、分类
		 */
        clearSConditions() {
        	this.sConditions.title = '';
            this.sConditions.departmentId = '';
            this.sConditions.userId = '';
            this.sConditions.categoryId = '';
            // 重新查询表格数据
            this.filter();
        },
        //------------------------删除信息-----------------------------
        /**
		 * TODO 打开批量删除消息模态框
		 */
        openRemoveMessSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                MessageWarning('请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeMessSelectModal = true;
        },
        /**
		 * 批量删除消息
		 */
        removeMessSelect() {
            // 关闭模态框
            this.removeNoticeSelectModal = false;
            // 获取索引集合
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.messPath + '/removeMessSelect';
            CallAjaxPost(url, data, this.removeMessSelectSuc);
            // 显示加载
            this.loading = true;
        },
        removeMessSelectSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('成功删除 ' + this.selection.length + ' 条消息');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },
        /**
		 * TODO 打开单个删除消息模态框
		 * 
		 * @param index
		 *            索引
		 */
        openRemoveMessModal(index) {
            // 获取索引值
            this.sMess.id = this.nowData[index].id;
            // 打开模态框
            this.removeMessModal = true;
        },
        /**
		 * 单个删除消息
		 */
        removeMess() {
            // 关闭模态框
            this.removeMessModal = false;
            let data = this.sMess.id;
            let url = this.messPath + '/removeMess';
            CallAjaxPost(url, data, this.removeMessSuc);
            // 打开加载提示
            this.loadingMsg = MessageLoading();
        },
        removeMessSuc(data) {
            // 关闭加载提示
            CloseMessageLoading(this.loadingMsg);
            MessageSuccess('消息删除成功');
            // 重新查询数据
            this.filter();
        },
        //---------------------编辑-----------------------------
        openEditMessModal(index){
        	console.log("nowData:", this.nowData[index]);
        	this.sMess.id = this.cloneMess.id =this.nowData[index].id;
         	this.sMess.title = this.cloneMess.title =this.nowData[index].title;
         	this.sMess.content = this.cloneMess.content = this.nowData[index].content;
         	this.sMess.userId =this.nowData[index].userId;//根据登录用户传的userId
         	this.sMess.departmentId =this.nowData[index].departmentId;//根据登录用户传的departmentId
         	this.sMess.categoryId = this.cloneMess.categoryId = this.nowData[index].categoryId;
         	this.sMess.readUserId = this.cloneMess.readUserId=this.readUserIdList = this.messReadUserName = this.nowData[index].readUserId;
        	this.sMess.roleId = this.cloneMess.roleId=this.roleIdList = this.messRoleName = this.nowData[index].roleId;
         	console.log("cloneMess:", this.cloneMess);
         	//显示原有数据
         	this.showReadUserSelected();
         	this.showRoleSelected();
         	console.log("显示原有数据后的readUserId"+this.sMess.readUserId)
        	//打开模态框
        	this.editMessModal=true;
        },
        editMess(){
        	//编辑确认前的对得到的数据进行校验
            if (this.checkMess()) {
                return;
            }
            if (typeof (this.sMess.roleId[0]) == "string") {
                this.sMess.roleId = this.cloneMess.roleId;
            }
            if (typeof (this.sMess.readUserId[0]) == "string") {
                this.sMess.readUserId = this.cloneMess.readUserId;
            }
            let data = {
                	id:this.sMess.id,
            		title: this.sMess.title,
            		content: this.sMess.content,
            		categoryId: this.sMess.categoryId,
                    readUserId: this.sMess.readUserId,//[]
                    roleId: this.sMess.roleId,//[]
                };
                console.log("editMess参数：",data)
                let url = this.messPath + '/editMess';
                CallAjaxPost(url, data, this.editMessSuc);
        },
        editMessSuc(data) {
        	console.log("editMess返回参数：",data)
            // 关闭模态框
            this.editMessModal = false;
            MessageSuccess("编辑成功");
            // 重新查询数据
            this.filter();
            // 清空编辑
            this.clearMess();
        },
        cancelEditMess(){
        	// 关闭模态框
        	this.editMessModal=false;
        	// 清空编辑
            this.clearMess();
        },
        /**
         * 清空Mess信息
         */
         clearMess(){
        	this.sMess.title = '';
        	this.sMess.content = '';
        	this.sMess.categoryId = '';
        	this.sMess.readUserId = [];
        	this.sMess.roleId = [];
         },
         /**
          * 给面向角色下拉框赋名称值
          */
         showRoleSelected() {
             let data = {
                 roleIdList: this.sMess.roleId
             };
             let url = this.rolePath + '/showRoleSelected';
             CallAjaxPost(url, data, this.showRoleSelectedSuc, null, null, false);
         },
         showRoleSelectedSuc(data) {
             this.sMess.roleId = data.obj;
             console.log(this.sMess.roleId)
         },
         /**
          * 给面向用户下拉框赋名称值
          */
         showReadUserSelected() {
             let data = {
                 userIdList: this.sMess.readUserId
             };
             let url = this.userPath + '/showUserSelected';
             CallAjaxPost(url, data, this.showUserSelectedSuc, null, null, false);
         },
         showUserSelectedSuc(data) {
        	 console.log(data.obj)
             this.sMess.readUserId = data.obj;
        	 console.log(this.sMess.readUserId)
         },
        //--------------------新增-------------------------
        /**
         * TODO        校验是否为空以及长度限制
         */
        checkMess() {
            if (CheckEmpty(this.sMess.title, '请填写标题') ||
                CheckEmpty(this.sMess.content, '请填写内容') ||
                CheckEmpty(this.sMess.categoryId, '请填写分类') ||
                CheckLength(this.sMess.title, '50', '请控制标题在50字以内')||
                CheckLength(this.sMess.content, '500', '请控制内容在500字以内')) {
                return true;
            }
        },
        /**
         * 新增消息--发布用户直接绑定对应的user，部门是他的部门
         */
        openAddMessModal(){
        	//打开模态框
        	this.addMessModal=true;
        	//清空信息
        	this.clearAdd();
        },
        addMess(){
            this.fileList[0].name = this.sMess.fileName;
            SubmitFileList(this.fileList, this.addFileMessSuc, "M")
        },
        addFileMessSuc(data){
        	console.log("SubmitFileList返回参数：",data)
        	//新增前的对得到的数据进行校验
            if (this.checkMess()) {
                return;
            }
        	let addData = {
            		title: this.sMess.title,
            		content: this.sMess.content,
            		categoryId: this.sMess.categoryId,
                    readUserId: this.sMess.readUserId,//[]
                    roleId: this.sMess.roleId,//[]
                    fileId: data.obj,
                };
            console.log("addMess参数：",addData)
            let url = this.messPath + '/addMess';
            CallAjaxPost(url, addData, this.addMessSuc);
        },
        addMessSuc(data) {
        	console.log("addMess返回参数：",data)
            // 关闭模态框
            this.addMessModal = false;
            MessageSuccess("新增成功");
            // 重新查询数据
            this.filter();
            // 清空新增
            this.clearAdd();
        },
        cancelAddMess(){
        	// 关闭模态框
        	this.addMessModal=false;
        	// 清空新增
            this.clearAdd();
        },
        // 清空新增
        clearAdd(){
        	this.sMess.title = '';
        	this.sMess.content = '';
        	this.sMess.roleId = [];
        	this.sMess.readUserId = [];
        },
        //------------附件上传--------------------------
        /**
         * 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
         * @param file 当前文件
         * @param fileList 文件列表
         */
        handleChange(file, fileList) {
            this.fileList = fileList;
            this.sMess.fileName = this.fileList[0].raw.name;
            console.log("this.fileList", this.fileList)
        },
        /**
         * 文件列表移除文件时的钩子
         * @param file 移除的文件
         * @param fileList 文件列表
         */
        handleRemove(file, fileList) {
            this.fileList = fileList;
            this.sMess.fileName = '';
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
        //---------------附件下载----------------------
        downloadFile(fileId){
        	downloadFileEncrypt(fileId);
        },
	}
});