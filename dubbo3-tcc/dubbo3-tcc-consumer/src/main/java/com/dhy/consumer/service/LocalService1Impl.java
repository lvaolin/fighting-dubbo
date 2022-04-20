package com.dhy.consumer.service;

import org.springframework.stereotype.Component;
@Component
public class LocalService1Impl implements ILocalService1 {

    @Override
    public boolean localMethod1() {
        System.out.println("localMethod1");
        return false;
    }

    @Override
    public boolean localMethod2() {
        System.out.println("localMethod2");
        return false;
    }
}
