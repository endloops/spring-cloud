package sxs.xas.bqq.hqz.yjgc.myw.entity;

public class ServerMessage {
	
	private String userName;
	
	private Long id;
	
	private String clientMark;
	
	private String contents;
	
	private String type;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientMark() {
		return clientMark;
	}

	public void setClientMark(String clientMark) {
		this.clientMark = clientMark;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ServerMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServerMessage(String userName, Long id, String clientMark, String contents, String type) {
		super();
		this.userName = userName;
		this.id = id;
		this.clientMark = clientMark;
		this.contents = contents;
		this.type = type;
	}

	public ServerMessage(String contents) {
		super();
		this.contents = contents;
	}
	
	
}
