package com.game.service.impl;

import java.util.List;
import java.util.Map;

import org.jay.frame.FrameProperites;
import org.jay.frame.jdbc.Constants;
import org.jay.frame.jdbc.DBType;
import org.jay.frame.jdbc.JdbcColumn;
import org.jay.frame.jdbc.JdbcModel;
import org.jay.frame.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.FrameDao;
import com.game.service.CodeCreatorService;
import com.game.util.TemplateUtil;

@Repository
public class CodeCreatorServiceImpl implements CodeCreatorService{

	@Autowired
	private FrameDao frameDao;
	
	public String createModel(String tableName,JdbcModel parent) {
		Map map = frameDao.getTableFrame(tableName);
		
		if(FrameProperites.DB_TYPE == DBType.POSTGRESQL){
			Map commentMap = frameDao.getPostgresqlComment(tableName);
			setComments(map,commentMap);
		}
		
		if(parent != null){
			Map<String,JdbcColumn> parentMap = parent.getPropertyMap();
			List<Map> colList = (List<Map>)map.get(PROPERTY_LIST);
			for (String key : parentMap.keySet()) {
				for (int i = colList.size()-1; i >= 0; i--) {
					Map tmp = colList.get(i);
					if(key.equals(tmp.get(JAVA_PROPERTY))){
						colList.remove(i);
					}
				}
			}
			Class c = parent.getC();
			map.put("parentClass",c.getSimpleName());
			map.put("fullParentClass", c.getName());
		}
		map.put("tableName", tableName);
		map.put(CLASS_NAME, toClassName(tableName));
		return TemplateUtil.toStr(map,"model_tpl.java");
	}
	
	private void setComments(Map data,Map comments){
		List cols = (List)data.get(CodeCreatorService.PROPERTY_LIST);
		for (int i = 0; i < cols.size(); i++) {
			Map col = (Map)cols.get(i);
			String cn = (String)col.get(CodeCreatorService.COLUMN_NAME);
			//String cn = StringUtil.trim2Empty();
			String comment = (String)comments.get(cn);
			col.put(CodeCreatorService.COLUMN_COMMENT, comment);
		}
	
	}
	
	private String toClassName(String tableName){
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		for (int i = 0; i < tableName.length(); i++) {
			char c = tableName.charAt(i);
			if(flag || i == 0){
				sb.append(Character.toUpperCase(c));
				flag = false;
				continue;
			}else if(c == '_'){
				flag = true;
				continue;
			}
			sb.append(Character.toLowerCase(c));
		}
		return sb.toString();
	}
}
