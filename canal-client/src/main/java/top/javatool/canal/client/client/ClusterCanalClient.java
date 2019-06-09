package top.javatool.canal.client.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;

import org.apache.commons.lang.StringUtils;
import top.javatool.canal.client.handler.MessageHandler;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClusterCanalClient extends AbstractCanalClient {




    public static final class Builder {
        private String filter = StringUtils.EMPTY;
        private Integer batchSize = 1;
        private Long timeout = 1L;
        private TimeUnit unit = TimeUnit.SECONDS;
        private String canalServers;
        private String destination;
        private String userName;
        private String password;
        private MessageHandler messageHandler;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder canalServers(String canalServers) {
            this.canalServers = canalServers;
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

        public ClusterCanalClient build() {
            List<InetSocketAddress> list = Stream.of(canalServers.split(",")).map(s -> {
                String[] split = s.split(":");
                return new InetSocketAddress(split[0], Integer.parseInt(split[1]));
            }).collect(Collectors.toList());
            CanalConnector connector = CanalConnectors.newClusterConnector(list, destination, userName, password);
            ClusterCanalClient clusterCanalClient = new ClusterCanalClient();
            clusterCanalClient.setMessageHandler(messageHandler);
            clusterCanalClient.setConnector(connector);
            clusterCanalClient.filter = this.filter;
            clusterCanalClient.unit = this.unit;
            clusterCanalClient.batchSize = this.batchSize;
            clusterCanalClient.timeout = this.timeout;
            return clusterCanalClient;
        }
    }
}
