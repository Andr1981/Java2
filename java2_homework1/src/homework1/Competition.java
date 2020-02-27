package homework1;

public class Competition {
    private Participant[] participants;
    private Obstacle[] obstacles;


    public void setObstacles(Obstacle... obstacles) {
        this.obstacles = obstacles;
    }

    public void setParticipants(Participant... participants) {
        this.participants = participants;
    }

    public void startCompetition() {

        for (Participant participant : participants) {
            boolean success = carryOutObstacle(participant);
            if (!success) {
                System.out.println("Участник " + participant + " покинул испытание");
            } else {
                System.out.println("Участник " + participant + " прошел испытание");
            }
        }
    }
    private boolean carryOutObstacle(Participant participant) {
        for (Obstacle obstacle : obstacles) {
            if ( !obstacle.carryOutExercise(participant) ) {
                return false;
            }
            System.out.println();
        }
        return true;
    }
}
