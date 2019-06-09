package top.javatool.canal.client.client;

import com.alibaba.otter.canal.client.kafka.KafkaCanalConnector;
import com.alibaba.otter.canal.protocol.FlatMessage;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.javatool.canal.client.handler.MessageHandler;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yang peng
 * @date 2019/3/2910:02
 */

public class KafkaCanalClient extends AbstractCanalClient {

    private Logger log = LoggerFactory.getLogger(KafkaCanalClient.class);


    public static Builder builder() {
        return Builder.builder();
    }


    @Override
    public void process() {
        KafkaCanalConnector connector = (KafkaCanalConnector) getConnector();
        MessageHandler messageHandler = getMessageHandler();
        while (flag) {
            try {
                connector.connect();
                connector.subscribe();
                while (flag) {
                    try {
                        List<FlatMessage> messages = connector.getFlatListWithoutAck(timeout, unit);
                        log.info("获取消息 {}", messages);
                        if (messages != null) {
                            for (FlatMessage flatMessage : messages) {
                                messageHandler.handleMessage(flatMessage);
                            }
                        }
                        connector.ack();
                    } catch (Exception e) {
                        log.error("canal 消费异常", e);
                    }
                }
            } catch (Exception e) {
                log.error("canal 连接异常", e);
            }
        }
        connector.unsubscribe();
        connector.disconnect();
    }


    public static final class Builder {
        private String filter = StringUtils.EMPTY;
        private Integer batchSize = 1;
        private Long timeout = 1L;
        private TimeUnit unit = TimeUnit.SECONDS;
        private String servers;
        private String topic;
        private Integer partition;
        private String groupId;
        private MessageHandler messageHandler;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder servers(String servers) {
            this.servers = servers;
            return this;
        }

        public Builder topic(String topic) {
            this.topic = topic;
            return this;
        }

        public Builder partition(Integer partition) {
            this.partition = partition;
            return this;
        }

        public Builder groupId(String groupId) {
            this.groupId = groupId;
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

        public KafkaCanalClient build() {
            KafkaCanalConnector connector = new KafkaCanalConnector(servers, topic, partition, groupId, batchSize, true);
            KafkaCanalClient kafkaCanalClient = new KafkaCanalClient();
            kafkaCanalClient.setMessageHandler(messageHandler);
            kafkaCanalClient.setConnector(connector);
            kafkaCanalClient.filter = this.filter;
            kafkaCanalClient.unit = this.unit;
            kafkaCanalClient.batchSize = this.batchSize;
            kafkaCanalClient.timeout = this.timeout;
            return kafkaCanalClient;
        }
    }
}
