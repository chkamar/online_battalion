package cn.ch.battalion.auth;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by karma on 2018/6/1.
 */
@Component
public class CookieUtils {

    public Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (StringUtils.isEmpty(name) || cookies == null) {
            return null;
        }
        if (cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public void setCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
//        cookie.setMaxAge(3600);
        cookie.setDomain("127.0.0.1");
        cookie.setPath("/");
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

}
