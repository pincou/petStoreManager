package org.csu.adminsys.Mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.adminsys.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<Order> {

//    //获取order表单中新增加的订单id
//    default int getOnly() {
//        QueryWrapper<Order> wrapper = new QueryWrapper<>();
//        wrapper.orderByDesc("orderid").last("limit 1");
//        return this.selectOne(wrapper).getOrderId();
//    }

}
