package com.basic.step1;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.MemberVO;
// DAO 뒤에서는 오라클 서버와 연동하기
// myBatos 레이어 대한 설정 필요함
@Service
public class AuthDao {
	// Logger logger = Logger.getLogger(AuthDao.class);
	// SqlSessionTemplate는 mybatis-spring.jar에서 제공하는 클래스로
	// SqlSession의 역할
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate = null;
	public MemberVO login() {
		// logger.info("login호출 성공");
		MemberVO memVO = null;
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("mem_id", "tomato");
		pMap.put("mem_pw", "123");
		// selectOne은 조회 건수가 반드시 한 건이어야 한다.
		// Too Many...
		memVO = sqlSessionTemplate.selectOne("login", pMap);
		if(memVO != null) {
			// logger.info(memVO.getMem_name()+memVO.getMem_id());			
		}
		return memVO;
	}
}
