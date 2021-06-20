package dercochenko.com.Vehicle;

public enum Color {
    Red("Red"),
    Grey("Grey"),
    LightBlue("Light Blue"),
    Blue("Blue"),
    Green("Green"),
    Yellow("Yellow"),
    Pink("Pink"),
    Orange("Orange"),
    Brown("Brown"),
    White("White"),
    Black("Black"),
    Violet("Violet");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

}
