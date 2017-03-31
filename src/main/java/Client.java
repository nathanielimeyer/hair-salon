import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private String phone_number;
  private LocalDateTime client_since;

  public Client(String name, String phone_number) {
    this.name = name;
    this.phone_number = phone_number;
    this.client_since = LocalDateTime.now();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phone_number;
  }

  public LocalDateTime getClientSince() {
    return client_since;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, phone_number, client_since) VALUES (:name, :phone_number, :client_since)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("phone_number", this.phone_number)
        .addParameter("client_since", java.sql.Timestamp.valueOf(this.client_since))
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getId() == newClient.getId() && this.getName().equals(newClient.getName()) && this.getPhoneNumber().equals(newClient.getPhoneNumber())
      // && this.getClientSince().equals(newClient.getClientSince())
      ;
    }
  }

  public static Client findById(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }
}
