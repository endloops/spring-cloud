package sxs.xas.bqq.hqz.yjgc.myw.entity;


public class ClientMessage {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClientMessage(String name) {
		super();
		this.name = name;
	}

	public ClientMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ClientMessage [name=" + name + "]";
	}

	 
    
}
