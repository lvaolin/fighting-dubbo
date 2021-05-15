package com.dhy.consumer.service;

import com.dhy.common.itf.SeataStoragePo;

import java.util.List;

/**
 * @Title ISeataStorageServiceCallback
 * @Description
 * @Author lvaolin
 * @Date 2021/5/15 22:46
 **/
public interface ISeataStorageServiceCallback {
    void oninvoke();

    void onreturn(List<SeataStoragePo> list);

    void onthrow(Throwable ex);
}
