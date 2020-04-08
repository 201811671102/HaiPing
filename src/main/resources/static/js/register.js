$(".sendButtom").click(function () {
    $.ajax({
        url: 'http://39.96.68.53:8080/user/userRegisteredEmailCode',
        type: 'POST',
        async:true,
        data: {
            uemail : $('#email').val(),
            uphone :$('#phone').val(),
            uaccount:$('#id').val()
        },
        dataType: 'json',
        success: function (res) {
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
            };
            if (res.code == 201){
                PromptBox.displayPromptBox(res.msg)
            }
            if (res.code == 400) {
                PromptBox.displayPromptBox('发送失败')
            };
        }
    })
})
var form = new FormData(),upload_file;


$("#updataImg").change(function() {
    upload_file = this.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(upload_file)
    reader.onload = function (e) {
        url = this.result
        $("#img").attr("src", url);
    }
    form.append('uphoto',upload_file);
});


$("#register").click(function () {
    console.log(upload_file)
    var email = $("#email").val();
    if ($("#id").val()==''){
        return PromptBox.displayPromptBox('账号不能为空')
    }
    if ($("#password").val()<6){
        return PromptBox.displayPromptBox('密码小于六位')
    }
    if(email == ''){
        return PromptBox.displayPromptBox("请输入您的邮箱");
    }
    if(email != "") {
        var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        isok= reg.test(email );
        if (!isok) {
            return PromptBox.displayPromptBox("邮箱格式不正确，请重新输入！");
        }
    }
    if ($("#phone").val().length!=11){
        return PromptBox.displayPromptBox('手机输入错误')
    }

    var login = {
        uaccount:$("#id").val(),
        upassword:$("#password").val()
    }
    form.append('code',$("#checkEmail").val())
    form.append('uaccount',$("#id").val())
    form.append('upassword',$("#password").val())
    form.append('uemail', email)
    form.append('uphone',$("#phone").val())
    form.append('rid',$("#demo").val())
    console.log(form)
    $.ajax({
        url: 'http://39.96.68.53:8080/user/userRegisteredByEmail',
        async:true,
        processData:false,
        contentType:false,
        type: 'POST',
        data: form,
        dataType: 'json',
        success: function (res) {
            if(res.code ==200){
                // $.cookie('user',JSON.stringify(res.data))
                // $.cookie('login',JSON.stringify(login))
                // $.cookie('res',JSON.stringify(res))
                $.cookie('Uuser',JSON.stringify(res.data))
                PromptBox.displayPromptBox('注册成功')
                setInterval(function () {
                    if ($("#demo").val() == 1){
                        $(location).attr('href','../html/authStudent.html')
                    }
                    if ($("#demo").val() == 2){
                        $(location).attr('href','../html/authEnterprise.html')
                        console.log(res)
                    }
                    if ($("#demo").val() == 3){
                        $(location).attr('href','../html/authSchool.html')
                    }
                },2000)
            }
            if (res.code == 400){
                PromptBox.displayPromptBox('注册失败')
            }
            if (res.code == 403){
                PromptBox.displayPromptBox('验证码超时')
            }
        }

    })

})




