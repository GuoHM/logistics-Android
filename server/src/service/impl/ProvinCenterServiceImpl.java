package service.impl;

import java.util.List;

import bean.ProvinceCenter;
import bean.Transportation;
import bean.TransportationManagement;
import bean.TransportationManagementId;
import dao.IProvinceCenterDao;
import service.IProvinceCenterService;

public class ProvinCenterServiceImpl implements IProvinceCenterService {
	private IProvinceCenterDao provinceCenterDao;

	public ProvinceCenter getProvinceCenterByIDAndPwd(String login,
			String password) throws Exception {
		// TODO Auto-generated method stub
		boolean isValid = login != null && password != null;
		if (!isValid) {
			return null;
		}
		return provinceCenterDao.getProvinceCenterByIDAndPwd(login, password);
	}

	public void save(ProvinceCenter user) throws Exception {
		if(user!=null){
			provinceCenterDao.save(user);
		}
	  
	}
	
	public ProvinceCenter getProvinceCenterByProvinceName(String ProvinceName) throws Exception {
		if(ProvinceName!=null)
			return provinceCenterDao.getProvinceCenterByProvinceName(ProvinceName);
		else
			return null;
		
	}

	public List<Transportation> getTransportationlistByDD(String senderProvince, String receiverProvince) {
		if(senderProvince!=null&&receiverProvince!=null){
			return provinceCenterDao.getTransportationlistByDD(senderProvince, receiverProvince);
		}else{
			return null;
		}
	}
	@Override
	public void save(TransportationManagement transportation) throws Exception {
		if(transportation!=null){
			provinceCenterDao.save(transportation);
		}
		
	}
	@Override
	public Transportation getTransportationByID(String ID) throws Exception {
		if(ID!=null){
			return 	provinceCenterDao.getTransportationByID(ID);
		}else{
			return null;
		}
	}
	@Override
	public ProvinceCenter getProvinceCenterByID(int id) throws Exception {
			return provinceCenterDao.getProvinceCenterByID(id);
	}
	public IProvinceCenterDao getProvinceCenterDao() {
		return provinceCenterDao;
	}

	public void setProvinceCenterDao(IProvinceCenterDao provinceCenterDao) {
		this.provinceCenterDao = provinceCenterDao;
	}



	
	


}
