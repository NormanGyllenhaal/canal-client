package top.javatool.canal.client.spring.boot.autoconfigure;


import com.alibaba.otter.canal.protocol.CanalEntry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import top.javatool.canal.client.client.ClusterCanalClient;
import top.javatool.canal.client.client.SimpleCanalClient;
import top.javatool.canal.client.factory.EntryColumnModelFactory;
import top.javatool.canal.client.handler.EntryHandler;
import top.javatool.canal.client.handler.MessageHandler;
import top.javatool.canal.client.handler.RowDataHandler;
import top.javatool.canal.client.handler.impl.AsyncMessageHandlerImpl;
import top.javatool.canal.client.handler.impl.RowDataHandlerImpl;
import top.javatool.canal.client.handler.impl.SyncMessageHandlerImpl;
import top.javatool.canal.client.spring.boot.properties.CanalClusterProperties;
import top.javatool.canal.client.spring.boot.properties.CanalProperties;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Configuration
@EnableConfigurationProperties(CanalClusterProperties.class)
@ConditionalOnBean(value = {EntryHandler.class})
@ConditionalOnProperty(value = CanalProperties.CANAL_MODE, havingValue = "cluster")
@Import(ThreadPoolAutoConfiguration.class)
public class ClusterClientAutoConfiguration {


    private CanalClusterProperties canalClusterProperties;


    public ClusterClientAutoConfiguration(CanalClusterProperties canalClusterProperties) {
        this.canalClusterProperties = canalClusterProperties;
    }

    @Bean
    public RowDataHandler<CanalEntry.RowData> rowDataHandler() {
        return new RowDataHandlerImpl(new EntryColumnModelFactory());
    }

    @Bean
    @ConditionalOnProperty(value = CanalProperties.CANAL_ASYNC, havingValue = "true", matchIfMissing = true)
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
    public ClusterCanalClient simpleCanalClient(MessageHandler messageHandler) {
        return ClusterCanalClient.Builder.builder().
                canalServers(canalClusterProperties.getCanalServers())
                .destination(canalClusterProperties.getDestination())
                .userName(canalClusterProperties.getUserName())
                .messageHandler(messageHandler)
                .password(canalClusterProperties.getPassword())
                .batchSize(canalClusterProperties.getBatchSize())
                .filter(canalClusterProperties.getFilter())
                .timeout(canalClusterProperties.getTimeout())
                .unit(canalClusterProperties.getUnit()).build();
    }
}
