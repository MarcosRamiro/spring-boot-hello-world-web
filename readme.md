docker build -t ramimar/web .

docker run -p 8080:8080 -e "JAVA_OPTS=-Ddebug -Dserver.servlet.context-path=/webout" ramimar/web

docker run -p 8080:8080 --env-file ./env.list  ramimar/web

docker run -p 8080:8080 -e "JAVA_OPTS=-Ddebug" ramimar/web