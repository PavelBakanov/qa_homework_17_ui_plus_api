package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    public static void openPage() {
        open("/profile");
    }

    public static void deleteCertainBook() {
        $("#delete-record-undefined").scrollTo().click();
        $("#closeSmallModal-ok").scrollTo().click();
    }

    public static void checkThatTheBookDeletedUI() {
        $("#see-book-Learning JavaScript Design Patterns").shouldNotBe(visible);
    }


}
