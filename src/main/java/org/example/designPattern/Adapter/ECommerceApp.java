package org.example.designPattern.Adapter;

// 1. Target Interface: The interface the client expects
// What our E-commerce app expects
interface PaymentProcessor {
    void makePayment(double amountInUSD);
}


// 2. Adaptee: The existing class with an incompatible interface
// Third-party SDK (cannot change this code)
class StripeApi {
    public void executeCharge(int amountInCents) {
        System.out.println("Paid " + amountInCents + " cents via Stripe.");
    }
}


// 3. Adapter: Implements the Target Interface and uses the Adaptee
class StripeAdapter implements PaymentProcessor {
    private StripeApi stripeApi;

    public StripeAdapter(StripeApi stripeApi) {
        this.stripeApi = stripeApi;
    }

    @Override
    public void makePayment(double amountInUSD) {
        // 1. Convert dollars to cents (Translation logic)
        int cents = (int) (amountInUSD * 100);

        // 2. Map to the incompatible method
        stripeApi.executeCharge(cents);
    }
}


// 4. Client: The code that uses the Target Interface
public class ECommerceApp {
    public static void main(String[] args) {
        // We have a Stripe API instance
        StripeApi externalService = new StripeApi();

        // We wrap it in our Adapter
        PaymentProcessor processor = new StripeAdapter(externalService);

        // The app continues to work with its usual interface
        processor.makePayment(49.99);
    }
}


