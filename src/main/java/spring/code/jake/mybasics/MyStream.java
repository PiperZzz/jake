package spring.code.jake.mybasics;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class MyStream {

    public static void stream() {
        List<String> myList = new ArrayList<>(
                Arrays.asList("Jake", "John", "Jill", "Jenny", "Jesse", "Jen"));

        /* Optional 也是Java 8新特性 在于优雅的处理null返回值 */
        Optional<Integer> length = myList.stream().filter(s -> s.length() > 3).map(s -> s.length()).reduce((a, b) -> a + b);
        /* filter()方法参数是Predicate<T>, Lambda实现其内部test(T t)方法 返回boolean */
        /* map()方法参数是Function<T, R>, Lambda实现其内部apply(T t)方法 返回泛型R */
        /* filter()和()map都属于Intermediate Operations(中间操作)会继续返回Stream<T>类型对象 */
        /* forEach(),reduce(),collect()都属于Terminal Operations(终端操作) */
        /* Terminal Operations会触发之前Chain Of Intermediate Operations并结束Stream，返回非Stream对象 */

        /* Lazy Evaluation是Stream API想对传统Iterator的最大优势 */
        /* 只有Terminal Operations才会触Chain Of Intermediate Operations这种特性，不会有中间状态的集合浪费资源*/
        if(length.isPresent()) {
            System.out.println(length.get());
        }
    }

    public static void forEach() {
        List<String> myList = new ArrayList<>(
                Arrays.asList("Jake", "John", "Jill", "Jenny", "Jesse"));

        myList.forEach(s -> s = s + "!"); // Collection自己的forEach方法，不会改变原来的list
        myList.forEach(s -> {
            s = s + "!";
            System.out.println(s);
        });
        List<String> myList2 = myList.stream().map(s -> s = s + "?").collect(Collectors.toList());
        myList.forEach(s -> {
            s = s + "#";
            System.out.println(s);
        });
        myList2.forEach(System.out::println);
    }
}
