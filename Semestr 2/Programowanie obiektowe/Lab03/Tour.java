import java.util.*;

public class Tour {
	
	Map<String, String> countries_cities = new TreeMap<String,String>();
	List<String> cities = new ArrayList<String>();
	Set<String> unique_cities = new HashSet<String>();
	
	public Tour() {
		countries_cities.put("Polska","Warszawa");
		countries_cities.put("Niemcy", "Berlin");
		countries_cities.put("Szwecja", "Sztokholm");
		
		cities.add("Berlin");
		cities.add("Warszawa");
		cities.add("Paryż");
		cities.add("Paryż");
		
		unique_cities.add("Berlin");
		unique_cities.add("Warszawa");
		unique_cities.add("Paryż");
		unique_cities.add("Paryż");
	}
	
	public void print_countries_cities() {
		System.out.println("Elementy kolekcji countries_cities: " + countries_cities);
		System.out.println("Czy kolekcja zawiera Szwecję? " + countries_cities.containsKey("Szwecja"));
		System.out.println("Liczba elementów kolekcji: " + countries_cities.size());
	}
	
	public void print_cities() {
		System.out.println("Elementy kolekcji cities: " + cities);
		System.out.println("Czy kolekcja zawiera Paryż? " + cities.contains("Paryż"));
		System.out.println("Liczba elementów kolekcji: " + cities.size());
	}
	
	public void print_unique_cities() {
		System.out.println("Elementy kolekcji countries_cities: " + unique_cities);
		System.out.println("Czy kolekcja zawiera Rzym? " + unique_cities.contains("Rzym"));
		System.out.println("Liczba elementów kolekcji: " + unique_cities.size());
	}
	
	public static void main(String[] args) {
		Tour tour = new Tour();
		tour.print_countries_cities();
		tour.print_cities();
		tour.print_unique_cities();
	}
}