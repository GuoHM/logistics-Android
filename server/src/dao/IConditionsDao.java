package dao;

import bean.Conditions;

public interface IConditionsDao {
	
	Conditions getConditionByConditionID(String conditionID) throws Exception;

}
