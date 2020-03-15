#!/usr/bin/env bash

#通过脚本路径计算APP相关变量
if readlink -f "$0" > /dev/null 2>&1
then
  SHELL_BIN=$(readlink -f "$0")
else
  SHELL_BIN="$0"
fi
BIN_HOME=$(dirname $SHELL_BIN)
APP_HOME=$BIN_HOME
PID_LOG=$APP_HOME/pid
CONF=$APP_HOME/config
JAR_NAME=$APP_HOME/futuredu-teacher.jar


#JVM启动参数
#-server:一定要作为第一个参数,在多个CPU时性能佳
#-Xloggc:记录GC日志,这里建议写成绝对路径,如此便可在任意目录下执行该shell脚本
DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8848,server=y,suspend=y"
JAVA_OPTS="-Duser.timezone=GMT+8 -server -Xms512m -Xmx1024m -XX:SurvivorRatio=4 -XX:+DisableExplicitGC -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:../logs/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs -XX:ErrorFile=../logs/hs_err_pid%p.log"


JAVA=`which java`



start(){
echo  -n "Starting Info "
if [ -f "$PID_LOG" ];then
	if kill -0 `cat "$PID_LOG"` > /dev/null 2>&1; then
         echo already running as process `cat "$PID_LOG"`.
         exit 0
    fi
fi

if [ "$1" == "test" ];then
    eval exec $JAVA $JAVA_OPTS -jar -Dloader.path=$CONF $JAR_NAME
else
   nohup $JAVA $JAVA_OPTS  -jar -Dloader.path=$CONF $JAR_NAME  > "$APP_HOME/start.log" 2>&1   &
fi

	if [ $? -eq 0 ]
    then
      if /bin/echo -n $! > "$PID_LOG"
      then
        sleep 1
        echo STARTED
          	echo "SUCCESS"
  			echo "application start success"
      else
        echo FAILED TO WRITE PID
        exit 1
      fi
    else
      echo WOKRE DID NOT START
      exit 1
    fi
}

stop(){
if [ -f "$PID_LOG" ];then
  if [ -n "$(cat $PID_LOG)" ];then
    kill -9 $(cat $PID_LOG)
    rm -f $PID_LOG
    echo "SUCCESS"
    echo "application stop success"
    exit 0
  else
    echo "FAIL"
    echo "PID FILE is empty,please check whether the application running, and use kill command to stop the application"
    exit 0
  fi
else
  echo "FAIL"
  echo "Pid file not exist,please check whether the application running, and use kill command to stop the application."
  exit 0
fi
}

case "$1" in
start)
  start "$2"
  ;;
  stop)
  stop
  ;;
  *)
  echo 'Usage command: {start|stop}\n'
  exit 1
  ;;
esac

exit 0
