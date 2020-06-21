package com.wm.zgy.bootmybatismbplusshiroesquartz;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/6/21 13:05
 * @Modified by:
 */
public class J8MyInterfaceUse {
    public static void main(String[] args) {
        System.out.println(MyInterface.getVersion());
        System.out.println(new HelloWorld().version());
        System.out.println(new HelloWorld().getName());
    }

    static class HelloWorld implements MyInterface{
        @Override
        public String getName() {
            return "hello";
        }
    }
}

interface MyInterface{
    default String myInfo(){
        return "myinfo";
    }

    public static String getVersion(){
        return "1.0";
    }

    default String version(){
        return "1.0";
    }

    public String getName();
}
