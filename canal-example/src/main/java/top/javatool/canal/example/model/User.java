package top.javatool.canal.example.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "rc_user")
public class User implements Serializable {



	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 用户的邮箱账号
	 */
	@Column(name = "user_account")
	private String userAccount;

	/**
	 * 第三方平台的用户id
	 */
	@Column(name = "three_party_id")
	private String threePartyId;

	/**
	 * 第三方用户的邮箱
	 */
	@Column(name = "three_party_email")
	private String threePartyEmail;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 应用id
	 */
	@Column(name = "app_id")
	private Integer appId;

	/**
	 * 主页背景图片
	 */
	private String background;

	/**
	 * 用户头像
	 */
	@Column(name = "head_img")
	private String headImg;

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
	 * 用户所在的国家名称
	 */
	@Column(name = "country_name")
	private String countryName;

	/**
	 * 金币数量
	 */
	@Column(name = "gold_num")
	private BigDecimal goldNum;

	/**
	 * 语言id
	 */
	@Column(name = "language_id")
	private String languageId;

	/**
	 * 语言名称，多个名称用，分割
	 */
	@Column(name = "language_name")
	private String languageName;

	/**
	 * 用户的年龄
	 */

	private Integer age;

	/**
	 * 用户出生日期
	 */
	private Date birthday;

	/**
	 * 用户使用的平台类型1 ios 2 android
	 */
	@Column(name = "platform_type")
	private Integer platformType;

	/**
	 * 用户账号的登录类型 1 注册登录，2 facebook登录，3 ，google+登录
	 */
	private Integer type;

	/**
	 * 是否是付费用户
	 */
	@Column(name = "pay_status")
	private Integer payStatus;

	/**
	 * 当前账号的状态 1.可用，2 禁用 ，3 被举报
	 */

	private Integer status;

	/**
	 * 用户创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 用户信息的更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * 钻石数量
	 */
	private BigDecimal stone;

	/**
	 * 钻石版本号
	 */
	@Column(name = "stone_version")
	private Integer stoneVersion;


	private String introduce;

	/**
	 * 用户注册渠道
	 */
	@Column(name = "channel")
	private Integer channel;

	/**
	 * 设备语言id
	 */
	@Transient
	private Integer deviceLanguageId;


	/**
	 * 国家id
	 */
	@Transient
	private List<Integer> countryIds;

	public List<Integer> getCountryIds() {
		return countryIds;
	}

	public void setCountryIds(List<Integer> countryIds) {
		this.countryIds = countryIds;
	}

	/**
	 * 色情行为
	 */
	@Column(name = "eroticism_behavior")
	private Boolean eroticismBehavior;


	@Column(name = "sign_eroticism")
	private Boolean signEroticism;


	@Transient
	private Integer registerPlatformType;

	/**
	 * 用户赞数
	 */
	@Transient
	private Integer userPraise;

	/**
	 * 工作时间(注册距离今天的天数)
	 */
	@Transient
	private Integer workDay;

	@Transient
	private String countryGroupName;

	@Transient
	private String oldCountryName;

	@Transient
	private String groupName;

	@Transient
	private Integer userLevel;

	@Transient
	private String city;

	@Transient
	private Boolean goddess=false;

	@Transient
	private Boolean minuteGirl=false;

	public Boolean getMinuteGirl() {
		return minuteGirl;
	}

	public void setMinuteGirl(Boolean minuteGirl) {
		this.minuteGirl = minuteGirl;
	}

	private static final long serialVersionUID = 1L;

	/**
	 * 获取主键
	 *
	 * @return id - 主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置主键
	 *
	 * @param id 主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取用户的邮箱账号
	 *
	 * @return user_account - 用户的邮箱账号
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * 设置用户的邮箱账号
	 *
	 * @param userAccount 用户的邮箱账号
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * 获取第三方平台的用户id
	 *
	 * @return three_party_id - 第三方平台的用户id
	 */
	public String getThreePartyId() {
		return threePartyId;
	}

	/**
	 * 设置第三方平台的用户id
	 *
	 * @param threePartyId 第三方平台的用户id
	 */
	public void setThreePartyId(String threePartyId) {
		this.threePartyId = threePartyId;
	}

	/**
	 * 获取第三方用户的邮箱
	 *
	 * @return three_party_email - 第三方用户的邮箱
	 */
	public String getThreePartyEmail() {
		return threePartyEmail;
	}

	/**
	 * 设置第三方用户的邮箱
	 *
	 * @param threePartyEmail 第三方用户的邮箱
	 */
	public void setThreePartyEmail(String threePartyEmail) {
		this.threePartyEmail = threePartyEmail;
	}

	/**
	 * 获取用户密码
	 *
	 * @return password - 用户密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置用户密码
	 *
	 * @param password 用户密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取用户名
	 *
	 * @return user_name - 用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名
	 *
	 * @param userName 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取应用id
	 *
	 * @return app_id - 应用id
	 */
	public Integer getAppId() {
		return appId;
	}

	/**
	 * 设置应用id
	 *
	 * @param appId 应用id
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	/**
	 * 获取主页背景图片
	 *
	 * @return background - 主页背景图片
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * 设置主页背景图片
	 *
	 * @param background 主页背景图片
	 */
	public void setBackground(String background) {
		this.background = background;
	}

	/**
	 * 获取用户头像
	 *
	 * @return head_img - 用户头像
	 */
	public String getHeadImg() {
		return headImg;
	}

	/**
	 * 设置用户头像
	 *
	 * @param headImg 用户头像
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	/**
	 * 获取用户性别 1 男性 2 女性
	 *
	 * @return gender - 用户性别 1 男性 2 女性
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * 设置用户性别 1 男性 2 女性
	 *
	 * @param gender 用户性别 1 男性 2 女性
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * 获取用户所在国家id
	 *
	 * @return country_id - 用户所在国家id
	 */
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * 设置用户所在国家id
	 *
	 * @param countryId 用户所在国家id
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	/**
	 * 获取用户所在的国家名称
	 *
	 * @return country_name - 用户所在的国家名称
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * 设置用户所在的国家名称
	 *
	 * @param countryName 用户所在的国家名称
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * 获取金币数量
	 *
	 * @return gold_num - 金币数量
	 */
	public BigDecimal getGoldNum() {
		return goldNum;
	}

	/**
	 * 设置金币数量
	 *
	 * @param goldNum 金币数量
	 */
	public void setGoldNum(BigDecimal goldNum) {
		this.goldNum = goldNum;
	}

	/**
	 * 获取语言id
	 *
	 * @return language_id - 语言id
	 */
	public String getLanguageId() {
		return languageId;
	}

	/**
	 * 设置语言id
	 *
	 * @param languageId 语言id
	 */
	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	/**
	 * 获取语言名称，多个名称用，分割
	 *
	 * @return language_name - 语言名称，多个名称用，分割
	 */
	public String getLanguageName() {
		return languageName;
	}

	/**
	 * 设置语言名称，多个名称用，分割
	 *
	 * @param languageName 语言名称，多个名称用，分割
	 */
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	/**
	 * 获取用户的年龄
	 *
	 * @return age - 用户的年龄
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 设置用户的年龄
	 *
	 * @param age 用户的年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 获取用户出生日期
	 *
	 * @return birthday - 用户出生日期
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 设置用户出生日期
	 *
	 * @param birthday 用户出生日期
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 获取用户使用的平台类型1 ios 2 android
	 *
	 * @return platform_type - 用户使用的平台类型1 ios 2 android
	 */
	public Integer getPlatformType() {
		return platformType;
	}

	/**
	 * 设置用户使用的平台类型1 ios 2 android
	 *
	 * @param platformType 用户使用的平台类型1 ios 2 android
	 */
	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}

	/**
	 * 获取用户账号的登录类型 1 注册登录，2 facebook登录，3 ，google+登录
	 *
	 * @return type - 用户账号的登录类型 1 注册登录，2 facebook登录，3 ，google+登录
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置用户账号的登录类型 1 注册登录，2 facebook登录，3 ，google+登录
	 *
	 * @param type 用户账号的登录类型 1 注册登录，2 facebook登录，3 ，google+登录
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取是否是付费用户
	 *
	 * @return pay_status - 是否是付费用户
	 */
	public Integer getPayStatus() {
		return payStatus;
	}

	/**
	 * 设置是否是付费用户
	 *
	 * @param payStatus 是否是付费用户
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * 获取当前账号的状态 1.可用，2 禁用 ，3 被举报
	 *
	 * @return status - 当前账号的状态 1.可用，2 禁用 ，3 被举报
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置当前账号的状态 1.可用，2 禁用 ，3 被举报
	 *
	 * @param status 当前账号的状态 1.可用，2 禁用 ，3 被举报
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取用户创建时间
	 *
	 * @return create_time - 用户创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置用户创建时间
	 *
	 * @param createTime 用户创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取用户信息的更新时间
	 *
	 * @return update_time - 用户信息的更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置用户信息的更新时间
	 *
	 * @param updateTime 用户信息的更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取钻石数量
	 *
	 * @return stone - 钻石数量
	 */
	public BigDecimal getStone() {
		return stone;
	}

	/**
	 * 设置钻石数量
	 *
	 * @param stone 钻石数量
	 */
	public void setStone(BigDecimal stone) {
		this.stone = stone;
	}

	/**
	 * 变更为用户版本号
	 *
	 * @return stone_version - 钻石版本号
	 */
	public Integer getStoneVersion() {
		return stoneVersion;
	}

	/**
	 * 设置钻石版本号 变更为用户版本号
	 *
	 * @param stoneVersion 变更为用户版本号
	 */
	public void setStoneVersion(Integer stoneVersion) {
		this.stoneVersion = stoneVersion;
	}


	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}


	public Integer getDeviceLanguageId() {
		return deviceLanguageId;
	}

	public void setDeviceLanguageId(Integer deviceLanguageId) {
		this.deviceLanguageId = deviceLanguageId;
	}


	public Boolean getEroticismBehavior() {
		return eroticismBehavior;
	}

	public void setEroticismBehavior(Boolean eroticismBehavior) {
		this.eroticismBehavior = eroticismBehavior;
	}

	public Boolean getSignEroticism() {
		return signEroticism;
	}

	public void setSignEroticism(Boolean signEroticism) {
		this.signEroticism = signEroticism;
	}


	public Integer getRegisterPlatformType() {
		return registerPlatformType;
	}

	public void setRegisterPlatformType(Integer registerPlatformType) {
		this.registerPlatformType = registerPlatformType;
	}

	public Integer getUserPraise() {
		return userPraise;
	}

	public void setUserPraise(Integer userPraise) {
		this.userPraise = userPraise;
	}

	public Integer getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}

	public String getCountryGroupName() {
		return countryGroupName;
	}

	public void setCountryGroupName(String countryGroupName) {
		this.countryGroupName = countryGroupName;
	}

	public String getOldCountryName() {
		return oldCountryName;
	}

	public void setOldCountryName(String oldCountryName) {
		this.oldCountryName = oldCountryName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}


	public Boolean getGoddess() {
		return goddess;
	}

	public void setGoddess(Boolean goddess) {
		this.goddess = goddess;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("User{");
		sb.append("id=").append(id);
		sb.append(", userAccount='").append(userAccount).append('\'');
		sb.append(", threePartyId='").append(threePartyId).append('\'');
		sb.append(", threePartyEmail='").append(threePartyEmail).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", userName='").append(userName).append('\'');
		sb.append(", appId=").append(appId);
		sb.append(", background='").append(background).append('\'');
		sb.append(", headImg='").append(headImg).append('\'');
		sb.append(", gender=").append(gender);
		sb.append(", countryId=").append(countryId);
		sb.append(", countryName='").append(countryName).append('\'');
		sb.append(", goldNum=").append(goldNum);
		sb.append(", languageId='").append(languageId).append('\'');
		sb.append(", languageName='").append(languageName).append('\'');
		sb.append(", age=").append(age);
		sb.append(", birthday=").append(birthday);
		sb.append(", platformType=").append(platformType);
		sb.append(", type=").append(type);
		sb.append(", payStatus=").append(payStatus);
		sb.append(", status=").append(status);
		sb.append(", createTime=").append(createTime);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", stone=").append(stone);
		sb.append(", stoneVersion=").append(stoneVersion);
		sb.append(", introduce='").append(introduce).append('\'');
		sb.append(", channel=").append(channel);
		sb.append(", deviceLanguageId=").append(deviceLanguageId);
		sb.append(", countryIds=").append(countryIds);
		sb.append(", eroticismBehavior=").append(eroticismBehavior);
		sb.append(", signEroticism=").append(signEroticism);
		sb.append(", registerPlatformType=").append(registerPlatformType);
		sb.append(", userPraise=").append(userPraise);
		sb.append(", workDay=").append(workDay);
		sb.append(", countryGroupName='").append(countryGroupName).append('\'');
		sb.append(", oldCountryName='").append(oldCountryName).append('\'');
		sb.append(", groupName='").append(groupName).append('\'');
		sb.append(", userLevel=").append(userLevel);
		sb.append(", goddess=").append(goddess);
		sb.append('}');
		return sb.toString();
	}

	public static final class Builder {
		private Integer id;
		private String userAccount;
		private String threePartyId;
		private String threePartyEmail;
		private String password;
		private String userName;
		private Integer appId;
		private String background;
		private String headImg;
		private Integer gender;
		private Integer countryId;
		private String countryName;
		private BigDecimal goldNum;
		private String languageId;
		private String languageName;
		private Integer age;
		private Date birthday;
		private Integer platformType;
		private Integer type;
		private Integer payStatus;
		private Integer status;
		private Date createTime;
		private Date updateTime;
		private BigDecimal stone;
		private Integer stoneVersion;
		private String introduce;
		private Integer deviceLanguageId;
		private Boolean eroticismBehavior;
		private Boolean signEroticism;

		private Builder() {
		}

		public static Builder builder() {
			return new Builder();
		}

		public Builder id(Integer id) {
			this.id = id;
			return this;
		}

		public Builder userAccount(String userAccount) {
			this.userAccount = userAccount;
			return this;
		}

		public Builder threePartyId(String threePartyId) {
			this.threePartyId = threePartyId;
			return this;
		}

		public Builder threePartyEmail(String threePartyEmail) {
			this.threePartyEmail = threePartyEmail;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder appId(Integer appId) {
			this.appId = appId;
			return this;
		}

		public Builder background(String background) {
			this.background = background;
			return this;
		}

		public Builder headImg(String headImg) {
			this.headImg = headImg;
			return this;
		}

		public Builder gender(Integer gender) {
			this.gender = gender;
			return this;
		}

		public Builder countryId(Integer countryId) {
			this.countryId = countryId;
			return this;
		}

		public Builder countryName(String countryName) {
			this.countryName = countryName;
			return this;
		}

		public Builder goldNum(BigDecimal goldNum) {
			this.goldNum = goldNum;
			return this;
		}

		public Builder languageId(String languageId) {
			this.languageId = languageId;
			return this;
		}

		public Builder languageName(String languageName) {
			this.languageName = languageName;
			return this;
		}

		public Builder age(Integer age) {
			this.age = age;
			return this;
		}

		public Builder birthday(Date birthday) {
			this.birthday = birthday;
			return this;
		}

		public Builder platformType(Integer platformType) {
			this.platformType = platformType;
			return this;
		}

		public Builder type(Integer type) {
			this.type = type;
			return this;
		}

		public Builder payStatus(Integer payStatus) {
			this.payStatus = payStatus;
			return this;
		}

		public Builder status(Integer status) {
			this.status = status;
			return this;
		}

		public Builder createTime(Date createTime) {
			this.createTime = createTime;
			return this;
		}

		public Builder updateTime(Date updateTime) {
			this.updateTime = updateTime;
			return this;
		}

		public Builder stone(BigDecimal stone) {
			this.stone = stone;
			return this;
		}

		public Builder stoneVersion(Integer stoneVersion) {
			this.stoneVersion = stoneVersion;
			return this;
		}

		public Builder introduce(String introduce) {
			this.introduce = introduce;
			return this;
		}


		public Builder deviceLanguageId(Integer deviceLanguageId) {
			this.deviceLanguageId = deviceLanguageId;
			return this;
		}

		public Builder eroticismBehavior(Boolean eroticismBehavior) {
			this.eroticismBehavior = eroticismBehavior;
			return this;
		}

		public Builder signEroticism(Boolean signEroticism) {
			this.signEroticism = signEroticism;
			return this;
		}

		public User build() {
			User user = new User();
			user.setId(id);
			user.setUserAccount(userAccount);
			user.setThreePartyId(threePartyId);
			user.setThreePartyEmail(threePartyEmail);
			user.setPassword(password);
			user.setUserName(userName);
			user.setAppId(appId);
			user.setBackground(background);
			user.setHeadImg(headImg);
			user.setGender(gender);
			user.setCountryId(countryId);
			user.setCountryName(countryName);
			user.setGoldNum(goldNum);
			user.setLanguageId(languageId);
			user.setLanguageName(languageName);
			user.setAge(age);
			user.setBirthday(birthday);
			user.setPlatformType(platformType);
			user.setType(type);
			user.setPayStatus(payStatus);
			user.setStatus(status);
			user.setCreateTime(createTime);
			user.setUpdateTime(updateTime);
			user.setStone(stone);
			user.setStoneVersion(stoneVersion);
			user.setIntroduce(introduce);
			user.setDeviceLanguageId(deviceLanguageId);
			user.setEroticismBehavior(eroticismBehavior);
			user.setSignEroticism(signEroticism);
			return user;
		}
	}
}