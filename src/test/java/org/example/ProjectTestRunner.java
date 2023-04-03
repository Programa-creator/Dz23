package org.example;

import org.junit.Test;
import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.engine.EngineDiscoveryRequest;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.*;



public class ProjectTestRunner {
    public static void runTestClass(String className) {
        LauncherFactory.create().execute(
                LauncherDiscoveryRequestBuilder.request()
                        .selectors(DiscoverySelectors.selectClass(className))
                        .build()
        );
    }

    public static void runTestClass(Class<?> testClass) {
        LauncherFactory.create().execute(
                LauncherDiscoveryRequestBuilder.request()
                        .selectors(DiscoverySelectors.selectClass(testClass))
                        .build());
    }

    public static void runTestClasses(String... classNames) {
        LauncherDiscoveryRequestBuilder requestBuilder = LauncherDiscoveryRequestBuilder.request();
        Arrays.stream(classNames).forEach(classname -> requestBuilder.selectors(DiscoverySelectors.selectClass(classname)));
        LauncherDiscoveryRequest request = requestBuilder.build();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);
        launcher.execute(request, listener);
        PrintWriter out = new PrintWriter(System.out);
        listener.getSummary().printTo(out);
    }

    public static void runTestClasses(Class<?>... testClasses) {
        LauncherFactory.create().execute(
                LauncherDiscoveryRequestBuilder.request()
                        .selectors(Arrays.stream(testClasses)
                                .map(DiscoverySelectors::selectClass).toArray(DiscoverySelector[]::new))
                        .build());
    }

    //    public static void runTestsByPackageName(String packageName) {
//        Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(packageName)).setScanners(new TypeAnnotationsScanner(), new SubTypesScanner(false)));
//        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(RunWith.class);
//
//        List<Class> classList = new ArrayList<>(classes);
//        Class[] classesArray = classList.toArray(new Class[0]);
//
//        JUnitCore.runClasses(classesArray);
//    }
//public static void runTestsForClasses(Class<?>[] classes) {
//    Launcher launcher = LauncherFactory.create();
//    EngineDiscoveryRequest request = EngineDiscoveryRequestBuilder
//            .forClasses(classes)
//            .build();
//    TestPlan testPlan = launcher.discover(request);
//    LauncherDiscoveryRequest runRequest = LauncherDiscoveryRequestBuilder
//            .request()
//            .selectors(request.getSelectorsByType(ClassNameSelector.class))
//            .build();
//    TestExecutionListener listener = new SimpleTestExecutionListener();
//    launcher.registerTestExecutionListeners(listener);
//    launcher.execute(runRequest);
//}
//}
    public static void runTestsInClass(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    Object obj = clazz.getDeclaredConstructor().newInstance();
                    method.invoke(obj);
                    System.out.println("Test passed: " + method.getName());
                } catch (Exception e) {
                    System.out.println("Test failed: " + method.getName() + ", reason: " + e.getMessage());
                }
            }
        }
    }
}
