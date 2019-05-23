package sxs.xas.bqq.hqz.yjgc.myw.entity;

public class ServerMessage {
	
	private String content;

	@Override
	public String toString() {
		return "ServerMessage [content=" + content + "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ServerMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServerMessage(String content) {
		super();
		this.content = content;
	}
	
	
}
