package com.dhy.common.itf;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SeataStoragePo implements Serializable {

    private  Integer id;
    private Double price;
    private Integer stock;
    private Date lastUpdateTime;
    private String desc;

}
