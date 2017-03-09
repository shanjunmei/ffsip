<html>
<head>
    <meta name="decorator" content="v2"/>
    <title>用户管理</title>
</head>
<body>

<form id="find-page-orderby-form" action="${BasePath !}/User/queryData.do" method="post" class="ff-form">
    <input id="find-page-size" type="hidden" name="pageSize" value="${(pageObj.pageSize) !}"/>
    <input id="find-page-index" type="hidden" name="pageIndex" value="1"/>
    <input id="find-page-count" type="hidden" value="${(pageObj.pageCount) !}"/>

    <div class="form-group">
        <label>用户名：</label>
        <div><input name="name" type="text" placeholder="" value="${(entity.name) !}"></div>
    </div>

    <div class="form-group">
        <label>密码：</label>
        <div><input name="password" type="text" placeholder="" value="${(entity.password) !}"></div>
    </div>


    <button id="find-page-orderby-button" class="ff-btn sm btn-inquire" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;查询
    </button>
    <a href="javascript:iframeFullPage('${BasePath !}/User/toForm.do')" class="ff-btn sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加</a>
    <button class="ff-btn sm white btn-clear-keyword" data-target="data_list"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;清空
    </button>

</form>

<div id="data_list" class="ff_DataTable"></div>
<script type="text/javascript" src="${BasePath !}/asset/js/ffsip/user/user_list.js?v=${ver !}"></script>
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
                url: rootPath + "/User/queryData.do",
                columns: [
                    {data: "name", label: '用户名称', class: 'text-nowrap'},
                    {data: "code", label: '登录名', class: 'text-nowrap'},
                    //{ data: "dataScopeInfo", label: '数据范围', class:'text-nowrap'},
                    {data: "password", label: '密码', class: 'text-nowrap', data_dict: {"1": "是", "0": "否"}},
                    {
                        data: "lastUpdateDate",
                        label: '最后更新时间',
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
