layui.config({
	base : '/js/system/'
}).extend({
	formSelects: 'module/formSelects/formSelects-v4',
	treetable: 'module/treetable-lay/treetable',
	dropdown: 'module/dropdown/dropdown',
	notice: 'module/notice/notice',
	step: 'module/step-lay/step',
	dtree: 'module/dtree/dtree',
	citypicker: 'module/city-picker/city-picker',
	tableSelect: 'module/tableSelect/tableSelect',
	ax: 'module/ax/ax',
	ztree: 'module/ztree/ztree-object'
}).use([ 'jquery', 'element', 'layer', 'navtab','ax' ], function() {
	window.jQuery = window.$ = layui.jquery;
	window.layer = layui.layer;
	var element = layui.element, navtab = layui.navtab({
		elem : '.larry-tab-box'
	});

	// iframe自适应
	$(window).on('resize', function() {
		var $content = $('#larry-tab .layui-tab-content');
		$content.height($(this).height() - 120);
		// $content.height($(this).height() - 163);
		$content.find('iframe').each(function() {
			$(this).height($content.height()-5);
		});
		tab_W = $('#larry-tab').width();
		// larry-footer：p-admin宽度设定
		var larryFoot = $('#larry-footer').width();
		$('#larry-footer p.p-admin').width(larryFoot - 300);
	}).resize();

	// 左侧菜单导航-->tab
	$(function() {
		$('#larry-nav-side').click(function() {
			if ($(this).attr('lay-filter') !== undefined) {
				$(this).children('ul').find('li').each(function() {
					var $this = $(this);
					if ($this.find('dl').length > 0) {
						var $dd = $this.find('dd').each(function() {
							$(this).click(function() {
								var $a = $(this).children('a');
								var href = $a.data('url');
								var icon = $a.children('i:first').data('icon');
								var title = $a.children('span').text();
								var data = {
									href : href,
									icon : icon,
									title : title
								}
								navtab.tabAdd(data);
							});
						});
					} else {
						$this.click(function() {
							var $a = $(this).children('a');
							var href = $a.data('url');
							var icon = $a.children('i:first').data('icon');
							var title = $a.children('span').text();
							var data = {
								href : href,
								icon : icon,
								title : title
							}
							navtab.tabAdd(data);
						});
					}
				});
			}
		})
	});
});


