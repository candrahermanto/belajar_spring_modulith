package net.candra.belajar_spring_modulith.message;

import java.util.Date;

public record MessageEventPublisher(String emailAddress, Date createdDate) {
}
