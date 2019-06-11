package sxs.xas.bqq.hqz.yjgc.myw.service;

import java.util.Queue;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VtaQueueService {
	
	@Resource(name="vtaQueue")
	Queue<String> queue;
	
	/**
	 * agent 注册切换Ready
	 * @param agentCode
	 * @return
	 */
	public Queue<String> add(String agentCode){
		
		if(queue.contains(queue)){
			
		}else{
			queue.add(agentCode);
		}
		return queue;
	}
	
	/**
	 * 移除 agent 排队
	 * @param agentCode
	 * @return
	 */
	public Queue<String> remove(String agentCode){

		queue.remove(agentCode);
		return queue;
	}
	/**
	 * 获得第一个注册的Agent
	 * @return
	 */
	public String getFirst(){
		return queue.remove();
	}
	
}
