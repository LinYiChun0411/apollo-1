package com.game.dao;

import java.util.List;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.springframework.stereotype.Repository;

import com.game.model.SysCache;


@Repository
public class SysCacheDao extends JdbcUtilImpl<SysCache>{
	
	public Page<SysCache> getPage(){
		String sql = " select * from sys_cache ";
		return super.paged2Obj(sql);
	}
	
	public List<SysCache> getAll(){
		String sql = " select * from sys_cache ";
		return super.query2Model(sql);
	}
}
