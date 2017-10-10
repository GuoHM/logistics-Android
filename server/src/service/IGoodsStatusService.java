package service;

import java.util.List;

import bean.GoodsStatus;

public interface IGoodsStatusService {
	List<GoodsStatus> getGoodsStatusByGoodsId(String goodsId) throws Exception;
	
	void save(GoodsStatus goodsStatus) throws Exception;

}
