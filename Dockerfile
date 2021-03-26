FROM openjdk:8-jdk-alpine
ADD /target/portfolio-watch-0.0.1-SNAPSHOT.jar portfolio-watch.jar
ENTRYPOINT ["java", "-jar", "portfolio-watch-0.0.1-SNAPSHOT.jar"]
CMD  ["java","-Dspring.mail.username=${EMAIL_USER}","-Dspring.mail.password=${EMAIL_PASS}","-Dserver.ssl.key-store=${KEY_STORE_LOC}","-Dserver.ssl.key-store-password=${KEY_STORE_PASS}","-Dtd-ameritrade.redirect=${TD_REDIRECT}","-Dtd-ameritrade.client-id=${TD_CLIENT_ID}","-Dfinancial-modeling-prep.api-key=${FMP_API_KEY}","-jar","/portfolio-watch.jar"]
