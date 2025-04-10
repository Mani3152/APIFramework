package example;


import File.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJsonParse {

    public static void main(String[] args) {
        int price = 0;

        JsonPath js = new JsonPath(payload.CoursePrise());
        int count = js.getInt("courses.size()");
        System.out.println(count);
        int amount = js.getInt("dashboard.purchaseAmount");
        System.out.println(amount);
        for (int i = 0; i < count; i++) {
            String titles = js.get("courses[" + i + "].title");
            System.out.println(titles);
            int value = js.get("courses[" + i + "].price");
            price = price + value;

        }
        System.out.println(price);
        int copies=0;
        for (int j=0;j<count;j++){
            String titles = js.get("courses[" + j + "].title");
            if (titles.equalsIgnoreCase("RPA")){
                 copies = js.get("courses[" + j + "].copies");
                break;
            }
        }
        Assert.assertEquals(copies,10);
    }
}
