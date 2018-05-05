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
            <a href="#">卡密表管理</a>
            <i class="fa fa-cube"></i>
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
                        <i class="icon-user"></i>卡密表管理
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
                <div class="portlet-body" id="cardKeysTable">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-12">
                                <form id="cardKeysTableForm" onsubmit="init();return false;">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <button id="newCardKeysButton" class="btn green" onclick="location.href = 'add';">
                                                批量生成卡密 <i class="fa fa-plus"></i>
                                            </button>
                                        </div>
                                        <div class="input-icon date-picker input-daterange col-md-2 " data-date-format="yyyy-mm-dd">
                                            <i class="fa fa-calendar "></i>
                                            <input type="text" name="created" class="form-control " readonly="" placeholder="生成时间">
                                        </div>
                                        <div class="input-group">
                                            <button class="btn btn-success" type="submit"><i class="fa fa-arrow-left fa-fw"></i> 搜索</button>
                                        </div>
                                        <div class="input-group-btn">
                                            <button class="btn btn-success" type="button" id="downloadButton"><i class="glyphicon glyphicon-save"></i> 导出</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover ">
                        <thead>
                        <tr>
                            <th column="id" type="checkbox" style="width: 50px"><input type="checkbox" id="allCheckBox" class="allCheckBox"></th>
                            <th column="id">流水号</th>
                            <th column="cardKeys">卡密</th>
                            <th column="status" type="enum" enum-v="{'0':'<span class=\'bold badge badge-default\'>未上线</span>',1:'<span class=\'bold badge badge-info\'>已上线</span>',2:'<span class=\'bold badge badge-success\'>已激活</span>'}">状态</th>
                            <th column="activateUserId">激活用户ID</th>
                            <th column="activateTime">激活日期</th>
                            <th column="created">创建时间</th>
                            <th column="updated">更新时间</th>
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
        $("#cardKeysTable").pagingGrid(
                {
                    dataUrl: '/services/mars/cardkeys/list'
                    , pageSize: 10
                    , scroll: false
                    ,pagingtoolbar: {
                        displayInfo: true
                    }
                }
        );
    }

    //初始化
    init();

    //下载
    $("#downloadButton").click(function () {
        var downloadUrl = contextPath+'/services/mars/cardkeys/export';
        var created =$("input[name$='created']").val();
        if(created){
            var params = "created="+created ;
            $.download(downloadUrl,params,'post' );
        }else{
            window.wxc.xcConfirm("导出数据时，必须选择生成日期", window.wxc.xcConfirm.typeEnum.info);
        }
    });

</script>

</body>
</html>


