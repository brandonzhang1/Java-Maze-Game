#### Instructions to build, run, and test the game:  
    
cd into the base directory of the project called **project**. Run the following commands:
   
1. Compile and run the game:  
	Step 1:	mvn clean install  
	Step 2:	java -cp target\276project-1.0-SNAPSHOT.jar Main  

2. Compile and run the game (without running tests):  
	Step 1:	mvn install -DskipTests  
	Step 2:	java -cp target\276project-1.0-SNAPSHOT.jar Main 
	
3. Run all the unit tests in all classes:  
	Step 1:	mvn clean test  
	
4. Run all the unit tests in a specific class:  
	Step 1:	mvn -Dtest=TestClassName test 
	Example:  mvn -Dtest=EnemyTest test  
	
5. Run an individual unit test(method) in a specific class:  
	Step 1:	mvn -Dtest=TestClassName#methodName test	
	Example:  mvn -Dtest=EnemyTest#testMovingEnemy_moveLeft test  
	
6. Run all the integration tests in all classes to ensure quality criteria are met:  
	Step 1:	mvn verify  
  
7. To remove all previous build and test:  
	Step 1:	mvn clean  
  

