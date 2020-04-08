var page = 0;
var size = 10;
getWork(page , size)


function getWork(page,size){
    $.ajax({
        url: 'http://39.96.68.53:8080/positionController/showAllPositionOn',
        type:'get',
        data: {
            sepcType:0,
            sepcState:0,
            start:page,
            size:size
        },
        success:function(res) {
            console.log(res)
            for (let key = 0;key<res.data.length;key++){
                $("#workText").append('<div class="container-fluid content" id = "work_'+res.data[key].pid+'">\n' +
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
                    '                    <div class="upResume" id="'+res.data[key].pid+'~'+res.data[key].userObject.uaccount+'" onclick = sendResume(this)>投简历</div>\n' +
                    '                    <div class="delect" id="D'+res.data[key].pid+'" onclick= deleteWork(this)>删除</div>\n' +
                    '                    <div class="addTalker" id="'+res.data[key].userObject.uid+'~'+res.data[key].userObject.uaccount+'~'+res.data[key].userObject.uphoto+'" onclick = addTalker(this)>联系他</div>\n' +
                    '                </div>\n' +
                    '            </div>')
            }
            if (user.role != 1){
                $(".upResume").css({
                    'display':'none'
                })
            }
            if (user.role  != 4){
                $(".delect").css({
                    'display':'none'
                })
            }
        }
    })
}
function addTalker(obj) {
    console.log(obj.id.match(/(\S*)~(\S*)~(\S*)/))
    let id = obj.id.match(/(\S*)~(\S*)~(\S*)/)[1]
    let name = obj.id.match(/(\S*)~(\S*)~(\S*)/)[2]
    let headImg = obj.id.match(/(\S*)~(\S*)~(\S*)/)[3]
    let repeat = 0;
    for (let key in $(".talkLeft").children('div')){
        if ($(".talkLeft").children('div')[key].id == id){
            repeat = 1
        }
    }
    let data = {
        uphoto:headImg,
        fromid:id,
        userDTO:{
            roleObject:{
                cname:name
            },
            role:3
        }
    }
    if (repeat == 0){
        addTalk(data)
    }
    openTalk()
}
function deleteWork(obj) {
    let pid = obj.id.match(/D(\S*)/)[1]
    let reason = prompt("请输入删除理由")
    console.log(reason)
    $.ajax({
        url:'http://39.96.68.53:8080/managerController/nanageDeletePosition',
        type:'post',
        data:{
            message:reason,
            pid:pid,
            _method:'delete'
        },
        success:function(res){
            $(location).attr('href','../html/studentWork.html')
        }
    })
}
function sendResume(obj) {
    let rg = new RegExp('@')
    let id = obj.id
    let pid = id.match(/(\S*)~(\S*)/)[1]
    let email = id.match(/(\S*)~(\S*)/)[2]
    $.ajax({
        url:'http://39.96.68.53:8080/user/sentResume',
        type:'post',
        data:{
            sid : user.roleObject.sid,
            pid : pid,
            email : email
        },
        success:function(res) {
            if (res.code == 400){
                PromptBox.displayPromptBox('请先上传简历')
            }
            if (res.code == 401){
                PromptBox.displayPromptBox('未通过学校审核')
            }
            if (res.code == 403){
                PromptBox.displayPromptBox('请勿重复操作')
            }
            console.log(res)
        }
    })
}