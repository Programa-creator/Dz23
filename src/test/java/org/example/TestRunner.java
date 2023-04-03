package org.example;

import org.junit.platform.console.ConsoleLauncher;

public class TestRunner {
    public static void main(String[] args) {
  //      ConsoleLauncher.main(args);
        ProjectTestRunner.runTestClass("org.example.SimpleMathLibraryTest");
        ProjectTestRunner.runTestClass(SimpleMathLibraryTest.class);
        ProjectTestRunner.runTestClasses("org.example.SimpleMathLibraryTest");
        ProjectTestRunner.runTestClasses(SimpleMathLibraryTest.class);
        ProjectTestRunner.runTestsInClass("org.example".getClass());
    }
}
