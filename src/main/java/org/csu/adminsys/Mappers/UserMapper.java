package org.csu.adminsys.Mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.adminsys.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
