package com.central.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.central.assignment.model.CalculateRequest;
import com.central.assignment.service.CaluclateService;

@RestController
@RequestMapping("/api")
public class CaluclatorController {
	
	@Autowired
	CaluclateService caluclateService;
	
	/*
	 * API input expression as operators and operands arrays
	 * Example Arrays
	 * operands = {5,6,9,2}
	 * operators = {"*","+","-"} - length should be less than operands length
	 */
	@PostMapping(value="/caluclate")
	public Object caluclate(@RequestBody CalculateRequest calculateRequest){
		return caluclateService.caluclate(calculateRequest.getOperands(), calculateRequest.getOperators());
	}
	
	/*
	 * API input expression as string
	 */
	@GetMapping(value="/caluclate/{expression}")
	public Object caluclateByExpression(@PathVariable String expression){
		return caluclateService.caluclate(expression);
	}
	

}
