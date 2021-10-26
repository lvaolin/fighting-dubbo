package com.dhy.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dhy.common.itf.ISeataStorageService;
import com.dhy.common.itf.SeataStoragePo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Component
public class SeataStorageServiceImpl implements ISeataStorageService {

    @Override
    public List<SeataStoragePo> selectAll(SeataStoragePo po) {
        try {
            this.selectCount();
            this.insert(null);
            this.delete(null);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<SeataStoragePo> seataStoragePos = new ArrayList<>();
        long i=100000;
        while (i-->0){
            SeataStoragePo seataStoragePo = new SeataStoragePo();
            seataStoragePo.setId(1000000000);
            seataStoragePo.setLastUpdateTime(new Date());
            seataStoragePo.setDesc("模拟大数据量传输，payload过载异常");
            seataStoragePos.add(seataStoragePo);
        }

        return seataStoragePos;
    }

    @Override
    public List<SeataStoragePo> selectBigData(SeataStoragePo po) {
        ArrayList<SeataStoragePo> seataStoragePos = new ArrayList<>();
        long i=100000;
        while (i-->0){
            SeataStoragePo seataStoragePo = new SeataStoragePo();
            seataStoragePo.setId(1000000000);
            seataStoragePo.setLastUpdateTime(new Date());
            seataStoragePo.setDesc("模拟大数据量传输，payload过载异常");
            seataStoragePos.add(seataStoragePo);
        }

        return seataStoragePos;
    }

    @Override
    public int selectCount() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(SeataStoragePo po) {
        return 0;
    }

    @Override
    public int delete(SeataStoragePo po) {
        return 0;
    }

    @Override
    public int update(SeataStoragePo po) {
        return 0;
    }
}
