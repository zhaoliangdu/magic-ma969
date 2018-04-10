package com.ma969.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.ma969.beans.Transmit;
import com.ma969.dao.AntennaMapper;
import com.ma969.dao.ChannelMapper;
import com.ma969.dao.FrequencyMapper;
import com.ma969.dao.RoomMapper;
import com.ma969.dao.StationPersonnelMapper;
import com.ma969.dao.DigitalDataMapper;
import com.ma969.dao.TowerMapper;
import com.ma969.dao.TransmitMapper;
import com.ma969.service.TransmitService; 
/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:54:36
 */
@Service
public class TransmitService {
	@Autowired
	TransmitMapper transmitMapper;
	@Autowired
	DigitalDataMapper testdataMapper;
	@Autowired
	AntennaMapper antennaMapper;
	@Autowired
	ChannelMapper channelMapper;
	@Autowired
	FrequencyMapper frequencyMapper;
	@Autowired
	RoomMapper roomMapper;
	@Autowired
	StationPersonnelMapper spMapper;
	@Autowired
	TowerMapper towerMapper;
 
	/**
	 * 添加台站
	 * @param transmit
	 * @return
	 */
	public int addTransmit(Transmit transmit) { 
		int addTransmit = 0;
		addTransmit = transmitMapper.saveTransmit(transmit);
		Map<String,Object> map = new HashMap<>(16);
		map.put("eid", transmit.getTransmitId());
		map.put("area", transmit.getArea());
		return addTransmit;
	}

	public List<Transmit> getTransmits() {

		return transmitMapper.listTransmits();
	}

	public Transmit findById(int id) {

		return transmitMapper.findById(id);
	}

	public int updateTransmit(Transmit transmit) {

		return transmitMapper.updateTransmit(transmit);
	}

	public int deleteTransmit(int id) {

		return transmitMapper.deleteTransmit(id);
	}

	@SuppressWarnings("rawtypes")
	public int getTransmitName(Map map) {

		return transmitMapper.getTransmitName(map);
	}
 
}
