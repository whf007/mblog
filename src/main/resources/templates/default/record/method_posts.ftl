<#include "/default/inc/layout.ftl"/>
<style>
    img{
        width: 350px;
        height: 250px;
        padding: 5px 5px 10px 5px;
        background: white;
        position: absolute;
        -webkit-transition: 0.5s;
        -moz-transition: 0.5s;
        transition: 0.5s;
        z-index: 1;
    }
    .img_1{
        top:0px;
        left: 220px;
        -webkit-transform: rotate(20deg);
        -moz-transform: rotate(20deg);
        transform: rotate(20deg);
    }
    .img_2{
        top:0px;
        left: 20px;
        -webkit-transform: rotate(-10deg);
        -moz-transform: rotate(-10deg);
        transform: rotate(-10deg);
    }
    .img_3{
        top:50px;
        right: 40px;
        -webkit-transform: rotate(-5deg);
        -moz-transform: rotate(-5deg);
        transform: rotate(-5deg);
    }
    .img_4{
        top:50px;
        right: 100px;
        -webkit-transform: rotate(30deg);
        -moz-transform: rotate(30deg);
        transform: rotate(30deg);
    }
    .img_5{
        top:200px;
        left: 300px;
        -webkit-transform: rotate(-15deg);
        -moz-transform: rotate(-15deg);
        transform: rotate(-15deg);
    }
    .img_6{
        top:200px;
        left: 50px;
        -webkit-transform: rotate(-20deg);
        -moz-transform: rotate(-20deg);
        transform: rotate(-20deg);
    }
    .img_7{
        bottom:50px;
        right: 30px;
        -webkit-transform: rotate(30deg);
        -moz-transform: rotate(30deg);
        transform: rotate(30deg);
    }
    img:hover{
        -webkit-transform: rotate(0deg);
        -moz-transform: rotate(0deg);
        transform: rotate(0deg);
        box-shadow: 10px 10px 15px gray;
        z-index: 2;
    }
</style>
<@layout user.name + "的文章">
<div class="row users-show">
    <div class="col-xs-12 col-md-12 side-left">
        <div class="container">
            <img src="/dist/lulu/1.jpg" class="img_1">
            <img src="/dist/lulu/2.jpg" class="img_2">
            <img src="/dist/lulu/3.jpg" class="img_3">
            <img src="/dist/lulu/4.jpg" class="img_4">
            <img src="/dist/lulu/5.jpg" class="img_5">
            <img src="/dist/lulu/6.jpg" class="img_6">
            <img src="/dist/lulu/7.jpg" class="img_7">
        </div>
    </div>

</div>
<!-- /end -->

<script type="text/javascript">

</script>
</@layout>