<#macro commonStyle>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta charset="utf-8"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>mars-后台管理</title>
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${request.contextPath}/static/assets/global/plugins/googleapis/googleapis.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/css/zyupload-1.0.0.css" rel="stylesheet" type="text/css"/>
<!--标签插件-->
<link href="${request.contextPath}/static/assets/global/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet" type="text/css"/>
<!--文件上传-->
<link href="${request.contextPath}/static/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN xcConfirm STYLES -->
<link rel="stylesheet" href="${request.contextPath}/static/assets/global/plugins/xcConfirm/css/xcConfirm.css">
<!-- END xcConfirm STYLES -->

<!-- BEGIN THEME LAYOUT STYLES -->
<link href="${request.contextPath}/static/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/layouts/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${request.contextPath}/static/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${request.contextPath}/static/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/admin/pages/css/profile.css" rel="stylesheet" type="text/css"/>
<!-- END THEME LAYOUT STYLES -->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<link href="${request.contextPath}/static/assets/global/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/clockface/css/clockface.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/jquery-multi-select/css/multi-select.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/icheck/skins/all.css" rel="stylesheet" type="text/css"/>
<link href="${request.contextPath}/static/assets/global/plugins/icheck/skins/square/square.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGINS -->
<link rel="stylesheet" href="${request.contextPath}/static/js/treeTable/stylesheets/jquery.treetable.css"/>
<link rel="stylesheet" href="${request.contextPath}/static/js/treeTable/stylesheets/jquery.treetable.theme.default.css"/>
<link rel="stylesheet" href="${request.contextPath}/static/assets/global/css/fileinput.css"/>

<!-- BEGIN PAGE LEVEL PLUGINS -->
<link href="${request.contextPath}/static/assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css" rel="stylesheet" type="text/css" />
<link href="${request.contextPath}/static/assets/global/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css" rel="stylesheet" type="text/css" />
<link href="${request.contextPath}/static/assets/global/plugins/bootstrap-summernote/summernote.css" rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL PLUGINS -->
</#macro>
<#macro  commonScript>
<script>
    var contextPath = "${request.contextPath}";
</script>
<!-- BEGIN CORE PLUGINS -->
<script src="${request.contextPath}/static/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!--标签插件-->
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-tagsinput/bootstrap-tagsinput.js" type="text/javascript"></script>
<!--文件上传-->
<!--tree-->
<link rel="stylesheet" href="${request.contextPath}/static/assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.min.css"/>
<link rel="stylesheet" href="${request.contextPath}/static/assets/global/plugins/jstree/dist/themes/default/style.css"/>
<script src="${request.contextPath}/static/assets/global/plugins/jstree/dist/jstree.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/common.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->

<!--UUID-->
<script src="${request.contextPath}/static/js/UUID.js" type="text/javascript"></script>

<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="${request.contextPath}/static/assets/global/scripts/app.min.js" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="${request.contextPath}/static/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
<!-- END THEME LAYOUT SCRIPTS -->

<!--datepicker -->
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${request.contextPath}/static/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<!-- BEGIN xcConfirm PLUGINS-->
<script src="${request.contextPath}/static/assets/global/plugins/xcConfirm/js/xcConfirm.js"></script>
<!-- END xcConfirm PLUGINS-->
<!-- validate -->
<script type="text/javascript" src="${request.contextPath}/static/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/assets/global/plugins/jquery-validation/js/localization/messages_zh.min.js"></script>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-select/i18n/defaults-zh_CN.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/pages/scripts/components-bootstrap-select.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/pages/scripts/components-bootstrap-switch.min.js" type="text/javascript"></script>

<script src="${request.contextPath}/static/assets/global/plugins/moment.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/clockface/js/clockface.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/pages/scripts/components-date-time-pickers.js" type="text/javascript"></script>

<script src="${request.contextPath}/static/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/pages/scripts/components-multi-select.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/perfect-load/PerfectLoad.js"></script>
<script src="${request.contextPath}/static/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/table.head.fixer/tableHeadFixer.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script src="${request.contextPath}/static/js/AppForm.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/icheck/icheck.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/pages/scripts/form-icheck.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/Map.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/PagingGrid.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/treeTable/javascripts/src/jquery.treetable.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/TableTree.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/DownloadUtils.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-fileinput/sortable.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-fileinput/fileinput.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-fileinput/locales/fr.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-fileinput/locales/es.js" type="text/javascript"></script>

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-markdown/lib/markdown.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-markdown/js/bootstrap-markdown.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-summernote/summernote.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/assets/global/plugins/bootstrap-summernote/lang/summernote-zh-CN.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->

</#macro>
