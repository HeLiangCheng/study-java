##disruptor框架

###disruptor框架介绍
* Martin-Fowler在网站上写了一篇LMAX架构的文章，在文章中他介绍了LMAX是一种新型零售金融交易平台，它能够以很低的延迟产生大量交易。这个系统是建立在JVM平台上，其核心是一个业务逻辑处理器，它能够在一个线程里每秒处理6百万订单。业务逻辑处理器完全是运行在内存中，使用事件源驱动方式。业务逻辑处理器的核心是Disruptor。
* Disruptor它是一个开源的并发框架，并获得2011 Duke’s 程序框架创新奖，能够在无锁的情况下实现网络的Queue并发操作。
* Disruptor是一个高性能的异步处理框架，或者可以认为是最快的消息框架（轻量的JMS），也可以认为是一个观察者模式的实现，或者事件监听模式的实现。 

###disruptor框架使用
#### 版本介绍
* 目前我们使用disruptor已经更新到了3.x版本，比之前的2.x版本性能更加的优秀，提供更多的API使用方式。
* 下载disruptor-3.3.2.jar引入我们的项目既可以开始disruptor之旅。
* 在使用之前，首先说明disruptor主要功能加以说明，你可以理解为他是一种高效的"生产者-消费者"模型。也就性能远远高于传统的BlockingQueue容器。具测试Disruptor要比ArrayBlockLinked效率高一个数量级。而且Disruptor每秒钟可以容纳600万订单
* 官方学习网站：http://ifeve.com/disruptor-getting-started/

#### 开发步骤
在Disruptor中，我们想实现hello world 需要如下几步骤：
第一：建立一个Event类
第二：建立一个工厂Event类，用于创建Event类实例对象
第三：需要有一个监听事件类，用于处理数据（Event类）
第四：我们需要进行测试代码编写。实例化Disruptor实例，配置一系列参数。然后我们对Disruptor实例绑定监听事件类，接受并处理数据。
第五：在Disruptor中，真正存储数据的核心叫做RingBuffer，我们通过Disruptor实例拿到它，然后把数据生产出来，把数据加入到RingBuffer的实例对象中即可。


### Disruptor术语说明

+ RingBuffer: 被看作Disruptor最主要的组件，然而从3.0开始RingBuffer仅仅负责存储和更新在Disruptor中流通的数据。对一些特殊的使用场景能够被用户(使用其他数据结构)完全替代。
+ Sequence: Disruptor使用Sequence来表示一个特殊组件处理的序号。和Disruptor一样，每个消费者(EventProcessor)都维持着一个Sequence。大部分的并发代码依赖这些Sequence值的运转，因此Sequence支持多种当前为AtomicLong类的特性。
+ Sequencer: 这是Disruptor真正的核心。实现了这个接口的两种生产者（单生产者和多生产者）均实现了所有的并发算法，为了在生产者和消费者之间进行准确快速的数据传递。
+ SequenceBarrier: 由Sequencer生成，并且包含了已经发布的Sequence的引用，这些的Sequence源于Sequencer和一些独立的消费者的Sequence。它包含了决定是否有供消费者来消费的Event的逻辑。
+ WaitStrategy：决定一个消费者将如何等待生产者将Event置入Disruptor。
+ Event：从生产者到消费者过程中所处理的数据单元。Disruptor中没有代码表示Event，因为它完全是由用户定义的。
+ EventProcessor：主要事件循环，处理Disruptor中的Event，并且拥有消费者的Sequence。它有一个实现类是BatchEventProcessor，包含了event loop有效的实现，并且将回调到一个EventHandler接口的实现对象。
+ EventHandler：由用户实现并且代表了Disruptor中的一个消费者的接口。
+ Producer：由用户实现，它调用RingBuffer来插入事件(Event)，在Disruptor中没有相应的实现代码，由用户实现。
+ WorkProcessor：确保每个sequence只被一个processor消费，在同一个WorkPool中的处理多个WorkProcessor不会消费同样的sequence。
+ WorkerPool：一个WorkProcessor池，其中WorkProcessor将消费Sequence，所以任务可以在实现WorkHandler接口的worker吃间移交
+ LifecycleAware：当BatchEventProcessor启动和停止时，于实现这个接口用于接收通知。

### RingBuffer深入理解


### 应用场景
+ 生产者消费者模式
  1. 简单生产则消费者 base
  2. 使用EventProcessor消息处理器 eventprocessor
       >  BatchEventProcessor 多线程并发执行,不同线程执行不同是不同的event 
  3. 使用WorkerPool消息处理器  workpool
  4. 多处理任务（P ——>(C1,C2)——>C3） multihandler
  5. 多生产者，多消费者  multiproducerconsumer
   
