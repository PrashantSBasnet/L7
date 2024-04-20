public class BookListing {
    private String title;
    private double price;
    private int quantity; // New field to track the quantity of the book listing

    public BookListing(String title, double price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTitle() {
        return title;
    }
}
