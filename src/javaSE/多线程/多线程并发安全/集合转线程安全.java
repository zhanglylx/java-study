package 多线程.多线程并发安全;

import java.util.*;

/**
 * 将集合或Map转换为线程安全的
 */
public class 集合转线程安全 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        //将现有的List集合转换为线程安全的
        list = Collections.synchronizedList(list);
        System.out.println(list);


        Set<String> set = new HashSet<>();
        //将现有的set集合转换为线程安全的
        set = Collections.synchronizedSet(set);


        Map<String,Integer> map = new HashMap<>();
        //将现有的map转换为线程安全的
        map = Collections.synchronizedMap(map);
    }
}
