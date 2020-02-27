package homework1;

public class Cat implements Participant {
    private String type;
    private String name;
    private int distance;
    private int height;

    public Cat(String name) {
        this.type = "Кот";
        this.name = name;
        this.distance = (int) (Math.random() * 500);
        this.height = (int) (Math.random() * 5);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int canRun() {
        System.out.println(this.type + " " + this.name + " пробегает дистанцию - " + this.distance + " м.");
        return distance;
    }

    @Override
    public String toString() {
        return "" + type +
                "  " + name + ' ' +
                ' ';
    }

    @Override
    public int canJump() {
        System.out.println(this.type + " " + this.name + " прыгает - " + this.height + " м.");
        return height;
    }
}
