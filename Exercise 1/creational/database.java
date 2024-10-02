class DatabaseConnectionPool {
    private static DatabaseConnectionPool instance;

    private DatabaseConnectionPool() {
        // private constructor
    }

    public static DatabaseConnectionPool getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionPool();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Connected to the database.");
    }
}

public class database{
    public static void main(String[] args) {
        DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();
        pool.connect();
    }
}
