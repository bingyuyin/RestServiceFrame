@echo off


rem This script:
rem 1) set's configuration parameters
rem 2) fires up jetty (where our app is hopefully installed)
rem 3) TERMINATES!
set CONF_DIR="%~dp0\..\conf"

call %CONF_DIR%\setenv_common.bat
call %CONF_DIR%\setenv_jetty.bat

echo JETTY_HOME=%JETTY_HOME%
echo JETTY_BASE=%JETTY_BASE%
echo CONSOLE_HOME=%CONSOLE_HOME%

set test_mode=%1
if /I "%test_mode%" EQU "Y" set test=-Dspring.profiles.active="test"

rem %JETTY_HOME%/etc/jetty-logging.xml

start java %JAVA_OPTIONS% -DSTOP.PORT=%JETTY_STOP_PORT% -DSTOP.KEY=%JETTY_STOP_SECRET% %test% -jar %JETTY_HOME%/start.jar --lib=%CONSOLE_HOME%/conf