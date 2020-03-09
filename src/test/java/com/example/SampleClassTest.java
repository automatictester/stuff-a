package com.example;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SampleClassTest {

    private static final String NOW = "16:31:17.904";
    private SampleClass sampleClass;

    @DataProvider(name = "inputData")
    public Object[][] createInputData() {
        return new Object[][]{
                {"VERSION", "1.0"},
                {"PING", "pong"},
                {"PING something", "something"},
                {"TIME", NOW}
        };
    }

    @BeforeClass
    public void setup() {
        LocalTimer timer = mock(LocalTimer.class);
        when(timer.now()).thenReturn(NOW);
        sampleClass = new SampleClass(timer);
    }

    @Test(dataProvider = "inputData")
    public void testHandleCommand(String commandLine, String expectedResult) {
        String result = sampleClass.handleCommand(commandLine);
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    public void testCount() {
        List<String> commandLines = Arrays.asList("VERSION", "PING", "PING abc", "TIME", "COUNT");
        commandLines.forEach(c -> sampleClass.handleCommand(c));
        assertThat(sampleClass.handleCommand("COUNT"), equalTo("{PING=2, VERSION=1, TIME=1, COUNT=2}"));
    }
}
