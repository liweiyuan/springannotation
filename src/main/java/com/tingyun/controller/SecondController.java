package com.tingyun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("/second")
public class SecondController {

	@RequestMapping(path="test")
	@ResponseBody
	public String second(){
		try {
			Thread.sleep(new Random(System.currentTimeMillis()).nextInt(200) + 500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "ActionName 应为 /second/test";
	}
}
