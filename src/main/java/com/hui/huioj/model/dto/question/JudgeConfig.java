package com.hui.huioj.model.dto.question;

import lombok.Data;

/**
 * 题目配置
 *
 * @author 王程辉
 * @date 2024/09/21 13:08
 */
@Data
public class JudgeConfig {
    /**
     * 时间限制（ms）
     */
    private Long timeLimit;

    /**
     * 内存限制（KB）
     */
    private Long memoryLimit;

    /**
     * 堆栈限制（KB）
     */
    private Long stackLimit;
}
