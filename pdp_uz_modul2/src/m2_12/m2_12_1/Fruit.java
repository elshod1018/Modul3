package m2_12.m2_12_1;

public class Fruit {
    private String name;
    private String type;
    private double sale=0;

    public Fruit() {
    }

    public Fruit(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Fruit(String name, String type, double sale) {
        this.name = name;
        this.type = type;
        this.sale = sale;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
