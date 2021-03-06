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
            <a href="index">保险信息表管理</a>
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
                    <i class="fa fa-adn"></i>编辑保险信息表
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
                <form action="/services/mars/insuranceproduct/put" class="form-horizontal" id="insuranceproductForm">
                    <input type="hidden" name="insuranceProductNo" id="insuranceProductNo">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                保险名称<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa  fa-user "></i>
                                    <input type="text" name="insuranceName" class="form-control" placeholder="保险名称" validate='{required: true}' maxlength="128">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                保险公司<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <select id="insuranceCompanyId" name="insuranceCompanyId" class="form-control selectpicker" data-live-search="false" placeholder="保险公司" validate='{required: true,digits: true}'>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                价格<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa  fa-rmb "></i>
                                    <input type="text" name="price" class="form-control" placeholder="价格" validate='{required: true}' maxlength="11">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                有效期类型<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <select id="periodsMode" name="periodsMode" class="form-control selectpicker" data-live-search="false" placeholder="有效期类型" validate='{required: true,digits: true}'>
                                        <option value="0"  >月</option>
                                        <option value="1">年</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                有效期期数<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="periodsNum" class="form-control" placeholder="有效期期数" validate='{required: true,digits: true}'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                展示图
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text" name="imgInfo" class="form-control" placeholder="展示图" validate='{required: false}' maxlength="128">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                状态<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <select id="status" name="status" class="form-control selectpicker" data-live-search="false" placeholder="状态" validate='{required: true,digits: true}'>
                                        <option value="0"  >停用</option>
                                        <option value="1">启用</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                详情
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa fa-comment "></i>
                                    <input type="text" name="remark" class="form-control" placeholder="详情" validate='{required: false}'>
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
        //加载保险公司列表
        $("#insuranceCompanyId").initSelectList(
                "/services/mars/insurancecompany/list"
                ,{limit:9999999}
                ,{keyName:"insuranceCompanyName",valueName:"insuranceCompanyId"}
        );
        //加载表单
        $("#insuranceproductForm").ajaxLoadFormData("/services/mars/insuranceproduct/get?insuranceProductNo=" + getParameter("insuranceProductNo"));
    })(jQuery);
</script>
</body>
</html>


