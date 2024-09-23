package com.hui.huioj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hui.huioj.common.ErrorCode;
import com.hui.huioj.exception.BusinessException;
import com.hui.huioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.hui.huioj.model.entity.Question;
import com.hui.huioj.model.entity.QuestionSubmit;
import com.hui.huioj.model.entity.User;
import com.hui.huioj.model.enums.QuestionSubmitLanguageEnum;
import com.hui.huioj.model.enums.QuestionSubmitStatusEnum;
import com.hui.huioj.service.QuestionService;
import com.hui.huioj.service.QuestionSubmitService;
import com.hui.huioj.mapper.QuestionSubmitMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Admin
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 * @createDate 2024-09-20 21:55:31
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        // 校验编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"编程语言错误");
        }

        long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已提交题目
        long userId = loginUser.getId();
        // 每个用户串行提交题目
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(language);
        // 设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");

        boolean save = this.save(questionSubmit);

        if (!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据插入失败");
        }
        return questionSubmit.getId();
    }
}




