/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : PostgreSQL
 Source Server Version : 90503
 Source Host           : localhost
 Source Database       : game
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90503
 File Encoding         : utf-8

 Date: 07/03/2016 13:41:47 PM
*/

-- ----------------------------
--  Sequence structure for admin_group_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."admin_group_menu_id_seq";
CREATE SEQUENCE "public"."admin_group_menu_id_seq" INCREMENT 1 START 133 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."admin_group_menu_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for admin_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."admin_menu_id_seq";
CREATE SEQUENCE "public"."admin_menu_id_seq" INCREMENT 1 START 32 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."admin_menu_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for admin_user_group_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."admin_user_group_id_seq";
CREATE SEQUENCE "public"."admin_user_group_id_seq" INCREMENT 1 START 17 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."admin_user_group_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for admin_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."admin_user_id_seq";
CREATE SEQUENCE "public"."admin_user_id_seq" INCREMENT 1 START 8 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."admin_user_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for agent_base_config_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."agent_base_config_id_seq";
CREATE SEQUENCE "public"."agent_base_config_id_seq" INCREMENT 1 START 82 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."agent_base_config_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for agent_base_config_value_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."agent_base_config_value_id_seq";
CREATE SEQUENCE "public"."agent_base_config_value_id_seq" INCREMENT 1 START 16 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."agent_base_config_value_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for agent_menu_group_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."agent_menu_group_id_seq";
CREATE SEQUENCE "public"."agent_menu_group_id_seq" INCREMENT 1 START 96 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."agent_menu_group_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for agent_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."agent_menu_id_seq";
CREATE SEQUENCE "public"."agent_menu_id_seq" INCREMENT 1 START 18 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."agent_menu_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for bc_buyLot_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."bc_buyLot_id_seq";
CREATE SEQUENCE "public"."bc_buyLot_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."bc_buyLot_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for bc_lottery_data_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."bc_lottery_data_id_seq";
CREATE SEQUENCE "public"."bc_lottery_data_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."bc_lottery_data_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for bc_lottery_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."bc_lottery_id_seq";
CREATE SEQUENCE "public"."bc_lottery_id_seq" INCREMENT 1 START 20 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."bc_lottery_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for bc_lottery_play_group_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."bc_lottery_play_group_id_seq";
CREATE SEQUENCE "public"."bc_lottery_play_group_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."bc_lottery_play_group_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for bc_lottery_play_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."bc_lottery_play_id_seq";
CREATE SEQUENCE "public"."bc_lottery_play_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."bc_lottery_play_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for bc_lottery_prep_code_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."bc_lottery_prep_code_id_seq";
CREATE SEQUENCE "public"."bc_lottery_prep_code_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."bc_lottery_prep_code_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for bc_lottery_time_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."bc_lottery_time_id_seq";
CREATE SEQUENCE "public"."bc_lottery_time_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."bc_lottery_time_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for bc_station_lottery_play_group_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."bc_station_lottery_play_group_id_seq";
CREATE SEQUENCE "public"."bc_station_lottery_play_group_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."bc_station_lottery_play_group_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for bc_station_lottery_play_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."bc_station_lottery_play_id_seq";
CREATE SEQUENCE "public"."bc_station_lottery_play_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."bc_station_lottery_play_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for mny_bank_list_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mny_bank_list_id_seq";
CREATE SEQUENCE "public"."mny_bank_list_id_seq" INCREMENT 1 START 7 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."mny_bank_list_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for mny_com_record_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mny_com_record_id_seq";
CREATE SEQUENCE "public"."mny_com_record_id_seq" INCREMENT 1 START 10 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."mny_com_record_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for mny_draw_record_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mny_draw_record_id_seq";
CREATE SEQUENCE "public"."mny_draw_record_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."mny_draw_record_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for mny_money_type_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mny_money_type_id_seq";
CREATE SEQUENCE "public"."mny_money_type_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."mny_money_type_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for mny_record_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mny_record_id_seq";
CREATE SEQUENCE "public"."mny_record_id_seq" INCREMENT 1 START 93 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."mny_record_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for sys_announcement_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_announcement_id_seq";
CREATE SEQUENCE "public"."sys_announcement_id_seq" INCREMENT 1 START 4 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."sys_announcement_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for sys_cache_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_cache_id_seq";
CREATE SEQUENCE "public"."sys_cache_id_seq" INCREMENT 1 START 8 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."sys_cache_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for sys_member_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_member_id_seq";
CREATE SEQUENCE "public"."sys_member_id_seq" INCREMENT 1 START 11 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."sys_member_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for sys_proposal_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_proposal_id_seq";
CREATE SEQUENCE "public"."sys_proposal_id_seq" INCREMENT 1 START 3 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."sys_proposal_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for sys_station_domain_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_station_domain_id_seq";
CREATE SEQUENCE "public"."sys_station_domain_id_seq" INCREMENT 1 START 10 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."sys_station_domain_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for sys_station_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_station_id_seq";
CREATE SEQUENCE "public"."sys_station_id_seq" INCREMENT 1 START 8 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."sys_station_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for sys_third_platform_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_third_platform_id_seq";
CREATE SEQUENCE "public"."sys_third_platform_id_seq" INCREMENT 1 START 7 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."sys_third_platform_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for third_station_group_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."third_station_group_id_seq";
CREATE SEQUENCE "public"."third_station_group_id_seq" INCREMENT 1 START 9 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."third_station_group_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Function structure for public.optmoney2(int4, numeric)
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."optmoney2"(int4, numeric);
CREATE FUNCTION "public"."optmoney2"(IN userid int4, IN optmoney numeric) RETURNS "_numeric" 
	AS $BODY$
    DECLARE 
	mny numeric(19,2);
	sql1 varchar;
	sql2 varchar;
	result numeric[2];
 BEGIN
    sql1 := 'select balance from mny_money where member_id = ''' || userId ||''' for update ';
    execute sql1 into mny ;
    IF mny + optmoney < 0 THEN
	result[0] := 0;
	result[1] := mny;
	return result;
    END IF;
    sql2 := 'update mny_money set balance = balance + ''' || optMoney ||''' where  member_id = ''' || userId ||'''';
    execute sql2;
    result[0] := 1;
    result[1] := mny;
    RETURN result;
 END;
 $BODY$
	LANGUAGE plpgsql
	COST 100
	CALLED ON NULL INPUT
	SECURITY INVOKER
	VOLATILE;
ALTER FUNCTION "public"."optmoney2"(IN userid int4, IN optmoney numeric) OWNER TO "postgres";

-- ----------------------------
--  Function structure for public.add_base_columns(varchar)
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."add_base_columns"(varchar);
CREATE FUNCTION "public"."add_base_columns"(IN tablename varchar) RETURNS "int4" 
	AS $BODY$
	DECLARE 
	sql_str varchar;
 BEGIN
	sql_str := 'ALTER TABLE  ' || tableName || ' ADD COLUMN create_datetime timestamp without time zone;COMMENT ON COLUMN '|| tableName ||'.create_datetime IS ''创建时间''';
	execute sql_str;

	sql_str := 'ALTER TABLE ' || tableName || '  ADD COLUMN modify_datetime timestamp without time zone;COMMENT ON COLUMN ' || tableName || ' .modify_datetime IS ''修改时间''';
	execute sql_str;
	
	sql_str := 'ALTER TABLE ' || tableName || '  ADD COLUMN create_user_id integer;COMMENT ON COLUMN  '|| tableName ||'.create_user_id IS ''创建者''';
	execute sql_str;

	sql_str := 'ALTER TABLE ' || tableName || ' ADD COLUMN modify_user_id integer;COMMENT ON COLUMN '|| tableName ||'.modify_user_id IS ''修改者''';
	execute sql_str;

	sql_str := 'ALTER TABLE  ' || tableName || ' ADD COLUMN flag_active integer;COMMENT ON COLUMN  ' || tableName || '.flag_active IS ''数据状态 0：逻辑删除 1:正常''';
	execute sql_str;
	return 1;
 END;
 $BODY$
	LANGUAGE plpgsql
	COST 100
	CALLED ON NULL INPUT
	SECURITY INVOKER
	VOLATILE;
ALTER FUNCTION "public"."add_base_columns"(IN tablename varchar) OWNER TO "postgres";

-- ----------------------------
--  Function structure for public.optmoney(int4, numeric)
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."optmoney"(int4, numeric);
CREATE FUNCTION "public"."optmoney"(IN userid int4, IN optmoney numeric) RETURNS "_numeric" 
	AS $BODY$
    DECLARE 
	mny numeric(19,2);
	sql1 varchar;
	sql2 varchar;
	result numeric[2];
 BEGIN
    sql1 := 'select money from mny_money where account_id = ''' || userId ||''' for update ';
    execute sql1 into mny ;
    IF mny + optmoney < 0 THEN
	result[0] := 0;
	result[1] := mny;
	return result;
    END IF;
    sql2 := 'update mny_money set money = money + ''' || optMoney ||''' where  account_id = ''' || userId ||'''';
    execute sql2;
    result[0] := 1;
    result[1] := mny;
    RETURN result;
 END;
 $BODY$
	LANGUAGE plpgsql
	COST 100
	CALLED ON NULL INPUT
	SECURITY INVOKER
	VOLATILE;
ALTER FUNCTION "public"."optmoney"(IN userid int4, IN optmoney numeric) OWNER TO "postgres";

-- ----------------------------
--  Table structure for C3P0TestTable
-- ----------------------------
DROP TABLE IF EXISTS "public"."C3P0TestTable";
CREATE TABLE "public"."C3P0TestTable" (
	"a" char(1) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."C3P0TestTable" OWNER TO "postgres";

-- ----------------------------
--  Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."admin_user";
CREATE TABLE "public"."admin_user" (
	"id" int4 NOT NULL DEFAULT nextval('admin_user_id_seq'::regclass),
	"account" varchar(50) COLLATE "default",
	"password" varchar(50) COLLATE "default",
	"group_id" int4,
	"create_datetime" timestamp(6) NULL,
	"modify_datetime" timestamp(6) NULL,
	"create_user_id" int4,
	"modify_user_id" int4,
	"flag_active" int4,
	"status" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."admin_user" OWNER TO "postgres";

COMMENT ON TABLE "public"."admin_user" IS '总控后台用户表';
COMMENT ON COLUMN "public"."admin_user"."id" IS '用户id';
COMMENT ON COLUMN "public"."admin_user"."account" IS '登陆用户名';
COMMENT ON COLUMN "public"."admin_user"."password" IS '账号密码';
COMMENT ON COLUMN "public"."admin_user"."group_id" IS '关联admin_user_group表ID';
COMMENT ON COLUMN "public"."admin_user"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."admin_user"."modify_datetime" IS '修改时间';
COMMENT ON COLUMN "public"."admin_user"."create_user_id" IS '创建者';
COMMENT ON COLUMN "public"."admin_user"."modify_user_id" IS '修改者';
COMMENT ON COLUMN "public"."admin_user"."flag_active" IS '数据状态 0：逻辑删除 1:正常';
COMMENT ON COLUMN "public"."admin_user"."status" IS '账号状态  1 停用  2 正常启用';

-- ----------------------------
--  Records of admin_user
-- ----------------------------
BEGIN;
INSERT INTO "public"."admin_user" VALUES ('5', 'jay', '1234', '1', '2016-04-05 15:14:40.545', null, '1', '1', '1', '2');
INSERT INTO "public"."admin_user" VALUES ('4', 'lex', '48432FB7A390A44D61531738CBB4F2AE', '1', '2016-04-05 15:13:33.25', '2016-04-14 19:50:48', '1', '1', '1', '2');
INSERT INTO "public"."admin_user" VALUES ('1', 'root', '0321666D2AF0B0CB11D7BE46E3D5217E', '0', '2016-03-23 16:12:00', '2016-04-06 09:48:39.479', '1', '1', '1', '2');
COMMIT;

-- ----------------------------
--  Table structure for admin_user_group
-- ----------------------------
DROP TABLE IF EXISTS "public"."admin_user_group";
CREATE TABLE "public"."admin_user_group" (
	"id" int4 NOT NULL DEFAULT nextval('admin_user_group_id_seq'::regclass),
	"name" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."admin_user_group" OWNER TO "postgres";

COMMENT ON TABLE "public"."admin_user_group" IS '总控后台用户组别';
COMMENT ON COLUMN "public"."admin_user_group"."name" IS '组别名称';

-- ----------------------------
--  Records of admin_user_group
-- ----------------------------
BEGIN;
INSERT INTO "public"."admin_user_group" VALUES ('1', '超级管理员');
INSERT INTO "public"."admin_user_group" VALUES ('4', '客服部');
COMMIT;

-- ----------------------------
--  Table structure for mny_money
-- ----------------------------
DROP TABLE IF EXISTS "public"."mny_money";
CREATE TABLE "public"."mny_money" (
	"account_id" int4 NOT NULL,
	"money" numeric(11,2) DEFAULT 0,
	"money_type_id" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."mny_money" OWNER TO "postgres";

COMMENT ON TABLE "public"."mny_money" IS '会员平台余额';
COMMENT ON COLUMN "public"."mny_money"."account_id" IS '用户id';
COMMENT ON COLUMN "public"."mny_money"."money" IS '本平台余额';

-- ----------------------------
--  Records of mny_money
-- ----------------------------
BEGIN;
INSERT INTO "public"."mny_money" VALUES ('2', '-5.00', '1');
INSERT INTO "public"."mny_money" VALUES ('3', '4687.00', '1');
COMMIT;

-- ----------------------------
--  Table structure for admin_group_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."admin_group_menu";
CREATE TABLE "public"."admin_group_menu" (
	"id" int4 NOT NULL DEFAULT nextval('admin_group_menu_id_seq'::regclass),
	"group_id" int4,
	"menu_id" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."admin_group_menu" OWNER TO "postgres";

COMMENT ON COLUMN "public"."admin_group_menu"."id" IS '主键';
COMMENT ON COLUMN "public"."admin_group_menu"."group_id" IS '关联admin_group表 id';
COMMENT ON COLUMN "public"."admin_group_menu"."menu_id" IS '关联admin_menu表id';

-- ----------------------------
--  Records of admin_group_menu
-- ----------------------------
BEGIN;
INSERT INTO "public"."admin_group_menu" VALUES ('28', '4', '1');
INSERT INTO "public"."admin_group_menu" VALUES ('29', '4', '4');
INSERT INTO "public"."admin_group_menu" VALUES ('30', '4', '12');
INSERT INTO "public"."admin_group_menu" VALUES ('31', '4', '13');
INSERT INTO "public"."admin_group_menu" VALUES ('32', '4', '14');
INSERT INTO "public"."admin_group_menu" VALUES ('33', '4', '5');
INSERT INTO "public"."admin_group_menu" VALUES ('34', '4', '2');
INSERT INTO "public"."admin_group_menu" VALUES ('35', '4', '3');
INSERT INTO "public"."admin_group_menu" VALUES ('108', '1', '1');
INSERT INTO "public"."admin_group_menu" VALUES ('109', '1', '4');
INSERT INTO "public"."admin_group_menu" VALUES ('110', '1', '12');
INSERT INTO "public"."admin_group_menu" VALUES ('111', '1', '13');
INSERT INTO "public"."admin_group_menu" VALUES ('112', '1', '14');
INSERT INTO "public"."admin_group_menu" VALUES ('113', '1', '5');
INSERT INTO "public"."admin_group_menu" VALUES ('114', '1', '7');
INSERT INTO "public"."admin_group_menu" VALUES ('115', '1', '6');
INSERT INTO "public"."admin_group_menu" VALUES ('116', '1', '17');
INSERT INTO "public"."admin_group_menu" VALUES ('117', '1', '15');
INSERT INTO "public"."admin_group_menu" VALUES ('118', '1', '16');
INSERT INTO "public"."admin_group_menu" VALUES ('119', '1', '22');
INSERT INTO "public"."admin_group_menu" VALUES ('120', '1', '23');
INSERT INTO "public"."admin_group_menu" VALUES ('121', '1', '24');
INSERT INTO "public"."admin_group_menu" VALUES ('122', '1', '18');
INSERT INTO "public"."admin_group_menu" VALUES ('123', '1', '19');
INSERT INTO "public"."admin_group_menu" VALUES ('124', '1', '20');
INSERT INTO "public"."admin_group_menu" VALUES ('125', '1', '21');
INSERT INTO "public"."admin_group_menu" VALUES ('126', '1', '2');
INSERT INTO "public"."admin_group_menu" VALUES ('127', '1', '3');
INSERT INTO "public"."admin_group_menu" VALUES ('128', '1', '25');
INSERT INTO "public"."admin_group_menu" VALUES ('129', '1', '28');
INSERT INTO "public"."admin_group_menu" VALUES ('130', '1', '26');
INSERT INTO "public"."admin_group_menu" VALUES ('131', '1', '27');
INSERT INTO "public"."admin_group_menu" VALUES ('132', '1', '29');
INSERT INTO "public"."admin_group_menu" VALUES ('133', '1', '30');
COMMIT;

-- ----------------------------
--  Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."admin_menu";
CREATE TABLE "public"."admin_menu" (
	"id" int4 NOT NULL DEFAULT nextval('admin_menu_id_seq'::regclass),
	"name" varchar(50) COLLATE "default",
	"url" varchar(200) COLLATE "default",
	"parent_id" int4,
	"sort" int4,
	"level" int4,
	"status" int4,
	"type" int4,
	"remark" varchar(200) COLLATE "default",
	"module_path" varchar(100) COLLATE "default",
	"icon" varchar(200) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."admin_menu" OWNER TO "postgres";

COMMENT ON TABLE "public"."admin_menu" IS '总控后台功能菜单栏';
COMMENT ON COLUMN "public"."admin_menu"."id" IS '主键ID';
COMMENT ON COLUMN "public"."admin_menu"."name" IS '菜单名';
COMMENT ON COLUMN "public"."admin_menu"."url" IS '功能路径';
COMMENT ON COLUMN "public"."admin_menu"."parent_id" IS '上一级id';
COMMENT ON COLUMN "public"."admin_menu"."sort" IS '菜单排序顺序 从小到大';
COMMENT ON COLUMN "public"."admin_menu"."level" IS '菜单级别';
COMMENT ON COLUMN "public"."admin_menu"."status" IS '菜单栏状态 1：禁用 2：启用  3：隐藏（URL可访问）';
COMMENT ON COLUMN "public"."admin_menu"."type" IS '菜单类别  1：无任何功能  2：页面   3：功能';
COMMENT ON COLUMN "public"."admin_menu"."remark" IS '备注';
COMMENT ON COLUMN "public"."admin_menu"."module_path" IS '模块路径,用于权限检验';
COMMENT ON COLUMN "public"."admin_menu"."icon" IS '图标';

-- ----------------------------
--  Records of admin_menu
-- ----------------------------
BEGIN;
INSERT INTO "public"."admin_menu" VALUES ('17', '权限设置', '/admin/permission/index.do', '1', '6', '2', '2', null, '', '/admin/permission', '');
INSERT INTO "public"."admin_menu" VALUES ('5', '菜单栏', '/admin/menu/index.do', '1', '2', '2', '2', '2', null, '/admin/menu', null);
INSERT INTO "public"."admin_menu" VALUES ('16', '站点管理', '/admin/station/index.do', '15', '1', '2', '2', '2', null, '/admin/station', null);
INSERT INTO "public"."admin_menu" VALUES ('18', '域名管理', '/admin/domain/index.do', '15', '2', '2', '2', null, '', '/admin/domain', '');
INSERT INTO "public"."admin_menu" VALUES ('20', '公告管理', '/admin/announcement/index.do', '15', '4', '2', '2', null, '', '/admin/announcement', '');
INSERT INTO "public"."admin_menu" VALUES ('7', '用户管理', '/admin/user/index.do', '1', '4', '2', '2', '2', null, '/admin/user', null);
INSERT INTO "public"."admin_menu" VALUES ('21', '投诉建议', '/admin/proposal/index.do', '15', '5', '2', '2', null, '', '/admin/proposal', '');
INSERT INTO "public"."admin_menu" VALUES ('14', '编辑数据', '/admin/cache/update.do', '4', '3', '3', '3', null, '', null, '');
INSERT INTO "public"."admin_menu" VALUES ('19', '账号管理', '/admin/account/index.do', '15', '3', '2', '2', null, '', '/admin/account', '');
INSERT INTO "public"."admin_menu" VALUES ('22', '代理菜单', '/admin/agentmenu/index.do', '15', '1', '2', '2', null, '', '/admin/agentmenu', '');
INSERT INTO "public"."admin_menu" VALUES ('23', '代理权限', '/admin/agentpermission/index.do', '15', '1', '2', '2', null, '', '/admin/agentpermission', '');
INSERT INTO "public"."admin_menu" VALUES ('24', '三方平台', '/admin/thirdplat/index.do', '15', '1', '2', '2', null, '', '/admin/thirdplat', '');
INSERT INTO "public"."admin_menu" VALUES ('25', '彩种管理', '/admin/lottery/index.do', '3', '1', '2', '2', null, '', '/admin/lottery', '');
INSERT INTO "public"."admin_menu" VALUES ('4', '缓存管理', '/admin/cache/index.do', '1', '1', '2', '2', '2', '', '/admin/cache', '');
INSERT INTO "public"."admin_menu" VALUES ('26', '玩法大类管理', '/admin/lotTypeGroup/index.do', '3', '6', '2', '2', null, '', '/admin/lotTypeGroup', '');
INSERT INTO "public"."admin_menu" VALUES ('27', '玩法小类管理', '/admin/lotType/index.do', '3', '10', '2', '2', null, '', '/admin/lotType', '');
INSERT INTO "public"."admin_menu" VALUES ('12', '新增数据', '/admin/cache/add.do', '4', '1', '3', '2', null, '', null, '');
INSERT INTO "public"."admin_menu" VALUES ('13', '删除数据', '/admin/cache/delete.do', '4', '2', '3', '1', null, '', null, '');
INSERT INTO "public"."admin_menu" VALUES ('29', '开奖结果管理', '/admin/lotteryData/index.do', '3', '15', '2', '2', null, '', '/admin/lotteryData', '');
INSERT INTO "public"."admin_menu" VALUES ('6', '用户组别', '/admin/group/index.do', '1', '5', '2', '2', '2', '', '/admin/group', '');
INSERT INTO "public"."admin_menu" VALUES ('30', '站点彩种管理', '/admin/lotStationManage/index.do', '3', '20', '2', '2', null, '', '/admin/lotStationManage', '');
INSERT INTO "public"."admin_menu" VALUES ('1', '系统管理', null, '0', '1', '1', '2', null, '备注事项', null, 'glyphicon glyphicon-cog');
INSERT INTO "public"."admin_menu" VALUES ('3', '彩票管理', null, '0', '4', '1', '2', null, '000', null, 'glyphicon glyphicon-fire');
INSERT INTO "public"."admin_menu" VALUES ('15', '平台管理', null, '0', '2', '1', '2', '1', null, null, 'glyphicon glyphicon-hdd');
INSERT INTO "public"."admin_menu" VALUES ('2', '体育管理', null, '0', '3', '1', '2', null, '测试', null, 'glyphicon glyphicon-knight');
INSERT INTO "public"."admin_menu" VALUES ('28', '开奖时间管理', '/admin/lotteryTime/index.do', '3', '3', '2', '2', null, '', '/admin/lotteryTime', '');
COMMIT;

-- ----------------------------
--  Table structure for sys_cache
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_cache";
CREATE TABLE "public"."sys_cache" (
	"id" int4 NOT NULL DEFAULT nextval('sys_cache_id_seq'::regclass),
	"name" varchar(100) COLLATE "default",
	"key" varchar(200) COLLATE "default",
	"expression" varchar(200) COLLATE "default",
	"remark" varchar COLLATE "default",
	"data_type" varchar(100) COLLATE "default",
	"db" int4,
	"timeout" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."sys_cache" OWNER TO "postgres";

COMMENT ON TABLE "public"."sys_cache" IS '系统缓存表';
COMMENT ON COLUMN "public"."sys_cache"."id" IS '主键';
COMMENT ON COLUMN "public"."sys_cache"."name" IS '缓存名称';
COMMENT ON COLUMN "public"."sys_cache"."key" IS '缓存键值';
COMMENT ON COLUMN "public"."sys_cache"."expression" IS '键值表达式';
COMMENT ON COLUMN "public"."sys_cache"."remark" IS '缓存描述说明';
COMMENT ON COLUMN "public"."sys_cache"."data_type" IS '缓存的数据类型';
COMMENT ON COLUMN "public"."sys_cache"."db" IS '数据库索引';
COMMENT ON COLUMN "public"."sys_cache"."timeout" IS '缓存过期时间 单位（秒）';

-- ----------------------------
--  Records of sys_cache
-- ----------------------------
BEGIN;
INSERT INTO "public"."sys_cache" VALUES ('5', '盘口站点域名', 'STATION_DOMAIN', '{key}_{domain}', 'domain：站点域名', 'com.game.model.SysStation', '1', '1800');
INSERT INTO "public"."sys_cache" VALUES ('3', '总控后台用户组权限', 'ADMIN_GROUP_PERMISSION_MAP', '{key}_{groupId}', 'groupId: admin_group主键', 'Map<String,Boolean>', '3', '1800');
INSERT INTO "public"."sys_cache" VALUES ('2', '总控后台用户组菜单', 'ADMIN_GROUP_MENU', '{key}_{groupId}', 'groupId: admin_group主键', 'com.game.model.vo.AdminMenuNode', '1', '1800');
INSERT INTO "public"."sys_cache" VALUES ('6', '代理后台用户组菜单', 'AGENT_MENU', '{key}_{userId}', '', 'com.game.model.vo.AgentMenu', '1', '1800');
COMMIT;

-- ----------------------------
--  Table structure for sys_station
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_station";
CREATE TABLE "public"."sys_station" (
	"id" int4 NOT NULL DEFAULT nextval('sys_station_id_seq'::regclass),
	"name" varchar(100) COLLATE "default",
	"status" int4,
	"floder" varchar(50) COLLATE "default",
	"create_datetime" timestamp(6) NULL,
	"close_time" timestamp(6) NULL,
	"open_time" timestamp(6) NULL,
	"account_id" int4 NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."sys_station" OWNER TO "postgres";

COMMENT ON COLUMN "public"."sys_station"."name" IS '站点名称';
COMMENT ON COLUMN "public"."sys_station"."status" IS '站点状态  1-关闭  2-开启';
COMMENT ON COLUMN "public"."sys_station"."floder" IS '站点目录名';
COMMENT ON COLUMN "public"."sys_station"."create_datetime" IS '站点创建时间';
COMMENT ON COLUMN "public"."sys_station"."close_time" IS '最近一次关闭时间';
COMMENT ON COLUMN "public"."sys_station"."open_time" IS '最近一次开启时间';

-- ----------------------------
--  Records of sys_station
-- ----------------------------
BEGIN;
INSERT INTO "public"."sys_station" VALUES ('1', '新葡京赌场', '2', 'a001', '2016-03-18 00:00:00', null, null, '2');
COMMIT;

-- ----------------------------
--  Table structure for sys_announcement
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_announcement";
CREATE TABLE "public"."sys_announcement" (
	"id" int4 NOT NULL DEFAULT nextval('sys_announcement_id_seq'::regclass),
	"name" varchar(50) COLLATE "default",
	"content" text COLLATE "default",
	"create_user_id" int4,
	"status" int4,
	"create_datetime" timestamp(6) NULL,
	"modify_datetime" timestamp(6) NULL,
	"modify_user_id" int4,
	"type" int4,
	"begin_datetime" timestamp(6) NULL,
	"end_datetime" timestamp(6) NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."sys_announcement" OWNER TO "postgres";

COMMENT ON COLUMN "public"."sys_announcement"."name" IS '公告标题';
COMMENT ON COLUMN "public"."sys_announcement"."content" IS '内容';
COMMENT ON COLUMN "public"."sys_announcement"."create_user_id" IS '创建人';
COMMENT ON COLUMN "public"."sys_announcement"."status" IS '状态';
COMMENT ON COLUMN "public"."sys_announcement"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."sys_announcement"."modify_datetime" IS '修改时间';
COMMENT ON COLUMN "public"."sys_announcement"."modify_user_id" IS '修改者';
COMMENT ON COLUMN "public"."sys_announcement"."type" IS '公告类型';
COMMENT ON COLUMN "public"."sys_announcement"."begin_datetime" IS '开始时间';
COMMENT ON COLUMN "public"."sys_announcement"."end_datetime" IS '结束时间';

-- ----------------------------
--  Records of sys_announcement
-- ----------------------------
BEGIN;
INSERT INTO "public"."sys_announcement" VALUES ('1', 'test', 'test', '1', '1', '2016-04-11 11:10:04.638', null, '1', '1', '2016-04-10 11:32:58', '2016-04-18 11:33:01');
INSERT INTO "public"."sys_announcement" VALUES ('2', '测试', '', '1', '2', '2016-04-11 11:36:51.766', null, '1', '2', '2016-04-11 11:36:38', '2016-04-12 11:36:40');
COMMIT;

-- ----------------------------
--  Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_account";
CREATE TABLE "public"."sys_account" (
	"id" int4 NOT NULL DEFAULT nextval('sys_member_id_seq'::regclass),
	"account" varchar(50) COLLATE "default",
	"password" varchar(50) COLLATE "default",
	"account_type" int4,
	"create_datetime" timestamp(6) NULL,
	"modify_datetime" timestamp(6) NULL,
	"create_user_id" int4,
	"modify_user_id" int4,
	"flag_active" int4,
	"account_status" int4,
	"agent_id" int4 NOT NULL DEFAULT 0,
	"station_id" int4 NOT NULL,
	"agent_name" varchar(50) COLLATE "default",
	"level" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."sys_account" OWNER TO "postgres";

COMMENT ON TABLE "public"."sys_account" IS '存储会员、代理、总代理、股东、大大股东 账号信息';
COMMENT ON COLUMN "public"."sys_account"."account" IS '账号';
COMMENT ON COLUMN "public"."sys_account"."password" IS '密码';
COMMENT ON COLUMN "public"."sys_account"."account_type" IS '当前账号类型 ： 1大大股东 2大股东 3股东  4总代理 5代理 6会员';
COMMENT ON COLUMN "public"."sys_account"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."sys_account"."modify_datetime" IS '修改时间';
COMMENT ON COLUMN "public"."sys_account"."create_user_id" IS '创建者';
COMMENT ON COLUMN "public"."sys_account"."modify_user_id" IS '修改者';
COMMENT ON COLUMN "public"."sys_account"."flag_active" IS '数据状态 0：逻辑删除 1:正常';
COMMENT ON COLUMN "public"."sys_account"."account_status" IS '账号状态 1:禁用 2:启用';
COMMENT ON COLUMN "public"."sys_account"."agent_id" IS '上级ID';
COMMENT ON COLUMN "public"."sys_account"."station_id" IS '站点ID';
COMMENT ON COLUMN "public"."sys_account"."agent_name" IS '上级账号';
COMMENT ON COLUMN "public"."sys_account"."level" IS '层级';

-- ----------------------------
--  Records of sys_account
-- ----------------------------
BEGIN;
INSERT INTO "public"."sys_account" VALUES ('3', 'jay', null, '1', '2016-04-07 15:57:45.681', '2016-05-04 19:08:57.777', '1', '2', '1', '2', '4', '1', 'king', null);
INSERT INTO "public"."sys_account" VALUES ('2', 'lex1', 'C3DE88597AC1C653508D046C49A975EB', '2', '2016-04-07 15:53:39.812', '2016-07-03 13:34:48.783', '1', '1', '1', '2', '0', '1', 'lex1', null);
COMMIT;

-- ----------------------------
--  Table structure for mny_money_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."mny_money_type";
CREATE TABLE "public"."mny_money_type" (
	"id" int4 NOT NULL DEFAULT nextval('mny_money_type_id_seq'::regclass),
	"name" varchar(20) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."mny_money_type" OWNER TO "postgres";

COMMENT ON COLUMN "public"."mny_money_type"."name" IS '币种';

-- ----------------------------
--  Records of mny_money_type
-- ----------------------------
BEGIN;
INSERT INTO "public"."mny_money_type" VALUES ('1', '人民币');
COMMIT;

-- ----------------------------
--  Table structure for mny_com_record
-- ----------------------------
DROP TABLE IF EXISTS "public"."mny_com_record";
CREATE TABLE "public"."mny_com_record" (
	"id" int4 NOT NULL DEFAULT nextval('mny_com_record_id_seq'::regclass),
	"order_no" varchar(20) COLLATE "default",
	"member_id" int4,
	"money" numeric,
	"create_datetime" timestamp(6) NULL,
	"create_user_id" int4,
	"modify_datetime" timestamp(6) NULL,
	"modify_user_id" int4,
	"status" int4,
	"station_id" int4,
	"type" int4,
	"remark" varchar(200) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."mny_com_record" OWNER TO "postgres";

COMMENT ON COLUMN "public"."mny_com_record"."order_no" IS '订单号';
COMMENT ON COLUMN "public"."mny_com_record"."member_id" IS '会员ID';
COMMENT ON COLUMN "public"."mny_com_record"."money" IS '存入金额';
COMMENT ON COLUMN "public"."mny_com_record"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."mny_com_record"."create_user_id" IS '创建人';
COMMENT ON COLUMN "public"."mny_com_record"."modify_datetime" IS '修改时间';
COMMENT ON COLUMN "public"."mny_com_record"."modify_user_id" IS '修改人';
COMMENT ON COLUMN "public"."mny_com_record"."status" IS '状态';
COMMENT ON COLUMN "public"."mny_com_record"."station_id" IS '站点ID';
COMMENT ON COLUMN "public"."mny_com_record"."type" IS '记录类型';
COMMENT ON COLUMN "public"."mny_com_record"."remark" IS '备注';

-- ----------------------------
--  Records of mny_com_record
-- ----------------------------
BEGIN;
INSERT INTO "public"."mny_com_record" VALUES ('1', '1123123', '2', '1000', '2016-04-06 09:48:39.479', '1', '2016-04-06 09:48:39.479', '1', '1', '2', null, null);
COMMIT;

-- ----------------------------
--  Table structure for sys_proposal
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_proposal";
CREATE TABLE "public"."sys_proposal" (
	"id" int4 NOT NULL DEFAULT nextval('sys_proposal_id_seq'::regclass),
	"name" varchar(50) COLLATE "default",
	"content" text COLLATE "default",
	"create_user_id" int4 NOT NULL,
	"station_id" int4 NOT NULL,
	"qq" varchar(12) COLLATE "default",
	"phone" varchar(20) COLLATE "default",
	"create_datetime" timestamp(6) NULL,
	"status" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."sys_proposal" OWNER TO "postgres";

COMMENT ON COLUMN "public"."sys_proposal"."name" IS '标题';
COMMENT ON COLUMN "public"."sys_proposal"."content" IS '内容';
COMMENT ON COLUMN "public"."sys_proposal"."create_user_id" IS '投诉建议人';
COMMENT ON COLUMN "public"."sys_proposal"."station_id" IS '站点';
COMMENT ON COLUMN "public"."sys_proposal"."qq" IS 'qq';
COMMENT ON COLUMN "public"."sys_proposal"."phone" IS '手机';
COMMENT ON COLUMN "public"."sys_proposal"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."sys_proposal"."status" IS '状态';

-- ----------------------------
--  Records of sys_proposal
-- ----------------------------
BEGIN;
INSERT INTO "public"."sys_proposal" VALUES ('1', 'test', 'dslkfjalskfdjalksjdl  ', '2', '1', '20938092', '13333303990', '2016-04-11 17:26:26', '2');
COMMIT;

-- ----------------------------
--  Table structure for mny_draw_record
-- ----------------------------
DROP TABLE IF EXISTS "public"."mny_draw_record";
CREATE TABLE "public"."mny_draw_record" (
	"id" int4 NOT NULL DEFAULT nextval('mny_draw_record_id_seq'::regclass),
	"member_id" int4 NOT NULL,
	"draw_money" numeric NOT NULL,
	"true_money" numeric NOT NULL,
	"fee" numeric,
	"status" int4,
	"type" int4,
	"station_id" int4,
	"create_user_id" int4,
	"create_datetime" timestamp(6) NULL,
	"modify_user_id" int4,
	"modify_datetime" timestamp(6) NULL,
	"order_no" varchar(20) COLLATE "default",
	"remark" varchar(200) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."mny_draw_record" OWNER TO "postgres";

COMMENT ON COLUMN "public"."mny_draw_record"."member_id" IS '会员ID';
COMMENT ON COLUMN "public"."mny_draw_record"."draw_money" IS '出款金额';
COMMENT ON COLUMN "public"."mny_draw_record"."true_money" IS '实际出款金额';
COMMENT ON COLUMN "public"."mny_draw_record"."fee" IS '手续费';
COMMENT ON COLUMN "public"."mny_draw_record"."status" IS '状态';
COMMENT ON COLUMN "public"."mny_draw_record"."type" IS '出款类型';
COMMENT ON COLUMN "public"."mny_draw_record"."station_id" IS '站点ID';
COMMENT ON COLUMN "public"."mny_draw_record"."create_user_id" IS '创建者';
COMMENT ON COLUMN "public"."mny_draw_record"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."mny_draw_record"."modify_user_id" IS '修改者';
COMMENT ON COLUMN "public"."mny_draw_record"."modify_datetime" IS '修改时间';
COMMENT ON COLUMN "public"."mny_draw_record"."order_no" IS '订单号';
COMMENT ON COLUMN "public"."mny_draw_record"."remark" IS '备注';

-- ----------------------------
--  Table structure for sys_register_config
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_register_config";
CREATE TABLE "public"."sys_register_config" (
	"id" int4 NOT NULL,
	"name" varchar(20) COLLATE "default",
	"show" int4 NOT NULL DEFAULT 1,
	"validate" int4 NOT NULL DEFAULT 1,
	"required" int4 NOT NULL DEFAULT 1,
	"status_validate" int4 NOT NULL DEFAULT 1,
	"station_id" int4 NOT NULL,
	"type" int4 NOT NULL,
	"platform" int4 NOT NULL DEFAULT 1,
	"create_user_id" int4,
	"create_datetime" timestamp(6) NULL,
	"modify_user_id" int4,
	"modify_datetime" timestamp(6) NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."sys_register_config" OWNER TO "postgres";

COMMENT ON COLUMN "public"."sys_register_config"."name" IS '字段名称';
COMMENT ON COLUMN "public"."sys_register_config"."show" IS '是否展示（1否2是）';
COMMENT ON COLUMN "public"."sys_register_config"."validate" IS '是否需要验证（1否2是）';
COMMENT ON COLUMN "public"."sys_register_config"."required" IS '是否必填（1否2是）';
COMMENT ON COLUMN "public"."sys_register_config"."status_validate" IS '状态验证（1否2是）';
COMMENT ON COLUMN "public"."sys_register_config"."station_id" IS '站点ID';
COMMENT ON COLUMN "public"."sys_register_config"."type" IS '字段类型';
COMMENT ON COLUMN "public"."sys_register_config"."platform" IS '作用平台（1会员、2代理）';
COMMENT ON COLUMN "public"."sys_register_config"."create_user_id" IS '创建者ID';
COMMENT ON COLUMN "public"."sys_register_config"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."sys_register_config"."modify_user_id" IS '最后修改者ID';
COMMENT ON COLUMN "public"."sys_register_config"."modify_datetime" IS '最后修改时间';

-- ----------------------------
--  Table structure for sys_fee_config
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_fee_config";
CREATE TABLE "public"."sys_fee_config" (
	"id" int4 NOT NULL,
	"money_type" int4 NOT NULL,
	"station_id" int4 NOT NULL,
	"deposit_fee" numeric,
	"max_deposit_fee" numeric,
	"draw_fee" numeric,
	"max_draw_fee" numeric,
	"effective_datetime" timestamp(6) NULL,
	"create_user_id" int4,
	"create_datetime" timestamp(6) NULL,
	"modify_user_id" int4,
	"modify_datetime" timestamp(6) NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."sys_fee_config" OWNER TO "postgres";

COMMENT ON COLUMN "public"."sys_fee_config"."money_type" IS '币种';
COMMENT ON COLUMN "public"."sys_fee_config"."station_id" IS '站点ID';
COMMENT ON COLUMN "public"."sys_fee_config"."deposit_fee" IS '入款手续费';
COMMENT ON COLUMN "public"."sys_fee_config"."max_deposit_fee" IS '最大入款手续';
COMMENT ON COLUMN "public"."sys_fee_config"."draw_fee" IS '出款手续费';
COMMENT ON COLUMN "public"."sys_fee_config"."max_draw_fee" IS '最大出款手续费';
COMMENT ON COLUMN "public"."sys_fee_config"."effective_datetime" IS '生效日期';
COMMENT ON COLUMN "public"."sys_fee_config"."create_user_id" IS '创建者';
COMMENT ON COLUMN "public"."sys_fee_config"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."sys_fee_config"."modify_user_id" IS '修改者';
COMMENT ON COLUMN "public"."sys_fee_config"."modify_datetime" IS '修改时间';

-- ----------------------------
--  Table structure for agent_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."agent_menu";
CREATE TABLE "public"."agent_menu" (
	"id" int4 NOT NULL DEFAULT nextval('agent_menu_id_seq'::regclass),
	"name" varchar(50) COLLATE "default",
	"url" varchar(200) COLLATE "default",
	"parent_id" int4,
	"sort" int4,
	"level" int4,
	"status" int4,
	"type" int4,
	"remark" varchar(200) COLLATE "default",
	"module_path" varchar(100) COLLATE "default",
	"icon" varchar(200) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."agent_menu" OWNER TO "postgres";

COMMENT ON TABLE "public"."agent_menu" IS '代理后台功能菜单栏';
COMMENT ON COLUMN "public"."agent_menu"."id" IS '主键ID';
COMMENT ON COLUMN "public"."agent_menu"."name" IS '菜单名';
COMMENT ON COLUMN "public"."agent_menu"."url" IS '功能路径';
COMMENT ON COLUMN "public"."agent_menu"."parent_id" IS '上一级id';
COMMENT ON COLUMN "public"."agent_menu"."sort" IS '菜单排序顺序 从小到大';
COMMENT ON COLUMN "public"."agent_menu"."level" IS '菜单级别';
COMMENT ON COLUMN "public"."agent_menu"."status" IS '菜单栏状态 1：禁用 2：启用  3：隐藏（URL可访问）';
COMMENT ON COLUMN "public"."agent_menu"."type" IS '菜单类别  1：无任何功能  2：页面   3：功能';
COMMENT ON COLUMN "public"."agent_menu"."remark" IS '备注';
COMMENT ON COLUMN "public"."agent_menu"."module_path" IS '模块路径,用于权限检验';
COMMENT ON COLUMN "public"."agent_menu"."icon" IS '图标';

-- ----------------------------
--  Records of agent_menu
-- ----------------------------
BEGIN;
INSERT INTO "public"."agent_menu" VALUES ('10', '第三方平台管理', '/agent/system/thirdplat/index.do', '1', '1', '2', '2', null, '', '/agent/system/thirdplat', '');
INSERT INTO "public"."agent_menu" VALUES ('11', '全部会员管理', '/agent/member/manager/index.do', '3', '1', '2', '2', null, '', '/agent/member/manager', '');
INSERT INTO "public"."agent_menu" VALUES ('9', '会员账变记录', '/agent/finance/memmnyrd/index.do', '2', '4', '2', '2', null, '', '/agent/finance/memmnyrd', '');
INSERT INTO "public"."agent_menu" VALUES ('1', '系统', null, '0', '1', '1', '2', null, '', null, 'glyphicon glyphicon-cog');
INSERT INTO "public"."agent_menu" VALUES ('2', '财务', null, '0', '2', '1', '2', null, '', null, 'glyphicon glyphicon-usd');
INSERT INTO "public"."agent_menu" VALUES ('3', '会员', null, '0', '3', '1', '2', null, '', null, 'glyphicon glyphicon-user');
INSERT INTO "public"."agent_menu" VALUES ('5', '体育', null, '0', '5', '1', '2', null, '', null, 'glyphicon glyphicon-knight');
INSERT INTO "public"."agent_menu" VALUES ('6', '彩票', null, '0', '6', '1', '2', null, '', null, 'glyphicon glyphicon-fire');
INSERT INTO "public"."agent_menu" VALUES ('7', '真人', null, '0', '7', '1', '2', null, '', null, 'glyphicon glyphicon-facetime-video');
INSERT INTO "public"."agent_menu" VALUES ('8', '报表', null, '0', '8', '1', '2', null, '', null, 'glyphicon glyphicon-stats');
INSERT INTO "public"."agent_menu" VALUES ('4', '代理', null, '0', '4', '1', '2', null, '', null, 'glyphicon glyphicon-sunglasses');
INSERT INTO "public"."agent_menu" VALUES ('12', '全局报表', '/agent/report/global/index.do', '8', '1', '2', '2', null, '', '/agent/report/global', '');
INSERT INTO "public"."agent_menu" VALUES ('13', '网站基本设定', '/agent/system/baseconfig/index.do', '1', '1', '2', '2', null, '', '/agent/system/baseconfig', '');
INSERT INTO "public"."agent_menu" VALUES ('14', '会员搜索功能', '/agent/member/search/index.do', '3', '4', '2', '2', null, '', '/agent/member/search', '');
INSERT INTO "public"."agent_menu" VALUES ('15', '会员加款扣款', '/agent/finance/memmnyope/index.do', '2', '4', '2', '2', null, '', '/agent/finance/memmnyope', '');
INSERT INTO "public"."agent_menu" VALUES ('16', '代理管理', '/agent/agent/manager/index.do', '4', '1', '2', '2', null, '', '/agent/agent/manager', '');
COMMIT;

-- ----------------------------
--  Table structure for agent_menu_group
-- ----------------------------
DROP TABLE IF EXISTS "public"."agent_menu_group";
CREATE TABLE "public"."agent_menu_group" (
	"id" int4 NOT NULL DEFAULT nextval('agent_menu_group_id_seq'::regclass),
	"agent_id" int4,
	"menu_id" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."agent_menu_group" OWNER TO "postgres";

COMMENT ON COLUMN "public"."agent_menu_group"."id" IS '主键';
COMMENT ON COLUMN "public"."agent_menu_group"."agent_id" IS '关联sys_account表 id';
COMMENT ON COLUMN "public"."agent_menu_group"."menu_id" IS '关联agent_menu表id';

-- ----------------------------
--  Records of agent_menu_group
-- ----------------------------
BEGIN;
INSERT INTO "public"."agent_menu_group" VALUES ('79', '2', '1');
INSERT INTO "public"."agent_menu_group" VALUES ('80', '2', '10');
INSERT INTO "public"."agent_menu_group" VALUES ('81', '2', '13');
INSERT INTO "public"."agent_menu_group" VALUES ('82', '2', '2');
INSERT INTO "public"."agent_menu_group" VALUES ('83', '2', '9');
INSERT INTO "public"."agent_menu_group" VALUES ('84', '2', '15');
INSERT INTO "public"."agent_menu_group" VALUES ('85', '2', '3');
INSERT INTO "public"."agent_menu_group" VALUES ('86', '2', '11');
INSERT INTO "public"."agent_menu_group" VALUES ('87', '2', '14');
INSERT INTO "public"."agent_menu_group" VALUES ('88', '2', '4');
INSERT INTO "public"."agent_menu_group" VALUES ('89', '2', '16');
INSERT INTO "public"."agent_menu_group" VALUES ('90', '2', '5');
INSERT INTO "public"."agent_menu_group" VALUES ('91', '2', '6');
INSERT INTO "public"."agent_menu_group" VALUES ('92', '2', '7');
INSERT INTO "public"."agent_menu_group" VALUES ('93', '2', '8');
INSERT INTO "public"."agent_menu_group" VALUES ('94', '2', '12');
COMMIT;

-- ----------------------------
--  Table structure for sys_account_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_account_info";
CREATE TABLE "public"."sys_account_info" (
	"account_id" int4 NOT NULL,
	"bank_id" int4,
	"user_name" varchar(50) COLLATE "default",
	"card_no" varchar(50) COLLATE "default",
	"receipt_pwd" varchar(50) COLLATE "default",
	"phone" varchar(50) COLLATE "default",
	"province" varchar(50) COLLATE "default",
	"city" varchar(50) COLLATE "default",
	"email" varchar(50) COLLATE "default",
	"bank_address" varchar(100) COLLATE "default",
	"draw_user" varchar(20) COLLATE "default",
	"qq" varchar(15) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."sys_account_info" OWNER TO "postgres";

COMMENT ON COLUMN "public"."sys_account_info"."phone" IS '手机';
COMMENT ON COLUMN "public"."sys_account_info"."province" IS '省份';
COMMENT ON COLUMN "public"."sys_account_info"."city" IS '城市';
COMMENT ON COLUMN "public"."sys_account_info"."email" IS '电子邮箱';
COMMENT ON COLUMN "public"."sys_account_info"."bank_address" IS '银行地址';
COMMENT ON COLUMN "public"."sys_account_info"."draw_user" IS '取现人';
COMMENT ON COLUMN "public"."sys_account_info"."qq" IS 'QQ号码';

-- ----------------------------
--  Records of sys_account_info
-- ----------------------------
BEGIN;
INSERT INTO "public"."sys_account_info" VALUES ('2', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO "public"."sys_account_info" VALUES ('3', '1', '老司机', '110', null, '110aa', null, null, 'gmail', '厦门', '新司机', '339');
COMMIT;

-- ----------------------------
--  Table structure for sys_third_platform
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_third_platform";
CREATE TABLE "public"."sys_third_platform" (
	"id" int4 NOT NULL DEFAULT nextval('sys_third_platform_id_seq'::regclass),
	"name" varchar(50) COLLATE "default",
	"status" int4,
	"remark" varchar(200) COLLATE "default",
	"create_datetime" timestamp(6) NULL,
	"modify_datetime" timestamp(6) NULL,
	"create_user_id" int4,
	"modify_user_id" int4,
	"flag_active" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."sys_third_platform" OWNER TO "postgres";

COMMENT ON COLUMN "public"."sys_third_platform"."name" IS '平台名称';
COMMENT ON COLUMN "public"."sys_third_platform"."status" IS '状态';
COMMENT ON COLUMN "public"."sys_third_platform"."remark" IS '备注';
COMMENT ON COLUMN "public"."sys_third_platform"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."sys_third_platform"."modify_datetime" IS '修改时间';
COMMENT ON COLUMN "public"."sys_third_platform"."create_user_id" IS '创建者';
COMMENT ON COLUMN "public"."sys_third_platform"."modify_user_id" IS '修改者';
COMMENT ON COLUMN "public"."sys_third_platform"."flag_active" IS '数据状态 0：逻辑删除 1:正常';

-- ----------------------------
--  Records of sys_third_platform
-- ----------------------------
BEGIN;
INSERT INTO "public"."sys_third_platform" VALUES ('2', 'BBIN平台', '2', '111', '2016-04-25 15:22:37.814', '2016-04-25 15:22:37.814', '1', '1', '1');
INSERT INTO "public"."sys_third_platform" VALUES ('3', 'MG平台', '2', 'a', '2016-04-25 15:36:29.517', '2016-04-25 15:36:29.517', '1', '1', '1');
INSERT INTO "public"."sys_third_platform" VALUES ('4', 'OG平台', '2', '111', '2016-04-25 15:37:51.034', '2016-04-25 15:37:51.034', '1', '1', '1');
INSERT INTO "public"."sys_third_platform" VALUES ('5', 'OPUS平台', '2', 'asd', '2016-04-25 16:17:25.976', '2016-04-25 16:17:25.976', '1', '1', '1');
INSERT INTO "public"."sys_third_platform" VALUES ('1', 'AG平台', '2', 'AG平台', '2016-04-25 16:17:05.118', '2016-04-25 22:06:22.61', '1', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for mny_money_record
-- ----------------------------
DROP TABLE IF EXISTS "public"."mny_money_record";
CREATE TABLE "public"."mny_money_record" (
	"id" int4 NOT NULL DEFAULT nextval('mny_record_id_seq'::regclass),
	"account_id" int4,
	"before_money" numeric,
	"after_money" numeric,
	"money" numeric,
	"type" int4,
	"remark" varchar(200) COLLATE "default",
	"create_datetime" timestamp(6) NULL,
	"modify_datetime" timestamp(6) NULL,
	"create_user_id" int4,
	"modify_user_id" int4,
	"flag_active" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."mny_money_record" OWNER TO "postgres";

COMMENT ON COLUMN "public"."mny_money_record"."account_id" IS '用户ID';
COMMENT ON COLUMN "public"."mny_money_record"."before_money" IS '改变前的余额';
COMMENT ON COLUMN "public"."mny_money_record"."after_money" IS '改变后的余额';
COMMENT ON COLUMN "public"."mny_money_record"."money" IS '本次变动金额';
COMMENT ON COLUMN "public"."mny_money_record"."type" IS '记录来源类型';
COMMENT ON COLUMN "public"."mny_money_record"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."mny_money_record"."modify_datetime" IS '修改时间';
COMMENT ON COLUMN "public"."mny_money_record"."create_user_id" IS '创建者';
COMMENT ON COLUMN "public"."mny_money_record"."modify_user_id" IS '修改者';
COMMENT ON COLUMN "public"."mny_money_record"."flag_active" IS '数据状态 0：逻辑删除 1:正常';

-- ----------------------------
--  Table structure for bc_lottery_time
-- ----------------------------
DROP TABLE IF EXISTS "public"."bc_lottery_time";
CREATE TABLE "public"."bc_lottery_time" (
	"id" int4 NOT NULL DEFAULT nextval('bc_lottery_time_id_seq'::regclass),
	"lot_code" varchar(20) COLLATE "default",
	"action_no" int2,
	"action_time" time(6)
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."bc_lottery_time" OWNER TO "postgres";

COMMENT ON TABLE "public"."bc_lottery_time" IS '开奖时间模版，整个系统就一套开奖时间';
COMMENT ON COLUMN "public"."bc_lottery_time"."lot_code" IS '彩票编码';
COMMENT ON COLUMN "public"."bc_lottery_time"."action_no" IS '期号，每天的期号';
COMMENT ON COLUMN "public"."bc_lottery_time"."action_time" IS '开奖时间';

-- ----------------------------
--  Table structure for third_station_group
-- ----------------------------
DROP TABLE IF EXISTS "public"."third_station_group";
CREATE TABLE "public"."third_station_group" (
	"id" int4 NOT NULL DEFAULT nextval('third_station_group_id_seq'::regclass),
	"third_platform_id" int4 NOT NULL,
	"station_id" int4 NOT NULL,
	"order_no" int4 DEFAULT 1,
	"name" varchar(50) COLLATE "default",
	"status" int4,
	"remark" varchar COLLATE "default",
	"create_datetime" timestamp(6) NULL,
	"modify_datetime" timestamp(6) NULL,
	"create_user_id" int4,
	"modify_user_id" int4,
	"flag_active" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."third_station_group" OWNER TO "postgres";

COMMENT ON COLUMN "public"."third_station_group"."third_platform_id" IS '第三方平台ID';
COMMENT ON COLUMN "public"."third_station_group"."station_id" IS '站点ID';
COMMENT ON COLUMN "public"."third_station_group"."order_no" IS '排序码';
COMMENT ON COLUMN "public"."third_station_group"."name" IS '自定名称';
COMMENT ON COLUMN "public"."third_station_group"."status" IS '启用状态';
COMMENT ON COLUMN "public"."third_station_group"."remark" IS '本平台注释';
COMMENT ON COLUMN "public"."third_station_group"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."third_station_group"."modify_datetime" IS '修改时间';
COMMENT ON COLUMN "public"."third_station_group"."create_user_id" IS '创建者';
COMMENT ON COLUMN "public"."third_station_group"."modify_user_id" IS '修改者';
COMMENT ON COLUMN "public"."third_station_group"."flag_active" IS '数据状态 0：逻辑删除 1:正常';

-- ----------------------------
--  Records of third_station_group
-- ----------------------------
BEGIN;
INSERT INTO "public"."third_station_group" VALUES ('4', '4', '1', '12', 'OG娱乐厅', '1', '123', '2016-04-26 21:53:35.327', '2016-05-04 20:57:13.06', '2', '2', '1');
INSERT INTO "public"."third_station_group" VALUES ('6', '1', '1', '3', 'AG娱乐厅', '1', '', '2016-04-26 22:04:37.499', '2016-05-05 14:53:00.851', '2', '2', '1');
INSERT INTO "public"."third_station_group" VALUES ('1', '2', '1', '1', 'BBIN娱乐厅', '1', '12312', '2016-04-26 21:41:29.899', '2016-05-05 14:53:03.597', '2', '2', '1');
INSERT INTO "public"."third_station_group" VALUES ('7', '3', '1', '2', 'MG娱乐厅', '2', '', '2016-04-26 22:05:38.793', '2016-05-05 14:56:18.187', '2', '2', '1');
INSERT INTO "public"."third_station_group" VALUES ('2', '5', '1', '2', 'OPUS娱乐厅', '2', 'asd', '2016-04-26 21:47:19.206', '2016-05-05 14:56:20.454', '2', '2', '1');
COMMIT;

-- ----------------------------
--  Table structure for agent_base_config_value
-- ----------------------------
DROP TABLE IF EXISTS "public"."agent_base_config_value";
CREATE TABLE "public"."agent_base_config_value" (
	"id" int4 NOT NULL DEFAULT nextval('agent_base_config_value_id_seq'::regclass),
	"config_id" int4,
	"value" varchar(200) COLLATE "default",
	"station_id" int4,
	"status" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."agent_base_config_value" OWNER TO "postgres";

COMMENT ON COLUMN "public"."agent_base_config_value"."config_id" IS '配置ID';
COMMENT ON COLUMN "public"."agent_base_config_value"."value" IS '值';
COMMENT ON COLUMN "public"."agent_base_config_value"."station_id" IS '站点ID';
COMMENT ON COLUMN "public"."agent_base_config_value"."status" IS '状态 1禁用 2 启用';

-- ----------------------------
--  Records of agent_base_config_value
-- ----------------------------
BEGIN;
INSERT INTO "public"."agent_base_config_value" VALUES ('2', '2', '1', '1', '2');
INSERT INTO "public"."agent_base_config_value" VALUES ('3', '3', '1984-01-01 00:00:00', '1', '2');
INSERT INTO "public"."agent_base_config_value" VALUES ('4', '4', '1984-05-17', '1', '2');
INSERT INTO "public"."agent_base_config_value" VALUES ('5', '5', '2016-05-26 20:40', '1', '2');
INSERT INTO "public"."agent_base_config_value" VALUES ('6', '6', '中 呵喳地 霜', '1', '2');
INSERT INTO "public"."agent_base_config_value" VALUES ('1', '1', '新葡京娱乐城', '1', '2');
INSERT INTO "public"."agent_base_config_value" VALUES ('14', '68', '2016-05-05 15:00', '1', '2');
INSERT INTO "public"."agent_base_config_value" VALUES ('13', '80', '1', '1', '2');
INSERT INTO "public"."agent_base_config_value" VALUES ('11', '65', '1', '1', '2');
INSERT INTO "public"."agent_base_config_value" VALUES ('12', '69', '测试', '1', '2');
COMMIT;

-- ----------------------------
--  Table structure for bc_lottery_data
-- ----------------------------
DROP TABLE IF EXISTS "public"."bc_lottery_data";
CREATE TABLE "public"."bc_lottery_data" (
	"id" int4 NOT NULL DEFAULT nextval('bc_lottery_data_id_seq'::regclass),
	"lot_code" varchar(20) COLLATE "default",
	"qi_hao" varchar(20) COLLATE "default",
	"hao_ma" varchar(40) COLLATE "default",
	"ommit" varchar(500) COLLATE "default",
	"end_time" timestamp(6) NULL,
	"open_status" int2,
	"open_time" timestamp(6) NULL,
	"station_id" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."bc_lottery_data" OWNER TO "postgres";

COMMENT ON TABLE "public"."bc_lottery_data" IS '彩票开奖数据，包括期号，开奖结果，遗漏';
COMMENT ON COLUMN "public"."bc_lottery_data"."lot_code" IS '彩票编码';
COMMENT ON COLUMN "public"."bc_lottery_data"."qi_hao" IS '期号';
COMMENT ON COLUMN "public"."bc_lottery_data"."hao_ma" IS '号码';
COMMENT ON COLUMN "public"."bc_lottery_data"."ommit" IS '遗漏';
COMMENT ON COLUMN "public"."bc_lottery_data"."end_time" IS '投注结束后，开盘时间';
COMMENT ON COLUMN "public"."bc_lottery_data"."open_status" IS '开奖状态{1未开奖,2都开奖了,3未开奖完, 4已经取消 ，5已经回滚}';
COMMENT ON COLUMN "public"."bc_lottery_data"."open_time" IS '开奖时间';
COMMENT ON COLUMN "public"."bc_lottery_data"."station_id" IS '站点ID';

-- ----------------------------
--  Table structure for bc_station_lottery_play
-- ----------------------------
DROP TABLE IF EXISTS "public"."bc_station_lottery_play";
CREATE TABLE "public"."bc_station_lottery_play" (
	"id" int4 NOT NULL DEFAULT nextval('bc_station_lottery_play_id_seq'::regclass),
	"station_id" int4,
	"status" int2 DEFAULT 2,
	"lot_play_id" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."bc_station_lottery_play" OWNER TO "postgres";

COMMENT ON COLUMN "public"."bc_station_lottery_play"."station_id" IS '站点ID';
COMMENT ON COLUMN "public"."bc_station_lottery_play"."status" IS '状态2=开启，1=关闭';
COMMENT ON COLUMN "public"."bc_station_lottery_play"."lot_play_id" IS '玩法id';

-- ----------------------------
--  Table structure for agent_base_config
-- ----------------------------
DROP TABLE IF EXISTS "public"."agent_base_config";
CREATE TABLE "public"."agent_base_config" (
	"id" int4 NOT NULL DEFAULT nextval('agent_base_config_id_seq'::regclass),
	"name" varchar(50) COLLATE "default",
	"key" varchar(50) COLLATE "default",
	"remark" varchar(200) COLLATE "default",
	"type" varchar(20) COLLATE "default",
	"title" varchar(50) COLLATE "default",
	"expand" varchar(200) COLLATE "default",
	"init_value" varchar(200) COLLATE "default",
	"status" int4,
	"source" text COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."agent_base_config" OWNER TO "postgres";

COMMENT ON COLUMN "public"."agent_base_config"."name" IS '前端的名称';
COMMENT ON COLUMN "public"."agent_base_config"."key" IS '前端输入框的ID';
COMMENT ON COLUMN "public"."agent_base_config"."remark" IS '备注';
COMMENT ON COLUMN "public"."agent_base_config"."type" IS '前端输入的类型（text, select)';
COMMENT ON COLUMN "public"."agent_base_config"."title" IS '描述标题';
COMMENT ON COLUMN "public"."agent_base_config"."expand" IS '输入框拓展属性';
COMMENT ON COLUMN "public"."agent_base_config"."init_value" IS '初始值';
COMMENT ON COLUMN "public"."agent_base_config"."status" IS '是否启用(1是否，2是启用）';

-- ----------------------------
--  Records of agent_base_config
-- ----------------------------
BEGIN;
INSERT INTO "public"."agent_base_config" VALUES ('67', '日期', 'date', '日期', 'date', '日期', null, '1984-05-15', '2', null);
INSERT INTO "public"."agent_base_config" VALUES ('68', '日期时间', 'datetime', '日期时间', 'datetime', '日期时间', null, '1984-05-15', '2', null);
INSERT INTO "public"."agent_base_config" VALUES ('80', '多选', 'checklist', '多选', 'checklist', '多选', null, '1,2', '2', '[{"1":"关闭"},{"2":"开启"}]');
INSERT INTO "public"."agent_base_config" VALUES ('65', '下拉选择', 'stationSwitch', '网站开关', 'select', 'stationSwitch', null, '2', '2', '[{"1":"关闭"},{"2":"开启"}]');
INSERT INTO "public"."agent_base_config" VALUES ('66', '下拉时间', 'serviceDate', '本站名称', 'combodate', '服务时间', '', '1984-05-15', '2', null);
INSERT INTO "public"."agent_base_config" VALUES ('69', '文本域', 'gg', '公告说明', 'textarea', '公告说明', null, '1sfasdfasdfas', '2', null);
INSERT INTO "public"."agent_base_config" VALUES ('1', '文本', 'stationName', '本站名称', 'text', '网站名称', '', '新葡京', '2', null);
COMMIT;

-- ----------------------------
--  Table structure for bc_lottery_play
-- ----------------------------
DROP TABLE IF EXISTS "public"."bc_lottery_play";
CREATE TABLE "public"."bc_lottery_play" (
	"id" int4 NOT NULL DEFAULT nextval('bc_lottery_play_id_seq'::regclass),
	"group_id" int4,
	"name" varchar(10) COLLATE "default",
	"max_bonus_odds" numeric(10,2),
	"min_bonus_odds" numeric(10,2),
	"rakeback" numeric(5,2),
	"status" int2 DEFAULT 2,
	"max_number" int2,
	"sort_no" int2,
	"detail_desc" varchar(500) COLLATE "default",
	"win_example" varchar(500) COLLATE "default",
	"play_method" varchar(500) COLLATE "default",
	"code" varchar(20) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."bc_lottery_play" OWNER TO "postgres";

COMMENT ON TABLE "public"."bc_lottery_play" IS '详细玩法数据';
COMMENT ON COLUMN "public"."bc_lottery_play"."max_bonus_odds" IS '最大中奖金额';
COMMENT ON COLUMN "public"."bc_lottery_play"."min_bonus_odds" IS '最小中奖金额';
COMMENT ON COLUMN "public"."bc_lottery_play"."rakeback" IS '返水';
COMMENT ON COLUMN "public"."bc_lottery_play"."status" IS '状态，2=启用，1=关闭';
COMMENT ON COLUMN "public"."bc_lottery_play"."max_number" IS '最高注数';
COMMENT ON COLUMN "public"."bc_lottery_play"."sort_no" IS '序号';
COMMENT ON COLUMN "public"."bc_lottery_play"."detail_desc" IS '详细介绍';
COMMENT ON COLUMN "public"."bc_lottery_play"."win_example" IS '中奖范例';
COMMENT ON COLUMN "public"."bc_lottery_play"."play_method" IS '玩法介绍';
COMMENT ON COLUMN "public"."bc_lottery_play"."code" IS '玩法编码';

-- ----------------------------
--  Table structure for bc_lottery
-- ----------------------------
DROP TABLE IF EXISTS "public"."bc_lottery";
CREATE TABLE "public"."bc_lottery" (
	"id" int4 NOT NULL DEFAULT nextval('bc_lottery_id_seq'::regclass),
	"name " varchar(20) NOT NULL COLLATE "default",
	"status" int2 DEFAULT 2,
	"code" varchar(20) NOT NULL COLLATE "default",
	"ago" int2 DEFAULT 30,
	"sort_no" int2 DEFAULT 1,
	"view_group" int2,
	"balls" int2,
	"station_id" int4,
	"type" int2
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."bc_lottery" OWNER TO "postgres";

COMMENT ON TABLE "public"."bc_lottery" IS '存放彩票种类信息';
COMMENT ON COLUMN "public"."bc_lottery"."name " IS '彩票名称，中文名称';
COMMENT ON COLUMN "public"."bc_lottery"."status" IS '彩种状态2=开启，1=关闭';
COMMENT ON COLUMN "public"."bc_lottery"."code" IS '彩票编码，如CQSSC,FFC';
COMMENT ON COLUMN "public"."bc_lottery"."ago" IS '开奖时间跟封盘时间差，单位秒';
COMMENT ON COLUMN "public"."bc_lottery"."sort_no" IS '序号';
COMMENT ON COLUMN "public"."bc_lottery"."view_group" IS '显示时分组，1=时时彩，2=低频彩，3=快开，4=快三，5=11选5，6=香港彩';
COMMENT ON COLUMN "public"."bc_lottery"."balls" IS '球数';
COMMENT ON COLUMN "public"."bc_lottery"."station_id" IS '站点ID';
COMMENT ON COLUMN "public"."bc_lottery"."type" IS '彩种类型，1=系统彩，2=时时彩，3=pk10，4=排列三，5=11选5，6=香港彩，7=PC蛋蛋';

-- ----------------------------
--  Records of bc_lottery
-- ----------------------------
BEGIN;
INSERT INTO "public"."bc_lottery" VALUES ('2', '重庆时时彩', '2', 'CQSSC', '30', '1', '1', '5', '0', '2');
INSERT INTO "public"."bc_lottery" VALUES ('1', '北京赛车', '2', 'BJSC', '30', '60', '3', '10', '0', '3');
INSERT INTO "public"."bc_lottery" VALUES ('3', '新疆时时彩', '2', 'XJSSC', '30', '5', '1', '5', '0', '2');
INSERT INTO "public"."bc_lottery" VALUES ('4', '天津时时彩', '2', 'TJSSC', '30', '10', '1', '5', '0', '2');
INSERT INTO "public"."bc_lottery" VALUES ('5', '分分彩', '2', 'FFC', '30', '15', '1', '5', '0', '1');
INSERT INTO "public"."bc_lottery" VALUES ('6', '二分彩', '2', 'EFC', '30', '20', '1', '5', '0', '1');
INSERT INTO "public"."bc_lottery" VALUES ('7', '五分彩', '2', 'WFC', '30', '25', '1', '5', '0', '1');
INSERT INTO "public"."bc_lottery" VALUES ('9', '江西11选5', '2', 'JX11X5', '30', '35', '5', '5', '0', '5');
INSERT INTO "public"."bc_lottery" VALUES ('10', '山东11选5', '2', 'SD11X5', '30', '40', '5', '5', '0', '5');
INSERT INTO "public"."bc_lottery" VALUES ('13', '福彩3D', '2', 'FC3D', '1800', '55', '2', '3', '0', '4');
INSERT INTO "public"."bc_lottery" VALUES ('12', '排列三', '2', 'PL3', '1800', '50', '2', '3', '0', '4');
INSERT INTO "public"."bc_lottery" VALUES ('14', '江西时时彩', '2', 'JXSSC', '30', '11', '1', '5', '0', '2');
INSERT INTO "public"."bc_lottery" VALUES ('8', '上海11选5', '1', 'SH11X5', '30', '30', '5', '5', '0', '5');
INSERT INTO "public"."bc_lottery" VALUES ('16', 'PC蛋蛋', '2', 'XY28', '30', '70', '1', '3', '0', '2');
INSERT INTO "public"."bc_lottery" VALUES ('11', '广东11选5', '2', 'GD11X5', '30', '45', '5', '5', '0', '5');
INSERT INTO "public"."bc_lottery" VALUES ('15', '六合彩', '2', 'LHC', '1800', '60', '6', '7', '0', '6');
COMMIT;

-- ----------------------------
--  Table structure for bc_lottery_prep_code
-- ----------------------------
DROP TABLE IF EXISTS "public"."bc_lottery_prep_code";
CREATE TABLE "public"."bc_lottery_prep_code" (
	"id" int4 NOT NULL DEFAULT nextval('bc_lottery_prep_code_id_seq'::regclass),
	"lot_code" varchar(20) COLLATE "default",
	"qi_hao" varchar(20) COLLATE "default",
	"hao_ma" varchar(40) COLLATE "default",
	"create_time" timestamp(6) NULL,
	"operator" varchar(50) COLLATE "default",
	"station_id" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."bc_lottery_prep_code" OWNER TO "postgres";

COMMENT ON TABLE "public"."bc_lottery_prep_code" IS '系统彩预开奖号码数据表';
COMMENT ON COLUMN "public"."bc_lottery_prep_code"."lot_code" IS '彩票编码';
COMMENT ON COLUMN "public"."bc_lottery_prep_code"."qi_hao" IS '期号';
COMMENT ON COLUMN "public"."bc_lottery_prep_code"."hao_ma" IS '预开奖号码';
COMMENT ON COLUMN "public"."bc_lottery_prep_code"."create_time" IS '添加时间';
COMMENT ON COLUMN "public"."bc_lottery_prep_code"."operator" IS '添加开奖号码的账号';
COMMENT ON COLUMN "public"."bc_lottery_prep_code"."station_id" IS '站点ID';

-- ----------------------------
--  Table structure for bc_lottery_play_group
-- ----------------------------
DROP TABLE IF EXISTS "public"."bc_lottery_play_group";
CREATE TABLE "public"."bc_lottery_play_group" (
	"lot_type" varchar(20) COLLATE "default",
	"name" varchar(20) COLLATE "default",
	"sort_no" int2,
	"status" int2 DEFAULT 2,
	"id" int4 NOT NULL DEFAULT nextval('bc_lottery_play_group_id_seq'::regclass)
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."bc_lottery_play_group" OWNER TO "postgres";

COMMENT ON TABLE "public"."bc_lottery_play_group" IS '玩法分组数据';
COMMENT ON COLUMN "public"."bc_lottery_play_group"."lot_type" IS '彩种类型，1=系统彩，2=时时彩，3=pk10，4=排列三，5=11选5，6=香港彩，7=PC蛋蛋';
COMMENT ON COLUMN "public"."bc_lottery_play_group"."name" IS '玩法分组名称';
COMMENT ON COLUMN "public"."bc_lottery_play_group"."sort_no" IS '序号';
COMMENT ON COLUMN "public"."bc_lottery_play_group"."status" IS '状态，2=开启，1=关闭';

-- ----------------------------
--  Table structure for bc_station_lottery_play_group
-- ----------------------------
DROP TABLE IF EXISTS "public"."bc_station_lottery_play_group";
CREATE TABLE "public"."bc_station_lottery_play_group" (
	"id" int4 NOT NULL DEFAULT nextval('bc_station_lottery_play_group_id_seq'::regclass),
	"station_id" int4,
	"status" int2 DEFAULT 2,
	"lot_play_group_id" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."bc_station_lottery_play_group" OWNER TO "postgres";

COMMENT ON COLUMN "public"."bc_station_lottery_play_group"."station_id" IS '站点ID';
COMMENT ON COLUMN "public"."bc_station_lottery_play_group"."status" IS '状态2=开启，1=关闭';
COMMENT ON COLUMN "public"."bc_station_lottery_play_group"."lot_play_group_id" IS '彩票玩法分组ID';

-- ----------------------------
--  Table structure for bc_lottery_order
-- ----------------------------
DROP TABLE IF EXISTS "public"."bc_lottery_order";
CREATE TABLE "public"."bc_lottery_order" (
	"order_id" varchar(36) COLLATE "default",
	"account" varchar(50) COLLATE "default",
	"lot_code" varchar(20) COLLATE "default",
	"qi_hao" varchar(20) COLLATE "default",
	"play_code" varchar(20) COLLATE "default",
	"station_id" int4,
	"hao_ma" text COLLATE "default",
	"create_time" timestamp(6) NULL,
	"open_time" timestamp(6) NULL,
	"buy_zhu_shu" int4,
	"win_zhu_shu" int4,
	"multiple" int4,
	"buy_money" numeric(12,2),
	"win_money" numeric(12,2),
	"status" int2 DEFAULT 1,
	"model" char(1) COLLATE "default",
	"zhui_hao" int2 DEFAULT 3,
	"roll_back_status" int2 DEFAULT 1,
	"roll_back_money" numeric(25,2),
	"roll_back_rate" numeric(5,2),
	"roll_back_desc" varchar(200) COLLATE "default",
	"id" int4 NOT NULL DEFAULT nextval('"bc_buyLot_id_seq"'::regclass)
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."bc_lottery_order" OWNER TO "postgres";

COMMENT ON TABLE "public"."bc_lottery_order" IS '彩票购买记录';
COMMENT ON COLUMN "public"."bc_lottery_order"."order_id" IS '订单号';
COMMENT ON COLUMN "public"."bc_lottery_order"."account" IS '会员账号名';
COMMENT ON COLUMN "public"."bc_lottery_order"."lot_code" IS '彩票编号';
COMMENT ON COLUMN "public"."bc_lottery_order"."qi_hao" IS '彩票期号';
COMMENT ON COLUMN "public"."bc_lottery_order"."play_code" IS '玩法编码';
COMMENT ON COLUMN "public"."bc_lottery_order"."station_id" IS '站点ID';
COMMENT ON COLUMN "public"."bc_lottery_order"."hao_ma" IS '购买的号码';
COMMENT ON COLUMN "public"."bc_lottery_order"."create_time" IS '购买时间';
COMMENT ON COLUMN "public"."bc_lottery_order"."open_time" IS '开奖时间';
COMMENT ON COLUMN "public"."bc_lottery_order"."buy_zhu_shu" IS '购买注数';
COMMENT ON COLUMN "public"."bc_lottery_order"."win_zhu_shu" IS '中奖注数';
COMMENT ON COLUMN "public"."bc_lottery_order"."multiple" IS '购买倍数';
COMMENT ON COLUMN "public"."bc_lottery_order"."buy_money" IS '购买金额';
COMMENT ON COLUMN "public"."bc_lottery_order"."win_money" IS '中奖金额';
COMMENT ON COLUMN "public"."bc_lottery_order"."status" IS '状态 1等待开奖 2已中奖 3未中奖 4撤单 5派奖回滚成功 6回滚异常的 7开奖异常';
COMMENT ON COLUMN "public"."bc_lottery_order"."model" IS '模式  Y元  J角 F分';
COMMENT ON COLUMN "public"."bc_lottery_order"."zhui_hao" IS '是否继续 1中奖撤单 2中奖继续 3非追号';
COMMENT ON COLUMN "public"."bc_lottery_order"."roll_back_status" IS '会员返水状态 （1，还未返水 2，已经返水 3，返水已经回滚 4,返水已到账 ）';
COMMENT ON COLUMN "public"."bc_lottery_order"."roll_back_money" IS '会员返水金额以 元为最小单位';
COMMENT ON COLUMN "public"."bc_lottery_order"."roll_back_rate" IS '返水比率(0-100)%';
COMMENT ON COLUMN "public"."bc_lottery_order"."roll_back_desc" IS '返水策略描述';

-- ----------------------------
--  Table structure for mny_bank_list
-- ----------------------------
DROP TABLE IF EXISTS "public"."mny_bank_list";
CREATE TABLE "public"."mny_bank_list" (
	"id" int4 NOT NULL DEFAULT nextval('mny_bank_list_id_seq'::regclass),
	"bank_name" varchar(50) COLLATE "default",
	"bank_key" varchar(50) COLLATE "default",
	"bank_type" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."mny_bank_list" OWNER TO "postgres";

COMMENT ON COLUMN "public"."mny_bank_list"."bank_name" IS '银行名称';
COMMENT ON COLUMN "public"."mny_bank_list"."bank_key" IS '银行的KEY';
COMMENT ON COLUMN "public"."mny_bank_list"."bank_type" IS '银行类型';

-- ----------------------------
--  Records of mny_bank_list
-- ----------------------------
BEGIN;
INSERT INTO "public"."mny_bank_list" VALUES ('1', '中国工商银行', 'ICBC', '1');
INSERT INTO "public"."mny_bank_list" VALUES ('2', '中国农业银行', 'ABC', '1');
INSERT INTO "public"."mny_bank_list" VALUES ('3', '中国建设银行', 'CBC', '1');
INSERT INTO "public"."mny_bank_list" VALUES ('5', '中国招商银行', 'CMB', '1');
INSERT INTO "public"."mny_bank_list" VALUES ('4', '中国交通银行', 'CTB', '1');
COMMIT;

-- ----------------------------
--  Table structure for sys_station_domain
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_station_domain";
CREATE TABLE "public"."sys_station_domain" (
	"id" int4 NOT NULL DEFAULT nextval('sys_station_domain_id_seq'::regclass),
	"station_id" int4,
	"domain" varchar(200) COLLATE "default",
	"status" int4,
	"create_datetime" timestamp(6) NULL,
	"modify_datetime" timestamp(6) NULL,
	"create_user_id" int4,
	"modify_user_id" int4,
	"flag_active" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."sys_station_domain" OWNER TO "postgres";

COMMENT ON TABLE "public"."sys_station_domain" IS '站点域名表';
COMMENT ON COLUMN "public"."sys_station_domain"."station_id" IS 'sys_station表 站点ID';
COMMENT ON COLUMN "public"."sys_station_domain"."domain" IS '域名';
COMMENT ON COLUMN "public"."sys_station_domain"."status" IS '状态 1-停用 2-启用';
COMMENT ON COLUMN "public"."sys_station_domain"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."sys_station_domain"."modify_datetime" IS '修改时间';
COMMENT ON COLUMN "public"."sys_station_domain"."create_user_id" IS '创建者';
COMMENT ON COLUMN "public"."sys_station_domain"."modify_user_id" IS '修改者';
COMMENT ON COLUMN "public"."sys_station_domain"."flag_active" IS '数据状态 0：逻辑删除 1:正常';

-- ----------------------------
--  Records of sys_station_domain
-- ----------------------------
BEGIN;
INSERT INTO "public"."sys_station_domain" VALUES ('2', '1', '127.0.0.1', '2', '2016-05-09 01:33:09.162644', '2016-07-03 13:31:20.38', null, '1', '1');
COMMIT;


-- ----------------------------
--  Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."admin_group_menu_id_seq" RESTART 134 OWNED BY "admin_group_menu"."id";
ALTER SEQUENCE "public"."admin_menu_id_seq" RESTART 33 OWNED BY "admin_menu"."id";
ALTER SEQUENCE "public"."admin_user_group_id_seq" RESTART 18 OWNED BY "admin_user_group"."id";
ALTER SEQUENCE "public"."admin_user_id_seq" RESTART 9 OWNED BY "admin_user"."id";
ALTER SEQUENCE "public"."agent_base_config_id_seq" RESTART 83;
ALTER SEQUENCE "public"."agent_base_config_value_id_seq" RESTART 17;
ALTER SEQUENCE "public"."agent_menu_group_id_seq" RESTART 97;
ALTER SEQUENCE "public"."agent_menu_id_seq" RESTART 19;
ALTER SEQUENCE "public"."bc_buyLot_id_seq" RESTART 4 OWNED BY "bc_lottery_order"."id";
ALTER SEQUENCE "public"."bc_lottery_data_id_seq" RESTART 4;
ALTER SEQUENCE "public"."bc_lottery_id_seq" RESTART 21;
ALTER SEQUENCE "public"."bc_lottery_play_group_id_seq" RESTART 4 OWNED BY "bc_lottery_play_group"."id";
ALTER SEQUENCE "public"."bc_lottery_play_id_seq" RESTART 4 OWNED BY "bc_lottery_play"."id";
ALTER SEQUENCE "public"."bc_lottery_prep_code_id_seq" RESTART 4;
ALTER SEQUENCE "public"."bc_lottery_time_id_seq" RESTART 4;
ALTER SEQUENCE "public"."bc_station_lottery_play_group_id_seq" RESTART 4;
ALTER SEQUENCE "public"."bc_station_lottery_play_id_seq" RESTART 4;
ALTER SEQUENCE "public"."mny_bank_list_id_seq" RESTART 8;
ALTER SEQUENCE "public"."mny_com_record_id_seq" RESTART 11;
ALTER SEQUENCE "public"."mny_draw_record_id_seq" RESTART 4;
ALTER SEQUENCE "public"."mny_money_type_id_seq" RESTART 4;
ALTER SEQUENCE "public"."mny_record_id_seq" RESTART 94 OWNED BY "mny_money_record"."id";
ALTER SEQUENCE "public"."sys_announcement_id_seq" RESTART 5;
ALTER SEQUENCE "public"."sys_cache_id_seq" RESTART 9 OWNED BY "sys_cache"."id";
ALTER SEQUENCE "public"."sys_member_id_seq" RESTART 12 OWNED BY "sys_account"."id";
ALTER SEQUENCE "public"."sys_proposal_id_seq" RESTART 4;
ALTER SEQUENCE "public"."sys_station_domain_id_seq" RESTART 11 OWNED BY "sys_station_domain"."id";
ALTER SEQUENCE "public"."sys_station_id_seq" RESTART 9 OWNED BY "sys_station"."id";
ALTER SEQUENCE "public"."sys_third_platform_id_seq" RESTART 8;
ALTER SEQUENCE "public"."third_station_group_id_seq" RESTART 10;
-- ----------------------------
--  Primary key structure for table admin_user
-- ----------------------------
ALTER TABLE "public"."admin_user" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table admin_user_group
-- ----------------------------
ALTER TABLE "public"."admin_user_group" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table mny_money
-- ----------------------------
ALTER TABLE "public"."mny_money" ADD PRIMARY KEY ("account_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table admin_group_menu
-- ----------------------------
ALTER TABLE "public"."admin_group_menu" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table admin_menu
-- ----------------------------
ALTER TABLE "public"."admin_menu" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table sys_cache
-- ----------------------------
ALTER TABLE "public"."sys_cache" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table sys_station
-- ----------------------------
ALTER TABLE "public"."sys_station" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table sys_announcement
-- ----------------------------
ALTER TABLE "public"."sys_announcement" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table sys_account
-- ----------------------------
ALTER TABLE "public"."sys_account" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table mny_money_type
-- ----------------------------
ALTER TABLE "public"."mny_money_type" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table mny_com_record
-- ----------------------------
ALTER TABLE "public"."mny_com_record" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table sys_proposal
-- ----------------------------
ALTER TABLE "public"."sys_proposal" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table mny_draw_record
-- ----------------------------
ALTER TABLE "public"."mny_draw_record" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table sys_register_config
-- ----------------------------
ALTER TABLE "public"."sys_register_config" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table sys_fee_config
-- ----------------------------
ALTER TABLE "public"."sys_fee_config" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table agent_menu
-- ----------------------------
ALTER TABLE "public"."agent_menu" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table agent_menu_group
-- ----------------------------
ALTER TABLE "public"."agent_menu_group" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table sys_account_info
-- ----------------------------
ALTER TABLE "public"."sys_account_info" ADD PRIMARY KEY ("account_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table sys_third_platform
-- ----------------------------
ALTER TABLE "public"."sys_third_platform" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table mny_money_record
-- ----------------------------
ALTER TABLE "public"."mny_money_record" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table bc_lottery_time
-- ----------------------------
ALTER TABLE "public"."bc_lottery_time" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table third_station_group
-- ----------------------------
ALTER TABLE "public"."third_station_group" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table agent_base_config_value
-- ----------------------------
ALTER TABLE "public"."agent_base_config_value" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table bc_lottery_data
-- ----------------------------
ALTER TABLE "public"."bc_lottery_data" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table bc_lottery_data
-- ----------------------------
CREATE UNIQUE INDEX  "lotCode_qiHao_stationId" ON "public"."bc_lottery_data" USING btree(lot_code COLLATE "default" ASC NULLS LAST, qi_hao COLLATE "default" ASC NULLS LAST, station_id ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table bc_station_lottery_play
-- ----------------------------
ALTER TABLE "public"."bc_station_lottery_play" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Uniques structure for table bc_station_lottery_play
-- ----------------------------
ALTER TABLE "public"."bc_station_lottery_play" ADD CONSTRAINT "station_lotPlay_id" UNIQUE ("station_id","lot_play_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table agent_base_config
-- ----------------------------
ALTER TABLE "public"."agent_base_config" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table bc_lottery_play
-- ----------------------------
ALTER TABLE "public"."bc_lottery_play" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Uniques structure for table bc_lottery_play
-- ----------------------------
ALTER TABLE "public"."bc_lottery_play" ADD CONSTRAINT "play_code" UNIQUE ("code") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table bc_lottery
-- ----------------------------
ALTER TABLE "public"."bc_lottery" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table bc_lottery
-- ----------------------------
CREATE UNIQUE INDEX  "lottery_code_index" ON "public"."bc_lottery" USING btree(code COLLATE "default" ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table bc_lottery_prep_code
-- ----------------------------
ALTER TABLE "public"."bc_lottery_prep_code" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table bc_lottery_play_group
-- ----------------------------
ALTER TABLE "public"."bc_lottery_play_group" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table bc_station_lottery_play_group
-- ----------------------------
ALTER TABLE "public"."bc_station_lottery_play_group" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Uniques structure for table bc_station_lottery_play_group
-- ----------------------------
ALTER TABLE "public"."bc_station_lottery_play_group" ADD CONSTRAINT "bc_station_lottery_play_group_stationId_lotPlayGroupId_key" UNIQUE ("station_id","lot_play_group_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table bc_lottery_order
-- ----------------------------
ALTER TABLE "public"."bc_lottery_order" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table mny_bank_list
-- ----------------------------
ALTER TABLE "public"."mny_bank_list" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table sys_station_domain
-- ----------------------------
ALTER TABLE "public"."sys_station_domain" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

