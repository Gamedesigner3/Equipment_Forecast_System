$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/params/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'Id', index: 'Id', width: 50, key: true, hidden: true},
            {label: '参数', name: 'idx1', index: 'idx1', width: 300}
        ],
        height: 560,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
});

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function paramAdd() {
    reset();
    $('.modal-title').html('参数添加');
    $('#paramModal').modal('show');
}

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    var idx1 = $("#idx1").val();
    var id = getSelectedRowWithoutAlert();
    var url = '/admin/params/update';
    var data = {
            "id": id,
            "idx1": idx1
    };
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            if (result.resultCode == 200) {
                $('#paramModal').modal('hide');
                swal("保存成功", {
                    icon: "success",
                });
                reload();
            } else {
                $('#paramModal').modal('hide');
                swal(result.message, {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
});

function paramEdit() {
    reset();
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    //请求数据
    $.get("/admin/params/info/" + id, function (r) {
        if (r.resultCode == 200 && r.data != null) {
            //填充数据至modal
            $("#idx1").val(r.data.idx1);
        }
    });
    $('.modal-title').html('轮播图编辑');
    $('#paramModal').modal('show');
}

function reset() {
    $("#idx1").val(0);
}