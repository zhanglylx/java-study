import java.util.Arrays;

public class 知识扩展 {
    /**
     * 变长参数
     * 变长参数后不能再加入其它参数，如果需要其它参数，需要在变长参数前
     * @param str
     */
    public static void test(String ... str){
        System.out.println(Arrays.toString(str));
    }
}
