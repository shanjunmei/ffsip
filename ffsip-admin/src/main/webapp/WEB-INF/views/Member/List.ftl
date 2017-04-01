<html>
<head>
    <meta name="decorator" content="v2"/>
    <title>用户管理</title>
</head>
<body>

<form id="find-page-orderby-form" action="${BasePath !}/Member/queryData.do" method="post" class="ff-form">
    <input id="find-page-size" type="hidden" name="pageSize" value="${(pageObj.pageSize) !}"/>
    <input id="find-page-index" type="hidden" name="pageIndex" value="1"/>
    <input id="find-page-count" type="hidden" value="${(pageObj.pageCount) !}"/>

    <div class="form-group">
        <label>用户名：</label>
        <div><input name="name" type="text" placeholder="" value="${(entity.name) !}"></div>
    </div>

    <div class="form-group">
        <label>手机号：</label>
        <div><input name="phone" type="text" placeholder="" value="${(entity.phone) !}"></div>
    </div>


    <button id="find-page-orderby-button" class="ff-btn sm btn-inquire" type="button"><i class="fa fa-search"></i>&nbsp;&nbsp;查询
    </button>
  <#--  <a href="javascript:iframeFullPage('${BasePath !}/User/toForm.do')" class="ff-btn sm"><i class="fa fa-plus"></i>&nbsp;&nbsp;添加</a>-->
    <button class="ff-btn sm white btn-clear-keyword" data-target="data_list"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;清空
    </button>

</form>

<div id="data_list" class="ff_DataTable"></div>
<script type="text/javascript" src="${BasePath !}/asset/js/ffsip/member/member_list.js?v=${ver !}"></script>
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
                url: rootPath + "/Member/queryData.do",
                columns: [
                    {data: "code", label: '会员编码', class: 'text-nowrap'},
                    {data: "name", label: '会员名称', class: 'text-nowrap'},
                    {data: "wxNickName", label: '昵称', class: 'text-nowrap'},
                    {data: "phone", label: '会员手机', class: 'text-nowrap'},
                    {
                        data: "createDate",
                        label: '注册时间',
                        class: 'text-nowrap',
                        format: {datetime: 'yyyy-MM-dd HH:mm:ss'}
                    },
                    {data:"consumed",label:"是否消费",data_dict: {"1": "是", "0": "否"}}
                ],
                show_checkbox: false,
                gen_permission: function () {
                    var map = [];
                 /*   map.push('edit');*/
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
