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

        Optional<Integer> length = myList.stream().filter(s -> s.length() > 3).map(s -> s.length()).reduce((a, b) -> a + b);

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
