package com.eshopping;
import java.util.Scanner;
public class Main {
    private static UserService userService = new UserService();
    private static ProductService productService = new ProductService();
    private static Cart cart = new Cart();
    private static User currentUser;

	public static void main(String[] args) {

		        Scanner scanner = new Scanner(System.in);
		        initialize();

		        while (true) {
		            if (currentUser == null) {
		                System.out.println("1. Register");
		                System.out.println("2. Login");
		                int choice = scanner.nextInt();
		                scanner.nextLine();  // Consume newline

		                if (choice == 1) {
		                    System.out.print("Enter username: ");
		                    String username = scanner.nextLine();
		                    System.out.print("Enter password: ");
		                    String password = scanner.nextLine();
		                    System.out.print("Are you an admin? (yes/no): ");
		                    boolean isAdmin = scanner.nextLine().equalsIgnoreCase("yes");
		                    userService.register(username, password, isAdmin);
		                    System.out.println("Registration successful!");
		                } else if (choice == 2) {
		                    System.out.print("Enter username: ");
		                    String username = scanner.nextLine();
		                    System.out.print("Enter password: ");
		                    String password = scanner.nextLine();
		                    currentUser = userService.login(username, password);
		                    if (currentUser == null) {
		                        System.out.println("Invalid credentials!");
		                    } else {
		                        System.out.println("Login successful!");
		                    }
		                }
		            } else {
		                if (currentUser.isAdmin()) {
		                    System.out.println("1. Add Product");
		                    System.out.println("2. Update Product");
		                    System.out.println("3. Delete Product");
		                    System.out.println("4. View All Products");
		                    System.out.println("5. Logout");
		                    int choice = scanner.nextInt();
		                    scanner.nextLine();  // Consume newline

		                    switch (choice) {
		                        case 1:
		                            System.out.print("Enter product name: ");
		                            String name = scanner.nextLine();
		                            System.out.print("Enter product price: ");
		                            double price = scanner.nextDouble();
		                            scanner.nextLine();  // Consume newline
		                            System.out.print("Enter product description: ");
		                            String description = scanner.nextLine();
		                            productService.addProduct(name, price, description);
		                            System.out.println("Product added successfully!");
		                            break;
		                        case 2:
		                            System.out.print("Enter product id to update: ");
		                            int idToUpdate = scanner.nextInt();
		                            scanner.nextLine();  // Consume newline
		                            System.out.print("Enter new product name: ");
		                            String newName = scanner.nextLine();
		                            System.out.print("Enter new product price: ");
		                            double newPrice = scanner.nextDouble();
		                            scanner.nextLine();  // Consume newline
		                            System.out.print("Enter new product description: ");
		                            String newDescription = scanner.nextLine();
		                            productService.updateProduct(idToUpdate, newName, newPrice, newDescription);
		                            System.out.println("Product updated successfully!");
		                            break;
		                        case 3:
		                            System.out.print("Enter product id to delete: ");
		                            int idToDelete = scanner.nextInt();
		                            scanner.nextLine();  // Consume newline
		                            productService.deleteProduct(idToDelete);
		                            System.out.println("Product deleted successfully!");
		                            break;
		                        case 4:
		                            System.out.println("All Products:");
		                            for (Product product : productService.getAllProducts()) {
		                                System.out.println(product.getId() + ". " + product.getName() + " - $" + product.getPrice() + " - " + product.getDescription());
		                            }
		                            break;
		                        case 5:
		                            currentUser = null;
		                            break;
		                        default:
		                            System.out.println("Invalid choice!");
		                    }
		                } else {
		                    System.out.println("1. View All Products");
		                    System.out.println("2. Add Product to Cart");
		                    System.out.println("3. View Cart");
		                    System.out.println("4. Remove Product from Cart");
		                    System.out.println("5. Logout");
		                    int choice = scanner.nextInt();
		                    scanner.nextLine();  // Consume newline

		                    switch (choice) {
		                        case 1:
		                            System.out.println("All Products:");
		                            for (Product product : productService.getAllProducts()) {
		                                System.out.println(product.getId() + ". " + product.getName() + " - $" + product.getPrice() + " - " + product.getDescription());
		                            }
		                            break;
		                        case 2:
		                            System.out.print("Enter product id to add to cart: ");
		                            int idToAdd = scanner.nextInt();
		                            scanner.nextLine();  // Consume newline
		                            System.out.print("Enter quantity: ");
		                            int quantity = scanner.nextInt();
		                            scanner.nextLine();  // Consume newline
		                            Product productToAdd = productService.getAllProducts().stream().filter(p -> p.getId() == idToAdd).findFirst().orElse(null);
		                            if (productToAdd != null) {
		                                cart.addProduct(productToAdd, quantity);
		                                System.out.println("Product added to cart successfully!");
		                            } else {
		                                System.out.println("Product not found!");
		                            }
		                            break;
		                        case 3:
		                            System.out.println("Cart Items:");
		                            cart.viewCart();
		                            break;
		                        case 4:
		                            System.out.print("Enter product id to remove from cart: ");
		                            int idToRemove = scanner.nextInt();
		                            scanner.nextLine();  // Consume newline
		                            Product productToRemove = productService.getAllProducts().stream().filter(p -> p.getId() == idToRemove).findFirst().orElse(null);
		                            if (productToRemove != null) {
		                                cart.removeProduct(productToRemove);
		                                System.out.println("Product removed from cart successfully!");
		                            } else {
		                                System.out.println("Product not found!");
		                            }
		                            break;
		                        case 5:
		                            currentUser = null;
		                            break;
		                        default:
		                            System.out.println("Invalid choice!");
		                    }
		                }
		            }
		        }
		    
	}
		    private static void initialize() {
		        // Initialize with some default users and products
		        userService.register("admin", "adminpass", true);
		        userService.register("user", "userpass", false);

		        productService.addProduct("Laptop", 999.99, "High performance laptop");
		        productService.addProduct("Smartphone", 499.99, "Latest model smartphone");
		        productService.addProduct("Headphones", 199.99, "Noise-cancelling headphones");

		        productService.addProduct("camera", 888.00, "High performance camera");
		        productService.addProduct("Watch", 559.00, "Latest model smartwatch");
		        productService.addProduct("Iphone", 299.99, "High security");
		    
		//System.out.println();
	}}



