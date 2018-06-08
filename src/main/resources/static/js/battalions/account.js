/**
 * Created by karma on 2018/5/18.
 */

function succLogin(result){
    if(result.status){
        ////保存token 到本地存储且长期保存。
        //var token = result.data.token;
        //localStorage.setItem('token', token);
        //跳转到首页
        window.location.href = result.toUrl;
    }else{
        alertMessage(result.msg);
    }
}

function logout(){
    $.cookie("token", '',  { expires: -1 ,path:'/'});
    alert('注销成功');
}

function loginStatus(){
    if(!$.cookie('token')){
        //未登录
        return false;
    }

    return true;
}