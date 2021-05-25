package com.dhy.common.itf;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class FoodDto implements Serializable {

    @NotNull(message = "参数FoodDto.id不能为null")
    private Integer id;
    @NotNull(message = "参数FoodDto.name不能为null")
    @Size(min = 10, max = 20,message = "参数FoodDto.name长度需要在10到20之间")
    private String name;
    private Double price;
    private Date lastUpdateTime;

}
