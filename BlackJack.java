import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.Collections;
import java.util.Stack;
import java.util.ArrayList;
import java.io.File;
import javax.swing.SwingConstants;
import java.awt.Color;
//import java.awt.Component;
import java.lang.Thread;
import java.lang.InterruptedException;

public class BlackJack extends JApplet
{
   private static Stack<Card> Deck = new Stack<Card>();
   private static ArrayList<Card> DealerHand = new ArrayList<Card>();
   private static ArrayList<Card> UserHand = new ArrayList<Card>();
   private static JButton Play = new JButton("Play!");
   private static JButton Hit = new JButton("Hit");
   private static JButton Stay = new JButton("Stay");
   private static JButton DoubleDown = new JButton("DoubleDown");
   private static JButton Bet = new JButton("Bet");
   private static JPanel userpanel = new JPanel();
   private static JPanel dealercardpanel = new JPanel();
   private static JPanel dealerpanel = new JPanel();
   private static JPanel usercardpanel = new JPanel();
   private static JPanel buttonpanel = new JPanel();
   private static JPanel board = new JPanel();
   private static JLabel message = new JLabel();
   private static JLabel dealermessage = new JLabel();
   private static JLabel count = new JLabel("Count = 00");
   private static JLabel moneyText = new JLabel("$1000");
   private static JLabel betText = new JLabel("Bet = ");
   private static int playerScore = 0;
   private static int dealerScore = 0;
   private static double bet = 0;
   private static String Sbet;
   private static double money = 1000;
   private static int runningCount;
   private static GridLayout g1 = new GridLayout(4,1);
   private static GridLayout g2 = new GridLayout(1,2);
   private static GridLayout g3 = new GridLayout(1,2);
   private static GridLayout g4 = new GridLayout(2,1);
   private static GridLayout g5 = new GridLayout(2,1);
   private static boolean stayed = false;
   
  
  
   
   public void init()
   {
      setSize(1000,600);
      ImageIcon main = new ImageIcon("title.png");
      ImageIcon sec = new ImageIcon("blackJ.png");
      JLabel titlePic = new JLabel(main);
      getContentPane().setBackground(Color.GREEN);
      add(titlePic,BorderLayout.CENTER);
      add(Play,BorderLayout.SOUTH);
      add(board,BorderLayout.EAST);
      setVisible(true);
      g1.setVgap(-10);
      g2.setHgap(-850);
      g3.setHgap(-850);
      g5.setVgap(-25);
      message.setHorizontalAlignment(SwingConstants.CENTER);
      dealermessage.setHorizontalAlignment(SwingConstants.CENTER);
      moneyText.setHorizontalAlignment(SwingConstants.CENTER);
      betText.setHorizontalAlignment(SwingConstants.CENTER);
      JTextField betfield = new JTextField("",5);
      betfield.setHorizontalAlignment(JTextField.RIGHT);
      
      class StayThread extends Thread
      {
         public StayThread()
         {
         }
         public void run()
         {
            if (dealerScore < 17)
            {
               while (dealerScore < 17)
               {
                  try
                  {
                     this.sleep(1000);
                  }
                  catch (InterruptedException ex)
                  {
                  }
               
                  Card temp2 = Deck.pop();
                  DealerHand.add(temp2);
                  JLabel tempLabel2 = new JLabel(temp2.getImage());
                  dealercardpanel.add(tempLabel2);
                  dealerScore += DealerHand.get(DealerHand.size()-1).getValue();
                  dealermessage.setText(""+ dealerScore);
                  if (dealerScore > 21)
                  {
                     for (int i = 0; i < DealerHand.size(); i++)  
                     {
                        if (DealerHand.get(i).getValue()==11)
                        {
                           DealerHand.get(i).setValue(1);
                           dealerScore -= 10;
                           dealermessage.setText(""+ dealerScore);
                           if (dealerScore >= 17)
                           {
                              if (playerScore > dealerScore)
                              {
                                 message.setText("You win!");
                              }
                              else if (playerScore < dealerScore)
                              {
                                 message.setText("You lose.");
                              }
                              else
                              {
                                 message.setText("Push");
                              }
                           }
                           break;
                        }
                        else
                        {
                           if (i == DealerHand.size()-1)
                           {
                              message.setText("Dealer Busted. You Win!");
                              validate();
                           }
                        }
                     }
                  }
                  else if (dealerScore >= 17)
                  {
                     if (playerScore > dealerScore)
                     {
                        message.setText("You win!");
                     }
                     else if (playerScore < dealerScore)
                     {
                        message.setText("You lose.");
                     }
                     else
                     {
                        message.setText("Push");
                     }
                  }
               }
            }
            else 
            {
               if (playerScore > dealerScore)
               {
                  message.setText("You win!");
               }
               else if (playerScore < dealerScore)
               {
                  message.setText("You lose.");
               }
               else
               {
                  message.setText("Push");
               }
            
            }
         }
      }
   
      
   
      class PlayButtonListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            //Pause(2000);
            CreateDeck();
            System.out.println(Deck.size());
            Collections.shuffle(Deck);
            remove(Play);
            remove(titlePic);
            getContentPane().setBackground(Color.GREEN);
            JLabel Cmain = new JLabel(sec);
            Cmain.setHorizontalAlignment(SwingConstants.CENTER);
            add(Cmain,BorderLayout.CENTER);
            userpanel.setLayout(g1);
            usercardpanel.setLayout(g2);
            dealercardpanel.setLayout(g3);
            dealerpanel.setLayout(g5);
            board.setLayout(g4);
            add(userpanel,BorderLayout.SOUTH);
            add(dealerpanel,BorderLayout.NORTH);
            //add(message,BorderLayout.CENTER);
            dealerpanel.add(dealercardpanel);
            dealerpanel.add(dealermessage);
            buttonpanel.add(Hit);
            buttonpanel.add(Stay);
            buttonpanel.add(DoubleDown);
            buttonpanel.add(betfield);
            buttonpanel.add(Bet);
            userpanel.add(message);
            userpanel.add(usercardpanel);
            userpanel.add(betText);
            board.add(count);
            board.add(moneyText);
            userpanel.add(buttonpanel);
            userpanel.setBackground(Color.GREEN);
            buttonpanel.setBackground(Color.GREEN);
            usercardpanel.setBackground(Color.GREEN);
            dealerpanel.setBackground(Color.GREEN);
            dealercardpanel.setBackground(Color.GREEN);
            validate();
             
         }
      }
      
      class BetButtonListener implements ActionListener  
      {
         public void actionPerformed(ActionEvent e)
         {
            dealermessage.setText("");
            Sbet=betfield.getText();
            bet = Double.parseDouble(Sbet);
            if (bet > money)
            {
               message.setText("Inadequet Funds Bet Again");
               bet = 0;
            }
            else
            {
               betText.setText("Bet = "+Sbet);
               Deal();
            }
         }
      }
   
      
      class HitButtonListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if (stayed == true)
            {
            }
            else if (playerScore == 21)
            {
               StayThread t1 = new StayThread();
               t1.start();
               stayed = true;
            }
            else
            {
               UserHand.add(Deck.pop());
               usercardpanel.add(new JLabel(UserHand.get(UserHand.size()-1).getImage()));
               playerScore += UserHand.get(UserHand.size()-1).getValue();
               message.setText("" + playerScore);
               if (playerScore > 21)
               {
                  for (int i = 0; i < UserHand.size(); i++)  
                  {
                     if (UserHand.get(i).getValue()==11)
                     {
                        UserHand.get(i).setValue(1);
                        playerScore -= 10;
                        message.setText("" + playerScore);
                        break;
                     }
                     else
                     {
                        if (i == UserHand.size()-1)
                        {
                           message.setText("Busted.");
                           validate();
                         
                        }
                     }
                  }
                  
               }
               validate();
            }
         }
      }
      
      class StayButtonListener implements ActionListener  
      {
         public void actionPerformed(ActionEvent e)
         {
            
            if (stayed == true)
            {
            }
            else
            {
               stayed = true;
               ImageIcon temp1 = DealerHand.get(1).getImage();
               dealercardpanel.remove(dealercardpanel.getComponent(1));
               JLabel tempLabel1 = new JLabel(temp1);
               dealercardpanel.add(tempLabel1);
               dealermessage.setText(""+ dealerScore);
               validate();
               StayThread t1 = new StayThread();
               t1.start();
               validate();
               
            }
            stayed = false;
         }
      }
      
      PlayButtonListener listener1 = new PlayButtonListener();
      Play.addActionListener(listener1);
      HitButtonListener listener2= new HitButtonListener();
      Hit.addActionListener(listener2);
      StayButtonListener listener3 = new StayButtonListener();
      Stay.addActionListener(listener3);
      BetButtonListener listener4= new BetButtonListener();
      Bet.addActionListener(listener4);
   }
   
   public void CreateDeck()
   {
      for (int j = 0; j < 6; j++)
      {
         for (int i = 2; i <= 10; i++)
         {
            Card temp1 = new Card(i + "c.jpg");
            Card temp2 = new Card(i + "d.jpg");
            Card temp3 = new Card(i + "h.jpg");
            Card temp4 = new Card(i + "s.jpg");
            Deck.push(temp1);
            Deck.push(temp2);
            Deck.push(temp3);
            Deck.push(temp4);
         }
      
         for (int i = 0; i < 4; i++)
         {
            String suit = "";
            if (i == 0)
               suit = "c.jpg";
            if (i == 1)
               suit = "d.jpg";
            if (i == 2)
               suit = "h.jpg";
            else
               suit = "s.jpg";
            
            Card tempj = new Card("j" + suit);
            Card tempq = new Card("q" + suit);
            Card tempk = new Card("k" + suit);
            Card tempa = new Card("a" + suit);
            Deck.push(tempj);
            Deck.push(tempq);
            Deck.push(tempk);
            Deck.push(tempa);
         }
      }
      
      //Create 6 decks
      /*for (int i = 0; i < 5; i++)
      {
         Stack<Card> temp = (Stack<Card>)Deck;
         for (int j = 0; j < 52; j++)
         {
            Card temp9 = (Card)temp.pop();
            Deck.push(temp9);
            System.out.println(temp9.getValue() + " ");
         }
         
      }*/
   }
   
   public void Deal()
   {
      playerScore = 0;
      dealerScore = 0;
      UserHand.clear();
      DealerHand.clear();
      usercardpanel.removeAll();
      dealercardpanel.removeAll();
      
      for (int i = 0; i < 2; i++)
      {
         UserHand.add(Deck.pop());
         DealerHand.add(Deck.pop());
         playerScore += UserHand.get(i).getValue();
         dealerScore += DealerHand.get(i).getValue();
      }
      
      if (dealerScore > 21)
      {
         for (int i = 0; i < DealerHand.size(); i++)  
         {
            if (DealerHand.get(i).getValue()==11)
            {
               DealerHand.get(i).setValue(1);
               dealerScore -= 10;
               break;
            }
         }
      }
      
      if (playerScore > 21)
      {
         for (int i = 0; i < UserHand.size(); i++)  
         {
            if (UserHand.get(i).getValue()==11)
            {
               UserHand.get(i).setValue(1);
               playerScore -= 10;
               break;
            }
         }
      }
   
      
      
      ImageIcon u1 = UserHand.get(0).getImage();
      ImageIcon u2 = UserHand.get(1).getImage();
      ImageIcon d1 = DealerHand.get(0).getImage();
      ImageIcon d2 = new ImageIcon("facedowncard.jpg");
      JLabel label = new JLabel(u1);
      JLabel label2 = new JLabel(u2);
      JLabel label3 = new JLabel(d1);
      JLabel label4 = new JLabel(d2);
      
      dealercardpanel.add(label3);
      dealercardpanel.add(label4);
      usercardpanel.add(label);
      usercardpanel.add(label2);
      message.setText("" + playerScore);
      
      if (playerScore==21)
      {
         message.setText("BlackJack! You Win!");
      }  
   }
   
   /*public synchronized void Pause(int time)
   {
      try 
      {
         wait(time);
      } 
      catch(InterruptedException ex)
      {
      }
   }*/
}