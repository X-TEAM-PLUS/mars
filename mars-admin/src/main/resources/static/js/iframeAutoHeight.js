/**
 * Created by yankun on 2016/4/18.
 */
// $(document).ready(function () {
    // $(document.body).bind("resize", function () {
    //     alert("abc");
    //     autoFullHeight();
    //
    // });
    function autoFullHeight() {
        var ifm= $("#mainWindow", window.parent.document);
        if(ifm && ifm.length>0) {
            if( ifm[0].contentDocument.body.offsetHeight>834){
             ifm[0].height =  ifm[0].contentDocument.body.offsetHeight;
            }else{
                ifm[0].height = 834;
            }
        }
    }

    setTimeout("autoFullHeight()",300);
// });
// if (window.parent.length>0){window.parent.document.all.mainWindow.style.height=document.body.scrollHeight;}