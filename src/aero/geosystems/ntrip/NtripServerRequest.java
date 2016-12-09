package aero.geosystems.ntrip;

import java.util.Map;
import java.util.TreeMap;

public class NtripServerRequest {
	public static String createRequest(String password, String mountpoint, Map<String, String> params) {
		return NtripUtils.createRequest("SOURCE " + password + " /" + mountpoint, params);
	}

	private String _sourceAgent = "NTRIP Server";
	private String _password = "";
	private String _mountpoint = "";

	public NtripServerRequest sourceAgent(String s) {
		this._sourceAgent = s;
		return this;
	}

	public NtripServerRequest password(String s) {
		this._password = s;
		return this;
	}

	public NtripServerRequest mountpoint(String s) {
		this._mountpoint = s;
		return this;
	}

	public Map<String, String> params() {
		Map<String, String> params = new TreeMap<>();
		if (_sourceAgent != null) params.put("Source-Agent", _sourceAgent);
		return params;
	}

	public String request() {
		return createRequest(_password, _mountpoint, params());
	}
}
