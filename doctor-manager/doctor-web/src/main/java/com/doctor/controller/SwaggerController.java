package com.doctor.controller;

import com.doctor.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SwaggerController {

    @ApiOperation(value = "SwaggerGet测试",notes = "SwaggerGet测试")
    @RequestMapping(value = "/look" ,method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RedirectServlet's doGet");

        request.setAttribute("name", "xyzmn");
        System.out.println("RedirectServlet's name: " + request.getAttribute("name"));
        //执行请求的重定向, 直接调用 response.sendRedirect(path) 方法,
        //path 为要重定向的地址
        String path = "http://localhost:8080/swagger-ui.html";
        response.sendRedirect(path);
    }




}
