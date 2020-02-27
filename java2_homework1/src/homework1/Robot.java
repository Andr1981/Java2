package homework1;

public class Robot implements Participant {
    private String type;
    private String name;
    private int distance;
    private int height;

    public Robot(String name) {
        this.type = "Робот";
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

    public int canRun() {
        System.out.println(this.type + " " + this.name + " пробегает дистанцию - " +
                this.distance + " м.");
        return distance;
    }

    public int canJump() {
        System.out.println(this.type + " " + this.name + " прыгает - " +
                this.height + " м.");
        return height;
    }

    @Override
    public String toString() {
        return "" + type +
                "  " + name + ' ' +
                ' ';
    }
}
