/**
 * 检查页面权限
 */
function CheckPermissions() {
    console.log('------检查页面权限------');
    let url = window.location.pathname + '.htm';
    CallAjaxGetNoParam(url, CheckPermissionsSuc, null, null, false);
}

function CheckPermissionsSuc(data) {
    console.log("CheckPermissionsSuc: ", data);
}