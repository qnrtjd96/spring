<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>	
	<div class="container">
		<img src="<%=request.getContextPath()%>/resources/001.png"/>
	</div>
</body>
</html>


<!-- 
	Spring에서 dbcp설정
	
	1.ojdbc8.jar 를 WEB-INF를 추가한다.
	2.build path 설정
	3.pom.xml dependence추가하기
	<!--ojdbc8.jar 
		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc8</artifactId>
			<version>11.2.0.1.0</version>
			<scope>system</scope>
			<systemPath>${basedir}/src/main/webapp/WEB-INF/lib/ojdbc8.jar</systemPath>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-jdbc</artifactId>
		    <version>5.2.6.RELEASE</version>
		</dependency>
		
	4.servlet-context.xml에 추가한다.
	오라클 서버위치,아이디, 비번, 드라이브
	<beans:bean class="org.springframwork.jdbc.datasource.DriverManagerDataSource">
		<bean:property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"></bean:property>
		<bean:property name="url" value="jdbc:oracle:thin:@localhost:1521:xe"></bean:property>
		<bean:property name="username" value="c##scott"></bean:property>
		<bean:property name="password" value="tiger"></bean:property>
	</beans:bean>
 -->