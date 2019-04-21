
var IChick = function () {

    var _checkbox;
    var _masterCheckbox;
    var handlerInitiCheck = function () {
        //激活
        $('input[type=checkbox].icheck ,input[type=checkbox].icheck').iCheck({
            checkboxClass: 'icheckbox_minimal-pink',
            radioClass: 'iradio_square-pink'
        })
        _masterCheckbox = $('.icheck_master');
        //获取全部Checkbox集合
        _checkbox =  $("tbody").find("[type='checkbox']");
    };

    var handlerCheckboxAll=function () {
        _masterCheckbox.on("ifClicked",function (e) {

            //当前状态已经选中，点击取消全选
            if(e.target.checked){
                _checkbox.iCheck("uncheck");
            }
            else {
                _checkbox.iCheck("check");
            }

        });
        /**
         * 一个没选取消全选，全部选上勾选全选
         * ifChanged 输入框的状态改变
         */
        _checkbox.on("ifChanged",function (){
            var arr = new Array();
            //遍历除全选框的其他框的状态
            _checkbox.each(function () {
                //如果没有被选中,arr+
                if(!$(this).is(':checked')){
                    arr.push($(this).is(':checked'));
                }

            })
            //有一个及以上没被选择，取消全选
            if(arr.length>0){
                _masterCheckbox.iCheck("uncheck");
            }
            //全部被选择，勾选全选
            else {
                _masterCheckbox.iCheck("check");
            }

        });
    }

    return {
        Init: function () {
            handlerInitiCheck();
            handlerCheckboxAll();
        }
    }
}();
