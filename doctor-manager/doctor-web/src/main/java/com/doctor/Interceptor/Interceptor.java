package com.doctor.Interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.doctor.pojo.User;
import com.doctor.service.UserService;

public class Interceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 Cookie[] cookies = request.getCookies();
	        if(cookies==null){
	            response.sendRedirect("redirect:login");
	        }
	        HttpSession session = request.getSession(false);
	        String sessionId = session.getId();
	 
	        for(Cookie cookie:cookies){
	            if (cookie.getName().equals("JSESSIONID")) {
	                if(!cookie.getValue().equals(sessionId)){
	                    response.sendRedirect("redirect:login");
	                }
	            }
	        }
	 
	        for (Cookie cookie2:cookies){
	            if(cookie2.getName().equals("username")&&cookie2.getValue()!=null){
	                String cookieUsername = cookie2.getValue();
	                try{
	                    String realPassword = userService.getUserUserLogin(cookieUsername).getPassword();
	                    User user = (User) session.getAttribute("user");
	                    if (user.getPassword().equals(realPassword)){
	                        response.sendRedirect("redirect:welcome");
	                    }else{
	                        response.sendRedirect("redirect:login");
	                    }
	                }catch (NullPointerException e){
	                    response.sendRedirect("redirect:login");
	                }
	 
	            }
	        }
			return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		 System.out.println("进入了postHandle方法！！！！");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
        System.out.println("进入了afterCompletion方法！！！！");

	}

}
