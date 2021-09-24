#!/bin/sh

JAVA_OPTS="-Dserver.port=${SERVER_PORT} \
 -Dserver.servlet.context-path=/${SERVER_SERVLET_CONTEXT_PATH} \
 -Daws.asg=${AWS_ASG} "
echo $JAVA_OPTS
exec java $JAVA_OPTS -jar /app/app.jar