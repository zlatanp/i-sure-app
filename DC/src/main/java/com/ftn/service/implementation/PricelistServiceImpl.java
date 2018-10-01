package com.ftn.service.implementation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.exception.BadRequestException;
import com.ftn.exception.NotFoundException;
import com.ftn.model.Pricelist;
import com.ftn.model.PricelistItem;
import com.ftn.model.dto.PricelistDTO;
import com.ftn.model.dto.RiskDTO;
import com.ftn.repository.PricelistItemRepository;
import com.ftn.repository.PricelistRepository;
import com.ftn.service.PricelistService;

@Service
public class PricelistServiceImpl implements PricelistService{
	
    private final PricelistRepository pricelistRepository;
    private final PricelistItemRepository pricelistItemRepository;

    @Autowired
    public PricelistServiceImpl(PricelistRepository pricelistRepository, PricelistItemRepository pricelistItemRepository){
        this.pricelistRepository = pricelistRepository;
        this.pricelistItemRepository = pricelistItemRepository;
    }
	
	@Override
	public List<PricelistDTO> readAll() {
		return pricelistRepository.findAll().stream().map(PricelistDTO::new).collect(Collectors.toList());
	}

	@Override
	public PricelistDTO create(PricelistDTO pricelistDTO) {

		try {
			if(pricelistDTO.getId() == 0L){
				final Pricelist pricelist = pricelistDTO.construct();
				Date dateFrom  = pricelistRepository.findMaxDateTo();
				
				if(dateFrom.after(pricelistDTO.getDateTo())){
					throw new BadRequestException();
				}
				
				pricelist.setDateFrom(dateFrom);
				pricelistRepository.save(pricelist);
			    return new PricelistDTO(pricelist);
			}else{
				Pricelist stari = pricelistRepository.findById(pricelistDTO.getId()).orElseThrow(NotFoundException::new);
				List<PricelistItem> itemsOld = stari.getPricelistItems();
				
				final Pricelist pricelist = pricelistDTO.construct();
				
				Date dateFrom  = pricelistRepository.findMaxDateTo();
				
				pricelist.setDateFrom(dateFrom);
				
				if(dateFrom.after(pricelistDTO.getDateTo())){
					throw new BadRequestException();
				}
				
				
				pricelist.setId(0L);
				pricelist.setActive(true);
				pricelist.setCreated(null);
				
				List<PricelistItem> items = new ArrayList();
				PricelistItem temp = null;
				for (PricelistItem pricelistItem : itemsOld) {
					temp = new PricelistItem(pricelistItem);
					temp.setActive(true);
					temp.setCreated(null);
					temp.setId(0L);
					temp.setUpdated(null);
					
					items.add(temp);
				}
				
				pricelist.setPricelistItems(items);

				pricelistRepository.save(pricelist);
				return new PricelistDTO(pricelist);
				
			}
//		
//		final Pricelist pricelist = pricelistDTO.construct();
//		pricelistRepository.save(pricelist);
//        return new PricelistDTO(pricelist);
		} catch (NotFoundException e) {
			return null;
		}
	}

	@Override
	public PricelistDTO update(Long id, PricelistDTO pricelistDTO) {
		final Pricelist pricelist = pricelistRepository.findById(id).orElseThrow(NotFoundException::new);
		pricelist.merge(pricelistDTO);
		pricelistRepository.save(pricelist);
        return new PricelistDTO(pricelist);
	}

	@Override
	public void delete(Long id) {
        final Pricelist pricelist = pricelistRepository.findById(id).orElseThrow(NotFoundException::new);
        pricelistRepository.delete(pricelist);		
	}

	@Override
	public PricelistDTO findById(Long id) {
        final Pricelist pricelist = pricelistRepository.findById(id).orElseThrow(NotFoundException::new);
        return new PricelistDTO(pricelist);
	}

	@Override
	public PricelistDTO findcurrentlyActive() {
		
		
	    Date now = new Date();
	    DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	    
	    Date todayWithZeroTime;
		try {
			todayWithZeroTime = formatter.parse(formatter.format(now));
			 System.out.println(todayWithZeroTime);
			 Pricelist pl = pricelistRepository.findCurrentlyActive(todayWithZeroTime).orElseThrow(NotFoundException::new);
			 PricelistDTO plDto = new PricelistDTO(pl);
			 PricelistDTO tempPlDto = new PricelistDTO(pl);
			 for(int i=0;i<tempPlDto.getPricelistItems().size();i++){
				 Long id = tempPlDto.getPricelistItems().get(i).getId();
				 PricelistItem pli = pricelistItemRepository.findById(id).orElseThrow(NotFoundException::new);
				 RiskDTO r = new RiskDTO(pli.getRisk());
				 plDto.getPricelistItems().get(i).setRisk(r);
			 }
			 return plDto;
		} catch (ParseException e) {
			
			//e.printStackTrace();
			return new PricelistDTO();
		}
        
	}
	
	@Override
	public Date findMaxDateTo() {
		Date maxDate  = pricelistRepository.findMaxDateTo();
		return maxDate;
	}

}
