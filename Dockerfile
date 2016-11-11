FROM tomcat:8.5-alpine

COPY tomcat/lib/*.jar lib/

RUN mkdir -p webapps/nats-reporter
COPY tomcat/WebContent/ webapps/nats-reporter/