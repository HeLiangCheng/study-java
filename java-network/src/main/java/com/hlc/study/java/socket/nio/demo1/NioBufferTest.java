package com.hlc.study.java.socket.nio.demo1;

import java.nio.IntBuffer;

/**
    *@ClassName BufferTest
    *@Description Buffer 测试类
    *@Author Liang
    *@Date 2019/1/17 15:10
    *@Version 1.0
**/

/*
    Buffer分类： ByteBuffer CharBuffer IntBuffer

   1.基本操作
        创建： IntBuffer.allocate(int capacity)
        存放数据：       put(数据)
                         put(index,数据)
        获取容量：       capacity()
        获取上限：       limit()
        复位：           flip()
        获取第index位置上的数据： get(index)
        包裹一个数组返回缓冲区：wrap

    2.warp详解
    wrap方法会包裹一个数组: 一般这种用法不会先初始化缓存对象的长度，因为没有意义，最后还会被wrap所包裹的数组覆盖掉。 并且wrap方法修改缓冲区对象的时候，数组本身也会跟着发生变化。

    3.其他方法
        duplicate   复制
        remaining

 */
public class NioBufferTest {

    public static void main(String[] args){

        /*
        //分配大小
        IntBuffer buffer = IntBuffer.allocate(100);
        buffer.put(12);
        buffer.put(13);
        buffer.put(14);

        System.out.println("容量："+ buffer.capacity());
        System.out.println("获取上限："+ buffer.limit());
        System.out.println("第2号位置："+ buffer.get(2));
        System.out.println("复位之前的buf:"+buffer);
        buffer.flip();
        System.out.println("复位之后的buf:："+ buffer);
        System.out.println("--------------------------");
        System.out.println("获取下标为1的元素："+buffer.get(1));
        System.out.println("get(index)方法,position位置不变"+buffer);
        buffer.put(1, 4);
        System.out.println("put方法之后："+buffer);
        System.out.println("获取下标为1的元素："+buffer.get(1));

        //遍历所有数据
        for(int i=0;i<buffer.limit();i++){
            //调用get方法
            System.out.println(buffer.get(i));
        }
        System.out.println("buf对象遍历之后："+buffer);
        */

        /*
        //warp方法
        int[] num={2,3,4,5,6,7};
        IntBuffer bf2=IntBuffer.wrap(num);
        System.out.println(bf2);
        for(int i=0;i<bf2.limit();i++){
            System.out.print(bf2.get(i)+" | ");
        }
        System.out.println();
        IntBuffer bf3=IntBuffer.wrap(num,0,2);
        System.out.println(bf3);
        for(int i=0;i<bf3.limit();i++){
            System.out.print(bf3.get(i)+" - ");
        }
       */

        IntBuffer buf1=IntBuffer.allocate(10);
        int[] arr=new int[]{1,2,3,4,5};
        buf1.put(arr);
        System.out.println(buf1);
        for(int i=0;i<buf1.limit();i++){
            System.out.print(buf1.get(i)+" | ");
        }
        System.out.println();
        //一种复制
        IntBuffer buf2=buf1.duplicate();
        System.out.println(buf2);
        for(int i=0;i<buf2.limit();i++){
            System.out.print(buf2.get(i)+" | ");
        }
        System.out.println();
        //设置buf1的位置属性
        buf1.position(6);
        System.out.println(buf1);
        buf1.flip();
        System.out.println(buf1);
        System.out.println("可读数据为："+buf1.remaining());

        //将缓冲区的数据放入arra2数组中
        int[] arr2=new int[buf1.remaining()];
        buf1.get(arr2);
        for(int n=0;n<buf1.limit();n++){
            System.out.print(buf1.get(n));
        }

    }
}
