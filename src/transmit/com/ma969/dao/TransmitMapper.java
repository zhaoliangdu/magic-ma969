package com.ma969.dao;

import java.util.List;
import java.util.Map;
import com.ma969.beans.Transmit;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:55:19
 */
public interface TransmitMapper {

	/**
	 * 获取台站
	 * @return
	 */
	List<Transmit> listTransmits();

	/**
	 * 添加台站
	 * @param transmit
	 * @return
	 */
	int saveTransmit(Transmit transmit);

	/**
	 * 根据id查找台站
	 * @param id
	 * @return
	 */
	Transmit findById(int id);

	/**
	 * 修改台站信息
	 * @param transmit
	 * @return
	 */
	int updateTransmit(Transmit transmit);

	/**
	 * 删除台站
	 * @param id
	 * @return
	 */
	int deleteTransmit(int id);

	/**
	 * 根据条件获取台站名称
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	int getTransmitName(Map map);

}
