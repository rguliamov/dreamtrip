package com.github.rguliamov.dreamtrip.app.model.entity.person;

import com.github.rguliamov.dreamtrip.app.model.entity.base.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author Guliamov Rustam
 *
 * User of the application
 */
@Entity
@Table(name = "ACCOUNTS")
public class Account extends AbstractEntity {
}
