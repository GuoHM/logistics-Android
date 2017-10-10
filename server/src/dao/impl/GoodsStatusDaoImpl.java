package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bean.GoodsStatus;

import dao.IGoodsStatusDao;

public class GoodsStatusDaoImpl extends HibernateDaoSupport implements IGoodsStatusDao {

	@Override
	public List<GoodsStatus> getGoodsStatusBygoodsId(String goodsId) throws Exception {//根据账单号获取状态链�?
		String hql = "from GoodsStatus t where t.id.goodsId=? ";
		Session session = null;
		List<GoodsStatus> list = null;
		try {
			session = getSession();
			list = (List<GoodsStatus>) session.createQuery(hql).setParameter(0, goodsId).list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

	@Override
	public void save(GoodsStatus goodsStatus) throws Exception {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(goodsStatus);
	}

}
