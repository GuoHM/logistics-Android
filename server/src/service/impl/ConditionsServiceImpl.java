package service.impl;

import bean.Conditions;
import dao.IConditionsDao;
import service.IConditionsService;

public class ConditionsServiceImpl implements IConditionsService {
	private IConditionsDao conditionsDao;

	@Override
	public Conditions getConditionsByConditonsId(String conditonsId) throws Exception {
		// TODO Auto-generated method stub
		if(conditonsId != null && !conditonsId.equals("")) {
			return conditionsDao.getConditionByConditionID(conditonsId);
		}
		return null;
	}

	/**
	 * @return the conditionsDao
	 */
	public IConditionsDao getConditionsDao() {
		return conditionsDao;
	}

	/**
	 * @param conditionsDao the conditionsDao to set
	 */
	public void setConditionsDao(IConditionsDao conditionsDao) {
		this.conditionsDao = conditionsDao;
	}
	
	

}
