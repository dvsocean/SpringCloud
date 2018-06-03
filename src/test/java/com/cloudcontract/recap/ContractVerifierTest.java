package com.cloudcontract.recap;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThat;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.Test;

public class ContractVerifierTest extends MvcTest {

  @Test
  public void validate_shouldMarkClientAsFraud() throws Exception {
    // given:
    MockMvcRequestSpecification request = given()
        .header("Content-Type", "application/json")
        .body("{\"description\":\"I am a simple description\",\"completed\":true}");

    // when:
    ResponseOptions response = given().spec(request)
        .get("https://ocean-rest-test.herokuapp.com/items");

    // then:
    assertThat(String.valueOf(response.statusCode())).isEqualTo(200);
    //assertThat(response.statusCode()).isEqualTo(200);
    assertThat(response.header("Content-Type")).isEqualTo("application/json");
    // and:
    DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
    assertThatJson(parsedJson).field("description").isEqualTo("FRAUD");
    assertThatJson(parsedJson).field("completed").isEqualTo("Amount too high");
  }

}
