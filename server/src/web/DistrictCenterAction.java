package web;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import bean.DistrictCenter;
import bean.Goods;
import bean.GoodsStatus;
import bean.GoodsStatusId;
import bean.ProvinceCenter;
import bean.TransportationManagement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import service.IConditionsService;
import service.IGoodsService;
import service.IGoodsStatusService;
import service.IProvinceCenterService;
import util.JsonHelper;
public class DistrictCenterAction extends ActionSupport implements ServletRequestAware {
    private static final long serialVersionUID = 1405926533311347411L;
    ActionContext context = ActionContext.getContext();
    protected HttpServletRequest servletRequest = null;
    Logger logger = Logger.getLogger(AllAction.class);
    private IGoodsService goodsService;
    private IGoodsStatusService goodsStatusService;
    private IConditionsService conditionsService;
    private IProvinceCenterService provinceCenterService;
    private String currentGoods;
    private String searchGoodsId; // 输入的要查询单号
    private String goodsId;
    private String senderName;
    private String senderPhone;
    private String senderProvince;
    private String senderCity;
    private String senderAddress;
    private String receiverName;
    private String receiverPhone;
    private String receiverProvince;
    private String receiverCity;
    private String receiverAddress;
    private String senderDistrict;
    private String receiverDistrict;
    private HttpServletResponse response = ServletActionContext.getResponse();
    private JsonHelper json = new JsonHelper(this.response);
    
    public void searchByGoodsID() throws Exception { // 根据单号查询快递单信息
        Goods goods = new Goods();
        goods = goodsService.getGoodsBygoodsId(searchGoodsId);
        List<GoodsStatus> statuslist = goodsStatusService.getGoodsStatusByGoodsId(searchGoodsId);
        if (goods != null) {
//            json.put("statuslist", statuslist);
            json.put("goodsId", goods.getGoodsId());
            json.put("receiverAddress", goods.getReceiverAddress());
            json.put("receiverProvince", goods.getReceiverProvince());
            json.put("receiverCity", goods.getReceiverCity());
            json.put("receiverName", goods.getReceiverName());
            json.put("receiverPhone",goods.getReceiverPhone());
            json.put("receiverDistrict",goods.getReceiverDistrict());
            json.put("senderAddress", goods.getSenderAddress());
            json.put("senderProvince", goods.getSenderProvince());
            json.put("senderCity", goods.getSenderCity());
            json.put("senderName", goods.getSenderName());
            json.put("senderPhone",goods.getSenderPhone());
            json.put("senderDistrict",goods.getSenderDistrict());
            json.output();
            return;
        } else {
            json.put("error", "找不到该订单信息");
            json.output();
            return;
        }
    }
    public String viewGoods() throws Exception {
        Goods goodsInfo = goodsService.getGoodsBygoodsId(currentGoods);
        context.getSession().put("goodsInfo", goodsInfo);
        context.getSession().put("show", "show");
        return SUCCESS;
    }
    public String modifyGoodsinfo() throws Exception { // 单号查询显示后，进行修改
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setSenderName(senderName);
        goods.setSenderPhone(senderPhone);
        goods.setSenderProvince(senderProvince);
        goods.setSenderCity(senderCity);
        goods.setSenderAddress(senderAddress);
        goods.setReceiverName(receiverName);
        goods.setReceiverPhone(receiverPhone);
        goods.setReceiverProvince(receiverProvince);
        goods.setReceiverCity(receiverCity);
        goods.setReceiverAddress(receiverAddress);
        goods.setSenderDistrict(senderDistrict);
        goods.setReceiverDistrict(receiverDistrict);
        // goods.setDistrictCenterBySendDestrictCenter(districtCenterBySendDestrictCenter);
        goodsService.save(goods);
        if (goods != null) {
            return "modifyGoodsinfoSuccess";
        } else
            return "modifyGoodsinfoFalse";
    }
    @SuppressWarnings({ "null", "unused" })
    public String getGoodsByDistrict() throws Exception {// 获取当前区县营业点未发送到省的的所有快递单
        List<Goods> list = null;
        List<Goods> list2 = new ArrayList<Goods>();
        DistrictCenter district = (DistrictCenter) context.getSession().get("login");
        list = goodsService.getGoodsByDistrict(district.getDistrict(), district.getCity(), district.getProvince());
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Goods goods = list.get(i);
                if (goods.getDistrictCenterBySendDestrictCenter() != null
                        && goods.getProvinceCenterBySendProvinceCenter() == null) {
                    list2.add(list.get(i));
                }
            }
        }
        if (list2 != null) {
            context.getSession().put("DistrictList", list2);
            return "getGoodsByDistrictSuccess";
        } else {
            return "getGoodsByDistrictFalse";
        }
    }
    public String addStatus() throws Exception {// 保存状态信息，并将区县营业点加入goods表中
        Goods goods = new Goods();
        goods = goodsService.getGoodsBygoodsId(goodsId);
        GoodsStatusId goodsStatusId = new GoodsStatusId();
        goodsStatusId.setGoodsId(goodsId);
        goodsStatusId.setConditionId("2");
        GoodsStatus goodsStatus = new GoodsStatus();
        goodsStatus.setId(goodsStatusId);
        DistrictCenter district = (DistrictCenter) context.getSession().get("login");
        System.out.println(goods);
        System.out.println(goodsId);
        goods.setDistrictCenterBySendDestrictCenter(district);
        goodsService.save(goods);
        goods = goodsService.getGoodsBygoodsId(goodsId);
        goodsStatus.setGoods(goods);
        goodsStatus.setConditions(conditionsService.getConditionsByConditonsId("2"));
        goodsStatusService.save(goodsStatus);
        if (goodsStatus != null) {
            return "saveStatusSuccess";
        } else
            return "saveStatusFalse";
    }
    @SuppressWarnings("null")
    public String adddistrictListStatus() throws Exception {// 发往本省时，将商品链表都加上状态信息
        int j = 0;
        List<Goods> list = null;
        List<Goods> list2 = new ArrayList<Goods>();
        DistrictCenter district = (DistrictCenter) context.getSession().get("login");
        list = goodsService.getGoodsByDistrict(district.getDistrict(), district.getCity(), district.getProvince());
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Goods goods = list.get(i);
                if (goods.getDistrictCenterBySendDestrictCenter() != null
                        && goods.getProvinceCenterBySendProvinceCenter() == null) {
                    list2.add(list.get(i));
                }
            }
        }
        for (j = 0; j < list2.size(); j++) {
            Goods goods = list2.get(j);
            GoodsStatusId goodsStatusId = new GoodsStatusId();
            goodsStatusId.setGoodsId(goods.getGoodsId());
            goodsStatusId.setConditionId("3");
            GoodsStatus goodsStatus = new GoodsStatus();
            goodsStatus.setId(goodsStatusId);
            ProvinceCenter provinceCenter = provinceCenterService
                    .getProvinceCenterByProvinceName(goods.getSenderProvince());
            goods.setProvinceCenterBySendProvinceCenter(provinceCenter);
            goodsService.save(goods);
            goods = goodsService.getGoodsBygoodsId(goods.getGoodsId());
            goodsStatus.setGoods(goods);
            goodsStatus.setConditions(conditionsService.getConditionsByConditonsId("3"));
            goodsStatusService.save(goodsStatus);
        }
        if (j == list2.size()) {
            return "savedistrictListStatusSuccess";
        } else
            return "savedistrictListStatusFalse";
    }
    
    
    
    @SuppressWarnings({ "null", "unused" })
    public String getGoodsByreceiverDistrict() throws Exception {// 获取当前区县营业点未配送的的所有快递单
        List<Goods> list = null;
        List<Goods> list2 = new ArrayList<Goods>();
        DistrictCenter district = (DistrictCenter) context.getSession().get("login");
        list = goodsService.getGoodsByreceiverDistrict(district.getDistrict(), district.getCity(), district.getProvince());
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Goods goods = list.get(i);
                if (goods.getDistrictCenterByReceiveDistrictCenter()!= null&&goodsStatusService.getGoodsStatusByGoodsId(goods.getGoodsId()).size()==5) {
                    list2.add(list.get(i));
                }
            }
        }
        if (list2 != null) {
            context.getSession().put("receiverDistrictList", list2);
            return "getGoodsByreceiverDistrictSuccess";
        } else {
            return "getGoodsByreceiverDistrictFalse";
        }
    }
    @SuppressWarnings("null")
    public String addreceiverDistrictListStatus() throws Exception {// 配送时，将商品链表都加上状态信息
        int j = 0;
        List<Goods> list = null;
        List<Goods> list2 = new ArrayList<Goods>();
        DistrictCenter district = (DistrictCenter) context.getSession().get("login");
        list = goodsService.getGoodsByreceiverDistrict(district.getDistrict(), district.getCity(), district.getProvince());
        if (list != null) {	
            for (int i = 0; i < list.size(); i++) {
                Goods goods = list.get(i);
                if (goods.getDistrictCenterByReceiveDistrictCenter()!= null&&goodsStatusService.getGoodsStatusByGoodsId(goods.getGoodsId()).size()==5) {
                    list2.add(list.get(i));
                }
            }
        }
        for (j = 0; j < list2.size(); j++) {
            Goods goods = list2.get(j);
            GoodsStatusId goodsStatusId = new GoodsStatusId();
            goodsStatusId.setGoodsId(goods.getGoodsId());
            goodsStatusId.setConditionId("6");
            GoodsStatus goodsStatus = new GoodsStatus();
            goodsStatus.setId(goodsStatusId);
            goodsStatus.setGoods(goods);
            goodsStatus.setConditions(conditionsService.getConditionsByConditonsId("6"));
            goodsStatusService.save(goodsStatus);
        }
        if (j == list2.size()) {
            return "savereceiverDistrictListStatusSuccess";
        } else
            return "savereceiverDistrictListStatusFalse";
    }
   
    
    // public String printGoodsinfo() throws Exception { //打印快递单信息
    // Goods goods = new Goods();
    // goods.setGoodsId((String)context.getSession().get(searchGoodsId));
    // goods.setSenderName(senderName);
    // goods.setSenderPhone(senderPhone);
    // goods.setSenderProvince(senderProvince);
    // goods.setSenderCity(senderCity);
    // goods.setSenderAddress(senderAddress);
    // goods.setReceiverName(receiverName);
    // goods.setReceiverPhone(receiverPhone);
    // goods.setReceiverProvince(receiverProvince);
    // goods.setReceiverCity(receiverCity);
    // goods.setReceiverAddress(receiverAddress);
    //
    // }
    /**
     * @return the context
     */
    public ActionContext getContext() {
        return context;
    }
    /**
     * @param context
     *            the context to set
     */
    public void setContext(ActionContext context) {
        this.context = context;
    }
    /**
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }
    /**
     * @param logger
     *            the logger to set
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    /**
     * @return the goodsService
     */
    public IGoodsService getGoodsService() {
        return goodsService;
    }
    /**
     * @param goodsService
     *            the goodsService to set
     */
    public void setGoodsService(IGoodsService goodsService) {
        this.goodsService = goodsService;
    }
    /**
     * @return the searchGoodsId
     */
    public String getSearchGoodsId() {
        return searchGoodsId;
    }
    /**
     * @param searchGoodsId
     *            the searchGoodsId to set
     */
    public void setSearchGoodsId(String searchGoodsId) {
        this.searchGoodsId = searchGoodsId;
    }
    /**
     * @return the goodsId
     */
    public String getGoodsId() {
        return goodsId;
    }
    /**
     * @param goodsId
     *            the goodsId to set
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    /**
     * @return the senderName
     */
    public String getSenderName() {
        return senderName;
    }
    /**
     * @param senderName
     *            the senderName to set
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    /**
     * @return the senderPhone
     */
    public String getSenderPhone() {
        return senderPhone;
    }
    /**
     * @param senderPhone
     *            the senderPhone to set
     */
    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }
    /**
     * @return the senderProvince
     */
    public String getSenderProvince() {
        return senderProvince;
    }
    /**
     * @param senderProvince
     *            the senderProvince to set
     */
    public void setSenderProvince(String senderProvince) {
        this.senderProvince = senderProvince;
    }
    /**
     * @return the senderCity
     */
    public String getSenderCity() {
        return senderCity;
    }
    /**
     * @param senderCity
     *            the senderCity to set
     */
    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }
    /**
     * @return the senderAddress
     */
    public String getSenderAddress() {
        return senderAddress;
    }
    /**
     * @param senderAddress
     *            the senderAddress to set
     */
    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }
    /**
     * @return the receiverName
     */
    public String getReceiverName() {
        return receiverName;
    }
    /**
     * @param receiverName
     *            the receiverName to set
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    /**
     * @return the receiverPhone
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }
    /**
     * @param receiverPhone
     *            the receiverPhone to set
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
    /**
     * @return the receiverProvince
     */
    public String getReceiverProvince() {
        return receiverProvince;
    }
    /**
     * @param receiverProvince
     *            the receiverProvince to set
     */
    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }
    /**
     * @return the receiverCity
     */
    public String getReceiverCity() {
        return receiverCity;
    }
    /**
     * @param receiverCity
     *            the receiverCity to set
     */
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }
    /**
     * @return the receiverAddress
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }
    /**
     * @param receiverAddress
     *            the receiverAddress to set
     */
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    /**
     * @return the servletRequest
     */
    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }
    @Override
    public void setServletRequest(HttpServletRequest arg0) {
        // TODO Auto-generated method stub
    }
    /**
     * @return the senderDistrict
     */
    public String getSenderDistrict() {
        return senderDistrict;
    }
    /**
     * @param senderDistrict
     *            the senderDistrict to set
     */
    public void setSenderDistrict(String senderDistrict) {
        this.senderDistrict = senderDistrict;
    }
    /**
     * @return the receiverDistrict
     */
    public String getReceiverDistrict() {
        return receiverDistrict;
    }
    /**
     * @param receiverDistrict
     *            the receiverDistrict to set
     */
    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }
    /**
     * @return the goodsStatusService
     */
    public IGoodsStatusService getGoodsStatusService() {
        return goodsStatusService;
    }
    /**
     * @param goodsStatusService
     *            the goodsStatusService to set
     */
    public void setGoodsStatusService(IGoodsStatusService goodsStatusService) {
        this.goodsStatusService = goodsStatusService;
    }
    /**
     * @return the conditionsService
     */
    public IConditionsService getConditionsService() {
        return conditionsService;
    }
    /**
     * @param conditionsService
     *            the conditionsService to set
     */
    public void setConditionsService(IConditionsService conditionsService) {
        this.conditionsService = conditionsService;
    }
    /**
     * @return the provinceCenterService
     */
    public IProvinceCenterService getProvinceCenterService() {
        return provinceCenterService;
    }
    /**
     * @param provinceCenterService
     *            the provinceCenterService to set
     */
    public void setProvinceCenterService(IProvinceCenterService provinceCenterService) {
        this.provinceCenterService = provinceCenterService;
    }
    /**
     * @return the currentGoods
     */
    public String getCurrentGoods() {
        return currentGoods;
    }
    /**
     * @param currentGoods
     *            the currentGoods to set
     */
    public void setCurrentGoods(String currentGoods) {
        this.currentGoods = currentGoods;
    }
}