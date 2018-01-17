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
            <a href="index">用户基本信息表管理</a>
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
                    <i class="fa fa-adn"></i>编辑用户基本信息表
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
                <form action="/services/mars/userinfo/put" class="form-horizontal" id="userinfoForm">
                    <input type="hidden" name="userId" id="userId">

                    <div class="form-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        租期<span class="required">* </span>
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="skuInfoDto.leaseTerm" class="form-control"
                                               id="skuInfoDto.leaseTerm"
                                               placeholder="租期" style="width: 100%"
                                               validate='{"required": true,"number":true}' maxlength="3">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        租期<span class="required">* </span>
                                    </label>
                                    <div class="input-group select2-bootstrap-prepend col-sm-9">
                                        <input type="text" name="skuInfoDto.leaseTerm" class="form-control"
                                               id="skuInfoDto.leaseTerm"
                                               placeholder="租期" style="width: 100%"
                                               validate='{"required": true,"number":true}' maxlength="3">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions right">
                        <button type="button" class="btn default" onclick="history.go(-1)"><i
                                class="fa fa-rotate-left"></i> 取消
                        </button>
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
        $("#userinfoForm").ajaxLoadFormData("/services/mars/userinfo/get?userId=" + getParameter("userId"));
    })(jQuery);
</script>
</body>
</html>


