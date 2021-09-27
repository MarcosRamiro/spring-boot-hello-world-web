docker build -t ramimar/web .

docker run -p 8080:8080 --env-file ./env.list  ramimar/web

docker run -p 8080:8080 -e "JAVA_OPTS=-Ddebug" ramimar/web

docker run --entrypoint /bin/sh -p 8080:8080 -it --env-file ./env.list  ramimar/web