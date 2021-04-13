package com.bitcamp.myapp.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	
	@RequestMapping("/ajaxView")
	public String ajaxView() {
		return "ajax/ajaxView";
	}
																		//한글이 리턴되는 경우 인코딩
	@RequestMapping(value="/ajaxString", method=RequestMethod.GET, produces="application/text;charset=UTF-8")
	@ResponseBody //ajax 컨트롤러 인 경우 return이 곧 데이터이기떄문에 responseBody를 적용한다.
	public String ajaxString(String num, String name, String id) {
		return num+",   " + name+ ",  "+id;
	}
	/*public String ajaxString(HttpServletRequest req) {
		String num = req.getParameter("num");
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		
		String msg = "num=" + num + ", name="+ name +", id="+ id;
		System.out.println("msg = " + msg);
		
		return "서버에서 받은 데이터--> " + msg;
	}*/
	
	@RequestMapping("/ajaxObject")
	@ResponseBody
	public TestVO ajaxObject(TestVO vo) {
		vo.setTel("010-1234-5678");
		vo.setAddr("서울시 마포구 범백로");
		return vo;
	}
	
	@RequestMapping("/ajaxList")
	@ResponseBody
	public List<TestVO> ajaxList() {
		List<TestVO> list =new ArrayList<TestVO>();
		list.add(new TestVO("1","홍길동","kang","010-1111-2222", "서울시 마포구"));
		list.add(new TestVO("2","김길동","sna","010-3333-2222", "서울시 서대문구"));
		list.add(new TestVO("3","최길동","san","010-2222-2222", "서울시 김포구"));
		list.add(new TestVO("4","박길동","lee","010-5312-3433", "서울시 수로구"));
		list.add(new TestVO("5","이길용","park","010-4321-1234", "서울시 명구"));
		
		return list;
	}
	
	@RequestMapping("/ajaxMap")
	@ResponseBody
	public HashMap<String, TestVO> ajaxMap() {
		HashMap<String, TestVO> map= new HashMap<String, TestVO>();
		
		map.put("p1", new TestVO("2","김길동","sna","010-3333-2222", "서울시 서대문구"));
		map.put("p2", new TestVO("3","곰길동","qer","010-1234-4321", "서울시 멍구"));
		map.put("p3", new TestVO("4","이길동","wer","010-3242-2343", "서울시 기구"));
		map.put("p4", new TestVO("5","구길동","ert","010-4322-2344", "서울시 영구"));
		
		return map;
	}
	
	@RequestMapping("/ajaxJson")
	@ResponseBody
	public String ajaxJson() {
		int no = 1234;
		String name="홍길동";
		String tel = "010-1234-5678";
		String addr = "서울시 마포구";
		String email = "abcd@naver.com";
		
		String jsonStr = "{\"no\":\""+no +"\",\"name\":\""+name+"\",\"tel\":\""+tel+"\",\"addr\":\""+addr+"\",\"email\":\""+email+"\"}";
		System.out.println(jsonStr);
		
		return jsonStr;
	}
	/*
	 
	 "{"no":"1234", "name":"홍길동", "tel": "010-6231-9238", "addr":"서울시 마포구"}"
	 
	 
	 */
}
