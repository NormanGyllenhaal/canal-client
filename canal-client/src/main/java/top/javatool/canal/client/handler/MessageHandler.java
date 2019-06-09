package top.javatool.canal.client.handler;

/**
 * @author yang peng
 * @date 2019/3/2622:06
 */
public interface MessageHandler<T> {



     void handleMessage(T t);
}
