package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.TbSpecialTopic;
import com.qigan.qiganshop.pojo.TbSpecialTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TbSpecialTopicMapper {
    int countByExample(TbSpecialTopicExample example);

    int deleteByExample(TbSpecialTopicExample example);

    int deleteByPrimaryKey(String topicId);

    int insert(TbSpecialTopic record);

    int insertSelective(TbSpecialTopic record);

    List<TbSpecialTopic> selectByExample(TbSpecialTopicExample example);

    TbSpecialTopic selectByPrimaryKey(String topicId);

    int updateByExampleSelective(@Param("record") TbSpecialTopic record, @Param("example") TbSpecialTopicExample example);

    int updateByExample(@Param("record") TbSpecialTopic record, @Param("example") TbSpecialTopicExample example);

    int updateByPrimaryKeySelective(TbSpecialTopic record);

    int updateByPrimaryKey(TbSpecialTopic record);

    @Select("select topic_id from tb_special_topic where special_id = #{specialId} ")
    List<String> selectTopicIdBySpecialId(@Param("specialId") String specialId);

    List<TbSpecialTopic> findSpecialTopic(@Param("conditions") String conditions);
}