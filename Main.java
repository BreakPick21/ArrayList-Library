import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
	private String name;
    private int year;
    private int pages;
    private String subject;
    private double rating;

    public Main(String name, int year, int pages, String subject, double rating) {
        this.name = name;
        this.year = year;
        this.pages = pages;
        this.subject = subject;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public String getSubject() {
        return subject;
    }

    public double getRating() {
        return rating;
    }

    public String toString() {
        return "Name: " + name + ", Year: " + year + ", Pages: " + pages + ", Subject: " + subject + ", Rating: " + rating;
    }

    public static ArrayList<Main> books = new ArrayList<>();
    public static Random rand = new Random();
    public static Scanner input = new Scanner(System.in);
    public static Scanner scanner = new Scanner(System.in);
    
    public static String[] subjects = {"Programming", "Data Structures", "Algorithms", "Operating Systems", "Gaming"};
    
    public static ArrayList<Main> createBooks() {

        // list of unique years
        ArrayList<Integer> years = new ArrayList<>();
        for (int i = 1980; i <= 2019; i++) {
            years.add(i);
        }

        // list of unique subjects
        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("Programming");
        subjects.add("Data Structures");
        subjects.add("Algorithms");
        subjects.add("Operating Systems");
        subjects.add("Gaming");

        for (int i = 1; i <= 20; i++) {
            int yearIndex = rand.nextInt(years.size());
            int year = years.get(yearIndex);
            years.remove(yearIndex);

            int subjectIndex = rand.nextInt(subjects.size());
            String subject = subjects.get(subjectIndex);

            // if all 5 books for a subject have been added, remove the subject from the list
            if (countSubject(books, subject) == 4) {
                subjects.remove(subjectIndex);
            }

            int pages = rand.nextInt(951) + 50;
            double rating = Math.round((rand.nextDouble() * (10.0 - 0.1) + 0.1) * 10.0) / 10.0;

            books.add(new Main("Book" + i, year, pages, subject, rating));
        }

        return books;
    }

    // method to count the number of books with a specific subject
    public static int countSubject(ArrayList<Main> books, String subject) {
        int count = 0;
        for (Main book : books) {
            if (book.getSubject().equals(subject)) {
                count++;
            }
        }
        return count;
    }
    
    public static void listAllBooks() {
        for (Main book : books) {
            System.out.println(book);
        }
    }

    public static void sortBooksByYear() {
        Collections.sort(books, (a, b) -> a.getYear() - b.getYear());
        listAllBooks();
    }

    public static void sortBooksByPages() {
        Collections.sort(books, (a, b) -> a.getPages() - b.getPages());
        listAllBooks();
    }

    public static void sortBooksByRating() {
        Collections.sort(books, (a, b) -> Double.compare(b.getRating(), a.getRating()));
        listAllBooks();
    }
    
    public static void searchSubject() {
    	System.out.print("Enter subject: ");
    	String subject = input.nextLine();
    	for (Main booker : books) {
    		if (booker.getSubject().equals(subject)) {
    			System.out.println(booker);
    		}
    	}
    }
    public static void searchBook() {
    	System.out.print("Enter book name: ");
    	String name = input.nextLine();
    	boolean found = false;
    	for (Main booker : books) {
    		if (booker.getName().equals(name)) {
    			System.out.println(booker);
    			found = true; break;
    		}
    	}
    }
    
    public static void addBook() {
    	System.out.println("Enter the book name:");
    	String name = input.nextLine();
    	System.out.println("Enter the year of publication:");
    	int year = input.nextInt();
    	System.out.println("Enter the number of pages:");
    	int pages = input.nextInt();
    	input.nextLine();
    	System.out.println("Enter the subject:");
    	String subject = input.nextLine();
    	System.out.println("Enter the review rating:");
    	double rating = input.nextDouble();
    	books.add(new Main(name, year, pages, subject, rating));
    	}
    
    public static void menu() {
    	int choice = 0;
    	while (choice != 8) {
    		System.out.println("1. List all the books");
    		System.out.println("2. Display the books sorted according to year of publication, starting with the oldest one");
    		System.out.println("3. Sort the books according to length in pages, starting with the shortest");
    		System.out.println("4. Sort the books according to review ratings, starting with the highest rating");
    		System.out.println("5. Ask user for a subject, and display all the books belonging to that specific subject");
    		System.out.println("6. Search for a specific book by name, and display all the details if the book exists");
    		System.out.println("7. Add a book to the list of books (ask the user for all the details)");
    		System.out.println("8. Exit");
    		System.out.print("Enter your choice: ");
    		choice = scanner.nextInt();
    		switch (choice) {
            	case 1:
            		listAllBooks();
            		break;
            	case 2:
            		sortBooksByYear();
            		break;
            	case 3:
            		sortBooksByPages();
            		break;
            	case 4:
            		sortBooksByRating();
            		break;
            	case 5:
            		searchSubject();
            		break;
            	case 6:
            		searchBook();
            		break;
            	case 7:
            		addBook();
            		break;
            	case 8:
            		break;
            	default:
            		System.out.println("Invalid choice");
    		}
    	}
    }
    
    public static void main(String[] args) {
    	createBooks();
        menu();
    	}
	}
