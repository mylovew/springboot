jQuery(document).ready(function(){
    //$(".right").css("width",(800-135)+"px");
    function scheduling(divClass){

        /***********渲染刻度 开始**********/
        var timeNum = 7;//从7点开始
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
        timeNum = 7; //从7点开始
        var myDate = new Date();//获取系统当前时间
        var hours = myDate.getHours();
        if (hours < 7){
            hours += 24;
        }
        var minutes = myDate.getMinutes();
        $('.timeLine').css("left",(hours - timeNum) * 60 + minutes);
        function timeLine() {
            //只走到第二天7点
            if (parseInt($('.timeLine').css("left")) < (24 * 60)){
                //一分钟执行一次
                setTimeout(timeLine, 1000 * 60);
            }
            //偏移
            $('.timeLine').css("left",parseInt($('.timeLine').css("left"))+1);
            console.log(hours+":"+minutes);
            //偏移量
            RefreshContainment();
        }
        timeLine();

        jQuery('.scrollbar-dynamic').scrollbar({
            "onScroll":function (y,x) {
                // console.log(x);
                //偏移量
                RefreshContainment();

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
                    RefreshContainment();
                }

            }
        });

    }
    scheduling("timeBody");
    function body(hallNumber) {

    }
    //更新偏移量
    function RefreshContainment(){
        $(".rightT .dataT").each(function () {
            $(this).draggable({
                containment: [$(this).parent().offset().left + parseInt($('.timeLine').css("left")) + 1 , $(this).parent().offset().top, $(this).parent().offset().left + $(this).parent().width() , $(this).parent().offset().top],
                scroll: false,
            })
        })
    }

    /*拖动*/
    $('.data').draggable({
        helper: "clone",
        zIndex:10,
        cursorAt:{left:0,top:$('.data').height()},
        start: function( event, ui ) {
            //宽度根据时长计算
            $(ui.helper).width($(ui.helper).data("time"))
            // $('.right').append($(ui.helper).width(100));
            $(ui.helper).append('<div class="startTime"></div>');
        },
        drag: function( event, ui ) {
            const left = ui.offset.left - $('.rightT').offset().left;
            if (left >= 0){
                pxToTime(left,ui.helper);
            }

        }
    })
    function pxToTime(left,that){
        //分钟转 HH:mm
        let time = "";
        //小时
        let HH = (left - (left % 60)) / 60;
        HH = HH + 7;//从7点开始
        if (HH > 9 && HH < 24){
            time = HH + ":";
        }else if (HH <= 9){
            time = "0" + HH + ":";
        }else if (HH >= 24){
            HH = HH - 24;
            time = "0" + HH + ":";
        }
        //分钟数
        let mm = left % 60;
        if (mm > 9 ){
            time = time + (left % 60);
        } else {
            time = time + "0" + (left % 60);
        }

        $(that).children(".startTime").html(time);
    }
    var revertLift = 0;
    /*放置*/
    $( ".rightT" ).droppable({
        greedy:true,
        drop:function( event, ui ){
            $('.right').mousemove(function(e) {
                var positionX=e.pageX-$(this).offset().left; //获取当前鼠标相对.right的X坐标
                var positionY=e.pageY-$(this).offset().top; //获取当前鼠标相对.right的Y坐标
            })
            //判断是否为新增
            if ($(ui.helper).parent().attr("class").indexOf('rightT') < 0){
                //判断是否在时间线右侧2分钟后
                if(parseInt($('.timeLine').css("left")) < ui.offset.left - $(this).offset().left){
                    var height = $(ui.helper).css("height");
                    var width = $(ui.helper).css("width");
                    $(this).append($(ui.helper).removeClass().clone().draggable({
                        // containment: "parent",
                        containment: [$(this).offset().left + parseInt($('.timeLine').css("left"))+1,$(this).offset().top,$(this).offset().left + $(this).width(),$(this).offset().top],
                        scroll: false,
                        start:function(event, ui){
                            revertLift = ui.position.left;
                        },
                        revert:function () {
                            console.log();
                            if (parseInt($('.timeLine').css("left")) < parseInt($(this).css("left"))){
                                return false;
                            } else {
                                const left = revertLift;
                                console.log("left:"+left)
                                if (left >= 0){
                                    pxToTime(left,this);
                                }
                                layer.msg("排程应晚于当前时间2分钟以上");
                                return true;
                            }

                        },
                        drag: function( event, ui ) {
                            const left = ui.position.left;
                            if (left >= 0){
                                pxToTime(left,ui.helper);
                            }

                        },
                        stop:function (event, ui) {
                            console.log(ui)

                        }
                    }).css({
                        width:width,
                        height:height,
                        top:0,
                        left:ui.offset.left - $(this).offset().left
                    }).addClass("dataT"));
                }else {
                    layer.msg("排程应晚于当前时间2分钟以上");
                }


            }

        }
    });





});