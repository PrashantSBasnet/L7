import java.util.*;

public class Main {
    static SellerAgent newSeller = null; // Initialize newSeller as null

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // List to hold all seller agents
        List<SellerAgent> sellers = new ArrayList<>();

        // Adding initial seller agents
        SellerAgent seller1 = new SellerAgent("Seller1");
        seller1.addBook("Book1", 20.0, 1); // Specify quantity
        seller1.addBook("Book2", 15.0, 2); // Specify quantity
        sellers.add(seller1);

        SellerAgent seller2 = new SellerAgent("Seller2");
        seller2.addBook("Book1", 18.0, 1); // Specify quantity
        seller2.addBook("Book3", 25.0, 2); // Specify quantity
        sellers.add(seller2);

        SellerAgent seller3 = new SellerAgent("Seller3");
        seller3.addBook("Book2", 18.0, 1); // Specify quantity
        seller3.addBook("Book3", 20.0, 5); // Specify quantity
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
                System.out.println("Enter the quantity of the book:");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                newSeller.addBook(title, price, quantity); // Add book with quantity
            }

            // Display book details for the new seller
            System.out.println("Books added for the new seller:");
            displaySellerDetails(newSeller);
            sellers.add(newSeller); // Add the new seller to the list of sellers
        }

        // Display details of all existing sellers
        // Check if the user wants to create a new seller
        System.out.println("Do You Want to view details of the sellers with their books?(Y/N)");
        String condition2 = scanner.nextLine();

        if (condition2.equalsIgnoreCase("Y")) {

            System.out.println("Details of existing sellers:");

            for (SellerAgent seller : sellers) {
                displaySellerDetails(seller);
            }
        }

        System.out.println("Do You Want to initiate bidding purchase?(Y/N)");
        String condition3 = scanner.nextLine();

        if (condition3.equalsIgnoreCase("Y")) {

            // Creating buyer agents
            BuyerAgent buyer1 = new BuyerAgent("Buyer1", "Book1");
            BuyerAgent buyer2 = new BuyerAgent("Buyer2", "Book2");
            BuyerAgent buyer3 = new BuyerAgent("Buyer3", "Book3");
            BuyerAgent buyer4 = new BuyerAgent("Buyer4", "Book2");
            BuyerAgent buyer5 = new BuyerAgent("Buyer5", "Book2");
            BuyerAgent buyer6 = new BuyerAgent("Buyer6", "randomBook");

            System.out.println("Buyer Details:");
            System.out.println("--------------------------------------------------");
            System.out.printf("| %-10s | %-10s |\n", "Buyer Name", "Targeted Book");
            System.out.println("--------------------------------------------------");
            System.out.printf("| %-10s | %-10s |\n", buyer1.getName(), buyer1.getTargetBook());
            System.out.printf("| %-10s | %-10s |\n", buyer2.getName(), buyer2.getTargetBook());
            System.out.printf("| %-10s | %-10s |\n", buyer3.getName(), buyer3.getTargetBook());
            System.out.printf("| %-10s | %-10s |\n", buyer4.getName(), buyer4.getTargetBook());
            System.out.printf("| %-10s | %-10s |\n", buyer5.getName(), buyer5.getTargetBook());
            System.out.printf("| %-10s | %-10s |\n", buyer6.getName(), buyer6.getTargetBook());
            System.out.println("--------------------------------------------------");


            System.out.println("Purchase Details:");
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-15s | %-15s | %-15s |\n", "Buyer Name", "Made Purchase", "Selected Price", "Targeted Book", "Selected Seller");
            System.out.println("------------------------------------------------------------------------------------------------------");
            for (BuyerAgent buyer : Arrays.asList(buyer1, buyer2, buyer3, buyer4, buyer5, buyer6)) {
                buyer.requestOffers(sellers);
                buyer.makePurchase(); // Make a purchase after requesting offers
                String selectedSeller = (buyer.getSelectedSeller() != null) ? buyer.getSelectedSeller().getName() : "None";
                System.out.printf("| %-10s | %-20s | %-15s | %-15s | %-15s |\n",
                        buyer.getName(),
                        buyer.hasMadePurchase(),
                        buyer.hasMadePurchase() ? buyer.getSelectedPrice() : "N/A",
                        buyer.getTargetBook(),
                        selectedSeller);
            }
            System.out.println("------------------------------------------------------------------------------------------------------");


        }
    }


    // Method to display seller details
    // Method to display seller details
    // Method to display seller details
    // Method to display seller details
    private static void displaySellerDetails(SellerAgent seller) {
        System.out.println("Seller: " + seller.getName());
        Map<String, List<BookListing>> catalog = seller.getCatalog();
        System.out.println("--------------------------------------------------");
        System.out.printf("| %-15s | %-10s | %-10s |\n", "Book", "Price", "Quantity");
        System.out.println("--------------------------------------------------");
        for (Map.Entry<String, List<BookListing>> entry : catalog.entrySet()) {
            String bookTitle = entry.getKey();
            List<BookListing> bookListings = entry.getValue();
            System.out.printf("| %-15s | %-10s | %-10s |\n", bookTitle, bookListings.get(0).getPrice(), bookListings.get(0).getQuantity());
        }
        System.out.println("--------------------------------------------------");
        System.out.println();
    }




}
