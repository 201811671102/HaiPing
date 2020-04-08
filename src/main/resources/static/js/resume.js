
// var code = JSON.parse($.cookie('auth'))
// $("#resume").click(function () {
//     if (code == 600){
//         PromptBox.displayPromptBox('请先认证学生信息')
//         setTimeout(function () {
//             $(location).attr('href','../html/authStudent.html')
//         })
//     }
//     if (code == 200){
//         $(".add").css({'display':'block'})
//     }
// })
$("#cancelResume").click(function () {
    $(".add").css({'display':'none'})
})

var form = new FormData();
// var data = {
//     uid : user.uid
// }

$("#file").change(function () {
    upload_file = this.files[0]
    form.append('resume',upload_file)
    form.append('sid',user.roleObject.sid)
    $("#upResume").css({'display':'none'})
    $("#get").css({'display':'block'})
    $("#file").css({'display':'none'})
})
function changepic() {
    f = document.getElementById('file').files[0];
    form.append('file',f);
}

$("#addResume").click(function () {
    $.ajax({
        url:'http://39.96.68.53:8080/user/upResume',
        type: 'post',
        dataType: 'json',
        contentType:false,
        data:form,
        processData:false,
        success:function(res){
            if (res.code == 403){
                PromptBox.displayPromptBox('该用户不是学生')
            }
            $(".add").css({'display':'none'})
            PromptBox.displayPromptBox('上传简历成功')
        }
    })
})