$.fn.editable.defaults.mode = 'inline';
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

// 格式化时分组
function showSFZ(value) {
	switch (value) {
	case 1:
		return '时时彩';
		break;
	case 2:
		return '低频彩';
		break;
	case 3:
		return '快开';
		break;
	case 4:
		return '快三';
		break;
	case 5:
		return '11选5';
		break;
	case 6:
		return '香港彩';
		break;
	}
}

/**
 * 
 * @param attrName
 *            select的ID属性
 * @param value
 *            option ID值
 */
function beSelect(attrName, value) {
	var obj = document.getElementById(attrName);
	for (var i = 0; i < obj.length; i++) {
		if (obj[i].value == value) {
			obj[i].selected = true;
		}
	}
}

/**
 * 判断是否是打开状态 attrName select ID值 value 参数值 status 状态
 */
function isOpen(attrName, value, status) {
	var obj = $('.' + attrName);
	for (var i = 0; i < obj.length; i++) {
		if (obj[i].id == value) {
			// if(status==2){
			obj[i].checked = true;
			// }
		}
	}
}

function showSFZSelect(value) {
	if (value == '时时彩') {
		return 1;
	}
	if (value == '低频彩') {
		return 2;
	}
	if (value == '快开') {
		return 3;
	}
	if (value == '快三') {
		return 4;
	}
	if (value == '11选5') {
		return 5;
	}
	if (value == '香港彩') {
		return 6;
	}
}

// 格式化彩种类型
function showCZ(value) {
	switch (value) {
	case 1:
		return '系统彩';
		break;
	case 2:
		return '时时彩';
		break;
	case 3:
		return 'pk10';
		break;
	case 4:
		return '排列三';
		break;
	case 5:
		return '11选5';
		break;
	case 6:
		return '香港彩';
		break;
	case 7:
		return 'PC蛋蛋';
		break;
	}
}

// 状态
function statusFormatter(value, row, index) {
	if (value === 2) {
		return '<span class="text-success">启用</span><a href="#"><span class="text-danger stateClose">(禁用)</span></a>';
	}
	return '<span class="text-danger">禁用</span><a href="#"><span class="text-success stateOpen">(启用)</span></a>';
}

// 格式化时间
function dateFormatter(value, row, index) {
	return DateUtil.formatDatetime(value);
}

// 刷新
function refresh() {
	$("#datagrid_tb").bootstrapTable('refresh');
}