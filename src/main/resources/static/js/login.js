$(".buttom").click(function () {
    let data = {
        'uaccount':$("#account").val(),
        'upassword':$("#password").val()
    }
    $.cookie('login',JSON.stringify(data))
    $.ajax({
        url:'http://39.96.68.53:8080/user/userLog',
        type:'POST',
        async:true,
        data:data,
        dataType: 'json',
        success:function(res){
            if (res.code ==200) {
                $.cookie("user", JSON.stringify(res.data), { expires: 1 });
                $(location).attr('href','../html/index.html')
            }
            if (res.code ==600) {
                $.cookie("Uuser", JSON.stringify(res.data), { expires: 1 });
                PromptBox.displayPromptBox('请先进行认证')
                setTimeout(function () {
                    if (res.data.role == 1){
                        $(location).attr('href','../html/authStudent.html')
                    }
                    if (res.data.role == 2){
                        $(location).attr('href','../html/authEnterprise.html')
                    }
                    if (res.data.role == 3){
                        $(location).attr('href','../html/authSchool.html')
                    }
                },2000)
            }
            if (res.code == 400){
                PromptBox.displayPromptBox('密码错误')
            }
            if (res.code == 401){
                alert(res.msg)
            }
            if (res.code == 404){
                PromptBox.displayPromptBox('用户不存在')
            }
            if (res.code == 500){
                PromptBox.displayPromptBox('服务器出现问题了')
            }
            // $(window).attr('location','../html/index.html')
        }
    })
})
if ($.cookie('user')){
    $(location).attr('href','../html/index.html')
}
// $('#account').focus(function () {
//     this.value = ''
// })
// $('#account').blur(function () {
//     console.log('blur')
//     if (this.value == ''){
//         console.log('账号为空')
//         this.value = '账号'
//     }
// })
// $('#password').focus(function () {
//     this.value = ''
//     this.type = 'password'
// })
// $('#account').blur(function () {
//     console.log('blur')
//     if (this.value == ''){
//         console.log('账号为空')
//         this.value = '账号'
//     }
// })
// var inputs = document.getElementsByTagName('input')
// var type = Array.prototype.slice.call(inputs)
// console.log(type)
// type.forEach(function (item) {
//     let name = item.value
//     item.onfocus = function () {
//         if (this.value == name) {
//             this.value = ''
//         }
//     }
//     item.onblur = function () {
//         if (this.value == ''){
//             this.value = name
//         }
//     }
// })
