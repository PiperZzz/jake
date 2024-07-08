package spring.code.jake.mybasics;

import java.util.function.Function;

public class MyFunctional {
    
    private <T> T getGenericType (MyClass myClass, Function<MyClass, T> function) {
        // 方法定义实现了根据传入的function来获取MyClass中的不同数据类型
        // Function接口的第一个参数是输入，第二个参数是输出
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
