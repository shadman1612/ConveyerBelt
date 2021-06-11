import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JTextArea;

public class GroceryCart 
{
        private Stack<GroceryItem> items;
 int currentIndex;
        private ListIterator<GroceryItem> iterator;
 
 public GroceryCart()
 {
  items = new Stack<GroceryItem>();
  currentIndex = -1;
 }
 
 public GroceryItem removeTopItem()
 {
  if (items.size() > 0)
  {
    currentIndex--;
    if (currentIndex < 0) currentIndex = 0;
    return items.pop();
  }
  return null;
 }
 
 public void startViewing()
 {
  currentIndex = 0;
 }
 
 public GroceryItem viewNextItem()
 {
  GroceryItem next = null;
  
  if (currentIndex >= 0 && currentIndex < items.size())
  {
    next = items.get(currentIndex);
    currentIndex++;
  }
  return next;
 }
 
 public void addItem(GroceryItem item)
 {
    items.push(item);
 }
 
 public void display(JTextArea displayArea)
 {
   displayArea.setText("");
   
   for (int i = 0; i < items.size(); i++)
   {
     displayArea.append(items.get(i).getLabel() + "\n\n");
   }
 }
 
 public void clear()
 {
  items = new Stack<GroceryItem>();
  currentIndex = -1;
 }
 
 public void fill() throws IOException {
    Scanner sc = new Scanner(new File("groceryItems.txt"));
    if (!sc.hasNextLine()) throw new IOException();
    boolean hasType = false;
    while (sc.hasNextLine()) {
      int foodCode = 0;
      int amount = 0;
      double price = 0.0;
      String item;
      String label = "";
      Scanner itm = new Scanner(sc.nextLine());
      if (itm.hasNextInt()) foodCode = itm.nextInt();
      while(itm.hasNext() && !itm.hasNextDouble()) {
        String word = itm.next();
        if(word.equals("Dairy")) {
          word = "";
          while (itm.hasNext() && !itm.hasNextDouble()) {
            label += (word + " ");
          }
          price = itm.nextDouble();
          amount = itm.nextInt();
          addItem(new Dairy(foodCode, label, price, amount));
          break;
        } else if (word.equals("Meat")) {
          word = "";
          while (itm.hasNext() && !itm.hasNextDouble()) {
            label += (word + " ");
          }
          price = itm.nextDouble();
          amount = itm.nextInt();
          addItem(new Meat(foodCode, label, price, amount));
          break;
        } else {
          label += (word + " ");
        }
      }
      price = itm.nextDouble();
      addItem(new GroceryItem(foodCode, label, price));
    }
    iterator = items.listIterator();
  }
 }

