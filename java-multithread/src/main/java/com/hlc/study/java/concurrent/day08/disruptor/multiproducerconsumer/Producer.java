package com.hlc.study.java.concurrent.day08.disruptor.multiproducerconsumer;

import com.lmax.disruptor.RingBuffer;

/**
    *@ClassName Producer
    *@Description todo
    *@Author Liang
    *@Date 2019/1/16 11:17
    *@Version 1.0
**/

public class Producer {

    private RingBuffer<Trade> ringbuffer;

    public Producer(RingBuffer<Trade> ringbuffer) {
        this.ringbuffer = ringbuffer;
    }

    /**
     * onData用来发布事件，每调用一次就发布一次事件
     * 它的参数会用过事件传递给消费者
     */
    public void onData(String data) {
        //1.可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
        long sequence = ringbuffer.next();
        try {
            //2.用上面的索引取出一个空的事件用于填充（获取该序号对应的事件对象）
            Trade trade = ringbuffer.get(sequence);
            //3.获取要通过事件传递的业务数据
            trade.setId(data);
            trade.setName("No00"+data);
            trade.setPrice(Math.random()*100);
            System.out.println("生产者 》id=" + trade.getId() + ",name=" + trade.getName() + ",price=" + trade.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4.发布事件
            ringbuffer.publish(sequence);
        }
    }

}
