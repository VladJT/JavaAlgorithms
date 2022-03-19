package com.company.gui.sample;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.*;
import java.io.File;
import java.util.Collections;
import java.util.Vector;

public class MyForm extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTree fileTree;
    private JTree fileTree2;


    public MyForm() {
        super("File Browser");
        setSize(800, 600);
        setLocation(400, 200);
        setContentPane(contentPane);

        getRootPane().setDefaultButton(buttonOK);

        buttonOK.setIcon(UIManager.getIcon("OptionPane.questionIcon"));
        buttonCancel.setIcon(UIManager.getIcon("OptionPane.warningIcon"));


        DefaultMutableTreeNode treeModel = addNodes(null, new File("D:\\Music"));
        fileTree = new JTree(treeModel);
        fileTree.setShowsRootHandles(true);

        fileTree2 = new JTree(treeModel);
        //contentPane.add(fileTree2);


        // установка обработчиков событий
        setListeners();
    }

    private void setListeners() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        dispose();
//        JOptionPane.showMessageDialog(contentPane, "<html><h4>Некорректный ввод</h4>", "Ошибка", JOptionPane.ERROR_MESSAGE,
//                UIManager.getIcon("OptionPane.warningIcon"));
    }


    /**
     * Add nodes from under "dir" into curTop. Highly recursive.
     */
    DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
        String curPath = dir.getPath();
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
        if (curTop != null) { // should only be null at root
            curTop.add(curDir);
        }
        Vector ol = new Vector();
        String[] tmp = dir.list();
        for (int i = 0; i < tmp.length; i++)
            ol.addElement(tmp[i]);
        Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
        File f;
        Vector files = new Vector();
        // Make two passes, one for Dirs and one for Files. This is #1.
        for (int i = 0; i < ol.size(); i++) {
            String thisObject = (String) ol.elementAt(i);
            String newPath;
            if (curPath.equals("."))
                newPath = thisObject;
            else
                newPath = curPath + File.separator + thisObject;
            if ((f = new File(newPath)).isDirectory())
                addNodes(curDir, f);
            else
                files.addElement(thisObject);
        }
        // Pass two: for files.
        for (int fnum = 0; fnum < files.size(); fnum++)
            curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
        return curDir;
    }

    public static void main(String[] args) {
        MyForm mainForm = new MyForm();
        mainForm.setVisible(true);
        //  System.exit(0);
    }


}
