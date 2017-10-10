package dao.impl;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import bean.Admin;
import bean.DistrictCenter;
import bean.Goods;
import bean.ProvinceCenter;
import dao.IAdminDao;
public class AdminDaoImpl extends HibernateDaoSupport implements IAdminDao {    //���������˺�����������ݿ⣬�鿴�û��Ƿ����
	 public Admin getAdminByIDAndPwd(String login, String password) throws Exception {
	        // TODO Auto-generated method stub
	        String hql = "from Admin where adminId=? and pwd=?";
	        Session session = null;
	        Admin user = null;
	        try {
	            session = getSession();
	            user = (Admin) session.createQuery(hql).setParameter(0, login).setParameter(1, password)
	                    .uniqueResult();
	        } finally {
	            releaseSession(session);
	        }
	        return user;
	    }

	public void save(Admin user) throws Exception {

		        getHibernateTemplate().saveOrUpdate(user);
		    }

	@SuppressWarnings("unchecked")
	public List<ProvinceCenter> getProvinceList() throws Exception {
		String hql = "from ProvinceCenter";
		Session session = null;
		List<ProvinceCenter> list = null;
		try {
			session = getSession();
			list = (List<ProvinceCenter>) session.createQuery(hql).list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DistrictCenter> getDistrictList(String province,String city) throws Exception {
		String hql = "from DistrictCenter where province=? and city=? ";
		Session session = null;
		List<DistrictCenter> list = null;
		try {
			session = getSession();
			list = (List<DistrictCenter>) session.createQuery(hql).setParameter(0, province).setParameter(1, city).list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

	@Override
	public void deleteProvince(ProvinceCenter user) throws Exception {
		 getHibernateTemplate().delete(user);
		
	}

	@Override
	public void deleteDistrict(DistrictCenter user) throws Exception {
		 getHibernateTemplate().delete(user);
		
	}

}