package service;

import java.util.List;

import bean.Conditions;
import bean.Goods;
import bean.GoodsStatus;

public interface IGoodsService {
	void save(Goods goods) throws Exception;

	Goods getGoodsBygoodsId(String goodsId);

	List<Goods> getGoodsByDistrict(String district,String city, String province) throws Exception;
	List<Goods> getGoodsByreceiverDistrict(String district,String city, String province) throws Exception;
	List<Goods> getGoodsByProvince( String province) throws Exception;
	List<Goods> getGoodsByreceiverProvince( String province) throws Exception;
}
