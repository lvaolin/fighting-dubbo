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
        SeataStoragePo seataStoragePo = new SeataStoragePo();
        seataStoragePo.setLastUpdateTime(new Date());
        seataStoragePos.add(seataStoragePo);
        seataStoragePos.add(new SeataStoragePo());
        seataStoragePos.add(new SeataStoragePo());
        seataStoragePos.add(new SeataStoragePo());
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
