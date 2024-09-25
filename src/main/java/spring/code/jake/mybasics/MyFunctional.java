package spring.code.jake.mybasics;

import java.util.function.Function;

public class MyFunctional {

    public String getMyString(MyClass myClass) {
        return getGenericType(myClass, MyClass::getString);
    }

    public Integer getMyInteger(MyClass myClass, Integer integer) {
        return getGenericType(myClass, myClass2 -> myClass2.getInteger(integer));
    }
    
    private <T> T getGenericType (MyClass myClass, Function<MyClass, T> function) {
        // 根据参数function中的泛型类型来获取MyClass中的不同数据类型
        // Function接口的第一个参数是输入类型，第二个参数是输出类型
        return function.apply(myClass);
    }
    class MyClass {
        private String string;
        private final Integer integer = 0;

        public String getString() {
            return string;
        }

        public Integer getInteger(Integer integer) {
            return this.integer + integer;
        }
    }
}
