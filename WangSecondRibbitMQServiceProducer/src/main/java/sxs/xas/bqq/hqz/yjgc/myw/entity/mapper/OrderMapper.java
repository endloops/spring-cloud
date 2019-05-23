package sxs.xas.bqq.hqz.yjgc.myw.entity.mapper;

import org.apache.ibatis.annotations.Mapper;

import sxs.xas.bqq.hqz.yjgc.myw.entity.model.Order;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}