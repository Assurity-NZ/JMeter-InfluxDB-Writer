package rocks.nt.apm.jmeter.config.influxdb;

/**
 * Constants (Tag, Field, Measurement) names for the requests measurement.
 * 
 * @author Alexander Wert
 *
 */
public interface RequestMeasurement {

	/**
	 * Measurement name.
	 */
	String MEASUREMENT_NAME = "requestsRaw";

	/**
	 * Tags.
	 * 
	 * @author Alexander Wert
	 *
	 */
	public interface Tags {
		/**
		 * Request name tag.
		 */
		String REQUEST_NAME = "requestName";

		/**
		 * Influx DB tag for a unique identifier for each execution(aka 'run') of a load test.
		 */
		String RUN_ID = "runId";

		/**
		 * Test name field
		 */
		String TEST_NAME = "testName";

		/**
		 * Response code tag.
		 */
		String RESPONSE_CODE = "responseCode";
	}

	/**
	 * Fields.
	 *
	 * @author Alexander Wert
	 *
	 */
	public interface Fields {
		/**
		 * Response time field.
		 */
		String RESPONSE_TIME = "responseTime";

		/**
		 * Error count field.
		 */
		String ERROR_COUNT = "errorCount";

		/**
		 * Node name field
		 */
		String NODE_NAME = "nodeName";

		/**
		 * Sent bytes field
		 */
		String SEND_BYTES = "sendBytes";

		/**
		 * Connect time field (ms).
		 */
		String CONNECT = "connect";

		/**
		 * Latency field (ms).
		 */
		String LATENCY = "latency";

		/**
		 * Received bytes field.
		 */
		String BYTES = "bytes";
	}
}
