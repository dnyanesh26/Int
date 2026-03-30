package org.example.designPattern.AbstractFactory;

interface Chair { void sitOn(); }
interface Sofa { void lieOn(); }

class ModernChair implements Chair{

    @Override
    public void sitOn() {
        System.out.println("Sit on Modern Chair");
    }
}

class VictorianChair implements Chair{

    @Override
    public void sitOn() {
        System.out.println("Sit on Victorian Chair");
    }
}

class ModernSofa implements Sofa{

    @Override
    public void lieOn() {
        System.out.println("Lie on Modern Sofa");
    }
}

class VictorianSofa implements Sofa{

    @Override
    public void lieOn() {
        System.out.println("Lie on Victorian Sofa");
    }
}

interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
}
class ModernFurnitureFactory implements FurnitureFactory {
    public Chair createChair() { return new ModernChair(); }
    public Sofa createSofa() { return new ModernSofa(); }
}

class VictorianFurnitureFactory implements FurnitureFactory {
    public Chair createChair() { return new VictorianChair(); }
    public Sofa createSofa() { return new VictorianSofa(); }
}

class FurnitureShop {
    private final FurnitureFactory factory;

    // The factory is INJECTED here
    public FurnitureShop(FurnitureFactory factory) {
        this.factory = factory;
    }

    public void showcase() {
        Chair chair = factory.createChair();
        Sofa sofa = factory.createSofa();
        System.out.println("Displaying a matching set!");
        chair.sitOn();
        sofa.lieOn();
    }
}

public class MainFurnitureApp {
    public static void main(String[] args) {
        // Step 1: Choose the concrete factory (The "Decision")
        FurnitureFactory modernFactory = new ModernFurnitureFactory();

        // Step 2: Inject it into the client
        FurnitureShop shop = new FurnitureShop(modernFactory);

        // Step 3: Run the app
        shop.showcase();
    }
}

