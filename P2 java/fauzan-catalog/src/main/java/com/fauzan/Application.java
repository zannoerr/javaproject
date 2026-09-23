package com.fauzan;

public class Application {

    public static void main(String[] args) {
        ProductRepository repo = new ProductRepository();

        repo.addProduct(new product("PRD-01", "Keyboard Mechanical", 450000.0));
        repo.addProduct(new product("PRD-02", "Mouse Wireless", 175000.0));

        System.out.println("=== DAFTAR SELURUH PRODUK ===");
        repo.findAll().forEach(System.out::println);

        System.out.println("\n=== PENCARIAN PRODUK ===");
        try {
            product p = repo.findById("PRD-99"); 
            System.out.println("Ditemukan: " + p);
        } catch (ProductNotFoundException e) {
            System.out.println("Error Terjadi: " + e.getMessage());
        }
    }
}
