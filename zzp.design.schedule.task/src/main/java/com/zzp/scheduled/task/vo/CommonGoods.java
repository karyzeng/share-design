package com.zzp.scheduled.task.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description 公共商品
 * @Author karyzeng
 * @since 2020.09.03
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommonGoods implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品数量
     */
    private Integer size;

}
