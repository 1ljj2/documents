pdfjsLib.GlobalWorkerOptions.workerSrc = '/templates/pdfh5/js/build/pdf.worker.js';//自己的路径,加载核心库
var vfileAudited = new Vue({
        el: '#fileAudited',
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
                //------------------------------------------------------------------------------------！！页面基本参数！！-------------------------
                categoryList: [], //分类
                termList: [],//学期
                courseList: [],//课程
                userList: [],//用户
                stateList: [],//档案状态
                processList: [],//档案流程
                componentSize: 'medium',// 组件尺寸

                //-------------------------------------------------------------------------------------页面参数---------------------------
                sFileInfo:{
                    fileName:'',
                    termId:'',
                    courseId:'',
                    categoryId:'',
                    auditState:'',
                    creater:'',
                    userId:'',
                },//文档查询条件
                auditStateList: [
                    {auditStateString: '未审核', auditState: 'A'},
                    {auditStateString: '审核中', auditState: 'B'},
                    {auditStateString: '审核通过', auditState: 'C'},
                    {auditStateString: '审核不通过', auditState: 'D'}
                ],//审核状态
                auditFileLoading: true, auditFileSelection: [],
                loadingMsg: '',// 加载提示
                auditFileTotalNum: 0, auditFilePageNum: 1, auditFilePageSize: 10,  // 分页参数
                auditFileColumn: [
                    {title: '文档名称', key: 'fileName'},
                    {title: '学期', key: 'termName'},
                    {title: '创建者',key: 'userName'},
                    {title: '分类', key: 'categoryName'},
                    {title: '备注', key: 'remark'},
                    {title: '状态', key: 'auditStateString'},
                    {title: '创建时间', key: 'stateTimeString'},
                ],//表格表头
                auditFileData:[],//表格数据
                aesKey: '',//对称加密密钥
                numPages: '',//pdf文件的页数,从1开始计数的页数
                //-------------------------------------------------------------模态框-------------------------------
                pdfModal: false,//预览文档模态框
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
            // //页面初始化
            this.initPage();
            //获取分类列表
            this.geCategoryList();
        },
        methods: {
            /**
             * 页面初始化加载项 表格表头
             */
            initPage() {
                // 设置文档模板表头
                this.setAuditFileTableTitle();
                // //设置文档监管表头
                // this.setChargingFileTableTitle();
                //获取文档表格数据
                this.filter();
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
             * 获取文档分类列表
             */
            geCategoryList() {
                setTimeout(() => {
                    let url = this.categoryPath + '/listFileCategory';
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
                this.processList=[];
            },
            //---------------------------------------------------页面方法---------------------------------------
            /**
             * 改变页码
             */
            onAuditFilePageChange(pageNum) {
                this.auditFilePageNum = pageNum;
                this.filter();
            },
            /**
             * 切换每页条数
             */
            onAuditFileSizeChange(pageSize) {
                this.auditFilePageSize = pageSize;
                this.filter();
            },
            /**
             * 在多选模式下有效，只要选中项发生变化时就会触发
             * @param selection 已选项数据
             */
            onAuditFileSelectionChange(selection) {
                this.auditFileSelection = selection;
                // console.log(this.selection);
            },
            /**
             * 设置表头
             */
            setAuditFileTableTitle() {
                //添加“备注”所在列信息
                // this.auditFileColumn.push(HeadTooltip('remark', '备注'));
                // 添加自定义slot-scope(操作栏)
                this.auditFileColumn.push(HeadActionSlot(true, 260));
                // 添加序号
                this.auditFileColumn.unshift(HeadIndex(true));
            },
            /**
             * 获取文档模板
             */
            filter() {
                let data = {
                    fileName: this.sFileInfo.fileName,
                    termId: this.sFileInfo.termId,
                    categoryId: this.sFileInfo.categoryId,
                    courseId: this.sFileInfo.courseId,
                    createrId: this.sFileInfo.userId,
                    pageNum: this.auditFilePageNum,
                    pageSize: this.auditFilePageSize,
                };
                let url = this.fileInfoPath + '/listMyAuditFile';
                console.log("filter参数：", data)
                CallAjaxPost(url, data, this.filterSuc);
                // 显示加载
                this.auditFileLoading = true;
            },
            filterSuc(data) {
                console.log("filterSuc:", data)
                // 取消显示加载
                this.auditFileLoading = false;
                this.auditFileData = data.obj.list;
                // console.log("auditFileData", this.auditFileData);
                for(let i = 0;i<this.auditFileData.length;i++){
                    if (this.auditFileData[i].auditState == 'B') {
                        this.auditFileData[i].auditStateString = '审核中'
                    } else if (this.auditFileData[i].auditState == 'C') {
                        this.auditFileData[i].auditStateString = '审核通过'
                    } else if (this.auditFileData[i].auditState == 'D') {
                        this.auditFileData[i].auditStateString = '审核不通过'
                    } else {
                        this.auditFileData[i].auditStateString = '未审核'
                    }
                }
                this.auditFileTotalNum = data.obj.total;
                // 再次设置当前页码,若查询记录为空，设为第一页
                this.auditFilePageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
                //清除历史记录
                this.clearList();
            },
            /**
             * 清除页面查询条件
             */
            clearSFileInfo() {
                this.sFileInfo.fileName = '';
                this.sFileInfo.termId = '';
                this.sFileInfo.courseId = '';
                this.sFileInfo.categoryId = '';
                this.sFileInfo.auditState = '';
                this.sFileInfo.userId = '';
                this.filter();
            },
            /**
             * 下载文件
             * @param index
             */
            downloadMyAuditFile(index){
                downloadFileEncrypt(this.auditFileData[index].fileId)
            },
            /**
             * 预览文件
             * @param index
             */
            previewMyAuditFilePdf(index){
                this.loadingMsg = MessageLoading();//打开加载提示
                this.aesKey = GetAesKey();//生成16位对称加密秘钥
                console.log(index);

                let url = "/file/file" + "/getFileCodeById"
                GetFileCodeById(this.auditFileData[index].fileId, this.aesKey, this.previewMyAuditFilePdfSuc)
            },
            previewMyAuditFilePdfSuc(data) {
                console.log("previewMyAuditFilePdfSuc", data)
                var that = this;
                // console.log("showSignaturePictureSuc",data)
                let base64 = AesDecrypt(this.aesKey, data.obj.base64);//AES非对称加密解码
                console.log("base64", base64)
                var loadingTask = pdfjsLib.getDocument({data: atob(base64.split(",")[1]),});
                loadingTask.promise.then(function (pdf) {
                    that.numPages = pdf.numPages;//pdf的页数
                    console.log("that.numPages", that.numPages);//pdf的页数
                    for (let i = 1; i <= that.numPages; i++) {//循环设置所有pdf页
                        pdf.getPage(i).then(function (page) {
                            var canvasList = document.getElementById('canvas_list');
                            let canvas = document.createElement('canvas');
                            canvas.id = "page" + i;//设置canvas的id
                            canvasList.appendChild(canvas);
                            // canvasList.appendChild(document.createElement('hr'));//横线
                            canvasList.appendChild(document.createElement('br'));//横线

                            var ctx = canvas.getContext('2d');
                            // var viewport = page.getViewport({scale: 1.5,});//设置缩放比例
                            var viewport = page.getViewport({scale: 1,});//设置缩放比例
                            canvas.height = viewport.height;
                            canvas.width = viewport.width;

                            // Render PDF page into canvas context
                            var renderContext = {
                                canvasContext: ctx,
                                viewport: viewport
                            };
                            var renderTask = page.render(renderContext);
                        });
                    }
                });
                // CloseMessageLoading(this.loadingMsg);// 关闭加载提示
                //延时1000ms
                // setTimeout(function () {
                //要延迟执行的代码块
                CloseMessageLoading(this.loadingMsg);
                this.pdfModal = true;
                // }, 1000);

            },
            /**
             * 关闭预览pdf文件模态框
             */
            closePdfModal() {
                this.pdfModal = false;
            },





































































            //TODO--------------------------------------------------------------------！！文档模板管理！！--------------------------------



            /**
             * 清除文档模板信息
             */
            clearTemInfo() {
                this.fileTem.fileId = '';
                this.fileTem.fileName = '';
                this.fileTem.remark = '';
                this.fileTem.categoryId = '';
                this.fileTem.categoryName = '';
                this.fileTem.termId = '';
                this.fileTem.termName = '';
                this.fileTem.courseId = '';
                this.fileTem.courseName = '';
            },
            
            /**
             * 打开下载文档模板的模态框
             */
            openDownFileTemModal(index) {
                // console.log(index)
                // console.log("this.temTableData[index]",this.temTableData[index])
                downloadFileEncrypt(this.temTableData[index].fileId)
            },
            
            /**
             * 清除文档模板
             */
            clearFileTem() {
                this.fileTem.fileId = '';
                this.fileTem.categoryId = '';
                this.fileTem.categoryName = '';
                this.fileTem.fileName = '';
                this.fileTem.remark = '';
                this.fileTem.termId = '';
                this.fileTem.termName = '';
                this.fileTem.courseId = '';
                this.fileTem.courseName = '';
                this.fileList = [];
            },
            /**
             * 校验文档模板数据
             */
            checkFileTem() {
                if (CheckEmpty(this.fileTem.fileName, '文档模板名称不能为空') ||
                    CheckEmpty(this.fileTem.categoryId, '请选择该文档模板的分类') ||
                    CheckEmpty(this.fileTem.termId, '请选择该文档模板所属的学年学期') ||
                    CheckEmpty(this.fileTem.courseId, '请选择该文档模板所属的课程') ||
                    CheckLength(this.fileTem.fileName, '50', '请控制文档模板名称在50字以内')) {
                    return true;
                }
            },
            

            /**
             * TODO--------------------------------------------------------------------！！系统文档监管！！--------------------------------
             */
            /**
             * 改变页码
             */
            onFilePageChange(pageNum) {
                this.filePageNum = pageNum;
                this.getChargingFileTableData();
            },
            /**
             * 切换每页条数
             */
            onTemPageSizeChange(pageSize) {
                this.filePageSize = pageSize;
                this.getChargingFileTableData();
            },
            /**
             * 设置表头
             */
            setChargingFileTableTitle() {
                //添加“备注”所在列信息
                this.fileColumn.push(HeadTooltip('remark', '备注'));
                // 添加自定义slot-scope(操作栏)
                this.fileColumn.push(HeadActionSlot(true, 180));
                // 添加序号
                this.fileColumn.unshift(HeadIndex(true));
            },
            /**
             * 处理点击tab事件
             * @param tab
             * @param event
             */
            handleClick(tab, event) {
                // console.log(tab, event);
                if (tab.index == 1) {
                    this.getChargingFileTableData()
                } else {

                }
            },
            /**
             * 获取文档模板
             */
            getChargingFileTableData() {
                let data = {
                    fileTemName: this.fileConditions.fileName,
                    termId: this.fileConditions.termId,
                    categoryId: this.fileConditions.categoryId,
                    state: this.fileConditions.state,
                    userId: this.fileConditions.userId,
                    pageNum: this.filePageNum,
                    pageSize: this.filePageSize,
                };
                let url = this.fileInfoPath + '/listChargingFileByCondition';
                console.log("getChargingFileTableData参数：", data)
                CallAjaxPost(url, data, this.getChargingFileTableDataSuc);
                // 显示加载
                this.fileLoading = true;
            },
            getChargingFileTableDataSuc(data) {
                console.log("getChargingFileTableDataSuc:", data)
                // 取消显示加载
                this.fileLoading = false;
                this.fileTableData = data.obj.list;
                this.fileTotalNum = data.obj.total;
                this.filePageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
                //清除历史记录
                this.clearList();
            },
            /**
             * 清除监察文档搜索条件
             */
            clearFileConditions() {
                this.fileConditions.fileName = null;
                this.fileConditions.termId = null;
                this.fileConditions.categoryId = null;
                this.fileConditions.state = null;
                this.fileConditions.userId = null;
                this.getChargingFileTableData();
            },
            /**
             * 下载需要监察的文档
             */
            openDownChargingFileModal(index) {
                // console.log(index)
                // console.log("this.fileTableData",this.fileTableData)
                downloadFileEncrypt(this.fileTableData[index].fileId)
            },
            /**
             * 改变需要监察的文档启用禁用状态
             */
            changeFileEnable(index) {
                console.log(index)
                console.log(this.fileTableData)
                let url = this.fileInfoPath;
                if (this.fileTableData[index].isEnable) {
                    url = url + '/disableFile';
                } else {
                    url = url + '/enableFile';
                }
                CallAjaxPost(url, this.fileTableData[index].fileId, this.changeFileEnableSuc)
            },
            /**
             * 改变需要监察的文档启用禁用状态回调函数
             * @param data
             */
            changeFileEnableSuc(data) {
                // console.log("changeFileEnableSuc",data)
                this.getChargingFileTableData()
            },

            /**
             * 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
             * @param file 当前文件
             * @param fileList 文件列表
             */
            handleChange(file, fileList) {
                this.fileList = fileList;
                this.fileTem.fileName = this.fileList[0].raw.name;
                console.log("this.fileList", this.fileList)

            },

            /**
             * 文件列表移除文件时的钩子
             * @param file 移除的文件
             * @param fileList 文件列表
             */
            handleRemove(file, fileList) {
                this.fileList = fileList;
                this.fileTem.fileName = '';
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

        }
    })
;