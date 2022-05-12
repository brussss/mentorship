package com.gsmserver;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import gsmserver.HomePage;
import gsmserver.SearchResultPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class SearchTests {

    @BeforeAll
    private static void OpenMaximizeWindow() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }


//KISS+DRY (Don't repeat yourself & keep it simple stupid

    @BeforeEach
    void openHomePage () {
        open("https://gsmserver.com/");
    }

    @Test
    void searchProductByTitleAndAddToCart() {

        var productName = "Front View Camera for Toyota Highlander 2012-2013 YM";
        var productId = 891888;

        OpenMaximizeWindow();

        $("[name='searchword']").setValue(productName).pressEnter();
        $(".product-tile").shouldHave(text(productName));

        $(by("key", String.valueOf(productId))).$(".btn--add-to-cart").click();

        $("[space*=\"cart\"]").click();
        $("[class='page_cart_container container']").shouldHave(Condition.text(productName));

        $("[name='checkout']").shouldBe(Condition.visible, text("Proceed to Checkout")).click();
        System.out.println("Successful Checkout");
    }

    @Test
    void searchProductByTitleTest() {
        var productName = "Front View Camera for Toyota Highlander 2012-2013 YM";

        new HomePage().searchFor(productName);
        var actualSearchResultTitle = new SearchResultPage().getSearchResultTitle();

        Assertions.assertEquals(productName,actualSearchResultTitle);
    }

}
