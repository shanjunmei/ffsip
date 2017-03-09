/* Simplified Chinese translation for the jQuery Timepicker Addon /
/ Written by Will Lu */
( function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define( [ "jq/datetimepicker" ], factory );
	} else {

		// Browser globals
		factory( jQuery.timepicker );
	}
}( function() {

	$.timepicker.regional['zh-CN'] = {
		timeOnlyTitle: '选择时间',
		timeText: '时间',
		hourText: '小时',
		minuteText: '分钟',
		secondText: '秒钟',
		millisecText: '毫秒',
		microsecText: '微秒',
		timezoneText: '时区',
		currentText: '现在时间',
		closeText: '关闭',
		timeFormat: 'HH:mm',
		timeSuffix: '',
		amNames: ['AM', 'A'],
		pmNames: ['PM', 'P'],
		isRTL: false
	};
	
	$.timepicker.setDefaults($.timepicker.regional['zh-CN']);

} ) );