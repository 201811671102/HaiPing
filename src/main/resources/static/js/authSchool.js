
$("#register").click(function () {
    let ipt = document.getElementsByTagName('input');
    for (let key in ipt){
        if (ipt[key].value == ''){
            return PromptBox.displayPromptBox('请先完善信息')
        }
    }
    var Uuser = JSON.parse($.cookie('Uuser'))
    $.ajax({
        url:'http://39.96.68.53:8080/collage/collageAddInfo',
        type:'post',
        data:{
            uid:Uuser.uid,
            caddress:$("#caddress").val(),
            cname:$("#cname").val()
        },
        success:function(res) {
            if (res.code == 200){
                PromptBox.displayPromptBox('认证成功')
                setTimeout(function () {
                    $(location).attr('href','../html/login.html')
                },2000)
            }
            if (res.code == 201){
                PromptBox.displayPromptBox('已被注册')
            }
            if (res.code == 500){
                PromptBox.displayPromptBox('服务器出现错误')
            }
        }
    })
})