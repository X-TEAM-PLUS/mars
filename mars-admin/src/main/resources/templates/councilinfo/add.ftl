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
                    <i class="fa fa-adn"></i>添加地方理事会信息表
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
                <form action="/services/mars/councilinfo/post" class="form-horizontal">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                城市编号<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <select class="area" id="cityNo" name="cityNo"
                                        style="width: 100%">
                                </select>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                添加成员
                            </label>
                            <div class="col-md-5">

                                <select class="userInfo" id="userInfo" name="userInfo" multiple="multiple"
                                        style="width: 100%">
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                选择职务
                            </label>
                            <div class="col-md-5">

                                <select class="jobType" id="jobType" name="jobType"
                                        style="width: 100%">
                                    <option value="NOT_JOB">成员</option>
                                    <option value="DEPUTY_DIRECTOR_GENERAL">副理事长</option>
                                    <option value="ADMINISTARATION">行政</option>
                                </select>
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

<script>

    $(".jobType").select2({minimumResultsForSearch: Infinity});
    $(".userInfo").select2({
        theme: "bootstrap",
        allowClear: true,
        placeholder: "增加成员",
        ajax: {
            url: "/services/mars/userinfo/list?start=0&limit=50",
            dataType: "json",
            delay: 250,
            data: function (params) {
                return {
                    realName: params.term
                };
            },
            cache: true,
            processResults: function (res, params) {
                var options = [];
                for (var i = 0; i < res.list.length; i++) {
                    var option = {"id": res.list[i].userId, "text": res.list[i].realName};
                    options.push(option);
                }
                return {
                    results: options,
                    pagination: {}
                };
            },
            escapeMarkup: function (markup) {
                return markup;
            },
            minimumInputLength: 1
        }
    });

    $(".area").select2({
        theme: "bootstrap",
        allowClear: true,
        placeholder: "选择地市",
        ajax: {
            url: "/services/mars/cityinfo/list?start=0&limit=50&areaType=1",
            dataType: "json",
            delay: 250,
            data: function (params) {
                return {
                    name: params.term
                };
            },
            cache: true,
            processResults: function (res, params) {
                var options = [];
                for (var i = 0; i < res.list.length; i++) {
                    var option = {"id": res.list[i].code, "text": res.list[i].name};
                    options.push(option);
                }
                return {
                    results: options,
                    pagination: {}
                };
            },
            escapeMarkup: function (markup) {
                return markup;
            },
            minimumInputLength: 1
        }
    });

</script>
</body>
</html>


