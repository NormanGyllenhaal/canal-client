package top.javatool.canal.client.handler;

/**
 * @author yang peng
 * @date 2019/3/2915:46
 */
public interface EntryHandler<T> {



    default void insert(T t) {

    }


    default void update(T before, T after) {

    }


    default void delete(T t) {

    }
}
