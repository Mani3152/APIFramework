package File;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    @Test(dataProvider = "Books")
    public void AddBook(String isbn, String aisle) throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-type", "application/json")
                .body(payload.AddBook(isbn,aisle)).when()
                .post("Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReusableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }


    @DataProvider(name = "Books")
    public Object[][] getData() {
        return new Object[][]{
                {"Selenium", "4.5"}, {"Java", "11"}, {"Cucumber", "7"}};
    }

    //Static Json fom file (read json from the file)
    @Test
    public void AddBook() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\dange\\Downloads\\sample1.json")))).when()
                .post("Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReusableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);

    }
}