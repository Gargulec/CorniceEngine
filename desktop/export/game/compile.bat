cd export
cd game
javac -cp game.jar; Main.java MainScreen.java
del game.jar Main.java MainScreen.java
cd ..
jar -uf game.jar game graphics
java -jar game.jar
del Main.class MainScreen.class
del graphics
