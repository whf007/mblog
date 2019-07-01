package com.mtons.mblog.sdk;

import com.mtons.mblog.sdk.http.OasrRequesterSender;
import com.mtons.mblog.sdk.model.OasrBytesRequest;
import com.mtons.mblog.sdk.model.OasrResponse;
import com.tencent.cloud.asr.realtime.sdk.config.AsrBaseConfig;
import com.tencent.cloud.asr.realtime.sdk.config.AsrInternalConfig;
import com.tencent.cloud.asr.realtime.sdk.config.AsrPersonalConfig;
import com.tencent.cloud.asr.realtime.sdk.model.enums.EngineModelType;
import com.tencent.cloud.asr.realtime.sdk.model.enums.VoiceFormat;
import com.tencent.cloud.asr.realtime.sdk.utils.ByteUtils;

//import com.tencent.cloud.asr.realtime.sdk.utils.ByteUtils;

/**
 * 录音文件识别（即：以前的离线识别）请求实例，可运行。
 * 
 * <pre>
 * 并发说明：
 * 用户可根据根据需要自行改成多线程版本： 每个线程都通过new OasrRequesterSender()创建出自己的sender对象，
 * 然后用自己的sender对象发送请求获得回执，线程间互不干扰。从而形成并发的效果，加快请求发出的速度。 
 * 此代码写起来很简单，暂时就不写到本例子中了。如确有需要让我们提供并发实例，欢迎随时联络我们。
 * </pre>
 * 
 * @author iantang
 * @version 1.0
 */
public class OasrRequestSample {

	private OasrRequesterSender oasrRequesterSender = new OasrRequesterSender();

	static {
		initBaseParameters();
	}

	public static void main(String[] args) {
		OasrRequestSample rasrRequestSample = new OasrRequestSample();
		String path = "";
		rasrRequestSample.start(path);
	}

	public OasrResponse start(String path) {
//		this.sendUrlRequest();
		 return this.sendBytesRequest(path);
//		System.exit(0);
	}

	/**
	 * 指定语音文件的Url，发出请求。建议使用此方法。
	 */
//	private void sendUrlRequest() {
//		OasrBytesRequest oasrBytesRequest = new OasrBytesRequest("http://xxx.xx.xxx",
//				"https://xuhai2-1255824371.cos.ap-chengdu.myqcloud.com/test.wav");
//		// oasrBytesRequest.setChannelNum(2); //设置为2声道语音，默认为1声道。目前仅8K语音支持2声道。
//		OasrResponse oasrResponse = this.oasrRequesterSender.send(oasrBytesRequest);
//		this.printReponse(oasrResponse);
//	}

	/**
	 * 从文件中读取语音数据，通过HttpBody发出请求。语音文件大小需小于5兆才可使用此方法。
	 */
	 private OasrResponse sendBytesRequest(String path) {
	 byte[] content = ByteUtils.inputStream2ByteArray(path);
	 OasrBytesRequest oasrBytesRequest = new OasrBytesRequest("http://www.whfrecord.com", content);
	 // oasrBytesRequest.setChannelNum(2); //特别设置为2声道，默认为1声道。目前仅8K语音支持2声道。
	 OasrResponse oasrResponse = this.oasrRequesterSender.send(oasrBytesRequest);
	 this.printReponse(oasrResponse);
	 return oasrResponse;
	 }

	private void printReponse(OasrResponse oasrResponse) {
		if (oasrResponse != null)
			System.out.println("Result is: " + oasrResponse.getOriginalText());
		else
			System.out.println("Result is null.");
	}

	/**
	 * 初始化基础参数, 请将下面的参数值配置成你自己的值。
	 * 
	 * 参数获取方法可参考： <a href="https://cloud.tencent.com/document/product/441/6203">签名鉴权 获取签名所需信息</a>
	 */
	private static void initBaseParameters() {
		// required, 必须配置
		 AsrBaseConfig.appId = "1259075518";
		 AsrBaseConfig.secretId = "AKIDey3uVqer0tZ0C9pIXXHp8C1f4FPKoXnn";
		 AsrBaseConfig.secretKey = "ub5KK9i3C7rOfgV6UeZGjxvhEWZMvGMC";
		AsrInternalConfig.SUB_SERVICE_TYPE = 0; // 0表示离线识别

		// optional，根据自身需求配置值
		AsrPersonalConfig.engineModelType = EngineModelType._8k_0;
		AsrPersonalConfig.voiceFormat = VoiceFormat.wav;

		// optional 可忽略
		// AsrBaseConfig.PRINT_CUT_REQUEST = true; // 是否打印中间的每个分片请求到控制台
		// AsrBaseConfig.PRINT_CUT_RESPONSE = true; // 是否打印中间的每个分片请求的结果到控制台
		// 默认使用自定义连接池，连接数可在AsrGlobelConfig中修改，更多细节参数，可直接修改源码HttpPoolingManager.java,然后自行打Jar包。
		// AsrGlobelConfig.USE_CUSTOM_CONNECTION_POOL = true;
	}

}
