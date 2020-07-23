package 格式化.数字格式化;

import java.text.DecimalFormat;

public class DecimalFormat的使用 {
    public static double pi;

    public  void 零和井号配置使用() {
//         0和#配合使用
        double pi = 3.1415927;//圆周率
//取一位整数
        System.out.println(new DecimalFormat("0").format(pi));//3
//取一位整数和两位小数
        System.out.println(new DecimalFormat("0.00").format(pi));//3.14
//取两位整数和三位小数，整数不足部分以0填补。
        System.out.println(new DecimalFormat("00.000").format(pi));// 03.142
//取所有整数部分
        System.out.println(new DecimalFormat("#").format(pi));//3
//以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("#.##%").format(pi));//314.16%

        /**
         * 上面的代码就是网上很经典的案例，下面我们来分析另外的一个值
         */
        pi = 12.34567;
//取一位整数
        System.out.println(new DecimalFormat("0").format(pi));//12
//取一位整数和两位小数
        System.out.println(new DecimalFormat("0.00").format(pi));//12.35
//取两位整数和三位小数，整数不足部分以0填补。
        System.out.println(new DecimalFormat("00.000").format(pi));// 12.346
//取所有整数部分
        System.out.println(new DecimalFormat("#").format(pi));//12
//以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("#.##%").format(pi));//1234.57%

/**
 * 扩展，如果是其他的数字会是下面的效果
 */
        pi = 12.34;
//整数
        System.out.println(new DecimalFormat("6").format(pi));//612
        System.out.println(new DecimalFormat("60").format(pi));//612
        System.out.println(new DecimalFormat("06").format(pi));//126
        System.out.println(new DecimalFormat("00600").format(pi));//00126
        System.out.println(new DecimalFormat("#####60000").format(pi));//00126
//小数
        System.out.println(new DecimalFormat(".6").format(pi));//12.6
        System.out.println(new DecimalFormat(".06").format(pi));//12.36
        System.out.println(new DecimalFormat(".60").format(pi));//12.36
        System.out.println(new DecimalFormat(".0600").format(pi));//12.3406
        System.out.println(new DecimalFormat(".6000").format(pi));//12.3406
        System.out.println(new DecimalFormat(".600000##").format(pi));//12.340006

    }

    public void 科学技术发() {
        double pi = 123456789.3456;
        System.out.println(new DecimalFormat("0E0").format(pi));//1E8
        System.out.println(new DecimalFormat("0E00").format(pi));//1E08
        System.out.println(new DecimalFormat("#E0").format(pi));//.1E9
        System.out.println(new DecimalFormat("##E0").format(pi));//1.2E8
        System.out.println(new DecimalFormat("###E0").format(pi));//123E6
        System.out.println(new DecimalFormat("####E0").format(pi));//1.235E8
        System.out.println(new DecimalFormat("#####E0").format(pi));//1234.6E5
        System.out.println(new DecimalFormat("######E0").format(pi));//123.457E6
        System.out.println(new DecimalFormat("#######E0").format(pi));//12.34568E7
        System.out.println(new DecimalFormat("########E0").format(pi));//1.2345679E8
        System.out.println(new DecimalFormat("#########E0").format(pi));//123456789E0
        System.out.println(new DecimalFormat("##########E0").format(pi));//123456789.3E0

        pi = 12345678.3456;
        System.out.println(new DecimalFormat("0E0").format(pi));//1E7
        System.out.println(new DecimalFormat("0E00").format(pi));//1E07
        System.out.println(new DecimalFormat("#E0").format(pi));//.1E8
        System.out.println(new DecimalFormat("##E0").format(pi));//12E6
        System.out.println(new DecimalFormat("###E0").format(pi));//12.3E6
        System.out.println(new DecimalFormat("####E0").format(pi));//1235E4
        System.out.println(new DecimalFormat("#####E0").format(pi));//123.46E5
        System.out.println(new DecimalFormat("######E0").format(pi));//12.3457E6
        System.out.println(new DecimalFormat("#######E0").format(pi));//12.34568E7
        System.out.println(new DecimalFormat("########E0").format(pi));//12345678E0
        System.out.println(new DecimalFormat("#########E0").format(pi));//12345678.3E0
        System.out.println(new DecimalFormat("##########E0").format(pi));//12345678.35E0

/**
 * 0的个数决定最后输出结果的位数
 * 并且与0的位置无关
 */
        pi = 12345;
        System.out.println(new DecimalFormat("###.##E0").format(pi));//12.345E3
        System.out.println(new DecimalFormat("##0.##E0").format(pi));//12.345E3
        System.out.println(new DecimalFormat("##0.0##E0").format(pi));//12.345E3
        System.out.println(new DecimalFormat("##0.00000##E0").format(pi));//12.3450E3
        System.out.println(new DecimalFormat("#00.0000##E0").format(pi));//12.3450E3
        System.out.println(new DecimalFormat("#00.00000##E0").format(pi));//12.34500E3
    }

    public void 分组分隔符() {
        double pi = 1299792458;
//每三位以逗号进行分隔。
        System.out.println(new DecimalFormat(",###").format(pi));//1,299,792,458
        System.out.println(new DecimalFormat(",##").format(pi));//12,99,79,24,58
        System.out.println(new DecimalFormat("###,##").format(pi));//12,99,79,24,58
    }

    public void 减少(){
//        减号 -
        double pi = 3.14;
        System.out.println(new DecimalFormat("-0.00").format(pi));//-3.14
    }

    class 前缀后缀{

        public void 将数字乘以100(){
//            % 将数字乘以100
            pi = 0.1234;
            System.out.println(new DecimalFormat("0.00%").format(pi));//12.34%
            System.out.println(new DecimalFormat("0%.00").format(pi));//12.34%
            System.out.println(new DecimalFormat("%0.00").format(pi));//%12.34
//            %处理最前面不能放置之外，其他的地方都可以放置。
        }

        public void 将数字乘以1000(){
            pi = 0.1234;
            System.out.println(new DecimalFormat("0.00\u2030").format(pi));//123.40‰
            System.out.println(new DecimalFormat("0.0\u20300").format(pi));//123.40‰
            System.out.println(new DecimalFormat("\u20300.00").format(pi));//‰123.40
//            \u2030和%用法是一样的。
        }

        public void 本地化货币符号(){
            pi = 1234.5678;
            System.out.println(new DecimalFormat(",000.00¤").format(pi));//1,234.57￥
            System.out.println(new DecimalFormat(",000.¤00").format(pi));//1,234.57￥
            System.out.println(new DecimalFormat("¤,000.00").format(pi));//￥1,234.57
            System.out.println(new DecimalFormat(",00¤0.¤00").format(pi));//1,234.57￥￥
            System.out.println(new DecimalFormat("¤,000.¤00").format(pi));//￥1,234.57￥
            System.out.println(new DecimalFormat(",000.00¤¤").format(pi));//1,234.57CNY
        }

        public void 用于引用特殊的字符(){
            pi = 4.5678;
            System.out.println(new DecimalFormat("'#'0.00").format(pi));//#4.57
            System.out.println(new DecimalFormat("'^ _ ^'0.00").format(pi));//^ _ ^4.57
//使用'本身作为前缀或后缀
            System.out.println(new DecimalFormat("''0.00").format(pi));//'4.57
        }


    }

}
