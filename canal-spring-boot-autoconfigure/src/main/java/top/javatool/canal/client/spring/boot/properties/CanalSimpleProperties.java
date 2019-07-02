package top.javatool.canal.client.spring.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = CanalSimpleProperties.CANAL_PREFIX)
public class CanalSimpleProperties extends CanalProperties {



    private String userName;

    private String password;


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
