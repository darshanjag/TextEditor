import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.print.PrinterException;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

class Editor extends JFrame implements ActionListener {
        JFrame f;

        JTextArea t;






    public void actionPerformed(ActionEvent e){

            String s = e.getActionCommand();
            if(s.equals("New")){
                t.setText("");

            }else if (s.equals("Cut")) {
                t.cut();
            }
            else if (s.equals("Open")) {
              JFileChooser jFileChooser = new JFileChooser("C:/Users/Acer/Documents");
              int r = jFileChooser.showOpenDialog(null);
              if(r==JFileChooser.APPROVE_OPTION){
                  File fi = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                  try{
                      String s1="",s2="";
                      FileReader fr= null;

                      fr = new FileReader(fi);

                      BufferedReader br = new BufferedReader(fr);


                      s2=br.readLine();

                      while((s1=br.readLine())!=null){
                          s2=s2+'\n'+s1;
                      }
                      t.setText(s2);
                  }catch (Exception et){
                      JOptionPane.showMessageDialog(f,et.getMessage());
                  }

              }
            }else if (s.equals("Save")) {
                JFileChooser j = new JFileChooser("C:/Users/Acer/Documents");
                int r = j.showSaveDialog(null);
                if(r==JFileChooser.APPROVE_OPTION){
                    try{
                        File fi = new File(j.getSelectedFile().getAbsolutePath());
                        FileWriter fw = new FileWriter(fi,false);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(t.getText());


                        bw.flush();
                        bw.close();
                    }catch (Exception et){
                        JOptionPane.showMessageDialog(f,et.getMessage());
                    }


                }

            }
            else if (s.equals("Copy")) {

                t.copy();
            }else if (s.equals("Paste")) {
                t.paste();
            }else if (s.equals("Close")) {
                System.out.println("close");
            }else if(s.equals(("Print"))){

                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        Editor(){
            f = new JFrame("Notepad");
            f.setVisible(true);
            try{
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                MetalLookAndFeel.setCurrentTheme(new OceanTheme());
            }catch (Exception e){

            }
            t= new JTextArea("");
            f.add(t);
            MenuBar menu = new MenuBar();
            Menu file =  new Menu("File");
            MenuItem m1 = new MenuItem("New");
            MenuItem m2 = new MenuItem("Open");
            MenuItem m3 = new MenuItem("Save");
            MenuItem m4 = new MenuItem("Print");

            m1.addActionListener(this);
            m2.addActionListener(this);
            m3.addActionListener(this);
            m4.addActionListener(this);

            file.add(m1);
            file.add(m2);
            file.add(m3);
            file.add(m4);

            Menu edit =  new Menu("Edit");
            MenuItem m5 = new MenuItem("Cut");
            MenuItem m6 = new MenuItem("Copy");
            MenuItem m7 = new MenuItem("Paste");

            m5.addActionListener(this);
            m6.addActionListener(this);
            m7.addActionListener(this);


            edit.add(m5);
            edit.add(m6);
            edit.add(m7);
            Menu close = new Menu("Close");

            menu.add(file);
            menu.add(edit);
            menu.add(close);
            f.setMenuBar(menu);
            f.setSize(1600,900);

        }

    public static void main(String [] args){
        Editor editor = new Editor();
    }

}
