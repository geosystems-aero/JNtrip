package aero.geosystems.ntrip;

public class NtripServerResponse {
	private boolean ok = false;
	private boolean error = false;
	private String msg;

	public boolean isError() {
		return error;
	}

	public boolean isOk() {
		return ok;
	}

	public String getMsg() {
		return msg;
	}

	private void init(String response) {
		msg = response;
		if (response.equals("OK")) {
			ok = true;
		} else {
			error = true;
		}
	}

	public NtripServerResponse(String response) {
		init(response);
	}

	public NtripServerResponse(byte[] buf, int offset, int length) {
		String s = new String(buf, offset, length);
		int i = s.indexOf("\r\n");
		if (i >= 0) {
			s = s.substring(0, i);
		}
		init(s);
	}
}
