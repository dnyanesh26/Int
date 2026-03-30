package org.example.designPattern.Observer;

import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;

// 1. Concrete Observer (Subscriber)
class NewsSubscriber implements Subscriber<String> {
    private final String name;
    private Subscription subscription;

    public NewsSubscriber(String name) { this.name = name; }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        // Request 1 update to start (Backpressure control)
        this.subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        System.out.println(name + " received news: " + item);
        // Request the next update
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) { throwable.printStackTrace(); }

    @Override
    public void onComplete() { System.out.println(name + ": All news received."); }
}

// 2. Usage (Subject and Observers)
public class ObserverFlowExample {
    public static void main(String[] args) throws InterruptedException {
        // Concrete Subject using SubmissionPublisher
        try (SubmissionPublisher<String> publisher = new SubmissionPublisher<>()) {

            // Create and register observers
            NewsSubscriber sub1 = new NewsSubscriber("Subscriber A");
            NewsSubscriber sub2 = new NewsSubscriber("Subscriber B");

            publisher.subscribe(sub1);
            publisher.subscribe(sub2);

            // Notify Observers of state changes
            System.out.println("Publishing news...");
            publisher.submit("Java Flow API is powerful!");
            // Brief wait for async delivery

            publisher.submit("Observer pattern is now reactive!");



            // Brief wait for async delivery
            Thread.sleep(2500);
        }
    }
}
