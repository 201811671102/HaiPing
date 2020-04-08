var user = JSON.parse($.cookie('user'))
$.ajax({
    url:'http://39.96.68.53:8080/enterpriseController/selectEnterprise',
    type: 'get',
    data: {
        eid:user.roleObject.eid
    },
    success:function(res) {
        console.log(res)
        $("#ename").val(res.data.ename)
        $("#eIntroduction").val(res.data.eintroduction)
        $("#eAddress").val(res.data.eaddress)
        $("#eURL").val(res.data.eurl)
    }
})

$("#register").click(function () {
    let ipt = document.getElementsByTagName('input')
    for (let key in ipt){
        if (ipt[key].value == ''){
            return PromptBox.displayPromptBox('请完善信息')
        }
    }
    var user = JSON.parse($.cookie('user'))
    let formData = new FormData()
    formData.append('eid',user.roleObject.eid)
    formData.append('ename',$("#ename").val())
    formData.append('eIntroduction',$("#eIntroduction").val())
    formData.append('eAddress',$("#eAddress").val())
    formData.append('eURL',$("#eURL").val())
    formData.append('_method','put')

    $.ajax({
        url:'http://39.96.68.53:8080/enterpriseController/changeEnterpriseInfo',
        type:'post',
        data:formData,
        dataType:'json',
        contentType:false,
        processData:false,

        success:function(res){
            if (res.code == 200){
                $.removeCookie('user')
                PromptBox.displayPromptBox('修改成功')
                setInterval(function () {
                    $(location).attr('href','../html/login.html')
                },1000)
            }
            else {
                PromptBox.displayPromptBox(res.msg)
            }
        }
    })
})