package com.dhy.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dhy.common.itf.IOrderService;
import com.dhy.common.itf.ISeataStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Reference
    private ISeataStorageService storageService;
    @RequestMapping("/selectBigData")
    Object selectBigData() {
        return storageService.selectBigData(null);
    }



}
