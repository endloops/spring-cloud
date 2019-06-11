package sxs.xas.bqq.hqz.yjgc.myw.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sxs.xas.bqq.hqz.yjgc.myw.utils.VtaStatusAndOrderDetail;

@Service
public class VtaStatusAndOrderService {

	@Autowired
	Map<String, VtaStatusAndOrderDetail> vtaStatusAndOrderDetails;

	public Map<String, VtaStatusAndOrderDetail> update(String agentCode, VtaStatusAndOrderDetail vtaStatusAndOrderDetail) {
		int length = vtaStatusAndOrderDetails.keySet().size();
		if (length != 0 && vtaStatusAndOrderDetails.keySet().contains(agentCode)) {
			vtaStatusAndOrderDetails.replace(agentCode, vtaStatusAndOrderDetail);
		} else {
			vtaStatusAndOrderDetails.put(agentCode, vtaStatusAndOrderDetail);
		}
		return vtaStatusAndOrderDetails;
	}
}
