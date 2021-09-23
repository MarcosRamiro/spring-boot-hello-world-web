#!/bin/sh

JAVA_OPTS_IN=${JAVA_OPTS}
JAVA_OPTS_IN="${JAVA_OPTS_IN} -Dserver.port=80 "

exec java $JAVA_OPTS_IN -jar /app/app.jar