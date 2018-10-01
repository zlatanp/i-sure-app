package com.ftn.service.implementation;

import com.ftn.model.dto.*;
import com.ftn.model.dto.report.PolicyReportParamsDTO;
import com.ftn.model.dto.report.ReportCustomerTableDTO;
import com.ftn.model.dto.report.ReportTableDTO;
import com.ftn.service.ReportService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Jasmina on 22/01/2018.
 */
@Service
public class ReportServiceImpl implements ReportService {

    public enum InsuranceType {
        TRAVEL,
        HOME,
        CAR
    }

    @Value("${dc.home}")
    private String dc_home;

    @Value("${dc.insurancePolicies}")
    private String dc_insurancePolicies;

    @Value("${dc.travelInsurance}")
    private String dc_travel_insurance;

    @Value("${dc.homeInsurance}")
    private String dc_home_insurance;

    @Value("${dc.carInsurance}")
    private String dc_car_insurance;

    @Value("${dc.risk}")
    private String dc_risk;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public InsurancePolicyDTO getInsurancePolicyForTransaction(long transactionId) {
        String URI = dc_home + dc_insurancePolicies + "/transaction/" + transactionId;
        ResponseEntity<InsurancePolicyDTO> response = restTemplate.getForEntity(URI, InsurancePolicyDTO.class);
        return response.getBody();
    }

    @Override
    public String generatePolicyReport(InsurancePolicyDTO insurancePolicyDTO) {
        String jasperFilePath = "src/main/resources/jasper/InsurancePolicy.jasper";
        Map<String, Object> data = new HashMap<>();
        PolicyReportParamsDTO params = prepareParams(insurancePolicyDTO);
        List<ReportTableDTO> travelTable = prepareRisks(insurancePolicyDTO, InsuranceType.TRAVEL);
        List<ReportTableDTO> homeTable = new ArrayList<>();
        List<ReportTableDTO> carTable = new ArrayList<>();

        if(insurancePolicyDTO.getHomeInsurance() != null) {
            homeTable = prepareRisks(insurancePolicyDTO, InsuranceType.HOME);
        }
        if(insurancePolicyDTO.getRoadsideAssistanceInsurance() != null){
            carTable = prepareRisks(insurancePolicyDTO, InsuranceType.CAR);
        }
        List<ReportCustomerTableDTO> customerTable = prepareCustomers(insurancePolicyDTO);

        placeParamsToData(data, params);
        data.put("CustomersDataset", new JRBeanCollectionDataSource(customerTable));
        data.put("TravelRisks", new JRBeanCollectionDataSource(travelTable));
        data.put("HomeRisks", new JRBeanCollectionDataSource(homeTable));
        data.put("CarRisks",  new JRBeanCollectionDataSource(carTable));

        try {
            String fileName = "policy" + insurancePolicyDTO.getId() + ".pdf";
            String filePath = "src/main/resources/policy/" + fileName;
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, data, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/resources/policy/policy" + insurancePolicyDTO.getId() + ".pdf");

            File file = new File("C:\\Users\\Jasmina\\policy.pdf");
            OutputStream outputStream = new FileOutputStream(file);
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void placeParamsToData(Map<String, Object> data, PolicyReportParamsDTO params) {
        data.put("hasCar", params.isHasCar());
        data.put("hasHome", params.isHasHome());
        data.put("totalPrice", params.getTotalPrice());
        data.put("policyNumber", params.getPolicyNumber());
        data.put("issueDate", params.getIssueDate());
        data.put("effectiveDate", params.getEffectiveDate());
        data.put("expirationDate", params.getExpirationDate());
        data.put("numPersons", params.getNumPerson());
        data.put("numDays", params.getNumDays());
        data.put("travelInsurancePrice", params.getTravelInsurancePrice());
        data.put("homeAddress", params.getHomeAddress());
        data.put("ownerHome", params.getOwnerHome());
        data.put("ownerHomeId", params.getOwnerHomeId());
        data.put("homeInsurancePrice", params.getHomeInsurancePrice());
        data.put("carBrand", params.getCarBrand());
        data.put("carType", params.getCarType());
        data.put("ownerCar", params.getOwnerCar());
        data.put("ownerCarId", params.getOwnerCarId());
        data.put("licenceNum", params.getLicenceNum());
        data.put("undercarriageNum", params.getUndercarriageNum());
        data.put("carInsurancePrice", params.getCarInsurancePrice());
    }

    @Override
    public PolicyReportParamsDTO prepareParams(InsurancePolicyDTO insurancePolicyDTO) {
        PolicyReportParamsDTO policyReportParamsDTO = new PolicyReportParamsDTO();
        policyReportParamsDTO.setEffectiveDate(insurancePolicyDTO.getDateBecomeEffective());
        policyReportParamsDTO.setPolicyNumber(insurancePolicyDTO.getId());
        RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO = insurancePolicyDTO.getRoadsideAssistanceInsurance();
        if (roadsideAssistanceInsuranceDTO == null) {
            policyReportParamsDTO.setHasCar(false);
        } else {
            policyReportParamsDTO.setHasCar(true);
            policyReportParamsDTO.setCarBrand(roadsideAssistanceInsuranceDTO.getCarBrand());
            policyReportParamsDTO.setCarType(roadsideAssistanceInsuranceDTO.getCarType());
            policyReportParamsDTO.setLicenceNum(roadsideAssistanceInsuranceDTO.getLicencePlateNumber());
            policyReportParamsDTO.setUndercarriageNum(roadsideAssistanceInsuranceDTO.getUndercarriageNumber());
            policyReportParamsDTO.setOwnerCar(roadsideAssistanceInsuranceDTO.getOwnerFirstName() + " " + roadsideAssistanceInsuranceDTO.getOwnerLastName());
            policyReportParamsDTO.setOwnerCarId(roadsideAssistanceInsuranceDTO.getPersonalId());
            policyReportParamsDTO.setCarInsurancePrice(roadsideAssistanceInsuranceDTO.getPrice());
        }
        HomeInsuranceDTO homeInsuranceDTO = insurancePolicyDTO.getHomeInsurance();
        if (homeInsuranceDTO == null) {
            policyReportParamsDTO.setHasHome(false);
        } else {
            policyReportParamsDTO.setHasHome(true);
            policyReportParamsDTO.setHomeAddress(homeInsuranceDTO.getAddress());
            policyReportParamsDTO.setOwnerHome(homeInsuranceDTO.getOwnerFirstName() + " " + homeInsuranceDTO.getOwnerLastName());
            policyReportParamsDTO.setOwnerHomeId(homeInsuranceDTO.getPersonalId());
            policyReportParamsDTO.setHomeInsurancePrice(homeInsuranceDTO.getPrice());
        }
        InternationalTravelInsuranceDTO internationalTravelInsuranceDTO = insurancePolicyDTO.getInternationalTravelInsurance();
        policyReportParamsDTO.setIssueDate(insurancePolicyDTO.getDateOfIssue());
        policyReportParamsDTO.setEffectiveDate(internationalTravelInsuranceDTO.getStartDate());
        policyReportParamsDTO.setExpirationDate(internationalTravelInsuranceDTO.getEndDate());
        policyReportParamsDTO.setNumDays(internationalTravelInsuranceDTO.getDurationInDays());
        policyReportParamsDTO.setNumPerson(internationalTravelInsuranceDTO.getNumberOfPersons());
        policyReportParamsDTO.setTravelInsurancePrice(internationalTravelInsuranceDTO.getPrice());
        policyReportParamsDTO.setTotalPrice(insurancePolicyDTO.getTotalPrice());
        return policyReportParamsDTO;
    }

    @Override
    public List<ReportCustomerTableDTO> prepareCustomers(InsurancePolicyDTO insurancePolicyDTO) {
        List<ReportCustomerTableDTO> customerTableDTOS = new ArrayList<>();
        List<CustomerDTO> customers = insurancePolicyDTO.getCustomers();
        for (CustomerDTO customer : customers) {
            ReportCustomerTableDTO reportCustomer = new ReportCustomerTableDTO();
            reportCustomer.setCarrier(customer.isCarrier());
            reportCustomer.setEmail(customer.getEmail());
            reportCustomer.setJmbg(customer.getPersonalId());
            reportCustomer.setPassport(customer.getPassport());
            reportCustomer.setTelephone(customer.getTelephoneNumber());
            reportCustomer.setAddress(customer.getAddress());
            reportCustomer.setLastname(customer.getLastName());
            reportCustomer.setName(customer.getFirstName());
            customerTableDTOS.add(reportCustomer);
        }

        return customerTableDTOS;
    }

    @Override
    public List<ReportTableDTO> prepareRisks(InsurancePolicyDTO insurancePolicyDTO, InsuranceType insuranceType) {
        List<ReportTableDTO> reportTableTravelRisk = new ArrayList<>();
        List<RiskDTO> risks = getInsuranceRisksByType(insurancePolicyDTO, insuranceType);
        for (RiskDTO risk : risks) {
            long riskId = risk.getId();
            String riskUri = dc_home + dc_risk + "/" + riskId;
            ResponseEntity<RiskDTO> riskDTOResponseEntity = restTemplate.getForEntity(riskUri, RiskDTO.class);
            RiskDTO riskFull = riskDTOResponseEntity.getBody();
            if (riskFull != null) {
                ReportTableDTO reportTableDTO = new ReportTableDTO();
                reportTableDTO.setRiskName(riskFull.getRiskName());
                reportTableDTO.setRiskType(riskFull.getRiskType().getRiskTypeName());
                List<PricelistItemDTO> prices = riskFull.getPricelistItem();
                for (PricelistItemDTO priceListItemDTO : prices) {
                    if (priceListItemDTO.isActive()) {
                        reportTableDTO.setRiskPrice(priceListItemDTO.getPrice());
                        break;
                    }
                }
                reportTableTravelRisk.add(reportTableDTO);
            }
        }

        return reportTableTravelRisk;
    }

    private List<RiskDTO> getInsuranceRisksByType(InsurancePolicyDTO insurancePolicyDTO, InsuranceType insuranceType) {
        ResponseEntity response;
        List<RiskDTO> riskList = new ArrayList<>();

        if (insuranceType.equals(InsuranceType.TRAVEL)) {
            long travelInsuranceId = insurancePolicyDTO.getInternationalTravelInsurance().getId();
            String URI = dc_home + dc_travel_insurance + "/" + travelInsuranceId;
            response = restTemplate.getForEntity(URI, InternationalTravelInsuranceDTO.class);
            InternationalTravelInsuranceDTO internationalTravelInsuranceDTO = (InternationalTravelInsuranceDTO) response.getBody();
            if (internationalTravelInsuranceDTO != null) {
                riskList = internationalTravelInsuranceDTO.getRisks();
            }
        } else if (insuranceType.equals(InsuranceType.HOME)) {
            long homeInsuranceId = insurancePolicyDTO.getHomeInsurance().getId();
            String URI = dc_home + dc_home_insurance + "/" + homeInsuranceId;
            response = restTemplate.getForEntity(URI, HomeInsuranceDTO.class);
            HomeInsuranceDTO homeInsuranceDTO = (HomeInsuranceDTO) response.getBody();
            if (homeInsuranceDTO != null) {
                riskList = homeInsuranceDTO.getRisks();
            }
        } else {
            long carInsuranceId = insurancePolicyDTO.getRoadsideAssistanceInsurance().getId();
            String URI = dc_home + dc_car_insurance + "/" + carInsuranceId;
            response = restTemplate.getForEntity(URI, RoadsideAssistanceInsuranceDTO.class);
            RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO = (RoadsideAssistanceInsuranceDTO) response.getBody();
            if (roadsideAssistanceInsuranceDTO != null) {
                riskList = roadsideAssistanceInsuranceDTO.getRisks();
            }
        }

        return riskList;
    }
}
