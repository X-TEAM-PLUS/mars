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
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <div class="input-group select2-bootstrap-prepend">
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" type="button"
                                                        data-select2-open="single-prepend-text">
                                                    <span class="glyphicon glyphicon-search"></span>
                                                </button>
                                            </span>
                                            <select id="insuranceProductNo" name="insuranceProductNo"
                                                    class="form-control select2"
                                                    validate="{required: true}">
                                                <option value="" selected>请选择</option>
                                            </select>
                                        </div>

                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <div class="input-icon col-md-1">
                                        <span class="btn btn-success fileinput-button">
                                            <i class="fa fa-plus"></i>
                                            <span>保险导入</span>
                                            <input name="uploadFile" id="uploadFile" type="file"
                                                   onchange="uploadImport()"> </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <div class="input-icon col-md-1">

                                            <button class="btn btn-success" type="button"><i
                                                    class="glyphicon glyphicon-search" onclick="goBack()"/></i> 返回
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
                            <th column="insuranceCompany.linkMan">保险公司联系人</th>
                            <th column="holderIdNumber">投保人身份证号</th>
                            <th column="insuranceProduct.insuranceName">保险公司名称</th>
                            <th column="policyHolder">投保人</th>
                            <th column="linkAddress">地址</th>
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

    function uploadImport() {
        var oMyForm = new FormData();
        alert($('#insuranceProductNo').val());
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
            },
            error: function (data) {
                $('#serverResponse').html(data.status + " : " + data.statusText + " : " + data.responseText);
                $('#layer').remove()
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