Java Project Setup: 
	tools: maven, gradle
			distributed structure: source, test, images
			dependency mgmt: project object model.xml
								<project>
									<aritifact><group>												<dependencies><build>
								</project>
			deployment: java achieve rar / web archieve rar
					maven goals/ commands
						mvn clean		>> compile , download required 											dependency
						mvn package	>> jar/war(target folder)
						mvn install	>> push to remote repo
						mvn test		>> execute only test files


	maven dependencies:
		remote: mvnrepository.com	>> 55M libraries
			search for >> copy snippet look follows
					<dependency>
						<group-id></group-id>
						<artifact-id></artificat-id>
					</dependency>
			paste inside dependencies section of pom.xml

		local: c://users//ADMIN// .m2


	Maven Project:
		archetype>> quick-start(CLI), web
		src:
			main:
				java			>> source code
					group.id>> package
						App.java
				resources	>> keep static files, application.properties

			test:
				java>> test files(Junit, Mockito)

		target:
			generate, surfire reports, jar/war
		pom.xml		>> configuration file
	



Adding MySQL Dependency:
	1. mvnrepository.com
		search>> MySQL connector j
			click the first result>> top version>> click and copy dependency snippet

	2. paste it inside <dependencies> of pom.xml

	3. inside pom.xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-shade-plugin</artifactId>
      <version>3.5.0</version>
      <executions>
        <execution>
          <phase>package</phase>
          <goals>
            <goal>shade</goal>
          </goals>
          <configuration>
            <transformers>
              <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                <mainClass>doc.kub.cli.App</mainClass>
              </transformer>
            </transformers>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
  </build>

  mvn clean package
  java -jar target/demo-1.0-SNAPSHOT.jar