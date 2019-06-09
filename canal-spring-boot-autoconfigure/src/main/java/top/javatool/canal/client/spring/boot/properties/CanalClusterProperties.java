package top.javatool.canal.client.spring.boot.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = CanalSimpleProperties.CANAL_PREFIX)
public class CanalClusterProperties extends CanalProperties {

    private String canalServers;


    private String destination;


    private String userName;


    private String password;


    public String getCanalServers() {
        return canalServers;
    }

    public void setCanalServers(String canalServers) {
        this.canalServers = canalServers;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
