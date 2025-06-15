/**
 *坑：
 * 之前版本（具体版本分界线我也不知道，我下载 的是2.0.943版本）写法：
 *      PDFJS.workerSrc = '../build/pdf.worker.js';//加载核心库
 *      PDFJS.getDocument(url).then(function getPdfHelloWorld(pdf) {
 *
 *      })
 * 2.0.943版本写法：
 *      pdfjsLib.GlobalWorkerOptions.workerSrc ='../build/pdf.worker.js';
 *      pdfjsLib.getDocument(url).then(function(pdf){
 *
 *      });
 */
// pdfjsLib.GlobalWorkerOptions.workerSrc = '/templates/pdfh5/js/pdf.worker.js';
// var PDFJS.workerSrc = '/templates/pdfh5/js/pdf.worker.js';//加载核心库
pdfjsLib.GlobalWorkerOptions.workerSrc = '/templates/pdfh5/js/build/pdf.worker.js';//自己的路径,加载核心库

var vPdf = new Vue({
    el: '#pdf',
    data: function () {
        return {
            filePath: '/file/file',//文件请求地址
            signUrl: "",//公章签名图片url地址
            pdfBase64: '',//pdf文件的Base64编码
            pdfUrl: "",//pdf文件的地址
            numPages: '',//pdf文件的页数,从1开始计数的页数
            signatureUrl: "",//签名图片地址
            sealUrl: "",//公章地址
            signatureNowData: [],//公章签名对象信息集合
            signatureColumn: [
                {title: '公章/签名', key: 'signatureName',width: 150},
                {title: '备注', key: 'remark',width: 150},
            ],//公章签名列表表头
            signatureModal: false,//公章签名模态框
            loading: true, selection: [],
            picModel: false,//展示图片模态框
            doc: '',//pdf文件输出对象
            pdfModal: false,//查看pdf文件模态框
            signatureSize: 100,//签章大小
        }
    },
    mounted() {
        //获取路径传过来的参数
        this.pdfUrl = GetUrlParam("pdfUrl");//需要签署的文件的地址


        this.selectSign();//获取签名和公章

        this.getPdfBase64();//获得文件的Base64编码回调函数，并在页面上显示

        this.signatureColumn.push(HeadActionSlot());// 添加slot-scope操作栏

    },
    methods: {

        /**
         * 获得文件的Base64编码
         */
        getPdfBase64() {
            let url = this.filePath + "/downloadFile";//请求获取文件base64编码的地址
            let data = {//请求文件base64编码的参数
                fileUrl: this.pdfUrl,
            }
            CallAjaxPost(url, data, this.getPdfBase64Suc);//发送获取文件base64编码的请求
        },
        /**
         * 获得文件的Base64编码回调函数，并在页面上显示
         */
        getPdfBase64Suc(data) {
            var that = this;
            // console.log("getPdfBase64Suc", data);
            this.base64 = data.obj;//获取后端返回的文件base64编码
            this.base64 = this.base64.replace(/[\n\r]/g, '');//去除编码中多余的换行和空格
            // console.log("this.base64", this.base64);

            var loadingTask = pdfjsLib.getDocument({data: atob(this.base64),});
            loadingTask.promise.then(function (pdf) {
                that.numPages = pdf.numPages;//pdf的页数
                console.log("that.numPages", that.numPages);//pdf的页数
                for (let i = 1; i <= that.numPages; i++) {//循环设置所有pdf业
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
        },




        /**
         * 下载pdf文件
         */
        exportPdfFile() {
            // 默认a4大小，竖直方向，mm单位的PDF
            // var doc = new jsPDF();
            // 方向，单位，尺寸格式
            // const doc = new jspdf('l', 'pt', [205, 115]);
            this.doc = new jsPDF('l', 'pt', 'a5');

            console.log("this.numPages", this.numPages);
            for (let i = 1; i <= this.numPages; i++) {
                // for (let i = 1; i <= 2; i++) {
                let canvas = document.getElementById("page" + i);
                let ctx = canvas.getContext("2d");
                // 将图片转化为dataUrl
                let imgData = canvas.toDataURL('image / jpeg');
                // console.log("imgData" + i, imgData);
                // 添加图片
                // 第3，4参数：距离左上角x和y轴偏移的位置
                // 第5，6参数：生成图片的宽高
                // doc.insertPage(i).addImage(imgData, 'PNG', 0, 0, canvas.width, canvas.height);
                // doc.insertPage(i).addImage(imgData, 'PNG', 0, 0, 1350, 750);
                this.doc.addImage(imgData, 'PNG', 0, 0);

                // console.log(canvas.width, canvas.height);
                // doc.addImage(imgData);
                // doc.addPage();
                if (i != this.numPages) {
                    this.doc.addPage();
                }
            }
            var datauri = this.doc.output('dataurlstring');
            var base64  = datauri.split("base64,")[1];
            var data={'data':base64};
            console.log("pdf Base64 data",data);


            // 保存
            this.doc.save('a4.pdf');
        },

        /**
         * 在pdf上生成签名
         */
        signature() {
            this.closePicModel();
            this.closeSignatureModal();
            //cnavs添加签名
            var that = this;
            let canvas = document.getElementById("page" + this.numPages);
            let ctx = canvas.getContext("2d");
            let img = new Image();
            // img.src = "/upload_document/signature/5.png";//图片地址
            img.src = this.signUrl;//公章签名图片地址
            img.onload = function () {
                ctx.drawImage(img, 50, 50, that.signatureSize, that.signatureSize);   //在画布X轴 50  Y轴坐标50 处添加签名
            };

            //在事件外声明需要用到的变量
            let ax, ay, x, y;
            //添加鼠标按下事件
            canvas.onmousedown = function (e) {
                console.log("onmousedown e",e);
                //按下后可移动
                canvas.onmousemove = function (e) {
                    // console.log("onmousedown e",e);
                    // x = e.clientX;//鼠标相对于该页面的X轴位置
                    x = e.layerX;
                    // y = e.clientY;//鼠标相对于该页面的Y轴位置
                    y = e.layerY;
                    // console.log(x, y);
                    //先清除之前的然后重新绘制
                    // ctx.clearRect(0, 0, canvas.width, canvas.height);
                    // ctx.drawImage(img, x, y - canvas.height * (that.numPages - 1) - 70, 100, 100);//重画签章
                    if(navigator.userAgent.indexOf("Firefox")>0){//修复火狐Firefox浏览器layerY值不一样的问题
                        y=y - canvas.height * (that.numPages - 1);
                    }
                    ctx.drawImage(img, x-0.5*that.signatureSize, y-0.5*that.signatureSize, that.signatureSize, that.signatureSize);//重画签章
                };
                //鼠标抬起清除绑定事件
                canvas.onmouseup = function () {
                    //先清除之前的然后重新绘制
                    ctx.clearRect(0, 0, canvas.width, canvas.height);
                    var loadingTask = pdfjsLib.getDocument({data: atob(that.base64),});
                    loadingTask.promise.then(function (pdf) {
                        //重画被签名的pdf页面
                        pdf.getPage(that.numPages).then(function (page) {
                            // var canvasList = document.getElementById('canvas_list');
                            let canvas = document.createElement('canvas');
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
                    });
                    canvas.onmousemove = null;
                    canvas.onmouseup = null;
                    // debugger
                    //盖章延时900ms
                    setTimeout(function () {
                        //要延迟执行的代码块
                        // ctx.drawImage(img, 50, 50, 150, 100);//重画签章
                        // ctx.drawImage(img, x, y - canvas.height * (that.numPages - 1) - 70, 100, 100);//重画签章
                        ctx.drawImage(img, x-0.5*that.signatureSize, y-0.5*that.signatureSize, that.signatureSize, that.signatureSize);//重画签章
                    }, 900);
                };
            };
        },

        /**
         * 打开公章签名列表模态框
         */
        openSignatureModal(){
            // console.log("openSignatureModal");
            this.signatureModal = true;
        },

        /**
         * 关闭公章签名列表模态框
         */
        closeSignatureModal(){
            // console.log("closeSignatureModal");
            this.signatureModal = false;
        },

        /**
         * 查找签名
         */
        selectSign() {
            let url = "/signature/signature/listSignature";//获取签名公章列表的地址
            CallAjaxGetNoParam(url,this.selectSignSuc);//发送获取签名公章列表的的请求
        },

        /**
         * 查找签名回调函数
         * @param data
         */
        selectSignSuc(data) {
            console.log("selectSignSuc", data);
            this.signatureNowData = data.obj;//将返回的公章签名集合信息放到nowData
            console.log("this.signatureNowData",this.signatureNowData);

        },

        /**
         * 查看签名
         * @param index
         */
        showSignature(index) {
            console.log('showSignature', index);
            this.signUrl = this.signatureNowData[index].accessUrl;
            this.picModel = true;
            console.log("this.signUrl", this.signUrl);
        },
        /**
         * 关闭展示图片模态框
         */
        closePicModel() {
            this.picModel = false;

        },

        /**
         * 打开查看pdf文件模态框
         */
        openPdfModal() {
            this.pdfModal = true;

        },
        /**
         * 关闭查看pdf文件模态框
         */
        closePdfModal() {
            this.pdfModal = false;
        },

    },


});



