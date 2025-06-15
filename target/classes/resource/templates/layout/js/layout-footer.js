// 负责页面底部信息的展示
module.exports = {
    data: function () {
        return {
            bottomInfo: ''
        }
    },
    mounted() {
        this.showBottomInfo();

    },
    methods: {
        /**
         * 获取TXT文件中定义的项目名
         */
        // 通过 cookieUtil 从 cookie 中获取底部信息，如果 cookie 中没有则通过 AJAX 请求 /config/txtRead/getSystemInfo 接口获取。
        showBottomInfo() {
            if (IsNotEmpty(window.cookieUtil.get("bottomInfo"))) {
                this.bottomInfo = window.cookieUtil.get("bottomInfo");
            } else {
                let url = '/config/txtRead/getSystemInfo';
                CallAjaxGetNoParam(url, this.showBottomInfoSuc);
            }
        },
        showBottomInfoSuc(data) {
            var map = data.obj;
            for (var key in map) {
                window.cookieUtil.set(key, map[key]);
            }
            this.bottomInfo = window.cookieUtil.get("bottomInfo");
        },
    }
};