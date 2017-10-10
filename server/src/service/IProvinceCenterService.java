package service;

import java.util.List;

import bean.ProvinceCenter;
import bean.Transportation;
import bean.TransportationManagement;
import bean.TransportationManagementId;
public interface IProvinceCenterService {
	ProvinceCenter getProvinceCenterByIDAndPwd(String id,String pwd) throws Exception;
	 void save(ProvinceCenter user) throws Exception;
	 ProvinceCenter getProvinceCenterByProvinceName(String ProvinceName) throws Exception;
	 List<Transportation> getTransportationlistByDD(String senderProvince,String receiverProvince);
	void save(TransportationManagement transportation) throws Exception;
	Transportation getTransportationByID(String ID)throws Exception;
	ProvinceCenter getProvinceCenterByID(int id)throws Exception;
}
