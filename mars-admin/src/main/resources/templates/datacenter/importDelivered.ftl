<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<#import '/common/common.ftl' as commonMacro>
<@commonMacro.commonStyle />
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
            <a href="#">发货信息导出</a>
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
                        <i class="fa fa-cube"></i>发货信息导入
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
                <div class="portlet-body" id="deliveredTable">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-12 pull-right">
                                <form id="ordersTableForm" onsubmit="init();return false;">
                                    <div class="input-group">
                                        <input type="hidden" name="status" value="1">
                                        <div class="input-icon date-picker input-daterange col-md-2 " data-date-format="yyyy-mm-dd">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="startDate" class="form-control " readonly="" placeholder="发货开始日期">
                                        </div>
                                        <div class="input-icon date-picker input-daterange col-md-2 " data-date-format="yyyy-mm-dd">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="endDate" class="form-control " readonly="" placeholder="发货截止日期">
                                        </div>
                                        <div class="input-icon col-md-1" >
                                            <button class="btn btn-success" type="submit"><i class="glyphicon glyphicon-search"/></i> 搜索</button>
                                        </div>
                                        <span class="input-group-btn">
                                            <a class="btn btn-success" data-toggle="modal" href="#uploadDialog"> <i class="glyphicon glyphicon-open"/></i> 导入Excel </a>
												</span>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover ">
                        <thead>
                        <tr>
                            <th column="userHealthCard.cardNo">会员卡号</th>
                            <th column="userInfo.realName">姓名</th>
                            <th column="userInfo.mobileNo">手机号</th>
                            <th column="userInfo.provinceName">省</th>
                            <th column="userInfo.cityName">市</th>
                            <th column="userInfo.countyName">区县</th>
                            <th column="userInfo.linkAddress">联系地址</th>
                            <th column="userHealthCard.cardActivateTime">激活日期</th>
                            <th column="userHealthCard.cardDeadline">有效期</th>
                            <th column="userHealthCard.sendCount">发货次数</th>
                            <th column="deliveredFee">快递费</th>
                            <th column="waybillNo">快递单号</th>
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

    <!-- 模态框（Modal） -->
    <div class="modal fade" id="uploadDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="categoryModalTitle">导入发货Excel</h4>
                </div>
                <div class="modal-body">
                    <!-- 添加类目body-->
                    <!-- BEGIN FORM-->
                    <form class="form-horizontal" id="modalForm" onsubmit="return false;">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">发货Excel文件:</label>
                                <div class="col-md-7" id="logoDiv">
                                    <div class="file-loading" id="initDiv">
                                        <input id="mainImgFile" class="file" name="logoImg" type="file" data-min-file-count="1">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
                    <button type="button" class="btn btn-primary" id="modalFormButton">导入数据</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</div>
<@commonMacro.commonScript />
<script>
    function init() {
        $("#deliveredTable").pagingGrid(
                {
                    dataUrl: '/services/mars/deliveredinfo/list'
                    , pageSize: 10
                    , scroll: false
                    ,  pagingtoolbar: {
                        displayInfo: true
                    }
                }
        );
    }
    //初始化
    init();
    //下载
    $("#downloadButton").click(function () {
        var downloadUrl = contextPath+'/services/mars/deliveredinfo/export';
        var startDate =$("input[name$='startDate']").val();
        var endDate = $("input[name$='endDate']").val();
        var params = "startDate="+startDate +"&endDate="+endDate ;
        $.download(downloadUrl,params,'post' );
    });

</script>

</body>
</html>


