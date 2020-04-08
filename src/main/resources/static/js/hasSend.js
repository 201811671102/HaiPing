var page = 0,size = 10;
if (user.role == 3){
    $.ajax({
        url:'http://39.96.68.53:8080/collage/showCollagePosition',
        type:'get',
        data:{
            cid :user.roleObject.cid,
            start :page,
            size: size,
            sepcState:0
        },
        success(res){
            console.log(res)
            for (let key = 0;key<res.data.length;key++){
                $("#workText").append('<div class="container-fluid content" id = "work_'+res.data[key].pid+'"  onclick = getResume(this)>\n' +
                    '                <div class="col-md-7 col-lg-7 col-sm-7 col-xs-7">\n' +
                    '                    <div class="work">'+res.data[key].ptype+'—'+res.data[key].pname+'</div>\n' +
                    '                    <div class="container-fluid workInfo">\n' +
                    '                        <div>'+'薪 '+res.data[key].pcompensation+'/月</div>\n' +
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
                    '                    <div>'+res.data[key].updateTime+'</div>\n' +
                    '                </div>\n' +
                    '                <div class="col-md-2 col-lg-2 col-sm-2 col-xs-2 control">\n' +
                    '                    <div class="Lable">学校</div>\n' +
                    '                    <div class="delect" id=" pid_'+res.data[key].pid+'" onclick= deleteSend(this)>删除</div>\n' +
                    '                </div>\n' +
                    '            </div>')
            }
        }
    })
}
if (user.role == 2){
    $.ajax({
        url:'http://39.96.68.53:8080/enterpriseController/showEnterprisePosition',
        type:'get',
        data:{
            eid :user.roleObject.eid,
            start :page,
            size: size,
            sepcState:0
        },
        success(res){
            console.log(res)
            for (let key = 0;key<res.data.length;key++){
                $("#workText").append('<div class="container-fluid content">\n' +
                    '                <div class="col-md-7 col-lg-7 col-sm-7 col-xs-7">\n' +
                    '                    <div class="work"  id = "work_'+res.data[key].pid+'"  onclick = getResume(this)>'+res.data[key].ptype+'—'+res.data[key].pname+'</div>\n' +
                    '                    <div class="container-fluid workInfo">\n' +
                    '                        <div>'+'薪 '+res.data[key].pcompensation+'/月</div>\n' +
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
                    '                    <div>'+res.data[key].updateTime+'</div>\n' +
                    '                </div>\n' +
                    '                <div class="col-md-2 col-lg-2 col-sm-2 col-xs-2 control">\n' +
                    '                    <div class="Lable">企业</div>\n' +
                    '                    <div class="delect" id=" pid_'+res.data[key].pid+'" onclick= deleteSend(this)>删除</div>\n' +
                    '                </div>\n' +
                    '            </div>')
            }
        }
    })
}
function getResume(obj) {
    let  pid = obj.id.match(/work_(\S*)/)[1]
    $(".Resumes").fadeIn()
    $.ajax({
        url:'http://39.96.68.53:8080/positionController/checkPosition',
        type:'get',
        data:{
            start: 0,
            size:10,
            pid:pid
        },
        success:function(res){
            console.log(res)
            $(".resumes").empty()
            $(".resumes").append('<div id="pid_'+pid+'">收到简历</div>' +
                '<div class="closeResume" onclick = closeResume()>X</div>')
            for (let key in res.data){
                $(".resumes").append('<div class="resume">\n' +
                    '        <div>\n' +
                    '            <div class="resumeImg">\n' +
                    '                <img src="'+res.data[key].uphoto+'">\n' +
                    '            </div>\n' +
                    '            <div>\n' +
                    '                <div>用户名：'+res.data[key].uaccount+'</div>\n' +
                    '                <div>电话：'+res.data[key].uphone+'</div>\n' +
                    '                <div>邮箱：'+res.data[key].uemail+'</div>\n' +
                    '            </div>\n' +
                    '            <div class="control" onclick = openResume()>\n' +
                    '                <div class="resumeControl">\n' +
                    '                    <div>'+result[res.data[key].roleObject.spState]+'</div>\n' +
                    '                    <div>\n' +
                    '                        <span class="caret"></span>\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '\n' +
                    '                <div class="select"">\n' +
                    '                    <li value = "1" id="sid_'+res.data[key].roleObject.sid+'" onclick = pass(this)>通过审核</li>\n' +
                    '                    <li value = "2" id="sid_'+res.data[key].roleObject.sid+'" onclick = reject(this)>拒绝通过</li>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '\n' +
                    '        </div>\n' +
                    '        <div class="getImfo">\n' +
                    '            <table>\n' +
                    '                <tr>\n' +
                    '                    <td>姓名：</td>\n' +
                    '                    <td>'+res.data[key].roleObject.sname+'</td>\n' +
                    '                </tr>\n' +
                    '                <tr>\n' +
                    '                    <td>学历：</td>\n' +
                    '                    <td>'+res.data[key].roleObject.seducation+'</td>\n' +
                    '                </tr>\n' +
                    '                <tr>\n' +
                    '                    <td>微信号：</td>\n' +
                    '                    <td>'+res.data[key].roleObject.swechar+'</td>\n' +
                    '                </tr>\n' +
                    '                <tr>\n' +
                    '                    <td>学号：</td>\n' +
                    '                    <td>'+res.data[key].roleObject.sstudentnumber+'</td>\n' +
                    '                </tr>\n' +
                    '                <tr>\n' +
                    '                    <td>简历：</td>\n' +
                    '                    <td>点击打开</td>\n' +
                    '                </tr>\n' +
                    '            </table>\n' +
                    '            <table>\n' +
                    '                <tr>\n' +
                    '                    <td>性别：</td>\n' +
                    '                    <td>'+res.data[key].roleObject.ssex+'</td>\n' +
                    '                </tr>\n' +
                    '                <tr>\n' +
                    '                    <td>工作经验：</td>\n' +
                    '                    <td>'+res.data[key].roleObject.sworkexperience+'</td>\n' +
                    '                </tr>\n' +
                    '                <tr>\n' +
                    '                    <td>学校：</td>\n' +
                    '                    <td>广东海洋大学</td>\n' +
                    '                </tr>\n' +
                    '                <tr>\n' +
                    '                    <td>年级：</td>\n' +
                    '                    <td>18级</td>\n' +
                    '                </tr>\n' +
                    '            </table>\n' +
                    '        </div>\n' +
                    '    </div>')
            }
        }
    })
}
var result = {
    0:'未审核',
    1:'通过',
    2:'淘汰'
}
function pass(info) {
    let pid = $(".resumes").find('div').eq(0)[0].id.match(/pid_(\S*)/)[1]
    let sid = info.id.match(/sid_(\S*)/)[1]
    $(".resumeControl").find('div').eq(0).html(result[info.value])
    $.ajax({
        url:'http://39.96.68.53:8080/positionController/passPosition',
        type:'put',
        data:{
            pid:pid,
            sid:sid,
            ifpass:info.value
        },
        success:function(res){
            console.log(res)
        }
    })
}
function reject(info) {
    let pid = $(".resumes").find('div').eq(0)[0].id.match(/pid_(\S*)/)[1]
    let sid = info.id.match(/sid_(\S*)/)[1]
    $(".resumeControl").find('div').eq(0).html(result[info.value])
    $.ajax({
        url:'http://39.96.68.53:8080/positionController/passPosition',
        type:'put',
        data:{
            pid:pid,
            sid:sid,
            ifpass:info.value
        },
        success:function(res){
            console.log(res)
        }
    })
}
function closeResume() {
    $(".Resumes").fadeOut()
}
function openResume() {
    if ($('.select').is(':hidden')){
        $(".select").fadeIn()
    }else{
        $(".select").fadeOut()
    }
}
function deleteSend(obj) {
    console.log(obj.id.match(/pid_(\S*)/))
    let pid = obj.id.match(/pid_(\S*)/)[1]
    $.ajax({
        url:'http://39.96.68.53:8080/positionController/deletePosition',
        type:'post',
        data: {
            _method:'delete',
            pid:pid
        },
        success:function(res) {
            if (res.code == 200){
                PromptBox.displayPromptBox('删除成功')
                setTimeout(function () {
                    $(location).attr('href','../html/hasSend.html')
                },2000)
            }else{
                PromptBox.displayPromptBox(res.msg)
            }
        }
    })
}
// let select = document.getElementsByClassName('select').style.display;



