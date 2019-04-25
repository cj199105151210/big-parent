package com.bosssoft.bigdata.common.transaction.rewrite;

import com.codingapi.tx.netty.service.TxManagerHttpRequestService;
import com.lorne.core.framework.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: bigdata
 * @Description:请求tx-manager接口实现
 * @Date: Created in 17:05 2019/3/10
 * @Modified By:
 */
@Slf4j
@Service
public class TxManagerHttpRequestServiceImpl implements TxManagerHttpRequestService {

	@Override
	public String httpGet(String url) {
		String res = HttpUtils.get(url);
		log.debug("Get请求接口 {}，响应报文：{}", url, res);
		return res;
	}

	@Override
	public String httpPost(String url, String params) {
		String res = HttpUtils.post(url, params);
		log.debug("Post请求接口 {}，响应报文：{}", url, res);
		return res;
	}
}
