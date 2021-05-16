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
@Component("notify")
public class NotifyImpl implements INotify {
    @Override
    public void oninvoke(SeataStoragePo po){
        System.out.println("oninvoke");
    }
    @Override
    public void onreturn(List<SeataStoragePo> list,SeataStoragePo po){
        System.out.println("onreturn");
        list.stream().forEach((storagePo)->{
            System.out.println(storagePo.getId());
        });
    }
    @Override
    public void onthrow(Throwable ex,SeataStoragePo po){
        System.out.println("onthrow:"+ex.getMessage());
    }
}
