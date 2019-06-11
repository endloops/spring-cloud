package sxs.xas.bqq.hqz.yjgc.myw.utils;

public enum VtaStatus {

	READY("Ready"),
	
	FIRST("First"),
	
	SECOND("Second"),
	
	THIRD("Third"),
	
	FOUTH("Fouth"),
	
	FIFTH("Fifth"),
	
	MISSING("Missing");
	
	private String status;

	private VtaStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {

		return String.valueOf(this.status);

	}
}
