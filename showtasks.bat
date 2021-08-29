call runcrud
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo There were problems with starting runcrud
goto fail

:openbrowser
start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo There was problem with opening browser

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.