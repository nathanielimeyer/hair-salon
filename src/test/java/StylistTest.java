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
      String deleteStylistsQuery = "DELETE FROM stylists *;";
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
    Stylist savedStylist = Stylist.all().get(0);
    assertTrue(myStylist.getId() > 0);
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
    assertTrue(Stylist.all().get(0).equals(firstStylist));
    assertTrue(Stylist.all().get(1).equals(secondStylist));
  }

  @Test
  public void clear_emptiesAllStylistsFromArrayList_0() {
    assertEquals(0, Stylist.all().size());
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

}
