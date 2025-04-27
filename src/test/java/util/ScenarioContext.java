package util;

import factory.DriverFactory;
import io.cucumber.java.Scenario;

public class ScenarioContext {

    private static Scenario scenario;

    public static void setScenario(Scenario scenario) {
        ScenarioContext.scenario = scenario;
    }

    public static Scenario getScenario() {
        return scenario;
    }

    public static void addScreenshot(String description, boolean isFullpage) {
        getScenario().attach(DriverFactory.takeScreenshot(isFullpage), "image/png", description);
    }
}
