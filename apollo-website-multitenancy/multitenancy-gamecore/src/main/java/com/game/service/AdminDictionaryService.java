package com.game.service;

import java.util.List;

public interface AdminDictionaryService {
	
	public List getUserDictionarys(Long type);
	
	public String getDictionaryName(Long type,Long id);
	
}
