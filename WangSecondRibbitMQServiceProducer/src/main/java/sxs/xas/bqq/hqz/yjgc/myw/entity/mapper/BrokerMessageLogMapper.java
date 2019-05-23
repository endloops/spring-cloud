package sxs.xas.bqq.hqz.yjgc.myw.entity.mapper;

import sxs.xas.bqq.hqz.yjgc.myw.entity.model.BrokerMessageLog;

public interface BrokerMessageLogMapper {
    int deleteByPrimaryKey(String messageId);

    int insert(BrokerMessageLog record);

    int insertSelective(BrokerMessageLog record);

    BrokerMessageLog selectByPrimaryKey(String messageId);

    int updateByPrimaryKeySelective(BrokerMessageLog record);

    int updateByPrimaryKey(BrokerMessageLog record);
}