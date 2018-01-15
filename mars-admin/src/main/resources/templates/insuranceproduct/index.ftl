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
            <a href="#">保险信息表管理</a>
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
                        <i class="icon-user"></i>保险信息表管理
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
                <div class="portlet-body" id="insuranceProductTable">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-10">
                                <div class="btn-group">
                                    <button id="newInsuranceProductButton" class="btn green" onclick="location.href = 'add';">
                                        添加保险信息 <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                            <div class="col-md-2 pull-right">
                                <form id="insuranceProductTableForm" onsubmit="init();return false;">
                                    <div class="input-group">
                                        <div class="input-icon">
                                            <i class="icon-magnifier"></i>
                                            <input class="form-control" type="text" name="insuranceProductNo" placeholder="保险产品编号"/>
                                        </div>
                                        <span class="input-group-btn">
												<button class="btn btn-success" type="submit"><i class="fa fa-arrow-left fa-fw"/></i> 搜索</button>
												</span>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover ">
                        <thead>
                        <tr>
                            <th column="insuranceProductNo">保险产品编号</th>
                            <th column="imgInfo" type="image" imageWidth="60" imageHeight="20">展示图</th>
                            <th column="insuranceName">保险名称</th>
                            <th column="insuranceCompanyId">保险公司ID</th>
                            <th column="status"   type="enum" enum-v="{0:'停用',1:'启用'}">状态</th>
                            <th column="periodsMode"  type="enum" enum-v="{0:'月',1:'年'}">有效期类型</th>
                            <th column="periodsNum">有效期期数</th>
                            <th column="price">价格</th>
                            <th column="created">创建时间</th>
                            <th column="updated">更新时间</th>
                            <th type="action" >操作</th>
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
        $("#insuranceProductTable").pagingGrid(
                {
                    dataUrl: '/services/mars/insuranceproduct/list'
                    , pageSize: 10
                    , scroll: false
                    , dockedItems: [{
                        name: '修改'
                        , iconClass: 'fa fa-edit'
                        , action: '/mars/insuranceproduct/edit'
                        , confirm: false
                        , parmaName: 'insuranceProductNo'
                        , column: 'insuranceProductNo'
                    }
                        , {
                            name: '删除'
                            , iconClass: 'glyphicon glyphicon-trash'
                            , action: '/services/mars/insuranceproduct/delete'
                            , ajax: true
                            , confirm: true
                            , parmaName: 'insuranceProductNo'
                            , column: 'insuranceProductNo'
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

</script>


</body>
</html>


