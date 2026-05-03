# JMeter-InfluxDB-Writer
Plugin for JMeter that allows to write load test data on-the-fly to influxDB.

## Local testing

```bash
git clone https://github.com/Assurity-Cloud/performance-tools.git
```

1. Commit and push changes to the active branch. This will trigger the `build-release-candidate` workflow to create a pre-release with the JAR attached.
2. Retrieve the pre-release version in the GitHub repository. (e.g. `2.0.2-rc.4`)
3. In docker-compose.yml, replace the `INFLUXDB_WRITER_VERSION` with the pre-release version.
4. Run `docker compose up --build --force-recreate`.
5. Depending on your changes, verify the measurements by running the command below.

```docker compose exec influxdb influx -username jmeter -password 123qweasd -database jmeter_metrics -execute 'SELECT * FROM "requestsRaw"'```
