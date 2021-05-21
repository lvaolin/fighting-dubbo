package com.dhy.common.itf;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class FoodDto implements Serializable {

    @NotNull
    private Integer id;
    @NotNull
    @Size(min = 10, max = 11)
    private String name;
    private Double price;
    private Date lastUpdateTime;

}
