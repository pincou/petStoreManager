package org.csu.adminsys;

import org.csu.adminsys.Mappers.ItemMapper;
import org.csu.adminsys.entity.Item;
import org.csu.adminsys.service.AccountService;
import org.csu.adminsys.service.CatalogService;
import org.csu.adminsys.service.OrderService;
import org.csu.adminsys.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("org.csu.adminsys.Mappers")
public class UserTest {
    @Autowired
   UserService userService;
    @Test
    void testOrder(){
        userService.findAllUsers();
    }


}
