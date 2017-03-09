<html>
<head>
    <meta name="decorator" content="v2"/>
    <title>联系人管理</title>
</head>
<body>

<form id="find-page-orderby-form" action="${BasePath !}/Contact/queryData.do" method="post" class="ff-form">
    <input id="find-page-size" type="hidden" name="pageSize" value="${(pageObj.pageSize) !}"/>
    <input id="find-page-index" type="hidden" name="pageIndex" value="1"/>
    <input id="find-page-count" type="hidden" value="${(pageObj.pageCount) !}"/>



    <div class="form-group">
        <label>编码：</label>
        <div><input name="code" type="text"  value="${(entity.code) !}"></div>
    </div>

    <div class="form-group">
        <label>公司编码：</label>
        <div><input name="companyCode" type="text"  value="${(entity.companyCode) !}"></div>
    </div>

    <div class="form-group">
        <label>职位：</label>
        <div><input name="position" type="text"  value="${(entity.position) !}"></div>
    </div>

    <div class="form-group">
        <label>工号：</label>
        <div><input name="jobNumber" type="text"  value="${(entity.jobNumber) !}"></div>
    </div>

    <div class="form-group">
        <label>手机号：</label>
        <div><input name="phone" type="text"  value="${(entity.phone) !}"></div>
    </div>



    <a href="javascript:iframeFullPage('${BasePath !}/Contact/toForm.do')" class="ff-btn sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加</a>


    <button id="find-page-orderby-button" class="ff-btn sm btn-inquire" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;查询
    </button>
  <#--  <a href="javascript:iframeFullPage('${BasePath !}/User/toForm.do')" class="ff-btn sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加</a>-->
    <button class="ff-btn sm white btn-clear-keyword" data-target="data_list"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;清空
    </button>

</form>

<div id="data_list" class="ff_DataTable"></div>
<script type="text/javascript" src="${BasePath !}/asset/js/ffsip/Contact/Contact_list.js?v=${ver !}"></script>
<style>.clm_action {
    width: 150px !important;
}</style>
<script>

    $(document).ready(function () {


        ffzx.ui(['datatable'], function () {

            var dt_role_list = new initDataTable({
                div_id: 'data_list',
                url: rootPath + "/Contact/queryData.do",
                columns: [

                    {data: "code", label: '编码', class: 'text-nowrap'},
                    {data: "companyCode", label: '公司编码', class: 'text-nowrap'},
                    {data: "position", label: '职位', class: 'text-nowrap'},
                    {data: "jobNumber", label: '工号', class: 'text-nowrap'},
                    {data: "phone", label: '手机号', class: 'text-nowrap'},
                    {data: "email", label: '邮箱', class: 'text-nowrap'},
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
