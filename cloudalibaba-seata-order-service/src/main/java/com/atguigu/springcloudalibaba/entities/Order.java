package com.atguigu.springcloudalibaba.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 19:05
 */
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Order {

    private long id;
    @NotNull
    private long userId;
    @NotNull
    private long productId;
    @NotNull
    private Integer count;
    @NotNull
    private BigDecimal money;

    private Integer status;

    public Order() {
        this.status = 0;
    }
}
