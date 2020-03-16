FROM tomcat

RUN rm -Rf /user/local/tomcat/webapps/mavenweb
RUN rm -Rf /user/local/tomcat/webapps/mavenweb.war

COPY ./mavenweb/target/mavenweb.war /usr/local/tomcat/webapps/mavenweb.war