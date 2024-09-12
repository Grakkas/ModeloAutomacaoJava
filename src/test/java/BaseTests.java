import fabrica.DriverFactory;
import fabrica.DriverType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class BaseTests {

    @AfterAll
    public static void depoisDeTudo() {
        DriverFactory.fecharDriver();
    }

    @AfterEach
    public void depoisDeCada() {
        DriverFactory.fecharDriver();
    }

    @ParameterizedTest
    @EnumSource(value = DriverType.class)
    public void validarAberturaDoDriver(DriverType driverType) {
        DriverFactory.iniciarDriver(driverType);
    }
}