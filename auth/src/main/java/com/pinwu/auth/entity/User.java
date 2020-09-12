package com.pinwu.auth.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("用户实体类")
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  @ApiModelProperty("用户id")
  private Integer id;

  @Column(name = "name")
  @ApiModelProperty("用户名")
  private String username;

  @Column(name = "avatar_url")
  @ApiModelProperty("用户头像")
  private String avatarUrl;

  @Column(name = "phone")
  @ApiModelProperty("用户手机号")
  private String phone;

  @Column(name = "password")
  @ApiModelProperty("用户密码")
  private String password;

  @CreatedDate
  @Column(name = "created_at")
  @ApiModelProperty(value = "创建时间", required = false)
  private Date createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  @ApiModelProperty(value = "更新时间", required = false)
  private Date updatedAt;

  @Column(name = "email")
  @ApiModelProperty("邮箱")
  private String email;

  @Column(name = "verification_code")
  @ApiModelProperty("邮箱验证码")
  private String verificationCode;


}
