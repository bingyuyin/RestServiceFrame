@echo off
cd /d %~dp0
cd ..
set JETTY_HOME="%cd%"

echo %JETTY_HOME%
java %JAVA_OPTS% -DSTOP.PORT=8079 -DSTOP.KEY=secret -jar %JETTY_HOME%/start.jar