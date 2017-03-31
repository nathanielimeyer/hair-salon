import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class StylistTest {

  @Before public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteStylistsQuery = "DELETE FROM stylists WHERE ID <> 1;";
      con.createQuery(deleteStylistsQuery).executeUpdate();
    }
  }

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    assertTrue(myStylist instanceof Stylist);
  }

  @Test
  public void Stylist_instantiatesWithAnId_true() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    myStylist.save();
    Stylist savedStylist = Stylist.all().get(1);
    assertTrue(myStylist.getId() > 1);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

  @Test
  public void getName_returnsName_Kelsi() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    assertEquals("Kelsi", myStylist.getName());
  }

  @Test
  public void getPhoneNumber_returnsPhoneNumber_4155551313() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    assertEquals("415-555-1313", myStylist.getPhoneNumber());
  }

  @Test
  public void worksWeeknights_returnsWeeknights_true() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    assertTrue(myStylist.worksWeeknights());
  }

  @Test
  public void worksWeekdays_returnsWeekdays_true() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    assertTrue(myStylist.worksWeekdays());
  }

  @Test
  public void worksWeekends_returnsWeekends_true() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    assertFalse(myStylist.worksWeekends());
  }

  @Test
  public void all_returnsAllInstancesOfStylist_true() {
    Stylist firstStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    firstStylist.save();
    Stylist secondStylist = new Stylist("Mysti", "415-555-4444", false, false, true);
    secondStylist.save();
    assertTrue(Stylist.all().get(1).equals(firstStylist));
    assertTrue(Stylist.all().get(2).equals(secondStylist));
  }

  @Test
  public void clear_emptiesAllStylistsFromArrayList_0() {
    assertEquals(1, Stylist.all().size());
  }

  @Test
  public void findById_returnsStylistWithSameId_secondStylist() {
    Stylist firstStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    firstStylist.save();
    Stylist secondStylist = new Stylist("Mysti", "415-555-4444", false, false, true);
    secondStylist.save();
    assertEquals(Stylist.findById(secondStylist.getId()), secondStylist);
  }

  @Test
  public void getClients_initiallyReturnsEmptyList_ArrayList() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    myStylist.save();
    assertEquals(0, myStylist.getClients().size());
  }

  @Test
  public void addClient_addsClientToList_true() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    myStylist.save();
    Client myClient = new Client("Lila", "415-555-1212", myStylist.getId());
    myClient.save();
    assertTrue(myStylist.getClients().contains(myClient));
  }

  @Test
  public void delete_movesClientsAndDeletesStylist_true() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    myStylist.save();
    Client myClient = new Client("Lila", "415-555-1212", myStylist.getId());
    myClient.save();
    assertTrue(myStylist.getClients().contains(myClient));
    int myStylistId = myStylist.getId();
    assertEquals(Stylist.findById(myStylistId), myStylist);
    myStylist.delete();
    myClient = Client.findById(myClient.getId());
    assertEquals(null, Stylist.findById(myStylistId));
    assertEquals(1, myClient.getStylistId());
  }

  @Test
  public void setNameAndUpdate_setsNameAndUpdatesDB_Bobbi() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    myStylist.save();
    myStylist.setName("Bobbi");
    myStylist.update();
    Stylist savedStylist = Stylist.findById(myStylist.getId());
    assertEquals("Bobbi", savedStylist.getName());
  }

  @Test
  public void setPhoneNumberAndUpdate_setsPhoneNumberAndUpdatesDB_5034441414() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    myStylist.save();
    myStylist.setPhoneNumber("503-444-1414");
    myStylist.update();
    Stylist savedStylist = Stylist.findById(myStylist.getId());
    assertEquals("503-444-1414", savedStylist.getPhoneNumber());
  }

  @Test
  public void setWeeknightsAndUpdate_setsWeeknightsAndUpdatesDB_false() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    myStylist.save();
    myStylist.setWeeknights(false);
    myStylist.update();
    Stylist savedStylist = Stylist.findById(myStylist.getId());
    assertFalse(savedStylist.worksWeeknights());
  }

  @Test
  public void setWeekdaysAndUpdate_setsWeekdaysAndUpdatesDB_false() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    myStylist.save();
    myStylist.setWeekdays(false);
    myStylist.update();
    Stylist savedStylist = Stylist.findById(myStylist.getId());
    assertFalse(savedStylist.worksWeekdays());
  }

  @Test
  public void setWeekendsAndUpdate_setsWeekendsAndUpdatesDB_false() {
    Stylist myStylist = new Stylist("Kelsi", "415-555-1313", true, true, false);
    myStylist.save();
    myStylist.setWeekends(true);
    myStylist.update();
    Stylist savedStylist = Stylist.findById(myStylist.getId());
    assertTrue(savedStylist.worksWeekends());
  }
}
