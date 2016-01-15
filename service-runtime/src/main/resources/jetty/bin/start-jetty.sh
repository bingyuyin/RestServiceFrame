#!/bin/sh
DIRNAME=`dirname $0`
 if [ ! -f "$JAVA_HOME" ]
    then
      export JAVA=$JAVA_HOME/bin/java
 fi
${DIRNAME}/jetty.sh run