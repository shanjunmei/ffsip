<!-- 公共js  strat -->
<script src="${BasePath !}/asset/js/jquery-1.10.2.min.js?v=${ver !}"></script>
<script src="${BasePath !}/asset/v2/js/require/require.js?v=${ver !}"></script>
<script src="${BasePath !}/asset/js/control/bootstrap/js/bootstrap.min.js?v=${ver !}"></script>
<script src="${BasePath !}/asset/js/control/artDialog-6.0.4/dist/dialog-plus.js?v=${ver !}"></script>
<script src="${BasePath !}/asset/js/control/artDialog-6.0.4/dialogUtils.js?v=${ver !}"></script>
<script src="${BasePath !}/asset/js/control/ajax/js/ajaxUtils.js?v=${ver !}" type="text/javascript"></script>
<script src="${BasePath !}/asset/js/common/common.js?v=${ver !}"></script>
<!-- 时间控件 -->
<script src="${BasePath !}/asset/js/control/My97DatePicker/WdatePicker.js?v=${ver !}" type="text/javascript"></script>

<!-- 公共js  end -->
<script type="text/javascript" src="${BasePath !}/asset/js/common/pageadm.js?v=${ver !}"></script>
<script type="text/javascript">
	var rootPath = "${BasePath !}";
	var version = "${ver !}";
</script>

<!-- 当前项目的全局 js -->
<#if sys ??>
<script type="text/javascript" src="${BasePath !}/asset/js/${sys !}.js?v=${ver !}"></script>
</#if>