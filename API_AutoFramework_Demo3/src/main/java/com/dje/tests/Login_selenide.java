package com.dje.tests;

import org.testng.annotations.Test;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class Login_selenide {
	@Test
	public void testBaidu() throws InterruptedException {
		Configuration.browser="Chrome";
		Configuration.baseUrl="https://www.baidu.com";
		open("/");
		$("#kw").setValue("selenide");
		$("#su").click();
		Thread.sleep(1000);
		$$("h3>a").shouldHave(size(9));
		$("h3>a").setValue(String.valueOf(text("selenide_百度翻译")));
	}
}
