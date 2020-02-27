package homework1;

public class RunTrack implements Obstacle {
    private int distance;

    public RunTrack(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean carryOutExercise(Participant... participants) {
        for (Participant e : participants) {
            if (e.canRun() >= distance) {
                System.out.println(e + " пробежал дистанцию, длинной  " + distance + " м.");
                return true;
            } else {
                System.out.println(e + " не пробежал дистанцию, длинной  " + distance + " м.");
                return false;
            }
        } return false;
    }
}

