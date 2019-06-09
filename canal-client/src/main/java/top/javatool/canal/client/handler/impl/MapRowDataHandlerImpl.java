package top.javatool.canal.client.handler.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import top.javatool.canal.client.factory.IModelFactory;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.handler.RowDataHandler;


import java.util.List;
import java.util.Map;

public class MapRowDataHandlerImpl implements RowDataHandler<List<Map<String, String>>> {



    private IModelFactory<Map<String,String>> modelFactory;


    public MapRowDataHandlerImpl(IModelFactory<Map<String, String>> modelFactory) {
        this.modelFactory = modelFactory;
    }

    @Override
    public <R> void handlerRowData(List<Map<String, String>> list, EntryHandler<R> entryHandler, CanalEntry.EventType eventType) throws Exception{
        if (entryHandler != null) {
            switch (eventType) {
                case INSERT:
                    R entry  = modelFactory.newInstance(entryHandler, list.get(0));
                    entryHandler.insert(entry);
                    break;
                case UPDATE:
                    R before = modelFactory.newInstance(entryHandler, list.get(1));
                    R after = modelFactory.newInstance(entryHandler, list.get(0));
                    entryHandler.update(before, after);
                    break;
                case DELETE:
                    R o = modelFactory.newInstance(entryHandler, list.get(0));
                    entryHandler.delete(o);
                    break;
                default:
                    break;
            }
        }
    }
}
