/*
 * The MIT License
 *
 * Copyright 2015 Diego Geronimo D' Onofre.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package erpsystem.forms;

import erpsystem.Util;
import static erpsystem.Util.*;
import erpsystem.db.Produto;
import javax.swing.JFrame;

/**
 *
 * @author Diego
 */
public class ProdutosView extends javax.swing.JFrame {

    /**
     * Creates new form Produto
     */
    public ProdutosView() {
        initComponents();
        
        java.awt.Point p = Util.getCenterPoint(this.getWidth(), this.getHeight());
        this.setLocation(p);
        this.setResizable(false);
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Cadastro de Produtos");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCad = new javax.swing.JButton();
        lblDesc = new javax.swing.JLabel();
        tfdDesc = new javax.swing.JTextField();
        tfdCodBarras = new javax.swing.JTextField();
        lblCodBarras = new javax.swing.JLabel();
        tfdPreco = new javax.swing.JTextField();
        lblPreco = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCad.setText("Cadastrar");
        btnCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadActionPerformed(evt);
            }
        });

        lblDesc.setText("Descrição");

        lblCodBarras.setText("Código de Barras");

        lblPreco.setText("Preço");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCad)
                    .addComponent(lblDesc)
                    .addComponent(tfdDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodBarras)
                    .addComponent(tfdCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPreco)
                    .addComponent(tfdPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(262, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodBarras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblDesc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPreco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCad)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean validateFields()
    {
        if ( tfdCodBarras.getText().trim().equals("") )
        {
            msg("Por favor, informe o código de barras do produto.");
            tfdCodBarras.grabFocus();
            return false;        
        }
        
        if ( tfdDesc.getText().trim().equals("") )
        {
            msg("Por favor, informe uma descrição para o produto.");
            tfdDesc.grabFocus();
            return false;        
        }
        
        
        //Validando o preço.    
        
        
        String preco = tfdPreco.getText().trim();
        
        if ( preco.equals("") )
        {
            msg("Por favor, informe um preço para produto.");
            tfdPreco.grabFocus();
            return false;        
        }
        
        if (! isDouble(preco) ){
            msg("Por favor, informe um valor numérico\n"
              + " válido para o preço do produto.");
            tfdPreco.grabFocus();
            return false;
        }
        
        if (Double.parseDouble(preco) <= 0 ){
            msg("O preço do produto deve ser igual ou superior a zero.");
            tfdPreco.grabFocus();
            return false;
        }        
        
        return true;
    }
    
    private void btnCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadActionPerformed
        // TODO add your handling code here:
        if ( validateFields() ){
            Produto prod = new Produto();

            String codBarras = tfdCodBarras.getText();
            String desc = tfdDesc.getText();
            double preco = Double.parseDouble(tfdPreco.getText());

            prod.setCodBarras(codBarras);
            prod.setDescricao(desc);
            prod.setPreco(preco);
            business.Produtos.add(prod);
        }
    }//GEN-LAST:event_btnCadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProdutosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProdutosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProdutosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProdutosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProdutosView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCad;
    private javax.swing.JLabel lblCodBarras;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JTextField tfdCodBarras;
    private javax.swing.JTextField tfdDesc;
    private javax.swing.JTextField tfdPreco;
    // End of variables declaration//GEN-END:variables
}
