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
            <a href="index">发货信息表管理</a>
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
                    <i class="fa fa-adn"></i>编辑发货信息表
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
                <form action="/services/mars/deliveredinfo/put" class="form-horizontal" id="deliveredinfoForm">
                    <input type="hidden" name="deliveredId" id="deliveredId">

                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                下一支试剂发货日期
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="nextDate" class="form-control" readonly="" validate='{required: false}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                运单号
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="waybillNo" class="form-control" readonly="" validate='{required: false}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                发货流水号<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="deliveredId" class="form-control" placeholder="发货流水号" validate='{required: true}'
                                    >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                发件日期
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="deliveredDate" class="form-control" readonly="" validate='{required: false}'>
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
                                邮寄地址<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="address" class="form-control" placeholder="邮寄地址" validate='{required: true}'
                                           maxlength="200">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                发货次数<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="deliveredCount" class="form-control" placeholder="发货次数" validate='{required: true,digits: true}'
                                    >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                快递费
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa  fa-rmb "></i>
                                    <input type="text" name="deliveredFee" class="form-control" placeholder="快递费" validate='{required: false}'
                                           maxlength="11">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                上一支试剂发货日期
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10 date-picker input-daterange " data-date-format="yyyy-mm-dd">
                                    <i class="fa fa-calendar "></i>
                                    <input type="text" name="previousDate" class="form-control" readonly="" validate='{required: false}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                省<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="province" class="form-control" placeholder="省" validate='{required: true}'
                                           maxlength="50">
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
                                市<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="city" class="form-control" placeholder="市" validate='{required: true}'
                                           maxlength="50">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                区<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="area" class="form-control" placeholder="区" validate='{required: true}'
                                           maxlength="50">
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
        $("#deliveredinfoForm").ajaxLoadFormData("/services/mars/deliveredinfo/get?deliveredId=" + getParameter("deliveredId"));
    })(jQuery);
</script>
</body>
</html>


