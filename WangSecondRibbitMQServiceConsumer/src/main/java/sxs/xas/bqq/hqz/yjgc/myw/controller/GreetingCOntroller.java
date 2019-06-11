//package sxs.xas.bqq.hqz.yjgc.myw.controller;
//
//import java.security.Principal;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.concurrent.ConcurrentHashMap;
//
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.messaging.simp.annotation.SendToUser;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.context.request.async.DeferredResult;
//import org.springframework.web.util.HtmlUtils;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import sxs.xas.bqq.hqz.yjgc.myw.entity.ClientMessage;
//import sxs.xas.bqq.hqz.yjgc.myw.entity.VtmInformation;
//import sxs.xas.bqq.hqz.yjgc.myw.service.TestService;
//import sxs.xas.bqq.hqz.yjgc.myw.utils.VtaStatus;
//
//@Controller
//@EnableScheduling
//public class GreetingCOntroller {
//	private static Logger LOG = LoggerFactory.getLogger(GreetingCOntroller.class);
//	
//	private Map<String, DeferredResult<Boolean>> watchRequests =new ConcurrentHashMap<String, DeferredResult<Boolean>>();	 
//
//	@Autowired
//	Map<String, VtaStatus> users;
//	
//	@Autowired
//	Map<String, String> applications;
//
//	@Autowired
//	ObjectMapper objectMapper;
//
//	@Autowired
//	private SimpMessagingTemplate template;
//
//	@Autowired
//	private TestService tt;
//
//	@MessageMapping("/hello")
//	@SendTo("/topic/greetings")
//	public ClientMessage greeting(ClientMessage message) throws Exception {
//		// 模拟延时，以便测试客户端是否在异步工作
//		Thread.sleep(1000);
//		return new ClientMessage("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//	}
//
//	@MessageMapping("/receiveCall")
//	public void myself(Principal principal, ClientMessage message) throws Exception {
//		// 模拟延时，以便测试客户端是否在异步工作
//		Thread.sleep(1000);
//		System.out.println(principal.getName());
//		if (message.getName().startsWith("RECEIVE CALL")) {
//			for (Entry<String, VtaStatus> vta : users.entrySet()) {
//				if (vta.getKey().equals(principal.getName())) {
//					vta.setValue(VtaStatus.FIRST);
////					applications.
////					template.convertAndSendToUser(vta.getKey(), "/userTest/other", new ClientMessage(message.getName()+" SUCCESS"));
//				}else{
//					template.convertAndSendToUser(vta.getKey(), "/userTest/other", new ClientMessage("STOP FLICHER"));
//				}
//			}
//		}else{
//			template.convertAndSendToUser(principal.getName(), "/userTest/other", new ClientMessage("UPDATE BUTTON"));
//		}
//	}
//
//	@MessageMapping("/other/{otherName}")
//	@SendToUser("/userTest/other")
//	public void othersSelf(@DestinationVariable(value = "otherName") String otherName, ClientMessage message)
//			throws Exception {
//		// 模拟延时，以便测试客户端是否在异步工作
//
//		Thread.sleep(1000);
//		/*
//		 * Map<String, Object> headers = new HashMap<>();
//		 * headers.put("simpMessageType", "MESSAGE");
//		 * headers.put("conversionHint", "method 'myself' parameter -1");
//		 * headers.put("contentType", "application/json;charset=UTF-8");
//		 * headers.put("simpSessionId", "si1tgt5h");
//		 */ template.convertAndSendToUser(otherName, "/userTest/other", HtmlUtils.htmlEscape(message.getName()));
//
//		// return new ClientMessage("Hello, " +
//		// HtmlUtils.htmlEscape(message.getName()) + "!");
//	}
//
//	/**
//	 * VTA 登录
//	 * 
//	 * @param session
//	 * @param model
//	 * @param username
//	 * @param password
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(path = "/login", method = RequestMethod.GET)
//	public String login(HttpSession session, Model model, @RequestParam("username") String username,
//			@RequestParam("password") String password) throws Exception {
//		model.addAttribute("groupId", "user_groupId");
//		model.addAttribute("session_id", session.getId());
//		System.out.println("跳转：" + session.getId());
//		session.setAttribute("loginName", username);
//		return "/inde22x.html";
//	}
//
//	/**
//	 * VTM call
//	 * 
//	 * @param session
//	 * @param model
//	 * @param username
//	 * @param password
//	 * @return
//	 * @throws Exceptio
//	 */
//	@RequestMapping(path = "/vtmcall", method = RequestMethod.POST)
//	@ResponseBody
//	public DeferredResult<Boolean> vtmCall(@RequestBody VtmInformation vtminformation) throws Exception {
//		// Thread.sleep(2000);
//		
//		DeferredResult<Boolean> result = new DeferredResult<>(5000l);
//		
//		result.onCompletion(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				System.out.println("success");
//			}
//		});
//		
//		result.onTimeout(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("无人接听");
//			}
//		});
//		if(watchRequests.containsKey(vtminformation.getVtmId())){
//			watchRequests.replace(vtminformation.getVtmId(), result)
//		}else{
//			watchRequests.(vtminformation.getVtmId(), result);
//		}
//		return result;
//	}
//}
