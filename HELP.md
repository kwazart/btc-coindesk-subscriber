# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

1. ```./mvnw clean package```
2. ```docker build -t btc-coindesk-subscriber:latest .```
3. ```docker compose up```
4. [Local actuator](http://localhost:8080/actuator)
5. [Prometheus](http://localhost:9090/graph)
6. [Grafana](http://localhost:3000)
   - Create new datasource
     - Name: Prometheus
     - Prometheus server URL: *http://host.docker.internal:9090*
   - Add new dashboards:
       - Import a dashboard - jvm-micrometer_rev9.json
       - Import a dashboard - micrometer-spring-throughput_rev2.json
       - Add visualization: we have some custom metrics
7. [Demo: Grafana + Prometheus + Alertmanager + Node exporter](https://demo.do.prometheus.io/)
8. [Demo Grafana](https://play.grafana.org/)

