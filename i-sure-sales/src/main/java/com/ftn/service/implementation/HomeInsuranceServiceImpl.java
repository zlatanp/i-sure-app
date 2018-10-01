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

	@Value("${dc.home.insurance}")
	private String URI;

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<HomeInsuranceDTO> readAll() {

		ResponseEntity<HomeInsuranceDTO[]> response = restTemplate.getForEntity(URI, HomeInsuranceDTO[].class);

		return Arrays.asList(response.getBody());
	}

	@Override
	public HomeInsuranceDTO create(HomeInsuranceDTO homeInsuranceDTO) {

		ResponseEntity<HomeInsuranceDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(homeInsuranceDTO),
				HomeInsuranceDTO.class);

		return response.getBody();
	}

	@Override
	public HomeInsuranceDTO update(Long id, HomeInsuranceDTO homeInsuranceDTO) {

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		restTemplate.setRequestFactory(requestFactory);

		ResponseEntity<HomeInsuranceDTO> response = restTemplate.exchange(URI + id, HttpMethod.PATCH,
				new HttpEntity<>(homeInsuranceDTO), HomeInsuranceDTO.class);

		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		restTemplate.delete(URI + id);
	}

	@Override
	public HomeInsuranceDTO findById(Long id) {

		ResponseEntity<HomeInsuranceDTO> response = restTemplate.getForEntity(URI + id, HomeInsuranceDTO.class);

		return response.getBody();
	}

	@Override
	public List<HomeInsuranceDTO> findByPersonalId(String personalId) {

		ResponseEntity<HomeInsuranceDTO[]> response = restTemplate.getForEntity(URI + "byPersonalId/" + personalId, HomeInsuranceDTO[].class);

		return Arrays.asList(response.getBody());
	}
}
