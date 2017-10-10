package dao.impl;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bean.Conditions;
import dao.IConditionsDao;
import dao.IGoodsDao;

public class ConditionsDaoImpl extends HibernateDaoSupport implements IConditionsDao {

	@Override
	public Conditions getConditionByConditionID(String conditionID) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from Conditions where conditionID=? ";
		Session session = null;
		Conditions conditions = null;
		try {
			session = getSession();
			conditions = (Conditions) session.createQuery(hql).setParameter(0, conditionID).uniqueResult();
		} finally {
			releaseSession(session);
		}
		return conditions;
	}

}
