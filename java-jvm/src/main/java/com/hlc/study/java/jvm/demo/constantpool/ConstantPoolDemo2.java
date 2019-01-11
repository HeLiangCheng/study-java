package com.hlc.study.java.jvm.demo.constantpool;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *@ClassName ConstantPoolDemo2
 *@Description 借助CGlib实现方法区内存溢出
 *@Author Liang
 *@Date 2019/1/10 16:16
 *@Version 1.0
 **/

/**
 当前的很多主流框架，如Spring、Hibernate，在对类进行增强时，都会使用到CGLib这类字节码技术，增强的类越多，就需要越大的方法区来保证动态生成的Class可以加载入内存。另外，JVM上的动态语言（例如Groovy等）通常都会持续创建类来实现语言的动态性，随着这类语言的流行，也越来越容易遇到方法区溢出场景。

 该方法在JDK1.7中允许报错OutOfMemoryError，但是在JDK1.8下可以正常运行，因为jdk1.8已结去掉了永久代，当然 -XX:PermSize=2m -XX:MaxPermSize=2m 也将被忽略。jdk1.8 使用元空间（ Metaspace ）替代了永久代（ PermSize ），因此我们可以在 1.8 中指定 Metaspace 的大小模拟上述两种情况
 */
public class ConstantPoolDemo2 {

    static class OOMObject {
        private byte[] by = new byte[1024];
    }

    public static void main(String[] arge) {
        List<Enhancer> enhancerList = new ArrayList<Enhancer>();
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
            enhancerList.add(enhancer);
            System.out.println("enhancer 动态类已经创建");
        }
    }
}
