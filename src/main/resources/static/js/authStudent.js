var Uuser = JSON.parse($.cookie('Uuser'))
// if ($.cookie('Uuser')){
//     var Uuser = JSON.parse($.cookie('Uuser'))
// }
var serverNum = 0
$("#add").click(function () {
    serverNum += 1
    console.log(serverNum)
    $("#serverInfo").append('<li class="container-fluid">\n' +
        '                                    <div class="col-md-4 col-lg-4 col-sm-4 col-xs-4">\n' +
        '                                        <input placeholder="项目名称" class = "vname">\n' +
        '                                    </div>\n' +
        '                                    <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3">\n' +
        '                                        <input placeholder="总服务时长" class = "vtype">\n' +
        '                                    </div>\n' +
        '                                    <div class="col-md-5 col-lg-5 col-sm-5 col-xs-5">\n' +
        '                                        <input placeholder="项目所属类" class="vstime">\n' +
        '                                    </div>\n' +
        '                                </li>')
    $("#serverInfo li").eq(serverNum-1).attr('id','server_'+serverNum)
})
var prideNum = 0
$("#prideAdd").click(function () {
    prideNum += 1
    console.log(prideNum)
    $("#prideInfo").append('<li class="col-md-12 col-lg-12 col-sm-12 col-xs-12">\n' +
        '                                    <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6">\n' +
        '                                        <input placeholder="证书名">\n' +
        '                                    </div>\n' +
        '                                    <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6">\n' +
        '                                        <input placeholder="证书所属类">\n' +
        '                                    </div>\n' +
        '                                </li>')
    $("#prideInfo li").eq(prideNum-1).attr('id','pride_'+prideNum)
    $("#prideInfo li").eq(prideNum-1).attr('id','pride_'+prideNum)
})



$("#register").click(function () {
    var ssex = $("#gentle").val()
    var seducation = $("#Education").val()
    console.log()
    let cert = new FormData()
    cert.append('uid',Uuser.uid)
    cert.append('scollege',$("#scollege").val())
    cert.append('sstudentnumber',$("#sstudentnumber").val())
    cert.append('sgrade',$("#sgrade").val())
    cert.append('scollegeaddress',$("#scollegeaddress").val())
    cert.append('sname',$("#sname").val())
    cert.append('ssex',ssex)
    cert.append('seducation',seducation)
    cert.append('swechar',$("#swechar").val())
    cert.append('sworkexperience',$("#sworkexperience").val())
    var input = document.getElementsByTagName('input')
    for (let i = 0 ;i<input.length;i++){
        if (input[i].value == ''){
            return PromptBox.displayPromptBox('请完善以上信息')
        }
    }
    $.ajax({
        url:'http://39.96.68.53:8080/student/studentAndInfo',
        type:'post',
        contentType:false,
        dataType:'json',
        processData:false,
        data:cert,
        success:function(res){
            if (res.code == 201){
                PromptBox.displayPromptBox('微信号已被注册')
            }
            if (res.code == 200){
                addSV()
            }
        }
    })
})
function addSSC() {
    if (prideNum == 0){
        PromptBox.displayPromptBox('认证成功');
        $(location).attr('href','../html/login.html')
        // setInterval(function () {
        //     let login = JSON.parse($.cookie('login'))
        //     $.ajax({
        //         url:'http://39.96.68.53:8080/user/userLog',
        //         type:'POST',
        //         async:true,
        //         data:login,
        //         dataType: 'json',
        //         success(res){
        //             if (res.code ==200) {
        //                 // function windowAttr(){
        //                 //     $(location).attr('href','../html/index.html')
        //                 // }
        //                 $.cookie("user", JSON.stringify(res.data), { expires: 1 });
        //                 $.cookie("auth", JSON.stringify(res.code), { expires: 1 });
        //                 // windowAttr()
        //                 $(location).attr('href','../html/index.html')
        //             }
        //             if (res.code ==600) {
        //                 $.cookie("user", JSON.stringify(res.data), { expires: 1 });
        //                 $.cookie("auth", JSON.stringify(res.code), { expires: 1 });
        //                 PromptBox.displayPromptBox('请先进行认证')
        //                 setTimeout(function () {
        //                     if (res.data.role == 1){
        //                         $(location).attr('href','../html/authStudent.html')
        //                     }
        //                     if (res.data.role == 2){
        //                         $(location).attr('href','../html/authEnterprise.html')
        //                     }
        //                     if (res.data.role == 3){
        //                         $(location).attr('href','../html/authSchool.html')
        //                     }
        //                 },2000)
        //             }
        //             if (res.code == 400){
        //                 PromptBox.displayPromptBox('密码错误')
        //             }
        //             if (res.code == 404){
        //                 PromptBox.displayPromptBox('用户不存在')
        //             }
        //             if (res.code == 500){
        //                 PromptBox.displayPromptBox('服务器出现问题了')
        //             }
        //             // $(window).attr('location','../html/index.html')
        //         }
        //     })
        // },1000)
    }else{
        for (let key = 0 ; key<prideNum ; key++){

            let server = {
                uid : Uuser.uid,
                cname:$("#prideInfo>li").eq(key)[0].children[0].children[0].value,
                stype:$("#prideInfo>li").eq(key)[0].children[1].children[0].value
            }
            console.log(server)
            for (let info in server){
                if (server[info] == ''){
                    return PromptBox.displayPromptBox('请完善相关证书信息')
                }
            }
            $.ajax({
                url: 'http://39.96.68.53:8080/SCController/addSC',
                type: 'post',
                dataType: 'json',
                data:server,
                success:function(res) {
                    if (res.code == 201){
                        PromptBox.displayPromptBox('服务器发生错误')
                    }
                    if(res.code == 200){
                        PromptBox.displayPromptBox('认证成功')
                        // setInterval(function () {
                        //     let login = JSON.parse($.cookie('login'))
                        //     $.ajax({
                        //         url:'http://39.96.68.53:8080/user/userLog',
                        //         type:'POST',
                        //         async:true,
                        //         data:login,
                        //         dataType: 'json',
                        //         success(res){
                        //             if (res.code ==200) {
                        //                 // function windowAttr(){
                        //                 //     $(location).attr('href','../html/index.html')
                        //                 // }
                        //                 $.cookie("user", JSON.stringify(res.data), { expires: 1 });
                        //                 $.cookie("auth", JSON.stringify(res.code), { expires: 1 });
                        //                 // windowAttr()
                        //                 $(location).attr('href','../html/index.html')
                        //             }
                        //             if (res.code ==600) {
                        //                 $.cookie("user", JSON.stringify(res.data), { expires: 1 });
                        //                 $.cookie("auth", JSON.stringify(res.code), { expires: 1 });
                        //                 PromptBox.displayPromptBox('请先进行认证')
                        //                 setTimeout(function () {
                        //                     if (res.data.role == 1){
                        //                         $(location).attr('href','../html/authStudent.html')
                        //                     }
                        //                     if (res.data.role == 2){
                        //                         $(location).attr('href','../html/authEnterprise.html')
                        //                     }
                        //                     if (res.data.role == 3){
                        //                         $(location).attr('href','../html/authSchool.html')
                        //                     }
                        //                 },2000)
                        //             }
                        //             if (res.code == 400){
                        //                 PromptBox.displayPromptBox('密码错误')
                        //             }
                        //             if (res.code == 404){
                        //                 PromptBox.displayPromptBox('用户不存在')
                        //             }
                        //             if (res.code == 500){
                        //                 PromptBox.displayPromptBox('服务器出现问题了')
                        //             }
                        //             // $(window).attr('location','../html/index.html')
                        //         }
                        //     })
                        // },1000)
                        $(location).attr('href','../html/login.html')
                    }
                }
            })
        }
    }

}

function addSV() {
    if (serverNum == 0){
        addSSC()
    } else{
        for (let key = 0 ; key<serverNum ; key++){

            let server = {
                uid : Uuser.uid,
                vname:$("#serverInfo>li").eq(key)[0].children[0].children[0].value,
                vtype:$("#serverInfo>li").eq(key)[0].children[1].children[0].value,
                vstime:$("#serverInfo>li").eq(key)[0].children[2].children[0].value
            }
            for (let info in server){
                if (server[info] == ''){
                    return PromptBox.displayPromptBox('请完善志愿服务信息')
                }
            }
            $.ajax({
                url: 'http://39.96.68.53:8080/SVController/addSV',
                type: 'post',
                dataType: 'json',
                data:server,
                success:function(res) {
                    addSSC()
                }
            })
        }

    }
}