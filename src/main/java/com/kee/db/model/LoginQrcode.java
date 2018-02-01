package com.kee.db.model;


import java.util.Date;

public class LoginQrcode {

  private Long id;
  private String qrcode;
  private int state;
  private Date crateTime;
  private Date updateTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getQrcode() {
    return qrcode;
  }

  public void setQrcode(String qrcode) {
    this.qrcode = qrcode;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public Date getCrateTime() {
    return crateTime;
  }

  public void setCrateTime(Date crateTime) {
    this.crateTime = crateTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @Override
  public String toString() {
    return "LoginQrcode{" +
            "id=" + id +
            ", qrcode='" + qrcode + '\'' +
            ", state=" + state +
            ", crateTime=" + crateTime +
            ", updateTime=" + updateTime +
            '}';
  }
}
