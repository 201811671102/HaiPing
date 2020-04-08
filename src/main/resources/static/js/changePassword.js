$("#change").click(function () {
    if ($('input').val() == ''){
        PromptBox.displayPromptBox('请填写完整。')
    } else {
        let data = new FormData()
        data.append('uaccount',$("#uaccount").val())
        data.append('upassword',$("#upassword").val())
        data.append('unewpassword',$("#unewpassword").val())
        data.append('_method','PUT')
        Udata = {
                    uaccount:$("#uaccount").val(),
                    upassword:$("#upassword").val(),
                    unewpassword:$("#unewpassword").val()
                }
        $.ajax({
            url:'http://39.96.68.53:8080/user/userChangePassword',
            type:'post',
            dataType:'json',
            data:data,
            // contentType:'application/json',
            contentType:false,
            processData:false,
            success:function(res){
                console.log(res)
                $.removeCookie('user')
                $.removeCookie('auth')
                PromptBox.displayPromptBox('密码修改成功')
                setTimeout(function () {
                    $(location).attr('href','../html/login.html')
                },2000)

            }
        })
    }
})