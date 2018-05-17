<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<#import '/common/common.ftl' as commonMacro>
<@commonMacro.commonStyle />
    <link href="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css"
          rel="stylesheet" type="text/css"/>
    <link href="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css"
          rel="stylesheet" type="text/css"/>
</head>
<body class="page-content">
<!-- BEGIN PAGE HEADER-->
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a href="/welcome">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <i class="fa fa-cube"></i>
            <a href="#">导入导出</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <i class="fa fa-cube"></i>
            <a href="#">保险数据导入</a>
        </li>

    </ul>
</div>

<div class="page-content">
    <!-- END PAGE HEADER-->
    <!-- BEGIN PAGE CONTENT-->
    <div class="row">
        <div class="col-md-12">
            <!-- BEGIN EXAMPLE TABLE PORTLET-->
            <div class="portlet box grey-cascade">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="fa fa-cube"></i>保险数据导入
                    </div>
                    <div class="tools">
                        <a href="javascript:;" class="collapse">
                        </a>
                        <a href="#portlet-config" data-toggle="modal" class="config">
                        </a>
                        <a href="javascript:;" class="reload" onclick="init()">
                        </a>
                    </div>
                </div>
                <div class="portlet-body" id="ordersTable">
                    <div class="table-toolbar">
                        <div class="row">
                            <form id="ordersTableForm" name="ordersTableForm" class="form-horizontal"
                                  onsubmit="init();return false;">
                                <div class="input-icon date-picker input-daterange col-md-2 "
                                     data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="acceptBeginTime" class="form-control " readonly=""
                                           placeholder="承保开始日期">
                                </div>
                                <div class="input-icon date-picker input-daterange col-md-2 "
                                     data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="acceptEndTime" class="form-control " readonly=""
                                           placeholder="承保截止日期">
                                </div>

                                <div class="col-md-1">
                                    <div class="form-group">
                                        <span class="input-group-btn">
                                            <a class="btn btn-success" data-toggle="modal" href="#uploadDialog"> <i
                                                    class="glyphicon glyphicon-open"/></i> 导入Excel </a>
												</span>
                                    </div>

                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <div class="input-icon col-md-12">
                                            <button class="btn btn-success" type="button" onclick="search()"><i
                                                    class="glyphicon glyphicon-search"/></i> 搜索
                                            </button>
                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>

                    <table class="table table-striped table-bordered table-hover ">
                        <thead>
                        <tr>
                            <th column="cardNo">卡号</th>
                            <th column="insuranceCompany.linkMan">保险公司联系人</th>
                            <th column="holderIdNumber">投保人身份证号</th>
                            <th column="insuranceProduct.insuranceName">保险产品名称</th>
                            <th column="policyHolder">投保人</th>
                            <th column="address">地址</th>
                            <th column="contractNo">保单号</th>
                            <th column="acceptInsuranceDate">生效日期</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <div class="table-toolbar pagination-toolbar">
                    </div>
                </div>
            </div>
            <!-- END EXAMPLE TABLE PORTLET-->
        </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="uploadDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="categoryModalTitle">导入保险Excel</h4>
            </div>

            <div class="modal-body">
                <!-- 添加类目body-->
                <!-- BEGIN FORM-->
                <form class="form-horizontal" id="uploadDeliveredForm" name="uploadDeliveredForm"
                      onsubmit="return false;">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">请选择保险产品</label>
                            <div class="col-md-7" id="logoDiv">

                                    <select id="insuranceProductNo" name="insuranceProductNo"
                                            class="form-control select2"
                                            validate="{required: true}">
                                        <option value="" selected>请选择</option>
                                    </select>

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">保险Excel文件:</label>
                            <div class="col-md-7" id="logoDiv">
                                <div class="file-loading" id="initDiv">
                                    <input id="uploadFile" class="file" name="uploadFile" type="file"
                                           data-min-file-count="1">
                                </div>
                            </div>
                        </div>


                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
                <button type="button" class="btn btn-primary" id="modalFormButton" onclick="uploadImport()">导入数据
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<@commonMacro.commonScript />

<script>
    function init() {

        // 品牌
        $.ajax({
            url: contextPath + '/services/mars/insuranceproduct/list?start=0&limit=90000',
            type: 'get',
            dataType: 'json',
            success: function (data) {
                if (data.success != true) {
                    alert("系统错误，获取品牌列表失败!");
                    return false;
                }
                var select2 = document.getElementById("insuranceProductNo");
                for (var i = 0; i < data.list.length; i++) {
                    var insuranceName = data.list[i].insuranceName;
                    var insuranceProductNo = data.list[i].insuranceProductNo;
                    select2.options.add(new Option(insuranceName, insuranceProductNo));
                }
            }
        });

        $("#ordersTable").pagingGrid(
                {
                    dataUrl: '/services/mars/userinsurance/listForProuct'
                    , pageSize: 10
                    , scroll: false
                    , dockedItems: [{
                        name: '修改'
                        , iconClass: 'fa fa-edit'
                        , action: '/mars/orders/edit'
                        , confirm: false
                        , parmaName: 'orderNo'
                        , column: 'orderNo'
                    }
                        , {
                            name: '删除'
                            , iconClass: 'glyphicon glyphicon-trash'
                            , action: '/services/mars/orders/delete'
                            , ajax: true
                            , confirm: true
                            , parmaName: 'orderNo'
                            , column: 'orderNo'
                        }
                    ]
                    , pagingtoolbar: {
                        displayInfo: true
                    }
                }
        );
    }

    function search() {
        $("#ordersTable").pagingGrid(
                {
                    dataUrl: '/services/mars/userinsurance/listForProuct'
                    , pageSize: 10
                    , scroll: false
                    , dockedItems: [{
                        name: '修改'
                        , iconClass: 'fa fa-edit'
                        , action: '/mars/orders/edit'
                        , confirm: false
                        , parmaName: 'orderNo'
                        , column: 'orderNo'
                    }
                        , {
                            name: '删除'
                            , iconClass: 'glyphicon glyphicon-trash'
                            , action: '/services/mars/orders/delete'
                            , ajax: true
                            , confirm: true
                            , parmaName: 'orderNo'
                            , column: 'orderNo'
                        }
                    ]
                    , pagingtoolbar: {
                        displayInfo: true
                    }
                }
        );
    }

    function uploadImport() {
        var oMyForm = new FormData();
        if ($('#insuranceProductNo').val() == "" || $('#insuranceProductNo').val() == undefined) {
            alert("请选择保险公司后进行导入!")
            return false;
        }
        oMyForm.append("uploadFile", $('#uploadFile')[0].files[0]);
        oMyForm.append("insuranceProductNo", $('#insuranceProductNo').val());
        $('body').append('<div id="layer"></div>');
        $.ajax({
            url: "/services/mars/policyinfo/uploadImport",
            type: "POST",
            async: false,
            contentType: false,    //这个一定要写
            processData: false, //这个也一定要写，不然会报错
            data: oMyForm,
            success: function (data) {
                alert(data.message);
                $('#layer').remove();
                init();
            },
            error: function (data) {
                $('#serverResponse').html(data.status + " : " + data.statusText + " : " + data.responseText);
                $('#layer').remove();
                init();
            }
        });
    }

    function goBack() {
        location.href = "/mars/datacenter/importPolicy";
    }

    //初始化
    init();

</script>

</body>
</html>