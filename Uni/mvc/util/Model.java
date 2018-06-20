package util;
import java.util.Observable;

public abstract class Model extends Observable {

   public void notifyChanged() {
    notifyChanged(null);
   }
   
   public void notifyChanged(Object arg){
      System.out.println("Model has changed.  Notify registered observers");
      setChanged();
      notifyObservers(arg);
   }   
}
