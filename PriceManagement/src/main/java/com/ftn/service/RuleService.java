package com.ftn.service;

import com.ftn.model.User;
import com.ftn.model.dto.HomeInsuranceDTO;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.model.dto.InternationalTravelInsuranceDTO;
import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;
import org.kie.api.runtime.KieSession;

/**
 * Created by zlatan on 11/25/17.
 */
public interface RuleService {

    User getUser(User u);
    InternationalTravelInsuranceDTO getInternationalTravelInsurance(InternationalTravelInsuranceDTO internationalTravelInsuranceDTO);
    HomeInsuranceDTO getHomeInsurance(HomeInsuranceDTO homeInsuranceDTO);
    RoadsideAssistanceInsuranceDTO getRoadsideAssistanceInsurance(RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO);
    InsurancePolicyDTO getInsurancePolicy(InsurancePolicyDTO insurancePolicyDTO);
}