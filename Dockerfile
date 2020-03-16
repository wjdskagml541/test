FROM tomcat:latest

RUN rm -Rf /usr/local/tomcat/webapps/mavenweb
RUN rm -Rf /usr/local/tomcat/webapps/mavenweb.war
COPY mavenweb.war /usr/local/tomcat/webapps/mavenweb.war
