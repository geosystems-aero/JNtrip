package aero.geosystems.ntrip;

public class NtripClientResponse {
	private boolean ok = false;
	private boolean sourcetable = false;
	private boolean error = false;
	private String msg;

	public boolean isError() {
		return error;
	}

	public boolean isOk() {
		return ok;
	}

	public boolean isSourcetable() {
		return sourcetable;
	}

	public String getMsg() {
		return msg;
	}

	private void init(String response) {
		msg = response;
		switch (response) {
			case "ICY 200 OK":
				ok = true;
				break;
			case "SOURCETABLE 200 OK":
				sourcetable = true;
				break;
			default:
				error = true;
				break;
		}
	}

	public NtripClientResponse(String response) {
		int i = response.indexOf("\r\n");
		if (i >= 0) {
			response = response.substring(0, i);
		}
		init(response);
	}

	public NtripClientResponse(byte[] buf, int offset, int length) {
		this(new String(buf, offset, length));
	}
}
