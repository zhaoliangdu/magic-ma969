package com.ma969.dao;

import java.util.List;

import com.ma969.beans.TvSignal;

/**
 * 
 * @author zhao
 *
 */
public interface TvSignalMapper {

	/**
	 * 获取tvsignal
	 * @return
	 */
	List<TvSignal> listTVSignals();

	/**
	 * 添加tvsignal
	 * @param tvSignal
	 * @return
	 */
	int saveTVSignal(TvSignal tvSignal);

	/**
	 * 修改tvsignal
	 * @param tvSignal
	 * @return
	 */
	int updateTVSignal(TvSignal tvSignal);

	/**
	 * 根据id返回tvsignal
	 * @param id
	 * @return
	 */
	TvSignal findTVSignalById(int id);

	/**
	 * 删除tvtvsignal
	 * @param id
	 * @return
	 */
	int deleteTVSignal(int id);
}
