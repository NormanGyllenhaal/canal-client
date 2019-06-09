package top.javatool.canal.client.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import top.javatool.canal.client.context.CanalContext;
import top.javatool.canal.client.model.CanalModel;
import top.javatool.canal.client.util.HandlerUtil;


import java.util.List;
import java.util.Map;

/**
 * @author yang peng
 * @date 2019/3/2921:38
 */
public abstract class AbstractMessageHandler implements MessageHandler<Message> {



    private Map<String, EntryHandler> tableHandlerMap;



    private RowDataHandler<CanalEntry.RowData> rowDataHandler;


    public  AbstractMessageHandler(List<? extends EntryHandler> entryHandlers, RowDataHandler<CanalEntry.RowData> rowDataHandler) {
        this.tableHandlerMap = HandlerUtil.getTableHandlerMap(entryHandlers);
        this.rowDataHandler = rowDataHandler;
    }

    @Override
    public  void handleMessage(Message message) {
        List<CanalEntry.Entry> entries = message.getEntries();
        for (CanalEntry.Entry entry : entries) {
            if (entry.getEntryType().equals(CanalEntry.EntryType.ROWDATA)) {
                try {
                    EntryHandler<?> entryHandler = HandlerUtil.getEntryHandler(tableHandlerMap, entry.getHeader().getTableName());
                    if(entryHandler!=null){
                        CanalModel model = CanalModel.Builder.builder().id(message.getId()).table(entry.getHeader().getTableName())
                                .executeTime(entry.getHeader().getExecuteTime()).database(entry.getHeader().getSchemaName()).build();
                        CanalContext.setModel(model);
                        CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                        List<CanalEntry.RowData> rowDataList = rowChange.getRowDatasList();
                        CanalEntry.EventType eventType = rowChange.getEventType();
                        for (CanalEntry.RowData rowData : rowDataList) {
                            rowDataHandler.handlerRowData(rowData,entryHandler,eventType);
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                }finally {
                   CanalContext.removeModel();
                }

            }
        }
    }


}
