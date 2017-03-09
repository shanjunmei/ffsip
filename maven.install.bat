@echo off

cd /d %~dp0

call svn update . -r HEAD --force
if %errorlevel% == 0 (echo.)
if %errorlevel% == 1 (echo [SVN] update failed, Please install 'TortoiseSVN-1.6.x' to support the svn command. & echo.)

call mvn clean install -U -Dmaven.test.skip=true
if %errorlevel% == 0 (echo. & echo [Building] Successful. & echo. & goto :eof)
if %errorlevel% == 1 (echo. & echo [Building] Failed. & echo. & pause)