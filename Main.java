import javax.swing.*;     // For JFrame
import java.awt.*;        // For setBackground() method
import java.awt.event.*;  // Event handling
import java.io.*;
import javax.swing.text.*;
import javax.swing.plaf.metal.*;

class gui extends JFrame implements ActionListener {
  JFrame f;
  JTextArea t;

  gui() {
    // frame initialization
    f = new JFrame("Notes");

    try {
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
      MetalLookAndFeel.setCurrentTheme(new OceanTheme());
    }
    catch (Exception e) {
    }


    f.setBounds(400,300,1280,920);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setResizable(true);
    f.setVisible(true);

    // text component
    t = new JTextArea();

    // menu
    JMenuBar mb = new JMenuBar();

    // menu -- file
    JMenu m1 = new JMenu("File");
    mb.add(m1);

    JMenuItem mi1 = new JMenuItem("New");
    JMenuItem mi2 = new JMenuItem("Open");
    JMenuItem mi3 = new JMenuItem("Save");

    mi1.addActionListener(this);
    mi2.addActionListener(this);
    mi3.addActionListener(this);

    m1.add(mi1);
    m1.add(mi2);
    m1.add(mi3);

    // menu -- edit
    JMenu m2 = new JMenu("Edit");
    mb.add(m2);

    JMenuItem mi4 = new JMenuItem("Cut");
    JMenuItem mi5 = new JMenuItem("Copy");
    JMenuItem mi6 = new JMenuItem("Paste");


    mi4.addActionListener(this);
    mi5.addActionListener(this);
    mi6.addActionListener(this);

    m2.add(mi4);
    m2.add(mi5);
    m2.add(mi6);

    f.getContentPane().add(BorderLayout.NORTH, mb);
    f.add(t);

    JPanel LPanel = new JPanel();
  }

  public void actionPerformed(ActionEvent e) {
    String s = e.getActionCommand();

    // cut text
    if (s.equals("Cut")) {
      t.cut();
    }
    // copy text
    else if (s.equals("Copy")) {
      t.copy();
    }
    // paste text
    else if (s.equals("Paste")) {
      t.paste();
    }
    // save file
    else if (s.equals("Save")) {
      JFileChooser j = new JFileChooser("f:");
      int r = j.showSaveDialog(null);

      if (r == JFileChooser.APPROVE_OPTION) {

        File fi = new File(j.getSelectedFile().getAbsolutePath());

        try {
          FileWriter wr = new FileWriter(fi, false);

          BufferedWriter w = new BufferedWriter(wr);

          w.write(t.getText());

          w.flush();
          w.close();
        }
        catch (Exception evt) {
          JOptionPane.showMessageDialog(f, evt.getMessage());
        }
      }
      else {
          JOptionPane.showMessageDialog(f, "User canceled operation");
        }
      }

      else if (s.equals("Open")) {
        JFileChooser j = new JFileChooser("f:");
        int r = j.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
          File fi = new File(j.getSelectedFile().getAbsolutePath());

          try {
            String s1 = "", sl = "";

            FileReader fr = new FileReader(fi);
            BufferedReader br = new BufferedReader(fr);

            sl = br.readLine();

            while ((s1 = br.readLine()) != null) {
              sl = sl + "\n" + s1;
            }

            t.setText(sl);
          }
          catch (Exception evt){
            JOptionPane.showMessageDialog(f, evt.getMessage());
          }
        }
        else {
          JOptionPane.showMessageDialog(f, "User canceled operation");
        }
      }

      else if (s.equals("New")) {
        t.setText("");
      }
    }
}

public class Main {
  public static void main(String[] args) {
    new gui();
  }
}
