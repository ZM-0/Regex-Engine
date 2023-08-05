@echo off
cd out\production\RegexEngine
java Regex %1 %2
cd ..\..\..
@echo on