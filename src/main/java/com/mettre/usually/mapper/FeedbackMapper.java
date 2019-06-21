package com.mettre.usually.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.usually.pojo.Feedback;
import com.mettre.usually.pojoVM.FeedbackSearchVM;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FeedbackMapper {

    int deleteByPrimaryKey(Long feedbackId);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Long feedbackId);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

    List<Feedback> findFeedbackListPageVo(Page<Feedback> page, @Param(value = "feedbackSearchVM") FeedbackSearchVM feedbackSearchVM, @Param(value = "userId") String userId);
}