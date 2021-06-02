package org.jjd.lessons.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_messages") /* + информацию по индексам и связям
 с другими сущностями */
public class Message {
    private int id;

    @Column(name = "message_text",
//            length = 1000,
//            nullable = false,
            unique = true,
            columnDefinition="VARCHAR(100) NOT NULL")
    private String text;

    private LocalDateTime sent;


}
