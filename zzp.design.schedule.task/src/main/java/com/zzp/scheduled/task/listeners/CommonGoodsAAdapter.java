package com.zzp.scheduled.task.listeners;

import com.zzp.scheduled.task.vo.CommonGoods;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 公共商品A类适配器
 * @Author karyzeng
 * @since 2020.09.03
 **/
@Component(value = "commonGoodsAAdapter")
public class CommonGoodsAAdapter extends BaseTaskListener<CommonGoods>{

    @Override
    public void save(List<CommonGoods> list) {
        System.out.println("调用保存公共商品方法");
    }

    @Override
    public List<CommonGoods> count() {
        System.out.println("调用汇总公共商品A方法");
        return null;
    }
}
