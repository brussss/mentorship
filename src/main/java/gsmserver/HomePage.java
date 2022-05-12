package gsmserver;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public void searchFor(String searchQuery) {
        $("[name='searchword']").setValue(searchQuery).pressEnter();
    }
}
