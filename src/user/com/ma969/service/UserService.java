package com.ma969.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ma969.beans.User;
import com.ma969.dao.SystemSetMapper;
import com.ma969.dao.UserMapper;
import com.ma969.utils.Sha;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:49:56
 */
@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	SystemSetMapper systemMapper;

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * regist
	 * 
	 * @param user
	 * @return 返回1代表注册成功
	 */

	public int userRegist(User user) {
		user.setPassword(Sha.getResult(user.getUserName() + user.getPassword()));
		user.setCreateTime(LocalDateTime.now().toString());
		return userMapper.addUser(user);
	}

	/**
	 * 通过用户名查找用户
	 */

	public User findUserByName(String username) {
		return userMapper.findByName(username);
	}

	/**
	 * 根据ID查找用户
	 */
	public User findById(int id) {
		return userMapper.findById(id);
	}

	/**
	 * 查询全部用户
	 */
	public List<User> getUsers() {
		return userMapper.getUsers();
	}

	/**
	 * 更新用户信息
	 */
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 */
	public int updatePwd(User user) {
		return userMapper.updatePwd(user);
	}

	/**
	 * 删除用户
	 */
	public int deleteUser(int id) {
		return userMapper.deleteUser(id);
	}

	/**
	 * 添加用户ID
	 * 
	 * @param uid
	 * @return
	 */
	public int addUserId(int uid) {
		return systemMapper.addUserId(uid);
	}

	/**
	 * 黑名单列表
	 * 
	 * @return
	 */
	public List<User> blackList() {
		return userMapper.blackList();
	}

	/**
	 * 恢复删除用户
	 * 
	 * @param id
	 * @return
	 */
	public int userRecovery(int id) {
		return userMapper.userRecovery(id);
	}

	/**
	 * 获取最后登录时间
	 * 
	 * @param uId
	 * @return
	 */
	public String getLastLoginTime(int uId) {
		return userMapper.getlastLoginTime(uId);
	}

	/**
	 * 记录最后登录时间
	 * 
	 * @param map
	 * @return
	 */
	public int updateLastLoginTime(Map<String, Object> map) {
		return userMapper.updateLoginTime(map);
	}

	/**
	 * 获取用户创建时间
	 * 
	 * @param uId
	 * @return
	 */
	public String getCreateTime(int uId) {
		return userMapper.getCreateTime(uId);
	}

	/**
	 * 发送邮件
	 * 
	 * @param emailaddress
	 * @param code
	 */
	public void resetPwd(String emailaddress, int code) {
		System.err.println("开始发送邮件" + emailaddress + "-验证码：" + code);
		try {
			Properties properties = new Properties();

			properties.put("mail.transport.protocol", "smtp");// 连接协议

			properties.put("mail.smtp.host", "smtp.qq.com");// 主机名

			properties.put("mail.smtp.port", 465);// 端口号

			properties.put("mail.smtp.auth", "true");

			properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用

			properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息

			// 得到回话对象

			Session session = Session.getInstance(properties);

			// 获取邮件对象

			Message message = new MimeMessage(session);

			// 设置发件人邮箱地址

			message.setFrom(new InternetAddress("zhaoliangdu@qq.com"));

			// 设置收件人地址
			message.setRecipients(RecipientType.TO, new InternetAddress[] { new InternetAddress(emailaddress) });

			// 设置邮件标题

			message.setSubject("这是一封Ma969发送的验证码邮件");

			// 设置邮件内容

			message.setText("内容为： 这是一封Ma969发送来的验证邮件。验证码：" + code + "。不需要回复");

			// 得到邮差对象

			Transport transport = session.getTransport();

			// 连接自己的邮箱账户

			transport.connect("zhaoliangdu@qq.com", "okywfygufdxjcabc");// 密码为刚才得到的授权码

			// 发送邮件
			transport.sendMessage(message, message.getAllRecipients());
			System.err.println("邮件发送成功！");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
