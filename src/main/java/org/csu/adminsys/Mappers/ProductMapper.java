package org.csu.adminsys.Mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.csu.adminsys.entity.Product;
import org.springframework.stereotype.Repository;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
