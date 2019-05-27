function createGroup() {
    $.ajax({
        type:"GET",                      //请求类型
        url:_MTONS.BASE_PATH+"/live/home",           //URL
        dataType:"json",                 //返回的数据类型
        success:function(data){          //data就是返回的json类型的数据
            var str_json= JSON.stringify(data)
            alert(str_json);
            if(data.mess == "true"){
                alert("删除成功");
                obj.parents("tr").remove();
            }else if(data.mess == "false"){
                alert("对不起, "+obj.attr("username")+" 用户删除失败");
            }else if(data.mess == "noex"){
                alert("对不起,用户 "+obj.attr("username")+" 不存在");
            }
        },
        error:function(data){
            var str_json= JSON.stringify(data)
            alert("删除失败" + str_json);
        }
    });
}
