var user = ''
if ($.cookie('user')){
    user = JSON.parse($.cookie('user'))
} else {
    PromptBox.displayPromptBox('请先登录')
}
$("#register").click(function () {

    let ipt = document.getElementsByTagName('input')
    for (let key in ipt){
        if (ipt[key].value == ''){
            return PromptBox.displayPromptBox('请先完善信息')
        }
    }
    $.ajax({
        url:'http://39.96.68.53:8080/collage/collageChangeInfo',
        type:'put',
        data:{
            cid:user.roleObject.cid,
            cname :$("#cname").val(),
            caddress:$("#caddress").val()
        },
        success:function(res){
            if (res.code == 200){
                PromptBox.displayPromptBox('修改成功')
                setTimeout(function () {
                    $(location).attr('href','../html/index.html')
                },2000)
            } else {
                PromptBox.displayPromptBox(res.msg)
            }
        }
    })
})