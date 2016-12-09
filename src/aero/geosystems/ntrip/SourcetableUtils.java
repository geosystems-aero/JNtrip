package aero.geosystems.ntrip;

import java.util.Locale;

/**
 * Created by aimozg on 06.04.2016.
 * Confidential.
 */
public class SourcetableUtils {
	private SourcetableUtils(){}

	public static String buildCasRecord(
			String hostname,
			int port,
			String identifier,
			String operator,
			boolean nmeaSupport,
			String country,
			double latitude,
			double longitude,
			String fallback_host,
			int fallback_port,
			String miscInfoUrl,
			String... additional_records) {
		StringBuilder sb = new StringBuilder();
		sb.append("CAS;")
				.append(hostname).append(";")
				.append(port).append(";")
				.append(identifier).append(";")
				.append(operator).append(";")
				.append(nmeaSupport?"1":"0").append(";")
				.append(country).append(";")
				.append(String.format(Locale.ENGLISH, "%05.2f", latitude)).append(";")
				.append(String.format(Locale.ENGLISH, "%05.2f", longitude)).append(";")
				.append(fallback_host).append(";")
				.append(fallback_port).append(";");
		for (String s : additional_records) sb.append(s).append(";");
		sb.append(miscInfoUrl);
		return sb.toString();
	}
}
