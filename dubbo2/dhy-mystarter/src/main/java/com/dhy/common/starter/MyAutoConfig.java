package com.dhy.common.starter;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//可以通过import导入其它配置类或者普通类
@Import({MyConfig.class})
public class MyAutoConfig {

    //构造此Bean生效的条件
    @Bean
    @ConditionalOnMissingBean
    MyBean myBean(){
        MyBean myBean = new MyBean();
        myBean.setId(1L);
        myBean.setName("bean");
        return myBean;
    }
    //装配属性，从application.yaml读取配置信息
    @ConfigurationProperties("dhy")
    @Bean
    MyDto myDto(){
        MyDto myDto = new MyDto();
        return myDto;
    }

    public class MyDto {
        @Override
        public String toString() {
            return "MyDto{" +
                    "jobname='" + jobname + '\'' +
                    ", scanPath='" + scanPath + '\'' +
                    ", cron='" + cron + '\'' +
                    ", appname='" + appname + '\'' +
                    ", regip='" + regip + '\'' +
                    '}';
        }

        private  String jobname;
        private  String scanPath;
        private  String cron;
        private String appname;
        private String regip;


        public String getJobname() {
            return jobname;
        }

        public void setJobname(String jobname) {
            System.out.println("正在自动装配属性");
            this.jobname = jobname;
        }

        public String getScanPath() {
            return scanPath;
        }

        public void setScanPath(String scanPath) {
            this.scanPath = scanPath;
        }

        public String getCron() {
            return cron;
        }

        public void setCron(String cron) {
            this.cron = cron;
        }

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getRegip() {
            return regip;
        }

        public void setRegip(String regip) {
            this.regip = regip;
        }



    }

    public class MyBean {

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private Long id;
        private String name;

        @Override
        public String toString() {
            return "MyBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
