package org.example.designPattern.Builder;

public class Client {
    public static void main(String[] args) {
        // Create a basic computer with mandatory parts
        Computer basicComputer = new Computer.Builder("Intel i3", "8GB").build();
        System.out.println(basicComputer);

        // Create a gaming computer with optional parts using method chaining
        Computer gamingComputer = new Computer.Builder("Intel i7", "32GB")
                .hasSSD(true)
                .graphicsCard("Nvidia RTX 4080")
                .build();
        System.out.println(gamingComputer);
    }
}
