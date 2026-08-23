package rocks.nt.apm.jmeter.config.influxdb;

import java.net.InetSocketAddress;
import java.net.Proxy;
import org.apache.commons.lang3.StringUtils;
import org.apache.jmeter.visualizers.backend.BackendListenerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration for influxDB.
 * 
 * @author Alexander Wert
 *
 */
public class InfluxDBConfig {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(InfluxDBConfig.class);

	/**
	 * Default database name.
	 */
	public static final String DEFAULT_DATABASE = "jmeter";

	/**
	 * Default retention policy name.
	 */
	public static final String DEFAULT_RETENTION_POLICY = "autogen";

	/**
	 * Default http scheme name.
	 */
	public static final String DEFAULT_HTTP_SCHEME = "http";

	/**
	 * Default port.
	 */
	public static final int DEFAULT_PORT = 8086;

	/**
	 * Default connect timeout in milliseconds (OkHttp default).
	 */
	public static final int DEFAULT_CONNECT_TIMEOUT_MS = 10000;

	/**
	 * Default proxy.
	 */
	public static final Proxy DEFAULT_PROXY = Proxy.NO_PROXY;

	/**
	 * Config key for database name.
	 */
	public static final String KEY_INFLUX_DB_DATABASE = "influxDBDatabase";

	/**
	 * Config key for password.
	 */
	public static final String KEY_INFLUX_DB_PASSWORD = "influxDBPassword";

	/**
	 * Config key for user name.
	 */
	public static final String KEY_INFLUX_DB_USER = "influxDBUser";

	/**
	 * Config key for port.
	 */
	public static final String KEY_INFLUX_DB_PORT = "influxDBPort";

	/**
	 * Config key for connect timeout (milliseconds).
	 */
	public static final String KEY_INFLUX_DB_CONNECT_TIMEOUT = "influxDBConnectTimeout";
	
	/**
	 * Config key for host.
	 */
	public static final String KEY_INFLUX_DB_HOST = "influxDBHost";

	/**
	 * Config key for retention policy name.
	 */
	public static final String KEY_RETENTION_POLICY = "retentionPolicy";
	
	/**
	 * Config key for http scheme.
	 */
	public static final String KEY_HTTP_SCHEME = "influxHTTPScheme";

	/**
	 * Config key for proxy.
	 */
	public static final String KEY_INFLUX_PROXY = "influxProxy";

	/**
	 * InfluxDB Host.
	 */
	private String influxDBHost;

	/**
	 * InfluxDB User.
	 */
	private String influxUser;

	/**
	 * InfluxDB Password.
	 */
	private String influxPassword;

	/**
	 * InfluxDB database name.
	 */
	private String influxDatabase;

	/**
	 * InfluxDB database retention policy.
	 */
	private String influxRetentionPolicy;

	/**
	 * InfluxDB Port.
	 */
	private int influxDBPort;

	/**
	 * InfluxDB connect timeout in milliseconds.
	 */
	private int connectTimeout;
	
	/**
	 * InfluxDB database retention policy.
	 */
	private String influxHTTPScheme;

	/**
	 * InfluxDB proxy.
	 */
	private Proxy influxProxy;

	public InfluxDBConfig(BackendListenerContext context) {
		String influxDBHost = context.getParameter(KEY_INFLUX_DB_HOST);
		if (StringUtils.isEmpty(influxDBHost)) {
			throw new IllegalArgumentException(KEY_INFLUX_DB_HOST + " must not be empty!");
		}
		setInfluxDBHost(influxDBHost);

		int influxDBPort = context.getIntParameter(KEY_INFLUX_DB_PORT, InfluxDBConfig.DEFAULT_PORT);
		setInfluxDBPort(influxDBPort);

		setConnectTimeout(sanitiseTimeout(KEY_INFLUX_DB_CONNECT_TIMEOUT,
				context.getIntParameter(KEY_INFLUX_DB_CONNECT_TIMEOUT, DEFAULT_CONNECT_TIMEOUT_MS), DEFAULT_CONNECT_TIMEOUT_MS));
		
		String influxUser = context.getParameter(KEY_INFLUX_DB_USER);
		setInfluxUser(influxUser);

		String influxPassword = context.getParameter(KEY_INFLUX_DB_PASSWORD);
		setInfluxPassword(influxPassword);

		String influxDatabase = context.getParameter(KEY_INFLUX_DB_DATABASE);
		if (StringUtils.isEmpty(influxDatabase)) {
			throw new IllegalArgumentException(KEY_INFLUX_DB_DATABASE + " must not be empty!");
		}
		setInfluxDatabase(influxDatabase);

		String influxRetentionPolicy = context.getParameter(KEY_RETENTION_POLICY, DEFAULT_RETENTION_POLICY);
		if (StringUtils.isEmpty(influxRetentionPolicy)) {
			influxRetentionPolicy = DEFAULT_RETENTION_POLICY;
		}
		setInfluxRetentionPolicy(influxRetentionPolicy);
		
		String influxHTTPScheme = context.getParameter(KEY_HTTP_SCHEME, DEFAULT_HTTP_SCHEME);
		if (StringUtils.isEmpty(influxHTTPScheme)) {
			influxHTTPScheme = DEFAULT_HTTP_SCHEME;
		}
		// TODO: no checks but should be only "http" and "https"
		setInfluxHTTPScheme(influxHTTPScheme);

		String influxProxy = context.getParameter(KEY_INFLUX_PROXY, DEFAULT_PROXY.toString());
		setInfluxProxy(influxProxy);
	}

	/**
	 * Falls back to the default when a timeout is negative, since OkHttp rejects
	 * negative values and this config is built outside the client's try/catch.
	 *
	 * @param key
	 *            config key, for logging.
	 * @param value
	 *            configured value in milliseconds.
	 * @param defaultValue
	 *            value to fall back to.
	 * @return a non-negative timeout in milliseconds.
	 */
	private int sanitiseTimeout(String key, int value, int defaultValue) {
		if (value < 0) {
			LOGGER.warn("{} must not be negative (was {}); falling back to {} ms", key, value, defaultValue);
			return defaultValue;
		}
		return value;
	}

	/**
	 * Builds URL to influxDB.
	 * 
	 * @return influxDB URL.
	 */
	public String getInfluxDBURL() {
		return influxHTTPScheme + "://" + influxDBHost + ":" + influxDBPort;
	}

	/**
	 * @return the influxDBHost
	 */
	public String getInfluxDBHost() {
		return influxDBHost;
	}

	/**
	 * @param influxDBHost
	 *            the influxDBHost to set
	 */
	public void setInfluxDBHost(String influxDBHost) {
		this.influxDBHost = influxDBHost;
	}

	/**
	 * @return the influxUser
	 */
	public String getInfluxUser() {
		return influxUser;
	}

	/**
	 * @param influxUser
	 *            the influxUser to set
	 */
	public void setInfluxUser(String influxUser) {
		this.influxUser = influxUser;
	}

	/**
	 * @return the influxPassword
	 */
	public String getInfluxPassword() {
		return influxPassword;
	}

	/**
	 * @param influxPassword
	 *            the influxPassword to set
	 */
	public void setInfluxPassword(String influxPassword) {
		this.influxPassword = influxPassword;
	}

	/**
	 * @return the influxDatabase
	 */
	public String getInfluxDatabase() {
		return influxDatabase;
	}

	/**
	 * @param influxDatabase
	 *            the influxDatabase to set
	 */
	public void setInfluxDatabase(String influxDatabase) {
		this.influxDatabase = influxDatabase;
	}

	/**
	 * @return the influxRetentionPolicy
	 */
	public String getInfluxRetentionPolicy() {
		return influxRetentionPolicy;
	}

	/**
	 * @param influxRetentionPolicy
	 *            the influxRetentionPolicy to set
	 */
	public void setInfluxRetentionPolicy(String influxRetentionPolicy) {
		this.influxRetentionPolicy = influxRetentionPolicy;
	}

	/**
	 * @param influxHTTPScheme
	 *            the influxHTTPScheme to set
	 */
	public void setInfluxHTTPScheme(String influxHTTPScheme) {
		this.influxHTTPScheme = influxHTTPScheme;
	}

	/**
	 * @return the influxDBPort
	 */
	public int getInfluxDBPort() {
		return influxDBPort;
	}

	/**
	 * @param influxDBPort
	 *            the influxDBPort to set
	 */
	public void setInfluxDBPort(int influxDBPort) {
		this.influxDBPort = influxDBPort;
	}

	/**
	 * @return the influxProxy
	 */
	public Proxy getInfluxProxy() {
		return influxProxy;
	}

	/**
	 * @param influxProxy
	 *            the influxProxy to set
	 */
	public void setInfluxProxy(String influxProxy) {
		LOGGER.debug("setInfluxProxy - value passed in: |{}|", influxProxy);
		if (influxProxy != null && !influxProxy.isEmpty()) {
			String[] hostAndPort = influxProxy.split(":");
			this.influxProxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(hostAndPort[0], Integer.parseInt(hostAndPort[1])));
		} else {
			this.influxProxy = Proxy.NO_PROXY;
		}
		LOGGER.debug("setInfluxProxy - value set: |{}|", this.influxProxy.address());
	}

	/**
	 * @return the connectTimeout in milliseconds
	 */
	public int getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * @param connectTimeout
	 *            the connectTimeout to set, in milliseconds
	 */
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
}
