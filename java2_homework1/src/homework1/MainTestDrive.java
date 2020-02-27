package homework1;

public class MainTestDrive {

    public static void main(String[] args) {

        Human hum1 = new Human("Александр");
        Cat cat1 = new Cat("Мурзик");
        Robot rob1 = new Robot("Java");
        Wall wall1 = new Wall(3);
        RunTrack runTrack1 = new RunTrack(300);
        runTrack1.carryOutExercise(hum1, cat1, rob1);
        wall1.carryOutExercise(hum1, cat1, rob1);
        System.out.println();
        System.out.println("Начинаем соревнование!\n");
        Competition competition = new Competition();
        competition.setObstacles(runTrack1, wall1);
        competition.setParticipants(hum1,cat1,rob1);
        competition.startCompetition();


    }
}
