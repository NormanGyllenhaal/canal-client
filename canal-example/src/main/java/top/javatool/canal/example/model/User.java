package top.javatool.canal.example.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 测试实体类
 */
@Table(name = "t_user")
public class User implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;


    /**
     * 用户性别 1 男性 2 女性
     */

    private Integer gender;

    /**
     * 用户所在国家id
     */
    @Column(name = "country_id")
    private Integer countryId;


    /**
     * 用户出生日期
     */
    private Date birthday;


    /**
     * 用户创建时间
     */
    @Column(name = "create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}