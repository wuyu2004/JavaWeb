package top.soft.class02request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

//获取get请求参数
@WebServlet("/requestDemo04")
public class RequestDemo04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据参数名获取参数值
        String username = req.getParameter("username");
        System.out.println("username="+username);

        //根据参数名获取参数值的数组
        String[] hobbies = req.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println("hobby="+hobby);
        }

        //获取所以参数的参数名称
        System.out.println("======================");
        System.out.println("获取所有请求的参数名称");
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            System.out.println("参数名"+name);
            String value = req.getParameter(name);
            System.out.println("参数值"+value);
        }

        //获取说有参数的map集合
        System.out.println("======================");
        System.out.println("获取所有请求的map集合");
        Map<String, String[]> parameterMap = req.getParameterMap();
        //遍历获取
        Set<String> keySet = parameterMap.keySet();
        for (String name : keySet) {
            System.out.println("参数名称:"+name);
            String[] values = parameterMap.get(name);
            for (String value : values) {
                System.out.println(value);
            }
        }


    }
}
