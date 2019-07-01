[![Build Status](https://travis-ci.org/NormanGyllenhaal/canal-client.svg?branch=master)](https://travis-ci.org/NormanGyllenhaal/canal-client)
## 易用的canal 客户端 easy canal client

### 介绍
canal 是阿里巴巴mysql数据库binlog的增量订阅&消费组件  
使用该客户端前请先了解canal,https://github.com/alibaba/canal  
canal 自身提供了简单的客户端，如果要转换为数据库的实体对象，处理消费数据要每次进行对象转换。
该客户端直接将canal的数据原始类型转换为各个数据表的实体对象，并解耦数据的增删改操作，方便给业务使用。

### 要求
java8+

### 特性
* 解耦单表增删操作
* simple,cluster,zookeeper,kafka客户端支持
* 同步异步处理支持
* spring boot 开箱即用

### 如何使用
spring boot 方式 
maven 依赖
```xml
  <dependency>
      <groupId>top.javatool</groupId>
      <artifactId>canal-spring-boot-starter</artifactId>
      <version>1.1.0-RELEASE</version>
  </dependency>
```
java 方式
```xml
 <dependency>
       <groupId>top.javatool</groupId>
       <artifactId>canal-client</artifactId>
       <version>1.1.0-RELEASE</version>
 </dependency>
```
配置说明

|属性|描述|默认值|
|:----    |:---------------------    |:------- |
|canal.mode |canal 客户端类型 目前支持4种类型 simple,cluster,zookeeper,kafka(kafka 目前支持flatMessage 格式)|simple
|canal.filter| canal过滤的表名称，如配置则只订阅配置的表|""
|canal.batchSize| 消息的数量，超过该次数将进行一次消费 |1(个)
|canal.timeout  |消费的时间间隔(s)|1s
|canal.canalServers     |服务地址    |null
|canal.destination |canal 的instance 名称    |null
|canal.userName     |canal 的用户名    |null
|canal.password |canal 的密码     |null
|canal.zkServers |zookeeper 地址，zookeeper canal 客户端需配置   |null
|canal.servers |kafka地址，kafka canal 客户端  |null
|canal.topic |kafka topic，为canal的instance名称 kafka canal 客户端    |null
|canal.groupId  |kafka groupId 消费者订阅消息时可使用，kafka canal 客户端 |null
|canal.async |是否是异步消费，异步消费时，消费时异常将导致消息不会回滚，也不保证顺序性 |true

### 订阅数据库的增删改操作
实现EntryHandler<T> 接口，泛型为想要订阅的数据库表的实体对象，
该接口的方法为 java 8 的 default 方法，方法可以不实现，如果只要监听增加操作，只实现增加方法即可  
下面以一个t_user表的user实体对象为例,
默认情况下，将使用实体对象的jpa 注解 @Table中的表名来转换为EntryHandler中的泛型对象，
```java
public class UserHandler implements EntryHandler<User>{

}
```
如果实体类没有使用jpa @Table的注解，也可以使用@CanalTable 注解在EntryHandler来标记表名，例如  
```java
@CanalTable(value = "t_user")
@Component
public class UserHandler implements EntryHandler<User>{

   /**
   *  新增操作
   * @param user
    */
   @Override
    public void insert(User user) {
	   //你的逻辑
        log.info("新增 {}",user);
    }
    /**
    * 对于更新操作来讲，before 中的属性只包含变更的属性，after 包含所有属性，通过对比可发现那些属性更新了
   * @param before
   * @param after
    */
    @Override
    public void update(User before, User after) {
    	//你的逻辑
        log.info("更新 {} {}",before,after);
    }
    /**
    *  删除操作
    * @param user
    */
    @Override
    public void delete(User user) {
       //你的逻辑
        log.info("删除 {}",user); 
   }
}
```
另外也支持统一的处理@CanalTable(value="all"),这样除去存在EntryHandler的表以外，其他所有表的处理将通过该处理器,统一转为Map<String, String>对象
```java
@CanalTable(value = "all")
@Component
public class DefaultEntryHandler implements EntryHandler<Map<String, String>> {
     @Override
        public void insert(Map<String, String> map) {
            logger.info("insert message  {}", map);
        }
    
        @Override
        public void update(Map<String, String> before, Map<String, String> after) {
            logger.info("update before {} ", before);
            logger.info("update after {}", after);
        }
    
        @Override
        public void delete(Map<String, String> map) {
            logger.info("delete  {}", map);
        }
}
```
如果你想获取除实体类信息外的其他信息，可以使用
```java
CanalModel canal = CanalContext.getModel();
```
具体使用可以查询项目demo 示例  
https://github.com/NormanGyllenhaal/canal-client/tree/master/canal-example
