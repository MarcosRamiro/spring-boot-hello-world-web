#!/bin/sh

result=$(wget "https://swapi.dev/api/${PATH_1}/${PATH_2}")
echo "Response from server"
echo $result

SERVER_SERVLET_CONTEXT_PATH=${SERVER_SERVLET_CONTEXT_PATH}
PARAMETRO_1=$1
printenv

JAVA_OPTS="-Dserver.port=${SERVER_PORT} \
 -Dserver.servlet.context-path=/$SERVER_SERVLET_CONTEXT_PATH \
 -Duser.timezone=${TZ}
 -Daws.asg=${AWS_ASG} \
 -Xms512m -Xmx2048m "

echo $JAVA_OPTS
echo $PARAMETRO_1

exec java $JAVA_OPTS -jar /app/app.jar $PARAMETRO_1