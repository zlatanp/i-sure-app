package com.ftn.service;

import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.model.dto.report.PolicyReportParamsDTO;
import com.ftn.model.dto.report.ReportCustomerTableDTO;
import com.ftn.model.dto.report.ReportTableDTO;
import com.ftn.service.implementation.ReportServiceImpl;

import java.util.List;

/**
 * Created by Jasmina on 22/01/2018.
 */
public interface ReportService {

    InsurancePolicyDTO getInsurancePolicyForTransaction(long transactionId);

    String generatePolicyReport(InsurancePolicyDTO insurancePolicyDTO);

    PolicyReportParamsDTO prepareParams(InsurancePolicyDTO insurancePolicyDTO);

    List<ReportCustomerTableDTO> prepareCustomers(InsurancePolicyDTO insurancePolicyDTO);

    List<ReportTableDTO> prepareRisks(InsurancePolicyDTO insurancePolicyDTO, ReportServiceImpl.InsuranceType insuranceType);
}
