package com.dhy.common.itf;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class Dto1 implements Serializable {
    private Integer id;
    private String name;
    private Double price;
    private Date lastUpdateTime;
}
