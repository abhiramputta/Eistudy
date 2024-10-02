// Legacy payment system
class LegacyPaymentGateway {
    public void processPayment(String account, float amount) {
        System.out.println("Processing payment of " + amount + " for account " + account);
    }
}

// Modern payment interface
interface ModernPaymentGateway {
    void makePayment(String account, float amount);
}

// Adapter
class PaymentAdapter implements ModernPaymentGateway {
    private LegacyPaymentGateway legacyGateway;

    public PaymentAdapter(LegacyPaymentGateway legacyGateway) {
        this.legacyGateway = legacyGateway;
    }

    @Override
    public void makePayment(String account, float amount) {
        legacyGateway.processPayment(account, amount);
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        LegacyPaymentGateway legacyGateway = new LegacyPaymentGateway();
        ModernPaymentGateway modernGateway = new PaymentAdapter(legacyGateway);

        modernGateway.makePayment("12345", 500);
    }
}
