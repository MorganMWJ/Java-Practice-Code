package util;

import java.util.Observer;
import javax.swing.JFrame;


public abstract class View extends JFrame implements Observer {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
    * This registers the view with the model.
    * When any changes are made
    * in the model, the view will be updated
    */
   protected void registerWithModel(Model model) {
      System.out.println("Make the view an Observer of the model so when changes to the model are made the view can be updated"); 	
      model.addObserver(this);
   }  
   
}
