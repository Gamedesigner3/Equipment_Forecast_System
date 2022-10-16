$(function () {
    $("#jqGrid").jqGrid({
        url: '/forecast/result',
        datatype: "json",
        colModel: [
            {label: 'idx1', name: 'Idx1', index: 'Idx1', width: 300, key: true, hidden: true},
            {label: 'idx2', name: 'Idx1', index: 'Idx1', width: 300}
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
                swal("装备可靠", {
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
