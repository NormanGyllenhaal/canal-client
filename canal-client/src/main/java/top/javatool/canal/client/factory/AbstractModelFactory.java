package top.javatool.canal.client.factory;

import top.javatool.canal.client.enums.TableNameEnum;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.util.GenericUtil;
import top.javatool.canal.client.util.HandlerUtil;

public abstract class AbstractModelFactory<T> implements IModelFactory<T> {


    @Override
    public <R> R newInstance(EntryHandler entryHandler, T t) throws Exception {
        String canalTableName = HandlerUtil.getCanalTableName(entryHandler);
        if (TableNameEnum.ALL.name().toLowerCase().equals(canalTableName)) {
            return (R) t;
        }
        Class<R> tableClass = GenericUtil.getTableClass(entryHandler);
        if (tableClass != null) {
            return newInstance(tableClass, t);
        }
        return null;
    }


    abstract <R> R newInstance(Class<R> c, T t) throws Exception;
}
