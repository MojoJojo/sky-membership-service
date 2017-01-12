package com.sky.membership.reporting.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MemberAccount {

    @Id
    Long id;
    String bouquet;
    String name;

    @Enumerated(EnumType.STRING)
    AccountStatus status;

    public enum AccountStatus {
        ACTIVE,
        CANCELLED
    }
}
