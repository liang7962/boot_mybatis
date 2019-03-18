package com.mybatis.springSources.servlet;


import com.mybatis.springSources.controller.UserController;
import com.mybatis.springSources.myInterface.MyAutowired;
import com.mybatis.springSources.myInterface.MyController;
import com.mybatis.springSources.myInterface.MyRequestParam;
import com.mybatis.springSources.myInterface.MyService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/18.
 */
@Slf4j
public class DispatcherServlet extends HttpServlet {

    List<String> classNames=new ArrayList<String>();
    Map<String,Object> beans=new HashMap<String,Object>();
    Map<String,Object> handlerMap=new HashMap<String,Object>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        this.doPost(req,resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //扫码所有的bean，
        scanPackage("com.mybatis.springSources");
        doInstace();//创建beans
        
        doIoc();//beans依赖注入

        buildUrlMapping();

//        super.init(config);
    }

    private void buildUrlMapping() {
        if (beans.entrySet().size()<=0){
            log("没有可以实例化的类");
            return;
        }
        for (Map.Entry<String,Object> entry:beans.entrySet()){
            Object instace = entry.getValue();
            Class<?> clazz = instace.getClass();
            if (clazz.isAnnotationPresent(MyController.class)){
                MyRequestParam myRequestParam = clazz.getAnnotation(MyRequestParam.class);
                String classPath = myRequestParam.value();//
                Method[] methods = clazz.getMethods();
                for (Method method:methods){
                    if (method.isAnnotationPresent(MyRequestParam.class)){
                        MyRequestParam annotation = method.getAnnotation(MyRequestParam.class);
                        String methodPath = annotation.value();
                        handlerMap.put(classPath+methodPath,method);
                    }else {
                        continue;
                    }
                }

            }
        }
    }

    private void doIoc() {
        if (beans.size()<=0){
            log("没有一个实例化类");
        }else {
            for (Map.Entry<String,Object> entry:beans.entrySet()){
                Object instace = entry.getValue();
                Class<?> clazz = instace.getClass();
                if (clazz.isAnnotationPresent(MyController.class)){
                    Field[] declaredFields = clazz.getDeclaredFields();//获取类中的所有方法
                    for (Field field:declaredFields){
                        if (field.isAnnotationPresent(MyAutowired.class)){
                            MyAutowired auto = field.getAnnotation(MyAutowired.class);
                            String key = auto.value();
                            field.setAccessible(true);//如果遇到private可以注入，不加无法注入
                            try {
                                field.set(instace,beans.get(key));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }else {
                            continue;
                        }
                    }
                }else {
                    continue;
                }

            }
        }

    }

    /**
    * @Param:
    * @return:  根据list全类名，进行实例化
    * @Author: liang
    * @Date: 2019/3/18
    */
    private void doInstace() {
        if(classNames.size()<=0){
            log("没有需要初始化的bean");
            return;
        }else {
            for (String className:classNames){
                String replace = className.replace(".class", "");
                try {
                    Class<?> clazz = Class.forName(replace);
                    if (clazz.isAnnotationPresent(MyController.class)){
                        Object instance = clazz.newInstance();//创建控制类
                        MyRequestParam myRequestParam=clazz.getAnnotation(MyRequestParam.class);
                        String value = myRequestParam.value();
                        beans.put(value,instance);
                    }else  if (clazz.isAnnotationPresent(MyService.class)){
                        Object instance = clazz.newInstance();//创建控制类
                        MyService myService=clazz.getAnnotation(MyService.class);
                        beans.put(myService.value(),instance);
                    }else {
                        continue;
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void scanPackage(String basePackage) {
        URL url=this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.","/"));
        String fileStr=url.getFile();
        File file=new File(fileStr);
        String[] filesStr = file.list();
        for (String path:filesStr){
            File filePath=new File(fileStr+path);
            if (filePath.isDirectory()){
                scanPackage(fileStr+"."+path);
            }else {
                classNames.add(basePackage+"."+filePath.getName());
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String uri = req.getRequestURI();
        String context = req.getContextPath();
        String path = uri.replace(context, "");
        Method method= (Method) handlerMap.get(path);
        //根据key="/ty" 去beans中匹配拿到beans
        UserController instace = (UserController) beans.get("/" + path.split("/")[1]);
        Object[] args = hand(req, resp, method);
        try {
            method.invoke(instace,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
    * @Param:
    * @return:  非策略模式
    * @Author: liang
    * @Date: 2019/3/18
    */
    private static Object[] hand(HttpServletRequest request,HttpServletResponse response,Method method){
        //拿到执行方法有哪些参数
        Class<?>[] paramClazzs= method.getParameterTypes();

        //根据参数的个数，new一个参数的数组，将方法里的参数赋值到args来
        Object[] args=new Object[paramClazzs.length];
        int args_i=0;
        int index=0;
        for (Class<?> paramClazz:paramClazzs){
            if (ServletRequest.class.isAssignableFrom(paramClazz)){
                args[args_i++]=request;
            }if (ServletResponse.class.isAssignableFrom(paramClazz)){
                args[args_i++]=response;
            }
            Annotation[] paramAns=method.getParameterAnnotations()[index];
            if (paramAns.length>0){
                for (Annotation paramAn:paramAns){
                    if (MyRequestParam.class.isAssignableFrom(paramClazz.getClass())){
                        MyRequestParam rp= (MyRequestParam) paramAn;
                        //找到注解里面的name和age
                        args[args_i++]=request.getParameter(rp.value());
                    }
                }
            }
            index++;
        }
        return args;
    }
}
