import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String name;
  private String phone_number;
  private boolean weeknights;
  private boolean weekdays;
  private boolean weekends;


  public Stylist(String name, String phone_number, boolean weeknights, boolean weekdays, boolean weekends) {
    this.name = name;
    this.phone_number = phone_number;
    this.weeknights = weeknights;
    this.weekdays = weekdays;
    this.weekends = weekends;
  }

  public int getId(){
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phone_number;
  }

  public void setPhoneNumber(String phone_number) {
    this.phone_number = phone_number;
  }

  public boolean worksWeeknights() {
    return weeknights;
  }

  public void setWeeknights(boolean weeknights) {
    this.weeknights = weeknights;
  }

  public boolean worksWeekdays() {
    return weekdays;
  }

  public void setWeekdays(boolean weekdays) {
    this.weekdays = weekdays;
  }

  public boolean worksWeekends() {
    return weekends;
  }

  public void setWeekends(boolean weekends) {
    this.weekends = weekends;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(name, phone_number, weeknights, weekdays, weekends) VALUES (:name, :phone_number, :weeknights, :weekdays, :weekends)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("phone_number", this.phone_number)
        .addParameter("weeknights", this.weeknights)
        .addParameter("weekdays", this.weekdays)
        .addParameter("weekends", this.weekends)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Stylist> all() {
    String sql = "SELECT * FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getId() == newStylist.getId() &&
      this.getName().equals(newStylist.getName()) &&
      this.getPhoneNumber().equals(newStylist.getPhoneNumber()) &&
      this.worksWeeknights() == newStylist.worksWeeknights() &&
      this.worksWeekdays() == newStylist.worksWeekdays() &&
      this.worksWeekends() == newStylist.worksWeekends();
    }
  }

  public static Stylist findById(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id = :id";
      return con.createQuery(sql).addParameter("id", this.id).executeAndFetch(Client.class);
    }
  }

   public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql1 = "UPDATE clients SET stylist_id = 1 WHERE stylist_id=:id";
      con.createQuery(sql1)
      .addParameter("id", id)
      .executeUpdate();

      String sql2 = "DELETE FROM stylists WHERE id=:id";
      con.createQuery(sql2)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name=:name, phone_number=:phone_number, weeknights=:weeknights, weekdays=:weekdays, weekends=:weekends WHERE id=:id";
      con.createQuery(sql)
      .addParameter("name", this.name)
      .addParameter("phone_number", this.phone_number)
      .addParameter("weeknights", this.weeknights)
      .addParameter("weekdays", this.weekdays)
      .addParameter("weekends", this.weekends)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }
}
