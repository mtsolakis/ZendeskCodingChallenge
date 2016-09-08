This is a project created as a submission for the Zendesk Coding Challenge by Michael Tsolakis.

The Project makes use of the following libraries:
* gson 2.3.1
* junit 4.12
* hamcrest core 1.3
* mockito 1.10.19
The jars for which can be found in the "External Libraries" directory in the project root.

Before using the application ensure you have the Java SE Development Kit 8u101 installed which can be found here:
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Launching instructions: 

Navigate to the "src" folder which can be found in the root of the project.

(LINUX Terminal)
----------------
Ensure both the "compile.sh" and "run.sh" files have executable permissions.

Compile the project by executing the "compile.sh" script.

Run the project by executing the "run.sh" script.


(WINDOWS command prompt)
------------------------
Ensure both the "compile.bat" and "run.bat" files have executable permissions.

Compile the project by executing the "compile.bat" script.

Run the project by executing the "run.bat" script.



(Testing - Eclipse IDE)
-----------------------
Install the Eclipse Neon IDE 4.6.0 from the following url:
https://eclipse.org/downloads/

Complete the setup and have established a workspace.
Unzip the "CodingChallenge.zip" file and copy the contained folder into your Eclipse workspace.

In your Eclipse IDE select File -> Import. Under "General" select "Existing Projects into Workspace" and click Next.

With "Select root directory" selected, browse to the location of the folder that was just placed into your workspace and select it.

Select the project from under the projects list and click finish.

Now that the project is available in your Eclipse IDE, the tests can be run by opening any of the classes in the test folder and clicking run.

If the project fails to run, you may need to re add the external libraries to the build path. Do so by right clicking on the project, hovering "Build Path" and clicking "Configure Build Path".

Once the window has oppened, under the libraries tab, remove any jars which failed to load correctly and re-add them by clicking "Add JARs" and selecting them from the "External Libraries" directory in the project root.







