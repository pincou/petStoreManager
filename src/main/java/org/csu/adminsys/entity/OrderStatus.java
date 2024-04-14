package org.csu.adminsys.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("orderstatus")
public class OrderStatus {
    @TableId(value = "orderid",type = IdType.INPUT)
    private int orderid;
    @TableField(value = "timestamp")
    private String timestamp;
    @TableField(value = "linenum")
    private int linenum;
    @TableField(value = "status")
    private String status;//订单状态：未支付、未发货、已发货、已收货、已取消
}
