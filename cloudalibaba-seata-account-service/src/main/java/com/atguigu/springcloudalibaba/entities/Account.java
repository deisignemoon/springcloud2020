package com.atguigu.springcloudalibaba.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 20:59
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private long id;
    private long userId;
    private BigDecimal total;
    private BigDecimal used;
    private BigDecimal residue;
}
