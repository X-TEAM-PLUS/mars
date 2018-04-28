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
                            <div class="col-md-12 pull-right">
                                <form id="ordersTableForm" name="ordersTableForm" class="form-horizontal"  onsubmit="init();return false;">
                                    <div class="input-group">
                                        <div class="input-icon col-md-2">
                                            <select id="cardType" name="status" class="form-control selectpicker"
                                                    data-live-search="false" placeholder="订单状态">
                                                <option value="">全部</option>
                                                <option value="0">已失效</option>
                                                <option value="0">未支付</option>
                                                <option value="1">已支付</option>
                                            </select>
                                        </div>
                                        <div class="input-icon date-picker input-daterange col-md-2 "
                                             data-date-format="yyyy-mm-dd">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="payTime" class="form-control " readonly=""
                                                   placeholder="支付开始日期">
                                        </div>
                                        <div class="input-icon date-picker input-daterange col-md-2 "
                                             data-date-format="yyyy-mm-dd">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="payTime" class="form-control " readonly=""
                                                   placeholder="支付截止日期">
                                        </div>
                                        <div class="input-icon col-md-1">
                                            <button class="btn btn-success" type="submit"><i
                                                    class="glyphicon glyphicon-search"/></i> 搜索
                                            </button>
                                        </div>
                                        <div class="input-icon col-md-1">
                                            <button class="btn btn-success" type="button"><i
                                                    class="glyphicon glyphicon-search" onclick="goImport()"/></i> 导入
                                            </button>
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover ">
                        <thead>
                        <tr>
                            <th column="cardNo">会员卡号</th>
                            <th column="realName">姓名</th>
                            <th column="idNumber">身份证号</th>
                            <th column="mobileNo">手机号</th>
                            <th column="linkAddress">地址</th>
                            <th column="policyNo">保单号</th>
                            <th column="effectiveDate">生效日期</th>
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
<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/js/vendor/tmpl.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/js/vendor/load-image.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/js/vendor/canvas-to-blob.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/js/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-process.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-image.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-audio.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-video.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-validate.js" type="text/javascript"></script>
<#--<script src="${request.contextPath}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-ui.js"/>-->
<script>
    function init() {
        $("#ordersTable").pagingGrid(
                {
                    dataUrl: '/services/mars/orders/list'
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
                    ],
                    pagingtoolbar: {
                        displayInfo: true
                    }
                }
        );
    }

    function uploadImport(){
        alert("upload");
        var oMyForm = new FormData();
        oMyForm.append("uploadFile", $('#uploadFile')[0].files[0])
        $('body').append('<div id="layer"></div>');
        $.ajax({
            url : "/services/mars/policyinfo/uploadImport",
            type : "POST",
            async:false,
            contentType: false,    //这个一定要写
            processData: false, //这个也一定要写，不然会报错
            data : oMyForm,
            success : function(data) {
                alert(data.message);
                $('#layer').remove();
            },
            error : function(data) {
                $( '#serverResponse').html(data.status + " : " + data.statusText + " : " + data.responseText);
                $('#layer').remove()
            }
        });
    }
    function goImport() {
        location.href='/mars/datacenter/importInsuranceUser';
    }
    //初始化
    init();

</script>

</body>
</html>


