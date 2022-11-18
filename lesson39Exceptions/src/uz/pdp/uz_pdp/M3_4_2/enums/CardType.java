package uz.pdp.uz_pdp.M3_4_2.enums;

public enum CardType {
    UZCARD("SUM"),
    HUMO("SUM"),
    VISA("DOLLAR"),
    UNION_PAY("DOLLAR");
    private String type;

    CardType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
