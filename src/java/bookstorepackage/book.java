/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstorepackage;

/**
 *
 * @author thaku
 */
public class book {
    protected int id;
    protected String title, author;
    protected float price;
    
    public book(){}
    
    public book(int id){
        this.id = id;
    }
    
    public book(int id, String title, String author, float price){
        this(title, author, price);
        this.id = id;
        //this.title = title;
        //this.author = author;
        //this.price = price;
    }
    
    public book(String title, String author, float price){
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setPrice(float price) {this.price = price;}

    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public float getPrice() {return price;}
    
}
