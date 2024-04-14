package org.csu.adminsys.Controller;

import jakarta.servlet.http.HttpSession;
import org.csu.adminsys.entity.Order;
import org.csu.adminsys.entity.OrderStatus;
import org.csu.adminsys.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderManagerController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/orderManage")
    public String orderManage(HttpSession session){
        //首页显示所有订单数据
        List<Order> orderList = orderService.findAllOrders();
        List<OrderStatus> orderStatusList = orderService.findAllOrderStatus();
        List<Order> newOrderList = new ArrayList<>();
        //放入OrderStatus信息
        for (Order order : orderList) {
            for (OrderStatus orderStatus : orderStatusList) {
                if (order.getOrderId() == orderStatus.getOrderid()) {
                    order.addOrderStatus(orderStatus);
                }
            }
            if(order.getOrderStatus().getStatus()!=null)
                newOrderList.add(order);
        }
        session.setAttribute("orderList",newOrderList);
        return "orderManage";
    }


}
