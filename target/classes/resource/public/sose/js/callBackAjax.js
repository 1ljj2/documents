/**
 * 获取设置在本地存储中token
 */
function GetToken() {
    let token = window.cookieUtil.get('Authorization');
    console.log(token);
    return token;
}

//指定预处理参数选项的函数
$.ajaxPrefilter(function (options, originalOptions, jqXHR) {
    // options对象 包括accepts、crossDomain、contentType、url、async、type、headers、error、dataType等许多参数选项
    // originalOptions对象 就是你为$.ajax()方法传递的参数对象，也就是 { url: "/index.php" }
    // jqXHR对象 就是经过jQuery封装的XMLHttpRequest对象(保留了其本身的属性和方法)

    // console.log(token);
//	options.type = "GET"; // 将请求方式改为GET
//     options.headers = {Authorization: token}; // 清空自定义的请求头

});

//设置AJAX的全局默认选项
$.ajaxSetup({
    headers: {
        'Authorization': GetToken(),
        'Referer-Uri': window.location.pathname,
        'X-Requested-With': 'XMLHttpRequest',
    }
});

/**
 * Ajax get 无参请求
 * @param url 请求路径，自动添加服务器地址
 * @param funcSuc 请求成功回调函数
 * @param funcErr 请求失败回调函数，没有则调用默认失败处理函数
 * @param funcComp  请求完成后调用的回调函数，没有则传null或不填
 * @param isAsync 同步:false;异步:true;null或不填默认为异步
 */
function CallAjaxGetNoParam(url, funcSuc, funcErr, funcComp, isAsync) {
    console.log('请求路径：' + METHOD_URL + url);
    if (isAsync === null) {
        isAsync = true;
    }
    $.ajax({
        url: METHOD_URL + url,
        async: isAsync,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        method: "get",
        dataType: "json",
        success: function (data, textStatus) {
            funcSuc(data)
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (IsNotEmpty(funcErr)) {
                funcErr(XMLHttpRequest.responseJSON);
            } else {
                AjaxDefaultErr(XMLHttpRequest.responseJSON);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            AjaxDefaultComp(XMLHttpRequest);
            if (IsNotEmpty(funcComp)) {
                funcComp();
            }
        }
    })
}

/**
 * Ajax post 请求
 * @param url 请求路径，自动添加服务器地址
 * @param params 请求参数，json格式
 * @param funcSuc 请求成功回调函数
 * @param funcErr 请求失败回调函数，没有则调用默认失败处理函数
 * @param funcComp  请求完成后调用的回调函数，没有则传null或不填
 * @param isAsync 同步:false;异步:true;null或不填，默认为异步
 */
function CallAjaxPost(url, params, funcSuc, funcErr, funcComp, isAsync) {
    console.log('请求路径：' + METHOD_URL + url);
    if (isAsync === null) {
        isAsync = true;
    }
    $.ajax({
        url: METHOD_URL + url,
        type: "post",
        async: isAsync,
        xhrFields: {
            withCredentials: true
        },// 携带cookie
        crossDomain: true,// 跨域
        data: JSON.stringify(params),
        contentType: "application/json; charset=utf-8",// 参数格式
        dataType: "json", // 返回JSON数据
        success: function (data, textStatus) {
            funcSuc(data)
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (IsNotEmpty(funcErr)) {
                funcErr(XMLHttpRequest.responseJSON);
            } else {
                AjaxDefaultErr(XMLHttpRequest.responseJSON);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            AjaxDefaultComp(XMLHttpRequest);
            if (IsNotEmpty(funcComp)) {
                funcComp();
            }
        }
    })
}


/**
 * Ajax post 表单提交方式
 * @param url 请求路径，自动添加服务器地址
 * @param params 请求参数，json格式
 * @param funcSuc 请求成功回调函数
 * @param funcErr 请求失败回调函数，没有则调用默认失败处理函数
 * @param funcComp  请求完成后调用的回调函数，没有则传null或不填
 * @param isAsync 同步:false;异步:true;null或不填默认为异步
 */
function CallAjaxPostForm(url, params, funcSuc, funcErr, funcComp, isAsync) {
    console.log('请求路径：' + METHOD_URL + url);
    if (isAsync === null) {
        isAsync = true;
    }
    $.ajax({
        url: METHOD_URL + url,
        type: 'POST',
        async: isAsync,
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        data: params,
        dataType: 'json',
        success: function (data, textStatus) {
            funcSuc(data)
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (IsNotEmpty(funcErr)) {
                funcErr(XMLHttpRequest.responseJSON);
            } else {
                AjaxDefaultErr(XMLHttpRequest.responseJSON);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            AjaxDefaultComp(XMLHttpRequest);
            if (IsNotEmpty(funcComp)) {
                funcComp();
            }
        }
    });
}
/**
 * 上传文件表单
 * @param url 上传路径
 * @param formData 文件表单
 * @param funcSuc 成功回调函数
 */
function CallAjaxFile(url, formData, funcSuc) {
    $.ajax({
        type: "post",
        url: METHOD_URL + url + METHOD_SUFFIX,
        data: formData,
        processData: false,// 告诉jQuery不要去处理发送的数据
        contentType: false,// 告诉jQuery不要去设置Content-Type请求头
        dataType: 'json',
        success: function (data) {
            funcSuc(data)
        },
        error: function (error) {
            console.log('Ajax请求错误！');
            console.log(data);
            errorClass(data);
        }
    });
}
/**
 * Ajax post上传文件表单
 * @param url 请求路径，自动添加服务器地址
 * @param formData 文件表单
 * @param funcSuc 请求成功回调函数
 * @param funcErr 请求失败回调函数，没有则调用默认失败处理函数
 * @param funcComp  请求完成后调用的回调函数，没有则传null或不填
 * @param isAsync 同步:false;异步:true;null或不填默认为异步
 */
function CallAjaxPostFile(url, formData, funcSuc, funcErr, funcComp, isAsync) {
    console.log('请求路径：' + METHOD_URL + url);
    if (isAsync === null) {
        isAsync = true;
    }
    $.ajax({
        type: "post",
        url: METHOD_URL + url,
        async: isAsync,
        data: formData,
        processData: false,// 告诉jQuery不要去处理发送的数据
        contentType: false,// 告诉jQuery不要去设置Content-Type请求头
        dataType: 'json',
        success: function (data, textStatus) {
            funcSuc(data)
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (IsNotEmpty(funcErr)) {
                funcErr(XMLHttpRequest.responseJSON);
            } else {
                AjaxDefaultErr(XMLHttpRequest.responseJSON);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            AjaxDefaultComp(XMLHttpRequest);
            if (IsNotEmpty(funcComp)) {
                funcComp();
            }
        }
    });
}

/**
 * Ajax post上传文件表单
 * @param url 请求路径，自动添加服务器地址
 * @param formData 文件表单
 * @param funcSuc 请求成功回调函数
 * @param funcErr 请求失败回调函数，没有则调用默认失败处理函数
 * @param funcComp  请求完成后调用的回调函数，没有则传null或不填
 * @param isAsync 同步:false;异步:true;null或不填默认为异步
 */
function CallAjaxPostFileWithHeader(url, formData, funcSuc, funcErr, funcComp, isAsync) {
    console.log('请求路径：' + METHOD_URL + url);
    if (isAsync === null) {
        isAsync = true;
    }
    $.ajax({
        type: "post",
        url: METHOD_URL + url,
        async: isAsync,
        data: formData,
        headers:{"token":localStorage.documentToken},//携带Token的请求头
        processData: false,// 告诉jQuery不要去处理发送的数据
        contentType: false,// 告诉jQuery不要去设置Content-Type请求头
        dataType: 'json',
        success: function (data, textStatus) {
            funcSuc(data)
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (IsNotEmpty(funcErr)) {
                funcErr(XMLHttpRequest.responseJSON);
            } else {
                AjaxDefaultErr(XMLHttpRequest.responseJSON);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            AjaxDefaultComp(XMLHttpRequest);
            if (IsNotEmpty(funcComp)) {
                funcComp();
            }
        }
    });
}

/**
 * Ajax post 支付宝请求
 * @param url 请求路径，自动添加服务器地址
 * @param params 请求参数，json格式
 * @param funcSuc 请求成功回调函数
 * @param funcErr 请求失败回调函数，没有则调用默认失败处理函数
 * @param funcComp  请求完成后调用的回调函数，没有则传null或不填
 * @param isAsync 同步:false;异步:true;null或不填默认为异步
 */
function CallAjaxPostPay(url, params, funcSuc, funcErr, funcComp, isAsync) {
    console.log('请求路径：' + PAY_URL + url + '.sose');
    if (isAsync === null) {
        isAsync = true;
    }
    $.ajax({
        url: PAY_URL + url + '.sose',
        type: "post",
        async: isAsync,
        xhrFields: {
            withCredentials: true
        },// 跨域
        crossDomain: true,// 跨域
        data: JSON.stringify(params),
        contentType: "application/json; charset=utf-8",// 参数格式
        dataType: "json", // 返回JSON数据
        success: function (data, textStatus) {
            funcSuc(data)
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (IsNotEmpty(funcErr)) {
                funcErr(XMLHttpRequest.responseJSON);
            } else {
                AjaxDefaultErr(XMLHttpRequest.responseJSON);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            AjaxDefaultComp(XMLHttpRequest);
            if (IsNotEmpty(funcComp)) {
                funcComp();
            }
        }
    })
}

/**
 * 默认Ajax异常处理函数，处理401/402/403/500错误
 * @param resp 返回的错误信息
 */
function AjaxDefaultErr(resp) {
    console.log(resp);
    switch (resp.code) {
        case 401:
            NoticeWarning('尚未登录', '请登录,即将跳转至登录页面');
            setTimeout(function () {
                window.location.href = '/login?redirectURL=' + encodeURIComponent(window.location.href);
            }, 2000); //延时执行
            break;
        case 402:
            NoticeWarning('登录过期', '请重新登录,即将跳转至登录页面');
            setTimeout(function () {
                window.location.href = '/login?redirectURL=' + encodeURIComponent(window.location.href);
            }, 2000);
            break;
        case 403:
            NoticeWarning('尚未开通权限');
            // window.location.href = '/403';
            alert("跳转至 403 页面");
            break;
        case 500:
            NoticeError('页面发生错误');
            break;
        default:
            break;
    }
}

/**
 * 默认Ajax请求完成回调函数
 * @param XMLHttpRequest
 */
function AjaxDefaultComp(XMLHttpRequest) {
    // 获取新的token
    let token = XMLHttpRequest.getResponseHeader("Authorization");
    if (IsNotEmpty(token)) {
        console.log('更新token', token);
        window.cookieUtil.del("Authorization");
        // 设置cookie，将作用域设为'/'
        window.cookieUtil.set("Authorization", token, '3d', '/');
    }
}