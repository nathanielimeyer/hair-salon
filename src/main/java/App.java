import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      Stylist noStylist = Stylist.findById(1);
      model.put("unassignedClients", noStylist.getClients());
      model.put("clients", Client.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Stylist stylist = Stylist.findById(Integer.parseInt(request.params(":id")));
        model.put("stylist", stylist);
        model.put("clients", stylist.getClients());
        model.put("template", "templates/stylist.vtl");
        return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/:id", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Client client = Client.findById(Integer.parseInt(request.params(":id")));
        Stylist stylist = Stylist.findById(client.getStylistId());
        model.put("client", client);
        model.put("stylist", stylist);
        model.put("template", "templates/client.vtl");
        return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String phone_number = request.queryParams("phone_number");
      boolean weeknights = Boolean.parseBoolean(request.queryParams("weeknights"));
      boolean weekdays = Boolean.parseBoolean(request.queryParams("weekdays"));
      boolean weekends = Boolean.parseBoolean(request.queryParams("weekends"));
      Stylist newStylist = new Stylist(name, phone_number, weeknights, weekdays, weekends);
      newStylist.save();
      model.put("template", "templates/stylist-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        // Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
        String name = request.queryParams("name");
        String phone_number = request.queryParams("phone_number");
        int stylist_id = Integer.parseInt(request.queryParams("stylist_id"));
        Client newClient = new Client(name, phone_number, stylist_id);
        newClient.save();
        model.put("template", "templates/stylist-clients-success.vtl");
        return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



    // get("/categories/new", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("template", "templates/category-form.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/categories", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("categories", Category.all());
    //   model.put("template", "templates/categories.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/categories/:id1", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Category category = Category.find(Integer.parseInt(request.params(":id1")));
    //   model.put("category", category);
    //   model.put("template", "templates/category.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("categories/:id1/tasks/new", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Category category = Category.find(Integer.parseInt(request.params(":id1")));
    //   model.put("category", category);
    //   model.put("template", "templates/category-tasks-form.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("categories/:id1/tasks/:id2", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Category category = Category.find(Integer.parseInt(request.params(":id1")));
    //   Task task = Task.find(Integer.parseInt(request.params(":id2")));
    //   model.put("category", category);
    //   model.put("task", task);
    //   model.put("template", "templates/task.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/tasks", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("tasks", Task.all());
    //   model.put("template", "templates/tasks.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //

  }
}
