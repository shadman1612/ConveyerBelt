import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;

import javax.swing.JTextArea;

public class NutritionScanner 
{
  NutritionChart chart;
  public NutritionScanner () throws IOException {
  chart = new NutritionChart();
  }
  
  ArrayList<FoodType> scannedItems = new ArrayList<FoodType>();
  
  public ArrayList<FoodType> getScannedFoodTypeList()
  {
    return scannedItems;
  }
  
  public void scanFoodCode(int foodCode)
  {
    FoodType food = null;
    
    for (int i = 0; i < scannedItems.size(); i++)
    {
      food = scannedItems.get(i);
      if (food.code == foodCode)
      {
        food.measure++;
        return;
      }
    }
    food = chart.getFoodType(foodCode);
    
    if (food != null)
      scannedItems.add(food);
  }
  
  public void displayAll(JTextArea displayArea)
  {
    displayArea.setText("");
    
    for (int i = 0; i < scannedItems.size(); i++)
    {
      displayArea.append(scannedItems.get(i).display() + "\n");
    }
  }
  
  public void clear()
  {
    scannedItems = new ArrayList<FoodType>();
  }
  
  
  public void sortByCalories()
  {
    Collections.sort(scannedItems);
  }
  
  private class FoodTypeCarbsComparator implements Comparator<FoodType>
  {
    public int compare(FoodType a, FoodType b)
    {
      if (a.measure*a.carbs < b.measure*b.carbs)      return 1;
      else if (a.measure*a.carbs > b.measure*b.carbs) return -1;
      else return 0;    
    }
  }
  
  public void sortByCarbs()
  {
    Collections.sort(scannedItems, new FoodTypeCarbsComparator());
  }
  
  
  private class FoodTypeFatComparator implements Comparator<FoodType>
  {
    public int compare(FoodType a, FoodType b)
    {
      if (a.measure*a.fat < b.measure*b.fat)      return 1;
      else if (a.measure*a.fat > b.measure*b.fat) return -1;
      else return 0;    
    }
  }
  public void sortByFat()
  {
    Collections.sort(scannedItems,new FoodTypeFatComparator());
  }
  
  
  private class FoodTypeSugarComparator implements Comparator<FoodType>
  {
    public int compare(FoodType a, FoodType b)
    {
      if (a.measure*a.sugar < b.measure*b.sugar)      return 1;
      else if (a.measure*a.sugar > b.measure*b.sugar) return -1;
      else return 0;    
    }
  }
  public void sortBySugar()
  {
    Collections.sort(scannedItems,new FoodTypeSugarComparator());
  }
}


