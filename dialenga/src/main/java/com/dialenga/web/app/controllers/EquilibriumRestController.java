package com.dialenga.web.app.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dialenga.web.app.core.FindIndexEquilibrium;
import com.dialenga.web.app.core.IFindIndexEquilibrium;
import com.dialenga.web.app.models.EquilibriumBean;
import com.dialenga.web.app.models.IndexDataBean;
import com.dialenga.web.app.service.IEquilibriumService;

@RestController
@RequestMapping(path="/api")
@CrossOrigin(origins="*")
public class EquilibriumRestController {
	
	private static final String NO_EQ_INDEX = "No tiene";
	
	@Autowired 
	private IEquilibriumService service;
	
    
    @GetMapping("equilibriumindex/find/{param}")
    public ResponseEntity<List<EquilibriumBean>> getEquilibriumParallel(@PathVariable("param") String param) {
    	final long iTime = System.nanoTime();
    	try {
    		 List<EquilibriumBean> eqbList = Collections.synchronizedList(new  ArrayList<>());
    		 String[] split = param.replace(" ", "").split(":");
    		 List<String> arrays = Arrays.asList(split);
    		 arrays.parallelStream().forEach(array -> eqbList.add(processArray(array)));
	         final long fTime = (System.nanoTime() - iTime) / 1000;
	         Logger.getGlobal().info(()-> "Hilo -> " + Thread.currentThread().getId() 
	        		+ " Duración getEquilibriumParallel to process " +arrays.size()+ "arrays -> microsec = " + fTime 
	        		+ " -> ms = " + fTime / 1000);
			 if (eqbList.isEmpty()) {
				 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			 }
	         eqbList.stream().forEach(eqb->service.save(eqb));
			 return new ResponseEntity<>(eqbList, HttpStatus.OK);
		} catch (RuntimeException e) {
			Logger.getGlobal().warning(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    	} catch (Exception e) {
			Logger.getGlobal().warning(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    
    @GetMapping("get/all")
    public ResponseEntity<List<EquilibriumBean>> getAllEquilibriumIndex() {
    	try {
    		List<EquilibriumBean> eqbList = service.getAllequilibriumIndex();
   		 	if (eqbList.isEmpty()) {
   		 		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   		 	}

   		 	return new ResponseEntity<>(eqbList, HttpStatus.OK);
		} catch (Exception e) {
			Logger.getGlobal().warning(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    
    @GetMapping("get/equilibriumindex")
    public ResponseEntity<List<EquilibriumBean>> getAll() {
    	try {
    		List<EquilibriumBean> eqbList = service.getAll();
   		 	if (eqbList.isEmpty()) {
   		 		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   		 	}

   		 	return new ResponseEntity<>(eqbList, HttpStatus.OK);
		} catch (Exception e) {
			Logger.getGlobal().warning(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    private EquilibriumBean processArray(String array) {
        List<Integer> integers = Collections.synchronizedList(Arrays.asList(
        		array.split(",")).stream()
        			.map(String::trim)
        			.map(Integer::valueOf)
        			.collect(Collectors.toList()));
        IFindIndexEquilibrium feq = new FindIndexEquilibrium();
        List<IndexDataBean> idb = feq.getEquilibriumIndex(integers);
        
        return buidlEquilibriumBean(integers, idb);
	}

    private EquilibriumBean buidlEquilibriumBean(List<Integer> integers, List<IndexDataBean> idb) {
		if (idb.isEmpty()) {
			return new EquilibriumBean(new Date(), integers.toString(), NO_EQ_INDEX);
		} else {
	        String equilibriumIndices = idb.stream()
	        		.map(i->String.valueOf(i.getEquilibriumIndex()))
	        		.collect(Collectors.joining(","));
	        return new EquilibriumBean(new Date(), integers.toString(), equilibriumIndices);
		}
	}
}
