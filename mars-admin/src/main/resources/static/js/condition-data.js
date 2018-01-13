/**
 * Created by yankun on 2016/12/8.
 */
//初始化组件
//$('.multi-select').multiSelect();

//定义表头标题
var COUNT_MIN_TITLE = "<i class=\"fa fa-hand-o-down\"></i>  最小笔数";
var COUNT_MAX_TITLE = "<i class=\"fa  fa-hand-o-up\"></i>  最大笔数";
var SUM_AMOUNT_MIN_TITLE = "<i class=\"fa fa-yen\"></i> 最小金额(元)";
var SUM_AMOUNT_MAX_TITLE = "<i class=\"fa fa-yen\"></i> 最大金额(元)";

function setValue(tab) {
    var jsonRoot = [];
    var tbody = $(tab).find("tbody");
    $(tbody[0]).find("tr").each(function () {
        var groupJson = [];
        $(this).find("td").find("input[type=text]").each(function () {
            var simpleJson = {};
            var jsonKey = $(this).attr("json-key");
            var jsonValue = $(this).val();
            simpleJson.jsonKey = jsonKey;
            simpleJson.jsonValue = jsonValue;

            groupJson.push(simpleJson);
        });
        jsonRoot.push(groupJson);
    });

    var hiddens = $(tab).find("input[type=hidden]");
    if (hiddens.length > 0){
        var jsonValue = JSON.stringify(jsonRoot);
        $(hiddens[0]).attr("value",jsonValue);

    }
}

/**
 * 添加条件行
 */
function newCondition() {
    var elementClass = "input ,select";
    //获取总行数
    var rowCount = $("#conditionTable tr").length;
    //复制最末行
    var tr = $("#conditionTable tr").eq(rowCount - 1).clone();

    //设置新的ID
    tr.find(elementClass).each(function (i, n) {
        $(n).attr("id", UUID.generate());
    });
    tr.appendTo("#conditionTable");
    //重新初始化验证
    reInitValidate();

    //设置起始值
    $("input[name=gradientMins]").eq(rowCount - 1).val($("input[name=gradientMaxs]").eq(rowCount - 2).val());
    $(".condition-btn").click(function () {
        if ($("#conditionTable tr").length > 2) {
            $(this).parents("tr")[0].remove();
        }
    });

    $("select[name=algorithmTypes]").change(function () {
        var algorithmTypeValue = $(this).children('option:selected').val();
        var trIndex = $(this).parents("tr").index();
        switch (algorithmTypeValue) {
            case "0":
                setFixAlgorithm(trIndex);
                break;
            case "1":
                setRatioAlgorithm(trIndex);
                break;
            default:
                break;
        }
    });
    initAlgorithmTypes();
}


/**
 * 移除按钮点击事件
 */
//$(".condition-btn").click(function () {
//    if ($("#conditionTable tr").length > 2) {
//        $(this).parents("tr")[0].remove();
//    }
//});

/**
 * 定义周期事件
 */
//$('.periodType').change(function (e) {
//    if (this.options[this.options.selectedIndex].value == 3) {
//        $('#customPeriod').show();
//    } else {
//        $('#customPeriod').hide();
//    }
//});

/**
 * 计费模式切换
 * @param mode
 */
function switchMode(mode) {
    switch (mode) {
        case "1":
            //设置金额head
            setAmountHead();

            //隐藏周期设置
            hideCustomPeriod();

            break;
        case "2":
            //设置金额head
            setAmountHead();

            //显示周期设置
            //showCustomPeriod();
            break;

        case "3":
            //设置笔数head
            setCountHead();

            //显示周期设置
            showCustomPeriod();
            break;
        default:
            break;
    }
}

/**
 * 设置金额head
 */
function setAmountHead() {
    $('#conditionTable tr').find('th:eq(0)').html(SUM_AMOUNT_MIN_TITLE);
    $('#conditionTable tr').find('th:eq(1)').html(SUM_AMOUNT_MAX_TITLE);
}

/**
 * 设置笔数head
 */
function setCountHead() {
    $('#conditionTable tr').find('th:eq(0)').html(COUNT_MIN_TITLE);
    $('#conditionTable tr').find('th:eq(1)').html(COUNT_MAX_TITLE);
}

/**
 * 显示周期设置
 */
function showCustomPeriod() {
    reInitValidate();
    $('#periodTypeDiv').show();
    $('#isConverseReplenishingDiv').show();
    if ($('.periodType').val() == 3) {
        $('#customPeriod').show();
    }
    //加上验证
    //$("input[name=period]").rules("add", {minlength: 1, maxlength: 3, number: true, required: true});
}

/**
 * 隐藏周期设置
 */
function hideCustomPeriod() {
    //移除验证
    reInitValidate();
    $("input[name=period]").rules("add", {required: false});
    $('#isConverseReplenishingDiv').hide();
    $('#periodTypeDiv').hide();
    $('#customPeriod').hide();
}

// $('#conditionTable tr').find('th:eq(0)').hide();
/**
 * 模式点击事件
 */
//$("input[name=billingMode]").click(function () {
//    switchMode($("input[name=billingMode]:checked").attr("value"));
//});

/**
 * 设置固定费率
 */
function setFixAlgorithm(index) {
    $("[name=algorithmValues]").get(index).value = '';
    $("[name=feeMins]").get(index).value = 0;
    $("[name=feeMins]").get(index).style.display = 'none';
    $("[name=feeMaxs]").get(index).value = 0;
    $("[name=feeMaxs]").get(index).style.display = 'none';
}

/**
 * 设置比率费率
 */
function setRatioAlgorithm(index) {
    $("[name=algorithmValues]").get(index).value = '';
    $("[name=feeMins]").get(index).value = 0;
    $("[name=feeMins]").get(index).style.display = '';
    $("[name=feeMaxs]").get(index).value = 0;
    $("[name=feeMaxs]").get(index).style.display = '';
}

//$("select[name=algorithmTypes]").change(function () {
//    var  algorithmTypeValue = $(this).children('option:selected').val();
//    var trIndex = $(this).parents("tr").index();
//    switch (algorithmTypeValue){
//        case "0":
//            setFixAlgorithm(trIndex);
//            break;
//        case "1":
//            setRatioAlgorithm(trIndex);
//            break;
//        default:
//            break;
//    }
//});

function initAlgorithmTypes() {
    $.each($("[name=algorithmTypes]"), function (index, value) {
        var algorithmTypeValue = $(this).children('option:selected').val();
        switch (algorithmTypeValue) {
            case "0":
                setFixAlgorithm(index);
                break;
            case "1":
                setRatioAlgorithm(index);
                break;
            default:
                break;
        }
    });


}

//设置默认计费模式
//switchMode($("input[name=billingMode]:checked").attr("value"));
