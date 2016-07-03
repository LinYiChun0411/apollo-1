架构：
spring mvc、spring jdbc、c3p0、redis缓存、log4j、postgresql、frame（基于spring的多租户开发基础框架）

项目部署：

7.数据库使用postgresql最新版(postgresql-9.5.3-2-osx.dmg),sql文件位于game/src/main/webapp/doc/db/目录下
8.使用postgres自带IDE，新建数据game,选中game数据库右键点击-》恢复=》选中对应数据库文件=》点击恢复按钮即可
9.运行项目前，必须开启redis(redis-3.0.7.tar.gz)
10.localhost:8080/game  总控后台   账号：root  密码：1234
11.后台域名管理配置下站点域名 （例如aa.com） 
12.本地修改下host文件把aa.com解析到本机    aa.com:8080/game （站点） aa.com:8080/game/agent （租户后台）


开发注意事项：
	A：数据库设计
		1、数据库表名、字段名一律小写，命名中出现多个单词以下划线分割例如 table_name、column_name
		2、数据库表主键用自增长类型 serial
		3、用于表示类型、状态等 integer类型字段 取值以1开始. 因为Validator.isNull方法会把0也认为是空值
		4、使用存储过程select add_base_columns('table_name');可自动生成字段create_datetime、modify_datetime、create_user_id、create_datetime
	B：java代码
		1.所有dao类、model类都是写在公共模块  game-core中
		2.dao.save(model);model若是继承BaseModel的话，可自动生成create_datetime、modify_datetime、create_user_id、create_datetime的值
		3.http://localhost:8080/admin/model.do  模块可以用来自动生成实体类
		4.controller路径规则   
				/admin/*  总控后台
				/agent/*  代理后台 
				/*        会员前端
		5.权限注解@Permission  @NotNeedLogin 请参照PermissionInterceptor.preHandle方法		
		6.session只存储当前登录用户信息，其他相关联数据存储请使用redis缓存去存储
	c: 资源文件目录
		1.公共模块目录 game/src/main/webapp/common/template/*  （体育模块、彩票模块、手机端、第三方）
		2.站点目录  game/src/main/webapp/memeber/*
		3.站点模块目录   /game/src/main/webapp/member/a001/module/*   （以a001站点为例）
	D:redis缓存使用
		1.缓存建议使用CacheUtil.getNull2Set方法
