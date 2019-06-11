package sxs.xas.bqq.hqz.yjgc.myw.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import sxs.xas.bqq.hqz.yjgc.myw.entity.VtmInformation;
import sxs.xas.bqq.hqz.yjgc.myw.service.VtaQueueService;
import sxs.xas.bqq.hqz.yjgc.myw.service.VtaStatusAndOrderService;
import sxs.xas.bqq.hqz.yjgc.myw.utils.VtaStatus;
import sxs.xas.bqq.hqz.yjgc.myw.utils.VtaStatusAndOrderDetail;

@RestController
@RequestMapping("/changlunxu")
public class ChangLunXunController {

	@Autowired
	public Map<String, String> vtmDetails;

	@Resource(name = "vtaQueue")
	public Queue<String> vtaQueue;

	@Resource(name = "vtmQueue")
	public Map<String, String> vtmQueue;

	@Autowired
	public Map<String, VtaStatusAndOrderDetail> vtaStatusAndOrderDetails;

	@Resource(name = "vtmWatchRequests")
	public Map<String, DeferredResult<String>> vtmWatchRequests;

	@Resource(name = "vtAWatchRequests")
	public Map<String, DeferredResult<String>> vtAWatchRequests;

	@Autowired
	public VtaQueueService vtaQueueService;

	@Autowired
	public VtaStatusAndOrderService vtaStatusAndOrderService;

	/**
	 * 
	 * @param vtminformation
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(path = "/vtmcall", method = RequestMethod.POST)
	@ResponseBody
	public DeferredResult<String> vtmCall(@RequestBody VtmInformation vtminformation) throws Exception {
		// Thread.sleep(2000);

		DeferredResult<String> result = new DeferredResult<>(50000l);

		vtmQueue.put(vtminformation.getVtmId(), "READY");

		String agentCode = null;

		if (vtaQueue.size() != 0) {

			agentCode = vtaQueue.remove();

			vtAWatchRequests.get(agentCode).setResult(vtminformation.getVtmId());
		}
		final String id = agentCode;
		if (vtmDetails.containsKey(vtminformation.getVtmId())) {
			vtmDetails.put(vtminformation.getVtmId(), vtminformation.getTransactionId());
		} else {
			vtmDetails.replace(vtminformation.getVtmId(), vtminformation.getTransactionId());
		}
		result.onCompletion(new Runnable() {

			@Override
			public void run() {
				System.out.println("success");
			}
		});

		result.onTimeout(new Runnable() {

			@Override
			public void run() {
				vtmQueue.remove(vtminformation.getVtmId());
				System.out.println("无人接听");
			}
		});
		if (vtmWatchRequests.containsKey(vtminformation.getVtmId())) {
			vtmWatchRequests.replace(vtminformation.getVtmId(), result);
		} else {
			vtmWatchRequests.put(vtminformation.getVtmId(), result);
		}
		return result;
	}

	@RequestMapping(path = "/vtacall/{vtaCode}", method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<String> vtaCall(@RequestParam("vtaCode") String vtaCode) throws Exception {
		// Thread.sleep(2000);

		DeferredResult<String> result = new DeferredResult<>(50000l);
		vtaQueueService.add(vtaCode);
		result.onCompletion(new Runnable() {

			@Override
			public void run() {
			}
		});

		result.onTimeout(new Runnable() {

			@Override
			public void run() {
				vtaQueue.remove(vtaCode);
				System.out.println("无人call");
			}
		});
		if (vtAWatchRequests.containsKey(vtaCode)) {
			vtAWatchRequests.replace(vtaCode, result);
		} else {
			vtAWatchRequests.put(vtaCode, result);
		}
		String vtmId = getFirstCanUseVtmId();
		if (vtmId != null) {
			result.setResult(vtmId);
			vtmQueue.replace(vtmId, "BUSY");
		}
		return result;
	}

	@RequestMapping(path = "/vtaReceive/{vtaCode}/{status}/{vtmid}", method = RequestMethod.GET)
	@ResponseBody
	public String receiveVtaCall(@RequestParam("vtaCode") String vtaCode, @RequestParam("status") String status,
			@RequestParam("vtmid") String vtmid) throws Exception {

		// Thread.sleep(2000);
		
		// vtaQueue.remove();
		if (!status.equals("Yes")) {
			vtaStatusAndOrderService.update(vtaCode,
					new VtaStatusAndOrderDetail(VtaStatus.MISSING, vtmDetails.get(vtmid)));
			vtmQueue.replace(vtmid, "READY");
			if (vtaQueue.size() != 0) {
				String agentId=vtaQueue.remove();
				vtAWatchRequests.get(agentId).setResult(vtmid);
			}
			return "不听不听，王八念经";
		} else {
			vtmWatchRequests.get(vtmid).setResult(vtaCode);
			vtaStatusAndOrderService.update(vtaCode,
					new VtaStatusAndOrderDetail(VtaStatus.FIRST, vtmDetails.get(vtmid)));
			vtmQueue.remove(vtmid);
			return "成功接call";
		}

	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	@ResponseBody
	public String login(HttpSession session, Model model, @RequestParam("username") String username,
			@RequestParam("password") String password) throws Exception {
		model.addAttribute("groupId", "user_groupId");
		model.addAttribute("session_id", session.getId());
		System.out.println("跳转：" + session.getId());
		session.setAttribute("loginName", username);
		VtaStatusAndOrderDetail vtaDetail = new VtaStatusAndOrderDetail();
		vtaDetail.setStatus(VtaStatus.READY);
		vtaStatusAndOrderService.update(username, vtaDetail);
		return "success";
	}

	public String getFirstCanUseVtmId() {
		Map<String, String> req = vtmQueue;
		for (Entry<String, String> string : req.entrySet()) {
			if (string.getValue().equals("READY")) {
				return string.getKey();
			}
			;
		}
		return null;
	}
}
