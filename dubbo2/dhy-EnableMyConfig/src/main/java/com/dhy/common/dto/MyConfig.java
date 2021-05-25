package com.dhy.common.dto;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    @ConditionalOnMissingBean
    MyImportBean myImportBean(){
        MyImportBean myBean = new MyImportBean();
        myBean.setId(2L);
        myBean.setName("importbean");
        return myBean;
    }

    public class MyImportBean {

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
            return "MyImportBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
