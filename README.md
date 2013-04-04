Requirements
============
[checkstyle 5.6](http://checkstyle.sourceforge.net/). Previous versions will probably work aswell.

Usage
=====

1. Download or create jar file
------------------------------

Download the jar file: https://github.com/djungowski/checkstyle-nyancat-listener/blob/master/nyan.jar?raw=true

If you insist on creating the jar file yourself, clone the git repo and type

	javac com/djungowski/listeners/NyanCatListener.java && jar cf nyan.jar com/


2. Add the jar file to your CLASSPATH
-------------------------------------

	export CLASSPATH=$CLASSPATH:~/checkstyle/nyan.jar


3. Add Nyan Cat to your checkstyle XML File
-------------------------------------------

	<module name="com.djungowski.listeners.NyanCatListener" />


4. Run checkstyle :-)
---------------------

[![Nyan Cat: Fail!](https://github.com/djungowski/checkstyle-nyancat-listener/raw/master/nyan-fail.gif)](https://github.com/djungowski/checkstyle-nyancat-listener/raw/master/nyan-fail.gif)

O noes! You have checkstyle issues!

[![Nyan Cat: Success!](https://github.com/djungowski/checkstyle-nyancat-listener/raw/master/nyan-success.gif)](https://github.com/djungowski/checkstyle-nyancat-listener/raw/master/nyan-success.gif)

Yay! \o/


Infinite Nyan Cat
=================

If you enjoy Nyan Cat so much and you want to run it infinitely, clone the repo and run

	javac NyanCat.java && java NyanCat



Acknowledgements
================
This project was inspired by [whatthejeff/nyancat-phpunit-resultprinter](https://github.com/whatthejeff/nyancat-phpunit-resultprinter)