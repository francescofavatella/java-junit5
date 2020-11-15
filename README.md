# Introduction to junit5

## How to install junit5
### Create a new gradle project
https://www.jetbrains.com/help/idea/getting-started-with-gradle.html

### Add Junit5
https://www.baeldung.com/junit-5-gradle

https://junit.org/junit5/docs/current/user-guide/
     
     repositories {
        mavenCentral()
     }
    
    dependencies {
        testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
        testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    
        testCompileOnly 'junit:junit:4.12'
        testRuntimeOnly 'org.junit.vintage:junit-vintage-engine:5.7.0'
        
        testImplementation 'org.junit.jupiter:junit-jupiter-params:5.7.0'
    }
    
### Add Mockito
https://www.baeldung.com/mockito-junit-5-extension

        repositories {
            jcenter()
        }
        
        dependencies {
            testRuntimeOnly "org.mockito:mockito-core:2.23.0"
            testCompileOnly "org.mockito:mockito-junit-jupiter:2.23.0"
        }

### Add Lombok (not required)
https://projectlombok.org/setup/gradle

    repositories {
        mavenCentral()
    }
    
    dependencies {
        compileOnly 'org.projectlombok:lombok:1.18.16'
        annotationProcessor 'org.projectlombok:lombok:1.18.16'
        
        testCompileOnly 'org.projectlombok:lombok:1.18.16'
        testAnnotationProcessor 'org.projectlombok:lombok:1.18.16'
    }  
    
## How to use junit5
#### 0. Platform
JUNIT 5 =  platform + jupiter + vintage

The **JUnit Platform** serves as a foundation for launching testing frameworks on the JVM. It also defines the TestEngine API for developing a testing framework that runs on the platform. Furthermore, the platform provides a Console Launcher to launch the platform from the command line and a JUnit 4 based Runner for running any TestEngine on the platform in a JUnit 4 based environment. First-class support for the JUnit Platform also exists in popular IDEs (see IntelliJ IDEA, Eclipse, NetBeans, and Visual Studio Code) and build tools (see Gradle, Maven, and Ant).

**JUnit Jupiter** is the combination of the new programming model and extension model for writing tests and extensions in JUnit 5. The Jupiter sub-project provides a TestEngine for running Jupiter based tests on the platform.

**JUnit Vintage** provides a TestEngine for running JUnit 3 and JUnit 4 based tests on the platform.

#### 1. Test instance lifecycle
    @BeforeEach (@Before)
    @AfterEach (@After)
    @BeforeAll (@BeforeClass)
    @AfterAll (@AfterClass)
    
#### 2. Meta-Annotations and Composed Annotations    
    @Tag
    Composed annotation
    Gradle Task to use tags

TODO: Check how to pass tags in gradlew
    
    task slowTest(type: Test) {
        useJUnitPlatform {
            includeTags 'slow'
        }
    }

    ./gradlew slowTest

#### 3. Nested Classes
    @Nested
    
#### 4. Disable tests
    @Disabled (@Ignore)
    @DisplayName
    @DisplayNameGeneration

Display name works only if you use intellij runner and in the html report.

#### 5. Assertions
    GroupedAssertions (assertAll)
    Exceptions (assertThrows)
    Timeout (assertTimeout)

#### 6. Assumptions
    assumeTrue
    assumeThat

#### 7. Conditional Test Execution
    @EnabledIfSystemProperty 
    @DisabledIfSystemProperty
    @EnabledIfEnvironmentVariable
    @EnabledIf
    @DisabledIf
    
#### 8. Dependency Injection for Constructors and Methods
    TestInfo
    Custom params
    @ExtendWith
    
#### 9. Timeout Test
    @Timeout
   
#### 10. Dynamic Test Examples
    @TestFactory
   
#### 11. Parametrized Tests
    @ParameterizedTest (@Theory)

#### 12. Mockito integration
    @ExtendWith(MockitoExtension.class)
