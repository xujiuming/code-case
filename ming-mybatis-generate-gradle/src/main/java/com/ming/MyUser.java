package com.ming;
import java.util.Date;


/**脚本自动生成 entity
 *
 * @author ming
 * @date 2018-04-22 14:58:49
 */
public class MyUser{
    
  private Integer id;
  private String userName;
  private Date userDate;
  private Boolean userBoolean;

    

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Date getUserDate() {
    return userDate;
  }

  public void setUserDate(Date userDate) {
    this.userDate = userDate;
  }

  public Boolean getUserBoolean() {
    return userBoolean;
  }

  public void setUserBoolean(Boolean userBoolean) {
    this.userBoolean = userBoolean;
  }

}