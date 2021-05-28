package org.jjd.lessons.lombok;


import java.util.HashSet;

public class Hill {
    private int height;
    private final HashSet<Climber> climbers = new HashSet<>();

    public void walk(Climber climber){
        if (climber.getAge() < 18) throw new Exception("Исключение по возрасту");
        climbers.add(climber);
    }
}
