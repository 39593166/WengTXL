package config;

import java.io.Serializable;

public class ResponseStatu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4947207461263305971L;

	private int state;
	private String msg;

	public ResponseStatu(int state, String msg) {
		super();
		this.state = state;
		this.msg = msg;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
