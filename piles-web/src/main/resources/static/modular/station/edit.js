/**
 * 角色详情对话框
 */
var RoleInfoDlg = {
    data: {
        pid: "",
        pName: ""
    }
};

layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var layer = layui.layer;
    var admin = layui.admin;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    //初始化角色的详情数据
    var ajax = new $ax("/role/view/" + System.getUrlParam("roleId"));
    var result = ajax.start();
    form.val('roleForm',result.data);

    // 点击上级角色时
    $('#pName').click(function () {
        var formName = encodeURIComponent("parent.RoleInfoDlg.data.pName");
        var formId = encodeURIComponent("parent.RoleInfoDlg.data.pid");
        // var treeUrl = encodeURIComponent("/role/roleTreeList");

        layer.open({
            type: 2,
            title: '父级角色选择',
            area: ['300px', '200px'],
            content: '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                $("#pid").val(RoleInfoDlg.data.pid);
                $("#pName").val(RoleInfoDlg.data.pName);
            }
        });
    });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax( "/role/edit", function (data) {
            System.success("修改成功!");

            //传给上个页面，刷新table用
            // admin.putTempData('formOk', true);

            //关掉对话框
            // admin.closeThisDialog();
        }, function (data) {
            System.error("修改失败!" + data.responseJSON.message + "!");
        });
        ajax.set(data.field);
        ajax.start();
    });
});
