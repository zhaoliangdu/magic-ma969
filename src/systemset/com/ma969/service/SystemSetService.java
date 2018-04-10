package com.ma969.service;

import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ma969.beans.SystemSet;
import com.ma969.dao.SystemSetMapper;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:51:15
 */
@Service
public class SystemSetService {
	private static final Log LOG = LogFactory.getLog(SystemSetService.class);
	@Autowired
	SystemSetMapper systemSetMapper;

	/**
	 * 根据最大最小场强计算分段
	 * 
	 * @param min
	 * @param max
	 * @param score
	 * @return
	 */
	public String getRange(int min, int max, int score) {
		Map<Integer, Integer> fieldmap = new TreeMap<>();
		int count = (max - min) % score == 0 ? (max - min) / score : Math.abs((max - min) / score);
		LOG.info("count:" + count);
		for (int i = 0; i < score - 1; i++) {
			fieldmap.put(min, min += count);
		}
		fieldmap.put(min, max);
		String str = fieldmap.toString();
		String range = str.substring(1, str.length() - 1).replace("=", "-"); 
		return range;
	}

	public SystemSet getSystemSet(int uid) { 
		return systemSetMapper.getSystemSet(uid);
	}

	public int updateFieldSet(SystemSet systemSet) { 
		return systemSetMapper.updateFieldSet(systemSet);
	}

	public int updateSnrSet(SystemSet systemSet) { 
		return systemSetMapper.updateSnrSet(systemSet);
	}

	public int updateLdpcSet(SystemSet systemSet) { 
		return systemSetMapper.updateLdpcSet(systemSet);
	}

	public String getFieldColors(int uid) {
		return systemSetMapper.getFieldColors(uid);
	}

	public String getSnrColors(int uid) {
		return systemSetMapper.getSnrColors(uid);
	}

	public String getLdpcColors(int uid) {
		return systemSetMapper.getLdpcColors(uid);
	}

	public int addUserId(int uid) {

		return systemSetMapper.addUserId(uid);
	}

	public int resetSysSet(int uid) { 
		return systemSetMapper.resetSysSet(uid);
	}

	public int delSysSet(int uid) { 
		return systemSetMapper.delSysSet(uid);
	}

	public int updateBgColorFont(SystemSet systemSet) {
		return systemSetMapper.updateBgColorFont(systemSet);
	}

}
