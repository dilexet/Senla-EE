package eu.senla.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "send_date", nullable = false, columnDefinition = "DATE")
    private LocalDate sendDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @Override
    public int hashCode() {
        final long prime = 31L;
        long result = 1L;
        result = prime * result + this.getId();
        return (int) result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (this == obj)
            return true;
        Message entity = (Message) obj;
        return this.getId().equals(entity.getId());
    }
}
