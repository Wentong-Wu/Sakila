package CucumberTest;

import com.Sakila.api.SakilaApp.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilmStepDefs {

    private ChromeOptions options = new ChromeOptions();
    private ChromeDriver driver = new ChromeDriver(options);
    private SakilaAppApplication sakilaAppApplication;

    @Mock
    FilmRepository filmRepository;
    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setup(){
        filmRepository = mock(FilmRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        sakilaAppApplication = new SakilaAppApplication(filmRepository,categoryRepository);
    }
    @Given("The application is running")
    public void the_application_is_running() {
        driver.get("http://localhost:3000");
        // Write code here that turns the phrase above into concrete actions

    }
    @When("the {string} page is open")
    public void the_page_is_open(String pageName) {
        // Write code here that turns the phrase above into concrete actions
        WebDriverWait wait = new WebDriverWait(driver,Duration.of(5,SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(pageName)));
        WebElement page = driver.findElement(By.linkText(pageName));
        page.click();
    }
    @When("the api connects")
    public void the_api_connects() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.of(500,SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='page-loaded']")));
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("display list of films")
    public void display_list_of_films() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.of(500,SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='film_id_loaded']")));
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//*[@class='film_id_loaded']"));
    }

    @After()
    public void closeBrowser(){
        driver.quit();
    }
}
