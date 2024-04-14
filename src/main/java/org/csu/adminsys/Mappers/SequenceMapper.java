package org.csu.adminsys.Mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.csu.adminsys.entity.Sequence;
import org.springframework.stereotype.Repository;

@Mapper
public interface SequenceMapper extends BaseMapper<Sequence> {
}
