package com.llu.spelDemo;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

/**
 * SpelUtil 类提供了一个静态方法来解析 Spring Expression Language (SpEL) 表达式。
 * 它允许在给定参数的情况下评估 SpEL 表达式。
 */
public class SpelUtil {

    /**
     * SpEL 表达式解析器的实例。
     */
    /**
     * 用于SpEL表达式解析.
     */
    private static SpelExpressionParser parser = new SpelExpressionParser();

    /**
     * 解析给定的 SpEL 表达式字符串。
     *
     * @param spELString SpEL 表达式字符串。
     * @param param 表达式评估时使用的参数数组。
     * @param parameterNames 参数名称数组，与参数数组对应。
     * @return 解析后的表达式值的字符串表示。
     *         如果解析失败或表达式不需要解析，则返回原始表达式字符串。
     */
    public static String parseSpEL(String spELString, Object[] param, String[] parameterNames) {
        try {
            // 检查表达式字符串是否为空，为空则直接返回原字符串
            if (StringUtils.isEmpty(spELString)) {
                return spELString;
            }
            // 检查参数是否为空或长度为0，为空则直接返回原字符串
            if (param == null || param.length == 0) {
                return spELString;
            }
            // 解析 SpEL 表达式字符串
            // 解析过后的Spring表达式对象
            Expression expression = parser.parseExpression(spELString);
            // 创建一个标准的表达式上下文
            // spring的表达式上下文对象
            EvaluationContext context = new StandardEvaluationContext();
            // 将参数绑定到表达式上下文中
            // 给上下文赋值
            for (int i = 0; i < param.length; i++) {
                context.setVariable(parameterNames[i], param[i]);
            }
            // 评估表达式并返回结果的字符串表示
            return expression.getValue(context).toString();
        } catch (Exception e) {
            // 捕获并处理解析过程中可能抛出的任何异常
            System.out.println("解析异常" + e.getMessage());
            return spELString;
        }
    }
}


