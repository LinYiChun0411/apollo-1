GlobalTypeUtil = {
	datas : {
		// tablekey在resources/tablekey.txt中声明
		1 : {
			// columnkey在resources/columnkey.txt中声明
			1 : {
				1 : "人工存款",
				2 : "人工取款",
				3 : "在线存款",
				4 : "在线取款"
			}
		},
		2 : {
			1 : {
				1 : "禁用",
				2 : "启用"
			},
			2 : {
				1 : "会员",
				2 : "租户"
			}
		},
		3 : {
			1 : {
				1 : "禁用",
				2 : "启用"
			}
		},
		4 : {
			1 : {
				1 : "未发布",
				2 : "已发布"
			},
			2 : {
				1 : "推广",
				2 : "维护"
			}
		},
		5 : {
			1 : {
				1 : "未读",
				2 : "已读"
			}
		},
		6 : {
			1 : {
				1 : "禁用",
				2 : "启用",
				3 : "隐藏"
			}
		},
		7 : {
			1 : {
				1 : "禁用",
				2 : "启用"
			},
			2 : {
				1 : "会员",
				2 : "代理"
			},
			3 : {
				1 : "文本",
				2 : "下拉框",
				3 : "单选",
				4 : "多选",
				5 : "文本域"
			},
			4 : {
				1 : "隐藏",
				2 : "可见"
			}
		},
		8 : {
			1 : {
				"text" : "文本",
				"date" : "文本域",
				"datetime" : "日期时间",
				"checklist" : "多选",
				"select" : "下拉选择",
				"combodate" : "下拉时间",
				"textarea" : "文本域"
			}
		}
	},
	getTypeName : function(tableKey, colKey, key) {
		if (!key) {
			return "";
		}
		return GlobalTypeUtil.datas[tableKey][colKey][key];
	},
	getCombo : function(tableKey, colKey) {
		if (!colKey) {
			return {};
		}
		var data = GlobalTypeUtil.datas[tableKey][colKey];
		var res = [];
		var son = {};
		for ( var key in data) {
			son = {};
			son.id = key;
			son.name = data[key];
			res.push(son);
		}
		return res;
	}

}