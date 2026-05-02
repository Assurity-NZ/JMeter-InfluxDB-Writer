# JMeter-InfluxDB-Writer
Plugin for JMeter that allows to write load test data on-the-fly to influxDB.


## Local testing

```bash
git clone https://github.com/Assurity-Cloud/performance-tools.git
```

1. Commit and push changes to the active branch. This will trigger `build-release-candidate` workflow to create a pre-release with the jar attached.
2. Retrieve the pre-release version in the GitHub repository. (e.x `2.0.1-rc.4`)
4. In docker-compose.yml, replace the `INFLUXDB_WRITER_VERSION` version with the pre-released version.
5. Run `docker compose up --build --force-recreate`.
6. Depending on your changes, verify the measurements by running the command below

```docker compose exec influxdb influx -username jmeter -password 123qweasd -database jmeter_metrics -execute 'SELECT * FROM "requestsRaw"'```
