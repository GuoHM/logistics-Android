package web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

import bean.DistrictCenter;

import bean.ProvinceCenter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

import service.IAdminService;
import service.IDistrictCenterService;
import service.IProvinceCenterService;

public  class AdminAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1405926533311347411L;
	ActionContext context = ActionContext.getContext();
	protected HttpServletRequest servletRequest = null;
	Logger logger = Logger.getLogger(AllAction.class);
	private IProvinceCenterService provinceCenterService;
	private IDistrictCenterService districtCenterService;
	private IAdminService adminService;
	private String district;
	private String newpassword;
	private String city;
	private String province;
	private String provinceName;
	private String cityName;
	private int centerId;

	public String addDistrict() throws Exception {
		DistrictCenter user = new DistrictCenter();
		user.setDistrict(district);
		user.setCity(city);
		user.setProvince(province);
		user.setPwd(newpassword);
		if (user != null) {
			districtCenterService.save(user);
			return "addDistrictSuccess";
		}
		return "addDistrictFalse";
	}

	public String addProvince() throws Exception {
		ProvinceCenter user = new ProvinceCenter();
		user.setProvince(province);
		user.setPwd(newpassword);
		if (user != null) {
			provinceCenterService.save(user);
			return "addProvinceSuccess";
		}
		return "addprovinceFalse";
	}

	public String getProvinceList() throws Exception {
		List<ProvinceCenter> list = adminService.getProvinceList();
		if (list != null) {
			context.getSession().put("Provincelist", list);
			return "getProvincelistSuccess";
		} else {
			return "getProvincelistFalse";
		}
	}

	public String getDistrictList() throws Exception {
		List<DistrictCenter> list = adminService.getDistrictList(provinceName, cityName);
		if (list != null) {
			context.getSession().put("Districtlist", list);
			return "getDistrictlistSuccess";
		} else {
			return "getDistrictlistFalse";
		}
	}
	public String deleteDistrict()throws Exception{
		DistrictCenter districtCenter=districtCenterService.getDistrictCenterByID(centerId);
		if(districtCenter!=null){
		adminService.deleteDistrict(districtCenter);
		return "success";
		}else{
			return "false";
		}
		
	}
	
	public String deleteProvince()throws Exception{
		ProvinceCenter provinceCenter=provinceCenterService.getProvinceCenterByID(centerId);
		if(provinceCenter!=null){
		adminService.deleteProvince(provinceCenter);
		return "success";
		}else{
			return "false";
		}
		
	}

	/**
	 * @return the context
	 */
	public ActionContext getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(ActionContext context) {
		this.context = context;
	}

	/**
	 * @return the servletRequest
	 */
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	/**
	 * @param servletRequest the servletRequest to set
	 */
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * @param logger the logger to set
	 */
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * @return the provinceCenterService
	 */
	public IProvinceCenterService getProvinceCenterService() {
		return provinceCenterService;
	}

	/**
	 * @param provinceCenterService the provinceCenterService to set
	 */
	public void setProvinceCenterService(IProvinceCenterService provinceCenterService) {
		this.provinceCenterService = provinceCenterService;
	}

	/**
	 * @return the districtCenterService
	 */
	public IDistrictCenterService getDistrictCenterService() {
		return districtCenterService;
	}

	/**
	 * @param districtCenterService the districtCenterService to set
	 */
	public void setDistrictCenterService(IDistrictCenterService districtCenterService) {
		this.districtCenterService = districtCenterService;
	}

	/**
	 * @return the adminService
	 */
	public IAdminService getAdminService() {
		return adminService;
	}

	/**
	 * @param adminService the adminService to set
	 */
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	
	/**
	 * @return the newpassword
	 */
	public String getNewpassword() {
		return newpassword;
	}

	/**
	 * @param newpassword the newpassword to set
	 */
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the provinceName
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * @param provinceName the provinceName to set
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the centerId
	 */
	public int getCenterId() {
		return centerId;
	}

	/**
	 * @param centerId the centerId to set
	 */
	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	
	
}
