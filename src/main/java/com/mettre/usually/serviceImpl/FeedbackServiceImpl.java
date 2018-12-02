package com.mettre.usually.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.usually.base.Result;
import com.mettre.usually.base.ReturnType;
import com.mettre.usually.dto.UserDto;
import com.mettre.usually.enum_.ResultEnum;
import com.mettre.usually.enum_.StateEnum;
import com.mettre.usually.exception.CustomerException;
import com.mettre.usually.feign.UserClient;
import com.mettre.usually.mapper.FeedbackMapper;
import com.mettre.usually.pojo.Feedback;
import com.mettre.usually.pojoVM.FeedbackModifyVM;
import com.mettre.usually.pojoVM.FeedbackSearchVM;
import com.mettre.usually.pojoVM.FeedbackVM;
import com.mettre.usually.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    public UserClient userClient;

    @Autowired
    public FeedbackMapper feedbackMapper;

    @Override
    public int deleteByPrimaryKey(Long feedbackId) {
        int type = feedbackMapper.deleteByPrimaryKey(feedbackId);
        return ReturnType.ReturnType(type, ResultEnum.DELETE_ERROR);
    }

    @Override
    public int insert(FeedbackVM feedbackVM) {
        Result<UserDto> userDtoResult = userClient.findUserInfo(feedbackVM.getUserId());
        if (userDtoResult.getData() == null) {
            throw new CustomerException(ResultEnum.USEREMPTY);
        }
        int type = feedbackMapper.insert(new Feedback(feedbackVM, StateEnum.SUBMITTED));
        return ReturnType.ReturnType(type, ResultEnum.INSERT_ERROR);
    }

    @Override
    public int insertSelective(FeedbackVM feedbackVM) {
        return 0;
    }

    @Override
    public Feedback selectByPrimaryKey(Long feedbackId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(FeedbackModifyVM feedbackVM) {
        int type = feedbackMapper.updateByPrimaryKeySelective(new Feedback(feedbackVM));
        return ReturnType.ReturnType(type, ResultEnum.FEEDBACK_STATE);
    }

    @Override
    public int updateByPrimaryKey(Feedback record) {
        return 0;
    }

    @Override
    public Page<Feedback> findFeedbackListPageVo(Page<Feedback> page, FeedbackSearchVM feedbackSearchVM) {
        if (null != feedbackSearchVM.getState() && !StateEnum.contains(feedbackSearchVM.getState().name())) {
            throw new CustomerException("反馈状态选择错误");
        }
        List<Feedback> feedbackList = (List<Feedback>) feedbackMapper.findFeedbackListPageVo(page, feedbackSearchVM);
        page = page.setRecords(feedbackList);
        return page;
    }
}
