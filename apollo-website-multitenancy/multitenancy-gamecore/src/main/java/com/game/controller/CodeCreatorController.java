package com.game.controller;

import org.jay.frame.exception.JayFrameException;
import org.jay.frame.jdbc.JdbcModel;
import org.jay.frame.jdbc.JdbcModelSet;
import org.jay.frame.util.ActionUtil;
import org.jay.frame.util.MixUtil;
import org.jay.frame.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.permission.annotation.NotNeedLogin;
import com.game.service.CodeCreatorService;
import com.game.service.impl.FrameServiceImpl;
import com.game.util.SpringUtil;

@Controller
public class CodeCreatorController extends ActionUtil{
	
	
	@Autowired
	private CodeCreatorService creatorService;
	
	@NotNeedLogin
	@RequestMapping("/admin/model")
	public String model(){
		return "/admin/page/model_tool.jsp";
	}
	
	@NotNeedLogin
	@RequestMapping("/admin/createModel")
	@ResponseBody
	public void createModel(String tableName,String className){
		
		if(Validator.isNull(tableName)){
			throw new JayFrameException(JayFrameException.MISSING_PARAMETER,"tableName");	
		}
		JdbcModel model = null;
		try{
			//className = "org.jay.frame.jdbc.model.BaseModel";
			if(Validator.isNotNull(className)){
				Class clazz = Class.forName(className);
				model = JdbcModelSet.get(clazz);
				if(JdbcModelSet.get(clazz) == null){
					throw new JayFrameException(JayFrameException.NOT_JDBC_MODEL,className);
				}
			}
		}catch(ClassNotFoundException e){
			throw new JayFrameException(JayFrameException.CLASS_NOT_FOUND,className);
		}
		String output = creatorService.createModel(tableName,model);
		super.renderJson(MixUtil.newHashMap("success",true,"code",output));
	}
	
}
