package org.example.designPattern.Singleton;

import java.util.Properties;


/*
Why this is the "Best" Approach
Resource Efficiency:
Database connections are expensive;
sharing one instance (or pool) avoids the overhead of repeatedly establishing new ones.

Consistency:
Centralizing configurations ensures that if a setting (like a password) changes,
it reflects across the entire application immediately.

Safety:
It prevents "connection leaks" where different modules might open their own connections and fail to close them.

JVM Guarantees:
The JVM ensures the instance is created exactly once and handles all thread-safety and
serialization automatically.
 */
public enum ConfigManager {
    INSTANCE;

    private final Properties settings = new Properties();

    private ConfigManager() {
        // Load settings once from a file or environment variables
        settings.setProperty("api.timeout", "5000");
        settings.setProperty("app.version", "1.0.2");
    }

    public String getSetting(String key) {
        return settings.getProperty(key);
    }
}
