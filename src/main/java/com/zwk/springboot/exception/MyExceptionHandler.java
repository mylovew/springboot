package com.zwk.springboot.exception;

import com.alibaba.fastjson.JSONObject;
import com.zwk.springboot.util.ResultAPI;
import com.zwk.springboot.util.WebUtilsPro;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-05 11:29
 */
@ControllerAdvice
public class MyExceptionHandler {
    /**
     * 登录认证异常
     */
    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public String authenticationException(HttpServletRequest request, HttpServletResponse response) {
        if (WebUtilsPro.isAjaxRequest(request)) {
            // 输出JSON
            writeJson(new ResultAPI(0,"未登录/登录信息失效，请登录"), response);
            return null;
        } else {
            return "redirect:/system/login";
        }
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        if (WebUtilsPro.isAjaxRequest(request)) {
            // 输出JSON
            writeJson(new ResultAPI(0,"权限不足"), response);
            return null;
        } else {
            return "redirect:/error/403";
        }
    }


    //其他异常
    @ExceptionHandler(Exception.class)
    public String hadleServerException(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        exception.printStackTrace();
        if (WebUtilsPro.isAjaxRequest(request)) {
            // 输出JSON
            writeJson(new ResultAPI(0,"服务异常"), response);
            return null;
        } else {
            return "redirect:/error/500";
        }
    }

    /**
     * 输出JSON
     *
     * @param response
     * @author SHANHY
     * @create 2017年4月4日
     */
    private void writeJson(ResultAPI resultAPI, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(JSONObject.toJSONString(resultAPI));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
