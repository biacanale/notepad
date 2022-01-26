package notepad;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Notepad extends JFrame implements ActionListener {
    
    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("Arquivo");
    JMenu edit = new JMenu("Editar");
   
    JMenuItem newFile = new JMenuItem("Novo");
    JMenuItem saveFile = new JMenuItem("Salvar");
    JMenuItem exit = new JMenuItem("Sair");
    
    JMenuItem cut = new JMenuItem ("Recortar");
    JMenuItem copy = new JMenuItem ("Copiar");
    JMenuItem paste = new JMenuItem ("Colar");
    JMenuItem selectAll = new JMenuItem ("Selecionar tudo");
    
    JTextArea textArea = new JTextArea();
    
    
    Notepad() {
        setTitle("Bloco de Notas");
        setBounds(100,100,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        setJMenuBar(menubar);
        menubar.add(file);
        menubar.add(edit);
        
        file.add(newFile);
        file.add(saveFile);
        file.add(exit);
        
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        
        //Ajustando a barra de rolagem vertical e horizontal
        JScrollPane scrollpane = new JScrollPane(textArea);
        add(scrollpane);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
                
        //Quebra a frase em outra linha
        textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        newFile.addActionListener(this);
        saveFile.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,KeyEvent.CTRL_DOWN_MASK));
        
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("Novo")){
            textArea.setText(null);
        }else if (e.getActionCommand().equalsIgnoreCase("Salvar")){
            
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter ("Documentos de Texto (.txt)", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);
            
            int action = fileChooser.showSaveDialog(null);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }else{
                String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();
                if(!fileName.contains (".txt"))
                    fileName = fileName+".txt";
                try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                textArea.write(writer);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
              
            }else if (e.getActionCommand().equalsIgnoreCase("Sair")){
                
                System.exit(0);
                
                }else if (e.getActionCommand().equalsIgnoreCase("Recortar")){
                     textArea.cut();
                     
                    }else if (e.getActionCommand().equalsIgnoreCase("Copiar")){
                        textArea.copy();
                        
                        }else if (e.getActionCommand().equalsIgnoreCase("Colar")){
                        textArea.paste();
                        
                        }else if (e.getActionCommand().equalsIgnoreCase("Selecionar tudo")){
                            textArea.selectAll();
        }
    }
    
 
    public static void main(String[] args) throws Exception {
            
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new Notepad().setVisible(true);
    }
}

