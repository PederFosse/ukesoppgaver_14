package webprog.ukesoppgaver;

public class Car {
    private int id;
    private String pnr;
    private String name;
    private String address;
    private String plate_number;
    private String brand;
    private String type;

    public Car(int id, String pnr, String name, String address, String plate_number, String brand, String type) {
        this.pnr = pnr;
        this.name = name;
        this.address = address;
        this.plate_number = plate_number;
        this.brand = brand;
        this.type = type;
    }

    public Car() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
