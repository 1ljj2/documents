/**
 * 获取项目全局路径,暂时用不到
 *
 * @param name
 * @return {*}
 */
function GetGlobalPath(name) {
    //获取当前网址，如： http://localhost:8080/SSM/account/register.html
    // let curWwwPath = window.document.location.href;
    let curWwwPath = window.location.href;
    //获取主机地址之后的目录，如： SSM/account/register.html
    let pathName = window.location.pathname;
    console.log("路径:" + pathName);
    // pathName于curWwwPath的位置
    let pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    let localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/SSM ，如项目名为空，则不截取
    let projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    // let projectName = '';
    switch (name) {
        case "root":
            // 主机地址+项目名
            return localhostPath + projectName;
            break;
        case "proj":
            // 项目名 项目名隐藏后
            return projectName;
            break;
        case "path":
            // 获取主机地址之后的目录
            return pathName;
            break;
        default:
            break;
    }
}

/**
 * 获取页面URL路径中的信息(解决中文乱码)
 * @param name 要获取的值的key
 * @returns {*}
 */
function GetUrlParam(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    let paramName = window.location.search.substr(1).match(reg);
    if (paramName !== null) {
        return decodeURIComponent(paramName[2]); //decodeURIComponent 处理中文乱码
    }
    return '';
}

/**
 * 获取当前时间
 */
function GetNowFormatDate() {
    let date = new Date();
    let seperator1 = "-";
    let seperator2 = ":";
    let month = date.getMonth() + 1;
    let strDate = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hour >= 0 && hour <= 9) {
        hour = "0" + hour
    }
    if (minute >= 0 && minute <= 9) {
        minute = "0" + minute
    }
    if (second >= 0 && second <= 9) {
        second = "0" + second
    }
    let currentdate = date.getFullYear() + seperator1 + month + seperator1
        + strDate + " " + hour + seperator2 + minute + seperator2 + second;
    return currentdate;
}

/**
 * 获取当前时间，返回时间字符串
 *
 * @returns {string} 时间字符串
 * @constructor
 */
function GetDateNow() {
    let vNow = new Date();
    let sNow = "";
    sNow += String(vNow.getFullYear());
    sNow += String(vNow.getMonth() + 1);
    sNow += String(vNow.getDate());
    sNow += String(vNow.getHours());
    sNow += String(vNow.getMinutes());
    sNow += String(vNow.getSeconds());
    sNow += String(vNow.getMilliseconds());
    return sNow;
}

/**
 * 比较两个日期相差天数
 */
function checkIsUpdate(startDateString,endDateString,keepTime){
	let separator = "-"; //日期分隔符  
	let startDates = startDateString.split(separator);  
	let endDates = endDateString.split(separator);  
	let startDate = new Date(startDates[0], startDates[1]-1, startDates[2]);  
	let endDate = new Date(endDates[0], endDates[1]-1, endDates[2]);  
    let minuDate=parseInt(Math.abs(endDate - startDate ) / 1000 / 60 / 60 /24) + 1;//把相差的毫秒数转换为天数  
	if(minuDate > keepTime){
		this.upDate = true;
	}
}

/**
 * 时间戳转字符串
 * @param timeNumber 时间戳 单位毫秒 13位
 * @return {string} 字符串 2019-11-10 17:19:26
 */
function TimeNumberToString(timeNumber) {
    let d = new Date(timeNumber);    //根据时间戳生成的时间对象
    let updateDate = (d.getFullYear()) + "-" +
        (d.getMonth() + 1) + "-" +
        (d.getDate()) + " " +
        (d.getHours()) + ":" +
        (d.getMinutes()) + ":" +
        (d.getSeconds());
    return updateDate;
}


/**
 * 克隆对象
 * @param obj 目标对象
 * @return {*} 克隆出来的对象
 */
function CloneObj(obj) {
    let o;
    if (typeof obj === 'object') {
        if (obj === null) {
            o = null
        } else {
            if (obj instanceof Array) {
                o = [];
                for (let i = 0, len = obj.length; i < len; i++) {
                    o.push(cloneObj(obj[i]))
                }
            } else {
                o = {};
                for (let j in obj) {
                    o[j] = cloneObj(obj[j])
                }
            }
        }
    } else {
        o = obj
    }
    return o
}

/**
 * 判断是否是对象类型
 * @param object
 * @returns {*|boolean}
 */
function IsObj(object) {
    return object && typeof(object) == 'object' && Object.prototype.toString.call(object).toLowerCase() == "[object object]";
}

/**
 * 判断是否是数组
 * @param object
 * @returns {*|boolean}
 */
function IsArray(object) {
    return object && typeof(object) == 'object' && object.constructor == Array;
}

/**
 * 获取对象长度
 * @param object
 * @returns {number}
 */
function GetLength(object) {
    let count = 0;
    for (let i in object) count++;
    return count;
}

/**
 * 判断json对象是否相等
 * @param objA
 * @param objB
 * @returns {boolean}
 * @constructor
 */
function Compare(objA, objB) {
    if (!isObj(objA) || !isObj(objB)) return false; //判断类型是否正确
    if (getLength(objA) != getLength(objB)) return false; //判断长度是否一致
    return compareObj(objA, objB, true); //默认为true
}

function CompareObj(objA, objB, flag) {
    for (let key in objA) {
        if (!flag) //跳出整个循环
            break;
        if (!objB.hasOwnProperty(key)) {
            flag = false;
            break;
        }
        if (!isArray(objA[key])) { //子级不是数组时,比较属性值
            if (objB[key] != objA[key]) {
                flag = false;
                break;
            }
        } else {
            if (!isArray(objB[key])) {
                flag = false;
                break;
            }
            let oA = objA[key],
                oB = objB[key];
            if (oA.length != oB.length) {
                flag = false;
                break;
            }
            for (let k in oA) {
                if (!flag) //这里跳出循环是为了不让递归继续
                    break;
                flag = CompareObj(oA[k], oB[k], flag);
            }
        }
    }
    return flag;
}

/**
 * 比较两个json数组，获取改变的对象，并放在一个数组中返回
 * @param oldList 原集合
 * @param newList 新集合
 * @return {Array} 改变的集合
 */
function CompareGetChangeList(oldList, newList) {
    let changeList = [];
    // 循环判断，获取改变的对象
    for (let i = 0; i < oldList.length; i++) {
        let isChange = compare(oldList[i], newList[i]);
        if (!isChange) {
            changeList.push(newList[i]);
        }
    }
    return changeList;
}


/**
 * 数字转成大写中文
 * @param n 阿拉伯数字
 * @returns {*} 中文大写/数据错误信息
 * @constructor
 */
function NumberToChinese(n) {
    if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
        return "数据错误，请重新输入！";
    let unit = "京亿万仟佰拾兆万仟佰拾亿仟佰拾万仟佰拾元角分", str = "";
    n += "00";
    let p = n.indexOf('.');
    if (p >= 0)
        n = n.substring(0, p) + n.substr(p + 1, 2);
    unit = unit.substr(unit.length - n.length);
    for (let i = 0; i < n.length; i++) str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
    return str.replace(/零(仟|佰|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(兆|万|亿|元)/g, "$1").replace(/(兆|亿)万/g, "$1").replace(/(京|兆)亿/g, "$1").replace(/(京)兆/g, "$1").replace(/(京|兆|亿|仟|佰|拾)(万?)(.)仟/g, "$1$2零$3仟").replace(/^元零?|零分/g, "").replace(/(元|角)$/g, "$1整");
}

/*
 * width 弹出页面宽度
 * height 弹出页面高度
 * url 弹出页面url
 */
function OpenWin(width, height, url) {

    let popUrl = url;

    let iWidth = width;                         //弹出窗口的宽度;
    let iHeight = height;                        //弹出窗口的高度;

    let iTop = (window.screen.height - 30 - iHeight) / 2;       //获得窗口的垂直位置;
    let iLeft = (window.screen.width - 10 - iWidth) / 2;        //获得窗口的水平位置

    window.open(popUrl, '', 'height=' + iHeight + ', width=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ' toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')

}

function OpenWin(url) {
    let popUrl = url;
    let iWidth = window.screen.width * 2 / 3;                  //弹出窗口的宽度;
    let iHeight = window.screen.height * 2 / 3;                //弹出窗口的高度;
    let iTop = (window.screen.height - 30 - iHeight) / 2;       //获得窗口的垂直位置;
    let iLeft = (window.screen.width - 10 - iWidth) / 2;        //获得窗口的水平位置
    window.open(popUrl, '', 'height=' + iHeight + ', width=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ' toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
}


/**
 * 详情页关闭窗口
 */
function CloseDetail() {
    if (confirm("您确定要关闭本页吗？")) {
        window.opener = null;
        window.open('', '_self');
        window.close();
    }
}

/**
 * 判断数据是否为int类型
 */
// Number.prototype.isInteger = function (global) {
//     let floor = Math.floor, isFinite = global.isFinite;
//     Object.defineProperty(Number, 'isInteger', {
//         value: function isInteger(value) {
//             return typeof value === 'number' && isFinite(value)
//                 && floor(value) === value;
//         },
//         configurable: true,
//         enumerable: false,
//         writable: true
//     });
// };


