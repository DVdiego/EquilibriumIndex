package com.dialenga.web.app.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dialenga.web.app.core.CalculateEquilibrium;
import com.dialenga.web.app.models.EquilibriumBean;

@RestController
@RequestMapping(path="/json", produces="application/json")
@CrossOrigin(origins="*")
public class RestJsonController {
	
    @GetMapping("/debug/loadtesting/{arraySize}")
    public List<EquilibriumBean> debug(@PathVariable("arraySize") int arraySize) {
    	List<EquilibriumBean> eqbList = new ArrayList<>();
        String lista = "-7,1,5,2,-4,3,0"; // Equilibrium Index es 3 y 0
        // límite en mi host 9 
        for (int i = 0; i < arraySize; i++) {
			lista = lista.concat(",").concat(lista);
		}
        
        eqbList.add(processArray(lista));
        return eqbList;
    }
    
    
    @GetMapping("/debug/loadtesting2/{arraysNumber}")
    public String debug2(@PathVariable("arraysNumber") int arraysNumber) {
    	final String l1 = "2, 9, 3, 4, 0, 3, 3, 2, 9, 1"; // Equilibrium Index es 4
    	final String l2 = "0, -4, 7, -4, -2, 6, -3, 0"; // Equilibrium Index es 5
    	final String l3 = "9, 3, 7, 6, 8, 1, 10"; // Equilibrium Index es 3
        final String l4 = "-7,1,5,2,-4,3,0"; // Equilibrium Index es 3 y 0
        String lista = l1
				.concat(":")
				.concat(l2)
				.concat(":")
				.concat(l3)
				.concat(":")
				.concat(l4);
        // límite en mi host 9 
        // arraysNumber * 4
        for (int i = 0; i < arraysNumber; i++) {
        	lista = lista.concat(":").concat(lista);
		}
       
        return  getEquilibriumParallel(lista);
    }
    
    
    @GetMapping("/debug/loadtestingOnebyOne/{arraysNumber}")
    public List<EquilibriumBean> debug3(@PathVariable("arraysNumber") int arraysNumber) {
    	final String l1 = "2, 9, 3, 4, 0, 3, 3, 2, 9, 1"; // Equilibrium Index es 4
    	final String l2 = "0, -4, 7, -4, -2, 6, -3, 0"; // Equilibrium Index es 5
    	final String l3 = "9, 3, 7, 6, 8, 1, 10"; // Equilibrium Index es 3
        final String l4 = "-7,1,5,2,-4,3,0"; // Equilibrium Index es 3 y 0
        String lista = l1
				.concat(":")
				.concat(l2)
				.concat(":")
				.concat(l3)
				.concat(":")
				.concat(l4);
        // límite en mi host 9 
        // arraysNumber * 4
        for (int i = 0; i < arraysNumber; i++) {
        	lista = lista.concat(":").concat(lista);
		}
       
        return  getEquilibriumOnebyOne(lista);
    }
    
    
    @GetMapping("equilibrium/{param}")
    public String getEquilibriumParallel(@PathVariable("param") String param) {
    	final long iTime = System.nanoTime();
    	try {
    		 List<EquilibriumBean> eqbList = Collections.synchronizedList(new  ArrayList<>());
    		 String[] split = param.replace(" ", "").split(":");
    		 List<String> arrays = Arrays.asList(split);
    		 arrays.parallelStream().forEach(array -> eqbList.add(processArray(array)));
	        final long fTime = (System.nanoTime() - iTime) / 1000;
	        Logger.getGlobal().info(()-> "Hilo -> " + Thread.currentThread().getId() 
	        		+ " Duración getEquilibrium to process " +arrays.size()+ "arrays -> microsec = " + fTime 
	        		+ " -> ms = " + fTime / 1000);
			return eqbList.toString();
		} catch (Exception e) {
			Logger.getGlobal().warning(e.getMessage());
			return "Se ha producido un error";
		}
    	//return Collections.emptyList();
			
    }
    
    public List<EquilibriumBean> getEquilibriumOnebyOne(@PathVariable("param") String param) {
    	final long iTime = System.nanoTime();
 //   	try {
    		 List<EquilibriumBean> eqbList = Collections.synchronizedList(new  ArrayList<>());
    		 String[] split = param.replace(" ", "").split(":");
    		 List<String> arrays = Arrays.asList(split);
    		 arrays.stream().forEach(array -> eqbList.add(processArray(array)));
	        final long fTime = (System.nanoTime() - iTime) / 1000;
	        Logger.getGlobal().info(()-> "Hilo -> " + Thread.currentThread().getId() 
	        		+ " Duración getEquilibriumOnebyOne to process " +arrays.size()+ "arrays -> microsec = " + fTime 
	        		+ " -> ms = " + fTime / 1000);
			return eqbList;
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
    	//return Collections.emptyList();
			
    }
    
    private EquilibriumBean processArray(String array) {
        final long iTime = System.nanoTime();
        ;
        List<Integer> integers = Collections.synchronizedList(Arrays.asList(
        		array.split(",")).stream()
        			.map(String::trim)
        			.map(Integer::valueOf)
        			.collect(Collectors.toList()));
        CalculateEquilibrium ce = new CalculateEquilibrium();
        EquilibriumBean eqb = new EquilibriumBean(LocalDateTime.now(), integers, ce.getEquilibriumIndex(integers));
        final long fTime = (System.nanoTime() - iTime) / 1000;
//        Logger.getGlobal().info(()-> "Hilo -> " + Thread.currentThread().getId() 
//        		+ " Duración processArray " + integers 
//        		+ "to get index equilibrium() microsec = " + fTime 
//        		+ " -> ms = " + fTime / 1000);
        return eqb;
	}
    
}
