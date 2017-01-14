public class Carrot implements Munchable{
    final double length;
    final double radius;
    final static double DENSITY = 1.14;
    final static double CALORIES_PER_GRAM = 0.414;

    public Carrot (double length, double diameter) {
        this.length = length;
        this.radius = diameter / 2;
    }

    public double count() {
        final double volume = ((Math.PI * radius * radius) * length);
        return (volume * DENSITY) * CALORIES_PER_GRAM;
    }
}