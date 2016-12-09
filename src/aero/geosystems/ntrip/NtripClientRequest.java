package aero.geosystems.ntrip;

import java.util.Map;
import java.util.TreeMap;

public class NtripClientRequest {
	public static String createRequest(String mountpoint, String protocol, Map<String, String> params) {
		return NtripUtils.createRequest("GET /" + mountpoint + " " + protocol, params);
	}

	private String _userAgent = "NTRIP Client";
	private String _mountpoint = "";
	private String _protocol = "HTTP/1.0";
	private Authorization _authorization = NoneAuthorization.instance;

	public static abstract class Authorization {
		public abstract void addParams(Map<String, String> params);
	}

	public static class NoneAuthorization extends Authorization {
		public static final NoneAuthorization instance = new NoneAuthorization();

		private NoneAuthorization() {
		}

		@Override
		public void addParams(Map<String, String> params) {
		}
	}

	public static class BasicAuthorization extends Authorization {
		private String authstr;

		public BasicAuthorization(String username, String password) {
			this.authstr = username+":"+password;
		}

		public BasicAuthorization(String authstr) {
			this.authstr = authstr;
		}

		@Override
		public void addParams(Map<String, String> params) {
			params.put("Authorization", "Basic " + Base64.encode(authstr.getBytes()));
		}
	}

	public NtripClientRequest userAgent(String u) {
		this._userAgent = u;
		return this;
	}

	public NtripClientRequest mountpoint(String s) {
		this._mountpoint = s;
		return this;
	}

	public NtripClientRequest protocol(String s) {
		this._protocol = s;
		return this;
	}

	public NtripClientRequest authentication(Authorization a) {
		this._authorization = a;
		return this;
	}

	public Map<String, String> params() {
		Map<String, String> params = new TreeMap<>();
		if (_userAgent != null) params.put("User-Agent", _userAgent);
		_authorization.addParams(params);
		return params;
	}

	public String request() {
		return createRequest(_mountpoint, _protocol, params());
	}
}
