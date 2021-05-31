package org.jjd.lessons.lombok;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Climber {
    private String fullName;
    @NonNull
    private int age;
    @NonNull
    private String email;
    @NonNull
    private UUID uuid;
}