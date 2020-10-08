# APPIUM_PEGASUS

1. Explain: 
Using maven project, apply Page Object Pattern and Cucumber framework for Appium on Android.
Commons package contains the common functions actions on elements. Declare the element UI (xpath) in pageUIs package. 
Package "pageObject" contains the specific function for each UI. Test case classes are defined in the src/test.

2. Configuration:
To run on real device, you have to connect the Android device to PC. Then change some paramters to config the connection.
Parameters:
- udid: your device ID, check by using "adb devices" on terminal commandline.
- platformVersion: input the Android version on your device. For ex: 6.1, 9, 10....
Each time connect the different devices to PC, change these paramters then run the test. Don't change other parameters.

3. How to use:
If you can maintenance the framework, just run the cucumber 1 time to check what cases are failed. 1-2 test case in the feature files are failed
and need to maintenance.
All the steps are written by Cucumber (Gherkin) and readable. 
Run file CucumberTestRunner in src/test/java/cucumberOptions.

If you want to run all testcases, input these parameters to "tags": 
- @RegisterAndLogin
- @CarTypeTestCases
- @MapTestCases
Like this: 
        tags = {"@RegisterAndLogin, @CarTypeTestCases, @MapTestCases"})
(Input the specific paramter if you just want to run the specific feature file.)

Then run the cucumberTestRunner.java file.
