package com.hlc.study.java.netty.demo2;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
    *@ClassName MarshallingCodeCFactory
    *@Description todo
    *@Author Liang
    *@Date 2019/1/21 10:01
    *@Version 1.0
**/

public class MarshallingCodeCFactory {

    //构建Marshalling 编码解码器
    public static MarshallingDecoder buildMarshallingDecoder() {
         /*
         * 通过 Marshalling 工具类的 getProvidedMarshallerFactory 静态方法获取MarshallerFactory 实例,参数 serial 表示创建的是 Java 序列化工厂对象.它是由jboss-marshalling-serial 包提供
         */
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        //创建
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);

        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(factory, configuration);
        //provider： 提供商maxSize 单个对象最大尺寸
        int maxSize = 1024 << 2;
        MarshallingDecoder decoder = new MarshallingDecoder(provider, maxSize);
        return decoder;
    }

    //构建Marshalling 编码编码器
    public static MarshallingEncoder buildMarshallingEncoder() {
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(factory, configuration);
        MarshallingEncoder encoder = new MarshallingEncoder(provider);
        return encoder;
    }

}





















