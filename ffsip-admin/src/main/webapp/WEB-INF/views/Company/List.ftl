<html>
<head>
    <meta name="decorator" content="v2"/>
    <title>公司信息管理</title>
    <#include "../common/share_macro.ftl" encoding="utf-8">
</head>
<body>

<form id="find-page-orderby-form" action="${BasePath !}/Company/queryData.do" method="post" class="ff-form">
    <input id="find-page-size" type="hidden" name="pageSize" value="${(pageObj.pageSize) !}"/>
    <input id="find-page-index" type="hidden" name="pageIndex" value="1"/>
    <input id="find-page-count" type="hidden" value="${(pageObj.pageCount) !}"/>

    <div class="form-group">
        <label>名称：</label>
        <div><input name="name" type="text" placeholder="" value="${(entity.name) !}"></div>
    </div>
    
    <div class="form-group">
        <label>编码：</label>
        <div><input name="code" type="text" placeholder="" value="${(entity.code) !}"></div>
    </div>

    <div class="form-group">
        <label>绑定会员编码：</label>
        <div><input name="memberCode" type="text" placeholder="" value="${(entity.memberCode) !}"></div>
    </div>


    <button id="find-page-orderby-button" class="ff-btn sm btn-inquire" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;查询
    </button>
    <a href="javascript:iframeFullPage('${BasePath !}/Company/toForm.do')" class="ff-btn sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加</a>
    <button class="ff-btn sm white btn-clear-keyword" data-target="data_list"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;清空
    </button>

</form>

<div id="data_list" class="ff_DataTable"></div>
<script type="text/javascript" src="${BasePath !}/asset/js/ffsip/Company/company_list.js?v=${ver !}"></script>
<style>.clm_action {
    width: 150px !important;
}</style>
<script>

	var dict_actFlag = {"0":"启用", "1":"禁用"};
	var vehicle_type = {"0":"产品详情", "1":"文章详情","2":"web链接"};

    $(document).ready(function () {

        requirejs(['ff/select2'], function () {
            $('select').select2();
        });

        requirejs(['ff/init_datatable'], function (initDataTable) {

            var dt_role_list = new initDataTable({
                div_id: 'data_list',
                url: rootPath + "/Company/queryData.do",
                columns: [
                    {data: "name", label: '名称', class: 'text-nowrap'},
                    {data: "abbreviation", label: '简称'},
                    {data: "memberCode", label: '绑定会员编码'},
                    {
                        data: "createDate",
                        label: '创建时间',
                        class: 'text-nowrap',
                        format: {datetime: 'yyyy-MM-dd hh:mm:ss'}
                    },
                    {
                        data: "lastUpdateDate",
                        label: '修改时间',
                        class: 'text-nowrap',
                        format: {datetime: 'yyyy-MM-dd HH:mm:ss'}
                    },

                ],
                show_checkbox: false,
                gen_permission: function () {
                    var map = [];
                    map.push('edit');
                    map.push('deletes');

                    return map;
                },
                clm_action: function (item) {
                    return gen_action(item);
                }
            });
        });
    });

</script>
</body>
</html>
