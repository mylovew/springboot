jQuery(document).ready(function(){
    //$(".right").css("width",(800-135)+"px");
    function scheduling(divClass){
        //初始化时间线高度
        $('.timeLine').height($('.right').height()-35);

        jQuery('.scrollbar-dynamic').scrollbar({
            "onScroll":function (y,x) {
                //console.log(x);

            }
        });
        jQuery('.scrollbar-inner').scrollbar({
            "onScroll":function (y,x) {
                if(y.maxScroll > 0){
                    //表示有纵向滚动条
                    //底部横向栏固定
                    jQuery('.scroll-element.scroll-x.scroll-scrollx_visible').position({
                        of: $( "."+divClass ),
                        at: "center+46 bottom-8"
                    });
                    var num = ($('.time').width() - $( "."+divClass ).width())/2 + $('.left').width();
                    jQuery('.time').position({
                        of: $( "."+divClass ),
                        at: "center+"+num+" top+18"
                    });
                }

            }
        });

    }
    scheduling("timeBody");
    function body(hallNumber) {

    }








});