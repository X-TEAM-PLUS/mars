<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<#import '/common/common.ftl' as commonMacro>
<@commonMacro.commonStyle />
    <@commonMacro.commonScript />
</head>
<body class="page-content">

<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <i class="fa fa-home"></i>
            <a href="/welcome">首页</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li>
            <i class="fa fa-cube"></i>
            <a href="index">地方理事会信息表管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li class=" active">
            <i class="fa fa-plus "></i>
            <a href="#">添加</a>
        </li>
    </ul>
</div>
<div class="page-content">
    <div class="tab-pane active" id="tab_0">
        <div class="portlet box grey-cascade">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-adn"></i>查看用户检测历史
                </div>
                <div class="tools">
                    <a href="javascript:;" class="collapse">
                    </a>
                    <a href="#portlet-config" data-toggle="modal" class="config">
                    </a>
                    <a href="javascript:;" class="reload">
                    </a>
                    <a href="javascript:;" class="remove" data-original-title="" title="" onclick="history.go(-1)">
                    </a>
                </div>
            </div>
            <div class="portlet-body form">
                <!-- BEGIN FORM-->
                <form action="/services/mars/councilinfo/post" id="userHealthcardForm" class="form-horizontal">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                用户ID<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control" name="activateUserInfo.userId" id="activateUserInfo.userId" value="" readonly>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                用户姓名<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control" name="activateUserInfo.realName" id="activateUserInfo.realName" value="" readonly>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                会员开通日期<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control" name="activateUserInfo.cardActivateTime" id="activateUserInfo.cardActivateTime"
                                       value="" readonly>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                会员有效期<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control" name="activateUserInfo.cardLifeTime" id="activateUserInfo.cardLifeTime" value=""
                                       readonly>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                体检次数<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control" name="sendTotalCount" id="sendTotalCount" value=""
                                       readonly>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                已体检次数<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control" name="sendCount" id="sendCount" value=""
                                       readonly>
                            </div>
                        </div>
                    </div>
                </form>

                <form name="form1">
                    <div class="portlet-body" id="userInfoTable">
                        <table class="table table-striped table-bordered table-hover ">
                            <thead>
                            <tr>
                                <th column="uploadTime">上传时间</th>
                                <th column="checkReport">体检报告</th>
                                <th column="checkResult">检测结果</th>
                                <th column="checkStatus">检测状态</th>
                                <th column="checkTime">检测时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <div class="table-toolbar pagination-toolbar">
                        </div>
                    </div>
                    <div class="form-actions right">
                        <button type="button" class="btn default" onclick="history.go(-1)"><i
                                class="fa fa-rotate-left"></i> 取消
                        </button>
                    </div>
                </form>
                <!-- END FORM-->
            </div>
        </div>
    </div>
</div>

<script>
    function init() {
        $("#userInfoTable").pagingGrid(
                {
                    dataUrl: '/services/mars/userhealthcard/getActiveUser?cardNo=' + getParameter("cardNo")
                    , pageSize: 10
                    , scroll: false
                    , dockedItems: [],
                    pagingtoolbar: {
                        displayInfo: true
                    }
                }
        );
    }

    (function ($) {
        $("#userHealthcardForm").ajaxLoadFormData("/services/mars/userhealthcard/get?cardNo=" + getParameter("cardNo"));
    })(jQuery);
    //初始化
    init();
</script>
</body>
</html>


