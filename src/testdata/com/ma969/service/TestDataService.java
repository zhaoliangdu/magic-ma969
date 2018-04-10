
package com.ma969.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ma969.beans.AnalogData;
import com.ma969.beans.CDRData;
import com.ma969.beans.DataMode;
import com.ma969.beans.DigitalData;
import com.ma969.beans.RadioData;
import com.ma969.beans.SystemSet;
import com.ma969.beans.TvSignal;
import com.ma969.dao.AnalogDataMapper;
import com.ma969.dao.CDRDataMapper;
import com.ma969.dao.DataModeMapper;
import com.ma969.dao.DigitalDataMapper;
import com.ma969.dao.RadioDataMapper;
import com.ma969.dao.TvSignalMapper;
import com.ma969.utils.CalculationTools;
import com.ma969.utils.OperationFileUtils;

/**
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年12月12日下午4:52:21
 */
@Service
public class TestDataService {
	@Autowired
	RadioDataService rService;
	@Autowired
	DigitalDataService dService;
	@Autowired
	AnalogDataService aService;
	@Autowired
	CDRDataService cService;
	@Autowired
	RadioDataMapper rMapper;
	@Autowired
	DigitalDataMapper dMapper;
	@Autowired
	AnalogDataMapper aMapper;
	@Autowired
	CDRDataMapper cMapper;
	@Autowired
	TvSignalMapper tvSignalMapper;
	@Autowired
	SystemSetService setService;
	@Autowired
	DataModeMapper dataModeMapper;

	private int testmode1 = 1, testmode2 = 2, testmode3 = 3, testmode4 = 4;

	/**
	 * 读取文件到数据库
	 * 
	 * @param files
	 * @param area
	 * @param eid
	 * @param filePath
	 * @return
	 */
	public boolean readFileToDataBase(CommonsMultipartFile files[], String area, int eid, String realPath) {
		String timeStr = LocalDate.now().toString() + System.currentTimeMillis() + "_";
		OperationFileUtils.uploadFIles(files, realPath, timeStr);
		for (int i = 0; i < files.length; i++) {
			try {
				String encoding = "GBK";
				File file = new File(realPath + timeStr + files[i].getOriginalFilename());

				if (file.isFile() && file.exists()) {
					InputStreamReader isread = new InputStreamReader(new FileInputStream(file), encoding);
					BufferedReader bufferedReader = new BufferedReader(isread);
					int t = 0;
					String lineTxt = bufferedReader.readLine();
					readfile: while (lineTxt != null) {
						if (t >= 1) {
							String[] pointarr = lineTxt.split("\t");

							if ("调频".equals(pointarr[5]) || "调幅".equals(pointarr[5])) {
								if (rService.readerFileToRadioData(realPath + timeStr + files[i].getOriginalFilename(),
										area, eid)) {
									break readfile;
								} else {
									return false;
								}
							} else if ("数字电视".equals(pointarr[5])) {
								if (dService.readerFileToTestData(realPath + timeStr + files[i].getOriginalFilename(),
										area, eid) == 1) {
									break readfile;
								} else {
									return false;
								}
							} else if ("CDR".equals(pointarr[5])) {
								if (cService.readerFileToTestData(realPath + timeStr + files[i].getOriginalFilename(),
										area, eid) == 1) {
									break readfile;
								} else {
									return false;
								}
							} else if ("模拟电视".equals(pointarr[5])) {
								if (aService.readerFileToTestData(realPath + timeStr + files[i].getOriginalFilename(),
										area, eid) == 1) {
									break readfile;
								} else {
									return false;
								}
							}
						}
						t++;
						lineTxt = bufferedReader.readLine();
					}
					isread.close();
					bufferedReader.close();
				} else {
					System.err.println("找不到指定的文件..");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return true;
	}

	public List<Map<String, Object>> getPointStatistics() {
		List<DataMode> listDataModes = dataModeMapper.listDataModes();
		int acount = aMapper.getSampleCount();
		int ccount = cMapper.getSampleCount();
		int dcount = dMapper.getSampleCount();
		int rcount = rMapper.getSampleCount();
		List<Map<String, Object>> listmap = new ArrayList();
		HashMap map1 = new HashMap<>();
		map1.put("url", "digitaldata");
		map1.put("testmode", "数字电视");
		map1.put("count", dcount);
		listmap.add(map1);
		HashMap map2 = new HashMap<>();
		map2.put("url", "analogdata");
		map2.put("testmode", "模拟电视");
		map2.put("count", acount);
		listmap.add(map2);
		HashMap map3 = new HashMap<>();
		map3.put("url", "cdrdata");
		map3.put("testmode", "CDR");
		map3.put("count", ccount);
		listmap.add(map3);
		HashMap map4 = new HashMap<>();
		map4.put("url", "radiodata");
		map4.put("testmode", "调幅调频");
		map4.put("count", rcount);
		listmap.add(map4);
		System.err.println(listmap);
		return listmap;
	}

	/**
	 * 获取场强矩形图坐标
	 * 
	 * @return
	 */
	public Map<String, Float> getFieldRectangle(int testmodeId) {
		Map<String, Float> map = new HashMap<>(16);
		if (testmodeId == testmode1) {
			map.put("maxlng", dMapper.findMaxLng());
			map.put("minlng", dMapper.findMinLng());
			map.put("maxlat", dMapper.findMaxLat());
			map.put("minlat", dMapper.findMinLat());
		} else if (testmodeId == testmode2) {
			map.put("maxlng", rMapper.findMaxLng());
			map.put("minlng", rMapper.findMinLng());
			map.put("maxlat", rMapper.findMaxLat());
			map.put("minlat", rMapper.findMinLat());
		} else if (testmodeId == testmode3) {
			map.put("maxlng", cMapper.findMaxLng());
			map.put("minlng", cMapper.findMinLng());
			map.put("maxlat", cMapper.findMaxLat());
			map.put("minlat", cMapper.findMinLat());
		} else if (testmodeId == testmode4) {
			map.put("maxlng", aMapper.findMaxLng());
			map.put("minlng", aMapper.findMinLng());
			map.put("maxlat", aMapper.findMaxLat());
			map.put("minlat", aMapper.findMinLat());
		}
		return map;
	}

	/**
	 * 按照分段把采样点分类
	 * 
	 * @param pmap
	 * @return
	 */
	public List<List<TreeMap<String, Float>>> getFieldRangeList(int uid, int testModeId) {
		SystemSet systemSet = setService.getSystemSet(uid);

		String fieldRange = systemSet.getFieldRange();
		String[] fieldRangeArray = fieldRange.split(",");
		List<List<TreeMap<String, Float>>> pointList = new ArrayList<>();
		List<Map<String, Float>> rangeList = new ArrayList<>();
		for (int i = 0; i < fieldRangeArray.length; i++) {
			Map<String, Float> ranmap = new HashMap<>(16);
			ranmap.put("downfield", Float.parseFloat((String) fieldRangeArray[i].split("-")[0].trim()));
			ranmap.put("upfield", Float.parseFloat((String) fieldRangeArray[i].split("-")[1].trim()));
			rangeList.add(ranmap);
		}
		List<DigitalData> digitalDatas = dService.listDigitalDatas();
		List<RadioData> radioDatas = rService.listRadioDatas();
		List<AnalogData> analogDatas = aService.listAnalogDatas();
		List<CDRData> cdrDatas = cService.listCDRDatas();
		String downfield = "downfield", upfield = "upfield";
		rangeList.forEach(rangelist -> {
			List<TreeMap<String, Float>> rangeIList = new ArrayList<>();
			if (testModeId == testmode1) {
				digitalDatas.forEach(digitalData -> {
					if (digitalData.getFieldStrength() >= rangelist.get(downfield)
							&& digitalData.getFieldStrength() < rangelist.get(upfield)) {
						TreeMap<String, Float> pointmap = new TreeMap<>();
						pointmap.put("field", digitalData.getFieldStrength());
						pointmap.put("lng", digitalData.getTransforLng());
						pointmap.put("lat", digitalData.getTransforLat());
						pointmap.put("distance", digitalData.getDistance());
						pointmap.put("angle", digitalData.getAngle());
						rangeIList.add(pointmap);
					}
				});
			} else if (testModeId == testmode2) {
				radioDatas.forEach(radioData -> {
					if (radioData.getFieldStrength() >= rangelist.get(downfield)
							&& radioData.getFieldStrength() < rangelist.get(upfield)) {
						TreeMap<String, Float> pointmap = new TreeMap<>();
						pointmap.put("field", radioData.getFieldStrength());
						pointmap.put("lng", radioData.getTransforLng());
						pointmap.put("lat", radioData.getTransforLat());
						pointmap.put("distance", radioData.getDistance());
						pointmap.put("angle", radioData.getAngle());
						rangeIList.add(pointmap);
					}
				});
			} else if (testModeId == testmode3) {
				cdrDatas.forEach(cdrData -> {
					if (cdrData.getFieldStrength() >= rangelist.get(downfield)
							&& cdrData.getFieldStrength() < rangelist.get(upfield)) {
						TreeMap<String, Float> pointmap = new TreeMap<>();
						pointmap.put("field", cdrData.getFieldStrength());
						pointmap.put("lng", cdrData.getTransforLng());
						pointmap.put("lat", cdrData.getTransforLat());
						pointmap.put("distance", cdrData.getDistance());
						pointmap.put("angle", cdrData.getAngle());
						rangeIList.add(pointmap);
					}
				});
			} else if (testModeId == testmode4) {
				analogDatas.forEach(analogData -> {
					if (analogData.getFieldStrength() >= rangelist.get(downfield)
							&& analogData.getFieldStrength() < rangelist.get(upfield)) {
						TreeMap<String, Float> pointmap = new TreeMap<>();
						pointmap.put("field", analogData.getFieldStrength());
						pointmap.put("lng", analogData.getTransforLng());
						pointmap.put("lat", analogData.getTransforLat());
						pointmap.put("distance", analogData.getDistance());
						pointmap.put("angle", analogData.getAngle());
						rangeIList.add(pointmap);
					}
				});
			}
			pointList.add(rangeIList);
		});
		return pointList;
	}

	/**
	 * 场强图信息 场强图 粗略 算法
	 * 
	 * @param uid
	 * @param testModeId
	 * @return
	 */
	public List<List<TreeMap<String, Float>>> getFieldOverlay(int uid, int testModeId) {
		List<List<TreeMap<String, Float>>> fieldOverlayList = new ArrayList<>();
		List<List<TreeMap<String, Float>>> fieldRangeList = getFieldRangeList(uid, testModeId);
		Map<String, Float> fieldRectangle = getFieldRectangle(testModeId);

		// 第一个点
		float maxlng = fieldRectangle.get("maxlng");
		float minlat = fieldRectangle.get("minlat");
		// 第二个点
		// maxlng
		float maxlat = fieldRectangle.get("maxlat");
		// 第三个点
		float minlng = fieldRectangle.get("minlng");
		// maxlat
		// 第四个点
		// minlng minlat

		// 计算矩形的w和h
		float height = CalculationTools.getDistance(minlat, maxlng, maxlat, maxlng);
		System.err.println("height:" + height);
		float width = CalculationTools.getDistance(minlat, maxlng, minlat, minlng);
		System.err.println("width:" + width);
		// width:94778.11 height:72224.055
		// 小正方形 边长50 总面积6845246272 一共273698个格子

		fieldRangeList.forEach(fieldRanList -> {
			List<TreeMap<String, Float>> newmapList = new ArrayList<>();
			int angle = 360, increasing = 5;
			fieldRanList.forEach(treeMap -> {
				for (int a = 0; a <= angle; a += increasing) {
					if (Math.abs(a - treeMap.get("angle")) < 1) {
						newmapList.add(treeMap);
					}
				}
			});
			fieldOverlayList.add(newmapList);
		});
		return fieldOverlayList;
	}

	public List<TvSignal> listTVSignals() {
		return tvSignalMapper.listTVSignals();
	}

	public int saveTVSignal(TvSignal tvSignal) {
		return tvSignalMapper.saveTVSignal(tvSignal);
	}

	public int updateTVSignal(TvSignal tvSignal) {
		return tvSignalMapper.updateTVSignal(tvSignal);
	}

	public TvSignal findTVSignalById(int id) {
		return tvSignalMapper.findTVSignalById(id);
	}

	public int deleteTVSignal(int id) {
		return tvSignalMapper.deleteTVSignal(id);
	}

}
