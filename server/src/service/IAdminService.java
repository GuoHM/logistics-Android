package service;

import java.util.List;

import bean.Admin;
import bean.DistrictCenter;
import bean.ProvinceCenter;

public interface IAdminService {
	Admin getAdminByLoginAndPassword(String login, String password) throws Exception;
	void save(Admin user) throws Exception;
	List<ProvinceCenter> getProvinceList() throws Exception;
	List<DistrictCenter> getDistrictList(String province,String city)throws Exception;
	void deleteProvince(ProvinceCenter user)throws Exception;
	void deleteDistrict(DistrictCenter user)throws Exception;
}
