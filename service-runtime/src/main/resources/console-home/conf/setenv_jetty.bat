
set JETTY_HOME=%CONSOLE_HOME%\${jetty.relative.home}
set JETTY_BASE=%JETTY_HOME%
set JETTY_LOGS=%CONSOLE_LOG_DIR%
set JETTY_STATE=%JETTY_HOME%\jetty.state
set JETTY_STOP_PORT=8079
set JETTY_STOP_SECRET=secret-of-tikal

set JAVA_OPTIONS=%JAVA_OPTIONS% -Djetty.home=%JETTY_HOME% -Djetty.base=%JETTY_BASE% -Djetty.state=%JETTY_STATE% -Djetty.logs=%JETTY_LOGS% -Dpath=%ADD_TO_CLASSPATH%

rem JAVA_OPTIONS+=("-Djetty.port=$JETTY_PORT")
rem JAVA_OPTIONS+=("-Djava.io.tmpdir=$TMPDIR")
rem JAVA_OPTIONS+=("-DSTART=$JETTY_HOME/etc/start.config")

rem set WATCHED_LOGS=%CONSOLE_LOG_DIR%\stderrout.log %WATCHED_LOGS%