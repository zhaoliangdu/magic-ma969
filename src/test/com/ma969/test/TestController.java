package com.ma969.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@RequestMapping("testview")
	public ModelAndView test() {
		return new ModelAndView("jsp/systemset/helptest");
	}

	@RequestMapping("test")
	@ResponseBody
	public void test1(@RequestParam("a") String a, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.println("b");
		writer.flush();
		writer.close();
	}
}
