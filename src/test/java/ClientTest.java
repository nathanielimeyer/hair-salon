import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class ClientTest {

  @Before public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteClientsQuery = "DELETE FROM clients *;";
      con.createQuery(deleteClientsQuery).executeUpdate();
    }
  }

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client myClient = new Client("Lila", "415-555-1212", 3);
    assertTrue(myClient instanceof Client);
  }

  @Test
  public void getId_clientsInstantiateWithAnId_true() {
    Client myClient = new Client("Lila", "415-555-1212", 3);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertTrue(myClient.getId() > 0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void getName_returnsName_Lila() {
    Client myClient = new Client("Lila", "415-555-1212", 3);
    assertEquals("Lila", myClient.getName());
  }

  @Test
  public void getPhoneNumber_returnsPhoneNumber_4155551212() {
    Client myClient = new Client("Lila", "415-555-1212", 3);
    assertEquals("415-555-1212", myClient.getPhoneNumber());
  }

  @Test
  public void getClientSince_returnsDate_today() {
    Client myClient = new Client("Lila", "415-555-1212", 3);
    assertEquals(LocalDateTime.now().getDayOfWeek(), myClient.getClientSince().getDayOfWeek());
  }

  @Test
  public void getStylistId_returnsStylistId_3() {
    Client myClient = new Client("Lila", "415-555-1212", 3);
    assertEquals(3, myClient.getStylistId());
  }

  @Test public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Lila", "415-555-1212", 3);
    firstClient.save();
    Client secondClient = new Client("Ilana", "415-555-1313", 4);
    secondClient.save();
    assertTrue(Client.all().get(0).equals(firstClient));
    assertTrue(Client.all().get(1).equals(secondClient));
  }

  @Test
  public void clear_emptiesAllClientsFromArrayList_0() {
    assertEquals(0, Client.all().size());
  }

  @Test
  public void findById_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("Lila", "415-555-1212", 3);
    firstClient.save();
    Client secondClient = new Client("Ilana", "415-555-1313", 4);
    secondClient.save();
    assertEquals(Client.findById(secondClient.getId()), secondClient);
  }

  @Test
  public void setNameAndUpdate_setsNameAndUpdatesDB_Ilana() {
    Client myClient = new Client("Lila", "415-555-1212", 3);
    myClient.save();
    myClient.setName("Ilana");
    myClient.update();
    Client savedClient = Client.findById(myClient.getId());
    assertEquals("Ilana", savedClient.getName());
  }

  @Test
  public void setPhoneNumberAndUpdate_setsPhoneNumberAndUpdatesDB_5037654321() {
    Client myClient = new Client("Lila", "415-555-1212", 3);
    myClient.save();
    myClient.setPhoneNumber("503-765-4321");
    myClient.update();
    Client savedClient = Client.findById(myClient.getId());
    assertEquals("503-765-4321", savedClient.getPhoneNumber());
  }

  @Test
  public void setStylistIdAndUpdate_setsNameAndUpdatesDB_4() {
    Client myClient = new Client("Lila", "415-555-1212", 3);
    myClient.save();
    myClient.setStylistId(4);
    myClient.update();
    Client savedClient = Client.findById(myClient.getId());
    assertEquals(4, savedClient.getStylistId());
  }
}
