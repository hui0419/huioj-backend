package com.hui.huioj.model.dto.question;

import lombok.Data;

/**
 * 题目用例
 *
 * @author 王程辉
 * @date 2024/09/21 13:08
 */
@Data
public class JudgeCase {
    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
