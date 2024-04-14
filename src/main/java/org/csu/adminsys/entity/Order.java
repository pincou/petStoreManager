package org.csu.adminsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Data
@TableName("orders")
public class Order {
    @TableId(value = "orderid",type = IdType.INPUT)
    private int orderId;
    @TableField(value = "userid")
    private String username;

    @TableField(value = "orderdate")
    private  String orderDate;
    @TableField(value = "shipaddr1")
    private String shipAddress1;
    @TableField(value = "shipaddr2")
    private String shipAddress2;
    @TableField(value = "shipcity")
    private String shipCity;
    @TableField(value = "shipstate")
    private String shipState;
    @TableField(value = "shipzip")
    private String shipZip;
    @TableField(value = "shipcountry")
    private String shipCountry;
    @TableField(value = "billaddr1")
    private String billAddress1;
    @TableField(value = "billaddr2")
    private String billAddress2;
    @TableField(value = "billcity")
    private String billCity;
    @TableField(value = "billstate")
    private String billState;
    @TableField(value = "billzip")
    private String billZip;
    @TableField(value = "billcountry")
    private String billCountry;
    @TableField(value = "courier")
    private String courier;
    @TableField(value = "totalprice")
    private BigDecimal totalPrice;
    @TableField(value = "billtofirstname")
    private String billToFirstName;
    @TableField(value = "billtolastname")
    private String billToLastName;
    @TableField(value = "shiptofirstname")
    private String shipToFirstName;
    @TableField(value = "shiptolastname")
    private String shipToLastName;
    @TableField(value = "creditcard")
    private String creditCard;
    @TableField(value = "exprdate")
    private String expiryDate;
    @TableField(value = "cardtype")
    private String cardType;
    @TableField(value = "locale")
    private String locale;
    //status状态对应OrderStatus中的属性们
    @TableField(exist = false)
    private String status;

    @TableField(exist = false)//数据库表单中不含此属性值
    private List<LineItem> lineItems = new ArrayList<LineItem>();

    @TableField(exist = false)
    private OrderStatus orderStatus = new OrderStatus();

//初始化
    public void initOrder(Account account, Cart cart) {

        username = account.getUsername();
//        orderDate = new Timestamp(System.currentTimeMillis());
        orderDate = new Date(System.currentTimeMillis()).toString();

        shipToFirstName = account.getFirstName();
        shipToLastName = account.getLastName();
        shipAddress1 = account.getAddress1();
        shipAddress2 = account.getAddress2();
        shipCity = account.getCity();
        shipState = account.getState();
        shipZip = account.getZip();
        shipCountry = account.getCountry();

        billToFirstName = account.getFirstName();
        billToLastName = account.getLastName();
        billAddress1 = account.getAddress1();
        billAddress2 = account.getAddress2();
        billCity = account.getCity();
        billState = account.getState();
        billZip = account.getZip();
        billCountry = account.getCountry();

        totalPrice = cart.getSubTotal();

        creditCard = "123 4567 8888 9999";
        expiryDate = "4/2";
        cardType = "Visa";
        courier = "UPS";
        locale = "CA";
        status = "P";

        Iterator<CartItem> i = cart.getAllCartItems();
        while (i.hasNext()) {
            CartItem cartItem = (CartItem) i.next();
            addLineItem(cartItem);
        }

    }

    public void addLineItem(CartItem cartItem) {
        LineItem lineItem = new LineItem(lineItems.size() + 1, cartItem);
        lineItems.add(lineItem);
    }


    public void addOrderStatus(OrderStatus orderStatus){
        this.orderStatus.setOrderid(orderId);
        this.orderStatus.setLinenum(orderStatus.getLinenum());
        this.orderStatus.setTimestamp(orderStatus.getTimestamp());
        this.orderStatus.setStatus(orderStatus.getStatus());
    }
}
