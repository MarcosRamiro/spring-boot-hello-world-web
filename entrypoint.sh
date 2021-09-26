#!/bin/sh

result=$(wget "https://swapi.dev/api/${PATH_1}/${PATH_2}")
echo "Response from server"
echo $result

SERVER_SERVLET_CONTEXT_PATH=${SERVER_SERVLET_CONTEXT_PATH}

printenv

JAVA_OPTS="-Dserver.port=${SERVER_PORT} \
 -Dserver.servlet.context-path=/$SERVER_SERVLET_CONTEXT_PATH \
 -Daws.asg=${AWS_ASG} "

echo $JAVA_OPTS

exec java $JAVA_OPTS -jar /app/app.jar