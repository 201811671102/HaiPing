$.ajax({
    url:"http://39.96.68.53:8080/managerController/NoCertificationEnterprise",
    type:'get',
    data:{
        start:0,
        size:10
    },
    success:function(res){
        console.log(res)
        for (let key in res.data){
            $("#studentText").append('<div class="container-fluid content">\n' +
                '            <div class="col-md-6 col-lg-6 col-sm-6 col-xs-6">\n' +
                '                <div class="container-fluid">\n' +
                '                    <div class="img">\n' +
                '                        <img src="'+res.data[key].uphoto+'">\n' +
                '                    </div>\n' +
                '                    <div class="etpsName">\n' +
                '                        '+res.data[key].uaccount+'\n' +
                '                    </div>\n' +
                '                    <div class="identifyInfo">\n' +
                '                        <div>电话：'+res.data[key].uphone+'</div>\n' +
                '                        <div>邮箱：'+res.data[key].uemail+'</div>\n' +
                '                    </div>\n' +
                '\n' +
                '                </div>\n' +
                '            </div>\n' +
                '            <div class="col-md-4 col-lg-4 col-sm-4 col-xs-4 etpsInfo">\n' +
                '                <div>企业名称：'+res.data[key].roleObject.ename+'</div>\n' +
                '                <div>企业简介：'+res.data[key].roleObject.eintroduction+'</div>\n' +
                '                <div>企业地址：'+res.data[key].roleObject.eaddress+'</div>\n' +
                '                <div>企业网页：'+res.data[key].roleObject.eurl+'</div>\n' +
                '            </div>\n' +
                '            <div class="col-md-2 col-lg-2 col-sm-2 col-xs-2">\n' +
                '                <div class="Lable">企业</div>\n' +
                '                <div class="select" >\n' +
                '                    <select id="'+res.data[key].roleObject.eid+'" onchange= selectAuth(this)>\n' +
                '                        <option>待审批</option>\n' +
                '                        <option>通过</option>\n' +
                '                        <option>拒绝</option>\n' +
                '                    </select>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>')
        }
    }
})
function selectAuth(obj) {
    if (obj.value == '通过'){
        $.ajax({
            url:'http://39.96.68.53:8080/managerController/CertificationEnterprise',
            type:'post',
            data: {
                eid:obj.id
            },
            success:function(res) {
                console.log(res)
                PromptBox.displayPromptBox('审核成功')
            }
        })
    }
}