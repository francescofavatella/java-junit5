package com.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TestInfo Demo")
@ExtendWith(RandomParametersExtension.class)
@ExtendWith(TimingExtension.class)
class TestJUnit508DependencyInjectionForConstructorsAndMethods {

    TestJUnit508DependencyInjectionForConstructorsAndMethods(TestInfo testInfo) {
        assertEquals("TestInfo Demo", testInfo.getDisplayName());
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        List<String> names = List.of("TEST 1", "Test Random Parameter", "Test Random Parameter Wrong Type");
        String displayName = testInfo.getDisplayName();
        assertTrue(names.contains(displayName));
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("my-tag")
    void test1(TestInfo testInfo) {
        assertEquals("TEST 1", testInfo.getDisplayName());
        assertTrue(testInfo.getTags().contains("my-tag"));
    }

    @Test
    @DisplayName("Test Random Parameter")
    void testRandomParameter(@RandomParameter int param1, @RandomParameter int param2) {
        assertNotEquals(param1, param2);
    }

    @Test
    @DisplayName("Test Random Parameter Wrong Type")
    void testRandomParameterWrongType(@RandomParameter String param) {
        // it will fail because the parameter has a wrong type
    }

    @Test
    void testNameIsNotInTheAllowedList() {
        // it will fail
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface RandomParameter {
}

class RandomParametersExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(RandomParameter.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Random random = new Random();
        Class<?> type = parameterContext.getParameter().getType();
        if (int.class.equals(type)) {
            return random.nextInt();
        }
        throw new ParameterResolutionException("No random generator implemented for " + type);
    }
}


class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final String START_TIME = "start time";

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        getStore(context).put(START_TIME, System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method testMethod = context.getRequiredTestMethod();
        long startTime = getStore(context).remove(START_TIME, long.class);
        long duration = System.currentTimeMillis() - startTime;

        System.out.println(String.format("Method [%s] took %s ms.", testMethod.getName(), duration));
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
    }

}