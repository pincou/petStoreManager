package org.csu.adminsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("inventory")//库存
public class Inventory {
    @TableId(value = "itemid",type = IdType.INPUT)
    private String itemid;
    @TableField(value = "qty")
    private int quantity;
    @TableField(value = "store")
    private int store;//是否上架商店。1上，0未上
}
