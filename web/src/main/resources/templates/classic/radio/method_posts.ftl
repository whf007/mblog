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
        #my-player {
            width: 100%;
            height: 300px;
        }

        #tabContent {
            height: 213px;
        }

        .messages {
            height: 100%;
            overflow: auto;
        }
    }

    @media (min-width: 467px) {
        #my-player {
            width: 100%;
            height: 300px;
        }

        #tabContent {
            height: 514px;
        }

        .messages {
            height: 514px;
            overflow: auto;
        }
    }

    @media (min-width: 767px) {
        #my-player {
            width: 100%;
            height: 500px;
        }

        #tabContent {
            height: 514px;
        }

        .messages {
            height: 514px;
            overflow: auto;
        }
    }

    .side-left {
        border: solid 1px #f0f0f0;
        padding-bottom: 0;
        margin: 0;
        background-color: #fff;
    }

    #menuTabs, #chatinput {
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
                    <form id="submitForm" class="form" action="${base}/post/submit" method="post"
                          enctype="multipart/form-data">
                        <div class="upload-btn">
                            <label>
                                <span>点击上传文件</span>
                                <input id="upload_btn" type="file" name="file"  title="点击上传文件">
                                <input type="submit" >提交</input>
                            </label>
                        </div>
                    </form>
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


            //var player = new qcVideo.Player("id_video_container", {
            //    "channel_id": "16093104850682282611",
            //    "app_id": "1251783441",
            //    "width" : 500,
            //    "height" : 400
            //});
        </script>
</@layout>