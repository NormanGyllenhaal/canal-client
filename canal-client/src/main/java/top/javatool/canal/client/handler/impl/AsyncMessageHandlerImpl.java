package top.javatool.canal.client.handler.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import top.javatool.canal.client.handler.AbstractMessageHandler;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.handler.RowDataHandler;


import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author yang peng
 * @date 2019/3/2921:40
 */
public class AsyncMessageHandlerImpl extends AbstractMessageHandler {


    private ExecutorService executor;


    public AsyncMessageHandlerImpl(List<? extends EntryHandler> entryHandlers, RowDataHandler<CanalEntry.RowData> rowDataHandler, ExecutorService executor) {
        super(entryHandlers, rowDataHandler);
        this.executor = executor;
    }

    @Override
    public void handleMessage(Message message) {
        executor.execute(() -> super.handleMessage(message));
    }
}
