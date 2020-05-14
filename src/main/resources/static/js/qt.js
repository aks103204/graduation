// JavaScript Document
$(function(){
	
	$('.tabTit span').click(function(){
		$(this).addClass('current').siblings('span').removeClass('current');
		$(this).parent().siblings('.tabCon').eq($(this).index()).show().siblings('.tabCon').hide()
	});

	$('.tabTitHover span').mouseover(function(){
		$(this).addClass('current').siblings('span').removeClass('current');
		$(this).parent().siblings('.tabCon').eq($(this).index()).show().siblings('.tabCon').hide()
	});
	

	$('.windowBg').height($(document).height());
	$(window).resize(function(){
			$('.windowBg').height($(document).height())
	});

	var i = 0;
	$('.indexBanner .dotAll span').each(function(){
		i = i + $(this).width() + 14;
	});
	$('.indexBanner .dotAll').width(i);

	setPagingWidth();
	//分享	

	$('.icon_share').each(function() {

		$(this).click(function(){
			var div=$(this).children('div');
			if(div.css('display')=="none") {
				if($(this).parent().hasClass('listLi')) {
					$(this).parent('.listLi').siblings('.listLi').find('.shareLi,.arrow').hide();
				}
				div.show();
			} else {
				div.hide();
			}
		});
	});

	//绿色头
	$('.headGreen .searchArea').focus(function(){
		$(this).css({'color':'#fff'})
	}).blur(function(){
		$(this).css({'color':'#adc9bd'})	
	});

	// 三个级导航
//	$('.rightList li h1').click(function(){
//		var span=$(this).children('span');
//		if(span.attr('class')=="iconImg") {
//			span.addClass('iconImgClose').removeClass('iconImg');
//			$(this).next('ul').hide();
//		} else {
//			span.addClass('iconImg').removeClass('iconImgClose');
//			$(this).siblings('ul').show();
//		}
//	});
	
	$('.rightList li h1').click(function(){
		var span=$(this).children('span');
		if(span.attr('class')=="iconImg") {
			span.addClass('iconImgClose').removeClass('iconImg');
			$(this).next('ul').hide();
			$(this).parent('li').find("h3").hide();
			$(this).parent('li').find("h2").children("span").addClass('iconImgClose').removeClass('iconImg');
		} else {
			span.addClass('iconImg').removeClass('iconImgClose');
			$(this).siblings('ul').show();			
		}
		
	});
	
	$('.rightList li h2').click(function(){
		var span=$(this).children('span');
		if(span.attr('class')=="iconImg") {
			span.addClass('iconImgClose').removeClass('iconImg');
			$(this).next('h3').hide();
		} else {
			span.addClass('iconImg').removeClass('iconImgClose');
			$(this).siblings('h3').show();
		}
	});
	
	$('.rightList .exercises').click(function(){
		$('.rightList .exercises').removeClass('current');
		$(this).addClass('current')
	});
	
	
});

function setPagingWidth() {
	var j = 0;
	$('.pageAll a').each(function(){
		j = j + $(this).width() + 8;
	});

	$('.pageAll').width(j);
}
/**
 * ajax提交，没有提示消息，直接提交
 * 
 * @param url
 *            url
 * @param formId
 *            form id
 * @param func
 *            成功返回后执行的函数
 */
function ajax_submit_no_msg(url, formId, func) {
	url = url + (url.indexOf("?") > 0 ? "&" : "?") + "timestamp="
			+ new Date().getTime();

	var postObj = new Object();
	if (formId != null && formId != "") {
		postObj = $("#" + formId).serialize();
	}

	$.ajax({
		url : url,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : postObj,
		dataType : "json",
		type : "post",
		success : function(data) {
			if (!isUndefinedOrNull(func))
				func(data);
		}
	});
}
function isUndefinedOrNull(val) {
	if (typeof (val) == "undefined" || val == null) {
		return true;
	}
	return false;
}

/**
 * ajax 加载页面
 * 
 * @param url
 *            url
 * @param formId
 *            form id
 * @param contentId
 *            刷新的区域id
 * @param func
 *            成功之后的操作
 * @param isBlock
 *            是否锁屏3
 */
function load_page(url, formId, contentId, func, isBlock) {
	url = url + (url.indexOf("?") > 0 ? "&" : "?") + "timestamp="
			+ new Date().getTime();

	if (isUndefinedOrNull(contentId) || contentId == '')
		contentId = "page-wrapper";
	if (isUndefinedOrNull(isBlock))
		isBlock = true;
	if (isBlock)
		blockWindow();

	var postObj = new Object();
	if (formId != null && formId != "") {
		postObj = $("#" + formId).serialize();
	}

	$.ajax({
		url : url,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : postObj,
		dataType : "html",
		type : "post",
		success : function(data) {
			$("#" + contentId).html(data);
			if (!isUndefinedOrNull(func))
				func(data);
			if (isBlock)
				$.unblockUI();
		}
	});
}

function blockWindow() {
	$.blockUI({
		message : "<div><img src='" + _context_path + "/images/blockui/loading_big.gif' style='background-color:transparent;'/></div>",
		css : {
			border : 'none',
			top : ($(window).height() - 100) / 2 + 'px',
			left : ($(window).width() - 100) / 2 + 'px',
			width : '100px',
			background : 'Transparent'
		}
	});
}
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

String.prototype.replaceAll = function(s1, s2){ 
	return this.replace(new RegExp(s1, "gm"), s2); 
}

// 文件下载共通
function zjy_download_file(dlPath, dlName, iframeId) {

	if(isUndefinedOrNull(iframeId)) {
		iframeId = "dl_iframe";
	}

    $(window.frames[iframeId].document).find("#dl_filePath").val(dlPath);
    $(window.frames[iframeId].document).find("#dl_fileName").val(dlName);
    $(window.frames[iframeId].document).find("#dl_form").submit();
}

function showFrtConfirmMsg(msg, okFunc) {
    $M({
        title: '确认',
        content: msg,
        width: '200px',
        ok: function(){
        	if (typeof(okFunc) != "undefined") {
                okFunc();
        	}
            this.close();
        },
        okVal: '提交',
        cancel: false,
        cancelVal: '关闭'
    });
}

function showFrtAlertMsg(msg) {
    $M({
        title: '提示',
        content: msg,
        width: '300px',
        ok: false
    });
}

//ymdhmsS 转成 y-m-d h：m：s
function ymdhmsS2ymdhms(ymdhmsS) {
	if(isUndefinedOrNull(ymdhmsS)) {
		return ymdhmsS;
	}
	if(ymdhmsS.length == 14 || ymdhmsS.length == 17) {
		var y = ymdhmsS.substring(0, 4);
		var m = ymdhmsS.substring(4, 6);
		var d = ymdhmsS.substring(6, 8);
		var h = ymdhmsS.substring(8, 10);
		var mi = ymdhmsS.substring(10, 12);
		var s = ymdhmsS.substring(12, 14);
		return y + "-" + m + "-" + d + " " + h + ":" + mi + ":" + s;
	}
	return ymdhmsS;
}

//ymdh 转成 y-m-d
function ymd2ymdHyphen(ymd) {
	if(isUndefinedOrNull(ymd)) {
		return ymd;
	}
	if(ymd.length != 8) {
		return ymd;
	}
	var y = ymd.substring(0, 4);
	var m = ymd.substring(4, 6);
	var d = ymd.substring(6);
	return y + "-" + m + "-" + d;
}

//表格显示文本内容
function substrCellTxt(cellTxt) {
	if (isUndefinedOrNull(cellTxt)) return "";
	if (cellTxt.length > 30) {
		return cellTxt.substr(0, 30) + "...";
	} else {
		return cellTxt;
	}
}

// 表格显示文本区域内容
function substrCellTxtAr(cellTxtAr) {
	if (isUndefinedOrNull(cellTxtAr)) return "";
	var chgCellTxt = cellTxtAr.replaceAll("\r\n", "<br/>").replaceAll("\n", "<br/>").replaceAll("\r", "<br/>")
	return substrCellTxt(chgCellTxt);
}

function createTblTxtArCell(cellTxtAr) {
	return "<label class='td_omit_tooltip' title='" + cellTxtAr + "'>" + substrCellTxtAr(cellTxtAr) + "</label>";
}

function getTxtArWord(cellTxtAr) {
	if (isUndefinedOrNull(cellTxtAr)) return "";
	return cellTxtAr.replaceAll("\r\n", "<br/>").replaceAll("\n", "<br/>").replaceAll("\r", "<br/>");
}

//显示通知
function showQtNotice(url, noticeId) {
    var params = new Object();
    // 通知编号
    params["noticeId"] = noticeId;

    createQtSubmitForm(url, "notice", params);
}

// 显示企业
function showQtCmp(url, cmpId) {
    var params = new Object();
    // 通知编号
    params["cmpId"] = cmpId;

    createQtSubmitForm(url, "cmp", params);
}

// 显示职位
function showQtJob(url, jobId) {
    var params = new Object();
    // 通知编号
    params["jobId"] = jobId;

    createQtSubmitForm(url, "job", params);
}

function createQtUrlParam(url, params) {
	if (params == null) return url;
	for (var param in params) {
		if(url.indexOf("?") != -1) {
			url += "&";
		} else {
			url += "?";
		}
		url += param + "=" + params[param];
	}
	return url;
}

function createQtSubmitForm(url, tagetNm, params) {
	createQtSubmitForm(url, tagetNm, params, false);
}

function createQtSubmitForm(url, tagetNm, params, blockUI) {
	if (blockUI) {
		$.blockUI({
			message : "<div><img src='" + _context_path + "/images/blockui/loading_big.gif' style='background-color:transparent;'/></div>",
			css : {
				border : 'none',
				top : ($(window).height() - 100) / 2 + 'px',
				left : ($(window).width() - 100) / 2 + 'px',
				width : '100px',
				background : 'Transparent'
			}
		});
	}

	var form = $("<form></form>")
	var postUrl = createQtUrlParam(url, params);

	form.prop('action', postUrl);
	form.prop('method', 'post');
	if (tagetNm != null) {
		form.prop('target', tagetNm);
	}

	form.appendTo("body");
	form.css('display','none');
	form.submit();

	form.remove();
	
}
// 跳转到个人中心
function goIntoMyZoe(url) {
	createQtSubmitForm(url, null, null, true);
}

// 退出登录
function gotoLogout(url) {
	createQtSubmitForm(url, null, null);
}