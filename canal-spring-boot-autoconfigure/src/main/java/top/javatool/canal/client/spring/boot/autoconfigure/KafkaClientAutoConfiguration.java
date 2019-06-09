package top.javatool.canal.client.spring.boot.autoconfigure;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import top.javatool.canal.client.client.KafkaCanalClient;
import top.javatool.canal.client.factory.MapColumnModelFactory;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.handler.MessageHandler;
import top.javatool.canal.client.handler.RowDataHandler;
import top.javatool.canal.client.handler.impl.AsyncFlatMessageHandlerImpl;
import top.javatool.canal.client.handler.impl.MapRowDataHandlerImpl;
import top.javatool.canal.client.handler.impl.SyncFlatMessageHandlerImpl;
import top.javatool.canal.client.spring.boot.properties.CanalKafkaProperties;
import top.javatool.canal.client.spring.boot.properties.CanalProperties;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Configuration
@EnableConfigurationProperties(CanalKafkaProperties.class)
@ConditionalOnBean(value = {EntryHandler.class})
@ConditionalOnProperty(value = CanalProperties.CANAL_MODE, havingValue = "kafka")
@Import(ThreadPoolAutoConfiguration.class)
public class KafkaClientAutoConfiguration {


    private CanalKafkaProperties canalKafkaProperties;


    public KafkaClientAutoConfiguration(CanalKafkaProperties canalKafkaProperties) {
        this.canalKafkaProperties = canalKafkaProperties;
    }


    @Bean
    public RowDataHandler<List<Map<String, String>>> rowDataHandler() {
        return new MapRowDataHandlerImpl(new MapColumnModelFactory());
    }

    @Bean
    @ConditionalOnProperty(value = CanalProperties.CANAL_ASYNC, havingValue = "true")
    public MessageHandler messageHandler(RowDataHandler<List<Map<String, String>>> rowDataHandler, List<EntryHandler> entryHandlers,
                                         ExecutorService executorService) {
        return new AsyncFlatMessageHandlerImpl(entryHandlers,rowDataHandler,executorService);
    }


    @Bean
    @ConditionalOnProperty(value = CanalProperties.CANAL_ASYNC, havingValue = "false")
    public MessageHandler messageHandler(RowDataHandler<List<Map<String, String>>> rowDataHandler, List<EntryHandler> entryHandlers) {
        return new SyncFlatMessageHandlerImpl(entryHandlers, rowDataHandler);
    }


    @Bean(initMethod = "start", destroyMethod = "stop")
    public KafkaCanalClient zookeeperClusterCanalClient(MessageHandler messageHandler) {
        return KafkaCanalClient.builder().servers(canalKafkaProperties.getServers())
                .groupId(canalKafkaProperties.getGroupId())
                .topic(canalKafkaProperties.getTopic())
                .messageHandler(messageHandler)
                .batchSize(canalKafkaProperties.getBatchSize())
                .filter(canalKafkaProperties.getFilter())
                .timeout(canalKafkaProperties.getTimeout())
                .unit(canalKafkaProperties.getUnit())
                .build();
    }

}
