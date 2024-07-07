package spring.code.jake.mybasics;

import java.util.function.Function;

public class MyFunctional {
    
    private <T> T getGenericType (MyClass myClass, Function<MyClass, T> function) {
        return function.apply(myClass);
    }

    public String getMyString(MyClass myClass) {
        return getGenericType(myClass, MyClass::getString);
    }

    public Integer getMyInteger(MyClass myClass) {
        return getGenericType(myClass, MyClass::getInteger);
    }

    class MyClass {
        private String string;
        private Integer integer;

        public String getString() {
            return string;
        }

        public Integer getInteger() {
            return integer;
        }
    }
}
