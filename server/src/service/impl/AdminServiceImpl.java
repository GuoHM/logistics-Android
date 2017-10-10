package service.impl;

import java.util.List;

import bean.Admin;
import bean.DistrictCenter;
import bean.ProvinceCenter;
import dao.IAdminDao;
import service.IAdminService;

public class AdminServiceImpl implements IAdminService {
	private IAdminDao adminDao;

	public Admin getAdminByLoginAndPassword(String login, String password)
			throws Exception {
		// TODO Auto-generated method stub
		boolean isValid = login != null && password != null;
		if (!isValid) {
			return null;
		}
		return adminDao.getAdminByIDAndPwd(login, password);
	}
	public void save(Admin user) throws Exception {
		   if (user != null) {
			   adminDao.save(user);
		   }
		
	}

	@Override
	public List<ProvinceCenter> getProvinceList() throws Exception {
		return adminDao.getProvinceList();
	}
	@Override
	public List<DistrictCenter> getDistrictList(String province, String city) throws Exception {
		if(province!=null&&city!=null){
			return adminDao.getDistrictList(province, city);
		}else{
			return null;
		}
			
	}
	@Override
	public void deleteProvince(ProvinceCenter user) throws Exception {
		if(user!=null){
			 adminDao.deleteProvince(user);
		}
		
	}
	@Override
	public void deleteDistrict(DistrictCenter user) throws Exception {
		if(user!=null){
			adminDao.deleteDistrict(user);
		}
		
	}

	public IAdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}
	


}
