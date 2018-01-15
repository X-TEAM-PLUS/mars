<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<#import '/common/common.ftl' as commonMacro>
<@commonMacro.commonStyle />
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
            <a href="index">用户保险表管理</a>
            <i class="fa fa-angle-right"></i>
        </li>
        <li class=" active">
            <i class="fa fa-edit "></i>
            <a href="#">编辑</a>
        </li>
    </ul>
</div>
<div class="page-content">
    <div class="tab-pane active" id="tab_0">
        <div class="portlet box grey-cascade">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-adn"></i>编辑用户保险表
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
                <form action="/services/mars/userinsurance/put" class="form-horizontal" id="userinsuranceForm">
                    <input type="hidden" name="insuranceOrderId" id="insuranceOrderId">

                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                截止日期
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="expirationDate" class="form-control" readonly="" validate='{required: false}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                状态<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="status" class="form-control" placeholder="状态" validate='{required: true,digits: true}'
                                    >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                创建时间<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="created" class="form-control" readonly="" validate='{required: true}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                更新时间
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="updated" class="form-control" readonly="" validate='{required: false}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                保险合同号
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="contractNo" class="form-control" placeholder="保险合同号" validate='{required: false}'
                                           maxlength="20">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                投保人身份证号
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="holderIdNumber" class="form-control" placeholder="投保人身份证号" validate='{required: false}'
                                           maxlength="18">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                投保人
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="policyHolder" class="form-control" placeholder="投保人" validate='{required: false}'
                                           maxlength="20">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                承保日期
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="acceptInsuranceDate" class="form-control" readonly="" validate='{required: false}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                用户保单流水号<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="insuranceOrderId" class="form-control" placeholder="用户保单流水号" validate='{required: true}'
                                    >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                保险产品编号<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="insuranceProductNo" class="form-control" placeholder="保险产品编号" validate='{required: true}'
                                    >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                用户ID<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa  fa-user-circle "></i>
                                    <input type="text" name="userId" class="form-control" placeholder="用户ID" validate='{required: true}'
                                    >
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions right">
                        <button type="button" class="btn default" onclick="history.go(-1)"><i class="fa fa-rotate-left"></i> 取消</button>
                        <button type="submit" class="btn green"><i class="fa fa-check"></i> 保存</button>
                    </div>
                </form>
                <!-- END FORM-->
            </div>
        </div>
    </div>
</div>
<@commonMacro.commonScript />
<script>
    (function ($) {
        $("#userinsuranceForm").ajaxLoadFormData("/services/mars/userinsurance/get?insuranceOrderId=" + getParameter("insuranceOrderId"));
    })(jQuery);
</script>
</body>
</html>


