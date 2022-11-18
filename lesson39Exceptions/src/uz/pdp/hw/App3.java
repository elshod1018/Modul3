package uz.pdp.hw;

import java.time.LocalDateTime;

public class App3 {

    public static void main(String[] args) {
        // TrafficLight: green(5), yellow(3), red(4)

        while (true){
            window(TrafficLight.GREEN, ANSI_GREEN);
            window(TrafficLight.YELLOW, ANSI_YELLOW);
            window(TrafficLight.RED, ANSI_RED);
            window(TrafficLight.YELLOW, ANSI_YELLOW);
        }

    }

    private static void window(TrafficLight trafficLight, String color) {
        System.out.print("\r"+color+trafficLight);
        LocalDateTime stopTime = LocalDateTime.now().plusSeconds(trafficLight.getSecond());
        while (LocalDateTime.now().isBefore(stopTime));
    }

    enum TrafficLight {
        GREEN (5), YELLOW (3), RED (4);
        private int second;

        TrafficLight(int second) {
            this.second = second;
        }

        public int getSecond() {
            return second;
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
}
