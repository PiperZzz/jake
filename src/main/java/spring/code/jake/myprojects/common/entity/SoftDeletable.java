package spring.code.jake.myprojects.common.entity;

import java.time.Instant;

public interface SoftDeletable {
    boolean isDeleted();

    void setDeleted(boolean deleted);

    Instant getDeletedAt();

    void setDeletedAt(Instant deletedAt);
}
