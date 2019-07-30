jQuery(document).ready(function(){
    //$(".right").css("width",(800-135)+"px");
    var hallJson = [
        {
            "hallId": "1",
            "hallName": "一号厅"
        },
        {
            "hallId": "2",
            "hallName": "二号厅"
        },
        {
            "hallId": "3",
            "hallName": "三号厅"
        },
        {
            "hallId": "4",
            "hallName": "VIP厅"
        },
        {
            "hallId": "5",
            "hallName": "五号厅"
        },
        {
            "hallId": "6",
            "hallName": "六号厅"
        },
        {
            "hallId": "7",
            "hallName": "7号厅"
        },
        {
            "hallId": "8",
            "hallName": "8号厅"
        },
        {
            "hallId": "9",
            "hallName": "9号厅"
        },
        {
            "hallId": "10",
            "hallName": "10厅"
        },
        {
            "hallId": "11",
            "hallName": "11号厅"
        },
        {
            "hallId": "12",
            "hallName": "12号厅"
        }
        ,
        {
            "hallId": "13",
            "hallName": "13厅"
        },
        {
            "hallId": "14",
            "hallName": "14号厅"
        },
        {
            "hallId": "15",
            "hallName": "15号厅"
        }
    ];

    var schedulingJson = {
            "1": [
                {
                    "filmTitle": "哪吒",
                    "filmTime": "120",
                    "startTime": "2019-07-29 11:15"
                },
                {
                    "hallId": "1",
                    "filmTitle": "复仇者联盟:4",
                    "filmTime": "180",
                    "startTime": "2019-07-29 18:00"
                }
            ],
            "2": [
                {
                    "filmTitle": "哪吒",
                    "filmTime": "120",
                    "startTime": "2019-07-29 13:15"
                },
                {
                    "filmTitle": "哪吒",
                    "filmTime": "120",
                    "startTime": "2019-07-29 20:30"
                }
            ],
            "4": [
                {
                    "filmTitle": "哪吒",
                    "filmTime": "120",
                    "startTime": "2019-07-29 11:15"
                },
                {
                    "filmTitle": "复仇者联盟:4",
                    "filmTime": "180",
                    "startTime": "2019-07-30 01:30"
                }
            ]
        }
    ;

    var myDate = new Date();//获取系统当前时间
    function hallAndScheduling(divClass,hall,scheduling){
        /************渲染厅和排程内容：开始*************/
        var body = "";
        body += '<div class="body">\n' +
            '            <div class="left">\n';
        var rightBody = "";
        for (i = 0; i < hall.length; i++) {
            //排程内容
            var sch = "";
            var schList = scheduling[hall[i].hallId];
            if (schList != null) {
                for (j = 0; j < schList.length; j++){
                    let left = timeToPx(schList[j].startTime);
                    sch += '<div class="dataT" data-time="'+ schList[j].filmTime +'" data-startTime="'+ schList[j].startTime +
                        '" style="position: absolute ;width: '+ schList[j].filmTime +'px;height: 60px;left: '+ left +
                        'px;top: 0px;">'+ schList[j].filmTitle +'<div class="startTime">'+ schList[j].startTime +'</div></div>';
                }
            }

            if (i == 0) {
                body += '<div id="hall'+ hall[i].hallId + '" class="leftT leftTFirst">'+ hall[i].hallName + '</div>\n';
                rightBody += '<div id="hallScheduling'+ hall[i].hallId + '" class="rightT rightTFirst">\n' + sch +
                    '                    </div>\n';
            } else {
                body += '<div id="hall'+ hall[i].hallId + '" class="leftT">'+ hall[i].hallName + '</div>\n';
                rightBody += '<div id="hallScheduling'+ hall[i].hallId + '" class="rightT">\n' + sch +
                    '                    </div>\n';
            }
        }
        body += '            </div>\n' +
            '            <div class="right scrollbar-dynamic">\n' +
            '                <div class="rightBody">\n' +
            '<!--                    刻度-->\n' +
            '                    <div class="time"></div>\n' +
            '<!--                    时间线-->\n' +
            '                    <div class="timeLine"></div>\n' +
            rightBody+
            '                </div>\n' +
            '            </div>\n' +
            '        </div>';
        $("."+divClass).html(body);
        /***********渲染刻度 开始**********/
        var timeNum = 7;//从7点开始
        var timeBody = "";
        //循环帧数
        var time = (24 + 1) * 60;
        for (var i = 0; i < time; i++){
            if (i == 0){
                timeBody += '<div class="heightLine" style="margin-left: 0px">' + (i+timeNum) + '</div>';
            }else if ((i+1) % 60 == 0){
                if(((i+1)/60) + timeNum > 23){
                    timeNum = timeNum - 24;
                }
                timeBody += '<div class="heightLine">' + (((i+1)/60) + timeNum) + '</div>';
            }else if ((i+1) % 30 == 0){
                timeBody += '<div class="centerLine"></div>';
            }
            else if (i % 2 == 0) {//一个小格格代表两分钟
                timeBody += '<div class="line"></div>';
            }
        }
        $('.time').html(timeBody);
        /*******渲染刻度 结束**********/
        //渲染draggable
        $(".rightT .dataT").each(function () {
            $(this).draggable({
                containment: [$(this).offset().left + parseInt($('.timeLine').css("left")) + 1, $(this).offset().top, $(this).offset().left + $(this).width(), $(this).offset().top],
                scroll: false,
                create:function(event, ui){
                    rightMenu($(this));
                },
                start: function (event, ui) {
                    revertLift = ui.position.left;
                },
                revert: function () {
                    if (parseInt($('.timeLine').css("left")) < parseInt($(this).css("left"))) {
                        return false;
                    } else {
                        const left = revertLift;
                        console.log("left:" + left)
                        if (left >= 0) {
                            //pxToTime(left,this);
                            $(this).children(".startTime").html(pxToTime(left));
                        }
                        layer.msg("排程应晚于当前时间2分钟以上");
                        return true;
                    }

                },
                drag: function (event, ui) {
                    const left = ui.offset.left - $('.rightT').offset().left;
                    if (left >= 0) {
                        let time=pxToTime(left);
                        $(ui.helper).children(".startTime").html(time);
                        $(ui.helper).data("starttime",time);
                    }

                }
            })
        })
        //初始化时间线高度
        $('.timeLine').height($('.right').height()-35);
        //初始化时间线位置
        //获取服务器时间
        timeNum = 7; //从7点开始

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
            myDate.setMinutes(myDate.getMinutes() + 1);
            //偏移
            $('.timeLine').css("left",parseInt($('.timeLine').css("left"))+1);
            //偏移量
            RefreshContainment();
            //移除压线的draggable拖动效果
            $(".rightT .dataT").each(function () {
                if (parseInt($(this).css("left")) <= parseInt($('.timeLine').css("left")) + 1){
                    $(this).draggable( "disable" ).addClass("dataTDisable");
                    $(this).data("menu","hide");
                }
            })
        }
        timeLine();
        /************渲染厅和排程内容：结束*************/
        /*********滚动条**********/
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
    hallAndScheduling("timeBody",hallJson,schedulingJson);
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
                let time=pxToTime(left);
                $(ui.helper).children(".startTime").html(time);
                $(ui.helper).data("starttime",time);
            }

        }
    })
    //将字符串格式yyyy-MM-dd HH:mm:ss 转换为js日期对象
    function getDateByTimeStr(timeStr) {
        var timeArr = timeStr.split(" ");
        var d = timeArr[0].split("-");
        var t = timeArr[1].split(":");
        return new Date(d[0], (d[1] - 1), d[2], t[0], t[1]);
    }
    //时间转像素1px=1分钟
    function timeToPx(time) {
        let startDate = getDateByTimeStr(time);
        let d = startDate.getDate();
        let H = startDate.getHours();
        let m = startDate.getMinutes();
        //从7点钟开始
        H = H - 7;
        if (d == myDate.getDate()){
            //当天
            return (H * 60) + m;
        } else if (d > myDate.getDate()){
            //第二天
            H = H + 24;
            return (H * 60) + m;
        }
    }
    //像素转时间  1px=1分钟
    function pxToTime(left){
        let date = new Date(myDate.getTime());

        //分钟转 HH:mm
        let time = "" ;
        //小时
        let HH = (left - (left % 60)) / 60;
        HH = HH + 7;//从7点开始
        if (HH > 9 && HH < 24){
            time = HH + ":";
        }else if (HH <= 9){
            time = "0" + HH + ":";
        }else if (HH >= 24){
            date.setDate(date.getDate()+ 1);
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
        let y = date.getFullYear();
        let M = date.getMonth() + 1;
        let d = date.getDate();
        return y + '-' + (M < 10 ? ('0' + M) : M) + '-' + (d < 10 ? ('0' + d) : d) + " "+ time;
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
                        create:function(event, ui){
                            rightMenu($(this));
                        },
                        start:function(event, ui){
                            revertLift = ui.position.left;
                        },
                        revert:function () {
                            if (parseInt($('.timeLine').css("left")) < parseInt($(this).css("left"))){
                                return false;
                            } else {
                                const left = revertLift;
                                console.log("left:"+left)
                                if (left >= 0){
                                    //pxToTime(left,this);
                                    $(this).children(".startTime").html(pxToTime(left));
                                }
                                layer.msg("排程应晚于当前时间2分钟以上");
                                return true;
                            }

                        },
                        drag: function( event, ui ) {
                            const left = ui.position.left;
                            if (left >= 0){
                                //pxToTime(left,ui.helper);
                                let time=pxToTime(left);
                                $(ui.helper).children(".startTime").html(time);
                                $(ui.helper).data("starttime",time);
                            }

                        },
                        stop:function (event, ui) {
                            //console.log(ui)
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

function rightMenu(that) {
    var tar = null;
    that.contextMenu({
        width: 110, // width
        itemHeight: 30, // 菜单项height
        bgColor: "#333", // 背景颜色
        color: "#fff", // 字体颜色
        fontSize: 12, // 字体大小
        hoverBgColor: "#99CC66", // hover背景颜色
        target: function(ele) { // 当前元素
            if($(that).data("menu") == "hide")
                $(".ul-context-menu").hide();

            tar = ele;
        },
        menu: [
            { // 菜单项
                text: "删除排程",
                icon: "",
                callback: function() {
                    console.log(tar);
                    //询问框
                    layer.confirm('确定要删除该排程吗？', {
                        btn: ['确定','取消'] //按钮
                    }, function(){
                        $(tar).remove();
                        layer.msg('删除成功');
                    }, function(){

                    });
                }
            },
            {
                text: "编辑排程",
                icon: "",
                callback: function() {
                    edit(tar);
                }
            }
        ]

    });
}

    function edit(that) {
        layer.open({
            type: 1,
            skin: 'edit_class', //加上边框
            area: ['420px', '440px'], //宽高
            content: 'html内容'
        });
    }



});