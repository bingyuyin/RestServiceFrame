@echo off
set WORKDIR=%cd%
cd /d %~dp0
cd ..
set CONSOLE_HOME="%cd%"
cd %WORKDIR%

echo Setting CONSOLE_HOME to %CONSOLE_HOME%

set CONSOLE_LOG_DIR=%CONSOLE_HOME%\log

set LOG_OUT=%CONSOLE_HOME%\LastServerOutput.log

set ADD_TO_CLASSPATH=%CONSOLE_HOME%\conf

if defined CLASSPATH (
	set CLASSPATH="%CLASSPATH%;%ADD_TO_CLASSPATH%"
) else (
	set CLASSPATH="%ADD_TO_CLASSPATH%"
)

set JAVA_HEAP_OPTS=-Xms512m -Xmx512m -XX:MaxPermSize=128m -XX:+UseParallelGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=HPHeapDump.txt
rem set DEBUG_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000

set JAVA_OPTS=%JAVA_OPTS% -Dconsole.home=%CONSOLE_HOME% %JAVA_HEAP_OPTS% %DEBUG_OPTS%
set JAVA_OPTIONS=%JAVA_OPTIONS% -Dfile.encoding=UTF-8 -Dconsole.home=%CONSOLE_HOME% %JAVA_HEAP_OPTS% %DEBUG_OPTS%

set WATCHED_LOGS=%CONSOLE_LOG_DIR%\connector.log %CONSOLE_LOG_DIR%\service.log
