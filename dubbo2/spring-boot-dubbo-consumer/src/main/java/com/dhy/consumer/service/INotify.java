package com.dhy.consumer.service;

import com.dhy.common.itf.SeataStoragePo;

import java.util.List;

/**
 * @Title ISeataStorageServiceCallback
 * @Description
 * @Author lvaolin
 * @Date 2021/5/15 22:46
 **/
public interface INotify {
    void oninvoke(SeataStoragePo po);

    void onreturn(List<SeataStoragePo> list,SeataStoragePo po);

    void onthrow(Throwable ex,SeataStoragePo po);
}
