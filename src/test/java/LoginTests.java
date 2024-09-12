import fabrica.DriverFactory;
import fabrica.DriverType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import paginas.PaginaLogin;

public class LoginTests {

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
    public void validarLoginSucesso(DriverType driverType) {
        DriverFactory.iniciarDriver(driverType);
        PaginaLogin loginPage = new PaginaLogin();
        loginPage.realizarLogin("standard_user", "secret_sauce");
        loginPage.validarLogin("sucesso");
    }

    @ParameterizedTest
    @EnumSource(value = DriverType.class)
    public void validarLoginBloqueado(DriverType driverType) {
        DriverFactory.iniciarDriver(driverType);
        PaginaLogin loginPage = new PaginaLogin();
        loginPage.realizarLogin("locked_out_user", "secret_sauce");
        loginPage.validarLogin("bloqueio");
    }

    @ParameterizedTest
    @EnumSource(value = DriverType.class)
    public void validarCredenciaisIncorretas(DriverType driverType) {
        DriverFactory.iniciarDriver(driverType);
        PaginaLogin loginPage = new PaginaLogin();
        loginPage.realizarLogin("abc", "abc");
        loginPage.validarLogin("invalido");
    }
}