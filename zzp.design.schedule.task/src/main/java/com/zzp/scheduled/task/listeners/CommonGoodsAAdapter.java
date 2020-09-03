package com.zzp.scheduled.task.listeners;

import com.zzp.scheduled.task.vo.CommonGoods;

import java.util.List;

/**
 * @Description 公共商品A类适配器
 * @Author karyzeng
 * @since 2020.09.03
 **/
public class CommonGoodsAAdapter extends BaseTaskListener<CommonGoods>{

    @Override
    public void internalSave(List<CommonGoods> list) {

    }

    @Override
    public List<CommonGoods> internalCount() {
        return null;
    }
}
