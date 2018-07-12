# teamcity-xenserver-plugin

### Set up the environment
To get started writing a plugin for TeamCity, set up the plugin development environment. https://confluence.jetbrains.com/display/TCD18/Getting+Started+with+Plugin+Development#GettingStartedwithPluginDevelopment-Step1.Setuptheenvironment

1) Download and install Oracle Java. Set the Java_Home environment variable on your system.  Java 1.8 is required since TeamCity 10, the 32-bit version is recommended, the 64-bit version can be used.
2) Download and install TeamCity on your development machine. Since you are going to use this machine to test your plugin, it is recommended that this TeamCity server is of the same version as your production server. We are using TeamCity 9.0.2 installed on Windows in our setup.
3) Download and install a Java IDE; we are using Intellij IDEA Community Edition, which has a built-in Maven integration.
4) Download and install Apache Maven. Maven 3.2.x is recommended. Set the M2_HOME environment variable. Run mvn -version to verify your setup. We are using Maven 3.2.5. in our setup.

### Building package

Run `mvn package`
