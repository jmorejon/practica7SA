package com.tistory.heowc;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.tistory.heowc.web.SimpleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@WebMvcTest(SimpleController.class)
public class SeleniumTests {

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private WebClient webClient;

    @Test
    public void testClientExample() throws IOException {
        HtmlPage page = webClient.getPage("/hello/world");
        assertThat(page.getBody().getTextContent()).isEqualTo("hello, world");
    }

    @Test
    public void testDriverExample() {
        webDriver.get("/hello/world");
        assertThat(webDriver.findElement(By.tagName("body")).getText()).isEqualTo("hello, world");
    }
}