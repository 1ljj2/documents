var vExcel = new Vue({
    el: '#fileCheck',
    data: function () {
        let vm = this;
        return {
            nowData: [],
            column: [
                {title: '文件名称', key: 'fileName'},
                {title: '检验结果', key: 'result'},
            ],
            loading: false, selection: [],
            loadingMsg: '',// 加载提示
            fileInfo: {
                fileName: '', fileCode: '',
                id: '', userName: '', accessUrl: '', type: '',
            },// 公章/签名实体类

            firstPath: '/signature/signature',
            uploadUrl: METHOD_URL, // 请求地址
            FormData: new FormData(), // 图片表单对象

            IdList: [],// 图片id列表
            maxPixSize: 30,// 最大大小，单位mb(单位可自行调整)
            signUrl: '',//图片路径
            dialogImageUrl: '',// 图片预览地址
            dialogImageAlt: '',// 图片预览提示文字
            dialogVisible: false,// 是否显示预览

            // ============ 文本上传
            // 文件相关属性
            textFormData: new FormData(), // 文本表单对象
            textFileType: 'T',// 文件类型
            textFileList: [],// 文件列表
            textIdList: [],// 图片id列表
            maxTextSize: 20,// 最大大小，单位mb(单位可自行调整)
            fileStr: '',// 加密后的文件base64编码
            aesKey: '',// 对称加密秘钥
            base64: '',// 文件的base64编码


            roleList: [],// 角色集合


            submitFileModal: false,//上传文件模态框
            submitFileData: [],//上传文件的列表信息
            fileList: [],// 文件列表

        }
    },
    components: {

        'layout-header': httpVueLoader(PROJECT_NAME + '/templates/layout/layout-header.vue'),
        'layout-sider': httpVueLoader(PROJECT_NAME + '/templates/layout/layout-sider.vue'),
        'layout-footer': httpVueLoader(PROJECT_NAME + '/templates/layout/layout-footer.vue'),
    },
    beforeCreate: function () {
    },
    mounted() {
        this.initPage();//初始化表格表头
        // this.filter();//表格过滤查询
    },
    methods: {
        /**
         * 初始化表格表头
         */
        initPage() {
            // 添加自定义slot-scope
            // this.column.push(HeadActionSlot());
            // 添加多选
            // this.column.unshift(HeadSelection());
        },

        /**
         * 在多选模式下有效，只要选中项发生变化时就会触发
         * @param selection 已选项数据
         */
        onSelectionChange(selection) {
            this.selection = selection;
            console.log(this.selection);
        },

        /**
         * 打开上传文件模态框
         */
        openSubmitFileModal() {
            this.submitFileModal = true;//打开添加签名模态框
        },

        /**
         * 关闭添加签名模态框
         */
        closeSubmitFileModal() {
            this.submitFileModal = false;//关闭模态框
            this.clearFileInfo();//清空文件信息及文件名

        },

        /**
         * 清除上传文件信息
         */
        clearFileInfo() {
            this.fileList = []; // 清空文件列表
            this.submitFileData = [];//清空上传文件信息
        },

        /**
         * 上传提交文件
         */
        submitFileList() {
            this.loadingMsg = MessageLoading();//打开上传文件加载提示
            let that = this;


            // for (let i = 0; i < this.fileList.length; i++) {
            //     this.fileList[i].roleId = this.submitFileData[i].roleId;
            //     this.fileList[i].remark = this.submitFileData[i].remark;
            //     this.fileList[i].name = this.submitFileData[i].signatureName;
            //     if (!Boolean(this.fileList[i].name)) {
            //         console.log("this.fileList[i].name",this.fileList[i].name)
            //         MessageWarning('公章签名名称不能为空！');
            //         return;
            //     }
            // }
            SubmitFileList(this.fileList, this.submitFileListSuc,"F")


        },

        submitFileListSuc(data){

            console.log("submitFileList",data)
            MessageSuccess('上传成功');
            if(data.obj.result=="success"){
                let json = {fileName:data.obj.fileName,resultStr:"文件为系统原始文件",result:"success"}
                this.nowData.push(json);
                this.$notify({
                    title: '成功',
                    message: '文件为系统原始文件',
                    type: 'success'
                });
            }else{
                let json = {fileName:data.obj.fileName,resultStr:"文件非系统原始文件",result:"fail"}
                this.nowData.push(json);
                this.$notify({
                    title: '警告',
                    message: '文件非系统原始文件',
                    type: 'warning'
                });
            }
            CloseMessageLoading(this.loadingMsg)//关闭加载提示
            this.closeSubmitFileModal()//关闭新增公章/签名模态框
            // this.tableRowClassName({row, rowIndex});
        },


        /**
         * 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
         * @param file 当前文件
         * @param fileList 文件列表
         */
        handleChange(file, fileList) {
            // console.log("上传图片数据：", file);
            // console.log("fileList：", fileList);
            // console.log("this.submitFileData：", this.submitFileData);
            this.fileList = fileList;
            // this.submitFileData = [];//清空上传文件信息
            // for (let i = 0; i < this.fileList.length; i++) {
            //     this.submitFileData.push({
            //         fileName: this.fileList[i].name,
            //         roleId: [],
            //         remark: '',
            //     });
            // }

        },

        /**
         * 文件列表移除文件时的钩子
         * @param file 移除的文件
         * @param fileList 文件列表
         */
        handleRemove(file, fileList) {
            this.fileList = fileList;
            // this.submitFileData = [];//清空上传文件信息
            // for (let i = 0; i < this.fileList.length; i++) {
            //     this.submitFileData.push({
            //         fileName: this.fileList[i].name,
            //         roleId: [],
            //         remark: '',
            //     });
            //
            // }
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
         * 签名信息编辑
         * @param index
         * @param row
         */
        handleEdit(index, row) {
            console.log(index, row);
        },

        /**
         * 签名删除
         * @param index
         * @param row
         */
        handleDelete(index, row) {
            console.log(index, row);
            // this.fileList.splice(index, 1)
            // this.submitFileData = [];//清空上传文件信息
            // for (let i = 0; i < this.fileList.length; i++) {
            //     this.submitFileData.push({
            //         fileName: this.fileList[i].name, signUrl: this.fileList[i].url,
            //         roleId: [],
            //         remark: '',
            //         signatureName: this.fileList[i].name
            //     });
            //
            // }
        },

        tableRowClassName({row, rowIndex}) {
            console.log("row,rowIndex",row,rowIndex)
            // if (rowIndex === 1) {
            //     return 'warning-row';
            // } else if (rowIndex === 3) {
            //     return 'success-row';
            // }
            if(row.result=="success"){
                return 'success-row';
            }else{
                return 'warning-row';
            }
            return '';
        },



    }
})