package utils;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Utils {
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private String password;
    private String city;
    private String address;
    private String zip;
    private String phoneNumber;
    private String creditCard;
    private String cardNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void generateUserInfo(){
        Faker faker = new Faker();
        setFirstName(faker.name().firstName());
        setLastName(faker.name().lastName());
        setEmail(faker.internet().emailAddress());
        setCompanyName("LLC company");
        setPassword(faker.internet().password());
    }
    public void saveUserInfo(String firstName, String lastName, String email, String companyName, String password) throws IOException, ParseException {
        String fileName="./src/test/resources/Users.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject userObj = new JSONObject();

        userObj.put("firstname",firstName);
        userObj.put("lastname",lastName);
        userObj.put("email",email);
        userObj.put("companyName",companyName);
        userObj.put("password",password);
        jsonArray.add(userObj);

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }

//    public static JSONObject loadJson(String file, int index) throws IOException, ParseException {
//        JSONParser jsonParser = new JSONParser();
//        Object obj = jsonParser.parse(new FileReader(file));
//        JSONArray jsonArray = (JSONArray) obj;
//        return (JSONObject) jsonArray.get(index);
//    }

    public static void replaceJsonFile() throws IOException {
        String fileName="./src/test/resources/Users.json";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write("[]");
        fileWriter.flush();
        fileWriter.close();
    }
    public static void replaceJsonGuestFile() throws IOException {
        String fileName="./src/test/resources/Users.json";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write("[]");
        fileWriter.flush();
        fileWriter.close();
    }

    public void generateGuestUserInfo(){
        Faker faker = new Faker();
        setFirstName(faker.name().firstName());
        setLastName(faker.name().lastName());
        setEmail(faker.internet().emailAddress());
        setCity(faker.address().city());
        setAddress(faker.address().streetAddress());
        setZip(faker.address().zipCode());
        setPhoneNumber(faker.phoneNumber().phoneNumber());
    }

    public void saveGuestUserInfo(String firstName, String lastName, String email, String city, String address, String zip, String phoneNumber) throws IOException, ParseException {
        String fileName="./src/test/resources/GuestUser.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject userObj = new JSONObject();

        userObj.put("firstname",firstName);
        userObj.put("lastname",lastName);
        userObj.put("email",email);
        userObj.put("city",city);
        userObj.put("address",address);
        userObj.put("zip",zip);
        userObj.put("phoneNumber",phoneNumber);
        jsonArray.add(userObj);

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }
    public void generateCreditCardInfo(){
        Faker faker = new Faker();
        setFirstName(faker.name().fullName());
        setCreditCard("4663-2141-8269-7448");
        setCardNumber("7444");
    }
}
