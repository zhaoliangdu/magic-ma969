
package com.ma969.dao;

import java.util.List;

import com.ma969.beans.DataMode;;

/**
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月10日下午2:14:31
 */
public interface DataModeMapper {
	
	/**
	 * 根据id获取数据类型
	 * @param id
	 * @return
	 */
	String getDataMode(int id);
	/**
	 * 获取所有数据类型
	 * @return 返回数据类型的list
	 */
	List<DataMode> listDataModes();
}
