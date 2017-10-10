package dao.impl;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bean.DistrictCenter;
import bean.GoodsStatusId;
import bean.ProvinceCenter;
import bean.Transportation;
import bean.TransportationManagement;
import bean.TransportationManagementId;
import dao.IProvinceCenterDao;
public class ProvinceCenterDaoImpl extends HibernateDaoSupport implements IProvinceCenterDao {    //���������˺�����������ݿ⣬�鿴�û��Ƿ����
	 public ProvinceCenter getProvinceCenterByIDAndPwd(String login, String password) throws Exception {
	        // TODO Auto-generated method stub
	        String hql = "from ProvinceCenter where centerId=? and pwd=?";
	        Session session = null;
	        ProvinceCenter user = null;
	        try {
	            session = getSession();
	            user = (ProvinceCenter) session.createQuery(hql).setParameter(0, Integer.parseInt(login)).setParameter(1, password)
	                    .uniqueResult();
	        } finally {
	            releaseSession(session);
	        }
	        return user;
	    }

	@Override
	public void save(ProvinceCenter user) throws Exception {
		   getHibernateTemplate().saveOrUpdate(user);
	}

	@Override
	public ProvinceCenter getProvinceCenterByProvinceName(String ProvinceName) throws Exception {//根据省的名字获取省实�?
		   String hql = "from ProvinceCenter where province=?";
	        Session session = null;
	        ProvinceCenter province = null;
	        try {
	            session = getSession();
	            province = (ProvinceCenter) session.createQuery(hql).setParameter(0, ProvinceName)
	                    .uniqueResult();
	        } finally {
	            releaseSession(session);
	        }
	        return province;
	}

	@SuppressWarnings("unchecked")
	public List<Transportation> getTransportationlistByDD(String senderProvince, String receiverProvince) {
		String hql = "from Transportation where departure=? and destintion=?";
		Session session = null;
		List<Transportation> list = null;
		try {
			session = getSession();
			list = (List<Transportation>) session.createQuery(hql).setParameter(0, senderProvince).setParameter(1, receiverProvince).list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

	@Override
	public void save(TransportationManagement transportation) throws Exception {
		 getHibernateTemplate().saveOrUpdate(transportation);
		
	}

	@Override
	public Transportation getTransportationByID(String ID) throws Exception {
		 String hql = "from Transportation where transportationID=?";
	        Session session = null;
	        Transportation transportation = null;
	        try {
	            session = getSession();
	            transportation = (Transportation) session.createQuery(hql).setParameter(0, ID)
	                    .uniqueResult();
	        } finally {
	            releaseSession(session);
	        }
	        return transportation;
	}

	@Override
	public ProvinceCenter getProvinceCenterByID(int id) throws Exception {
		String hql = "from ProvinceCenter where centerId=?";
        Session session = null;
        ProvinceCenter user = null;
        try {
            session = getSession();
            user = (ProvinceCenter) session.createQuery(hql).setParameter(0, id)
                    .uniqueResult();
        } finally {
            releaseSession(session);
        }
        return user;
	}
	}