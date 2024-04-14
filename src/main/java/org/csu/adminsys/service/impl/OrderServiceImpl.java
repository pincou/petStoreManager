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

    /**
     * 删除对应status的所有订单
     * @author Zhao
     * @param status
     */
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
    /**
     * 删除对应日期的所有订单
     * @author Zhao
     * @param date
     */
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
    /**
     * 删除对应userid的所有订单
     * @author Zhao
     * @param userid
     */
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
    /**
     * 删除对应orderid的所有订单
     * @author Zhao
     * @param orderid
     */
    @Override
    public void deleteOrderByOrderid(int orderid) {
        QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderStatusQueryWrapper.eq("orderid",orderid);
        orderQueryWrapper.eq("orderid",orderid);
        orderStatusMapper.delete(orderStatusQueryWrapper);
        orderMapper.delete(orderQueryWrapper);
    }
    /**
     * 删除对应orderid的订单状态
     * @author Zhao
     * @param orderId
     */
    @Override
    public void deleteOrderStatusByOrderId(int orderId) {
        orderStatusMapper.deleteById(orderId);
    }
    /**
     * 删除对应orderid的lineItem
     * @author Zhao
     * @param orderId
     */
    @Override
    public void deleteLineItemByOrderId(int orderId) {
        lineItemMapper.deleteById(orderId);
    }
    /**
     * 删除对应orderid的订单状态
     * @author Zhao
     * @return Order的List
     */
    @Override
    public List<Order> findAllOrders() {
        return orderMapper.selectList(null);
    }
    /**
     * 找对应orderid的订单
     * @author Zhao
     * @param orderid
     * @return Order
     */
    @Override
    public Order findOrderByOrderid(int orderid) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("orderid",orderid);
        return orderMapper.selectOne(orderQueryWrapper);
    }
    /**
     * 找到拥有status的全部订单
     * @author Zhao
     * @param status
     */
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
    /**
     * 找到对应date的全部订单
     * @author Zhao
     * @param date
     * @return order的List
     */
    @Override
    public List<Order> findOrderByDate(String date) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("orderdate",date);
        return orderMapper.selectList(orderQueryWrapper);
    }
    /**
     * 找到对应userid的全部订单
     * @author Zhao
     * @param userid
     * @return order的List
     */
    @Override
    public List<Order> findOrderByUserid(String userid) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("userid",userid);
        return orderMapper.selectList(orderQueryWrapper);
    }
    /**
     * 找到对应itemid的全部订单
     * @author Zhao
     * @param itemid
     * @return order的List
     */
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
    /**
     * 查询全部订单状态
     * @author Zhao
     * @return orderStatus的List
     */
    @Override
    public List<OrderStatus> findAllOrderStatus() {
        return orderStatusMapper.selectList(null);
    }
    /**
     * 找到对应orderid的全部LineItem
     * @author Zhao
     * @param orderId
     * @return LineItem的List
     */
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
    /**
     * 更新订单
     * @author Zhao
     * @param order
     */
    @Override
    public void updateOrderByOrderid(Order order)  {
        orderMapper.updateById(order);
    }
    /**
     * 更新订单状态
     * @author Zhao
     * @param orderid
     * @param status
     */
    @Override
    public void updateStatusByOrderid(int orderid, String status) {
        QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
        orderStatusQueryWrapper.eq("orderid",orderid);
        OrderStatus orderStatus = orderStatusMapper.selectOne(orderStatusQueryWrapper);
        orderStatus.setStatus(status);
        orderStatusMapper.updateById(orderStatus);
    }
    /**
     * 更新库存
     * @author Zhao
     * @param inventory
     */
    @Override
    public void updateInventoryByItemid(Inventory inventory) {

        inventoryMapper.updateById(inventory);
    }
}
