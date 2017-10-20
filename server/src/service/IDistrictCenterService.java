package service;

import bean.DistrictCenter;

public interface IDistrictCenterService {
	DistrictCenter getDistrictCenterByIDAndPwd(String id, String pwd) throws Exception;

	void save(DistrictCenter user) throws Exception;
	DistrictCenter getDistrictCenter(String district) throws Exception;
	DistrictCenter getDistrictCenterByID(int id)throws Exception;
}
