How to build and run the solution:
----------------------------------
To run this code machine should have Java and Maven configured in classpath.
I have used maven 3.2.5 and JRE System Library [JavaSE1.8]

	1. Open command prompt and navigate to project home directory e.g C:\PromotionEngine
	2. mvn clean package
   	3. mvn exec:java -Dexec.mainClass="org.abhi.promotion.client.OrderCalculateClient" -- to see the output as desired format.

	Or import the project in any of the IDE and see the result after running OrderCalculateClient class in console.