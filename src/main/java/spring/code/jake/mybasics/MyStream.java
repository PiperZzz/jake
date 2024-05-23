package spring.code.jake.mybasics;

import java.util.*;
import java.util.stream.Collectors;

public class MyStream {
    
    public static void forEach() {
        List<String> myList = new ArrayList<>(Arrays.asList("Jake", "John", "Jill", "Jenny", "Jesse", "Jen", "Jen", "Jen"));

        myList.forEach(s -> s = s + "!"); // Collection自己的forEach方法，不会改变原来的list
        List<String> myList2 = myList.stream().map(s -> s = s + "?").collect(Collectors.toList());

        myList2.forEach(System.out::println);
    }
}
