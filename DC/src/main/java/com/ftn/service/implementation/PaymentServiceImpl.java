package com.ftn.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.exception.NotFoundException;
import com.ftn.model.InsurancePolicy;
import com.ftn.model.Payment;
import com.ftn.model.dto.PaymentDTO;
import com.ftn.repository.InsurancePolicyRepository;
import com.ftn.repository.PaymentRepository;
import com.ftn.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	private final PaymentRepository paymentRepository;
	
	
	@Autowired
	public PaymentServiceImpl(PaymentRepository PaymentRepository) {
		this.paymentRepository = PaymentRepository;
	}
	
	@Override
	public List<PaymentDTO> readAll() {
		return paymentRepository.findAll().stream().map(PaymentDTO::new).collect(Collectors.toList());
	}

	@Override
	public PaymentDTO create(PaymentDTO paymentDTO) {
		final Payment payment = paymentDTO.construct();
		
		
		
		Payment retVal = paymentRepository.save(payment);
		return new PaymentDTO(retVal);
	}

	@Override
	public PaymentDTO update(Long id, PaymentDTO paymentDTO) {
		final Payment payment = paymentRepository.findById(id).orElseThrow(NotFoundException::new);
		payment.merge(paymentDTO);
		
		Payment retVal = paymentRepository.save(payment);
		return new PaymentDTO(retVal);
	}

	@Override
	public void delete(Long id) {
        final Payment payment = paymentRepository.findById(id).orElseThrow(NotFoundException::new);

        payment.setActive(false);
        paymentRepository.save(payment);
	}

	@Override
	public PaymentDTO findById(Long id) {
		final Payment payment = paymentRepository.findById(id).orElseThrow(NotFoundException::new);
        return new PaymentDTO(payment);
	}

}
