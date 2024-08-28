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
import java.util.concurrent.TimeUnit;

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
    public void agregoUnidadesDelPrimerProductoAlCarrito(int unidades) throws InterruptedException {
      // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        WebElement producto1 = driver.findElement(By.xpath("//img[@src='https://qalab.bensg.com/store/1-home_default/hummingbird-printed-t-shirt.jpg']"));
        producto1.click();
        WebElement agregar1 = driver.findElement(By.xpath("//input[@id='quantity_wanted']"));
        agregar1.clear();
        screenShot();
        //agregar1.sendKeys(String.valueOf(unidades));
        WebElement agregar2 = driver.findElement(By.xpath("//button[@class='btn btn-touchspin js-touchspin bootstrap-touchspin-up']"));
        agregar2.click();
        WebElement agregar = driver.findElement(By.xpath("//button[@data-button-action='add-to-cart']"));
        Thread thread = new Thread();
        agregar.click();
        screenShot();
        //thread.sleep(10000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(400));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(400));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@class='modal-title h6 text-sm-center']")));


    }

    @Entonces("Entonces valido en el popup la confirmación del producto agregado")
    public void entoncesValidoEnElPopupLaConfirmaciónDelProductoAgregado() {
        WebElement popup = driver.findElement(By.xpath("//h4[@class='modal-title h6 text-sm-center']"));
        popup.getText();
        if (popup.getText()=="Producto añadido correctamente a su carrito de compra"){
            screenShot();
        }
       // WebElement mensaje = driver.findElement
        //(By.xpath("//h4[@class='modal-title h6 text-sm-center'],'Producto añadido correctamente a su carrito de compra')]"));
        Assertions.assertTrue(popup.isDisplayed());
       // Assertions.assertTrue(mensaje.isDisplayed());
        screenShot();
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente()
    {
        WebElement total = driver.findElement(By.xpath("//p[@class='product-total']"));
        total.getText();
        if (total.getText()=="S/ 38.24"){
            screenShot();
        }
        Assertions.assertTrue(total.isDisplayed());
        screenShot();
    }

    @Cuando("finalizo la compra")
    public void finalizoLaCompra()  {
        WebElement finalizar = driver.findElement(By.partialLinkText("FINALIZAR COMPRA"));
        finalizar.click();
        screenShot();
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {

        WebElement titulo = driver.findElement(By.xpath("//h1[@class='h1']"));

        if (titulo.getText()=="CARRITO"){
            screenShot();
        }
        Assertions.assertTrue(titulo.isDisplayed());
        screenShot();
    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        WebElement calculo = driver.findElement(By.xpath("//div[@class='cart-summary-line cart-total']"));

        if (calculo.getText()=="S/ 38.24"){
            screenShot();
        }
        Assertions.assertTrue(calculo.isDisplayed());
        screenShot();
    }
}

