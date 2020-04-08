// $(function(){
//     console.log(1)
//     $("#city_3").citySelect({
//         prov:"湖南",
//         city:"长沙"
//     });
//
// });
var position = new Object()
var city = ''
var province = ''

if ($.cookie('selectPosition')){
    var selectPosition = $.cookie('selectPosition');
    var selectPosition = JSON.parse($.cookie('selectPosition'))
    console.log(selectPosition)
    $('#city').append(selectPosition.selectProvince+' '+selectPosition.selectCity)
}else {
    function showLocation(data) {
        city = data.content.address_detail.city
        province = data.content.address_detail.province
        $('#city').append(province+' '+city)
    }
    $.getScript("http://api.map.baidu.com/location/ip?ak=rCN6PAQ5GSf8yw0RFuyH5Cno7zVtNoEW&callback=showLocation");
}
// if ($.cookie('user')) {
//     var user = JSON.parse($.cookie('user'));
//     if (user.role == 1) {
//         $(".Nav>div:nth-child(3)").css({'display': 'none'})
//         $(".Nav>div:nth-child(4)").css({'display': 'none'})
//         $(".Nav>div:nth-child(5)").css({'display': 'none'})
//     }
//     if (user.role == 2) {
//         $(".Nav>div:nth-child(3)").css({'display': 'none'})
//         $(".Nav>div:nth-child(5)").css({'display': 'none'})
//     }
//     if (user.role == 3) {
//         $(".Nav>div:nth-child(4)").css({'display': 'none'})
//         $(".Nav>div:nth-child(5)").css({'display': 'none'})
//     }
//     if (user.role == 4) {
//         $(".Nav>div:nth-child(3)").css({'display': 'none'})
//         $(".Nav>div:nth-child(4)").css({'display': 'none'})
//     }
// }else {
//     $(".Nav>div:nth-child(3)").css({'display': 'none'})
//     $(".Nav>div:nth-child(4)").css({'display': 'none'})
//     $(".Nav>div:nth-child(5)").css({'display': 'none'})
// }





$("#city").click(function () {
    $("#city_3").citySelect({
        prov:"湖南",
        city:"长沙"
    });
    $(".cityBlock").css({'display':'block'})
})

$('#citySet').click(function () {
    $(".cityBlock").css({'display':'none'})
    position.selectProvince = $('#selectProvince').val()
    position.selectCity = $("#selectCity").val()
    console.log(position.selectCity,position.selectProvince)
    $.cookie("selectPosition", JSON.stringify(position), { expires: 1 });
    $(location).attr('href','../html/index.html')
})
$('#cancel').click(function () {
    $(".cityBlock").css({'display':'none'})
})
$("#studentWork").click(function () {
    $(location).attr('href','../html/studentWork.html')
})
$("#index").click(function () {
    $(location).attr('href','../html/index.html')
})
$("#schoolSend").click(function () {
    // if (code == 600){
    //     PromptBox.displayPromptBox('请先学校认证')
    //     setTimeout(function () {
    //         $(location).attr('href','../html/authSchool.html')
    //     },1000)
    // }
    // if (code == 200){
    //     $(location).attr('href','../html/schoolSend.html')
    // }
    $(location).attr('href','../html/schoolSend.html')

})
$("#enterpriseSend").click(function () {
    $(location).attr('href','../html/enterpriseSend.html')
})
$("#adminUser").click(function () {
    $(location).attr('href','../html/adminUser.html')
})




let CreatPromptBox =function(){
    let newNode=document.createElement('div');
    newNode.classList.add('PromptBox');
    newNode.style.display='none';
    document.body.appendChild(newNode);
    this.newNode=newNode;
};
CreatPromptBox.prototype.displayPromptBox=function(text){
    this.newNode.innerText=text;
    $(this.newNode).fadeIn(500);
    $(this.newNode).fadeOut(1500);
};

let PromptBox = new CreatPromptBox();
if ($.cookie('user')){
    var user = JSON.parse($.cookie('user'))
    var websocket =  new WebSocket('ws:39.96.68.53:8080/WebsocketServer/'+user.uid)
    websocket.onopen = function () {
        console.log('连接成功')
    }
    websocket.onmessage = function (res) {
        getMessage(res)
    }
    window.onbeforeunload = function() {
        websocket.onclose = function () {}; // disable onclose handler first
        websocket.close()
    };
}

function getMessage(res) {
    console.log(JSON.parse(res.data))
    let data = JSON.parse(res.data)
    let repeat = 0;
    for (let key in $(".talkLeft").children('div')){
        if ($(".talkLeft").children('div')[key].id == data.fromid){
            repeat = 1
        }
    }
    if (repeat == 0){
        addTalk(data)
    }
    document.getElementById('talkWith'+data.fromid).firstElementChild.innerHTML+='<div class="container-fluid talkLine">\n' +
        '                        <div class="talkHead">\n' +
        '                            <img src="'+data.uphoto+'">\n' +
        '                        </div>\n' +
        '                        <div class="talkText">'+data.message+'</div>\n' +
        '                    </div>'
}
function openTalk() {
    if ($.cookie('user')){
        $(".communication").fadeIn()
    }else{
        PromptBox.displayPromptBox('请先登录')
    }
}
function addTalk(data) {
    if (data.userDTO.role == 4){
        $(".talkLeft").append('<div class="list" id="'+data.fromid+'" onclick= switchTalk(this)>\n' +
            '                <div class="listImg">\n' +
            '                    <img src="'+data.uphoto+'">\n' +
            '                </div>\n' +
            '                <div class="listName">'+data.userDTO.roleObject.mname+'</div>\n' +
            '                <div class="identify">'+roleSelect[data.userDTO.role]+'</div>\n' +
            '            </div>')
    }
    if (data.userDTO.role == 1){
        $(".talkLeft").append('<div class="list" id="'+data.fromid+' "  onclick= switchTalk(this)>\n' +
            '                <div class="listImg">\n' +
            '                    <img src="'+data.uphoto+'">\n' +
            '                </div>\n' +
            '                <div class="listName">'+data.userDTO.roleObject.mname+'</div>\n' +
            '                <div class="identify">'+roleSelect[data.userDTO.role]+'</div>\n' +
            '            </div>')
    }
    if (data.userDTO.role == 3){
        $(".talkLeft").append('<div class="list" id="'+data.fromid+'"  onclick= switchTalk(this)>\n' +
            '                <div class="listImg">\n' +
            '                    <img src="'+data.uphoto+'">\n' +
            '                </div>\n' +
            '                <div class="listName">'+data.userDTO.roleObject.cname+'</div>\n' +
            '                <div class="identify">'+roleSelect[data.userDTO.role]+'</div>\n' +
            '            </div>')
    }
    if (data.userDTO.role == 2){
        $(".talkLeft").append('<div class="list" id="'+data.fromid+'"  onclick= switchTalk(this)>\n' +
            '                <div class="listImg">\n' +
            '                    <img src="'+data.uphoto+'">\n' +
            '                </div>\n' +
            '                <div class="listName">'+data.userDTO.roleObject.ename+'</div>\n' +
            '                <div class="identify">'+roleSelect[data.userDTO.role]+'</div>\n' +
            '            </div>')
    }
    $(".talkRight").append('<div class="hiddenS" id="talkWith'+data.fromid+'">\n' +
        '                <div class="talk">\n' +
        '                </div>\n' +
        '            </div>')
}
$(".closeIt").click(function () {
    $(".communication").fadeOut()
})
function switchTalk(obj) {
    $(".hiddenSs").remove()
    console.log(obj.childNodes[3].textContent)
    $(".talkName").html( obj.childNodes[3].textContent)
    $(".edit").remove()
    let all = document.getElementById('talkWith'+obj.id).parentNode.children
    for (let key = 0;key<all.length;key++){
        console.log(key)
        all[key].classList.remove('active')
    }
    document.getElementById('talkWith'+obj.id).classList.add('active')

    $(".talkRight").append('<div class="edit">\n' +
        '                <div class="sendMsg"  onclick= sendMsg()>发送</div>\n' +
        '                <textarea id="toSendText">\n' +
        '\n' +
        '                </textarea>\n' +
        '            </div>')
    $(".edit").css({
        'display':'block'
    })
}
function sendMsg() {
    let id = document.getElementsByClassName("active")[0].id.match(/talkWith(\S*)/)[1]
    websocket.onopen = function () {
        console.log('连接成功')
    }
    $.ajax({
        url:'http://39.96.68.53:8080/webSocketController/sendMessage',
        type:'post',
        data:{
            fromid:user.uid,
            toid:id,
            message:$("#toSendText").val()
        },
        success:function(res){
            console.log(res)
            if (res.code == 200){
                $(".active").children('div').append('\n' +
                    '                    <div class="container-fluid sendLine">\n' +
                    '\n' +
                    '                        <div class="sendHead">\n' +
                    '                            <img src="../image/headImg-Man.jpg">\n' +
                    '                        </div>\n' +
                    '                        <div class="sendText">'+$("#toSendText").val()+'</div>\n' +
                    '                    </div>')
                $("#toSendText").val('')
            }
        }
    })
}

$(".sendMsg").click(function () {


})
var roleSelect = {
    1:'学生',
    2:'企业',
    3:'学校',
    4:'管理员'
}