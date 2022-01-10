package com.dhy.common.itf;

import java.util.List;

public interface ISeataStorageService2 {

     List<SeataStoragePo> selectAll(SeataStoragePo po);

     int selectCount();

     int insert(SeataStoragePo po);

     int delete(SeataStoragePo po);

     int update(SeataStoragePo po);
}
