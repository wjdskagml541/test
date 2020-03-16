FROM tomcat:latest

RUN rm -Rf /usr/local/tomcat/webapps/mavenweb
RUN rm -Rf /usr/local/tomcat/webapps/mavenweb.war
