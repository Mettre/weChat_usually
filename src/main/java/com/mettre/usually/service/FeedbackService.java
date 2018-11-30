package com.mettre.usually.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.usually.pojo.Feedback;
import com.mettre.usually.pojoVM.FeedbackModifyVM;
import com.mettre.usually.pojoVM.FeedbackSearchVM;
import com.mettre.usually.pojoVM.FeedbackVM;

public interface FeedbackService {

    int deleteByPrimaryKey(Long feedbackId);

    int insert(FeedbackVM feedbackVM);

    int insertSelective(FeedbackVM feedbackVM);

    Feedback selectByPrimaryKey(Long feedbackId);

    int updateByPrimaryKeySelective(FeedbackModifyVM feedbackVM);

    int updateByPrimaryKey(Feedback record);

    Page<Feedback> findFeedbackListPageVo(Page<Feedback> page, FeedbackSearchVM feedbackSearchVM);
}
