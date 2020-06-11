
 (function (window, undefined) {
            var dom = {
                //绑定事件
                on: function (node, eventName, handler) {
                    if (node.addEventListener) {
                        node.addEventListener(eventName, handler);
                    }
                    else {
                        node.attachEvent("on" + eventName, handler);
                    }
                },
                //获取元素的样式
                getStyle: function (node, styleName) {
                    var realStyle = null;
                    if (window.getComputedStyle) {
                        realStyle = window.getComputedStyle(node, null)[styleName];
                    }
                    else if (node.currentStyle) {
                        realStyle = node.currentStyle[styleName];
                    }
                    return realStyle;
                },
                //获取设置元素的样式
                setCss: function (node, css) {
                    for (var key in css) {
                        node.style[key] = css[key];
                    }
                }
            };

            //#region 拖拽元素类
            function DragElement(node) {
                this.node = node;
                this.x = 0;
                this.y = 0;
            }
            DragElement.prototype = {
                constructor: DragElement,
                init: function () {                    
                    this.setEleCss({
                        "left": dom.getStyle(node, "left"),
                        "top": dom.getStyle(node, "top")
                    })
                    .setXY(node.style.left, node.style.top);
                },
                setXY: function (x, y) {
                    this.x = parseInt(x) || 0;
                    this.y = parseInt(y) || 0;
                    return this;
                },
                setEleCss: function (css) {
                    dom.setCss(this.node, css);
                    return this;
                }
            }
            //#endregion

            //#region 鼠标元素
            function Mouse() {
                this.x = 0;
                this.y = 0;
            }
            Mouse.prototype.setXY = function (x, y) {
                this.x = parseInt(x);
                this.y = parseInt(y);
            }
            //#endregion

            //拖拽配置
            var draggableConfig = {
                zIndex: 1,
                draggingObj: null,
                mouse: new Mouse()
            };

            function Drag(ele) {
                this.ele = ele;

                function mouseDown(event) {
                    event.stopPropagation();
                    var ele = event.target || event.srcElement;

                    draggableConfig.mouse.setXY(event.clientX, event.clientY);

                    draggableConfig.draggingObj = new DragElement(ele);
                    draggableConfig.draggingObj
                        .setXY(ele.style.left, ele.style.top)
                        .setEleCss({
                            "zIndex": draggableConfig.zIndex++
                        });
                }                

                ele.onselectstart = function () {
                    //防止拖拽对象内的文字被选中
                    return false;
                }
                dom.on(ele, "mousedown", mouseDown);
            }

            dom.on(document, "mousemove", function (event) {
                event.stopPropagation();
                var we = window.event; 
                var maxboxw = $('.maxbox').width();
                var imgleftx = $('#moveimg').offset().left;
                var imgleft = (maxboxw - imgx)/2;
                var x = event.clientX - imgleftx + imgleft -5;

                if (draggableConfig.draggingObj) {
                    var mouse = draggableConfig.mouse,
                        draggingObj = draggableConfig.draggingObj;
                    draggingObj.setEleCss({
                        "position": "fixed",
                        /*"left": parseInt(event.clientX - imgleftx + imgleft +30) + "px",*/
                        /*"top": parseInt(event.clientY - mouse.y + draggingObj.y) + "px"*/
                        "left": parseInt(event.clientX) + "px",
                        "top": parseInt(event.clientY) + "px"
                    });
                    var lcname = draggingObj.node.className;
                    var lt = $("#removediv").offset().top;
                    var ll = $(".maxbox").offset().left;
                    if(lcname.indexOf('lineH') != -1){
                        draggingObj.setEleCss({
                            "top": lt + "px",
                            "transform": "translateY(0)"
                        });
                    }
                    if(lcname.indexOf('lineV') != -1){
                        if(w<1200){
                            draggingObj.setEleCss({
                                "left": ll + "px",
                                "transform": "translateX(0)"
                            });
                        }else
                        draggingObj.setEleCss({
                            "left": ll-40 + "px",
                            "transform": "translateX(0)"
                        });
                    }
                }
                

            })

            dom.on(document, "mouseup", function (event) {
                draggableConfig.draggingObj = null;
            })


            window.Drag = Drag;
        })(window, undefined);



$(function () { 
            $('[data-toggle="popover"]').popover({
                trigger : "focus "
            })
            $('[data-toggle="popover"]').popover("show").on("mousemove", function () {
                var _this = this;
                $(this).popover("show");
            });
            /*$('[data-toggle="popover"]').popover({
                html: true,
                trigger : 'click',
                content: "<div class='tooltipnum'><input type='text'/>mm<button class='btn btn-default'>確認</button></div>"
            }).on("mousedown", function () {
                var _this = this;
                $(this).popover("show");
                $(this).siblings(".popover").on("mousemove", function () {
                     $(this).popover("show");
                });
            }).on("mousemove", function () {
                var _this = this;
                $(this).popover("show");
                $(this).siblings(".popover").on("mouseleave", function () {
                    $(_this).popover('hide');
                });
            }).on("mouseup", function () {
                var _this = this;
                setTimeout(function () {
                    if (!$(".popover:hover").length) {
                        $(_this).popover("hide")
                    }
                }, 100);
            });*/
        });

        var h = $(window).height();
        var w = $(window).width();
        var headerh = $('header.navbar').height();
        $("#container").css("height",h-headerh-14);
        var ch = $("#container").height();
        var cw = $("#content").width();
        var leftw = $(".leafbar").width();
        $(".leafbar").css("min-height",ch);
        $("#moveimg img").css("max-height",ch-300);

        var imgx = $('#moveimg').width();
        $('.imgrange-max').html(imgx+"mm");




        //旋转
            var spin_n = 0;
            var imgwrapw = $(".maxbox").width();
            var imgwraph = $(".maxbox").height();
            var imgh = $("#moveimg img").height();
            var imgw = $("#moveimg img").width();
            var maxh = $("#removediv").height();
            $(".lineH").css("height",imgwraph+140);
            $(".lineV").css("width",imgwrapw+140);

            if(w<1200){
                $(".lineV").css("width",imgwrapw+60);
            }

            $(".mask-clockwise").click(function () {
                spin_n += 90;
                $("#moveimg").css({
                    "transform":"rotate("+ spin_n +"deg)",
                    "-moz-transform":"rotate("+ spin_n +"deg)",
                    "-ms-transform":"rotate("+ spin_n +"deg)",
                    "-o-transform":"rotate("+ spin_n +"deg)",
                    "-webkit-transform":"rotate("+ spin_n +"deg)"
                });
                var rotateDegree = spin_n % 180;
                var r = /[1-9]+\d*/;
                if ( imgw>maxh && r.test(rotateDegree)) {
                    $("#moveimg img").css("width",imgh);
                    $("#moveimg").css("margin-top","70px");
                    $(".maxbox").css("height",imgh+140);
                    var maaaw=$(".maxbox").width();
                    $(".lineV").css("width",maaaw+140);
                } else {
                    $("#moveimg img").css("width",imgwrapw);
                    $("#moveimg").css("margin-top","0px");
                    $(".maxbox").css("height","auto");
                    var maahw=$(".maxbox").width();
                    $(".lineV").css("width",maahw+140);
                }
            });
            $(".mask-counterclockwise").click(function () {
                spin_n -= 90;
                $("#moveimg").css({
                    "transform":"rotate("+ spin_n +"deg)",
                    "-moz-transform":"rotate("+ spin_n +"deg)",
                    "-ms-transform":"rotate("+ spin_n +"deg)",
                    "-o-transform":"rotate("+ spin_n +"deg)",
                    "-webkit-transform":"rotate("+ spin_n +"deg)"
                });
                
                var rotateDegree = spin_n % 180;
                var r = /[1-9]+\d*/;
                if ( imgw>maxh && r.test(rotateDegree)) {
                    $("#moveimg img").css("width",imgh);
                    $("#moveimg").css("margin-top","70px");
                    $(".maxbox").css("height",imgh+140);
                } else {
                    $("#moveimg img").css("width",imgwrapw);
                    $("#moveimg").css("margin-top","0px");
                    $(".maxbox").css("height","auto");
                }
                
            });

        //缩放
            var zoom_n = 1;
            $(".mask-out").click(function () {
                zoom_n += 0.1;
                $(".mask-layer-imgbox img").css({
                    "transform": "scale(" + zoom_n + ")",
                    "-moz-transform": "scale(" + zoom_n + ")",
                    "-ms-transform": "scale(" + zoom_n + ")",
                    "-o-transform": "scale(" + zoom_n + ")",
                    "-webkit-": "scale(" + zoom_n + ")"
                });
            });
            $(".mask-in").click(function () {
                zoom_n -= 0.1;
                console.log(zoom_n)
                if (zoom_n <= 0.1) {
                    zoom_n = 0.1;
                    $(".mask-layer-imgbox img").css({
                        "transform":"scale(.1)"/*,
                        "-moz-transform":"scale(.1)",
                        "-ms-transform":"scale(.1)",
                        "-o-transform":"scale(.1)",
                        "-webkit-transform":"scale(.1)"*/
                    });
                } else {
                    $(".mask-layer-imgbox img").css({
                        "transform": "scale(" + zoom_n + ")"/*,
                        "-moz-transform": "scale(" + zoom_n + ")",
                        "-ms-transform": "scale(" + zoom_n + ")",
                        "-o-transform": "scale(" + zoom_n + ")",
                        "-webkit-transform": "scale(" + zoom_n + ")"*/
                    });
                }
            });