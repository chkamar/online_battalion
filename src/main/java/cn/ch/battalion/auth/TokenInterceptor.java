package cn.ch.battalion.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by karma on 2018/4/6.
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CookieUtils cookieUtils;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        String token = httpServletRequest.getHeader("Authorization");
//        if (token != null) {
//            if (token.length() > 7 && token.startsWith("bearer ")) {
//                token = token.substring(7);
//                Login login = JWT.parseToken(token, Login.class);
//                if (login != null) {
//                    httpServletRequest.setAttribute("login", login);
//                    logger.info("认证成功");
//                    //判断是否需要更新token
//                    String refreshToken = JWT.updateToken(token);
//                    if (refreshToken != null) {
//                        logger.info("更新token");
//                        httpServletResponse.setHeader("Authorization", "bearer " + refreshToken);
//                    }
//                    return true;
//                }
//            }
//        }
        String requestURI = httpServletRequest.getRequestURI();
        if (requestURI.contains("login") || requestURI.contains(".")) {
            return true;
        }
        Cookie cookie = cookieUtils.getCookie(httpServletRequest, "token");
        //-1表示未登录，0表示用户，1表示管理员
        int status = -1;
        if (cookie != null) {
            String token = cookie.getValue();
            Login login = JWT.parseToken(token, Login.class);

            if (login != null) {
                //判断是否需要更新token
                String refreshToken = JWT.updateToken(token);
                if (refreshToken != null) {
                    logger.info("更新token");
                    cookieUtils.setCookie(httpServletResponse, "token", token);
                }
                //后台管理请求地址需登录且为管理员类型
                String loginType = login.getType();
                if ("user".equals(loginType)) {
                    status = 0;
                }
                if ("admin".equals(loginType)) {
                    status = 1;
                }

            }
        }
        //根据URI拦截
        if (requestURI.contains("admin") && status != 1) {
            logger.info("无权限操作");
            httpServletResponse.setContentType("text/html; charset=UTF-8");
            //注意text/html，和application/html<br>　　　　
            httpServletResponse.getWriter().print("<html><body><script type='text/javascript'>alert('无访问权限！');window.location.href = '/html/admin/login.html';</script></body></html>");
            httpServletResponse.getWriter().close();
            return false;
        }
        if (requestURI.contains("user") && status != 0) {
            logger.info("未登录！");
            httpServletResponse.setContentType("text/html; charset=UTF-8");
            //注意text/html，和application/html<br>　　　　
            httpServletResponse.getWriter().print("<html><body><script type='text/javascript'>alert('未登录！');window.location.href = '/html/login.html';</script></body></html>");
            httpServletResponse.getWriter().close();
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
