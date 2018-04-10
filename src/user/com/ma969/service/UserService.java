package com.ma969.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ma969.beans.User;
import com.ma969.dao.SystemSetMapper;
import com.ma969.dao.UserMapper;
import com.ma969.utils.Sha;
import me.jor.util.Help;

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

	/**
	 * login
	 * 
	 * @param username
	 * @return true
	 */

	public boolean userLogin(String username, String password) {
		User user = userMapper.findByName(username);
		boolean flag = !Help.isEmpty(user)
				? user.getUserName().equals(username) && Sha.getResult(username + password).equals(user.getPassword())
				: false;
		return flag;
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
}
