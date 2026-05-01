# Bubble sort API

No need to use this it's to refresh memory

## Installation
Clone the repository:
```bash
git clone https://github.com/zr8x/Java-API.git
```
Open the directory:
```bash
cd Java-API
```

Duplicate the terminal, then on one open the server directory and build:
```bash
cd server
gradle build
```
Then navigate to the built class and run:
```bash
cd build/classes/java/main
java Server
```

On the other terminal window, open the client directory and navigate to the test directory:
```bash
cd client/src/test/java
```
Change whatever you want in the ClientTest.java file to test the API

Move back into the main directory and build the client:
```bash
cd ../../../
gradle build
```
Move into the built directory and run the client:
```bash
cd build/classes/java/test
java ClientTest
```
