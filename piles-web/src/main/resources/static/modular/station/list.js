layui.use(['layer', 'form', 'table', 'ax'], function (layer, form, table, $ax) {
    var $ = layui.$;
    var requestDomain = "/station";

    var DataStore = {
        tableId: "table",    //表格id
        pageUrl: requestDomain + "/queryForPage",
        condition: {
            condition: ""
        },
        initColumn: function () {
            return [[
                {type: 'checkbox'},
                {field: 'id', hide: true, sort: true, title: 'id'},
                {field: 'name', sort: true, title: '名称'},
                {field: 'desc', sort: true, title: '备注'},
                {field: 'createTime', sort: true, title: '创建时间'},
                {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
            ]];
        },
        search : function(){
            var queryData = {};
            queryData['condition'] = $("#condition").val();
            table.reload(this.tableId, {where: queryData});
        },
        add: function () {
            layer.open({
                type: 2,
                title: '添加',
                content: requestDomain + '/add',
                end: function () {
                    table.reload(DataStore.tableId);
                    // admin.getTempData('formOk') && table.reload(DataStore.tableId);
                }
            });
        },
        edit: function () {
            layer.open({
                type: 2,
                title: '编辑',
                content: requestDomain + '/edit',
                end: function () {
                    table.reload(DataStore.tableId);
                    // admin.getTempData('formOk') && table.reload(DataStore.tableId);
                }
            });
        },
        detail: function () {
            layer.open({
                type: 2,
                title: '详情',
                content: requestDomain + '/detail',
                end: function () {
                    table.reload(DataStore.tableId);
                    // admin.getTempData('formOk') && table.reload(DataStore.tableId);
                }
            });
        },
        delete: function (data) {
            var operation = function () {
                var ajax = new $ax(requestDomain + "/delete", function (data) {
                    System.success("删除成功!");
                    table.reload(DataStore.tableId);
                }, function (data) {
                    System.error("删除失败!" + data.responseJSON.message + "!");
                });
                ajax.set("dictId", data.dictId);
                ajax.start();
            };
            System.confirm("确定删除 " + data.name + "?", operation);
        }
    };

    table.render({
        elem: '#' + DataStore.tableId,
        url: DataStore.pageUrl,
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: DataStore.initColumn()
    });
    table.on('tool(' + DataStore.tableId + ')', function (obj) {
        debugger
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            DataStore.edit(data);
        } else if (layEvent === 'delete') {
            DataStore.delete(data);
        }
    });
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        DataStore.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        DataStore.add();
    });


});
