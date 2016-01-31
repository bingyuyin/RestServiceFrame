@echo off

set CONF_DIR=%~dp0\..\conf

call %CONF_DIR%\setenv_common.bat
call %CONF_DIR%\setenv_jetty.bat

java -DSTOP.PORT=%JETTY_STOP_PORT% -DSTOP.KEY=%JETTY_STOP_SECRET% -jar %JETTY_HOME%/start.jar --stop