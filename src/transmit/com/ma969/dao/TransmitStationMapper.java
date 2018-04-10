package com.ma969.dao;

import java.util.List;
import java.util.Map;

import com.ma969.beans.TransmitStation;
/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:55:53
 */
public interface TransmitStationMapper {

	/**
	 * 根据id获取台站详细信息
	 * @param id
	 * @return
	 */
	TransmitStation findByTransmitId(int id);

	/**
	 * 根据条件获取台站信息
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List<TransmitStation> findByParam(Map map);
	
	/**
	 * 返回台站信息List<TransmitStation>
	 * @return
	 */
	List<TransmitStation> listTransmitStation();
}
