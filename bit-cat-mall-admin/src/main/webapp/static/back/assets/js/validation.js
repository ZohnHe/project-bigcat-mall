$().ready(function() {
    $.validator.addMethod("tel",function (value,element) {
        var length = value.length;
        var tels = /^1[34578][0-9]{9}$/;
        return this.optional(element) || (length == 11 && tels.test(value));
    },"手机号码格式错误");

    $("#saveUser").validate({

        //用什么标签标记错误，默认是 label
        errorElement:'span',
        //指定错误提示的css类型名
        errorClass:'vali',
        //error指错误信息（如“用户名格式错误”）

        errorPlacement(error,elment){
            error.insertAfter(elment);
        },

        rules:
            {
                username:{
                    required:true,
                    minlength:2,
                },

                phone:{
                    required:true,
                    tel:"",
                },
                email:{
                    required:true,
                    email:true,
                },
            },
        messages:
            {
                username: {
                    required: "用户名不能为空",
                    minlength: "至少由两个字符组成"
                },
                phone: {
                    required: "电话号码不能为空",
                },

                email: {
                    required:"邮箱不能为空",
                    email: "请输入正确的邮箱样式",
                }
            },

        //使用ajax异步请求来提交表单，局部刷新不用重新加载全部页面资源
        submitHandler:function (form) {
            $(form).ajaxSubmit();
        }
    });
    $("#userPwd").validate({

        errorElement :'span',
        errorClass:'vali',
        errorPlacement(error,elment){
            error.insertAfter(elment);
        },

        rules:
            {
                oldPwd:{
                    required:true,
                },
                newPwd:{
                    required:true,
                    minlength:3,
                },
                newPwd2:{
                    required:true,
                    equalTo: "#newPwd"
                },

            },
        messages:
            {
                oldPwd:{
                    required:"请输入当前密码",
                },
                newPwd:{
                    required:"请输入新密码",
                    minlength:"密码过短",
                },
                newPwd2:{
                    required:"请再次输入新密码",
                    equalTo:"两次密码不一致",
                },
            },

        //使用ajax异步请求来提交表单，局部刷新不用重新加载全部页面资源
        submitHandler(form){
            $(form).ajaxSubmit();
        }

    });

    $("#addUser").validate({

        //用什么标签标记错误，默认是 label
        errorElement:'span',
        //指定错误提示的css类型名
        errorClass:'vali',
        //error指错误信息（如“用户名格式错误”）

        errorPlacement(error,elment){
            error.insertAfter(elment);
        },

        rules:
            {
                username:{
                    required:true,
                    minlength:2,
                },

                phone:{
                    required:true,
                    tel:"",
                },
                password:{
                    required:true,
                },
                email:{
                    required:true,
                    email:true,
                },
            },
        messages:
            {
                username: {
                    required: "用户名不能为空",
                    minlength: "至少由两个字符组成"
                },
                phone: {
                    required: "电话号码不能为空",
                },
                password:{
                    required:"密码不能为空"
                },
                email: {
                    required:"邮箱不能为空",
                    email: "请输入正确的邮箱样式",
                }
            },

        //使用ajax异步请求来提交表单，局部刷新不用重新加载全部页面资源
        submitHandler:function (form) {
            $(form).ajaxSubmit();
        }
    });

});

