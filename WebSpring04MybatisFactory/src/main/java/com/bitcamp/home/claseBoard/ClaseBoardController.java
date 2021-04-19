package com.bitcamp.home.claseBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import oracle.jdbc.internal.OracleConnection.TransactionState;

@Controller
public class ClaseBoardController {
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	private DataSourceTransactionManager transactionManger;
	
	//글목록
	@RequestMapping("/claseList")
	public ModelAndView claseList() {
		ModelAndView mav = new ModelAndView();
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		
		mav.addObject("totalRecord", dao.getTotalRecord());
		mav.addObject("list", dao.claseAllRecord());
		mav.setViewName("claseBoard/claseList");
		return mav;
	}
	
	@RequestMapping("claseWrite")
	public String claseWrite() {
		
		return "claseBoard/claseWrite";
	}
	
	//글쓰기
	@RequestMapping(value="/claseWriteOk", method=RequestMethod.POST)
	public ModelAndView claseWriteOk(ClaseBoardDTO vo, HttpServletRequest req) {
		vo.setIp(req.getRemoteAddr());
		vo.setUserid((String)req.getSession().getAttribute("logId"));
		
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		int cnt = dao.claseInsert(vo);
		
		ModelAndView mav = new ModelAndView();
		if(cnt>0) {
			mav.setViewName("redirect:claseList");
		}else {
			mav.setViewName("redirect:claseWrite");
		}
		return mav;
	}
	
	@RequestMapping("/claseView")
	public ModelAndView claseView(int no) {
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		
		ModelAndView mav = new ModelAndView();
		
		dao.hitcount(no);//조회수증가
		mav.addObject("dto", dao.claseSelect(no));
		mav.setViewName("claseBoard/claseView");
		
		//이전글 다음글
//		PrevNextVO vo = dao.lagLeadSelect(no);
		return mav;
	}
	//답글쓰기 폼
	@RequestMapping("/claseWriteForm")
	public ModelAndView claseWirteForm(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("no", no);
		mav.setViewName("claseBoard/claseWriteForm");
		return mav;
	}
	
	//답글쓰기
	@RequestMapping(value="/claseWriteFormOk", method=RequestMethod.POST)
	@Transactional(rollbackFor = {Exception.class, RuntimeException.class})//트랜잭션 어노테이션추가
	public ModelAndView claseWriteFormOk(ClaseBoardDTO dto, HttpSession session, HttpServletRequest req) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus stauts = transactionManger.getTransaction(def);
		
		dto.setIp(req.getRemoteAddr());
		dto.setUserid((String)session.getAttribute("logId"));
		
		ModelAndView mav = new ModelAndView();
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		try { //트랜잭션세팅
			//1. 원글의 ref,step, lvl을 선택한다.
			ClaseBoardDTO orgDto = dao.oriInfor(dto.getNo());
			
			//2. lvl 증가 : 원글번호가 같고 lv이 원글번호의 lvl보다 크면 1증가한다.
			int lvlCnt = dao.lvlcount(orgDto);
			System.out.println("lvlCnt = " + lvlCnt);
			//3. 답글추가
			dto.setRef(orgDto.getRef());
			dto.setStep(orgDto.getStep()+1);
			dto.setLvl(orgDto.getLvl()+1);
			
			int cnt = dao.claseDataInsert(dto);
			mav.addObject("no", dto.getNo());
			if(cnt>0) {
				//원글보기
				mav.setViewName("redirect:claseView");
				transactionManger.commit(stauts);
			}else {
				mav.setViewName("redirect:claseWriteForm");
				transactionManger.rollback(stauts);
			}
		}catch (Exception e) {
			mav.addObject("no", dto.getNo());
			mav.setViewName("redirect:claseWriteFrom");
		}
		return mav;
	}
	//수정폼으로 이동
	@RequestMapping("/claseEdit")
	public ModelAndView claseEdit(int no) {
		ModelAndView mav = new ModelAndView();
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		mav.addObject("dto", dao.claseSelect(no));
		mav.setViewName("claseBoard/claseEdit");
		
		return mav;
	}
	
	//글수정
	@RequestMapping(value="/claseEditOk", method=RequestMethod.POST)
	public ModelAndView claseEditOk(ClaseBoardDTO dto, HttpSession session) {
		dto.setUserid((String)session.getAttribute("logId"));
		ModelAndView mav = new ModelAndView();
		
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		int result = dao.claseUpdate(dto);
		System.out.println("dto.getNO = " + dto.getNo());
		System.out.println(" result = " + result); //잘나옴
		mav.addObject("no", dto.getNo());
		if(result>0) {//수정 성공시 --글내용보기
			mav.setViewName("redirect:claseView");
		}else {//수정 실패시 -- 수정페이지로
			mav.setViewName("redirect:claseEdit");
		}
		
		return mav;
	}
	@RequestMapping("/claseDel")
	public ModelAndView claseDel(int no, HttpSession session) {
		ClaseBoardDAOImp dao = sqlSession.getMapper(ClaseBoardDAOImp.class);
		String userid = (String)session.getAttribute("logId");
		//원글삭제가 가능하고 답글이 있는경우 답글까지 지운다.
		//답글은 제목과 글내용을 삭제된글입니다 라고
		
		//원글의 정보 -> step을 가져오거나, no, ref가 같은지 본다.
		ClaseBoardDTO orgData = dao.getStep(no); //step, userid
		int result=0;
		
		if(orgData.getStep()==0 && orgData.getUserid().equals(userid)) {//원글임
			result=dao.claseDelete(no, userid);
		}else if(orgData.getStep()>0 && orgData.getUserid().equals(userid)) {//답글이다.
			result = dao.claseDeleteUpdate(no, userid);
		}
		
		ModelAndView mav = new ModelAndView();
//		if(result>0) {//삭제
//			mav.setViewName("redirect:claseList");
//		}else {
//			mav.addObject("no", no);
//			mav.setViewName("redirect:claseView");
//		}
		mav.addObject("result", result);
		mav.addObject("no", no);
		mav.setViewName("claseboard/delCheck");
		return mav;
	}
}
