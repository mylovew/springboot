jQuery(document).ready(function(){
    //$(".right").css("width",(800-135)+"px");
    function scheduling(divClass){

        /***********渲染刻度 开始**********/
        var timeNum = 7;
        var body = "";
        //循环帧数
        var time = (24 + 1) * 60;
        for (var i = 0; i < time; i++){
            if (i == 0){
                body += '<div class="heightLine" style="margin-left: 0px">' + (i+timeNum) + '</div>';
            }else if ((i+1) % 60 == 0){
                if(((i+1)/60) + timeNum > 23){
                    timeNum = timeNum - 24;
                }
                body += '<div class="heightLine">' + (((i+1)/60) + timeNum) + '</div>';
            }else if ((i+1) % 30 == 0){
                body += '<div class="centerLine"></div>';
            }
            else if (i % 2 == 0) {//一个小格格代表两分钟
                body += '<div class="line"></div>';
            }
        }
        $('.time').html(body);
        /*******渲染刻度 结束**********/

        //初始化时间线高度
        $('.timeLine').height($('.right').height()-35);
        //初始化时间线位置
        //获取服务器时间
        timeNum = 7;
        var myDate = new Date();//获取系统当前时间
        var hours = myDate.getHours();
        if (hours < 7){
            hours += 24;
        }
        var minutes = myDate.getMinutes();
        console.log((hours - timeNum));
        console.log((hours - timeNum) * 60 + minutes)
        $('.timeLine').css("left",(hours - timeNum) * 60 + minutes);
        function timeLine() {
            //只走到第二天7点
            if (parseInt($('.timeLine').css("left")) < (24 * 60)){
                console.log("执行")
                //一分钟执行一次
                setTimeout(timeLine, 1000 * 60);
            }
            //偏移
            $('.timeLine').css("left",parseInt($('.timeLine').css("left"))+1);
        }
        timeLine();

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
                    $('.time').css('top',y.scroll);
                    // var num = ($('.time').width() - $( "."+divClass ).width())/2 + $('.left').width();
                    // console.log(num);
                    // jQuery('.time').position({
                    //     of: $( "."+divClass ),
                    //     at: "center+"+num+" top+18"
                    // });
                }

            }
        });

    }
    scheduling("timeBody");
    function body(hallNumber) {

    }
    /*拖动*/
    $('.data').draggable({
        helper: "clone",
        zIndex:99999,
        cursorAt:{left:0,top:30},
        start: function( event, ui ) {
            $('.right').append($(ui.helper).width(100));

        },
        stop: function( event, ui ) {

        }
    })
    /*放置*/
    $( ".rightT" ).droppable({
        greedy:true,
        drop:function( event, ui ){
            console.log(ui)
            $(this).append($(ui.draggable).clone().draggable().width(100));
        }
    });





});