$(".sendButtom").click(function () {
    if ($('input').val() == ''){
        PromptBox.displayPromptBox('请填写完整。')
    } else {
        var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        isok= reg.test($("#uemail").val());
        if (!isok) {
            return PromptBox.displayPromptBox("邮箱格式不正确，请重新输入！");
        }

        let data = new FormData()
        data.append('uaccount',$("#uaccount").val())
        data.append('uemail',$("#uemail").val())
        $.ajax({
            /*url:'http://39.96.68.53:8080/user/userFindPasswordMail',*/
            url:'http://localhost:8080/user/userFindPasswordMail',
            type:'post',
            dataType:'json',
            data:data,
            // contentType:'application/json',
            contentType:false,
            processData:false,
            success:function(res){
                if (res.code == 400) {
                    PromptBox.displayPromptBox('邮箱不存在')
                }
                if (res.code == 401) {
                    PromptBox.displayPromptBox('请不要重复发送验证码')
                }
                if (res.code == 200) {
                    PromptBox.displayPromptBox('发送成功到邮箱');
                    $(".sendButtom").css({'display': 'none'})
                    $("#checkEmail").css({'display':'block'})
                    $("#checkEmail").focus (function () {
                        this.value = ''
                    })
                    $("#checkEmail").blur(function () {
                        if (this.value.length != 4){
                            PromptBox.displayPromptBox('请输入正确的验证码')
                        }
                        if (this.value.length ==''){
                            this.value = '请输入验证码'
                        }
                    })
                }
            }
        })
    }
})

$("#change").click(function () {
    if ($('input').val() == ''){
        PromptBox.displayPromptBox('请填写完整。')
    } else {
        let data = new FormData()
        data.append('uaccount', $("#uaccount").val())
        data.append('uemail', $("#uemail").val())
        data.append('upassword', $("#unewpassword").val())
        data.append('code', $("#checkEmail").val())
        $.ajax({
            /*url:'http://39.96.68.53:8080/user/userFindPasswordMail',*/
            url:'http://localhost:8080/user/userFindPasswordMail',
            type:'post',
            dataType:'json',
            data:data,
            // contentType:'application/json',
            contentType:false,
            processData:false,
            success:function(res){
                if (res.code == 400) {
                    PromptBox.displayPromptBox('验证码错误')
                }
                if (res.code == 401) {
                    PromptBox.displayPromptBox('超时,验证码无效')
                }
                if (res.code == 200) {
                    PromptBox.displayPromptBox('成功修改密码');
                    setTimeout(function () {
                        $(location).attr('href', '../html/login.html')
                    }, 2000)
                }
            }
        })
    }
})

