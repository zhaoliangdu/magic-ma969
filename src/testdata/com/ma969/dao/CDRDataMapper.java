package com.ma969.dao;

import java.util.List;
import java.util.Map;
import com.ma969.beans.CDRData;

public interface CDRDataMapper {
	/**
	 * 获取频率
	 * 
	 * @return
	 */
	List<Float> listFrequencys();

	/**
	 * 条件查询数字电视数据
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List<CDRData> listCDRDataByParam(Map map);

	/**
	 * 获取统计
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	int getCount(Map map);

	/**
	 * 返回总记录数
	 * 
	 * @return
	 */
	int getSampleCount();

	/**
	 * 添加数字电视
	 * 
	 * @param testData
	 * @return
	 */
	int saveCDRData(CDRData testData);

	/**
	 * 根据地区获取场强
	 * 
	 * @param area
	 * @return
	 */
	List<Float> listFieldStrength(String area);

	/**
	 * 获取数字电视List
	 * 
	 * @return
	 */
	List<CDRData> listCDRDatas();

	/**
	 * 统计场强
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	int getFieldCount(Map map);

	/**
	 * 根据经度找纬度
	 * 
	 * @param lng
	 * @return
	 */
	Float getLatitueByLng(float lng);

	/**
	 * 根据纬度找经度
	 * 
	 * @param latF
	 * @return
	 */
	Float getLngitudeByLat(float latF);

	/**
	 * 查找最大场强
	 * 
	 * @return
	 */
	Float findMaxField();

	/**
	 * 查找最小场强
	 * 
	 * @return
	 */
	Float findMinField();

	/**
	 * 查找最大snr
	 * 
	 * @return
	 */
	Float findMaxSnr();

	/**
	 * 查找最小snr
	 * 
	 * @return
	 */
	Float findMinSnr();

	/**
	 * 查找最大ldpc
	 * 
	 * @return
	 */
	Float findMaxLdpc();

	/**
	 * 查找最小ldpc
	 * 
	 * @return
	 */
	Float findMinLdpc();

	/**
	 * 查找最大经度
	 * 
	 * @return
	 */
	Float findMaxLng();

	/**
	 * 查找最小经度
	 * 
	 * @return
	 */
	Float findMinLng();

	/**
	 * 查找最大纬度
	 * 
	 * @return
	 */
	Float findMaxLat();

	/**
	 * 查找最小纬度
	 * 
	 * @return
	 */
	Float findMinLat();

	/**
	 * 根据参数查找digital数据
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List<CDRData> listCDRDataByField(Map map);

	/**
	 * 根据参数修改eid
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	int updateEId(Map map);

	/**
	 * 根据参数修改经纬度
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	int updateLngLat(Map map);

	/**
	 * 根据id查找digital数据
	 * 
	 * @param id
	 * @return
	 */
	CDRData findById(int id);

	/**
	 * 根据时间查找数据
	 * 
	 * @param time
	 * @return
	 */
	int checkTime(String time);

	/**
	 * 根据地区找digital数据
	 * 
	 * @param area
	 * @return
	 */
	List<CDRData> findCDRDataByArea(String area);

	/**
	 * 获取所有地区
	 * 
	 * @return
	 */
	List<String> listAreas();

	/**
	 * 删除digital数据
	 * 
	 * @param id
	 * @return
	 */
	int deleteCDRData(int id);
}
