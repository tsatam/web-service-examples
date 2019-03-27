package com.github.tsatam.webservicesexamples.apitest.timing;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.*;

public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    private static final Logger logger = Logger.getLogger(TimingExtension.class.getName());
    private static final String START_TIME = "start time";

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        getStore(context).put(START_TIME, System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        Method testMethod = context.getRequiredTestMethod();
        var startTime = getStore(context).remove(START_TIME, long.class);
        var duration = System.currentTimeMillis() - startTime;

        logger.info(() -> String.format("Method [%s] took %s ms.", testMethod.getName(), duration));
    }

    private Store getStore(ExtensionContext context) {
        return context.getStore(create(getClass(), context.getRequiredTestMethod()));
    }
}
