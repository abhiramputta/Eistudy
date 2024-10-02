import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(float temperature);
}

class WeatherStation {
    private float temperature;
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

class PhoneDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Phone Display: Temperature updated to " + temperature + "°C");
    }
}

class LaptopDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Laptop Display: Temperature updated to " + temperature + "°C");
    }
}

public class weather {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        PhoneDisplay phone = new PhoneDisplay();
        LaptopDisplay laptop = new LaptopDisplay();

        station.addObserver(phone);
        station.addObserver(laptop);

        station.setTemperature(22.5f);
        station.setTemperature(25.0f);
    }
}
