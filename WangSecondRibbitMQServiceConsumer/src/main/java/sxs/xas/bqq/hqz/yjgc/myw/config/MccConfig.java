package sxs.xas.bqq.hqz.yjgc.myw.config;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import sxs.xas.bqq.hqz.yjgc.myw.utils.VtaStatus;
import sxs.xas.bqq.hqz.yjgc.myw.utils.VtaStatusAndOrderDetail;

@Configuration
public class MccConfig {
	
	/**
	 * 排队队列
	 * @return
	 */
	@Bean
	public Queue<String> vtaQueue(){
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
		return queue;
	}
	/**
	 * 排队队列
	 * @return
	 */
	@Bean
	public Map<String,String> vtmQueue(){
		Map<String,String> vtmQueue = new ConcurrentSkipListMap<>();
		return vtmQueue;
	}
	/**
	 * 当前Agent的状态，及编号
	 * @return
	 */
	@Bean
	public Map<String, VtaStatusAndOrderDetail> vtaStatusAndOrderDetails() {
		Map<String, VtaStatusAndOrderDetail> concurrentHashMap = new ConcurrentHashMap<>();
		return concurrentHashMap;
	}
	
	
	/**
	 * VTM call 的响应集合
	 */
	@Bean
	public Map<String, DeferredResult<String>> vtmWatchRequests(){
		Map<String, DeferredResult<String>> vtmWatchRequests =new ConcurrentHashMap<String, DeferredResult<String>>();
		return vtmWatchRequests;	 
	}

	/**
	 * VTA 等待 的响应集合
	 */
	@Bean
	public  Map<String, DeferredResult<String>> vtAWatchRequests(){
		Map<String, DeferredResult<String>> vtAWatchRequests =new ConcurrentHashMap<String, DeferredResult<String>>();	 
		return vtAWatchRequests;
	}
	
	/**
	 * VTM call等待 的响应集合
	 */
	@Bean
	public  Map<String, String> vtmDetails(){
		Map<String, String> vtmDetails =new ConcurrentHashMap<String, String>();	 
		return vtmDetails;
	}

}
