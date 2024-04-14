package org.csu.adminsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("lineitem")
public class LineItem {


    @TableId(value = "orderid",type = IdType.INPUT)
    private int orderId;
    @TableField(value = "linenum")
    private int lineNumber;
    @TableField(value = "quantity")
    private int quantity;
    @TableField(value = "itemid")
    private String itemId;
    @TableField(value = "unitprice")
    private BigDecimal unitPrice;
    @TableField(exist = false)
    private BigDecimal total;

    @TableField(exist = false)
    private String productId;
    @TableField(exist = false)
    private String categoryId;

    public LineItem(){}

    public LineItem(int lineNumber, CartItem cartItem) {
        this.orderId = lineNumber;
        this.lineNumber = lineNumber;
        this.quantity = cartItem.getQuantity();
        this.itemId = cartItem.getItem().getItemId();
        this.unitPrice = cartItem.getItem().getListPrice();
    }
}
