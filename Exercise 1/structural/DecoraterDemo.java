interface Coffee {
    String getDescription();
    double cost();
}

class BasicCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Basic Coffee";
    }

    @Override
    public double cost() {
        return 5.0;
    }
}

// Decorator base class
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double cost() {
        return coffee.cost();
    }
}

// Concrete decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return coffee.cost() + 1.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double cost() {
        return coffee.cost() + 0.5;
    }
}

public class DecoraterDemo {
    public static void main(String[] args) {
        Coffee basicCoffee = new BasicCoffee();
        Coffee milkCoffee = new MilkDecorator(basicCoffee);
        Coffee sugarMilkCoffee = new SugarDecorator(milkCoffee);

        System.out.println(sugarMilkCoffee.getDescription() + " costs $" + sugarMilkCoffee.cost());
    }
}
