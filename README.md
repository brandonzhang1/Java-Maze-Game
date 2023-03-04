# Java Maze Game

## Instructions to build, run, or test the game:  

Run the following commands from the directory with pom.xml using Apache Maven
   
Compile and run the game:  
	Step 1:	mvn clean install  
	Step 2:	java -cp target\276project-1.0-SNAPSHOT.jar Main  

Compile and run the game (without running tests):  
	Step 1:	mvn install -DskipTests  
	Step 2:	java -cp target\276project-1.0-SNAPSHOT.jar Main 
	
Run all the unit tests in all classes:  
	Step 1:	mvn clean test  
	
Run all the unit tests in a specific class:  
	Step 1:	mvn -Dtest=TestClassName test 
	Example:  mvn -Dtest=EnemyTest test  
	
Run an individual unit test(method) in a specific class:  
	Step 1:	mvn -Dtest=TestClassName#methodName test	
	Example:  mvn -Dtest=EnemyTest#testMovingEnemy_moveLeft test  
	
Run all the integration tests in all classes to ensure quality criteria are met:  
	Step 1:	mvn verify  
  
To remove all previous build and test:  
	Step 1:	mvn clean  
  

