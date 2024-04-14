import java.util.*;

public class Main {
    static SellerAgent newSeller = null; // Initialize newSeller as null

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // List to hold all seller agents
        List<SellerAgent> sellers = new ArrayList<>();

        // Adding initial seller agents
        SellerAgent seller1 = new SellerAgent("Seller1");
        seller1.addBook("Book1", 20.0);
        seller1.addBook("Book2", 15.0);
        sellers.add(seller1);

        SellerAgent seller2 = new SellerAgent("Seller2");
        seller2.addBook("Book1", 18.0);
        seller2.addBook("Book3", 25.0);
        sellers.add(seller2);

        SellerAgent seller3 = new SellerAgent("Seller3");
        seller3.addBook("Book2", 18.0);
        seller3.addBook("Book3", 20.0);
        sellers.add(seller3);

        // Check if the user wants to create a new seller
        System.out.println("Do You Want to create a New Seller and Add books? (Y/N)");
        String condition = scanner.nextLine();
        if (condition.equalsIgnoreCase("Y")) {
            // Adding a new seller agent via user input
            System.out.println("Adding a new seller agent:");
            System.out.println("Enter the name of the seller:");
            String sellerName = scanner.nextLine();

            // Create the new seller agent
            newSeller = new SellerAgent(sellerName);

            // Adding books to the catalog for the new seller
            while (true) {
                System.out.println("Enter the title of the book (or 'done' to finish adding books):");
                String title = scanner.nextLine();
                if (title.equals("done")) {
                    break;
                }
                System.out.println("Enter the price of the book:");
                double price = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                newSeller.addBook(title, price);
            }

            // Display book details for the new seller
            System.out.println("Books added for the new seller:");
            displaySellerDetails(newSeller);
            sellers.add(newSeller); // Add the new seller to the list of sellers
        }

        // Display details of all existing sellers
        System.out.println("Details of existing sellers:");
        for (SellerAgent seller : sellers) {
            displaySellerDetails(seller);
        }

        // Creating buyer agents
        BuyerAgent buyer1 = new BuyerAgent("Buyer1", "Book1");
        BuyerAgent buyer2 = new BuyerAgent("Buyer2", "Book2");
        BuyerAgent buyer3 = new BuyerAgent("Buyer3", "Book3");
        BuyerAgent buyer4 = new BuyerAgent("Buyer4", "randombook");

        // Request offers from seller agents for each buyer agent
        for (BuyerAgent buyer : Arrays.asList(buyer1, buyer2, buyer3, buyer4)) {
            buyer.requestOffers(sellers);
        }

        // Make purchases
        for (BuyerAgent buyer : Arrays.asList(buyer1, buyer2, buyer3, buyer4)) {
            buyer.makePurchase();
        }
    }

    // Method to display seller details
    private static void displaySellerDetails(SellerAgent seller) {
        System.out.println("Seller: " + seller.getName());
        Map<String, List<BookListing>> catalog = seller.getCatalog();
        for (Map.Entry<String, List<BookListing>> entry : catalog.entrySet()) {
            System.out.println("Book: " + entry.getKey());
            for (BookListing listing : entry.getValue()) {
                System.out.println("Price: " + listing.getPrice());
            }
        }
        System.out.println();
    }
}
