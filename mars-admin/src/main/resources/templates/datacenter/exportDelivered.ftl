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
                        <i class="fa fa-cube"></i>发货信息导出
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
                                <form id="ordersTableForm" onsubmit="init();return false;">
                                    <div class="input-group">
                                        <div class="input-icon col-md-2">
                                            <select id="cardType" name="status" class="form-control selectpicker" data-live-search="false" placeholder="订单状态" >
                                                <option value="" >全部</option>
                                                <option value="0"  >已失效</option>
                                                <option value="0"  >未支付</option>
                                                <option value="1">已支付</option>
                                            </select>
                                        </div>
                                        <div class="input-icon date-picker input-daterange col-md-2 " data-date-format="yyyy-mm-dd">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="payTime" class="form-control " readonly="" placeholder="支付开始日期">
                                        </div>
                                        <div class="input-icon date-picker input-daterange col-md-2 " data-date-format="yyyy-mm-dd">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="payTime" class="form-control " readonly="" placeholder="支付截止日期">
                                        </div>
                                        <div class="input-icon col-md-1" >
                                            <button class="btn btn-success" type="submit"><i class="glyphicon glyphicon-search"/></i> 搜索</button>
                                        </div>
                                        <span class="input-group-btn">
												<button class="btn btn-success" type="button"  id="downloadButton"><i class="glyphicon glyphicon-save"/></i> 导出Excel</button>
												</span>
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
                            <th column="mobileNo">手机号</th>
                            <th column="provinceName">省</th>
                            <th column="cityName">市</th>
                            <th column="countyName">区县</th>
                            <th column="linkAddress">联系地址</th>
                            <th column="cardActivateTime">激活日期</th>
                            <th column="cardLifeTime">有效期</th>
                            <th column="deliveredCount">发货次数</th>
                            <th column="freight">快递费</th>
                            <th column="previousDate">上一支试剂发货日期</th>
                            <th column="nextDate">下一支试剂发货日期</th>
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
        $("#ordersTable").pagingGrid(
                {
                    dataUrl: '/services/mars/orders/list'
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
        var downloadUrl = contextPath+'/bestlease/policyinfo/export';
        var signBeginDate =$("input[name$='signBeginDate']").val();
        var signEndDate = $("input[name$='endDate']").val();
        var params = "signBeginDate="+signBeginDate +"&signEndDate="+signEndDate ;
        $.download(downloadUrl,params,'post' );
    });

</script>


</body>
</html>


