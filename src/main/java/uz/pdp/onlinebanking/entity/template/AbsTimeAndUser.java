package uz.pdp.onlinebanking.entity.template;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.sql.Timestamp;
import java.util.UUID;

public class AbsTimeAndUser {
    @CreatedBy
    private UUID id;
    @LastModifiedBy
    private UUID lastModified;
    @CreatedDate
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
