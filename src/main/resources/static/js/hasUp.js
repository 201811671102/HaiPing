var page = 0,size = 10;

$.ajax({
    url:'http://39.96.68.53:8080/student/getPositionInfo',
    type:'get',
    data:{
        start:page,
        size:size,
        sid:user.roleObject.sid
    },
    success:function(res){
        console.log(res)
        if (res.code == 200){
            var show = new GetWorks(res)
            show.modolShow($('#workText'))
        }

    }
})


function GetWorks(res) {
    this.modols = [];
    if (res.data.length>0){
        for (let key = 0;key<res.data.length;key++){
            if (res.data[key].userObject.roleObject.ename){
                this.modols.push('<div class="container-fluid content" id = "work_'+res.data[key].pid+'">\n' +
                    '                <div class="col-md-7 col-lg-7 col-sm-7 col-xs-7">\n' +
                    '                    <div class="work">'+res.data[key].ptype+'—'+res.data[key].pname+'</div>\n' +
                    '                    <div class="container-fluid workInfo">\n' +
                    '                        <div>'+'薪 '+res.data[key].pcompensation+'</div>\n' +
                    '                        <div class="gan">|</div>\n' +
                    '                        <div>'+res.data[key].paddress+'</div>\n' +
                    '                        <div class="gan">|</div>\n' +
                    '                        <div>福利 '+res.data[key].pwelfare+'</div>\n' +
                    '                        <div class="gan">|</div>\n' +
                    '                        <div>5年经验</div>\n' +
                    '                    </div>\n' +
                    '                    <div>'+res.data[key].prequirements+'</div>\n' +
                    '                </div>\n' +
                    '                <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3 enterprise">\n' +
                    '                    <div>'+res.data[key].userObject.roleObject.ename+'</div>\n' +
                    '                    <div>'+res.data[key].userObject.roleObject.eintroduction+'</div>\n' +
                    '                </div>\n' +
                    '                <div class="col-md-2 col-lg-2 col-sm-2 col-xs-2 control">\n' +
                    '                    <div class="Lable">企业</div>\n' +
                    '                    <div class="delect" onclick= delete(this)>删除</div>\n' +
                    '                </div>\n' +
                    '            </div>')
            }else{
                this.modols.push('<div class="container-fluid content" id = "work_'+res.data[key].pid+'">\n' +
                    '                <div class="col-md-7 col-lg-7 col-sm-7 col-xs-7">\n' +
                    '                    <div class="work">'+res.data[key].ptype+'—'+res.data[key].pname+'</div>\n' +
                    '                    <div class="container-fluid workInfo">\n' +
                    '                        <div>'+'薪 '+res.data[key].pcompensation+'</div>\n' +
                    '                        <div class="gan">|</div>\n' +
                    '                        <div>'+res.data[key].paddress+'</div>\n' +
                    '                        <div class="gan">|</div>\n' +
                    '                        <div>福利 '+res.data[key].pwelfare+'</div>\n' +
                    '                        <div class="gan">|</div>\n' +
                    '                        <div>5年经验</div>\n' +
                    '                    </div>\n' +
                    '                    <div>'+res.data[key].prequirements+'</div>\n' +
                    '                </div>\n' +
                    '                <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3 enterprise">\n' +
                    '                    <div>'+res.data[key].userObject.roleObject.cname+'</div>\n' +
                    '                    <div>教育/培训/学术/科研/院校</div>\n' +
                    '                </div>\n' +
                    '                <div class="col-md-2 col-lg-2 col-sm-2 col-xs-2 control">\n' +
                    '                    <div class="Lable">学校</div>\n' +
                    '                    <div class="upResume">'+static[res.data[key].spState]+'</div>\n' +
                    '                </div>\n' +
                    '            </div>')
            }
        }
    }
}
GetWorks.prototype.modolShow = function (htmlText) {
    htmlText.append(this.modols)
}
var static = {
    0:'正在审核',
    1:'已被录取',
    2:'落选'
}