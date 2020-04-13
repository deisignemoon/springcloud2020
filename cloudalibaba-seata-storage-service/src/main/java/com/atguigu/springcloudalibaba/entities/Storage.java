package com.atguigu.springcloudalibaba.entities;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 20:14
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    private long id;
    @NotNull
    private long productId;
    private Integer total;
    private Integer userd;
    private Integer residue;
}
