package com.example.liquorhub.entity;

import com.example.liquorhub.security.WebUtils;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IS_ACTIVE", columnDefinition = "TINYINT(1) default 1 not null")
    private Boolean isActive;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;

    @Column(name = "UPDATED_AT", nullable = false)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Date.from(Instant.now());
        this.updatedAt = Date.from(Instant.now());
        this.isActive = Boolean.TRUE;
        this.createdBy = WebUtils.getCurrentUser();
        this.updatedBy = WebUtils.getCurrentUser();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Date.from(Instant.now());
        this.updatedBy = WebUtils.getCurrentUser();
    }

}
