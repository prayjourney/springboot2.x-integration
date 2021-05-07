package com.zgy.learn.returnerror;

/**
 * 测试返回值与错误之间的关系
 *
 * https://blog.csdn.net/bitcarmanlee/article/details/81746205
 * 对于一个java方法来说，退出的方式有两种：
 * ①遇到一个返回的指令（return语句）
 * ②遇到一个异常，并且没有搜索到异常处理器，不会给调用返回任何值。
 * 所以当catch中有return时，程序遇到return时就被标记了，finally块中只是执行最后的清理工作，如数据库连接的释放等。但是并不会修改return这个语句。
 *
 * @author: z.g.y
 * @date: 2021/5/7
 */
public class ReturnValueAndError {
    public static void main(String[] args) {
        System.out.println("finally之中赋值+return的值, 覆盖了一切!!!");
        System.out.println(returnValue01());
        System.out.println(returnValue02());

        System.out.println("=======================================\n");
        System.out.println("finally之中赋值, 没有return就不起作用!!!!!!!");
        System.out.println(returnValue03());
        System.out.println(returnValue04());

        System.out.println("=======================================\n");
        System.out.println("无特别需要不要在finally块中有额外操作。最佳实践, 发生错误仍然可以返回想要的值!!!");
        System.out.println(returnValue05());
        System.out.println(returnValue06());
        System.out.println(returnValue07());
    }

    // 1000, 正常执行, 本来是1, 最后值被finally修改
    public static int returnValue01() {
        int x = 1;
        try {
            return x;
        } catch (Exception e) {
            x = 100;
            return x;
        } finally {
            x = 1000;
            return x;
        }
    }

    // 1000, 出现异常, 导致x=100, 最后值被finally修改为1000, 并且返回
    public static int returnValue02() {
        int x = 1;
        try {
            return x / 0;
        } catch (Exception e) {
            x = 100;
            return x;
        } finally {
            x = 1000;
            return x;
        }
    }

    // 1, 执行到finally让x=1000, 但是没有影响返回值仍然是1
    public static int returnValue03() {
        int x = 1;
        try {
            return x;
        } catch (Exception e) {
            x = 100;
            return x;
        } finally {
            x = 1000;
        }
    }

    // 100, 出现异常让x=100, 执行到finally让x=1000, 但是没有影响返回值仍然是100
    public static int returnValue04() {
        int x = 1;
        try {
            return x / 0;
        } catch (Exception e) {
            x = 100;
            return x;
        } finally {
            x = 1000;
        }
    }

    // 1, 正常执行
    public static int returnValue05() {
        int x = 1;
        try {
            return x;
        } catch (Exception e) {
            x = 100;
            return x;
        } finally {
            System.out.println("finally之中就不要去赋值return了");
        }
    }

    // 1, 正常执行, 如果没有特殊需要就不要finally了
    public static int returnValue06() {
        int x = 1;
        try {
            return x;
        } catch (Exception e) {
            x = 100;
            return x;
        }
    }

    // 100, 发生错误, 但是仍然可以返回想要的东西
    public static int returnValue07() {
        int x = 1;
        try {
            return x / 0;
        } catch (Exception e) {
            x = 100;
            return x;
        }
    }

}
