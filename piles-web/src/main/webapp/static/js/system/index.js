'use strict';
layui.use([ 'jquery', 'layer', 'element' ], function() {
	window.jQuery = window.$ = layui.jquery;
	window.layer = layui.layer;
	// var element = layui.element;

	// 左侧导航栏向左折叠
	$('.larry-side-menu').click(function() {
		var sideWidth = $('#larry-side').width();
		if (sideWidth === 200) {
			$('#larry-body').animate({
				left : '0'
			}); // admin-footer
			$('#larry-footer').animate({
				left : '0'
			});
			$('#larry-side').animate({
				width : '0'
			});
		} else {
			$('#larry-body').animate({
				left : '200px'
			});
			$('#larry-footer').animate({
				left : '200px'
			});
			$('#larry-side').animate({
				width : '200px'
			});
		}
	});

	$(function() {

	});

});
