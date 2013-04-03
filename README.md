checkstyle-nyancat-listener
===========================
And AuditListener for checkstyle printing the Nyan Cat


Usage
=====

1. Download or create jar file
------------------------------

Download the jar file: https://github.com/djungowski/checkstyle-nyancat-listener/blob/master/nyan.jar?raw=true

If you insist on creating the jar file yourself, clone the git repo and type

	javac com/djungowski/listeners/NyanCatListener.java && jar cfv nyan.jar com/


2. Add the jar file to your CLASSPATH

	export CLASSPATH=$CLASSPATH:~/junit/junit-4.10.jar:~/checkstyle/checkstyle-5.6/checkstyle-5.6-all.jar:~/checkstyle/nyan.jar

3. Add Nyan Cat to your checkstyle XML File

	<module name="com.djungowski.listeners.NyanCatListener" />

4. Run checkstyle :-)

(https://github.com/djungowski/checkstyle-nyancat-listener/raw/master/nyan.png)