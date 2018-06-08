/**
 * Created by karma on 2018/5/16.
 */

function useUpload(){
    layui.use('upload', function() {
        var $ = layui.jquery
            , upload = layui.upload;

        //普通图片上传
        upload.render({
            elem: '#indexImgUpload'
            , url: '/image/admin/upload/index'
            , multiple:true
            , done: function (res) {
                window.location.reload();
                //refreshImageHtml();
                //alertMessage(res.msg);
            }
            , error: function () {
                alertMessage("上传失败");
            }
        });
    });
}

function deleImg(obj){
    var id = obj.id;
    var url = '/image/admin/delete/' + id;
    ajaxGet(url, deleSucc, errorCallBack);
    //后台返回html

    //后台返回数据，前台拼接html。
    //imageManage();
}

function deleSucc(res){
    console.log(res);
    if(!res.status){
        alertMessage(res.msg);
    }
        //refreshImageHtml();
    if(res.status){
        window.location.reload();
    }
}
