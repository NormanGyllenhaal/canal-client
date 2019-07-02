package top.javatool.canal.client.spring.boot.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = CanalSimpleProperties.CANAL_PREFIX)
public class CanalKafkaProperties extends CanalProperties {


    private Integer partition = 0;

    private String groupId;


    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
