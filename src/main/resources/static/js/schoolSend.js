$(document).ready(function () {
    $("#result").bind("click", function () {
        $("#result").positionSelect({
            containerId: "positionDiv",
            className: "big-window",
            nameId: "result"
        });
    });
});
var user = JSON.parse($.cookie('user'))
$("#register").click(function () {
    var ipt = document.getElementsByTagName('input');
    for (let key in ipt){
        if (ipt[key].value == ''){
            return PromptBox.displayPromptBox('请完善信息')
        }

    }
    $.ajax({
        url: 'http://39.96.68.53:8080/positionController/addPosition',
        async: 'false',
        type: 'POST',
        dataType: 'json',
        data: {
            'uid': user.uid,
            'pName': $("#pName").val(),
            'pType': $("#result").val(),
            'pDescribe': $("#pDescribe").val(),
            'pRequirements': $("#pRequirements").val(),
            'pCompensation': $("#pCompensation").val(),
            'pWelfare': $("#pWelfare").val(),
            'pAddress': $("#pAddress").val(),
        },
        success:function(res) {
            if (res.code == 200) {
                PromptBox.displayPromptBox(res.msg)
                setTimeout(function () {
                    $(location).attr('href', '../html/index.html')
                }, 2000)
            }
            if (res.code == 400){
                PromptBox.displayPromptBox('等待管理员认证')
            }
        }
    })
})