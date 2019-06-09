## 易用的canal 客户端 easy canal client

### 介绍
canal 阿里巴巴mysql数据库binlog的增量订阅&消费组件,https://github.com/alibaba/canal  
使用前请先了解canal  
canal 自身提供了简单的客户端，数据格式较为复杂，处理消费数据要每次进行格式转换，确实不太方便。
为了方便给业务使用，提供一种直接能获取实体对象的方式来进行消费才更方便。

### 如何使用
spring boot 方式 
maven 依赖
```xml
 <dependency>
     <groupId>top.javatool</groupId>
     <artifactId>canal-spring-boot-autoconfigure</artifactId>
     <version>1.0.0-RELEASE</version>
 </dependency>
```
配置说明

|属性|描述|默认值|
|:----    |:---------------------    |:------- |
|canal.mode |canal 客户端类型 目前支持4种类型 simple,cluster,zookeeper,kafka |simple
|canal.filter| canal过滤的表名称，如配置则只订阅配置的表|""
|canal.batchSize| 消息的数量，超过该次数将进行一次消费 |1(个)
|canal.timeout  |消费的时间间隔(ms)|1000ms
|canal.canalServers     |服务地址    |空
|canal.destination |canal 的instance 名称    |空
|canal.userName     |canal 的用户名    |空
|canal.password |canal 的密码     |空
|canal.zkServers |zookeeper 地址，zookeeper canal 客户端需配置   |空
|kafka.servers |kafka地址，kafka canal 客户端  |空
|kafka.topic |kafka topic，为canal的instance名称 kafka canal 客户端    |空
|kafka.groupId  |kafka groupId 消费者订阅消息时可使用，kafka canal 客户端 |空
|canal.async |是否是异步消费，异步消费时，消费时异常将导致消息不会回滚，也不保证顺序性 |true

## 订阅数据库的增删改操作
实现EntryHandler<T> 接口，泛型为想要订阅的数据库表的实体对象，下面以一个user对象为例
```java
public class UserHandler implements EntryHandler<User>{

}
```
该接口的方法为 java 8 的 default 方法，方法可以不实现，如果只要监听增加操作，只实现增加方法即可
例如，增加操作返回增加的对象信息  
```java
   @Override
    public void insert(User user) {
	   //你的逻辑
        log.info("context {}",CanalContext.getModel());
        log.info("新增 {}",user);
    }
```
更新操作  
```java
@Override
    public void update(User before, User after) {
	    //你的逻辑
        log.info("context {}",CanalContext.getModel());
        log.info("更新 {} {}",before,after);
    }
```
对于更新操作来讲，before 中的属性只包含变更的属性，after 包含所有属性，通过对比可发现那些属性更新了
删除操作,user为删除的对象信息  
```
@Override
    public void delete(User user) {
	    //你的逻辑
        log.info("context {}",CanalContext.getModel());
        log.info("删除 {}",user);
    }
```
如果你想获取除实体类信息外的其他信息，可以使用
```java
CanalModel canal = CanalContext.getModel();
```
