package top.javatool.canal.client.handler.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import top.javatool.canal.client.handler.AbstractMessageHandler;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.handler.RowDataHandler;


import java.util.List;

/**
 * @author yang peng
 * @date 2019/3/2710:52
 */
public class SyncMessageHandlerImpl extends AbstractMessageHandler {


    public SyncMessageHandlerImpl(List<? extends EntryHandler> entryHandlers, RowDataHandler<CanalEntry.RowData> rowDataHandler) {
        super(entryHandlers, rowDataHandler);
    }

    @Override
    public void handleMessage(Message message) {
        super.handleMessage(message);
    }


}
