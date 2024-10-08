package com.hui.huioj.service;

import com.hui.huioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.hui.huioj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hui.huioj.model.entity.User;

/**
* @author Admin
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-09-20 21:55:31
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);
}
