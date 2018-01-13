(function ($) {
    $.fn.comboBox = function comboBox(options) {
        var selector = $(this);
        var action = options.dataUrl + "&dt=" + new Date().getTime();
        var labelColumn = options.label;
        var valueColumn = options.value;

        //清空
      //  selector.empty();
        //加载数据
        $.ajax({
            url: contextPath + action,
            type: 'POST',
            success: function (result) {
                if (result.success) {
                    $.each(result.list, function (index, rowValue) {
                        selector.append($("<option value='"+ rowValue[valueColumn] +"'>"+ rowValue[labelColumn]+"</option>"));
                    });
                }
            }
        });
        };

})(jQuery);

