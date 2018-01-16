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
            <a href="index">问题表管理</a>
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
                    <i class="fa fa-adn"></i>添加问题
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
                <form  class="form-horizontal"  id="questioninfoForm">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                标题<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="input-icon margin-top-10">
                                    <i class="fa   fa-font "></i>
                                    <input type="text"  id="questionTitle" name="questionTitle"  class="form-control" placeholder="标题" validate='{required: true}' maxlength="200">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                回复<span class="required">* </span>
                            </label>
                            <div class="col-md-5">
                                <div class="form-group last margin-top-10">
                                    <input type="hidden" name="questionReply"  validate='{required: true}' >
                                    <div class="custom-toolbar"  ></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions right">
                        <button type="button" class="btn default" onclick="history.go(-1)"><i class="fa fa-rotate-left"></i> 取消</button>
                        <button type="button" class="btn green"  id="submitButton"><i class="fa fa-check"></i> 保存</button>
                    </div>
                </form>
                <!-- END FORM-->
            </div>
        </div>
    </div>
</div>
<@commonMacro.commonScript />
<#--<script src="/static/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>-->
<script type="text/javascript">
    $(function(){
        $('.custom-toolbar').summernote({
            height: 300,
            tabsize: 2,
            lang: 'zh-CN'
            ,  toolbar: [
                // [groupName, [list of button]]
                ['style', ['bold', 'italic', 'underline', 'clear']],
                ['font', ['strikethrough', 'superscript', 'subscript']],
                ['fontsize', ['fontsize']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['height', ['height']]
            ]
        });
    });

    $("#submitButton").click(function () {
        if(!$("#questionTitle").val()){
            $("#questionTitle").focusin();
            window.wxc.xcConfirm("请输入标题", window.wxc.xcConfirm.typeEnum.warning);
            return;
        }
        if ($('.custom-toolbar').summernote('isEmpty')) {
            $('.custom-toolbar').summernote('focus');
            window.wxc.xcConfirm("请输入回复的内容", window.wxc.xcConfirm.typeEnum.warning);
            return;
        }else {
            $("#questionReply").val($('.custom-toolbar').code());
        }
        //提交
        $("#questioninfoForm").ajaxSubmitForm({
            url: '/services/mars/questioninfo/post'
            ,success:function (result) {
                window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.info);
                history.go(-1);
            }
            ,error:function (result) {
                window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.error);
            }
        });

    })

</script>

</body>
</html>


