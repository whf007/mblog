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
            width: 100%;
            height: 300px;
        }
        #tabContent {
            height:213px;
        }
        .messages {
            height:100%;
            overflow:auto;
        }
    }
    @media (min-width: 467px) {
        #my-player {
            width: 100%;
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
            width: 100%;
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

    .side-radio{
        width: 1100%;
    }
</style>
<@layout user.name + "的文章">
<div class="row users-show">

<div class="col-xs-12 col-md-12 side-radio">
<div class="panel panel-default">
    <#if profile??>
        <div class="panel-heading">发表的文章</div>
        <div class="panel-body">
            <form enctype="multipart/form-data" action="/radio/upload" method="POST">
                <!--<input id="kv-explorer" type="file" multiple>-->
                <input id="file" name="file" type="file" class="file" data-show-preview="false">
                <br>

                <button type="button" class="btn btn-primary" id="btn">提交</button>
                <button type="button" class="btn btn-primary" id="download">
                    <div class="loader">
                        <div class="face">
                            <div class="circle"></div>
                        </div>
                        <div class="face">
                            <div class="circle"></div>
                        </div>
                    </div>
                </button>
            </form>
            <div class="progress" style="width: 500px">
                <div id="progress-bar" class="progress-bar progress-bar-success progress-bar-striped" role="progressbar"
                     aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
                    <span class="sr-only">40% Complete (success)</span>
                </div>
            </div>
        </div>

    <#else>
    <div class="col-xs-12  side-right">
        请先完成<a href="${base}/login" class="btn btn-default btn-sm signup">登录</a>
    </div>
    </#if>
</div>
<!-- /end -->
<link rel="stylesheet" href="${base}/dist/css/loading.css"></link>
<script src="${base}/dist/js/chat.js" charset="utf-8"></script>
    <script>
        $("#download").hide();
        $(function () {
            $("#btn").on('click', function () {
                UploadFile();
            });

            $("#file").change(function () {
                $("#progress-bar").css("width", 0);
            });

            // ajax + jQuery上传
            function UploadFile() {
                var xhrOnProgress = function (fun) {
                    xhrOnProgress.onprogress = fun; //绑定监听
                    //使用闭包实现监听绑
                    return function () {
                        //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
                        var xhr = $.ajaxSettings.xhr();
                        //判断监听函数是否为函数
                        if (typeof xhrOnProgress.onprogress !== 'function')
                            return xhr;
                        //如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
                        if (xhrOnProgress.onprogress && xhr.upload) {
                            xhr.upload.onprogress = xhrOnProgress.onprogress;
                        }
                        return xhr;
                    }
                }

                var file = $("#file")[0].files[0];
                var form = new FormData();
                form.append('file', file);
                $.ajax({
                    type: 'POST',
                    url: '/radio/upload',
                    data: form,
                    processData: false,  // 告诉jquery不转换数据
                    contentType: false,  // 告诉jquery不设置内容格式
                    xhr: xhrOnProgress(function (e) {
                        var percent = e.loaded / e.total;
                        $("#progress-bar").css("width", (percent * 500));
                    }),

                    success: function (arg) {
                        $("#download").show();
                        console.log(arg);
                        DiGui();
                    }
                })
            }
        });
        var status = true;
        DiGui = function (param) {
            $.ajax({
                type: 'GET',
                url: '/radio/download',
                success: function (arg) {
                    console.log(arg);
                    status= false;
                    window.setInterval("DiGui()", 5000);
                }
            });
            }
    </script>
</@layout>