package homework1;

public class Wall implements Obstacle {
    private int height;

    public Wall(int height){
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean carryOutExercise(Participant... participants) {
        for (Participant e : participants) {
            if (e.canJump() >= height) {
                System.out.println(e + " перепрыгнул стену, высотой " + height + " м.");
            } else {
                System.out.println(e + " не перепрыгнул стену, высотой " + height + " м.");
            }
        }
        return true;
    }
}
