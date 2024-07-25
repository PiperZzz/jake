package spring.code.jake.myprojects.common.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Identifiable<UUID>, Auditable {
    @Id
    @GeneratedValue(generator = "UUID") // 分布式系统避免使用自增长ID，可能会导致冲突，但是UUID不是有序序列，会导致索引效率问题，最终解决方案还是Snowflake ID
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;
}
