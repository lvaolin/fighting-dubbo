package com.dhy.common.itf;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderPo implements Serializable {

    private  Integer id;
    private Double price;
    private Integer stock;
    private Date lastUpdateTime;

}
