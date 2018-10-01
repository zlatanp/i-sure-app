package com.ftn.service.implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.exception.NotFoundException;
import com.ftn.model.PaymentType;
import com.ftn.model.Transaction;
import com.ftn.model.TransactionStatus;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.repository.InsurancePolicyRepository;
import com.ftn.repository.PaymentRepository;
import com.ftn.repository.PaymentTypeRepository;
import com.ftn.repository.TransactionRepository;
import com.ftn.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	private final TransactionRepository transactionRepository;
	private final PaymentRepository paymentRepository;
	private final InsurancePolicyRepository insurancePolicyRepository;
	private final PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository,
			PaymentRepository paymentRepository, InsurancePolicyRepository insurancePolicyRepository,
			PaymentTypeRepository paymentTypeRepository) {
		this.transactionRepository = transactionRepository;
		this.paymentRepository = paymentRepository;
		this.insurancePolicyRepository = insurancePolicyRepository;
		this.paymentTypeRepository = paymentTypeRepository;
	}
	
	@Override
	public List<TransactionDTO> readAll() {
		return transactionRepository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toList());
	}

	@Override
	public TransactionDTO create(TransactionDTO transactionDTO) {
		final Transaction transaction = transactionDTO.construct();
		transaction.setTimestamp(new Date());
		PaymentType paymentType = paymentTypeRepository.findByLabel(transactionDTO.getPaymentType().getLabel())
				.orElseThrow(NotFoundException::new);;
		transaction.setStatus(TransactionStatus.PENDING);
		transaction.setPaymentType(paymentType);
		Transaction retVal = transactionRepository.save(transaction);
		return new TransactionDTO(retVal);
	}

	@Override
	public TransactionDTO update(Long id, TransactionDTO transactionDTO) {
		final Transaction transaction = transactionRepository.findById(id).orElseThrow(NotFoundException::new);
		transaction.merge(transactionDTO);
		Transaction retVal = transactionRepository.save(transaction);
		return new TransactionDTO(retVal);
	}

	@Override
	public void delete(Long id) {
        final Transaction transaction = transactionRepository.findById(id).orElseThrow(NotFoundException::new);

        transaction.setActive(false);
        transactionRepository.save(transaction);
	}

	@Override
	public TransactionDTO findById(Long id) {
		final Transaction transaction = transactionRepository.findById(id).orElseThrow(NotFoundException::new);
        return new TransactionDTO(transaction);
	}

	@Override
	public TransactionDTO findByPaymentServiceId(String paymentId) {
		final Transaction transaction = transactionRepository.findByPaymentServiceId(paymentId).orElseThrow(NotFoundException::new);
		return new TransactionDTO(transaction);
	}

}
