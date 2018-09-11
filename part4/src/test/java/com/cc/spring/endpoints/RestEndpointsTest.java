package com.cc.spring.endpoints;

import com.cc.spring.RestApplication;
import com.cc.spring.domain.User;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.cc.spring.util.RestUtils.generateSampleUser;
import static io.restassured.RestAssured.given;

// Ergänzen um Annotationen, die Spring-Support aktivieren und den Context konfigurieren (Achtung: SpringBoot!)
// Zusätzlich benötigen wir einen laufenden Container - Annotation für Integrationstests hinzufügen
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = RestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port = 8080"}
)
public class RestEndpointsTest {
    
	@Test
	public void testWithSpringRestTemplate() {
	    // Given
        RestTemplate restTemplate = new RestTemplate();

	    // When
        String result = restTemplate.getForObject("http://localhost:8080/api/hello", String.class);

	    // Then
        Assert.assertEquals(RestEndpoints.RESPONSE, result);
	}

	@Test
    public void testWithRestAssured() {
        User user = generateSampleUser();

        given()
            .contentType(ContentType.JSON)
            .body(user)
        .when()
            .post("http://localhost:8080/api/produkt.json")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(ContentType.JSON);
    }
	
	
	
	

}
