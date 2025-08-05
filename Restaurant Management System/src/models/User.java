package models;

public abstract class User {
  protected String username;
  protected String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}