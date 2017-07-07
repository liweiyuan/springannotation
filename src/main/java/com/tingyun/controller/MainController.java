package com.tingyun.controller;

import com.tingyun.annocation.Apple;
import com.tingyun.annocation.RoleControl;
import com.tingyun.service.AppleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tingyun on 2017/6/13.
 */
@Controller
@RoleControl(value = "ADMIN")
public class MainController {


    @Autowired
    private AppleService appleService;

    //@FruitColor(fruitColor = FruitColor.Color.RED)
    //private String fruitColor;
    @RequestMapping("/apple")
    @RoleControl("")
    public String apple() {
        appleService.getClass().getAnnotation(Apple.class);
        return appleService.appleDomain().toString();
    }

    //@RequestMapping("/get")
    //@RoleControl("")
    public void getApple() {
        System.err.println(appleService.getClass().getAnnotation(Apple.class));
        try {
            Method m = AppleService.class.getMethod("getFruitInfo", String.class);
            Object v = m.invoke(appleService, "com.tingyun.domain.AppleDomain");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此方法为spring-security的验证与测试
     */
    @RequestMapping("/page/mypage")
    @RoleControl("")
    public String security(Model model, HttpServletRequest request, HttpServletResponse response){

        //通过登录的用户来判断是否登录
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");
        try {
            request.login(uname,pass);
            System.err.println("success:"+"yse");
        } catch (ServletException e) {
            e.printStackTrace();
            System.err.println("失败:"+"no");
        }


        getApple();
        //具有該权限的用户才能访问该方法
        admin("spark");
        model.addAttribute("message", "Only you are authenticated and authorized to view this page.");
        return "page/mypage";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void admin(String  user){
        //如果具有权限 ROLE_ADMIN 访问该方法
        System.err.println(user);
    }


    /**
     * 此方法为spring-security的验证与测试
     */
    @RequestMapping("/page/user")
    @RoleControl("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String security2(Model model){
        getApple();
        //具有該权限的用户才能访问该方法
        user("spark");
        model.addAttribute("message", "Only you are authenticated and authorized to view this page.");
        return "page/mypage";
    }
    //@PreAuthorize("hasRole('ROLE_USER')")
    public void user(String  user){
        //如果具有权限 ROLE_ADMIN 访问该方法
        System.err.println(user);
    }
}
