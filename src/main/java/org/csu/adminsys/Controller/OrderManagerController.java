package org.csu.adminsys.Controller;

import jakarta.servlet.http.HttpSession;
import org.csu.adminsys.entity.LineItem;
import org.csu.adminsys.entity.Order;
import org.csu.adminsys.entity.OrderStatus;
import org.csu.adminsys.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderManagerController {
    @Autowired
    private OrderService orderService;

    /**
     *订单列表展示
     * @author zhao
     * @return 订单列表
     */
    @GetMapping("/orderManage")//展示商品信息
    public String orderManage(HttpSession session){
        List<Order> orderList = orderService.findAllOrders();
        List<OrderStatus> orderStatusList = orderService.findAllOrderStatus();
        List<Order> newOrderList = new ArrayList<>();
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
    /**
     *订单删除
     * @author zhao
     * @param orderid
     * @return 重定向至订单列表
     */
    @PostMapping("/deleteOrder")
    public String deleteOrderByOrderid(HttpSession session,@RequestParam("orderid") int orderid){
        orderService.deleteOrderByOrderid(orderid);
        orderService.deleteOrderStatusByOrderId(orderid);
        orderService.deleteLineItemByOrderId(orderid);

        List<Order> orderList=orderService.findAllOrders();
        List<OrderStatus> orderStatusList = orderService.findAllOrderStatus();
        List<Order> newOrderList = new ArrayList<>();
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
        return "redirect:/orderManage";
    }

    /**
     *订单详情展示
     * @author zhao
     * @param orderid
     * @return order中的LineItem
     */
    @RequestMapping("/orderInfo")//获取订单详情
    @ResponseBody
    public List<LineItem> orderInfo(@RequestParam("orderid") int orderid){
        return  orderService.findLineItemsByOrderId(orderid);
    }
    /**
     *修改订单状态
     * @author zhao
     * @param orderId
     * @param orderStatus
     * @return 重定向至orders展示页
     */
    @GetMapping("/editOrderStatus")
    public String editOrderStatus(@RequestParam("orderId") int orderId,@RequestParam("orderStatus") String orderStatus){
        System.out.println(orderId+orderStatus);
        orderService.updateStatusByOrderid(orderId,orderStatus);
        System.out.println("订单"+orderId+" 状态修改为："+orderStatus);
        return "redirect:/orderManage";
    }
}
