package com.ftn.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.exception.NotFoundException;
import com.ftn.model.Customer;
import com.ftn.model.HomeInsurance;
import com.ftn.model.InsurancePolicy;
import com.ftn.model.InternationalTravelInsurance;
import com.ftn.model.RoadsideAssistanceInsurance;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.repository.CustomerRepository;
import com.ftn.repository.HomeInsuranceRepository;
import com.ftn.repository.InsurancePolicyRepository;
import com.ftn.repository.InternationalTravelInsuranceRepository;
import com.ftn.repository.RoadsideAssistanceInsuranceRepository;
import com.ftn.service.InsurancePolicyService;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

    private final InsurancePolicyRepository insurancePolicyRepository;
    private final HomeInsuranceRepository homeInsuranceRepository;
    private final RoadsideAssistanceInsuranceRepository raiRepository;
    private final InternationalTravelInsuranceRepository itiRepository;
    private final CustomerRepository customerRepository;
    
    
    

    @Autowired
    public InsurancePolicyServiceImpl(InsurancePolicyRepository insurancePolicyRepository, HomeInsuranceRepository homeInsuranceRepository,
    		RoadsideAssistanceInsuranceRepository raiRepository, InternationalTravelInsuranceRepository itiRepository,
    		CustomerRepository customerRepository){
        this.insurancePolicyRepository = insurancePolicyRepository;
        this.homeInsuranceRepository = homeInsuranceRepository;
        this.raiRepository = raiRepository;
        this.itiRepository = itiRepository;
        this.customerRepository = customerRepository;
        
    }
    

    @Override
    public List<InsurancePolicyDTO> readAll() {
        return insurancePolicyRepository.findAll().stream().map(InsurancePolicyDTO::new).collect(Collectors.toList());
    }

    @Override
    public InsurancePolicyDTO create(InsurancePolicyDTO insurancePolicyDTO) {

        final InsurancePolicy insurancePolicy = insurancePolicyDTO.construct();
        
        List<Customer> customers = insurancePolicy.getCustomers();
        List<Customer> savedCustomers = new ArrayList();
        Customer savedCustomer =null;
        for(Customer customer : customers){
        	savedCustomer = customerRepository.save(customer);
        	savedCustomers.add(savedCustomer);
        }
        
        insurancePolicy.setCustomers(savedCustomers);
        
        InternationalTravelInsurance international = insurancePolicy.getInternationalTravelInsurance();
        
        international = itiRepository.save(international);
        
        HomeInsurance home = insurancePolicy.getHomeInsurance();
        RoadsideAssistanceInsurance road = insurancePolicy.getRoadsideAssistanceInsurance();
        
        if(home != null){
        	
        	home = homeInsuranceRepository.save(home);
        	insurancePolicy.setHomeInsurance(home);
        	
        }
        
        if(road != null){
        	road = raiRepository.save(road);
        	insurancePolicy.setRoadsideAssistanceInsurance(road);
        }
        
        insurancePolicyRepository.save(insurancePolicy);
        return new InsurancePolicyDTO(insurancePolicy);
    }

    @Override
    public InsurancePolicyDTO update(Long id, InsurancePolicyDTO insurancePolicyDTO) {
        final InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).orElseThrow(NotFoundException::new);
        insurancePolicy.merge(insurancePolicyDTO);
       
        insurancePolicyRepository.save(insurancePolicy);
        return new InsurancePolicyDTO(insurancePolicy);
    }

    @Override
    public void delete(Long id) {
        final InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).orElseThrow(NotFoundException::new);

        insurancePolicy.setActive(false);
        insurancePolicyRepository.save(insurancePolicy);

    }

    @Override
    public InsurancePolicyDTO findById(Long id) {
        final InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).orElseThrow(NotFoundException::new);
        return new InsurancePolicyDTO(insurancePolicy);
    }

    @Override
    public List<InsurancePolicyDTO> findByDateOfIssue(Date date) {
    	
        return insurancePolicyRepository.findByDateOfIssue(date).stream().map(InsurancePolicyDTO::new).collect(Collectors.toList());
        
    }
    
    @Override
    public List<InsurancePolicyDTO> findByDateBecomeEffective(Date date) {
       return insurancePolicyRepository.findByDateOfIssue(date).stream().map(InsurancePolicyDTO::new).collect(Collectors.toList());

    }
}
