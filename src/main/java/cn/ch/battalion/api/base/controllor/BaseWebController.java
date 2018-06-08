package cn.ch.battalion.api.base.controllor;

import cn.ch.battalion.auth.JWT;
import cn.ch.battalion.auth.Login;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import jdk.nashorn.internal.parser.JSONParser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by karma on 2018/4/10.
 */
public class BaseWebController extends BaseController {

    public Login getLogin(HttpServletRequest request) {
//        TODO
//        Login login = new Login();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return JWT.parseToken(cookie.getValue(),Login.class);
                }
            }
        }
//        login.setUserId(1L);
//        Login login = (Login) request.getAttribute("login");
        return null;
    }
}
