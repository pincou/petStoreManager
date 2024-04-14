package org.csu.adminsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.adminsys.Mappers.*;
import org.csu.adminsys.entity.*;
import org.csu.adminsys.service.OrderService;
import org.csu.adminsys.Mappers.OrderMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private LineItemMapper lineItemMapper;

    @Override
    public void deleteOrderByStatus(String status) {
        QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
        orderStatusQueryWrapper.eq("status",status);
        List<OrderStatus> orderStatuses = orderStatusMapper.selectList(orderStatusQueryWrapper);
        //删除orders表中对应的订单
        for(int i = 0;i<orderStatuses.size();i++){
            QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
            orderQueryWrapper.eq("orderid",orderStatuses.get(i).getOrderid());
            orderMapper.delete(orderQueryWrapper);
        }
        //删除orderstatus表中的订单
        orderStatusMapper.delete(orderStatusQueryWrapper);
    }
    @Override
    public void deleteOrderByDate(String date) {
        QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
        orderStatusQueryWrapper.eq("timestamp",date);
        List<OrderStatus> orderStatuses = orderStatusMapper.selectList(orderStatusQueryWrapper);

        for(int i = 0;i<orderStatuses.size();i++){
            QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
            orderQueryWrapper.eq("orderid",orderStatuses.get(i).getOrderid());
            orderMapper.delete(orderQueryWrapper);
        }

        orderStatusMapper.delete(orderStatusQueryWrapper);
    }

    @Override
    public void deleteOrderByUserid(String userid) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("userid",userid);
        List<Order> orders = orderMapper.selectList(orderQueryWrapper);
        //删除orderstatus表中对应的订单
        for(int i = 0;i<orders.size();i++){
            QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
            orderStatusQueryWrapper.eq("orderid",orders.get(i).getOrderId());
            orderStatusMapper.delete(orderStatusQueryWrapper);
        }
    }

    @Override
    public void deleteOrderByOrderid(int orderid) {
        QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderStatusQueryWrapper.eq("orderid",orderid);
        orderQueryWrapper.eq("orderid",orderid);
        orderStatusMapper.delete(orderStatusQueryWrapper);
        orderMapper.delete(orderQueryWrapper);
    }

    @Override
    public void deleteOrderStatusByOrderId(int orderId) {
        orderStatusMapper.deleteById(orderId);
    }

    @Override
    public void deleteLineItemByOrderId(int orderId) {
        lineItemMapper.deleteById(orderId);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderMapper.selectList(null);
    }

    @Override
    public Order findOrderByOrderid(int orderid) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("orderid",orderid);
        return orderMapper.selectOne(orderQueryWrapper);
    }

    @Override
    public List<Order> findOrderByStatus(String status) {
        List<Order> orders = new ArrayList<>();
        QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
        orderStatusQueryWrapper.eq("status",status);
        List<OrderStatus> orderStatuses = orderStatusMapper.selectList(orderStatusQueryWrapper);

        for(int i = 0;i<orderStatuses.size();i++){
            QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
            orderQueryWrapper.eq("orderid",orderStatuses.get(i).getOrderid());
            orders.add(orderMapper.selectOne(orderQueryWrapper));
        }
        return orders;
    }

    @Override
    public List<Order> findOrderByDate(String date) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("orderdate",date);
        return orderMapper.selectList(orderQueryWrapper);
    }

    @Override
    public List<Order> findOrderByUserid(String userid) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("userid",userid);
        return orderMapper.selectList(orderQueryWrapper);
    }

    @Override
    public List<Order> findOrderByItemid(String itemid) {
        List<Order> list = new ArrayList<>();

        QueryWrapper<LineItem> lineItemQueryWrapper = new QueryWrapper<>();
        lineItemQueryWrapper.eq("itemid",itemid);
        List<LineItem> lineItems = lineItemMapper.selectList(lineItemQueryWrapper);
        for(int i = 0;i<lineItems.size();i++){
            QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
            orderQueryWrapper.eq("orderid",lineItems.get(i).getOrderId());
            list.add(orderMapper.selectOne(orderQueryWrapper));
        }
        return list;
    }

    @Override
    public List<OrderStatus> findAllOrderStatus() {
            return orderStatusMapper.selectList(null);
    }

    @Override
    public List<LineItem> findLineItemsByOrderId(int orderId) {
        QueryWrapper<LineItem> lineItemQueryWrapper = new QueryWrapper<>();
        lineItemQueryWrapper.eq("orderid", orderId);
        List<LineItem> lineItemList = lineItemMapper.selectList(lineItemQueryWrapper);
        for (LineItem lineItem : lineItemList) {
            String itemId = lineItem.getItemId();
            QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
            itemQueryWrapper.eq("itemid", itemId);
            Item item = itemMapper.selectOne(itemQueryWrapper);
            String productId = item.getProductId();
            QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
            productQueryWrapper.eq("productid", productId);
            Product product = productMapper.selectOne(productQueryWrapper);
            String categoryId = product.getCategoryId();
            lineItem.setProductId(productId);
            lineItem.setCategoryId(categoryId);
        }
        return lineItemList;
    }

    @Override
    public void updateOrderByOrderid(Order order)  {
        orderMapper.updateById(order);
    }
//修改订单状态
    @Override
    public void updateStatusByOrderid(int orderid, String status) {
        QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
        orderStatusQueryWrapper.eq("orderid",orderid);
        OrderStatus orderStatus = orderStatusMapper.selectOne(orderStatusQueryWrapper);
        orderStatus.setStatus(status);
        orderStatusMapper.updateById(orderStatus);
    }
    //修改库存
    @Override
    public void updateInventoryByItemid(Inventory inventory) {

        inventoryMapper.updateById(inventory);
    }
}
