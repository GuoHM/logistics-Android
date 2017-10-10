package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bean.Conditions;
import bean.Goods;
import bean.GoodsStatus;
import bean.GoodsStatusId;
import dao.IGoodsDao;

public class GoodsDaoImpl extends HibernateDaoSupport implements IGoodsDao {

	@Override
	public void save(Goods goods) throws Exception {
		getHibernateTemplate().saveOrUpdate(goods);
	}

	@Override
	public Goods getGoodsBygoodsId(String goodsId) {
		String hql = "from Goods where goodsId=?";
		Session session = null;
		Goods goods = null;
		try {
			session = getSession();
			goods = (Goods) session.createQuery(hql).setParameter(0, goodsId).uniqueResult();
		} finally {
			releaseSession(session);
		}
		return goods;
	}

	@SuppressWarnings("unchecked")
	public List<Goods> getGoodsByDistrict(String district,String city, String province) throws Exception {//获取区县营业点未发往本省的商品订单链�?
		String hql = "from Goods where senderDistrict=? and senderCity=? and senderProvince=?";
		Session session = null;
		List<Goods> list = null;
		try {
			session = getSession();
			list = (List<Goods>) session.createQuery(hql).setParameter(0, district).setParameter(1, city).setParameter(2,province)
					.list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<GoodsStatusId> getGoodsStatusBygoodsId(String goodsId) throws Exception {//根据账单号获取状态链�?
		String hql = "from GoodsStatus where goodsId=? ";
		Session session = null;
		List<GoodsStatusId> list = null;
		try {
			session = getSession();
			list = (List<GoodsStatusId>) session.createQuery(hql).setParameter(0, goodsId).list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

	
	@Override
	public void save(Conditions conditions) throws Exception {
		getHibernateTemplate().saveOrUpdate(conditions);
		
	}

	@SuppressWarnings("unchecked")
	public List<Goods> getGoodsByProvince(String province) throws Exception {//获取省商品链�?
		String hql = "from Goods where senderProvince=?";
		Session session = null;
		List<Goods> list = null;
		try {
			session = getSession();
			list = (List<Goods>) session.createQuery(hql).setParameter(0, province).list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Goods> getGoodsByreceiverProvince(String province) throws Exception {
		String hql = "from Goods where receiverProvince=?";
		Session session = null;
		List<Goods> list = null;
		try {
			session = getSession();
			list = (List<Goods>) session.createQuery(hql).setParameter(0, province).list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Goods> getGoodsByreceiverDistrict(String district, String city, String province) throws Exception {
		String hql = "from Goods where receiverDistrict=? and receiverCity=? and receiverProvince=?";
		Session session = null;
		List<Goods> list = null;
		try {
			session = getSession();
			list = (List<Goods>) session.createQuery(hql).setParameter(0, district).setParameter(1, city).setParameter(2,province)
					.list();
		} finally {
			releaseSession(session);
		}
		return list;
	}
}
