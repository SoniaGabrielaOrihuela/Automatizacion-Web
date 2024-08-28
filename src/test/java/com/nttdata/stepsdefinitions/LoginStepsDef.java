package com.nttdata.stepsdefinitions;

import com.nttdata.steps.InventorySteps;
import com.nttdata.steps.LoginSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;
public class LoginStepsDef {

    private WebDriver driver;

    private InventorySteps inventorySteps(WebDriver driver){
        return new InventorySteps(driver);
    }



    @Dado("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store");
        driver.get("https://qalab.bensg.com/store/pe/iniciar-sesion?back=https%3A%2F%2Fqalab.bensg.com%2Fstore%2Fpe%2F");
        screenShot();
    }

    @Y("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String user, String password) {
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.typeUser(user);
        loginSteps.typePassword(password);
        loginSteps.login();
        screenShot();

    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {

        WebElement categoryElement = driver.findElement(By.xpath("//a[@href='https://qalab.bensg.com/store/pe/3-clothes']"));
        categoryElement.click();
        WebElement subcategoryElement = driver.findElement(By.xpath("//li[@data-depth='0']"));
        subcategoryElement.click();
        screenShot();
    }

    @Y("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int unidades) {
        WebElement producto1 = driver.findElement(By.xpath("//img[@src='https://qalab.bensg.com/store/1-home_default/hummingbird-printed-t-shirt.jpg']"));
        producto1.click();
        WebElement agregar = driver.findElement(By.cssSelector("button[data-button-action='add-to-cart']"));
        agregar.click();
        for (int i = 0; i < unidades; i++) {

            agregar.click();

            screenShot();
        }

    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        WebElement popup = driver.findElement(By.xpath("//h4[@class='modal-title h6 text-sm-center']"));
        WebElement mensaje = driver.findElement(By.xpath("//h4[@class='modal-title h6 text-sm-center'],'Producto añadido correctamente a su carrito de compra')]"));
        Assertions.assertTrue(popup.isDisplayed());
        Assertions.assertTrue(mensaje.isDisplayed());
        screenShot();

    }
}

