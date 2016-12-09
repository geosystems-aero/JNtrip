package aero.geosystems.ntrip;

import java.util.Map;

public class NtripUtils {
	public static String createRequest(String header, Map<String, String> params) {
		StringBuilder buf = new StringBuilder();
		buf.append(header).append("\r\n");
		for (Map.Entry<String, String> e : params.entrySet()) {
			buf.append(e.getKey()).append(": ").append(e.getValue()).append("\r\n");
		}
		buf.append("\r\n");
		return buf.toString();
	}
}
