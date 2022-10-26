import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageObservable {

    private ArrayList<LanguageObserver> observers;
    private ResourceBundle language;

    public LanguageObservable(LanguageObserver firstObserver){
        observers = new ArrayList<>();
        observers.add(firstObserver);
        language = ResourceBundle.getBundle("language.Labels", Locale.getDefault());
    }

    public void attachObserver(LanguageObserver observer){
        observers.add(observer);
    }

    public void detachObserver(LanguageObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (LanguageObserver observer : observers){
            if (observer != null) observer.update(language);
        }
    }

    public ResourceBundle getLanguage() {
        return language;
    }

    public void setLanguage(ResourceBundle language) {
        this.language = language;
        notifyObservers();
    }
}
