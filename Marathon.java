


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RiaGupta
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Marathon {
    public static JPanel p_name;
    public static JLabel l_name;
    public static JTextField tf_name;
    public static JTextField tf_cash;
    public static JPanel p_second;
    public static JPanel p_open_first;
    public static JPanel p_open_sec;
    public static JPanel p_gdr_first;
    public static JPanel p_gdr_sec;
    public static JTextField tf_name_01;
  public static JTextField tf_name_02;
  public static JTextField tf_name_11;
  public static JTextField tf_name_12;
  public static JTextField tf_name_21;
  public static JTextField tf_name_22;

  
    public static JPanel p_main_one;
    public static JPanel p_name_one;
    public static JLabel l_name_one;
    public static JTextField tf_name_one;
    public static JPanel p_time;
    public static JLabel l_time;
    public static JTextField tf_time;
//    
    public static void main(String [] args){
        LinkedList L=new LinkedList();
        JFrame frame=new JFrame("Marathon");
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p_main_one = new JPanel();
        p_main_one.setLayout(new BoxLayout(p_main_one,BoxLayout.Y_AXIS));

        JPanel p_name_one = new JPanel();
        p_name_one.setLayout(new FlowLayout(FlowLayout.CENTER));
        p_main_one.add(p_name_one);
        
        l_name_one=new JLabel("NAME");
        tf_name_one=new JTextField();
        tf_name_one.setPreferredSize(new Dimension (150,50));
        p_name_one.add(l_name_one);
        p_name_one.add(tf_name_one);
        
        frame.add(p_main_one);
        

        /////////////
        JPanel p_time = new JPanel();
        p_time.setLayout(new FlowLayout(FlowLayout.CENTER));
        p_main_one.add(p_time);
        
        l_time=new JLabel("TIME(in mins)");
        tf_time=new JTextField();
        tf_time.setPreferredSize(new Dimension (150,50));
        p_time.add(l_time);
        p_time.add(tf_time);
        
        frame.add(p_main_one);

        ///////////
        
        JPanel p_joined=  new JPanel();
        p_joined.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel l_joined = new JLabel("CATEGORY : ");

        p_joined.add(l_joined);

        p_main_one.add(p_joined);

        ButtonGroup bg_joined = new ButtonGroup();

        JRadioButton rb_open = new JRadioButton("OPEN 10K RUN");
        JRadioButton rb_half = new JRadioButton("HALF MARATHON");
        JRadioButton rb_gdr = new JRadioButton("GREAT DELHI RUN");

        bg_joined.add(rb_open);
        bg_joined.add(rb_half); 
        bg_joined.add(rb_gdr);
        
        p_joined.add(rb_open);
        p_joined.add(rb_half);
        p_joined.add(rb_gdr);
        /////////////////////
        JPanel p_buttons=  new JPanel();
        p_buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton b_next = new JButton("NEXT");
        JButton b_winner = new JButton("WINNER");
        JButton b_cancel = new JButton("CANCEL");
        //////// backend for button next
        
        b_next.addActionListener(new ActionListener(){
        @Override
         public void actionPerformed(ActionEvent e) {
             int value= Integer.parseInt(tf_time.getText());
                int cat;
                if(rb_open.isSelected()){
                    cat=0;//open
                }
                else if(rb_half.isSelected()){
                    cat=1;//half
                }
                else {
                    cat=2;//gdr
                }
//                System.out.println(cat);
//            System.out.println(tf_name_one.getText());
             L.add(value,tf_name_one.getText(),cat);        //added all elements to LL
             
            tf_name_one.setText("");
            tf_time.setText("");
            bg_joined.clearSelection();
            //calculate winners and 2nd winners of each category 
        
        }
    });
        b_cancel.addActionListener(new ActionListener(){
        @Override
         public void actionPerformed(ActionEvent e) {
        frame.setVisible(false); 
        frame.dispose();
        }
    });
          
        b_winner.addActionListener(new ActionListener(){
        @Override
         public void actionPerformed(ActionEvent e) {
            
        Runner ptr=L.head;
        int min0=Integer.MAX_VALUE;
        int min1=Integer.MAX_VALUE;
        int min2=Integer.MAX_VALUE;
        String str_min0=" ";
        String str_min1=" ";
        String str_min2=" ";
        while(ptr!=null){
//            System.out.println(ptr.name+" ");
            if(ptr.category==0){
                if(ptr.data<min0){
                min0=ptr.data;
                str_min0=ptr.name;
                
                }
             }
            else if(ptr.category==1){
                if(ptr.data<min1){
                min1=ptr.data;
                str_min1=ptr.name;
                }
             }
             else if(ptr.category==2){
                if(ptr.data<min2){
                min2=ptr.data;
                str_min2=ptr.name;
                }
             }
        ptr=ptr.next;
        }
        
        int secmin0=Integer.MAX_VALUE;
        int secmin1=Integer.MAX_VALUE;
        int secmin2=Integer.MAX_VALUE;
        String str_secmin0=" ";
        String str_secmin1=" ";
        String str_secmin2=" ";
        
        Runner nptr=L.head;
        while(nptr!=null){
            if(nptr.category==0){
//                System.out.println(nptr.name);
                if(nptr.data>min0 && nptr.data<secmin0){
                secmin0=nptr.data;
                str_secmin0=nptr.name;
            }
            }
            else if(nptr.category==1){
                if(nptr.data>min1 && nptr.data<secmin1){
                secmin1=nptr.data;
                str_secmin1=nptr.name;
            }
            }
            else if(nptr.category==2){
                if(nptr.data>min2 && nptr.data<secmin2){
                secmin2=nptr.data;
                str_secmin2=nptr.name;
            }
            }
            nptr=nptr.next;
            
        }
//        System.out.println(min0+" "+secmin0);
//        System.out.println(str_min0+" "+str_secmin0);
            Display(str_min0,str_min1,str_min2,str_secmin0,str_secmin1,str_secmin2);
        }
    });
       
        p_buttons.add(b_next);
        p_buttons.add(b_winner);
         p_buttons.add(b_cancel);

        p_main_one.add(p_buttons);
        /////////////////////
        //////////////
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public static void Display(String min0,String min1,String min2,String secmin0,String secmin1,String secmin2){
//         System.out.println("min "+min0 +" second_min "+secmin0);
        JFrame frame1=new JFrame("Results");
        frame1.setSize(600,600);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p_main = new JPanel();
        p_main.setLayout(new BoxLayout(p_main,BoxLayout.Y_AXIS));

        JPanel p_name = new JPanel();
        p_name.setLayout(new FlowLayout(BoxLayout.Y_AXIS));
        p_main.add(p_name);
        l_name=new JLabel("HALF MARATHON FIRST PRIZE: ");
        JTextField tf_name11=new JTextField();
        tf_cash=new JTextField();
        tf_name11.setPreferredSize(new Dimension (100,50));
        tf_cash.setPreferredSize(new Dimension (100,50));
        p_name.add(l_name);
        p_name.add(tf_name11);
        p_name.add(tf_cash);
        tf_cash.setText("Rs. 2,80,000");
        tf_name11.setText(min1);
        
        ///////////////////////
        JPanel p_second = new JPanel();
        p_name.setLayout(new FlowLayout(BoxLayout.Y_AXIS));
        p_main.add(p_second);
        l_name=new JLabel("HALF MARATHON SECOND PRIZE: ");
        JTextField tf_name12=new JTextField();
        tf_cash=new JTextField();
        tf_name12.setPreferredSize(new Dimension (100,50));
        tf_cash.setPreferredSize(new Dimension (100,50));
        p_second.add(l_name);
        p_second.add(tf_name12);
        p_second.add(tf_cash);
        tf_cash.setText("2,10,000"); 
        tf_name12.setText(secmin1);
        //////////////////////////
        JPanel p_open_first = new JPanel();
        p_name.setLayout(new FlowLayout(BoxLayout.Y_AXIS));
        p_main.add(p_open_first);
        l_name=new JLabel("OPEN MARATHON FIRST PRIZE: ");
        JTextField tf_name01=new JTextField();
        tf_cash=new JTextField();
        tf_name01.setPreferredSize(new Dimension (100,50));
        tf_cash.setPreferredSize(new Dimension (100,50));
        p_open_first.add(l_name);
        p_open_first.add(tf_name01);
        p_open_first.add(tf_cash);
        tf_cash.setText("Rs. 1,90,000"); 
        tf_name01.setText(min0);
        ////////////////////
        JPanel p_open_sec = new JPanel();
        p_open_sec.setLayout(new FlowLayout(BoxLayout.Y_AXIS));
        p_main.add(p_open_sec);
        l_name=new JLabel("OPEN MARATHON SECOND PRIZE: ");
        JTextField tf_name02=new JTextField();
        tf_cash=new JTextField();
        tf_name02.setPreferredSize(new Dimension (100,50));
        tf_cash.setPreferredSize(new Dimension (100,50));
        p_open_sec.add(l_name);
        p_open_sec.add(tf_name02);
        p_open_sec.add(tf_cash);
        tf_cash.setText("Rs.1,50,00"); 
        tf_name02.setText(secmin0);
        ///////////////////////////
        JPanel p_gdr_first = new JPanel();
        p_gdr_first.setLayout(new FlowLayout(FlowLayout.CENTER));
        p_main.add(p_gdr_first);
        l_name=new JLabel("GREAT DELHI MARATHON FIRST PRIZE: ");
        JTextField tf_name21=new JTextField();
        tf_cash=new JTextField();
        tf_name21.setPreferredSize(new Dimension (100,50));
        tf_cash.setPreferredSize(new Dimension (100,50));
        p_gdr_first.add(l_name);
        p_gdr_first.add(tf_name21);
        p_gdr_first.add(tf_cash);
        tf_cash.setText("Rs. 1,35,000");
        tf_name21.setText(min2);
        //////////////////////////
        JPanel p_gdr_sec = new JPanel();
        p_gdr_sec.setLayout(new FlowLayout(BoxLayout.Y_AXIS));
        p_main.add(p_gdr_sec);
        l_name=new JLabel("GREAT DELHI MARATHON SECOND PRIZE: ");
        JTextField tf_name22=new JTextField();
        tf_cash=new JTextField();
        tf_name22.setPreferredSize(new Dimension (100,50));
        tf_cash.setPreferredSize(new Dimension (100,50));
        p_gdr_sec.add(l_name);
        p_gdr_sec.add(tf_name22);
        p_gdr_sec.add(tf_cash);
        tf_cash.setText("Rs.1,15,000");
        tf_name22.setText(secmin2);
        ///////////////// 
        /////////////////
        JPanel p_buttons=  new JPanel();
        p_buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton b_cancel = new JButton("CANCEL");
         b_cancel.addActionListener(new ActionListener(){
        @Override
         public void actionPerformed(ActionEvent e) {
        frame1.setVisible(false); 
        frame1.dispose();
        }
    });
         p_buttons.add(b_cancel);
         p_main.add(p_buttons);
        
        frame1.add(p_main);
        frame1.setSize(600,600);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
}
class LinkedList{
    Runner head;
    Runner pointer;
    public LinkedList(){
        head= new Runner(1,"A",5);
        pointer=head;
     }
    public void add(int x, String str_name, int category){
        while(pointer.next!=null){
            pointer=pointer.next;
        }
        Runner toadd= new Runner(x,str_name,category);
        pointer.next=toadd;
    }
}
class Runner{
    int data;
    String name;
    Runner next;
    int category;
    public Runner(int x,String str_name,int cat){
        data=x;
        next=null;
        name=str_name;
        category=cat;
    }
}

