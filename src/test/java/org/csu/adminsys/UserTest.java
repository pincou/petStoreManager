package org.csu.adminsys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.adminsys.Mappers.ItemMapper;
import org.csu.adminsys.Mappers.OrderStatusMapper;
import org.csu.adminsys.entity.Item;
import org.csu.adminsys.entity.LineItem;
import org.csu.adminsys.entity.Order;
import org.csu.adminsys.entity.OrderStatus;
import org.csu.adminsys.service.AccountService;
import org.csu.adminsys.service.CatalogService;
import org.csu.adminsys.service.OrderService;
import org.csu.adminsys.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("org.csu.adminsys.Mappers")
public class UserTest {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderStatusMapper orderStatusMapper;
    @Test
    void testOrder(){
        List<Order> allOrders = orderService.findAllOrders();
        System.out.println(allOrders.size());
        for (int i = 0; i < allOrders.size(); i++) {
            System.out.println(allOrders.get(i));
        }
    }
    @Test
    void testOrderDelete(){
        orderService.deleteOrderByOrderid(12312);
        List<Order> allOrders = orderService.findAllOrders();
        for (int i = 0; i < allOrders.size(); i++) {
            System.out.println(allOrders.get(i));
        }
    }
    @Test
    void editOrderStatusTest(){
        orderService.updateStatusByOrderid(1066,"未发货");
        QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
        orderStatusQueryWrapper.eq("orderid",1066);
        OrderStatus orderStatus = orderStatusMapper.selectOne(orderStatusQueryWrapper);
        System.out.println(orderStatus);
    }

    @Test
    void findLineItemsByOrderIdTest(){
        System.out.println(orderService.findLineItemsByOrderId(45645));
    }
}
