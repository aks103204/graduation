// JavaScript Document
$(function() {
	// 根据屏幕宽度进行样式表切换
	/*$(window).resize(function() {
		cssChange();
	});

	cssChange();*/

	function cssChange() {
		var windowWidth = $(window).width();
		if (windowWidth < 1190) {
			$('#styleChange').attr({
				'href' : _context_path + '/css/sjjx_style1024.css'
			});
		} else if (windowWidth >= 1190) {
			$('#styleChange').attr({
				'href' : ''
			});
		}
	}

	// tab效果
	$('.tabTit span').click(function() {
		$(this).addClass('current').siblings('span').removeClass('current');
		$(this).parent().siblings('.tabCon').eq($(this).index()).show().siblings('.tabCon').hide();
	});

	$('.tabTitHover span').mouseover(function() {
		$(this).addClass('current').siblings('span').removeClass('current');
		$(this).parent().siblings('.tabCon').eq($(this).index()).show().siblings('.tabCon').hide();
	});

	// 登录切换
	$('.windowBg').height($(document).height());

	$(window).resize(function() {
		$('.windowBg').height($(document).height())
	});

	// 翻页-页码部分初始化
	initPaging();

	/* 返回头部 */
	var windowSTop = $(window).scrollTop();

	$(window).scroll(function() {
		var windowsTop = $(window).scrollTop();
		if (windowsTop > 220) {
			$('.backTop').show();
		} else if (220 >= windowsTop >= 0) {
			$('.backTop').hide();
		}
	});
	$('.backTop').click(function() {
		$(window).scrollTop(0);
	});
});

function initPaging() {

	var j = 0;

	$('.pageAll a').each(function() {
		j = j + $(this).width() + 8;
	});

	$('.pageAll').width(j);
}