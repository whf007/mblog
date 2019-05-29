<#include "/classic/inc/layout.ftl"/>
<link href="http://vjs.zencdn.net/5.19/video-js.min.css" rel="stylesheet">
<!--引入播放器js-->
<script src="http://vjs.zencdn.net/5.19/video.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/videojs-flash@2/dist/videojs-flash.min.js"></script>
<script src="//qzonestyle.gtimg.cn/open/qcloud/video/live/h5/live_connect.js" charset="utf-8"></script>
<style>
    .nav {
        background-color: #fff;
    }

    @media (min-width: 267px) {
        #my-player{
            width: 200px;
            height: 300px;
        }
        #tabContent {
            height:213px;
        }
        .messages {
            height:514px;
            overflow:auto;
        }
    }
    @media (min-width: 467px) {
        #my-player {
            width: 400px;
            height: 300px;
        }
        #tabContent
        {
            height:514px;
        }
        .messages {
            height:514px;
            overflow:auto;
        }
    }

    @media (min-width: 767px) {
        #my-player {
            width: 650px;
            height: 500px;
        }
        #tabContent {
            height:514px;
        }
        .messages {
            height:514px;
            overflow:auto;
        }
    }
    .side-left{
        border:solid 1px #f0f0f0;
        padding-bottom: 0;
        margin: 0;
        background-color: #fff;
    }
    #menuTabs,#chatinput{
        width: 110%;
        margin-left: -14px;
    }

</style>
<@layout user.name + "的文章">
<div class="row users-show">

<div class="col-xs-12 col-md-9 side-right">
<div class="panel panel-default">
    <#if profile??>
        <div class="panel-heading">发表的文章</div>
        <div class="panel-body">
            <video id="my-player" class="video-js vjs-default-skin vjs-big-play-centered" controls preload="auto"
                   autoplay="autoplay"
                   poster="//vjs.zencdn.net/v/oceans.png" data-setup='{}'>
                <!--src: 规定媒体文件的 URL  type:规定媒体资源的类型-->
                <#--<source src='rtmp://129.211.3.40:1935/rtmplive/room' type='rtmp/flv'/>-->
            <source src='rtmp://58.200.131.2:1935/livetv/hunantv' type='rtmp/flv'/>
            </video>
        </div>
        <a href="http://www.adobe.com/go/getflashplayer" id="start_flash" rel="nofollow" target="_blank"
           title="升级Flash插件"><span>启用flash</span></a>
    </div>
        <div id="id_video_container" style="width:100%; height:auto;"></div>
    </div>
        <div class="col-xs-12 col-md-3 side-left">
            <!--tabs-->
            <ul id="menuTabs" class="nav nav-pills nav-justified">
                <li class="active">
                    <a href="#discussion" data-toggle="tab"><i class="fa fa-tree"></i>互动聊天</a>
                </li>
                <li>
                    <a href="#members" data-toggle="tab"><i class="fa fa-tree"></i>现场嘉宾</a>
                </li>

            </ul>
            <!--内容滚动区域开始-->
            <div id="tabContent" class="tab-content">
                <div class="tab-pane fade active in" id="discussion" style="padding:10px;">
                    <div class="messages"">

                    </div>
                </div>
                <!--内容滚动区域结束-->
                <!--现场观众统计开始-->
                <div class="tab-pane fade in" style="padding-top: 10px;" id="members" style="padding:10px;">
                    <div class="inusername"">

                </div>
                </div>
                <!--现场观众统计结束-->
            </div>
            <div id="chatinput" class="input-group" style="margin-top: 5px;">
                <input type="text" class="form-control" v-model="messageinput"
                       placeholder="参与话题讨论" id="inputMessage">
                <span class="input-group-btn">
				        	<button class="btn btn-success" type="button" id="sendMessage">发送!</button>
				      </span>
            </div>
        </div>
    <#else>
    <div class="col-xs-12  side-right">
        请先完成<a href="${base}/login" class="btn btn-default btn-sm signup">登录</a>
    </div>
    </#if>
</div>
<!-- /end -->

<script src="${base}/dist/js/chat.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function () {
        // 设置flash路径,用于在videojs发现浏览器不支持HTML5播放器的时候自动唤起flash播放器
        videojs.options.flash.swf = 'https://cdn.bootcss.com/videojs-swf/5.4.1/video-js.swf';
        var player = videojs('my-player'); //my-player为页面video元素的id
        player.play();
        // player.pause()
        // player.pause()

    });
    function flashChecker() {
        var hasFlash = 0;　　　　 //是否安装了flash
        var flashVersion = 0;　　 //flash版本
        if (document.all) {
            var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
            if (swf) {
                hasFlash = 1;
                VSwf = swf.GetVariable("$version");
                flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);
            }
        } else {
            if (navigator.plugins && navigator.plugins.length > 0) {
                var swf = navigator.plugins["Shockwave Flash"];
                if (swf) {
                    hasFlash = 1;
                    var words = swf.description.split(" ");
                    for (var i = 0; i < words.length; ++i) {
                        if (isNaN(parseInt(words[i]))) {
                            continue;
                        }
                        flashVersion = parseInt(words[i]);
                    }
                }
            }
        }
        return {
            f: hasFlash,
            v: flashVersion
        };
    }
    var fls = flashChecker();
    if (fls.f) {
        document.write("您安装了flash,当前flash版本为: " + fls.v + ".x");
    } else {
        $("#start_flash span").show();
        document.write("您没有安装flash");
    }
    ;

    //var player = new qcVideo.Player("id_video_container", {
    //    "channel_id": "16093104850682282611",
    //    "app_id": "1251783441",
    //    "width" : 500,
    //    "height" : 400
    //});
</script>
</@layout>