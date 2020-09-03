package com.zzp.scheduled.task.listeners;

import com.zzp.scheduled.task.vo.CommonGoods;

import java.util.List;

/**
 * @Description 公共商品定时子任务基类
 * @Author Garyzeng
 * @since 2020.09.03
 **/
public class CommonGoodsBaseTaskListener extends BaseTaskListener<CommonGoods>{

    @Override
    public void internalSave(List<CommonGoods> list) {

    }

    @Override
    public List<CommonGoods> internalCount() {
        return null;
    }
}
