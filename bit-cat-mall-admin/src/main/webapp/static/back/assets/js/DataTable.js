
var DateTable = function () {
    //私有属性


    /**
     * 私有方法，初始化DataTable
     * @param url
     * @param columns
     */
    var handlerInitDataTable = function (url, columns) {
        var _dataTable = $("#table_id_example").DataTable({
            aLengthMenu:[8],
            //是否开启本地分页
            "paging": true,
            //是否开启本地排序
            "ordering": false,
            //是否允许用户改变表格每页显示的记录数
            "lengthChange": false,
            //是否开启本地搜索
            "searching": false,
            // 是否开启服务器模式
            "serverSide": true,
            // 自动宽度
            "autoWidth": true,
            //
            "pagingType": "full_numbers",
            //通过ajax请求跳转实现
            "ajax": {
                "url": url,
            },
            //数据源
            "columns": columns,
            // 回调函数
            "drawCallback": function () {
                IChick.Init();
                SweetAlert.initSweetAlert();
            },
            loadingMessage:'加载中...',
            // 国际化
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });
        return _dataTable;
    }


    return {
        InitDataTable: function (url, columns) {
            return handlerInitDataTable(url, columns);
        }
    }
}();
