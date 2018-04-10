package com.ma969.dao;

import java.util.List;

import com.ma969.beans.Frequency;
/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:54:10
 */
public interface FrequencyMapper {
	/**
	 * 根据id查找频率
	 * @param id
	 * @return
	 */
	List<Frequency> getFrequencys(int id);

	/**
	 * 添加频率
	 * @param frequency
	 * @return
	 */
	int addFrequency(Frequency frequency);

	/**
	 * 查找频率
	 * @param frequency
	 * @return
	 */
	Frequency findByFrequency(Frequency frequency);

	/**
	 * 删除频率
	 * @param frequency
	 * @return
	 */
	int deleteFrequency(Float frequency);
	
	/**
	 * 更新状态
	 * @param id
	 * @return
	 */
	int updateFstate(int id);
	
	/**
	 * 根据id删除频率
	 * @param aid
	 * @return
	 */
	int deleteFrequencyById(int aid);
}
