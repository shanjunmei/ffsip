<html>
<head>
    <meta name="decorator" content="v2"/>
    <title>微信菜单管理</title>
</head>
<body>

<form id="find-page-orderby-form" action="${BasePath !}/WxMenu/queryData.do" method="post" class="ff-form">
    <input id="find-page-size" type="hidden" name="pageSize" value="${(pageObj.pageSize) !}"/>
    <input id="find-page-index" type="hidden" name="pageIndex" value="1"/>
    <input id="find-page-count" type="hidden" value="${(pageObj.pageCount) !}"/>

    <div class="form-group">
        <label>菜单名：</label>
        <div><input name="name" type="text" placeholder="" value="${(entity.name) !}"></div>
    </div>

    <div class="form-group">
        <label>菜单编码：</label>
        <div><input name="code" type="text" placeholder="" value="${(entity.code) !}"></div>
    </div>

    <div class="form-group">
        <label>菜单类型：</label>
        <div><input name="type" type="text" placeholder="" value="${(entity.type) !}"></div>
    </div>

    <div class="form-group">
        <label>菜单URL：</label>
        <div><input name="url" type="text" placeholder="" value="${(entity.url) !}"></div>
    </div>

    <div class="form-group">
        <label>父级菜单编码：</label>
        <div><input name="pCode" type="text" placeholder="" value="${(entity.pCode) !}"></div>
    </div>

    <div class="form-group">
        <label>排序：</label>
        <div><input name="sort" type="text" placeholder="" value="${(entity.sort) !}"></div>
    </div>

    <a href="javascript:iframeFullPage('${BasePath !}/WxMenu/toForm.do')" class="ff-btn sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加</a>

    <button id="publish_wx_menu" class="ff-btn sm btn-inquire" onclick="publish()" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;发布
    </button>

    <button id="find-page-orderby-button" class="ff-btn sm btn-inquire" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;查询
    </button>
  <#--  <a href="javascript:iframeFullPage('${BasePath !}/User/toForm.do')" class="ff-btn sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加</a>-->
    <button class="ff-btn sm white btn-clear-keyword" data-target="data_list"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;清空
    </button>

</form>

<div id="data_list" class="ff_DataTable"></div>
<script type="text/javascript" src="${BasePath !}/asset/js/ffsip/WxMenu/WxMenu_list.js?v=${ver !}"></script>
<style>.clm_action {
    width: 150px !important;
}</style>
<script>

    $(document).ready(function () {

        ffzx.ui(['select2'], function () {
            $('select').select2();
        });

        ffzx.ui(['datatable'], function () {

            var dt_role_list = new initDataTable({
                div_id: 'data_list',
                url: rootPath + "/WxMenu/queryData.do",
                columns: [
                    {data: "name", label: '菜单名称', class: 'text-nowrap'},
                    {data: "code", label: '菜单编码', class: 'text-nowrap'},
                    {data: "pCode", label: '父级编码', class: 'text-nowrap'},
                    {
                        data: "createDate",
                        label: '创建时间',
                        class: 'text-nowrap',
                        format: {datetime: 'yyyy-MM-dd HH:mm:ss'}
                    }

                ],
                show_checkbox: false,
                gen_permission: function () {
                    var map = [];
                    map.push('edit');
                    map.push('delete');
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
