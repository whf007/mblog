//package com.mtons.mblog.utils;/**
// * Created by Administrator on 2019/6/27.
// */
//
///**
// * @program: mblog
// * @description:
// * @author: whf
// * @create: on 2019/6/27.
// **/
//public class OasrRequestSample {
//    private OasrRequesterSender oasrRequesterSender = new OasrRequesterSender();
//
//    static {
//        initBaseParameters();
//    }
//
//    public static void main(String[] args) {
//        OasrRequestSample rasrRequestSample = new OasrRequestSample();
//        rasrRequestSample.start();
//    }
//
//    private void start() {
//        this.sendUrlRequest();
//        System.exit(0);
//    }
//
//    /**
//     * 指定语音文件的Url，发出请求。建议使用此方法。
//     */
//    private void sendUrlRequest() {
//        OasrBytesRequest oasrBytesRequest = new OasrBytesRequest("http://xxx.xx.xxx",
//                "https://xuhai2-1255824371.cos.ap-chengdu.myqcloud.com/test.wav");
//        OasrResponse oasrResponse = this.oasrRequesterSender.send(oasrBytesRequest);
//        this.printReponse(oasrResponse);
//    }
//
//    private void printReponse(OasrResponse oasrResponse) {
//        if (oasrResponse != null)
//            System.out.println("Result is: " + oasrResponse.getOriginalText());
//        else
//            System.out.println("Result is null.");
//    }
//
//    /**
//     * 初始化基础参数, 请将下面的参数值配置成您自己的值。
//     *
//     * 参数获取方法可参考： <a href="https://cloud.tencent.com/document/product/441/6203">签名鉴权 获取签名所需信息</a>
//     */
//    private static void initBaseParameters() {
//        AsrInternalConfig.SUB_SERVICE_TYPE = 0; // 0表示离线识别
//        // optional，根据自身需求配置值
//        AsrPersonalConfig.engineModelType = EngineModelType._8k_0;
//        AsrPersonalConfig.voiceFormat = VoiceFormat.wav;
//    }
//}
