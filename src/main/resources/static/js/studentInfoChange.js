var user = JSON.parse($.cookie('user'))

document.getElementById('sname').value = user.roleObject.sname
document.getElementById('sworkexperience').value = user.roleObject.sworkexperience
document.getElementById('swechar').value = user.roleObject.swechar
document.getElementById('scollege').value = user.roleObject.scollege
document.getElementById('sstudentnumber').value = user.roleObject.sstudentnumber
document.getElementById('sgrade').value = user.roleObject.sgrade
document.getElementById('scollegeaddress').value = user.roleObject.scollegeaddress

var ipt = document.getElementsByTagName('input')


$("#register").click(function iptNull() {
    for (let key in ipt){
        if (ipt[key].value == ''){
            return PromptBox.displayPromptBox('请完善信息。')
        }
    }
    let ssex = $("#gentle").val()
    let seducation = $("#Education").val()
    let cert = new FormData()
    cert.append('sid',user.roleObject.sid)
    cert.append('scollege',$("#scollege").val())
    cert.append('sstudentnumber',$("#sstudentnumber").val())
    cert.append('sgrade',$("#sgrade").val())
    cert.append('scollegeaddress',$("#scollegeaddress").val())
    cert.append('sname',$("#sname").val())
    cert.append('ssex',ssex)
    cert.append('seducation',seducation)
    cert.append('swechar',$("#swechar").val())
    cert.append('sworkexperience',$("#sworkexperience").val())
    cert.append('_method','put')
    $.ajax({
        url:'http://39.96.68.53:8080/student/studentChangeInfo',
        type:'post',
        dataType:'json',
        contentType:false,
        processData:false,
        data:cert,
        success:function(res) {
            PromptBox.displayPromptBox(res.msg)
            setTimeout(function () {
                $(location).attr('href','../html/index.html')
            },2000)

        }
    })
})

