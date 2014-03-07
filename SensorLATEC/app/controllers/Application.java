package controllers;

import javassist.tools.reflect.Sample;
import play.*;
import play.mvc.*;
import views.html.*;
import models.SamplesCollector;

public class Application extends Controller {

	static SamplesCollector collector;
	
    public static Result index() {
    	
    	collector = new SamplesCollector();
    	
    	try {
			collector.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        return ok(index.render("Your new application is ready."));
    }

}
