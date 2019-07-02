package top.javatool.canal.client.spring.boot.autoconfigure;


import com.alibaba.otter.canal.protocol.CanalEntry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import top.javatool.canal.client.client.ZookeeperClusterCanalClient;
import top.javatool.canal.client.factory.EntryColumnModelFactory;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.handler.MessageHandler;
import top.javatool.canal.client.handler.RowDataHandler;
import top.javatool.canal.client.handler.impl.AsyncMessageHandlerImpl;
import top.javatool.canal.client.handler.impl.RowDataHandlerImpl;
import top.javatool.canal.client.handler.impl.SyncMessageHandlerImpl;
import top.javatool.canal.client.spring.boot.properties.CanalSimpleProperties;
import top.javatool.canal.client.spring.boot.properties.CanalProperties;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Configuration
@EnableConfigurationProperties(CanalSimpleProperties.class)
@ConditionalOnBean(value = {EntryHandler.class})
@ConditionalOnProperty(value = CanalProperties.CANAL_MODE, havingValue = "zk")
@Import(ThreadPoolAutoConfiguration.class)
public class ZookeeperClientAutoConfiguration {


    private CanalSimpleProperties canalSimpleProperties;


    public ZookeeperClientAutoConfiguration(CanalSimpleProperties canalSimpleProperties) {
        this.canalSimpleProperties = canalSimpleProperties;
    }

    @Bean
    public RowDataHandler<CanalEntry.RowData> rowDataHandler() {
        return new RowDataHandlerImpl(new EntryColumnModelFactory());
    }

    @Bean
    @ConditionalOnProperty(value = CanalProperties.CANAL_ASYNC, havingValue = "true",matchIfMissing = true)
    public MessageHandler messageHandler(RowDataHandler<CanalEntry.RowData> rowDataHandler, List<EntryHandler> entryHandlers,
                                         ExecutorService executorService) {
        return new AsyncMessageHandlerImpl(entryHandlers, rowDataHandler, executorService);
    }


    @Bean
    @ConditionalOnProperty(value = CanalProperties.CANAL_ASYNC, havingValue = "false")
    public MessageHandler messageHandler(RowDataHandler<CanalEntry.RowData> rowDataHandler, List<EntryHandler> entryHandlers) {
        return new SyncMessageHandlerImpl(entryHandlers, rowDataHandler);
    }


    @Bean(initMethod = "start", destroyMethod = "stop")
    public ZookeeperClusterCanalClient zookeeperClusterCanalClient(MessageHandler messageHandler) {
        return ZookeeperClusterCanalClient.builder()
                .zkServers(canalSimpleProperties.getServer())
                .destination(canalSimpleProperties.getDestination())
                .userName(canalSimpleProperties.getUserName())
                .password(canalSimpleProperties.getPassword())
                .batchSize(canalSimpleProperties.getBatchSize())
                .filter(canalSimpleProperties.getFilter())
                .timeout(canalSimpleProperties.getTimeout())
                .unit(canalSimpleProperties.getUnit())
                .messageHandler(messageHandler)
                .build();
    }

}
