import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SimpleJunitTest {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl="https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void successfulSearchTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Adil");
        $("#lastName").setValue("Pankirey");
        $("#userEmail").setValue("pankireyadil@gmail.com");
        $(byText("Male")).click();
        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $("#userNumber").setValue("7089598475");

        //Date of birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue(String.valueOf(11));
        $(".react-datepicker__year-select").selectOptionByValue(String.valueOf(1999));
        $(byText("2")).click();

        executeJavaScript("window.scrollBy(0,500)");// почему то без этого крашится из-за рекламы

        //Current address
        $("#currentAddress").setValue("Kazakhstan, Astana");

        //Subjects selecting
        $("#subjectsInput").setValue("En").pressEnter();
        $("#subjectsInput").setValue("Co").sendKeys(Keys.DOWN);
        $("#subjectsInput").pressEnter();

        //File uploading
        File file = new File("src/test/resources/picture.jpg");
        $("#uploadPicture").uploadFile(file);

        //State
        $("#state").click();
        $(byText("NCR")).click();

        //City
        $("#city").click();
        $(byText("Delhi")).click();


        $("#submit").click();

        $$("tr").get(1).shouldHave(text("Adil Pankirey"));
        $$("tr").get(2).shouldHave(text("pankireyadil@gmail.com"));
        $$("tr").get(3).shouldHave(text("Male"));
        $$("tr").get(4).shouldHave(text("7089598475"));
        $$("tr").get(5).shouldHave(text("2 December,1999"));
        $$("tr").get(6).shouldHave(text("English, Commerce"));
        $$("tr").get(7).shouldHave(text("Sports, Reading"));
        $$("tr").get(8).shouldHave(text("picture.jpg"));
        $$("tr").get(9).shouldHave(text("Kazakhstan, Astana"));
        $$("tr").get(10).shouldHave(text("NCR Delhi"));


    }
}
