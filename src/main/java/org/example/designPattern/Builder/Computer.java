package org.example.designPattern.Builder;

public class Computer {
    // Mandatory parameters
    private final String CPU;
    private final String RAM;
    // Optional parameters
    private final boolean hasSSD;
    private final String graphicsCard;

    // Private constructor that takes the Builder as a parameter
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.hasSSD = builder.hasSSD;
        this.graphicsCard = builder.graphicsCard;
    }

    // Static nested Builder class
    public static class Builder {
        // Mandatory parameters (copied from outer class, but can be non-final here)
        private final String CPU;
        private final String RAM;
        // Optional parameters (with default values if desired)
        private boolean hasSSD = false;
        private String graphicsCard = "Basic Integrated";

        // Public constructor for required parameters
        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }

        // Setter methods for optional parameters, returning the Builder itself (method chaining)
        public Builder hasSSD(boolean hasSSD) {
            this.hasSSD = hasSSD;
            return this;
        }

        public Builder graphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        // The "build" method returns the final immutable Computer object
        public Computer build() {
            return new Computer(this);
        }
    }

    // Getters only, making the object immutable after creation
    public String getCPU() { return CPU; }
    public String getRAM() { return RAM; }
    public boolean hasSSD() { return hasSSD; }
    public String getGraphicsCard() { return graphicsCard; }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", hasSSD=" + hasSSD + ", graphicsCard=" + graphicsCard + "]";
    }
}

