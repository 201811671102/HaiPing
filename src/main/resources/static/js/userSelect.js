var cids = [
    {
        'cid':'学生'
    },{
        'cid':'企业'
    },
    {
        'cid':'学校'
    },
    {
        'cid':'管理员'
    }
];
var user = ''
//已登录显示用户控制台
if ($.cookie('user')){
    user = JSON.parse($.cookie('user'));
    console.log(user)
    $("#quiet").css({'display':'block'})
    $(".userInfo").append('<div class="container-fluid Info">\n' +
        '                <div>\n' +
        '                    <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3">邮箱：</div>\n' +
        '                    <div class="col-md-7 col-lg-7 col-sm-7 col-xs-7" id="email">593288785@qq.com</div>\n' +
        '                </div>\n' +
        '                <div>\n' +
        '                    <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3">手机：</div>\n' +
        '                    <div class="col-md-7 col-lg-7 col-sm-7 col-xs-7" id="phone">17623811139</div>\n' +
        '                </div>\n' +
        '                <div>\n' +
        '                    <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3">身份：</div>\n' +
        '                    <div class="col-md-7 col-lg-7 col-sm-7 col-xs-7" id="cid">学生</div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="container-fluid operation" id="studentResume">\n' +
        '                <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6" id="resume">\n' +
        '                    <span class="glyphicon glyphicon-edit"></span>\n' +
        '                    简历编辑\n' +
        '                </div>\n' +
        '                <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6" id="hasUp">\n' +
        '                    <span class="glyphicon glyphicon-collapse-up"></span>\n' +
        '                    已投简历\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="container-fluid operation" id="etpsResume">\n' +
        '                <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6" id="getResume">\n' +
        '                    <span class="glyphicon glyphicon-edit"></span>\n' +
        '                    收到简历\n' +
        '                </div>\n' +
        '                <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6" id="hasSend">\n' +
        '                    <span class="glyphicon glyphicon-align-left"></span>\n' +
        '                    已发职位\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="container-fluid operation">\n' +
        '                <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6" id="infoChange">\n' +
        '                    <span class="glyphicon glyphicon-cog"></span>\n' +
        '                    账户设置\n' +
        '                </div>\n' +
        '                <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6" id="changePassword">\n' +
        '                    <span class="glyphicon glyphicon-cog"></span>\n' +
        '                    修改密码\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="container-fluid operation" id="adminAuth">\n' +
        '                <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6" id="schoolAuth">\n' +
        '                    <span class="glyphicon glyphicon-inbox"></span>\n' +
        '                    学校认证\n' +
        '                </div>\n' +
        '                <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6" id="etpsAuth">\n' +
        '                    <span class="glyphicon glyphicon-list-alt"></span>\n' +
        '                    企业认证\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="container-fluid operation" id="schoolControl">\n' +
        '                <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6" id="studentAuth">\n' +
        '                    <span class="glyphicon glyphicon-align-left"></span>\n' +
        '                    学生认证\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="container-fluid">\n' +
        '                <div class="buttom" id="quiet">\n' +
        '                    退出\n' +
        '                </div>\n' +
        '            </div>')

    document.getElementById('account').innerText = user.uaccount;
    document.getElementById('email').innerText = user.uemail;
    document.getElementById('phone').innerText = user.uphone;
    document.getElementById('cid').innerText = cids[user.role - 1].cid;
    document.getElementById('img').src = user.uphoto;
}else {
    $(".userInfo").append('<div class="container-fluid">\n' +
        '                <div class="buttom" id="login">\n' +
        '                    登录\n' +
        '                </div>\n' +
        '            </div>')
}


//登录
$("#login").click(function () {
    $(location).attr('href','../html/login.html')
})
//退出
$("#quiet").click(function () {
    $.removeCookie('user');
    $.removeCookie('auth');
    $(location).attr('href','../html/index.html')
})


//判断是否认证
$("#resume").click(function () {
        $(".add").css({'display':'block'})      //添加简历
})
//改密码
$("#changePassword").click(function () {
    $(location).attr('href','../html/changePassword.html')
})


if (user.role == 1){
    $('#studentResume').css({'display':'block'})
}
if (user.role == 2){
    $('#etpsResume').css({'display':'block'})
}
if (user.role == 3){
    $('#etpsResume').css({'display':'block'});
    $("#schoolControl").css({'display':'block'});
    $("#studentAuth").click(function () {
        $(location).attr('href','../html/studentAuth.html')
    })
}
if (user.role == 4){
    $("#adminAuth").css({
        'display':'block'
    })
    $("#etpsAuth").click(function () {
        $(location).attr('href','../html/etpsAuth.html')
    })
    $("#schoolAuth").click(function () {
        $(location).attr('href','../html/schoolAuth.html')
    })

}
$("#infoChange").click(function () {
    if (user.role == 1){
        $(location).attr('href','../html/studentInfoChange.html')
    }
    if (user.role == 3){
        $(location).attr('href','../html/schoolInfoChange.html')
    }
    if (user.role == 2){
        $(location).attr('href','../html/etpsInfoChange.html')
    }
})
$("#hasSend").click(function () {
    $(location).attr('href','../html/hasSend.html')
})
$("#hasUp").click(function () {
    $(location).attr('href','../html/hasUp.html')
})



// $("#getResume").click(function () {
//     if ()
// })

