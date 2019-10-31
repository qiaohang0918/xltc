package com.qigan.qiganshop.mapper;

import com.qigan.qiganshop.pojo.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author starlord
 */
public interface FeedbackMapper {
    int insert(Feedback feedback);

    List<Feedback> getFeedbackPage(@Param("page") int page, @Param("size") int size, @Param("order") String order, @Param("sort") String sort);

    long count();
}
