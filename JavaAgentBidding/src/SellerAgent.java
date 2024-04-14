import java.util.*;

public class SellerAgent {
private String name;

    public Map<String, List<BookListing>> getCatalog() {
        return catalog;
    }

    private Map<String, List<BookListing>> catalog;

public SellerAgent(String name) {
    this.name = name;
    this.catalog = new HashMap<>();
}

// Add a new book listing to the catalog
public void addBook(String title, double price) {
    catalog.putIfAbsent(title, new ArrayList<>());
    catalog.get(title).add(new BookListing(title, price));
}

// Provide an offer for a specific book
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

    // Serve a purchase order and remove the requested book from the catalog
    public boolean servePurchase(String requestedBook) {
        List<BookListing> listings = catalog.get(requestedBook);
        if (listings != null && !listings.isEmpty()) {
            catalog.remove(requestedBook);
            return true; // Book served and removed successfully
        }
        return false; // Book not found in the catalog
    }

    public void addBookViaUI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the book:");
        String title = scanner.nextLine();
        System.out.println("Enter the price of the book:");
        double price = scanner.nextDouble();
        addBook(title, price);
        System.out.println("Book '" + title + "' added to the catalog.");
    }

public String getName() {
    return name;
}
}


