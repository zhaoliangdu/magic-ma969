package com.ma969.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.TransactionalCacheManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.ma969.beans.Antenna;
import com.ma969.beans.Channel;
import com.ma969.beans.Channels;
import com.ma969.beans.Frequency;
import com.ma969.beans.Room;
import com.ma969.beans.StationPersonnel;
import com.ma969.beans.Tower;
import com.ma969.beans.Transmit;
import com.ma969.beans.TransmitStation;
import com.ma969.dao.AntennaMapper;
import com.ma969.dao.ChannelMapper;
import com.ma969.dao.ChannelsMapper;
import com.ma969.dao.FrequencyMapper;
import com.ma969.dao.RoomMapper;
import com.ma969.dao.StationPersonnelMapper;
import com.ma969.dao.DigitalDataMapper;
import com.ma969.dao.TowerMapper;
import com.ma969.dao.TransmitMapper;
import com.ma969.dao.TransmitStationMapper;
import com.ma969.service.TransmitStationService;
import com.ma969.utils.OperationFileUtils;

import me.jor.util.Help;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:54:40
 */
@Service
public class TransmitStationService {

	@Autowired
	TransmitStationMapper transmitStationMapper;
	@Autowired
	AntennaMapper antennaMapper;
	@Autowired
	ChannelMapper channelMapper;
	@Autowired
	RoomMapper roomMapper;
	@Autowired
	StationPersonnelMapper sPersonnelMapper;
	@Autowired
	TowerMapper towerMapper;
	@Autowired
	TransmitMapper transmitMapper;
	@Autowired
	ChannelsMapper channelsMapper;
	@Autowired
	FrequencyMapper frequencyMapper;
	@Autowired
	DigitalDataMapper testdataMapper;

	private static final Log LOG = LogFactory.getLog(TransmitStationService.class);

	/**
	 * findByTransmitId
	 */

	public TransmitStation findByTransmitId(int id) {

		return transmitStationMapper.findByTransmitId(id);
	}

	/**
	 * deleteTransmitStation
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int deleteTransmitStation(int id) {
		TransactionalCacheManager trancaction = new TransactionalCacheManager();
		Transmit transmit = transmitMapper.findById(id);
		System.err.println("transmit:" + transmit);
		channelMapper.deleteChannelById(transmit.getAntennaId());
		frequencyMapper.deleteFrequencyById(transmit.getAntennaId());
		int deleteTransmit = transmitMapper.deleteTransmit(id);
		int deleteAntenna = antennaMapper.deleteAntenna(transmit.getAntennaId());
		int deleteRoom = roomMapper.deleteRoom(transmit.getRoomId());
		int deleteStationPersonnel = sPersonnelMapper.deleteStationPersonnel(transmit.getStationPersonId());
		int deleteTower = towerMapper.deleteTower(transmit.getTowerId());
		if ((deleteAntenna == 1) && (deleteRoom == 1) && (deleteStationPersonnel == 1) && (deleteTower == 1)
				&& (deleteTransmit == 1)) {
			trancaction.commit();
			return 1;
		} else {
			trancaction.rollback();
			return 0;
		}
	}

	public List<Channel> getChannels(int id) {
		return channelMapper.getChannels(id);
	}

	/**
	 * addTransmitStation
	 *
	 * @param aId
	 * @param room
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int addTransmitStation(int aId, Room room, StationPersonnel stationPersonnel, Tower tower,
			Transmit transmit) {
		TransactionalCacheManager trancaction = new TransactionalCacheManager();
		int addRoom = roomMapper.addRoom(room);
		int addStationPersonnel = sPersonnelMapper.addStationPersonnel(stationPersonnel);
		int addTower = towerMapper.addTower(tower);
		LOG.info("get insertId:" + room.getRoomId() + "-" + stationPersonnel.getStationPersonId() + "-"
				+ tower.getTowerId());
		Transmit transmit2 = new Transmit(aId, room.getRoomId(), stationPersonnel.getStationPersonId(),
				tower.getTowerId(), transmit.getArea(), transmit.gettName(), transmit.getLatitude(),
				transmit.getLongitude(), transmit.getHeight(), transmit.getLastUpt(), transmit.getLastUpName());
		int addTransmit = transmitMapper.saveTransmit(transmit2);
		Map<String, Object> map = new HashMap<>(16);
		map.put("eid", transmit2.getTransmitId());
		map.put("area", transmit.getArea());
		if (addRoom == 1 && addStationPersonnel == 1 && addTower == 1 && addTransmit == 1) {
			trancaction.commit();
			return 1;
		} else {
			trancaction.rollback();
			return 0;
		}
	}

	/**
	 * updateTransmitStation
	 *
	 * @param transmit
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateTransmitStation(int tId, Antenna antenna, Room room, StationPersonnel stationPersonnel,
			Tower tower, Transmit transmit) {
		TransactionalCacheManager transcation = new TransactionalCacheManager();
		Transmit findById = transmitMapper.findById(tId);
		antenna.setAntennaId(findById.getAntennaId());
		room.setRoomId(findById.getRoomId());
		stationPersonnel.setStationPersonId(findById.getStationPersonId());
		tower.setTowerId(findById.getTowerId());
		int updateAntenna = antennaMapper.updateAntenna(antenna);
		int updateRoom = roomMapper.updateRoom(room);
		int updateStationPersonnel = sPersonnelMapper.updateStationPersonnel(stationPersonnel);
		int updateTower = towerMapper.updateTower(tower);
		transmit.setTransmitId(tId);
		int updateTransmit = transmitMapper.updateTransmit(transmit);
		if (updateAntenna == 1 && updateRoom == 1 && updateStationPersonnel == 1 && updateTower == 1
				&& updateTransmit == 1) {
			transcation.commit();
			return 1;
		} else {
			transcation.rollback();
			return 0;
		}
	}

	/**
	 * 导入台站信息
	 * 
	 * @param files
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public boolean readFileToTransmit(CommonsMultipartFile[] files, String realPath) {
		String timeStr = LocalDate.now().toString() + System.currentTimeMillis() + "_";
		OperationFileUtils.uploadFIles(files, realPath, timeStr);
		int addTransmitStation = 0, addAntenna = 0;
		String encoding = "gbk", lineTxt = "";
		InputStreamReader isread = null;
		StringBuffer sBuffer = null;
		BufferedReader bufferedReader = null;
		try {
			for (int i = 0; i < files.length; i++) {
				File file = new File(realPath + timeStr + files[i].getOriginalFilename());
				if (file.isFile() && file.exists()) {
					isread = new InputStreamReader(new FileInputStream(file), encoding);
					bufferedReader = new BufferedReader(isread);
					sBuffer = new StringBuffer();
					while (lineTxt != null) {
						lineTxt = bufferedReader.readLine();
						sBuffer.append(lineTxt);
					}
					String string = sBuffer.toString().substring(0, sBuffer.toString().length() - 5);
					String[] transmitArray = string.split("-");
					Transmit transmit = null;
					Room room = null;
					StationPersonnel stationPersonnel = null;
					Antenna antenna = null;
					Tower tower = null;
					for (String transmitStr : transmitArray) {
						if (transmitStr != null) {
							String[] property = transmitStr.split(",");
							transmit = new Transmit(property[0].split(":")[1], property[1].split(":")[1],
									Float.parseFloat(property[2].split(":")[1]),
									Float.parseFloat(property[3].split(":")[1]),
									Float.parseFloat(property[4].split(":")[1]), LocalDateTime.now().toString(),
									"admin");
							room = new Room(property[12].split(":")[1], property[13].split(":")[1],
									property[14].split(":")[1], property[15].split(":")[1], property[16].split(":")[1]);
							stationPersonnel = new StationPersonnel(property[17].split(":")[1],
									property[18].split(":")[1], property[19].split(":")[1]);
							tower = new Tower(property[9].split(":")[1], property[10].split(":")[1],
									property[11].split(":")[1]);
							antenna = new Antenna(property[5].split(":")[1], property[6].split(":")[1],
									property[7].split(":")[1], property[8].split(":")[1]);
							addAntenna = addAntenna(antenna);
							addTransmitStation = addTransmitStation(antenna.getAntennaId(), room, stationPersonnel,
									tower, transmit);
							continue;
						}
					}
				} else {
					System.err.println("找不到指定的文件..");
				}
			}
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				isread.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (addTransmitStation == 1 && addAntenna == 1) ? true : false;
	}

	/**
	 * getchannel
	 */

	public List<Channels> listChannels() {
		return channelsMapper.listChannels();
	}

	/**
	 * addChannel
	 */

	public int addChannel(Channel channel) {
		return (channelMapper.findByChannel(channel) == null) ? channelMapper.addChannel(channel) : 0;
	}

	/**
	 * deleteChannel
	 */

	public int deleteChannel(String channel) {
		return channelMapper.deleteChannel(channel);
	}

	/**
	 * findByParam
	 *
	 * @throws Exception
	 */

	@SuppressWarnings("rawtypes")
	public List<TransmitStation> findByParam(Map map) throws Exception {
		return transmitStationMapper.findByParam(map);
	}

	/**
	 * 根据天线id 查询频率
	 */

	public List<Frequency> getFrequencys(int id) {
		return frequencyMapper.getFrequencys(id);
	}

	public int addFrequency(Frequency frequency) {
		return Help.isEmpty(frequencyMapper.findByFrequency(frequency)) ? frequencyMapper.addFrequency(frequency) : 0;
	}

	public int deleteFrequency(Float frequency) {
		return frequencyMapper.deleteFrequency(frequency);
	}

	public Channel findByChannel(Channel channel) {
		return channelMapper.findByChannel(channel);
	}

	public int addAntenna(Antenna antenna) {
		return antennaMapper.addAntenna(antenna);
	}

	public int deleteChannelById(int aid) {
		return channelMapper.deleteChannelById(aid);
	}

	public int deleteFrequencyById(int aid) {
		return frequencyMapper.deleteFrequencyById(aid);
	}

	public List<TransmitStation> listTransmitStation() {
		return transmitStationMapper.listTransmitStation();
	}

	static String transmitTab = "台站名称-台站地区-经度-纬度-海拔高度-天行型号-节目源接引方案-天线场形-天线安装设计-频道-频率-塔桅型号"
			+ "-塔桅结构-塔桅报告-机房位置-设备状况-供配电-防雷地接-空调通风及采暖-负责人-测试人员-负责人制度";

	/**
	 * 导出 transmit 数据到excel
	 * 
	 * @param tList
	 * @param frequ
	 * @param chann
	 * @param path
	 * @return
	 */
	public String exportTransmit(List<TransmitStation> tList, String path) {

		String[] split = transmitTab.split("-");
		// 创建HSSFWorkbook对象
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建HSSFCell对象
		HSSFSheet sheet = wb.createSheet("发射台站数据表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row0 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row0.createCell(0);
		// 设置单元格内容
		cell.setCellValue("发射台站数据表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 20));
		// 在sheet里创建第二行
		HSSFRow row1 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		for (int i = 0; i < split.length; i++) {
			row1.createCell(i).setCellValue(split[i]);
		}
		// 在sheet里创建第三行
		Iterator<TransmitStation> iterator = tList.iterator();
		int x = 2;
		while (iterator.hasNext()) {
			TransmitStation transmitStation = iterator.next();

			List<Channel> channels = getChannels(transmitStation.getaId());
			StringBuffer channelstr = new StringBuffer();
			for (int i = 0; i < channels.size(); i++) {
				channelstr.append(channels.get(i).getChannel() + ",");

			}
			List<Frequency> frequencys = getFrequencys(transmitStation.getaId());
			StringBuffer frequencystr = new StringBuffer();
			for (int i = 0; i < frequencys.size(); i++) {
				frequencystr.append(frequencys.get(i).getFrequency() + ",");
			}
			HSSFRow createRow = sheet.createRow(x);
			createRow.createCell(0).setCellValue(transmitStation.gettName());
			createRow.createCell(1).setCellValue(transmitStation.getArea());
			createRow.createCell(2).setCellValue(transmitStation.getLongitude());
			createRow.createCell(3).setCellValue(transmitStation.getLatitude());
			createRow.createCell(4).setCellValue(transmitStation.getHeight());
			createRow.createCell(5).setCellValue(transmitStation.getAntennaType());
			createRow.createCell(6).setCellValue(transmitStation.getProgramSource());
			createRow.createCell(7).setCellValue(transmitStation.getFieldPattern());
			createRow.createCell(8).setCellValue(transmitStation.getInstallPlan());

			createRow.createCell(9).setCellValue(channelstr.toString().substring(0, channelstr.length() - 1));
			createRow.createCell(10).setCellValue(frequencystr.toString().substring(0, frequencystr.length() - 1));
			createRow.createCell(11).setCellValue(transmitStation.getTowerType());
			createRow.createCell(12).setCellValue(transmitStation.getTowerFramework());
			createRow.createCell(13).setCellValue(transmitStation.getTowerReport());
			createRow.createCell(14).setCellValue(transmitStation.getRoomPosition());
			createRow.createCell(15).setCellValue(transmitStation.getRoomDeviceStatus());
			createRow.createCell(16).setCellValue(transmitStation.getPowerDistribution());
			createRow.createCell(17).setCellValue(transmitStation.getGrounding());
			createRow.createCell(18).setCellValue(transmitStation.getAirConditioner());
			createRow.createCell(19).setCellValue(transmitStation.getSupervisor());
			createRow.createCell(20).setCellValue(transmitStation.getTestPeople());
			createRow.createCell(21).setCellValue(transmitStation.getResponsiblePerson());
			x++;
		}
		// 输出Excel文件
		FileOutputStream output = null;
		try {
			path += LocalDate.now() + "_transmit.xls";
			output = new FileOutputStream(path);
			wb.write(output);
			output.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	/**
	 * 导出到 transmit到txt
	 * 
	 * @param data
	 * @param frequ
	 * @param chann
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public String exportDataToTxt(List<TransmitStation> data, String path) throws IOException {

		path = path + LocalDate.now() + "_" + System.currentTimeMillis() + "_transmit.txt";
		File file = new File(path);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		Iterator<TransmitStation> iterator = data.iterator();
		while (iterator.hasNext()) {
			TransmitStation transmit = iterator.next();
			List<Channel> channels = getChannels(transmit.getaId());
			StringBuffer channelstr = new StringBuffer();
			for (int i = 0; i < channels.size(); i++) {
				channelstr.append(channels.get(i).getChannel() + "_");
			}
			List<Frequency> frequencys = getFrequencys(transmit.getaId());
			StringBuffer frequencystr = new StringBuffer();
			for (int i = 0; i < frequencys.size(); i++) {
				frequencystr.append(frequencys.get(i).getFrequency() + "_");
			}

			try {
				bw.write("area:" + transmit.getArea() + ",");
				bw.newLine();
				bw.write("tName:" + transmit.gettName() + ",");
				bw.newLine();
				bw.write("longitude:" + transmit.getLongitude() + ",");
				bw.newLine();
				bw.write("latitude:" + transmit.getLatitude() + ",");
				bw.newLine();
				bw.write("height:" + transmit.getHeight() + ",");
				bw.newLine();

				bw.write("antennaType:" + transmit.getAntennaType() + ",");
				bw.newLine();
				bw.write("programSource:" + transmit.getProgramSource() + ",");
				bw.newLine();
				bw.write("fieldPattern:" + transmit.getFieldPattern() + ",");
				bw.newLine();
				bw.write("installPlan:" + transmit.getInstallPlan() + ",");
				bw.newLine();

				bw.write("channel:" + channelstr.substring(0, channelstr.length()-1) + ",");
				bw.newLine();
				bw.write("frequency:" + frequencystr.substring(0, frequencystr.length()-1) + ",");
				bw.newLine();

				bw.write("towerType:" + transmit.getTowerType() + ",");
				bw.newLine();
				bw.write("towerFramework:" + transmit.getTowerFramework() + ",");
				bw.newLine();
				bw.write("towerReport:" + transmit.getTowerReport() + ",");
				bw.newLine();

				bw.write("roomPosition:" + transmit.getRoomPosition() + ",");
				bw.newLine();
				bw.write("roomDeviceStatus:" + transmit.getRoomDeviceStatus() + ",");
				bw.newLine();
				bw.write("powerDistribution:" + transmit.getPowerDistribution() + ",");
				bw.newLine();
				bw.write("grounding:" + transmit.getGrounding() + ",");
				bw.newLine();
				bw.write("airConditioner:" + transmit.getAirConditioner() + ",");
				bw.newLine();

				bw.write("supervisor:" + transmit.getSupervisor() + ",");
				bw.newLine();
				bw.write("testPeople:" + transmit.getTestPeople() + ",");
				bw.newLine();
				bw.write("responsiblePerson:" + transmit.getResponsiblePerson());
				bw.newLine();
				bw.write("-");
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
