import java.util.*;

public class SellerAgent {

    private String name;
    private Map<String, List<BookListing>> catalog;
    private Map<String, Integer> bookQuantities;

    public SellerAgent(String name) {
        this.name = name;
        this.catalog = new HashMap<>();
        this.bookQuantities = new HashMap<>();
    }

    public Map<String, List<BookListing>> getCatalog() {
        return catalog;
    }

    public Map<String, Integer> getBookQuantities() {
        return bookQuantities;
    }

    public void addBook(String title, double price, int quantity) {
        catalog.putIfAbsent(title, new ArrayList<>());
        List<BookListing> listings = catalog.get(title);
        for (int i = 0; i < quantity; i++) {
            listings.add(new BookListing(title, price, quantity));
        }
        bookQuantities.put(title, quantity);
    }

    public double provideOffer(String targetBook) {
        List<BookListing> listings = catalog.get(targetBook);
        if (listings != null && !listings.isEmpty()) {
            double lowestPrice = Double.MAX_VALUE;
            for (BookListing listing : listings) {
                if (listing.getPrice() < lowestPrice) {
                    lowestPrice = listing.getPrice();
                }
            }
            return lowestPrice;
        }
        return -1.0; // Return -1 if book not found
    }

    public void removeBook(String title) {
        List<BookListing> listings = catalog.get(title);
        if (listings != null && !listings.isEmpty()) {
            listings.remove(0); // Remove the first occurrence
            int quantity = bookQuantities.get(title);
            quantity--;
            bookQuantities.put(title, quantity);
            if (listings.isEmpty()) {
                catalog.remove(title); // Remove the entry from the catalog if there are no more copies
                bookQuantities.remove(title); // Also remove from bookQuantities
            }
        } else {
            System.out.println("Book not available");
        }
    }

    public void servePurchase(String bookTitle) {
        removeBook(bookTitle);
    }

    public String getName() {
        return name;
    }
}
