package org.jjd.lessons.lombok;


import lombok.*;

import java.util.HashSet;

@ToString(exclude = "climbers") // {"climbers", "otherField"}
@EqualsAndHashCode(exclude = "climbers") // {"climbers", "otherField"}
public class Hill {
    @Getter /* генерация геттера int getHeight()  */
    @Setter /* генерация сеттера void setHeight(int height)  */
    private int height;

    @Getter
    private final HashSet<Climber> climbers = new HashSet<>();

    // если @NonNull аннотировано поле, то проверка добавляется в сеттер
    @SneakyThrows
    public void walk(@NonNull Climber climber){
        // if (climber == null) throw new NullPointerException();
        if (climber.getAge() < 18) throw new Exception("Исключение по возрасту");
        climbers.add(climber);
    }
}
