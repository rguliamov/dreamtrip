package com.github.rguliamov.dreamtrip.app.model.entity.base;

import com.github.rguliamov.dreamtrip.app.model.entity.person.Account;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Guliamov Rustam
 *
 * Base class for all buisness entities
 */
@MappedSuperclass
public class AbstractEntity {

    /**
     * Unique entity identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    /**
     * Timestamp of entity creation
     */
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp of last modification
     */
    @Column(name = "MODIFIED_AT", insertable = false)
    private LocalDateTime modifiedAt;

    /**
     * Person who created specify entity
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = "CREATED_BY", updatable = false)
    private Account createdBy;

    /**
     * Person who last modified specify entity
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = "MODIFIED_BY", insertable = false)
    private Account modifiedBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    public Account getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Account modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
