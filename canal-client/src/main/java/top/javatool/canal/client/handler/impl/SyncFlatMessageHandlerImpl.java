package top.javatool.canal.client.handler.impl;

import com.alibaba.otter.canal.protocol.FlatMessage;
import top.javatool.canal.client.handler.AbstractFlatMessageHandler;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.handler.RowDataHandler;


import java.util.List;
import java.util.Map;

public class SyncFlatMessageHandlerImpl extends AbstractFlatMessageHandler {



    public SyncFlatMessageHandlerImpl(List<? extends EntryHandler> entryHandlers, RowDataHandler<List<Map<String, String>>> rowDataHandler) {
        super(entryHandlers, rowDataHandler);
    }

    @Override
    public void handleMessage(FlatMessage flatMessage) {
        super.handleMessage(flatMessage);
    }
}
