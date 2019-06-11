package sxs.xas.bqq.hqz.yjgc.myw.utils;

/**
 *  Vta 状态 及 接单 号
 * @author wang
 *
 */
public class VtaStatusAndOrderDetail {
	
	public VtaStatus status;
	
	public String transactionId;

	public VtaStatus getStatus() {
		return status;
	}

	public void setStatus(VtaStatus status) {
		this.status = status;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public VtaStatusAndOrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VtaStatusAndOrderDetail(VtaStatus status, String transactionId) {
		super();
		this.status = status;
		this.transactionId = transactionId;
	}

	
}
