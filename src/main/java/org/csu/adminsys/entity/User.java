package org.csu.adminsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("userinfo")
public class User implements Serializable {
    //主键
    @TableId(value = "id",type = IdType.INPUT)
    private int id;

    @NotBlank(message="用户名不能为空")
    @TableField(value = "username")
    private String username;

    @NotBlank(message="密码不能为空")
    @TableField(value = "password")
    private String password;

    @TableField(value = "email")
    private String email;

    @TableField(value = "age")
    private int age;

    @TableField(value = "isAdmin")
//    private boolean isAdmin;
    private int isAdmin;

}
