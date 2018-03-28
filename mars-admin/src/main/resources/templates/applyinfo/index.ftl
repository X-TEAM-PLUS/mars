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
            <a href="#">申请记录表管理</a>
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
                        <i class="icon-user"></i>申请记录表管理
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
                <div class="portlet-body" id="applyInfoTable">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-10">
                                <div class="btn-group">
                                    <button id="newApplyInfoButton" class="btn green" onclick="location.href = 'add';">
                                        添加申请记录表 <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                            <div class="col-md-2 pull-right">
                                <form id="applyInfoTableForm" onsubmit="init();return false;">
                                    <div class="input-group">
                                        <div class="input-icon">
                                            <i class="icon-magnifier"></i>
                                            <input class="form-control" type="text" name="applyId" placeholder="申请流水号"/>
                                        </div>
                                        <span class="input-group-btn">
												<button class="btn btn-success" type="submit"><i
                                                        class="fa fa-arrow-left fa-fw"/></i> 搜索</button>
												</span>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover ">
                        <thead>
                        <tr>
                            <th column="applyId" type="checkbox" style="width: 50px"><input type="checkbox"
                                                                                            id="allCheckBox"
                                                                                            class="allCheckBox"></th>
                            <th column="created">创建时间</th>
                            <th column="updated">更新时间</th>
                            <th column="applyReason">申请原因</th>
                            <th column="status" type="enum" enum-v="{'-1':'<span class=\'bold badge badge-danger\'>审批未通过</span>','0':'<span class=\'bold badge badge-default\'>未审批</span>','1':'<span class=\'badge badge-success \'>审批通过</span>'}">状态</th>
                            <th column="userInfo.realName">用户姓名</th>
                            <th column="applyId">申请流水号</th>
                            <th column="applyWay" type="enum" enum-v="{'0':'手动申请','1':'任务晋升','2':'绿色通道'}">申请方式</th>
                            <th column="applyType" type="enum" enum-v="{'1':'社工','2':'理事','3':'常任理事'}">申请类型</th>
                            <th type="action" style="width: 200px">操作</th>
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
        $("#applyInfoTable").pagingGrid(
                {
                    dataUrl: '/services/mars/applyinfo/list'
                    , pageSize: 10
                    , scroll: false
                    , dockedItems: [{
                        name: '审批通过'
                        , buttonColor: 'green'
                        , iconClass: 'glyphicon glyphicon-ok'
                        , action: '/services/mars/applyinfo/auditpass'
                        , ajax: true
                        , confirm: true
                        , parmaName: 'applyId'
                        , column: 'applyId'
                        , equalField: 'status'
                        , equalValue: '0'
                    }, {
                        name: '审批驳回'
                        , buttonColor: 'red'
                        , iconClass: 'glyphicon glyphicon-remove'
                        , action: '/services/mars/applyinfo/dismissal'
                        , ajax: true
                        , confirm: true
                        , parmaName: 'applyId'
                        , column: 'applyId'
                        , equalField: 'status'
                        , equalValue: '0'
                    },{
                        name: '绿色通道'
                        , buttonColor: 'green'
                        , iconClass: 'glyphicon glyphicon-leaf'
                        , action: '#'
                        , ajax: true
                        , confirm: true
                        , parmaName: 'applyId'
                        , column: 'applyId'
                        , equalField: 'applyWay'
                        , equalValue: '2'
                        , datatoggle: 'modal'
                        , datatarget: '#draggable1'
                        , onclick: 'greenChannel'
                    }
                    ],
                    pagingtoolbar: {
                        displayInfo: true
                    }
                }
        );
    }

    //初始化
    init();
    var applyId = undefined;
    function greenChannel(id) {
        applyId = id;
    }

    function commitBuyLimit(){
        var buyLimitSurplusCount;
        $("#draggable1").find("input[type=text]").each(function () {
            if ($(this).attr("id") == 'buyLimitSurplusCount')
                buyLimitSurplusCount = $(this).val();
        });
        if (buyLimitSurplusCount == undefined || buyLimitSurplusCount == ""){
            window.wxc.xcConfirm("请输入购卡数!", window.wxc.xcConfirm.typeEnum.error);
        }
        $.ajax({
            url: contextPath + '/services/mars/applyinfo/greenChannel?applyId=' + applyId+"&cardNum="+buyLimitSurplusCount,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                if (data.success != true) {
                    window.wxc.xcConfirm(data.message, window.wxc.xcConfirm.typeEnum.error);
                } else {
                    window.wxc.xcConfirm("更新成功!", window.wxc.xcConfirm.typeEnum.success);
                }
                $('#draggable1').modal('hide');
                init();
            }
        });
    }
</script>

<!-- 抢购库存管理 -->
<div class="modal fade draggable-modal" id="draggable1" tabindex="-2" role="basic" aria-hidden="true">
    <div class="modal-dialog" style="width:900px">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label col-md-3">
                                购买卡数<span class="required">* </span>
                            </label>
                            <div class="input-group select2-bootstrap-prepend col-sm-9">
                                <input type="text" name="buyLimitSurplusCount" class="form-control"
                                       id="buyLimitSurplusCount"
                                       placeholder="购买卡数" style="width: 100%"
                                       validate='{"required": true,"number":true}' maxlength="5">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline sbold red" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-outline sbold blue" onclick="commitBuyLimit()">提交</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
</div>
</body>
</html>


