package com.ma969.dao;

import java.util.List;
import java.util.Map;

import com.ma969.beans.User;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:49:46
 */
public interface UserMapper {
	/**
	 * 获取所有用信息
	 * 
	 * @return
	 */
	List<User> getUsers();

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	int addUser(User user);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	User findByName(String username);

	/**
	 * 根据id查找用户
	 * 
	 * @param id
	 * @return
	 */
	User findById(int id);

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	int updateUser(User user);

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 */
	int updatePwd(User user);

	/**
	 * 删除用户
	 * 
	 * @param userId
	 * @return
	 */
	int deleteUser(int userId);

	/**
	 * 黑名单
	 * 
	 * @return
	 */
	List<User> blackList();

	/**
	 * 恢复用户
	 * 
	 * @param uId
	 * @return
	 */
	int userRecovery(int uId);

	/**
	 * 修改登录时间
	 * 
	 * @param map
	 * @return
	 */
	int updateLoginTime(Map<String, Object> map);

	/**
	 * 查询上次登录时间
	 * 
	 * @param uId
	 * @return
	 */
	String getlastLoginTime(int uId);

	/**
	 * 查询创建时间
	 * 
	 * @param uId
	 * @return
	 */
	String getCreateTime(int uId);
}
