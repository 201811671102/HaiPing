$("#register").click(function () {
    let ipt = document.getElementsByTagName('input')
    for (let key in ipt){
        if (ipt[key].value == ''){
            return PromptBox.displayPromptBox('请完善信息')
        }
    }
    var Uuser = JSON.parse($.cookie('Uuser'))
    let formData = new FormData()
    formData.append('uid',Uuser.uid)
    formData.append('ename',$("#ename").val())
    formData.append('eIntroduction',$("#eIntroduction").val())
    formData.append('eAddress',$("#eAddress").val())
    formData.append('eURL',$("#eURL").val())

    $.ajax({
        url:'http://39.96.68.53:8080/enterpriseController/addEnterpriseInfo',
        type:'post',
        data:formData,
        dataType:'json',
        contentType:false,
        processData:false,

        success:function(res){
            if (res.code == 200){
                $.removeCookie('user')
                PromptBox.displayPromptBox('认证成功')
                setInterval(function () {
                    let login = JSON.parse($.cookie('login'))
                    $(location).attr('href','../html/login.html')
                },1000)
            }
            else {
                PromptBox.displayPromptBox(res.msg)
            }
        }
    })
})