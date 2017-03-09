set currentDir=%cd%

for /f "delims=" %%i in ("%currentDir%") do (
	set sysName=%%~nxi
)

echo %currentDir%
set web-dir="%sysName%-web"
cd %web-dir%\target
call :pack %web-dir%

set jsw-dir="%sysName%-jsw"
cd %jsw-dir%\target

rem cd %currentDir%
pushd %currentDir%
cd %jsw-dir%\target
call :pack %jsw-dir%
goto :eof

:pack
set target=%1
set exe="C:\Program Files\7-Zip\7z.exe"

del /s /q %target%.tar.gz
%exe% a -ttar %target% %target%
%exe% a -tgzip %target% %target%.tar
rename %target%.gz %target%.tar.gz
del /s /q %target%.tar
goto :eof

