package service.impl;

import java.util.List;

import bean.GoodsStatus;
import dao.IGoodsStatusDao;
import service.IGoodsStatusService;

public class GoodsStatusServiceImpl implements IGoodsStatusService {
	private IGoodsStatusDao goodsStatusDao;

	@Override
	public List<GoodsStatus> getGoodsStatusByGoodsId(String goodsId) throws Exception {
		// TODO Auto-generated method stub
		if(goodsId != null && !goodsId.equals("")) {
			return goodsStatusDao.getGoodsStatusBygoodsId(goodsId);
		}
		return null;
	}

	/**
	 * @return the goodsStatusDao
	 */
	public IGoodsStatusDao getGoodsStatusDao() {
		return goodsStatusDao;
	}

	/**
	 * @param goodsStatusDao the goodsStatusDao to set
	 */
	public void setGoodsStatusDao(IGoodsStatusDao goodsStatusDao) {
		this.goodsStatusDao = goodsStatusDao;
	}

	@Override
	public void save(GoodsStatus goodsStatus) throws Exception {
		// TODO Auto-generated method stub
		if(goodsStatus != null) {
			goodsStatusDao.save(goodsStatus);
		}
	}
	
	

}
