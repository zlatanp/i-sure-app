package com.ftn.service.implementation;

import com.ftn.model.dto.HomeInsuranceDTO;
import com.ftn.service.HomeInsuranceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class HomeInsuranceServiceImpl implements HomeInsuranceService {

	@Value("${dc.home}")
	private String dc_home;

	@Value("${dc.home.insurance}")
	private String dc_home_insurance;

	@Override
	public List<HomeInsuranceDTO> readAll() {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<HomeInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_home_insurance, HomeInsuranceDTO[].class);

		return Arrays.asList(response.getBody());
	}

	@Override
	public HomeInsuranceDTO create(HomeInsuranceDTO homeInsuranceDTO) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<HomeInsuranceDTO> response = restTemplate.postForEntity(dc_home + dc_home_insurance, new HttpEntity<>(homeInsuranceDTO),
				HomeInsuranceDTO.class);

		return response.getBody();
	}

	@Override
	public HomeInsuranceDTO update(Long id, HomeInsuranceDTO homeInsuranceDTO) {

		dc_home_insurance += "/" + id;
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		restTemplate.setRequestFactory(requestFactory);

		ResponseEntity<HomeInsuranceDTO> response = restTemplate.exchange(dc_home + dc_home_insurance, HttpMethod.PATCH,
				new HttpEntity<>(homeInsuranceDTO), HomeInsuranceDTO.class);

		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		dc_home_insurance += "/" + id;
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(dc_home + dc_home_insurance);
	}

	@Override
	public HomeInsuranceDTO findById(Long id) {

		dc_home_insurance += "/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<HomeInsuranceDTO> response = restTemplate.getForEntity(dc_home + dc_home_insurance, HomeInsuranceDTO.class);

		return response.getBody();
	}

	@Override
	public List<HomeInsuranceDTO> findByPersonalId(String personalId) {

		dc_home_insurance += "/byPersonalId/" + personalId;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<HomeInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_home_insurance, HomeInsuranceDTO[].class);

		return Arrays.asList(response.getBody());
	}
}
