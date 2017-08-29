package co.kr.mingilab.controller;

import java.util.HashMap;
import java.util.Map;

import co.kr.mingilab.dto.AccountDTO;
import co.kr.mingilab.interfaces.ControllerInterface;

public class FrontController {
	
	public static void main(String[] args) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		AccountDTO accountdto = new AccountDTO();
		map.put("dto1", accountdto);
		
		ControllerInterface start = new LoginController();
		start.execute(map);
		
	} // main
	
} //class
