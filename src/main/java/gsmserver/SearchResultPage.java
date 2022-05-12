package gsmserver;

import static com.codeborne.selenide.Selenide.$;

public class SearchResultPage {
    public String getSearchResultTitle() {
        return $(".product-tile").getText();
    }
}
