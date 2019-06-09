package top.javatool.canal.client.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;

import org.apache.commons.lang.StringUtils;
import top.javatool.canal.client.handler.MessageHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author yang peng
 * @date 2019/3/2619:12
 */
public class ZookeeperClusterCanalClient extends AbstractCanalClient {



    public static Builder builder(){
        return Builder.builder();
    }



    public static final class Builder {
        private String filter = StringUtils.EMPTY;
        private Integer batchSize = 1;
        private Long timeout = 1L;
        private TimeUnit unit = TimeUnit.SECONDS;
        private String zkServers;
        private String destination;
        private String userName;
        private String password;
        private MessageHandler messageHandler;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder zkServers(String zkServers) {
            this.zkServers = zkServers;
            return this;
        }


        public Builder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder filter(String filter) {
            this.filter = filter;
            return this;
        }

        public Builder batchSize(Integer batchSize) {
            this.batchSize = batchSize;
            return this;
        }

        public Builder timeout(Long timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder unit(TimeUnit unit) {
            this.unit = unit;
            return this;
        }

        public Builder messageHandler(MessageHandler messageHandler) {
            this.messageHandler = messageHandler;
            return this;
        }

        public ZookeeperClusterCanalClient build() {
            CanalConnector connector = CanalConnectors.newClusterConnector(zkServers, destination, userName, password);
            ZookeeperClusterCanalClient zookeeperClusterCanalClient = new ZookeeperClusterCanalClient();
            zookeeperClusterCanalClient.setMessageHandler(messageHandler);
            zookeeperClusterCanalClient.setConnector(connector);
            zookeeperClusterCanalClient.filter = this.filter;
            zookeeperClusterCanalClient.unit = this.unit;
            zookeeperClusterCanalClient.batchSize = this.batchSize;
            zookeeperClusterCanalClient.timeout = this.timeout;
            return zookeeperClusterCanalClient;
        }
    }
}
