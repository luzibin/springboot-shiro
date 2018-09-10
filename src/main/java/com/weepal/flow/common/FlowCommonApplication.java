package com.weepal.flow.common;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * <p>
 * <b>FlowCommonApplication</b> is 启动项
 * </p>
 *
 * @author Bin
 * @version $Id: flow-common 62440 2018-09-07 09:03:15Z Bin $
 * @since 2018年09月07日
 **/
@SpringBootApplication
@MapperScan("com.weepal.flow.common.dao")
public class FlowCommonApplication {
    private static Logger logger = LoggerFactory.getLogger(FlowCommonApplication.class);

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(FlowCommonApplication.class,args);//配置环境
        //可选项
//        SpringContextHolder.setApplicationContext(applicationContext);  //引用自定义springContext

        Environment env = applicationContext.getEnvironment();//当前环境
        String protocol = "http";
        if(env.getProperty("server.ssl.key-store") != null){
            protocol = "https";
        }

        //访问路径
        String contextPath = env.getProperty("server.context-path");
        if(contextPath == null){
            contextPath = "";
        }
        try{
            //拼接项目的访问路径，并打印出来
            logger.info
                    ("\n----------------------------------------------------------\n\t" +
                                    "Application '{}' is running! Access URLs:\n\t" +
                                    "Local: \t\t{}://localhost:{}\n\t" +
                                    "External: \t{}://{}:{}\n\t" +
                                    "接口文档地址: \t\t{}://localhost:{}\n\t" +
                                    "Profile(s): \t{}\n----------------------------------------------------------",
                            env.getProperty("application.name "),
                            protocol,
                            env.getProperty("server.port")+contextPath,
                            protocol,
                            InetAddress.getLocalHost().getHostAddress(),
                            env.getProperty("server.port")+contextPath,
                            protocol,env.getProperty("server.port")+contextPath+"/swagger-ui.html",
                            env.getActiveProfiles()
                    );

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
