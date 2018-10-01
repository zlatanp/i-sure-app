package korenski.model.autorizacija;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jasmina on 21/11/2017.
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class Base {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Date created;

    @Column
    private Date updated;

    @Column(nullable = false)
    private boolean active = true;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

   
}
