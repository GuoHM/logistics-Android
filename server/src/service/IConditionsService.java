package service;

import bean.Conditions;

public interface IConditionsService {
	Conditions getConditionsByConditonsId(String conditonsId) throws Exception;

}
