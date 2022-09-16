package com.basic.step1.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.basic.step1.logic.BoardLogic;
import com.util.HangulConversion;
import com.util.HashMapBinder;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired(required=false)
	private BoardLogic boardLogic = null;
	/*
	 * dev_web과 basic 사이 비용계산 해보기
	 * Map선언만 함 - @RequestParam 사용
	 * HashMapBinder가 필요없어짐
	 * ModelAndView도 필요없음 Model
	 * 리턴타입 : ModelAndView -> String 변경
	 */
	@GetMapping("boardList.sp4")
	public String boardList(Model model, @RequestParam Map<String, Object> pMap) {
		logger.info("boardList 호출 성공");
		List<Map<String, Object>> boardList = null;
		boardList = boardLogic.boardList(pMap);
		model.addAttribute("boardList",boardList);
		return "forward:boardList.jsp";
	}
	// @GetMapping("boardInsert.sp4")
	@PostMapping("boardInsert.sp4")
	public String boardInsert(MultipartHttpServletRequest mpRequest, @RequestParam(value="bs_file", required=false) MultipartFile bs_file) {
		logger.info("boardInsert 호출 성공:");
		int result = 0;
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(mpRequest);
		hmb.mbind(pMap);
		if(!bs_file.isEmpty()) {
			String filename = HangulConversion.toUTF(bs_file.getOriginalFilename());
			logger.info("한글처리 테스트:"+filename);
			String savePath = "C:\\workspace_spring\\basic\\src\\main\\webapp\\pds";
			// 파일에 대한 풀 네임 담기
			String fullPath = savePath+"\\"+filename;
			try {
				// File객체는 파일명을 객체화 해줌
				File file = new File(fullPath);
				// board_sub_t에 파일 크기를 담기 위해 계삭
				byte[] bytes = bs_file.getBytes();
				BufferedOutputStream bos = 
						new BufferedOutputStream(new FileOutputStream(file));
				// 실제로 파일 내용이 채워짐
				bos.write(bytes);
				bos.close();
				long size = file.length();
				double d_size = Math.floor(size/1024.0); //kb
				logger.info("size"+d_size);
				pMap.put("bs_file", filename);
				pMap.put("bs_size", d_size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		result = boardLogic.boardInsert(pMap);
		return "redirect:boardList.sp4";
	}
	@GetMapping("boardUpdate.sp4")
	public String boardUpdate(@RequestParam Map<String, Object> pMap) {
		logger.info("boardUpdate 호출 성공");
		int result = 0;
		result = boardLogic.boardUpdate(pMap);
		return "redirect:boardList.sp4";
	}
	@GetMapping("boardDetail.sp4")
	public String boardDetail(Model model, @RequestParam Map<String, Object> pMap) {
		logger.info("boardDetail 호출 성공");
		List<Map<String, Object>> boardList = null;
		boardList = boardLogic.boardDetail(pMap);
		model.addAttribute("boardList",boardList);
		return "forward:read.jsp";
	}
	@GetMapping("boardDelete.sp4")
	public String boardDelete(@RequestParam Map<String, Object> pMap) {
		logger.info("boardDelete 호출 성공");
		int result = 0;
		result = boardLogic.boardDelete(pMap);
		return "redirect:boardList.sp4";
	}

}
