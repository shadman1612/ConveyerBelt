import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class NutritionChart 
{
  TreeMap<Integer, FoodType> foods; 
  
  public NutritionChart() throws FileNotFoundException
  {
    Scanner scanner = new Scanner(new File("nutrition.txt"));
    int foodCode = 0;
    int calories = 0;
    int fat = 0;
    int sugar = 0;
    int carbs = 0;
    String label = "";
    String line = "";
    while(scanner.hasNextLine()) {
      line = scanner.nextLine();
      Scanner lineSc = new Scanner(line);
      if (lineSc.hasNextInt()) foodCode = lineSc.nextInt();
      if (lineSc.hasNext()) label = lineSc.next();
      if (lineSc.hasNextInt()) calories = lineSc.nextInt();
      if (lineSc.hasNextInt()) sugar = lineSc.nextInt();
      if (lineSc.hasNextInt()) fat = lineSc.nextInt();
      if (lineSc.hasNextInt()) carbs = lineSc.nextInt();
      FoodType type = new FoodType(foodCode, label, calories, sugar, fat, carbs);
      foods.put(foodCode, type);
    }
  }
  
  public FoodType getFoodType(int foodCode)
  {
    if (foods.containsKey(new Integer(foodCode))) {
      return foods.get(new Integer(foodCode));
    } else {
      return null;
    }
  }
}
