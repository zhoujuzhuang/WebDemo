/**
 * Created by TF on 2018/1/15.
 */
var len = $("img[modal='zoomImg']").length;
var arrPic = new Array(); //定义一个数组
for (var i = 0; i < len; i++) {
    arrPic[i] = $(".reviewzoomImg").find("img[modal='zoomImg']").eq(i).prop("src"); //将所有img路径存储到数组中
}

var bheight=document.body.clientHeight;
$(".reviewcontent").css("height",bheight-100);
var conheight=$(".reviewcontent").height();
$(".cd-item-wrapper").css("height",conheight-140);
$(".reviewzoomImg").css("height",conheight-66);

var btnprev = $("#btnprevitem");
var btnnext = $("#btnnextitem");
$("img[modal='zoomImg']").each(function () {
    /*$(this).on("click", function () {*/
        //给body添加弹出层的html
       /* $(".mask-layer").remove();
        $("#masklayer").append("<div class=\"mask-layer\">" +
            "   <div class=\"mask-layer-container cd-gallery cd-container\">" +
            "       <div class=\"mask-layer-container-operate\">" +
            "           <button class=\"mask-out btn-default-styles\">放大</button>" +
            "           <button class=\"mask-in btn-default-styles\">缩小</button>" +
            "           <button class=\"mask-clockwise btn-default-styles\">顺旋转</button>" +
            "           <button class=\"mask-counterclockwise btn-default-styles\">逆旋转</button>" +
            "       </div>" +
            "       <ul class=\"mask-layer-imgbox auto-img-center cd-item-wrapper\"></ul>" +
            "   </div>" +
            "</div>"
        );

        var img_index = $("img[modal='zoomImg']").index(this); //获取点击的索引值
        var num = img_index;
        var num2 = img_index+1;
*/
        function showImg() {

            //上一款下一款
            var index = 0;
            $(".itemnumtotla").html(len/2);
            if(len>2){
                
                $("#btnprevitem").click(function () { 
                    index--;
                    if(index<0){
                        index=0;
                    }
                    var num = index*2;
                    $(".itemnum").html(index+1);
                    $(".reviewzoomImg ul").removeClass("current");
                    $(".reviewzoomImg ul:eq("+index+")").addClass("current");
                    $(".mask-layer-imgbox img").eq(0).prop("src", arrPic[num]); //给弹出框的Img赋值
                    $(".mask-layer-imgbox img").eq(1).prop("src", arrPic[num+1]); //给弹出框的Img赋值*/
                });
                $("#btnnextitem").click(function () {
                    index++;
                    if(index===len/2){
                        index=1;
                    }
                    var num = index*2;
                    $(".itemnum").html(index+1);
                    $(".reviewzoomImg ul").removeClass("current");
                    $(".reviewzoomImg ul:eq("+index+")").addClass("current");
                    $(".mask-layer-imgbox img").eq(0).prop("src", arrPic[num]); //给弹出框的Img赋值
                    $(".mask-layer-imgbox img").eq(1).prop("src", arrPic[num+1]); //给弹出框的Img赋值*/
                });
            }
            else{};

            //图片居中显示
            var box_width = $(".auto-img-center").width(); //图片盒子宽度
            var box_height = $(".auto-img-center").height();//图片高度高度
            var initial_width = $(".auto-img-center img").width();//初始图片宽度
            var initial_height = $(".auto-img-center img").height();//初始图片高度
            if (initial_width > initial_height) {
                var imgwidth = box_width * 0.4;
                $(".auto-img-center img").css("width", imgwidth);
                $(".auto-img-center img").css("height", "auto");
                var last_imgHeight = $(".auto-img-center img").height();
                $(".auto-img-center img").css("margin-top", -(last_imgHeight - box_height) / 2);
            } else {
                var imgheight = box_height * 0.6;
                $(".auto-img-center img").css("height", imgheight);
                $(".auto-img-center img").css("width", "auto");
                var last_imgHeight = $(".auto-img-center img").height();
                $(".auto-img-center img").css("margin-top", -(last_imgHeight - box_height) / 2);
            }

            //图片拖拽
            var $div_img = $(".mask-layer-imgbox li");
            //绑定鼠标左键按住事件
            $div_img.bind("mousedown", function (event) {
                event.preventDefault && event.preventDefault(); //去掉图片拖动响应
                //获取需要拖动节点的坐标
                var offset_x = $(this)[0].offsetLeft;//x坐标
                var offset_y = $(this)[0].offsetTop;//y坐标
                //获取当前鼠标的坐标
                var mouse_x = event.pageX;
                var mouse_y = event.pageY;
                //绑定拖动事件
                //由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素
                $(".mask-layer-imgbox").bind("mousemove", function (ev) {
                    // 计算鼠标移动了的位置
                    var _x = ev.pageX - mouse_x;
                    var _y = ev.pageY - mouse_y;
                    //设置移动后的元素坐标
                    var now_x = (offset_x + _x ) + "px";
                    var now_y = (offset_y + _y ) + "px";
                    //改变目标元素的位置
                    $div_img.css({
                        top: now_y,
                        left: now_x
                    });
                });
            });
            //当鼠标左键松开，接触事件绑定
            $(".mask-layer-imgbox").bind("mouseup", function () {
                $(this).unbind("mousemove");
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
            //旋转
            var spin_n = 0;
            $(".mask-clockwise").click(function () {
                spin_n += 90;
                $(".mask-layer-imgbox img").parent("li").css({
                    "transform":"rotate("+ spin_n +"deg)",
                    "-moz-transform":"rotate("+ spin_n +"deg)",
                    "-ms-transform":"rotate("+ spin_n +"deg)",
                    "-o-transform":"rotate("+ spin_n +"deg)",
                    "-webkit-transform":"rotate("+ spin_n +"deg)"
                });
            });
            $(".mask-counterclockwise").click(function () {
                spin_n -= 90;
                $(".mask-layer-imgbox img").parent("li").css({
                    "transform":"rotate("+ spin_n +"deg)",
                    "-moz-transform":"rotate("+ spin_n +"deg)",
                    "-ms-transform":"rotate("+ spin_n +"deg)",
                    "-o-transform":"rotate("+ spin_n +"deg)",
                    "-webkit-transform":"rotate("+ spin_n +"deg)"
                });
            });
            //关闭
            /*$(".mask-close").click(function () {
                $(".mask-layer").remove();
            });
            $(".mask-layer-black").click(function () {
                $(".mask-layer").remove();
            });*/
        }
        showImg();

        //下一张
       /* $(".mask-next").on("click", function () {
            $(".mask-layer-imgbox p img").remove();
            num++;
            if (num == len) {
                num = 0;
            }
            showImg();
        });*/
        //上一张
       /* $(".mask-prev").on("click", function () {
            $(".mask-layer-imgbox p img").remove();
            num--;
            if (num == -1) {
                num = len - 1;
            }
            showImg();
        });*/
   /* })*/
});