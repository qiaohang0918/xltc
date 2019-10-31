package com.qigan.qiganshop.service.impl;

import com.qigan.qiganshop.mapper.FeedbackMapper;
import com.qigan.qiganshop.pojo.Feedback;
import com.qigan.qiganshop.service.FeedbackService;
import com.qigan.qiganshop.util.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author starlord
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public void addFeedback(Feedback feedback) throws Exception {
        int result = feedbackMapper.insert(feedback);
        if(result!=1){
            throw new Exception("新增出错.");
        }
    }

    @Override
    public PageResult getFeedbackPage(int page, int size, String order, String sort) {
        List<Feedback> feedbacks = feedbackMapper.getFeedbackPage(page, size, order, sort);
        long total = feedbackMapper.count();
        return new PageResult(total, feedbacks);
    }
}
