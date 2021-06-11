/**
 * Momin Chaudhry
 * 500831162
 * April 13, 2018
 * CPS209
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ConveyerBelt extends JComponent {
  
  LinkGroceryItem first, last, pickedUpItem;
  
  /* Constructor for ConveyerBelt object, adding mouse listener to drag item and displays the graphics */
  public ConveyerBelt() { 
    addMouseListener();
    repaint();
  }
  
  /* addMouseListener holds an inner class that implements MouseListener to drag and drop items, and inner class for mouse motion to update the position in real time */
  public void addMouseListener() {
    class MousePressListener implements MouseListener {
      public void mousePressed(MouseEvent event) {
        if (pickedUpItem != null) {
          int x = event.getX();
          int y = event.getY();
          pickedUpItem.setLocation(x, y);
          repaint();
        }
      }
      public void mouseReleased(MouseEvent event) {
        if (event.getX() >= 10 && event.getX() <= 700 && event.getY() >= 150 && event.getY() <= 1200 && pickedUpItem != null && numItems() < 4) {
          addItem();
        }
        repaint();
      }
      public void mouseClicked(MouseEvent event){}
      public void mouseEntered(MouseEvent event){}
      public void mouseExited(MouseEvent event){}
    }
    MouseListener mListener = new MousePressListener();
    addMouseListener(mListener);
    
    class MouseMotion implements MouseMotionListener
    {
      public void mouseDragged(MouseEvent event)
      {
        if (pickedUpItem != null) {
          int x = event.getX();
          int y = event.getY();
          pickedUpItem.setLocation(x, y);
          repaint();
        }
      }
      public void mouseMoved(MouseEvent event){ }
    }
    MouseMotionListener motionListener = new MouseMotion();
    addMouseMotionListener(motionListener);
    repaint();
  }
  
  /* paintComponent displays the picked up item and calls draw method to draw items to conveyer belt
   * @param Graphics g is used to display graphics
   */
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    if (pickedUpItem != null) {
      g.drawRect((int)pickedUpItem.box.getX(), (int)pickedUpItem.box.getY(),80,50);
      String label = pickedUpItem.gitem.getLabel();
      g.drawString(label, (int)pickedUpItem.box.getX() + 10, (int)pickedUpItem.box.getY() + 20);
    }
    if (first != null) {
      first.setLocation (50, 170);
      first.draw(g2);
    }
    g.fillRect(0, 250, 600, 20);
  }
  
  /* numItems gives the number of items on the conveyer belt
   * @return int the number of items on the belt
   */
  public int numItems () {
    int size = 0;
    LinkGroceryItem i = first;
    if (i != null) {
      while(i.next!=null) {
        i = i.next;
        size++;
      }
    }
    return size;
  }
  
  /* addItem adds the picked up item to the belt, if there is an item picked up */
  public void addItem () {
    LinkGroceryItem f = new LinkGroceryItem(pickedUpItem.gitem, first);
    first = f;
    pickedUpItem = null;
    repaint();
  }
  
  /* removeItem removes the last item from the belt, if there is any left, and returns it
   * @return GroceryItem the item removed, null if there isnt any left
   */
  public GroceryItem removeItem () {
    LinkGroceryItem pos = first, prev = first;
    if (first == null) return null;
    else if (first.next == null) {
      GroceryItem b = first.gitem;
      first = null;
      return b;
    } else {
      LinkGroceryItem i = first;
      while (pos.next != null) {
        prev = pos;
        pos = pos.next;
      }
      prev.next = null;
      return pos.gitem;
    }
  }
  
  /* setPickedUpItem sets the picked up item to a LinkGroceryNode containing the given grocery item
   * @param GroceryItem item, the data for pickedUpItem
   */
  public void setPickedUpItem (GroceryItem item) {
    if (pickedUpItem == null) {
      pickedUpItem = new LinkGroceryItem(item, null);
      repaint();
    } 
  }
  
  /* nullItem checks if picked up item is null
   * @return true if it is null, false otherwise
   */
  public Boolean nullItem () { if (pickedUpItem == null) return true; return false; }
  
  /* Inner class LinkGroceryItem, a node of GroceryItem for a custom linked list in conveyer belt */
  class LinkGroceryItem {
    GroceryItem gitem;
    LinkGroceryItem next;
    Rectangle box;
    
    /* constructor for LinkGroceryItem objects, initializes the data of the node and the next node
     * @param GroceryItem item is the data for this node
     * @param LinkGroceryItem n is the next node after this
     */
    public LinkGroceryItem (GroceryItem item, LinkGroceryItem n) {
      gitem = item;
      next = n;
      box = new Rectangle(90, 0, 5, -5);
    }
    
    /* setLocation sets the location of the box of this item
     * @param int x is the x coordinate of the left of the box
     * @param int y is the y coordinate of the top of the box
     */
    public void setLocation (int x, int y) {
      box.setLocation(x, y);
    }
    
    /* intersects checks if this LinkGroceryItem intersects another
     * @param LinkGroceryItem item is the other item
     * @return boolean true if it intersects, false if it doesn't
     */
    public boolean intersects (LinkGroceryItem item) {
      if (item == null) return false;
      return this.box.intersects(item.box);
    }
    
    /* draw draws the graphics of the items on the conveyer belt starting from first, and following the links for each item following
     * @param Graphics2D g2 is used to display the graphics
     */
    public void draw (Graphics2D g2) {
      if (gitem != null) {
        g2.drawRect(box.x, box.y, 80, 50);
        String label = gitem.getLabel();
        g2.drawString(label, box.x+10, box.y+20);
        if (next!=null) {
          next.setLocation(box.x+85, box.y);
          next.draw(g2);
        }
      }
    }
    
  }
  
  
}
