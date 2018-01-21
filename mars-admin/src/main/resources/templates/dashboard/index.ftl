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
            <i class="fa fa-cube"></i>
            <a href="#">平台统计</a>
        </li>

    </ul>
</div>
<div class="page-content">
    <!-- END PAGE HEADER-->
    <!-- BEGIN PAGE CONTENT-->
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 blue" href="#">
                        <div class="visual">
                            <i class="fa fa-comments"></i>
                        </div>
                        <div class="details">
                            <div class="number"> +
                                <span data-counter="counterup" data-value="1349">0</span>
                            </div>
                            <div class="desc"> 今日新增访客</div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 red" href="#">
                        <div class="visual">
                            <i class="fa fa-bar-chart-o"></i>
                        </div>
                        <div class="details">
                            <div class="number"> +
                                <span data-counter="counterup" data-value="12,5">0</span></div>
                            <div class="desc"> 今日新增会员 </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 green" href="#">
                        <div class="visual">
                            <i class="fa fa-shopping-cart"></i>
                        </div>
                        <div class="details">
                            <div class="number"> +
                                <span data-counter="counterup" data-value="549">0</span>
                            </div>
                            <div class="desc"> 今日新增社员</div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 purple" href="#">
                        <div class="visual">
                            <i class="fa fa-globe"></i>
                        </div>
                        <div class="details">
                            <div class="number"> +
                                <span data-counter="counterup" data-value="89"></span></div>
                            <div class="desc"> 今日新增理事 </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 green-jungle" href="#">
                        <div class="visual">
                            <i class="fa fa-globe"></i>
                        </div>
                        <div class="details">
                            <div class="number"> +
                                <span data-counter="counterup" data-value="89"></span> </div>
                            <div class="desc"> 今日新增常务理事 </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 yellow" href="#">
                        <div class="visual">
                            <i class="fa fa-globe"></i>
                        </div>
                        <div class="details">
                            <div class="number"> +
                                <span data-counter="counterup" data-value="89">1000</span><i class="fa fa-rmb"></i> </div>
                            <div class="desc"> 今日营收 </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 blue" href="#">
                        <div class="visual">
                            <i class="fa fa-comments"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value="1349">0</span>
                            </div>
                            <div class="desc"> 总访客</div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 red" href="#">
                        <div class="visual">
                            <i class="fa fa-bar-chart-o"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value="12,5">0</span> </div>
                            <div class="desc"> 总会员 </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 green" href="#">
                        <div class="visual">
                            <i class="fa fa-shopping-cart"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value="549">0</span>
                            </div>
                            <div class="desc"> 总社员</div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 purple" href="#">
                        <div class="visual">
                            <i class="fa fa-globe"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value="89"></span> </div>
                            <div class="desc"> 总理事 </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 green-jungle" href="#">
                        <div class="visual">
                            <i class="fa fa-globe"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value="89"></span></div>
                            <div class="desc"> 总常务理事 </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
                    <a class="dashboard-stat dashboard-stat-v2 yellow" href="#">
                        <div class="visual">
                            <i class="fa fa-globe"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value="89">200000</span><i class="fa fa-rmb"></i> </div>
                            <div class="desc"> 总营收 </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<@commonMacro.commonScript />
<script>

</script>


</body>
</html>


