package top.javatool.canal.client.model;

/**
 * @author yang peng
 * @date 2019/3/2711:21
 */
public class CanalModel {


    /**
     * 消息id
     */
    private long id;

    /**
     * 库名
     */
    private String database;

    /**
     * 表名
     */
    private String table;

    /**
     * binlog executeTime
     */
    private Long executeTime;

    /**
     * dml build timeStamp
     */
    private Long createTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }


    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CanalModel{");
        sb.append("id=").append(id);
        sb.append(", database='").append(database).append('\'');
        sb.append(", table='").append(table).append('\'');
        sb.append(", executeTime=").append(executeTime);
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }

    public static final class Builder {
        private long id;
        private String database;
        private String table;
        private Long executeTime;
        private Long createTime;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder database(String database) {
            this.database = database;
            return this;
        }

        public Builder table(String table) {
            this.table = table;
            return this;
        }

        public Builder executeTime(Long executeTime) {
            this.executeTime = executeTime;
            return this;
        }

        public Builder createTime(Long createTime) {
            this.createTime = createTime;
            return this;
        }

        public CanalModel build() {
            CanalModel canalModel = new CanalModel();
            canalModel.setId(id);
            canalModel.setDatabase(database);
            canalModel.setTable(table);
            canalModel.setExecuteTime(executeTime);
            canalModel.setCreateTime(createTime);
            return canalModel;
        }
    }
}
