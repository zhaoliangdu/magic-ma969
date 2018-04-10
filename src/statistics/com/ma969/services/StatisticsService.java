package com.ma969.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ma969.beans.SystemSet;
import com.ma969.dao.AnalogDataMapper;
import com.ma969.dao.CDRDataMapper;
import com.ma969.dao.DigitalDataMapper;
import com.ma969.dao.RadioDataMapper;
import com.ma969.dao.SystemSetMapper;

/**
 * 
 * @author Zhao
 *
 */
@Service
public class StatisticsService {
	@Autowired
	DigitalDataMapper testDataMapper;
	@Autowired
	RadioDataMapper radioDataMapper;
	@Autowired
	CDRDataMapper cdrDataMapper;
	@Autowired
	AnalogDataMapper analogDataMapper;
	@Autowired
	SystemSetMapper systemSetMapper;

	/**
	 * 根据用户id返回直方图饼状图所需的参数
	 * 
	 * @param uid
	 * @param testmode
	 * @param area
	 * @return
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Float> getFieldStrength(int uid, int testmode, String area) {
		List list = new ArrayList<>();
		List<Float> fieldStrength = new ArrayList<>();
		if (testmode == 1) {
			fieldStrength = testDataMapper.listFieldStrength(area);
		} else if (testmode == 2) {
			fieldStrength = radioDataMapper.listFieldStrength(area);
		} else if (testmode == 3) {
			fieldStrength = cdrDataMapper.listFieldStrength(area);
		} else {
			fieldStrength = analogDataMapper.listFieldStrength(area);
		}

		SystemSet systemSet = systemSetMapper.getSystemSet(uid);
		String[] fieldrangeArray = systemSet.getFieldRange().split(",");
		String[] colorArray = systemSet.getFieldColor().split(",");
		int[] counts = new int[colorArray.length];
		for (int j = 0; j < colorArray.length; j++) { 
			for (int i = 0; i < fieldStrength.size(); i++) {
				if (fieldStrength.get(i) >= Integer.parseInt(fieldrangeArray[j].split("-")[0].trim())
						&& fieldStrength.get(i) < Integer.parseInt(fieldrangeArray[j].split("-")[1].trim())) {
					counts[j]++;
				} else {
					continue;
				}
			}
		}

		list.add(fieldrangeArray);
		list.add(colorArray);
		list.add(counts);
		return list;
	}
}
