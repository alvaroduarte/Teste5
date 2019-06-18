package br.com.traderbacktestapp.endpoint;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.traderbacktestapp.error.CustomErrorType;
import br.com.traderbacktestapp.error.ResourceNotFoundException;
import br.com.traderbacktestapp.model.Trader;
import br.com.traderbacktestapp.repository.TraderRepository;

/**
 * 
 * @author Duarte
 *
 * A anotação @RestController é mesma coisa que adicionar a anotacao @ResponseBody abaixo da anotacao  @Controller 
 * O Controle para de enviar respostas em formato HTML e passa enviar respostas em formato JSON.
 */


@RestController
@RequestMapping("v1")
public class TraderEndPoint {

	@Autowired
	private TraderRepository traderRepository;
	//traders

	//@GetMapping
	@RequestMapping(path="protected/traders/", method = RequestMethod.GET)
	public ResponseEntity<?> listAll(Pageable pageable){

		return new ResponseEntity<>(traderRepository.findAll(pageable), HttpStatus.OK);
	}

	//@GetMapping(path = "/{id}")
	@RequestMapping( method = RequestMethod.GET, path = "protected/traders/{id}")
	public ResponseEntity<?> getTraderById(@PathVariable("id") Long id){
		
		verifyIfTraderExists(id);

		return new ResponseEntity<>(traderRepository.findById(id).get(), HttpStatus.OK);
	}

	//@GetMapping(path = "/findByMotivo/{motivo}")
	@RequestMapping(method = RequestMethod.GET, path = "protected/traders/findByMotivo/{motivo}")
	public ResponseEntity<?> findByMotivo(@PathVariable("motivo") String motivo){

		List<Trader> traders = traderRepository.findByMotivoIgnoreCaseContaining(motivo);

		if(traders.size() == 0) {
			return new ResponseEntity<>(new CustomErrorType("Trader not found"), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(traders, HttpStatus.OK);
	}

	//@GetMapping(path = "/findByMotivo/{papel}")
	@RequestMapping(method = RequestMethod.GET, path = "protected/traders/findByPapel/{papel}")
	public ResponseEntity<?> findByPapel(@PathVariable("papel") String papel){

		List<Trader> traders = traderRepository.findByPapelIgnoreCaseContaining(papel);

		if(traders.size() == 0) {
			return new ResponseEntity<>(new CustomErrorType("Trader not found"), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(traders, HttpStatus.OK);
	}

	//@GetMapping(path = "/findByData/{data}")
	/*@RequestMapping(method = RequestMethod.GET, path = "/findByData/{data}")
	public ResponseEntity<?> findByData(@PathVariable("data") String data){

		
		List<Trader> traders = null;

		try {
			traders = traderRepository.findAllByData(new SimpleDateFormat("dd-MM-yyyy").parse(data));
		} catch (ParseException e) {
			return new ResponseEntity<>(new CustomErrorType("A formatação da data segue o seguinte padarão : dd-MM-yyyy"), HttpStatus.NOT_FOUND);
		}


		if(traders.size() == 0) {
			return new ResponseEntity<>(new CustomErrorType("Trader not found"), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(traders, HttpStatus.OK);
	}*/

	//@PostMapping
	@Transactional
	@RequestMapping(path="admin/traders/", method = RequestMethod.POST)
	public ResponseEntity<?> save(@Valid @RequestBody Trader trader){

		return new ResponseEntity<>(traderRepository.save(trader), HttpStatus.OK);
	}

	//@DeleteMapping
	//@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, path = "admin/traders/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		verifyIfTraderExists(id);
		
		traderRepository.deleteById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	//@PutMapping
	@RequestMapping(path="admin/traders/", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Trader trader){
		
		verifyIfTraderExists(trader.getId());
		
		traderRepository.save(trader);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	private void verifyIfTraderExists(Long id) {
		
		try {
			traderRepository.findById(id).get();
		}catch (Exception ex) {
			throw new ResourceNotFoundException("Trader not found for ID:"+id);
		}
		
	}

}