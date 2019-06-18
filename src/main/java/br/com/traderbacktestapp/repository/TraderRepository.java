package br.com.traderbacktestapp.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.traderbacktestapp.model.Trader;

public interface TraderRepository extends PagingAndSortingRepository<Trader, Long> {
	
	List<Trader> findByPapelIgnoreCaseContaining(String papel);
	
	List<Trader> findByMotivoIgnoreCaseContaining(String motivo);
	
	//List<Trader> findAllByData(Date data);

}