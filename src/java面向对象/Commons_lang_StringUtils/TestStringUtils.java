package Commons_lang_StringUtils;

import org.apache.commons.lang.StringUtils;

public class TestStringUtils {
    public static void main(String[] args) {
        /**
         * repeat的作用是将给定的字符串重复拼接给定次后返回
         */
        String info = StringUtils.repeat("hello", 5);

        System.out.println(info);
        //从左边补充指定长度的字符串并返回
        String leftPad = StringUtils.leftPad("hello", 20);
        System.out.println(leftPad);
        //重载功能
        leftPad = StringUtils.leftPad("hello", 20,"a");
        System.out.println(leftPad);
    }
}
