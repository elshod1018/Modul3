package m2_2.m2_2_5;

public class Ruchka {
    private String color;
    private double inkRate;
    private double inkUsage;
    private boolean button;

    public Ruchka() {
    }

    public Ruchka(String color, double inkRate, double inkUsage) {
        this.color = color;
        this.inkRate = inkRate;
        this.inkUsage = inkUsage;
    }

    public void write(String text) {
        if (button) {
            for (int i = 0; i < text.length(); i++) {
                if (Character.isAlphabetic(text.charAt(i))) {
                    if ((!Character.isUpperCase(text.charAt(i))) && inkRate >= inkUsage) {
                        System.out.print(text.charAt(i));
                        this.inkRate -= inkUsage;
                    } else if ((Character.isUpperCase(text.charAt(i))) && inkRate >= 2 * inkUsage) {
                        System.out.print(text.charAt(i));
                        this.inkRate -= (2 * inkUsage);
                    } else {
                        System.out.println("\nSiyoh tugadi,pastani almashtiring");
                        break;
                    }
                }
                else {
                    System.out.print(text.charAt(i));
                }
            }
        } else {
            System.out.print("Yozishdan oldin tugmani bosing");
        }
    }

    public void clickButton() {
        System.out.println("***** Chiq *****");
        button = !button;
    }

    public void changeInk() {
        inkRate = 100;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setInkRate(double inkRate) {
        this.inkRate = inkRate;
    }

    public void setInkUsage(double inkUsage) {
        this.inkUsage = inkUsage;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public String getColor() {
        return color;
    }

    public double getInkRate() {
        return inkRate;
    }

    public double getInkUsage() {
        return inkUsage;
    }

    public boolean isButton() {
        return button;
    }

    @Override
    public String toString() {
        return "Ruchka{" +
                "color='" + color + '\'' +
                ", inkRate=" + inkRate +
                ", inkUsage=" + inkUsage +
                ", button=" + button +
                '}';
    }
}
