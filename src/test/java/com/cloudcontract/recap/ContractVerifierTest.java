package com.cloudcontract.recap;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThat;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.Test;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;

@AutoConfigureStubRunner
public class ContractVerifierTest extends MvcTest {

  @Test
  public void validate_shouldMarkClientAsFraud() throws Exception {
    // given:
//    MockMvcRequestSpecification request = given()
//        .header("Content-Type", "application/json")
//        .body("{\"description\":\"I am a simple description\",\"completed\":true}");
    MockMvcResponse request = given()
        .when()
        .get("https://ocean-rest-test.herokuapp.com/items");

    // when:
    ResponseOptions response = given().when()
        .get("https://ocean-rest-test.herokuapp.com/items");

    // then:
    //assertThat(String.valueOf(response.statusCode())).isEqualTo(200);
//    assertThat(response.header("Content-Type"));
    //assertThat(response.header("Content-Type")).isEqualTo("application/json");
    // and:
    DocumentContext parsedJson = JsonPath.parse(response.statusCode());
    //assertThatJson(parsedJson).field("description").isEqualTo("Result of successful REST call!!");
    assertThatJson(parsedJson).field("description").isEqualTo("hello");
//    assertThatJson(parsedJson).field("completed").isEqualTo(true);
  }

}
