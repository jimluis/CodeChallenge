#!/bin/ksh
  
CURRENT_PATH=`pwd`
BASE_PATH=$(dirname "${CURRENT_PATH}")
echo "basepath: $BASE_PATH"

export JVM_ARGS="-Dlog4j.configurationFile=$BASE_PATH/etc/log4j2.xml -Dlogfile.name=$BASE_PATH/logs/producer.log"

CLASSPATH=$BASE_PATH/target/Producer-0.0.1-SNAPSHOT.jar:$BASE_PATH/target/ext_jars/TransportService-0.0.1-SNAPSHOT.jar:$BASE_PATH/target/ext_jars/ValueObjects-0.0.1-SNAPSHOT.jar:$BASE_PATH/target/ext_jars/amqp-client-4.0.2.jar:$BASE_PATH/target/ext_jars/slf4j-api-1.7.22.jar:$BASE_PATH/target/ext_jars/commons-lang3-3.8.1.jar:$BASE_PATH/target/ext_jars/slf4j-simple-1.7.22.jar:$BASE_PATH/target/ext_jars/log4j-api-2.11.1.jar:$BASE_PATH/target/ext_jars/log4j-core-2.11.1.jar; export CLASSPATH


if [ $# -lt 1 ]
then
    echo "Incorrect number of arguments"
    echo "To start the script run ./Producer start"
    echo "To stop the script run ./Producer stop"
elif [ $1 != "start" ] && [ $1 != "stop" ]   
then    
     echo "Invalid arguments"
     echo "To start the script run ./Producer start"
     echo "To stop the script run ./Producer stop"
     

fi 


if [ $1 = "start" ]
then
    processId=ps aux | grep -i 'com.luisfelipejimenez.producer.Producer'
    cnt=ps aux | grep -i 'com.luisfelipejimenez.producer.Producer' |wc -l
    echo "processId count: $cnt"
    
    if [ cnt -gt 0 ]
    then
        echo "Can not start process with pid $processId is already running"
        return
     else
        echo "Starting Producer"
        java -cp $CLASSPATH $JVM_ARGS com.luisfelipejimenez.producer.Producer $BASE_PATH/etc/TransportService.properties $BASE_PATH/etc/Producer.properties &
     fi 
fi   


if [ $1 = "stop" ]
then
    processId=`ps aux| grep 'com.luisfelipejimenez.producer.Producer' | grep TransportService.properties | awk '{print $2}'`
    echo "Stopping producer with processId: $processId"
    
    #if [ ! -z "$processId" ]
    if ["X$processId" != "X"]
    then
        kill -15 $processId
    fi
    
fi   