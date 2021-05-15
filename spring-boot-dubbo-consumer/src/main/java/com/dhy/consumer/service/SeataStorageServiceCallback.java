package com.dhy.consumer.service;

import com.dhy.common.itf.SeataStoragePo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title SeataStorageServiceCallback
 * @Description
 * @Author lvaolin
 * @Date 2021/5/15 17:55
 **/
@Component("seataStorageServiceCallback")
public class SeataStorageServiceCallback implements ISeataStorageServiceCallback{
    @Override
    public void oninvoke(){
        System.out.println("oninvoke");
    }
    @Override
    public void onreturn(List<SeataStoragePo> list){
        System.out.println("onreturn");
        list.stream().forEach((storagePo)->{
            System.out.println(storagePo.getId());
        });
    }
    @Override
    public void onthrow(Throwable ex){
        System.out.println("onthrow:"+ex.getMessage());
    }
}
