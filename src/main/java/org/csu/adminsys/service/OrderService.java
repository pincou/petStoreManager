package org.csu.adminsys.service;

import org.csu.adminsys.entity.Inventory;
import org.csu.adminsys.entity.LineItem;
import org.csu.adminsys.entity.Order;
import org.csu.adminsys.entity.OrderStatus;

import java.util.List;

public interface OrderService {

    public void deleteOrderByStatus(String status);//删除该状态的订单
    public void deleteOrderByDate(String date);//删除该日期的订单
    public void deleteOrderByUserid(String userid);
    public void deleteOrderByOrderid(int orderid);

    public void deleteOrderStatusByOrderId(int orderId);
    public void deleteLineItemByOrderId(int orderId);

    //查
    public List<Order> findAllOrders();
    public Order findOrderByOrderid(int orderid);//根据订单ID查询
    public List<Order> findOrderByStatus(String status);//根据订单状态查询
    public List<Order> findOrderByDate(String date);//根据订单日期查询
    public List<Order> findOrderByUserid(String userid);
    public List<Order> findOrderByItemid(String itemid);//查询购买该Item的订单+

    public List<OrderStatus> findAllOrderStatus();
    public List<LineItem> findLineItemsByOrderId(int orderId);

    //改
    public void updateOrderByOrderid(Order order);

    public void updateStatusByOrderid(int orderid,String orderStatus);//修改发货状态

    public void updateInventoryByItemid(Inventory inventory);//修改库存
}
