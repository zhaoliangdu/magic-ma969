package com.ma969.dao;

import com.ma969.beans.SystemSet;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:51:08
 */
public interface SystemSetMapper {
	/**
	 * 根据用户获取对应系统设置
	 * 
	 * @param uid
	 * @return
	 */
	SystemSet getSystemSet(int uid);

	/**
	 * 添加用户id
	 * 
	 * @param uid
	 * @return
	 */
	int addUserId(int uid);

	/**
	 * 获取场强颜色字符串
	 * 
	 * @param uid
	 * @return
	 */
	String getFieldColors(int uid);

	/**
	 * 获取snr颜色
	 * @param uid
	 * @return
	 */
	String getSnrColors(int uid);

	/**
	 * 获取ldpc颜色
	 * @param uid
	 * @return
	 */
	String getLdpcColors(int uid);

	/**
	 * 恢复默认设置
	 * 
	 * @param uid
	 * @return
	 */
	int resetSysSet(int uid);

	/**
	 * 修改设置状态
	 * 
	 * @param uid
	 * @return
	 */
	int updateSysSetstate(int uid);

	/**
	 * 修改场强
	 * 
	 * @param systemSet
	 * @return
	 */ 
	int updateFieldSet(SystemSet systemSet);

	/**
	 * 修改snr
	 * 
	 * @param systemSet
	 * @return
	 */ 
	int updateSnrSet(SystemSet systemSet);

	/**
	 * 修改ldpc
	 * 
	 * @param systemSet
	 * @return
	 */ 
	int updateLdpcSet(SystemSet systemSet);

	/**
	 * 根据用户id删除对应设置
	 * 
	 * @param uid
	 * @return
	 */
	int delSysSet(int uid);

	/**
	 * 修改背景
	 * 
	 * @param systemSet
	 * @return
	 */
	int updateBgColorFont(SystemSet systemSet);

	/**
	 * 获取背景
	 * 
	 * @param uid
	 * @return
	 */
	String getBgColorAndFont(int uid);

}
