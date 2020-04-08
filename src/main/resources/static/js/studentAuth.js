$("#blockade").click(function () {
    $("#blockade").css({'display':'none'})
    $(".time").css({'display':'block'})
})
$(".delectButtom").click(function () {
    $(".time").css({'display':'none'})
    $("#blockade").css({'display':'block'})
})


var page = 0,
    size = 10;
getStudent(page,size);
function getStudent(page,size){
    $.ajax({
        url:'http://39.96.68.53:8080/collage/getNotCertification',
        type:'get',
        data:{
            cid:user.roleObject.cid,
            start:page,
            size:size
        },
        success:function(res){
            console.log(res)
            for (let key = 0;key<res.data.length ;key++) {
                $("#studentText").append('<div class="container-fluid content">\n' +
                    '            <div class="col-md-4 col-lg-4 col-sm-4 col-xs-4">\n' +
                    '                <div class="container-fluid">\n' +
                    '                    <div class="img">\n' +
                    '                        <img src="../image/HeadPortrait.jpg">\n' +
                    '                    </div>\n' +
                    '                    <div class="name">\n' +
                    '                        <div>'+res.data[key].sname+'</div>\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '                <div></div>\n' +
                    '            </div>\n' +
                    '            <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3 enterprise">\n' +
                    '                <div>学校：'+res.data[key].scollege+'</div>\n' +
                    '                <div>学号：'+res.data[key].sstudentnumber+'</div>\n' +
                    '            </div>\n' +
                    '            <div class="col-md-2 col-lg-2 col-sm-2 col-xs-2"></div>\n' +
                    '            <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3">\n' +
                    '                <div class="Lable">学生</div>\n' +
                    '                <div class="delect" id="'+res.data[key].sid+'" onclick= authStudent(this)>认证</div>\n' +
                    '            </div>\n' +
                    '        </div>')
            }

        }
    })
}
function authStudent(obj) {
    console.log(obj.id)
    $.ajax({
        url: 'http://39.96.68.53:8080/collage/studentCertification',
        type: 'put',
        data:{
            sid: obj.id
        },
        success:function(res) {
            if (res.code == 200){
                PromptBox.displayPromptBox('认证成功')
                setTimeout(function () {
                    $(location).attr('href','../html/studentAuth.html')
                })
            }
        }
    })
}

