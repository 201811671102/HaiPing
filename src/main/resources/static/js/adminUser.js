var start = 0,size = 10;
$.ajax({
    url:'http://39.96.68.53:8080/managerController/findAllUser',
    type:'get',
    data:{
        start:start,
        size:size,
        rid:-1
    },
    success(res){
        console.log(res)
        let showUser = new GetUser(res.data)
        showUser.show()
    }
})
let identify = {
    1:'学生',
    2:'企业',
    3:'学校',
    4:'管理员'
}
function GetUser(data) {
    this.allUser = [];
    for (let key in data){
        this.allUser.push('<div class="container-fluid content">\n' +
            '            <div class="col-md-4 col-lg-4 col-sm-4 col-xs-4">\n' +
            '                <div class="container-fluid">\n' +
            '                    <div class="img">\n' +
            '                        <img src="'+data[key].uphoto+'">\n' +
            '                    </div>\n' +
            '                    <div class="name">\n' +
            '                        <div>'+data[key].uaccount+'</div>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '                <div></div>\n' +
            '            </div>\n' +
            '            <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3 enterprise">\n' +
            '                <div></div>\n' +
            '                <div></div>\n' +
            '            </div>\n' +
            '            <div class="col-md-2 col-lg-2 col-sm-2 col-xs-2"></div>\n' +
            '            <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3">\n' +
            '                <div class="Lable">'+identify[data[key].role]+'</div>\n' +
            '                <div class="delect" id="'+data[key].uemail+'" onclick=closeUser(this)>封号</div>\n' +
            '                <div class="time container-fluid" id="@'+data[key].uemail+'">\n' +
            '                    <input class="col-md-4 col-lg-4 col-sm-4 col-xs-4" value="1">\n' +
            '                    <select class="col-md-6 col-lg-6 col-sm-6 col-xs-6">\n' +
            '                        <option>日</option>\n' +
            '                        <option>月</option>\n' +
            '                        <option>年</option>\n' +
            '                    </select>\n' +
            '                    <div class="col-md-2 col-lg-2 col-sm-2 col-xs-2 delectButtom" id="'+data[key].uaccount+'" onclick = queryDelete(this)>\n' +
            '                        <span class="glyphicon glyphicon-trash"></span>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '        </div>')
    }
    console.log(this)
}
GetUser.prototype.show = function () {
    console.log(this.allUser)
    $("#adminText").append(this.allUser)
}


function closeUser(obj) {
    document.getElementById(obj.id).style.display = 'none'
    let id = document.getElementById('@'+obj.id).style.display = 'block'
}
function queryDelete(obj) {
    let reason = prompt('查封理由')
    let email = obj.parentNode.id.match(/@(\S*)/)[1]
    let uaccount = obj.id
    let num = obj.parentNode.children[0].value
    let company = obj.parentNode.children[1].value
    let sealed = num*unit[company]
    if (reason!=''){
        $.ajax({
            url:'http://39.96.68.53:8080/managerController/sealedAccount',
            type:'post',
            data:{
                uaccount:uaccount,
                uemail: email,
                message:reason,
                sealed:sealed
            },
            success:function(res) {
                if (res.code == 200){
                    PromptBox.displayPromptBox('查封成功')
                }else {
                    PromptBox.displayPromptBox(res.msg)
                }
            }
        })
    }
}
var unit = {
    '日':86400,
    '月':2592000,
    '年':31104000
}