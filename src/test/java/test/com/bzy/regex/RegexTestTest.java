package test.com.bzy.regex;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * RegexTest Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>07/03/2019</pre>
 */
public class RegexTestTest {

    /**
     * 不是零宽断言
     */
    @Test
    public void test1() {
        String pattern = "industr(?:y|ies)";
        String target = "industryyy";
        this.matcher(pattern, target);
    }

    /**
     * 零宽断言像\b ^ $那样用于指定一个位置，这个位置满足一定的条件。
     * 匹配零宽断言不消耗字符(注意断言只能判断字符)
     */
    /**
     * 零宽负预测先行断言 他断言自身出现的（位置）的（后面）不能匹配表达式
     */
    @Test
    public void test2() {
        String pattern = "ab(?![A-Z])";
        String target = "abZW863ab3";
        //匹配了位置8-9的（ab）

        this.matcher(pattern, target);
    }

    /**
     * 零宽正预测先行断言 他断言自身出现的（位置）的（后面）能匹配表达式
     */
    @Test
    public void test3() {
        String pattern = "ab(?=[A-Z])";
        String target = "abZW863abA";
        /**
         * 1. a匹配位置1成功，转交b
         * 2. b匹配位置2成功，转交断言
         * 3. 断言位置2-3成功，但不消耗字符
         * 4. 本次正则匹配成功，消耗1-2位置字符（ab）
         * 5. a匹配位置3，失败...a匹配位置8成功，转交b
         * 6. b匹配位置9成功，转交断言
         * 7. 断言位置9-10成功，但不消耗字符
         * 8. 本次正则匹配成功，消耗8-9位置字符（ab）
         * 9. a匹配位置10，失败
         * 10. 目标字符串匹配结束
         */
        this.matcher(pattern, target);
    }

    /**
     * 零宽正回顾后发断言 他断言自身出现的（位置）的（前面）能匹配表达式
     */
    @Test
    public void test4() {
        String pattern = "(?<=[A-Z])\\d+";
        String target = "abZW863ab3";
        //863
        this.matcher(pattern, target);
    }

    /**
     * 零宽负回顾后发断言 他断言自身出现的（位置）的（前面）不匹配表达式
     */
    @Test
    public void test5() {
        String pattern = "(?<![A-Z])\\d+";
        String target = "abZW863ab3";
        //63 6
        this.matcher(pattern, target);
    }

    @Test
    public void test6() {
        /**
         * ^ 匹配开始位置，零宽
         * $ 匹配结束位置，零宽
         * * 匹配前面的表达式0或多次，{0,}
         * + 匹配前面的表达式1或多次，{1,}
         * ? 匹配前面的表达式0或1次，{0,1}
         * {n} {n,} {n,m} 匹配多次
         * ? 当该字符紧跟在任何一个其他限制字符，表示非贪婪匹配。默认是贪婪匹配
         * . 匹配除换行符\n \r之外的所有字符
         * x|y 匹配x或者y，z|floor -> z或者floor; (z|f)loor -> zloor或者floor；因为|优先级比一般字符更低
         * [xyz] 匹配其中任一字符
         * [^xyz] 不匹配其中任何字符
         * [a-z] [^a-z] 匹配范围字符
         *
         * \b匹配单词边界 er\b -> never
         * \B匹配非单词边界 er\B -> verb
         * \d 匹配一个数字字符
         * \D 匹配一个非数字字符
         * \n 换行 \r 回车 \t 制表符
         * \s 空格 \S 非空格
         * \w 字符、数字、下划线 \W非
         * \num num是一个正整数，表示对所获取的匹配的引用。(.)\1 匹配两个连续的相同字符
         */
    }

    @Test
    public void test7() {
        /**
         * 优先级：
         * \ 转义
         * () [] 圆括号和方括号
         * * + ? {n,m} 限定符
         * ^ $ 其他元字符、任何字符
         * | 或操作，因为|比一般字符优先级低，所以f|kood 匹配k 或 kood，可以用()提高优先级
         */
    }

    @Test
    public void test8() {
        /**
         * (?<Name>)命名捕获 - group(Name)
         *
         * (?(if-exp) then-exp else-exp) 条件判断
         */

        Pattern pattern = Pattern.compile("My Name is (?<name>.*?)\\.");
        Matcher matcher = pattern.matcher("My Name is bzy.");
        if (matcher.find()) {
            //匹配是一个lazy-do必须先调用find去实际进行匹配
            String name = matcher.group("name");
            System.out.println(name);
        }
    }

    @Test
    public void test10() {
        /**
         * (?>) 固化分组 - 不会交还已经匹配的内容
         */
        matcher("(.*):", "123:");
        matcher("(.*)(?=:):", "abd:");
        /**
         * 1. .*匹配kkk:都匹配到，由于是固化分组，不会吐出已经匹配的字符，也就是所有字符都被.*吃了，匹配到行尾时，发现有:没有匹配，也不会吐出所以匹配失败
         * 2. "[a-zA-Z]+:" 匹配"Subject"，首先[a-zA-Z]+一直匹配到行尾，发现:没有被匹配，会从[a-zA-Z]+匹配字符吐出最后一个t
         * 当t不能匹配时，会继续吐出c一直到所有字符都吐出，这样性能比价低
         * 3. 对于不支持固化分组的正则引擎，可以使用环视处理
         */
        matcher("(?>.*):", "kkk:");
    }

    @Test
    public void test9() {
        /**
         * (?i) 开启不区分大小写匹配
         * (?-i) 关闭不区分大小写匹配
         * \Q...\E 之间...文本统一当作文本匹配，不解析为元字符，即不需要在大段文本里一个个用\处理元字符
         */
        matcher("(?i)p(?-i)etter", "Petter"); //可以匹配
        matcher("(?i)P(?-i)etter", "pETter"); //不可以匹配

        matcher("\\Q3.2\\E\\d+", "3.24"); //可以匹配
    }

    private void matcher(String patternStr, String target) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(target);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }


    @Test
    public void appendParam() {
        Pattern pattern = Pattern.compile("([^:]*$)");
        Matcher matcher = pattern.matcher("tag:tk_pub_id");
        //Matcher matcher = pattern.matcher("tk_pub_id");
        if (matcher.find()) {
            System.out.println("re: " + matcher.group(1));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> dimList = Arrays.asList("tag:abc", "abc:tag");
        List<String> collect = dimList.stream().sorted(Comparator.comparing(RegexTestTest::findSortWord)).collect(
            Collectors.toList());
        System.out.println(collect);
    }

    private static final Pattern DIM_SORT_USE = Pattern.compile("([^:]*$)");
    private static String findSortWord(String dim) {
        Matcher matcher = DIM_SORT_USE.matcher(dim);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return dim;
        }
    }
}
