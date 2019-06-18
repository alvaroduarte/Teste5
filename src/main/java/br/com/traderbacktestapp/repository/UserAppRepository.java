package br.com.traderbacktestapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import br.com.traderbacktestapp.model.UserApp;


public interface UserAppRepository extends PagingAndSortingRepository<UserApp, Long> {
	
	UserApp findByUsername(String username);

}