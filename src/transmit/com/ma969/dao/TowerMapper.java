package com.ma969.dao;

import com.ma969.beans.Tower;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:54:24
 */
public interface TowerMapper {
	/**
	 * 根据id查找塔桅
	 * @param id
	 * @return
	 */
	Tower findById(int id);

	/**
	 * 添加塔桅
	 * @param tower
	 * @return
	 */
	int addTower(Tower tower);

	/**
	 * 修改塔桅
	 * @param tower
	 * @return
	 */
	int updateTower(Tower tower);

	/**
	 * 删除塔桅
	 * @param id
	 * @return
	 */
	int deleteTower(int id);

	/**
	 * 修改塔桅状态
	 * @param id
	 * @return
	 */
	int updateTstate(int id);
}
