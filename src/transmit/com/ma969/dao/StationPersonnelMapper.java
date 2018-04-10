package com.ma969.dao;

import com.ma969.beans.StationPersonnel;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:54:19
 */
public interface StationPersonnelMapper {
	/**
	 * 根据id查找台站人员信息
	 * @param id
	 * @return
	 */
	StationPersonnel findById(int id);

	/**
	 * 添加台站人员配置
	 * @param stationPersonnel
	 * @return
	 */
	int addStationPersonnel(StationPersonnel stationPersonnel);

	/**
	 * 修改台站人员配置
	 * @param stationPersonnel
	 * @return
	 */
	int updateStationPersonnel(StationPersonnel stationPersonnel);

	/**
	 * 删除台站人员配置
	 * @param id
	 * @return
	 */
	int deleteStationPersonnel(int id);

	/**
	 * 更新台站人员状态
	 * @param id
	 * @return
	 */
	int updatepstate(int id);

}
