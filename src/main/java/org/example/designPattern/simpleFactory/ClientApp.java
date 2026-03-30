package org.example.designPattern.simpleFactory;

public class ClientApp {
    public static void main(String[] args) {
        // The client doesn't use the 'new' keyword for concrete classes
        Shape circle = ShapeFactory.getShape("Circle");
        circle.draw();
    }
}