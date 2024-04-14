package org.csu.adminsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cartshop")
public class CartShop {
    @TableId(value = "userid",type = IdType.INPUT)
    private String username;
    @TableField(value = "itemid")
    private String itemid;
    @TableField(value = "productid")
    private String productid;
    @TableField(value = "quantity")
    private int quantity;

}
