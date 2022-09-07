package com.basic.step1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.MemberVO;
@Service
public class AuthLogic {
	// Logger logger = Logger.getLogger(AuthLogic.class);
	@Autowired // setter가 필요없음
	private AuthDao authDao = null;
	public MemberVO login() {
		//logger.info("login 호출 성공");
		MemberVO mVO = null;
		mVO = authDao.login();
		return mVO;
	}
	
	
}
