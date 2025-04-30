package mobile.steps;

import factory.DriverFactory;
import factory.DriverType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.jupiter.api.Assertions;
import util.ScenarioContext;

public class Hooks {

    @Before
    public void antesCadaTeste(Scenario scenario) {
        ScenarioContext.setScenario(scenario);
        Assertions.assertTrue(DriverFactory.createWebDriver(DriverType.ANDROID));
        ScenarioContext.addScreenshot("Tela Inicial", false);
    }

    @After
    public void depoisCadaTeste() {
        Assertions.assertTrue(DriverFactory.closeWebDriver(), "Ocorreu um erro durante o fechamento do navegador");
    }
}
