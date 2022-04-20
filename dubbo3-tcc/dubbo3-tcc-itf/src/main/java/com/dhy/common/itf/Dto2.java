package com.dhy.common.itf;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Dto2 implements Serializable {
    private Integer id;
    private String name;
    private Double price;
    private Date lastUpdateTime;
}
