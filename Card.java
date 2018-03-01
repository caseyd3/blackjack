import javax.swing.ImageIcon;

public class Card
{
   private ImageIcon image;
   private int value;
   
   public Card(String FileName)
   {
      image = new ImageIcon(FileName);
      value = determineValue(FileName);
   }
   
   public int getValue()
   {
      return value;
   }
   
   public void setValue(int a)
   {
      value = a;
   }
   
   public ImageIcon getImage()
   {
      return image;
   }
   
   private int determineValue(String s)
   {
      if (s.charAt(0)=='1' && s.charAt(1)=='0')
      {
         return 10;
      }
      else
      {
         char c = s.charAt(0);
         switch (c)
         {
            case 'a': 
               return 11; 
            case 'j': case 'q': case 'k': 
               return 10; 
            default: 
               return Character.getNumericValue(c); 
         }
      }
   }
}