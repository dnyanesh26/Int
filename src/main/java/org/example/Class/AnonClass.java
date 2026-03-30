package Class;

public class AnonClass {
    public static void main(String[] args) {
        // Anonymous class that implements the Greeting interface
        Greeting greet = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello, World!");
            }
        }; // Semicolon is required to end the statement

        greet.sayHello();

        // Normal class instance
        Animal myAnimal = new Animal();
        myAnimal.makeSound();

        // Anonymous class that extends Animal and overrides makeSound()
        Animal anotherAnimal = new Animal() {
            @Override
            public void makeSound() {
                System.out.println("Woof woof");
            }
        }; // Semicolon is required

        anotherAnimal.makeSound();

        Calculator addFunc = new Calculator() {
            public int add(int a, int b) {
                return a + b;
            }
        };

        System.out.println(addFunc.add(23, 76));
    }

}

interface Greeting {
    void sayHello();
}

class Animal {
    public void makeSound() {
        System.out.println("Animal sound");
    }
}

interface Calculator {
    public int add(int a, int b);

}