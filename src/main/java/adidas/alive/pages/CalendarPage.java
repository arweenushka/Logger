package adidas.alive.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CalendarPage {

    private static final Duration EXPLICIT_WAIT = Duration.ofSeconds(20);
    private WebDriver webDriver;

    @FindBy(id = "__button4")
    WebElement monthBtn;

    @FindBy(id = "ivuFrm_page0ivu1")
    WebElement frame;

    @FindBy(id = "__picker0-inner")
    WebElement startTimeInput;

    @FindBy(id = "__picker1-inner")
    WebElement endTimeInput;

    @FindBy(xpath = "//input[contains(@placeholder,'SET START BREAK')]")
    WebElement startBreakInput;

    @FindBy(xpath = "//input[contains(@placeholder,'SET END BREAK')]")
    WebElement endBreakInput;

    @FindBy(id = "__button2")
    WebElement saveBtn;

    @FindBy(xpath = "//div[contains(@class,'selected')]/..//bdi")
    WebElement selectedMonthLbl;

    public String getSelectedMonthText() {
        return selectedMonthLbl.getText();
    }

    public CalendarPage(WebDriver driver) {
        webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    public void switchToMonthView() {
        webDriver.switchTo().frame(frame);
        WebDriverWait wait = new WebDriverWait(webDriver, EXPLICIT_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(monthBtn)).click();

    }

    public void fillTimePerShortDay(String startTime, String endTime, String startBreak, String endBreak) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, EXPLICIT_WAIT);
        List<WebElement> untrackedFridaysInCalendar;
        untrackedFridaysInCalendar = webDriver.findElements(By.xpath("//div[contains(@id,'hbox')]//div[5]/div/div[2]/span[contains(@aria-label,'icon-alert')]"));
        int amountOfFridays = untrackedFridaysInCalendar.size();

        for (int i = 0; i < amountOfFridays; i++) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner")));

            //reinitialize elements as page is reloaded after each "save"
            untrackedFridaysInCalendar = webDriver.findElements(By.xpath("//div[contains(@id,'hbox')]//div[5]/div/div[2]/span[contains(@aria-label,'icon-alert')]"));

            //open the day in calendar
            wait.until(ExpectedConditions.elementToBeClickable(untrackedFridaysInCalendar.get(0))).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner")));

            //fill all time inputs
            fillTimeInputs(startTime, endTime, startBreak, endBreak);
            saveTime();

        }
    }

    public void fillTimePerFullDay(String startTime, String endTime, String startBreak, String endBreak) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, EXPLICIT_WAIT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner")));

        List<WebElement> untrackedDaysInCalendar;
        untrackedDaysInCalendar = webDriver.findElements(By.xpath("//span[contains(@aria-label,'icon-alert')]"));
        int amountOfDays = untrackedDaysInCalendar.size();

        for (int i = 0; i < amountOfDays; i++) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner")));

            //reinitialize elements as page is reloaded after each "save"
            untrackedDaysInCalendar = webDriver.findElements(By.xpath("//span[contains(@aria-label,'icon-alert')]"));

            //open the day in calendar
            wait.until(ExpectedConditions.elementToBeClickable(untrackedDaysInCalendar.get(0))).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner")));

            //fill all time inputs
            fillTimeInputs(startTime, endTime, startBreak, endBreak);
            saveTime();

        }
    }

    private void fillTimeInputs(String startTime, String endTime, String startBreak, String endBreak) {
        startTimeInput.click();
        startTimeInput.sendKeys(startTime);
        endTimeInput.click();
        endTimeInput.sendKeys(endTime);
        startBreakInput.click();
        startBreakInput.sendKeys(startBreak);
        endBreakInput.click();
        endBreakInput.sendKeys(endBreak);
    }

    private void saveTime() {
        saveBtn.click();
    }
}
