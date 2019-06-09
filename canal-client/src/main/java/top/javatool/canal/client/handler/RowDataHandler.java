package top.javatool.canal.client.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * @author yang peng
 * @date 2019/3/2917:20
 */
public interface RowDataHandler<T> {


    <R> void handlerRowData(T t, EntryHandler<R> entryHandler, CanalEntry.EventType eventType) throws Exception;
}
