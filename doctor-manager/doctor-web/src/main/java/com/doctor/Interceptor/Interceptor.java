package com.doctor.Interceptor;
/**
 * 自动登录拦截器
 * fmq
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doctor.Iservice.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class Interceptor implements HandlerInterceptor {
	@Autowired
	private IUserService userService;

	public final Logger logger = LoggerFactory.getLogger(Interceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("进入拦截器-----------ok");
		//获取用户本地cookies
		 Cookie[] cookies = request.getCookies();
	        if(cookies==null){
	        	//本地cookies为空直接跳转登陆页面
	            response.sendRedirect("/loginPage");
				return true;
	        }
			//获取 session
	        HttpSession session = request.getSession(false);
			if(session!=null){
				String sessionId = session.getId();
				for(Cookie cookie:cookies){
					if (cookie.getName().equals("JSESSIONID")) {
						if(!cookie.getValue().equals(sessionId)){
							logger.info("跳转登陆页面");
							response.sendRedirect("/loginPage");
							return true;
						}
					}
				}
				//判断cookies知否有用户信息，false直接返回登陆
				for (Cookie cookie2:cookies)
					if (cookie2.getName().equals("userLogin") && cookie2.getValue() != null) {
						if (cookie2.getValue().equals(session.getAttribute("userLogin"))) {
							String realPassword = getRealPassword(cookie2.getValue());
							if (realPassword!=null&&!realPassword.equals("")){
								String password = (String)session.getAttribute("password");
								if (password.equals(realPassword)){
									response.sendRedirect("/index");
									logger.info("进入拦截器-----------登陆");
									return true;
								}
							}else{
								response.sendRedirect("/loginPage");
								return true;
							}
						}
					}
			}else{
				//session为空直接返回登陆页面
				response.sendRedirect("/loginPage");
				return true;
			}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("进入了postHandle方法！！！！");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
        logger.info("进入了afterCompletion方法！！！！");
	}

	public String  getRealPassword(String userLogin){
		String realPassword = null;
		if(userService.getUserUserLogin(userLogin).get(0)!=null){

			return realPassword = userService.getUserUserLogin(userLogin).get(0).getPassword();
		}
		return realPassword;
	}

}
