package com.pinwu.auth.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Bin Jia
 * @date 2020/9/6 0:34
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer id;

  @Column(name = "name")
  private String username;

  @Column(name = "avatar_url")
  private String avatarUrl;

  @Column(name = "phone")
  private String phone;

  @Column(name = "password")
  private String password;

  @CreatedDate
  @Column(name = "created_at")
  private Date createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private Date updatedAt;


}
