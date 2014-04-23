
/*
 * DataWindow.java
 *
 * Copyright (c) 2008 Sun Microsystems, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package org.sunspotworld.demo;

import com.sun.spot.util.IEEEAddress;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;

/**
 * Create a new window to graph the sensor readings in.
 *
 * @author Felipe Sales featuring Ron Goldman
 */
public class DataWindow extends JFrame {

    private int index = 0;
    private Color[] colors = new Color[10];

    DateFormat fmt = DateFormat.getDateTimeInstance();

    Spot[] spots;


    /** Creates new form DataWindow */
    public DataWindow() {
        initComponents();

        colors[0] = Color.red;
        colors[1] = Color.BLUE;
        colors[2] = Color.GREEN;
        colors[3] = Color.YELLOW;
        colors[4] = Color.BLACK;
        colors[5] = Color.orange;
        colors[6] = Color.PINK;
        colors[7] = Color.GRAY;
        colors[8] = Color.cyan;
        colors[9] = Color.lightGray;

        spots = new Spot[10];
        


    }


    public String[] addrToArray(){
        String[] aux = {"Vermelho" , "Azul", "Verde", "Amarelo", "Preto", "Laranja", "Rosa", "Cinza", "Cynza", "Light Gray"};

        return aux;
    }

    private Spot findSpot(long addr) {
               
        if (index > 0){
            for (int i = 0; i < index; i++){
                if(spots[i].getAddr() == addr){           
                    return spots[i];
                }
            }
        }

        spots[index] = new Spot(colors[index],addr);
        //ListSpots.addItem(Long.toString(spots[index].getAddr()));
        dataTextArea.append("Adiciona Addr:" + addr + "\n");
        dataTextArea.setCaretPosition(dataTextArea.getText().length());

        return spots[index++];

    }

    public void addData(long t, int v, long addr) {

        findSpot(addr).addData(t, v);

        dataTextArea.append(fmt.format(new Date(t)) + "    value = " + v + " Addr:" + addr + "\n");
        dataTextArea.setCaretPosition(dataTextArea.getText().length());
        repaint();
    }

    // Graph the sensor values in the dataPanel JPanel

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int left = dataPanel.getX() + 10;       // get size of pane
        int top = dataPanel.getY() + 30;
        int right = left + dataPanel.getWidth() - 20;
        int bottom = top + dataPanel.getHeight() - 20;

        int y0 = bottom - 20;                   // leave some room for margins
        int yn = top;
        int x0 = left + 33;
        int xn = right;
        double vscale = (yn - y0) / 800.0;      // light values range from 0 to 800
        double tscale = 1.0 / 2000.0;           // 1 pixel = 2 seconds = 2000 milliseconds

        // draw X axis = time
        g.setColor(Color.BLACK);
        g.drawLine(x0, yn, x0, y0);
        g.drawLine(x0, y0, xn, y0);
        int tickInt = 60 / 2;
        for (int xt = x0 + tickInt; xt < xn; xt += tickInt) {   // tick every 1 minute
            g.drawLine(xt, y0 + 5, xt, y0 - 5);
            int min = (xt - x0) / (60 / 2);
            g.drawString(Integer.toString(min), xt - (min < 10 ? 3 : 7) , y0 + 20);
        }

        // draw Y axis = sensor reading
        g.setColor(Color.BLUE);
        for (int vt = 800; vt > 0; vt -= 200) {         // tick every 200
            int v = y0 + (int)(vt * vscale);
            g.drawLine(x0 - 5, v, x0 + 5, v);
            g.drawString(Integer.toString(vt), x0 - 38 , v + 5);
        }

         // graph sensor values

       for (int j = 0; j < index; j++){

            Spot temp = spots[j];
            if(temp.isVisible()){
                g.setColor(temp.getColor());

                long[] time = (temp.getTime());
                int[] val = (temp.getVal());

                int xp = -1;
                int vp = -1;

                for (int i = 0; i < temp.getIndex(); i++) {
                    int x = x0 + (int)((time[i] - time[0]) * tscale);
                    int v = y0 + (int)(val[i] * vscale);
                    if (xp > 0) {
                        g.drawLine(xp, vp, x, v);
                    }
                    xp = x;
                    vp = v;
                }
             }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jCheckBox1 = new javax.swing.JCheckBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jOptionPane1 = new javax.swing.JOptionPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jToggleButton1 = new javax.swing.JToggleButton();
        dataPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTextArea = new javax.swing.JTextArea();
        MudarSpot = new javax.swing.JToolBar();
        MudaStatusSpot = new javax.swing.JToggleButton();
        ListSpots = new javax.swing.JComboBox();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jCheckBox1.setText("jCheckBox1");

        jButton1.setText("jButton1");

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList2);

        jToggleButton1.setText("jToggleButton1");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        dataPanel.setBackground(new java.awt.Color(255, 255, 255));
        dataPanel.setMinimumSize(new java.awt.Dimension(400, 250));
        dataPanel.setPreferredSize(new java.awt.Dimension(400, 250));
        getContentPane().add(dataPanel, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(400, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 100));

        dataTextArea.setColumns(20);
        dataTextArea.setEditable(false);
        dataTextArea.setRows(4);
        jScrollPane1.setViewportView(dataTextArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.SOUTH);

        MudarSpot.setRollover(true);

        MudaStatusSpot.setText("Mudar");
        MudaStatusSpot.setFocusable(false);
        MudaStatusSpot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MudaStatusSpot.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MudaStatusSpot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MudaStatusSpotActionPerformed(evt);
            }
        });
        MudarSpot.add(MudaStatusSpot);

        ListSpots.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Vermelho" , "Azul", "Verde", "Amarelo", "Preto", "Laranja", "Rosa", "Cinza", "Cynza", "Light Gray"}));
        ListSpots.setName(""); // NOI18N
        ListSpots.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListSpotsActionPerformed(evt);
            }
        });
        MudarSpot.add(ListSpots);

        getContentPane().add(MudarSpot, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void MudaStatusSpotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MudaStatusSpotActionPerformed
        int i = ListSpots.getSelectedIndex();

        if( i < index){
             (spots[i]).alternaVisible();
        }
    }//GEN-LAST:event_MudaStatusSpotActionPerformed

    private void ListSpotsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListSpotsActionPerformed

       //DefaultComboBoxModel model = (DefaultComboBoxModel) ListSpots.getModel();

        //for (int i = 0; i < 10; i++) {
          //  model.addElement(addrToArray()[i]);
        //}

        //ListSpots.setModel(model);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_ListSpotsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ListSpots;
    private javax.swing.JToggleButton MudaStatusSpot;
    private javax.swing.JToolBar MudarSpot;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JTextArea dataTextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

}
