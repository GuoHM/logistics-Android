package dao.impl;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bean.DistrictCenter;
import dao.IDistrictCenterDao;

public class DistrictCenterDaoImpl extends HibernateDaoSupport implements IDistrictCenterDao{

	public DistrictCenter getDistrictCenterByIDAndPwd(String login, String password)
			throws Exception {
		String hql = "from DistrictCenter where centerId=? and pwd=?";
        Session session = null;
        DistrictCenter user = null;
        try {
            session = getSession();
            user = (DistrictCenter) session.createQuery(hql).setParameter(0, Integer.parseInt(login)).setParameter(1, password)
                    .uniqueResult();
        } finally {
            releaseSession(session);
        }
        return user;
	}

	@Override
	public void save(DistrictCenter user) throws Exception {
		   getHibernateTemplate().saveOrUpdate(user);
		
	}

	@Override
	public DistrictCenter getDistrictCenter(String district, String city, String province) throws Exception {
		String hql = "from DistrictCenter where district=? and city=? and province=?";
        Session session = null;
        DistrictCenter user = null;
        try {
            session = getSession();
            user = (DistrictCenter) session.createQuery(hql).setParameter(0,district).setParameter(1, city).setParameter(2, province)
                    .uniqueResult();
        } finally {
            releaseSession(session);
        }
        return user;
	}

	@Override
	public DistrictCenter getDistrictCenterByID(int id) throws Exception {
		String hql = "from DistrictCenter where centerId=?";
        Session session = null;
        DistrictCenter user = null;
        try {
            session = getSession();
            user = (DistrictCenter) session.createQuery(hql).setParameter(0, id)
                    .uniqueResult();
        } finally {
            releaseSession(session);
        }
        return user;
	}

}


