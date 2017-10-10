package dao;

import java.util.List;

import bean.GoodsStatus;

public interface IGoodsStatusDao {
	
	List<GoodsStatus> getGoodsStatusBygoodsId(String goodsId) throws Exception;
	
	void save(GoodsStatus goodsStatus) throws Exception;

}
