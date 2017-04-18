package Util;

/**
 * ACIT 2515 Final Test
 *
 * @author Daniele Lisi - A00978006 Set A
 * @date 2017-04-17
 */
public class Transaction {

    //street,city,zip,state,beds,baths,sq__ft,type,sale_date,price,latitude,longitude
    private String street;
    private String city;
    private Integer zip;
    private String state;
    private Integer beds;
    private Integer baths;
    private Integer sqFeet;
    private String type;
    private String saleDate;
    private Integer price;
    private Double latitude;
    private Double longitude;

    public Transaction(String street, String city, Integer zip, String state, Integer beds, Integer baths, Integer sqFeet, String type, String saleDate, Integer price, Double latitude, Double longitude) {
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.beds = beds;
        this.baths = baths;
        this.sqFeet = sqFeet;
        this.type = type;
        this.saleDate = saleDate;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public Integer getZip() {
        return zip;
    }

    public String getState() {
        return state;
    }

    public Integer getBeds() {
        return beds;
    }

    public Integer getBaths() {
        return baths;
    }

    public Integer getSqFeet() {
        return sqFeet;
    }

    public String getType() {
        return type;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public Integer getPrice() {
        return price;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Transaction {" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                ", state='" + state + '\'' +
                ", beds=" + beds +
                ", baths=" + baths +
                ", sqFeet=" + sqFeet +
                ", type='" + type + '\'' +
                ", saleDate=" + saleDate +
                ", price=" + price +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
