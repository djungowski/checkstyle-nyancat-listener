checkstyle-nyancat-listener
===========================
An AuditListener for checkstyle printing the Nyan Cat


Usage
=====

1. Download or create jar file
------------------------------

Download the jar file: https://github.com/djungowski/checkstyle-nyancat-listener/blob/master/nyan.jar?raw=true

If you insist on creating the jar file yourself, clone the git repo and type

	javac com/djungowski/listeners/NyanCatListener.java && jar cfv nyan.jar com/


2. Add the jar file to your CLASSPATH
-------------------------------------

	export CLASSPATH=$CLASSPATH:~/checkstyle/nyan.jar


3. Add Nyan Cat to your checkstyle XML File
-------------------------------------------

	<module name="com.djungowski.listeners.NyanCatListener" />


4. Run checkstyle :-)
---------------------

[![Nyan Cat: Success!](https://github.com/djungowski/checkstyle-nyancat-listener/raw/master/nyan-fail.png)](https://github.com/djungowski/checkstyle-nyancat-listener/raw/master/nyan-fail.png)

O noes! You have checkstyle issues!

[![Nyan Cat: Success!](https://github.com/djungowski/checkstyle-nyancat-listener/raw/master/nyan-success.png)](https://github.com/djungowski/checkstyle-nyancat-listener/raw/master/nyan-success.png)

Yay! \o/
