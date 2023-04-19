package uz.pdp.onlinebanking.entity.template;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;
@MappedSuperclass
public abstract class AbsUUIDEntity extends AbsTimeAndUser {
    @Id
    @GeneratedValue
    public static UUID id;
    @Column(nullable = false)
    private String name;
    private boolean active;
}
