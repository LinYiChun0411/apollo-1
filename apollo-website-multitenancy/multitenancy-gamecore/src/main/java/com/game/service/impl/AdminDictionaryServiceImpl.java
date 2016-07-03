package com.game.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jay.frame.util.MixUtil;
import org.jay.frame.util.StringUtil;
import org.springframework.stereotype.Repository;

import com.game.model.AdminDictionary;
import com.game.service.AdminDictionaryService;

@Repository
public class AdminDictionaryServiceImpl implements AdminDictionaryService{

	@Override
	public List getUserDictionarys(Long type) {
		List dics = new ArrayList<Map<String,Object>>();
		dics.add(MixUtil.newHashMap("id",AdminDictionary.USER_STATUS_ENABLED,"name","启用"));
		dics.add(MixUtil.newHashMap("id",AdminDictionary.USER_STATUS_DISABLED,"name","禁用"));
		
		return dics;
	}

	@Override
	public String getDictionaryName(Long type, Long id) {
		List<Map> dics  = getUserDictionarys(type);
		for (Map map : dics) {
			Object did = map.get("id");
			if(StringUtil.isNotEmpty(did) && StringUtil.toLong(did).intValue() == id.intValue()){
				return StringUtil.strnull(map.get("name"));
			}
		}
		return "";
	}

	
}


