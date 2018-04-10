package com.ma969.dao;

import com.ma969.beans.Antenna;
/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:53:57
 */
public interface AntennaMapper {
	 /**
	  * 添加天线
	  * @param antenna
	  * @return
	  */
	 int addAntenna(Antenna antenna);
	 
	 /**
	  * 根据id查找添加
	  * @param id
	  * @return
	  */
	 Antenna findById(int id);

	 /**
	  * 更新天线
	  * @param antenna
	  * @return
	  */
	 int updateAntenna(Antenna antenna);

	 /**
	  * 删除天线
	  * @param id
	  * @return
	  */
	 int deleteAntenna(int id);
	 
	 /**
	  * 更新状态
	  * @param id
	  * @return
	  */
	 int updateAstate(int id);
}
