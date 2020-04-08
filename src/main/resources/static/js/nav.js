$(".head").append('<div class="row">\n' +
    '            <div class="col-md-2 col-lg-2 col-sm-2 col-xs-2 logo">HP海聘</div>\n' +
    '            <div class="col-md-7 col-lg-7 col-sm-7 col-xs-7 Nav">\n' +
    '                <div id="index">首页</div>\n' +
    '                <div id="studentWork">勤工俭学</div>\n' +
    '            </div>\n' +
    '            <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3 container-fluid">\n' +
    '                <div id="city"></div>\n' +
    '                <div class="cityBlock">\n' +
    '                    <div class="border container-fluid">\n' +
    '                        <div id="city_3" class=" col-md-7 col-lg-7 col-sm-7 col-xs-7">\n' +
    '                            <select class="prov" id="selectProvince"></select>\n' +
    '                            <select class="city" disabled="disabled" id="selectCity"></select>\n' +
    '                        </div>\n' +
    '                        <div class="cityButtom col-md-5 col-lg-5 col-sm-5 col-xs-5" id="citySet">确认</div>\n' +
    '                        <div class="cityButtom col-md-5 col-lg-5 col-sm-5 col-xs-5" id="cancel">取消</div>\n' +
    '                    </div>\n' +
    '\n' +
    '                </div>\n' +
    '            </div>\n' +
    '        </div>')

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
if ($.cookie('user')){
    let user = JSON.parse($.cookie('user'));
    if (user.role == 4){
        $(".Nav").append('<div id="adminUser">管理用户</div>')
        // $(".control").append('<div class="delect" onclick="delete(this)">删除</div>')
    }
    if (user.role == 3){
        $(".Nav").append('<div id="schoolSend">学校发布</div>')
    }
    if (user.role == 2){
        $(".Nav").append('<div id="enterpriseSend">企业发布</div>')
    }

}