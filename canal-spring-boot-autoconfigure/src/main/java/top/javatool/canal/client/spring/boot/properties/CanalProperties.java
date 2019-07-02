package top.javatool.canal.client.spring.boot.properties;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.TimeUnit;

public class CanalProperties {


    public static final String CANAL_PREFIX = "canal";


    public static final String CANAL_ASYNC = CANAL_PREFIX + "." + "async";


    public static final String CANAL_MODE = CANAL_PREFIX + "." + "mode";

    /**
     * simple,cluster,zookeeper,kafka,rocketMQ
     */
    private String mode;


    private Boolean async;



    private String server;



    private String destination;


    private String filter = StringUtils.EMPTY;

    private Integer batchSize = 1;

    private Long timeout = 1L;

    private TimeUnit unit = TimeUnit.SECONDS;


    public Boolean getAsync() {
        return async;
    }

    public void setAsync(Boolean async) {
        this.async = async;
    }


    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }


    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
