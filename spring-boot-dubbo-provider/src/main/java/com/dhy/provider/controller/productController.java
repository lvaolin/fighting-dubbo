package com.dhy.provider.controller;

import com.dhy.common.itf.ISeataStorageService;
import com.dhy.common.itf.SeataStoragePo;
import com.dhy.common.utils.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class productController {

    @Autowired
    private ISeataStorageService seataStorageService;

    @RequestMapping("/selectAll")
    Object getProduct() {
        return seataStorageService.selectAll();
    }

    @RequestMapping("/insert")
    Object insertProduct(SeataStoragePo po) {
        if (po == null || po.getStock() == null || po.getPrice() == null) {
            throw new BusinessException("", "参数不完整");
        }
        return seataStorageService.insert(po);
    }

    @RequestMapping("/delete")
    Object deleteProduct(SeataStoragePo po) {
        if (po == null || po.getId() == null) {
            throw new BusinessException("", "参数不完整");
        }
        return seataStorageService.delete(po);
    }


    @RequestMapping("/update")
    Object updateProduct(SeataStoragePo po) {
        if (po == null || po.getId() == null) {
            throw new BusinessException("", "参数不完整");
        }
        return seataStorageService.update(po);
    }

    @RequestMapping("/count")
    Object countProduct() {
        return seataStorageService.selectCount();
    }


}
