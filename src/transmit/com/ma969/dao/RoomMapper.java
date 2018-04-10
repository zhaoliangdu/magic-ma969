package com.ma969.dao;

import com.ma969.beans.Room;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:54:14
 */
public interface RoomMapper {
	/**
	 * 根据id查找机房信息
	 * 
	 * @param id
	 * @return
	 */
	public Room findById(int id);

	/**
	 * 添加机房信息
	 * @param room
	 * @return
	 */
	public int addRoom(Room room);

	/**
	 * 修改机房信息
	 * @param room
	 * @return
	 */
	public int updateRoom(Room room);

	/**
	 * 根据id删除机房
	 * @param id
	 * @return
	 */
	public int deleteRoom(int id);

	/**
	 * 更新机房状态
	 * @param id
	 * @return
	 */
	int updateRstate(int id);
}
