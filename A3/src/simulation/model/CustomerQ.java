package simulation.model;

public class CustomerQ {

    private int maxLength;
    private int totalCustomers;

    private LList4Q<Customer> customers;

    public CustomerQ() {
        customers = new LList4Q<>();
    }

    public void joinQ(int time) {
        Customer newCustomer = new Customer(time);
        customers.add(newCustomer);

        totalCustomers++;
        if (customers.size() > maxLength);
            maxLength = customers.size();
    }

    public Customer leaveQ() {
        return customers.removeNext();
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public int length() {
        return customers.size();
    }
}
