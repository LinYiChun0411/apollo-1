package com.game.controller.admin.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jay.frame.util.JsonUtil;
import org.jay.frame.util.MixUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.game.cache.redis.RedisAPI;
import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.SysCache;
import com.game.permission.annotation.CheckType;
import com.game.permission.annotation.Permission;
import com.game.service.SysCacheService;
/**
 * 缓存中心
 * @author admin
 *
 */
@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH+"/cache")
public class CacheCenterController extends BaseAdminController{
	
	@Autowired
	private SysCacheService cahceService;
	
	@RequestMapping("/index")
	public String index(){
		return super.goPage("/page/system/cache.jsp");
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public void list(){
		super.render(cahceService.getPage());
	}
	
	@Permission(CheckType.FUNCTION)
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(){
		long id = $cl("id");
		cahceService.delCache(id);
		super.renderSuccess();
	}
	
	@Permission(CheckType.FUNCTION)
	@RequestMapping("/add")
	@ResponseBody
	public void add(){
		SysCache cache = getCache();
		cache.setId(null);
		cahceService.saveCache(cache);
		super.renderSuccess();
	}
	
	private SysCache getCache(){
		String json = super.$c("data");
		SysCache cache = JsonUtil.toBean(json, SysCache.class);
		isNotNull(cache.getKey(),"键值不能为空");
		isNotNull(cache.getName(),"名称不能为空");
		return cache;	
	}
	
	@Permission(CheckType.FUNCTION)
	@RequestMapping("/update")
	@ResponseBody
	public void update(){
		SysCache cache = getCache();
		cahceService.saveCache(cache);
		isNotNull(cache.getId(),"id不能为空");
		super.renderSuccess();
	}
	
	/**
	 * 获取缓存
	 */
	@RequestMapping("/getCacheList")
	@ResponseBody
	public void getCacheList(){
		long db = $cl("db");
		String keyHeader = $c("key");
		Set<String> set = RedisAPI.getCacheList(keyHeader, (int)db);
		List list = new ArrayList();
		for (String val : set) {
			Map tmp = new HashMap();
			tmp.put("key", val);
			list.add(tmp);
		}
		Map data = new HashMap();
		data.put("total", set.size());
		data.put("rows", list);
		super.renderJson(data);
	}
	
	/**
	 * 删除一条缓存记录
	 */
	@RequestMapping("/delCache")
	@ResponseBody
	public void delCache(){
		String json = $c("key");
		int db = (int)$cl("db");
		JSONArray array = JSON.parseArray(json);
		if(array.size() == 0){
			super.renderFailure("请选中要删除的缓存");
			return;
		}
		String []keys = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			keys[i] = array.getString(i);
		}
		RedisAPI.delCache(db, keys);
		super.renderSuccess();
	}
	
	@RequestMapping("/getCacheContext")
	@ResponseBody
	public void getCacheContext(){
		String key = $c("key");
		int db = (int)$cl("db");
		String content = RedisAPI.getCache(key, db);
		super.renderJson(MixUtil.newHashMap("content",content));
	}
}
