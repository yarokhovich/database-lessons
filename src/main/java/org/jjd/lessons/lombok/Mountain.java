package org.jjd.lessons.lombok;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor // final @NonNull
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Mountain extends Hill{
    @Getter
    private final String name;
}