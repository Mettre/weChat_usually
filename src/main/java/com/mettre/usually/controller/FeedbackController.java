package com.mettre.usually.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.usually.base.Result;
import com.mettre.usually.base.ResultUtil;
import com.mettre.usually.pojo.Feedback;
import com.mettre.usually.pojoVM.FeedbackModifyVM;
import com.mettre.usually.pojoVM.FeedbackSearchVM;
import com.mettre.usually.pojoVM.FeedbackVM;
import com.mettre.usually.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(description = "用户反馈")
public class FeedbackController {

    @Autowired
    public FeedbackService feedbackService;

    @RequestMapping(value = "/addFeedback", method = RequestMethod.POST)
    @ApiOperation(value = "新增用户反馈")
    public Result<Object> addFeedback(@Valid @RequestBody FeedbackVM feedbackVM) {
        feedbackService.insert(feedbackVM);
        return new ResultUtil<>().setSuccess();
    }

    @RequestMapping(value = "/handleFeedback", method = RequestMethod.POST)
    @ApiOperation(value = "处理反馈状态")
    public Result<Object> handleFeedback(@Valid @RequestBody FeedbackModifyVM feedbackVM) {
        feedbackService.updateByPrimaryKeySelective(feedbackVM);
        return new ResultUtil<>().setSuccess();
    }

    @RequestMapping(value = "/FeedbackPageVo", method = RequestMethod.POST)
    @ApiOperation(value = "查询反馈列表")
    public Result<Object> findFeedbackList(@Valid @RequestBody FeedbackSearchVM feedbackSearchVM) {
        Page<Feedback> page = new Page<>(feedbackSearchVM.getPage(), feedbackSearchVM.getSize());
        return new ResultUtil<>().setData(feedbackService.findFeedbackListPageVo(page, feedbackSearchVM));
    }


    @RequestMapping(value = "/deleteFeedback", method = RequestMethod.POST)
    @ApiOperation(value = "删除反馈")
    public Result<Object> deleteFeedback(@RequestParam Long feedbackId) {
        feedbackService.deleteByPrimaryKey(feedbackId);
        return new ResultUtil<>().setSuccess();
    }

}
