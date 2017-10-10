package dao;

import java.util.List;

import bean.Admin;
import bean.ProvinceCenter;
import bean.DistrictCenter;

public interface IAdminDao {
Admin getAdminByIDAndPwd(String id,String pwd) throws Exception;
void save(Admin user) throws Exception;
List<ProvinceCenter> getProvinceList() throws Exception;
List<DistrictCenter> getDistrictList(String province,String city)throws Exception;
void deleteProvince(ProvinceCenter user)throws Exception;
void deleteDistrict(DistrictCenter user)throws Exception;
}
