package com.example.warehouse.entity;

import com.example.warehouse.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "users")
public class User {

    @GeneratedValue(strategy =GenerationType.UUID)
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    private UserRole userrole;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_at")
    private Instant lastModifiedAt;

}
