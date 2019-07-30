jQuery(document).ready(function () {
    //$(".right").css("width",(800-135)+"px");
    var laydate =null;
    layui.use(['laydate','form','layer'], function(){
        laydate = layui.laydate;
    });
        //var layer = layui.layer;
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
                    "startTime": "2019-07-30 11:15"
                },
                {
                    "hallId": "1",
                    "filmTitle": "复仇者联盟:4",
                    "filmTime": "180",
                    "startTime": "2019-07-30 18:00"
                }
            ],
            "2": [
                {
                    "filmTitle": "哪吒",
                    "filmTime": "120",
                    "startTime": "2019-07-30 13:15"
                },
                {
                    "filmTitle": "哪吒",
                    "filmTime": "120",
                    "startTime": "2019-07-30 20:30"
                }
            ],
            "4": [
                {
                    "filmTitle": "哪吒",
                    "filmTime": "120",
                    "startTime": "2019-07-30 11:15"
                },
                {
                    "filmTitle": "复仇者联盟:4",
                    "filmTime": "180",
                    "startTime": "2019-07-31 01:30"
                }
            ]
        }
    ;

    var myDate = new Date();//获取系统当前时间
    function hallAndScheduling(divClass, hall, scheduling) {
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
                for (j = 0; j < schList.length; j++) {
                    let startTime = new Date(schList[j].startTime);
                    let time = new Date(myDate.getTime());
                    time.setHours(7);
                    time.setMinutes(0);
                    time.setSeconds(0);
                    time.setMilliseconds(0);
                    let time2 = new Date(time.getTime());
                    time2.setDate(time2.getDate() + 1);
                    time2.setHours(8);
                    if (time.getTime() <= startTime.getTime() && startTime.getTime() <= time2.getTime()) {
                        let left = timeToPx(schList[j].startTime);
                        sch += '<div class="dataT" data-filmTitle="' + schList[j].filmTitle + '" data-time="' + schList[j].filmTime + '" data-startTime="' + schList[j].startTime +
                            '" style="position: absolute ;width: ' + schList[j].filmTime + 'px;height: 60px;left: ' + left +
                            'px;top: 0px;">' + schList[j].filmTitle + '<div class="startTime">' + schList[j].startTime + '</div></div>';
                    }

                }
            }

            if (i == 0) {
                body += '<div id="hall' + hall[i].hallId + '" class="leftT leftTFirst">' + hall[i].hallName + '</div>\n';
                rightBody += '<div id="hallScheduling' + hall[i].hallId + '" class="rightT rightTFirst">\n' + sch +
                    '                    </div>\n';
            } else {
                body += '<div id="hall' + hall[i].hallId + '" class="leftT">' + hall[i].hallName + '</div>\n';
                rightBody += '<div id="hallScheduling' + hall[i].hallId + '" class="rightT">\n' + sch +
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
            rightBody +
            '                </div>\n' +
            '            </div>\n' +
            '        </div>';
        $("." + divClass).html(body);
        /***********渲染刻度 开始**********/
        var timeNum = 7;//从7点开始
        var timeBody = "";
        //循环帧数
        var time = (24 + 1) * 60;
        for (var i = 0; i < time; i++) {
            if (i == 0) {
                timeBody += '<div class="heightLine" style="margin-left: 0px">' + (i + timeNum) + '</div>';
            } else if ((i + 1) % 60 == 0) {
                if (((i + 1) / 60) + timeNum > 23) {
                    timeNum = timeNum - 24;
                }
                timeBody += '<div class="heightLine">' + (((i + 1) / 60) + timeNum) + '</div>';
            } else if ((i + 1) % 30 == 0) {
                timeBody += '<div class="centerLine"></div>';
            } else if (i % 2 == 0) {//一个小格格代表两分钟
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
                zIndex:10,
                create: function (event, ui) {
                    rightMenu($(this));
                },
                start: function (event, ui) {
                    revertLift = ui.position.left;
                },
                revert: function () {
                    if (parseInt($('.timeLine').css("left")) < parseInt($(this).css("left"))) {
                        //跟现有排程有时间冲突
                        if (collision($(this))){
                            //不作处理
                            return false;
                        } else {
                            const left = revertLift;
                            if (left >= 0) {
                                //pxToTime(left,this);
                                $(this).children(".startTime").html(pxToTime(left));
                            }
                            layer.msg("该排程时间与已有排程时间冲突");
                            return true;
                        }
                    } else {
                        const left = revertLift;
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
                        let time = pxToTime(left);
                        $(ui.helper).children(".startTime").html(time);
                        $(ui.helper).data("starttime", time);
                    }

                }
            })
        })
        //初始化时间线高度
        $('.timeLine').height($('.right').height() - 35);
        //初始化时间线位置
        //获取服务器时间
        timeNum = 7; //从7点开始

        var hours = myDate.getHours();
        if (hours < 7) {
            hours += 24;
        }
        var minutes = myDate.getMinutes();
        $('.timeLine').css("left", (hours - timeNum) * 60 + minutes);

        function timeLine() {
            //只走到第二天7点
            if (parseInt($('.timeLine').css("left")) < (24 * 60)) {
                //一分钟执行一次
                setTimeout(timeLine, 1000 * 60);
            }
            myDate.setMinutes(myDate.getMinutes() + 1);
            //偏移
            $('.timeLine').css("left", parseInt($('.timeLine').css("left")) + 1);
            //偏移量
            RefreshContainment();
            //移除压线的draggable拖动效果
            $(".rightT .dataT").each(function () {
                if (parseInt($(this).css("left")) <= parseInt($('.timeLine').css("left")) + 1) {
                    $(this).draggable("disable").addClass("dataTDisable");
                    $(this).data("menu", "hide");
                }
            })
        }

        timeLine();
        /************渲染厅和排程内容：结束*************/
        /*********滚动条**********/
        jQuery('.scrollbar-dynamic').scrollbar({
            "onScroll": function (y, x) {
                // console.log(x);
                //偏移量
                RefreshContainment();

            }
        });
        jQuery('.scrollbar-inner').scrollbar({
            "onScroll": function (y, x) {
                if (y.maxScroll > 0) {
                    //表示有纵向滚动条
                    //底部横向栏固定
                    jQuery('.scroll-element.scroll-x.scroll-scrollx_visible').position({
                        of: $("." + divClass),
                        at: "center+46 bottom-8"
                    });
                    $('.time').css('top', y.scroll);
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

    hallAndScheduling("timeBody", hallJson, schedulingJson);

    function body(hallNumber) {

    }

    //更新偏移量
    function RefreshContainment() {
        $(".rightT .dataT").each(function () {
            $(this).draggable({
                containment: [$(this).parent().offset().left + parseInt($('.timeLine').css("left")) + 1, $(this).parent().offset().top, $(this).parent().offset().left + $(this).parent().width(), $(this).parent().offset().top],
                scroll: false,
            })
        })
    }

    /*拖动*/
    $('.data').draggable({
        helper: "clone",
        zIndex: 13,
        cursorAt: {left: 0, top: $('.data').height()},
        start: function (event, ui) {
            //宽度根据时长计算
            $(ui.helper).width($(ui.helper).data("time"))
            // $('.right').append($(ui.helper).width(100));
            $(ui.helper).append('<div class="startTime"></div>');
        },
        drag: function (event, ui) {
            const left = ui.offset.left - $('.rightT').offset().left;
            if (left >= 0) {
                let time = pxToTime(left);
                $(ui.helper).children(".startTime").html(time);
                $(ui.helper).data("starttime", time);
            }

        }
    })

    //将字符串格式yyyy-MM-dd HH:mm 转换为js日期对象
    function getDateByTimeStr(timeStr) {
        var timeArr = timeStr.split(" ");
        var d = timeArr[0].split("-");
        var t = timeArr[1].split(":");
        return new Date(d[0], (d[1] - 1), d[2], t[0], t[1]);
    }
    //将js日期对象转换为字符串格式 yyyy-MM-dd HH:mm,适用各种浏览器
    function getTimeStrByDate(date) {
        var y = date.getFullYear();
        var M = date.getMonth() + 1;
        var d = date.getDate();
        var H = date.getHours();
        var m = date.getMinutes();
        return y + '-' + (M < 10 ? ('0' + M) : M) + '-' + (d < 10 ? ('0' + d) : d) + " " + (H < 10 ? ('0' + H) : H) + ":" + (m < 10 ? ('0' + m) : m);
    }
    //时间转像素1px=1分钟
    function timeToPx(time) {
        let startDate = getDateByTimeStr(time);
        let d = startDate.getDate();
        let H = startDate.getHours();
        let m = startDate.getMinutes();
        //从7点钟开始
        H = H - 7;
        if (d == myDate.getDate()) {
            //当天
            return (H * 60) + m;
        } else if (d > myDate.getDate()) {
            //第二天
            H = H + 24;
            return (H * 60) + m;
        }
    }

    //像素转时间  1px=1分钟
    function pxToTime(left) {
        let date = new Date(myDate.getTime());

        //分钟转 HH:mm
        let time = "";
        //小时
        let HH = (left - (left % 60)) / 60;
        HH = HH + 7;//从7点开始
        if (HH > 9 && HH < 24) {
            time = HH + ":";
        } else if (HH <= 9) {
            time = "0" + HH + ":";
        } else if (HH >= 24) {
            date.setDate(date.getDate() + 1);
            HH = HH - 24;
            time = "0" + HH + ":";
        }
        //分钟数
        let mm = left % 60;
        if (mm > 9) {
            time = time + (left % 60);
        } else {
            time = time + "0" + (left % 60);
        }
        let y = date.getFullYear();
        let M = date.getMonth() + 1;
        let d = date.getDate();
        return y + '-' + (M < 10 ? ('0' + M) : M) + '-' + (d < 10 ? ('0' + d) : d) + " " + time;
    }

    var revertLift = 0;
    /*放置*/
    $(".rightT").droppable({
        greedy: true,
        drop: function (event, ui) {
            $('.right').mousemove(function (e) {
                var positionX = e.pageX - $(this).offset().left; //获取当前鼠标相对.right的X坐标
                var positionY = e.pageY - $(this).offset().top; //获取当前鼠标相对.right的Y坐标
            })
            //判断是否为新增
            if ($(ui.helper).parent().attr("class").indexOf('rightT') < 0) {
                //判断是否在时间线右侧2分钟后
                if (parseInt($('.timeLine').css("left")) < ui.offset.left - $(this).offset().left) {
                    var height = $(ui.helper).css("height");
                    var width = $(ui.helper).css("width");
                    var that = null;
                    $(this).append($(ui.helper).removeClass().clone().draggable({
                        // containment: "parent",
                        containment: [$(this).offset().left + parseInt($('.timeLine').css("left")) + 1, $(this).offset().top, $(this).offset().left + $(this).width(), $(this).offset().top],
                        scroll: false,
                        create: function (event, ui) {
                            const left = parseInt($(this).css("left")) - $('.rightT').offset().left;
                            if (left >= 0) {
                                let time = pxToTime(left);
                                $(this).children(".startTime").html(time);
                                $(this).data("starttime", time);
                            }
                            rightMenu($(this));
                            that = $(this);
                            $(this).css("zIndex", 10 );
                        },
                        start: function (event, ui) {
                            revertLift = ui.position.left;
                        },
                        revert: function () {
                            if (parseInt($('.timeLine').css("left")) < parseInt($(this).css("left"))) {
                                //跟现有排程有时间冲突
                                if (collision($(this))){
                                    //不作处理
                                    return false;
                                } else {
                                    const left = revertLift;
                                    if (left >= 0) {
                                        //pxToTime(left,this);
                                        $(this).children(".startTime").html(pxToTime(left));
                                    }
                                    layer.msg("该排程时间与已有排程时间冲突");
                                    return true;
                                }

                            } else {
                                const left = revertLift;
                                if (left >= 0) {
                                    //pxToTime(left,this);
                                    $(this).children(".startTime").html(pxToTime(left));
                                }
                                layer.msg("排程应晚于当前时间2分钟以上");
                                return true;
                            }

                        },
                        drag: function (event, ui) {
                            const left = ui.position.left;
                            if (left >= 0) {
                                //pxToTime(left,ui.helper);
                                let time = pxToTime(left);
                                $(ui.helper).children(".startTime").html(time);
                                $(ui.helper).data("starttime", time);
                            }

                        },
                        stop: function (event, ui) {
                            //console.log(ui)
                        }
                    }).css({
                        width: width,
                        height: height,
                        top: 0,
                        left: ui.offset.left - $(this).offset().left
                    }).addClass("dataT"));
                    //跟现有排程有时间冲突
                    if (collision(that)){
                        //不作处理

                    } else {
                        //移除
                        that.remove();
                        layer.msg("该排程时间与已有排程时间冲突");
                    }


                } else {
                    layer.msg("排程应晚于当前时间2分钟以上");
                }


            }

        }
    });
    //接触反馈
    function collision(that) {
        let flag = true;
        that.parent().children(".dataT").each(function () {
            //判断是否是同一个
            if (!$(this).is(that)){
                //判断是否时间重叠
                var thatLeft = parseInt(that.css("left"));
                var thatRight = thatLeft + parseInt(that.data("time"));

                var thisLeft = parseInt($(this).css("left")) - 2; //前后两分钟间隔
                var thisRight = parseInt($(this).css("left")) + parseInt($(this).data("time")) + 2;

                if ((thisLeft < thatLeft && thatLeft < thisRight) || (thisLeft < thatRight && thatRight < thisRight)){
                    flag = false;
                    return false;
                }
            }
        })
        return flag;
    }


    //右键菜单
    function rightMenu(that) {
        var tar = null;
        that.contextMenu({
            width: 110, // width
            itemHeight: 30, // 菜单项height
            bgColor: "#333", // 背景颜色
            color: "#fff", // 字体颜色
            fontSize: 12, // 字体大小
            hoverBgColor: "#99CC66", // hover背景颜色
            target: function (ele) { // 当前元素
                if ($(that).data("menu") == "hide")
                    $(".ul-context-menu").hide();

                tar = ele;
            },
            menu: [
                { // 菜单项
                    text: "删除排程",
                    icon: "",
                    callback: function () {
                        console.log(tar);
                        //询问框
                        layer.confirm('确定要删除该排程吗？', {
                            btn: ['确定', '取消'] //按钮
                        }, function () {
                            $(tar).remove();
                            layer.msg('删除成功');
                        }, function () {

                        });
                    }
                },
                {
                    text: "编辑排程",
                    icon: "",
                    callback: function () {
                        edit($(tar));
                    }
                }
            ]

        });
    }
    //保存排程设置的开始时间
    function saveStartTime(that) {
        let time = $("#chooseStartDate").val() + " " + $("#chooseStartTime").val();
        let left = timeToPx(time);
        let right = left + parseInt(that.data("time"));
        console.log(time);
        console.log(left + "," +right)
        if ((parseInt($('.timeLine').css("left"))+1) < left){
            let oldLeft = that.css("left");
            let oldStarttime = that.data("starttime");
            let oldChildren = that.children(".startTime").html();
            that.css("left",left).data("starttime",time).children(".startTime").html(time);
            if (collision(that)){

            }else {
                that.css("left",oldLeft).data("starttime",oldStarttime).children(".startTime").html(oldChildren);
                layer.msg("该排程时间与已有排程时间冲突");
            }

        } else {
            layer.msg("排程应晚于当前时间2分钟以上");
        }

    }
    //打开编辑面板
    function edit(that) {
        var endTime = getDateByTimeStr(that.data("starttime"));
        endTime.setMinutes(endTime.getMinutes() + parseInt(that.data("time")));
            layer.open({
                title:that.data("filmtitle"),
                type: 1,
                skin: 'edit_class', //加上边框
                area: ['420px', '440px'], //宽高
                content: '<div class="layui-form">\n' +
                    '  <div class="layui-form-item">\n' +
                    '      <label class="layui-form-label">开始日期：</label>\n' +
                    '      <div class="layui-input-inline">\n' +
                    '        <input type="text" class="layui-input" id="chooseStartDate" placeholder="yyyy-MM-dd" readonly>\n' +
                    '      </div>\n' +
                    '      <label class="layui-form-label">开始时间：</label>\n' +
                    '      <div class="layui-input-inline">\n' +
                    '        <input type="text" class="layui-input" id="chooseStartTime" placeholder="HH:mm" readonly>\n' +
                    '    </div>\n' +
                    '  </div>\n' +
                    '</div>'+
                    '<div>' +
                    '<label class="layui-form-label" style="width: 280px;text-align: center">结束时间：<span id="endTime">' +
                    getTimeStrByDate(endTime) +
                    '</span></label>' +
                    '<button id="save" type="button" class="layui-btn layui-btn-warm layui-btn-radius" style="height: 30px;line-height: 30px">保存</button>' +
                    '</div>'
                ,
                success:function (layero, index) {
                    $("#save").click(function () {
                        saveStartTime(that);
                    });
                    console.log("成功")
                    let nowDate = new Date(myDate.getTime());
                    let y = nowDate.getFullYear();
                    let M = nowDate.getMonth() + 1;
                    let d = nowDate.getDate();
                    let HH = nowDate.getHours();
                    let mm = nowDate.getMinutes();
                    let nextDayDate = new Date(myDate.getTime());
                    nextDayDate.setDate(nextDayDate.getDate() + 1);
                    let yy = nextDayDate.getFullYear();
                    let MM = nextDayDate.getMonth() + 1;
                    let dd = nextDayDate.getDate();
                    //渲染日期选择框
                    let date = new Date(that.data("starttime").split(" ")[0]);
                    let min = '00:00:00';
                    let max = '07:59:00';
                    if (date.getDate() == d) {
                        min = '00:00:00';
                        max = '23:59:00';
                    }
                    //初始化时间
                    var chooseStartTime = laydate.render({
                        elem: '#chooseStartTime'
                        ,type: 'time'
                        ,theme: '#393D49'
                        ,min: min
                        ,max: max
                        ,format:'HH:mm'
                        ,btns:["confirm"]
                        ,value: that.data("starttime").split(" ")[1]
                        ,done:function (value,date,endDate) {
                            let timeStrByDate = getDateByTimeStr($("#chooseStartDate").val() + " " +value);
                            timeStrByDate.setMinutes(timeStrByDate.getMinutes() + parseInt(that.data("time")));
                            $("#endTime").html(getTimeStrByDate(timeStrByDate));
                        }
                    });
                    //初始化日期
                    var chooseStartDate =laydate.render({
                        elem: '#chooseStartDate'
                        ,min: y + '-' + (M < 10 ? ('0' + M) : M) + '-' + (d < 10 ? ('0' + d) : d)
                        ,max: yy + '-' + (MM < 10 ? ('0' + MM) : MM) + '-' + (dd < 10 ? ('0' + dd) : dd)
                        ,btns:["confirm"]
                        ,theme: '#111111'
                        ,value:that.data("starttime").split(" ")[0]
                        ,done: function(value, date, endDate){
                            var da = new Date();
                            var defaultVal = "00:00";
                            if(date.date == d){
                                chooseStartTime.config.max={
                                    year: da.getFullYear(),
                                    month: da.getMonth(),
                                    date: da.getDate(),
                                    hours:23,
                                    minutes:59,
                                    seconds:0
                                }
                                chooseStartTime.config.min={
                                    year: da.getFullYear(),
                                    month: da.getMonth(),
                                    date: da.getDate(),
                                    hours:0,
                                    minutes:0,
                                    seconds:0
                                }
                                defaultVal = "07:00";
                            }else {
                                chooseStartTime.config.max={
                                    year: da.getFullYear(),
                                    month: da.getMonth(),
                                    date: da.getDate(),
                                    hours:7,
                                    minutes:59,
                                    seconds:0
                                }
                                chooseStartTime.config.min={
                                    year: da.getFullYear(),
                                    month: da.getMonth(),
                                    date: da.getDate(),
                                    hours:0,
                                    minutes:0,
                                    seconds:0
                                }
                            }
                            let timeStrByDate = getDateByTimeStr(value + " " + defaultVal);
                            timeStrByDate.setMinutes(timeStrByDate.getMinutes() + parseInt(that.data("time")));
                            $("#endTime").html(getTimeStrByDate(timeStrByDate));
                            $("#chooseStartTime").val(defaultVal)
                        }
                    });

                }
            });
    }


});