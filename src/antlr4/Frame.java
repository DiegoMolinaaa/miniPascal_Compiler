package antlr4;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;
import org.antlr.v4.runtime.BaseErrorListener;

import java.awt.Color;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author pame
 */
public class Frame extends javax.swing.JFrame {

    File archivo = null;

    public Frame() {
        initComponents();
        addKeywordHighlighting(new String[]{"public", "class", "void", "static", "main"}, new Color(102,102,255));
        synchronizeScrollBars(jScrollPane1, jScrollPane2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNumLinea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSalida = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuAbrirTxt = new javax.swing.JMenuItem();
        menuGuardarTxt = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuCompilar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        jPanel2.setBackground(new java.awt.Color(59, 61, 59));
        jPanel2.setMinimumSize(new java.awt.Dimension(950, 570));
        jPanel2.setPreferredSize(new java.awt.Dimension(950, 570));
        jPanel2.setLayout(null);

        txtCodigo.setBackground(new java.awt.Color(51, 51, 51));
        txtCodigo.setColumns(20);
        txtCodigo.setForeground(new java.awt.Color(204, 204, 204));
        txtCodigo.setRows(5);
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtCodigo);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(90, 20, 670, 380);

        jScrollPane2.setHorizontalScrollBar(null);
        jScrollPane2.setVerifyInputWhenFocusTarget(false);

        txtNumLinea.setEditable(false);
        txtNumLinea.setBackground(new java.awt.Color(51, 51, 51));
        txtNumLinea.setColumns(1);
        txtNumLinea.setForeground(new java.awt.Color(204, 204, 255));
        txtNumLinea.setRows(5);
        txtNumLinea.setText("1");
        jScrollPane2.setViewportView(txtNumLinea);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(40, 20, 50, 380);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Salida generada");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(40, 440, 110, 17);

        txtSalida.setEditable(false);
        txtSalida.setBackground(new java.awt.Color(51, 51, 51));
        txtSalida.setColumns(20);
        txtSalida.setForeground(new java.awt.Color(204, 204, 204));
        txtSalida.setRows(5);
        jScrollPane4.setViewportView(txtSalida);

        jPanel2.add(jScrollPane4);
        jScrollPane4.setBounds(40, 470, 720, 200);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        menuArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("../assets/open-folder.png"))); // NOI18N
        menuArchivo.setText("Archivo");

        menuAbrirTxt.setText("Abrir un archivo existente");
        menuAbrirTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirTxtActionPerformed(evt);
            }
        });
        menuArchivo.add(menuAbrirTxt);

        menuGuardarTxt.setText("Guardar como...");
        menuGuardarTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuardarTxtActionPerformed(evt);
            }
        });
        menuArchivo.add(menuGuardarTxt);

        jMenuBar1.add(menuArchivo);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("../assets/clean.png"))); // NOI18N
        jMenu1.setText("Limpiar");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        menuCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("../assets/play-button-arrowhead.png"))); // NOI18N
        menuCompilar.setText("Compilar código");
        menuCompilar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuCompilarMousePressed(evt);
            }
        });
        jMenuBar1.add(menuCompilar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuGuardarTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuardarTxtActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como");

        // Abrir cuadro de diálogo en modo guardar
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtener archivo seleccionado o nombre nuevo proporcionado por el usuario
            File archivoNuevo = fileChooser.getSelectedFile();

            // Agregar extensión si no está presente
            if (!archivoNuevo.getName().endsWith(".txt")) {
                archivoNuevo = new File(archivoNuevo.getAbsolutePath() + ".txt");
            }

            if (archivoNuevo.exists()) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "El archivo ya existe. ¿Deseas sobrescribirlo?",
                        "Confirmar sobrescritura",
                        JOptionPane.YES_NO_OPTION);

                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            // Guardar el contenido en el archivo
            FileWriter fw = null;
            BufferedWriter bw = null;
            try {
                fw = new FileWriter(archivoNuevo);
                bw = new BufferedWriter(fw);
                for (Object linea : txtCodigo.getText().lines().toArray()) {
                    bw.write(linea.toString());
                    bw.newLine();
                }
                bw.close();
                fw.close();
                JOptionPane.showMessageDialog(null, "El archivo fue guardado exitosamente.", "", 1);
            } catch (Exception ex) {
            }
        }
        
    }//GEN-LAST:event_menuGuardarTxtActionPerformed

    private void menuAbrirTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbrirTxtActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Archivo de texto", "txt"));
        int seleccion = fc.showOpenDialog(null);
        archivo = fc.getSelectedFile();
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            txtSalida.setText("");
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(archivo.getPath()));
                String st;
                int linea = 1;
                txtNumLinea.setText("1");
                while ((st = br.readLine()) != null) {
                    txtNumLinea.append('\n' + Integer.toString(++linea));
                    txtCodigo.append(st + '\n');
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }//GEN-LAST:event_menuAbrirTxtActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() != java.awt.event.KeyEvent.VK_ENTER && txtCodigo.getLineCount() < txtNumLinea.getLineCount()) {
            txtNumLinea.setText("1");
            for (int i = 2; i <= txtCodigo.getLineCount(); i++) {
                txtNumLinea.append('\n' + Integer.toString(i));
            }
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            txtNumLinea.setText("1");
            for (int i = 2; i <= txtCodigo.getLineCount() + 1; i++) {
                txtNumLinea.append('\n' + Integer.toString(i));
            }
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        int option = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro? Esta acción borrará el código y se perderán los cambios.",
                "Confirmación",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            txtCodigo.setText("");
            txtNumLinea.setText("1");
        }
    }//GEN-LAST:event_jMenu1MousePressed

    private void menuCompilarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCompilarMousePressed
        // Proceso de compilacion
        txtSalida.setText("");
        if (txtCodigo.getText().length() > 0 && !txtCodigo.getText().isBlank() && !txtCodigo.getText().isEmpty()) {
            String salida = "";
            try {
                CharStream input = CharStreams.fromString(txtCodigo.getText());
                MiniPascalLexer lexer = new MiniPascalLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                MiniPascalParser parser = new MiniPascalParser(tokens);
                ArrayList<ErrorCompilacion> erroresEncontrados = new ArrayList<>();


                // Personalizar listener de errores Lexicos
                lexer.removeErrorListeners();
                lexer.addErrorListener(new BaseErrorListener()
                   {
                       @Override
                       public void syntaxError(Recognizer<?, ?> recognizer, Object simboloIncorrecto, int linea, int posicionEnLinea, String msg, RecognitionException e)
                       {
                           erroresEncontrados.add(new ErrorCompilacion(linea, posicionEnLinea, traducirMensaje(msg), ErrorCompilacion.ErrorTipo.Léxico));
                       }
                   }
                );

                // Personalizar listener de errores Sintacticos
                parser.removeErrorListeners();
                parser.addErrorListener(new BaseErrorListener()
                    {
                        @Override
                        public void syntaxError(Recognizer<?, ?> recognizer, Object simboloIncorrecto, int linea, int posicionEnLinea, String msg, RecognitionException e)
                        {
                            erroresEncontrados.add(new ErrorCompilacion(linea, posicionEnLinea, traducirMensaje(msg), ErrorCompilacion.ErrorTipo.Sintáctico));
                        }
                    }
                );

                // Arbolito
                ParseTree tree = parser.program();

                // Imprimir resultado de la compilacion
                if (erroresEncontrados.isEmpty()) {
                    MiniPascalASTVisitorPersonal visitor = new MiniPascalASTVisitorPersonal(tree, salida);
//                    visitor.visit(tree);
//                    System.out.println("\nCompilación exitosa!");
                    salida += "\nCompilación exitosa!";
                }
                else {
//                    System.out.println();
                    salida += "\nErrores encontrados (" + erroresEncontrados.size() + "):\n";
                    for (ErrorCompilacion error : erroresEncontrados) {
//                        System.err.println(error);
                        salida += error.toString();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                txtSalida.setText(salida);
            }
        }
    }//GEN-LAST:event_menuCompilarMousePressed

    public void addKeywordHighlighting(String[] keywords, Color color) {
        JTextArea textArea = txtCodigo;
        Highlighter highlighter = textArea.getHighlighter();
        Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(color);

        // Construir una expresión regular para las palabras clave
        StringBuilder regexBuilder = new StringBuilder("\\b(");
        for (int i = 0; i < keywords.length; i++) {
            regexBuilder.append(Pattern.quote(keywords[i]));
            if (i < keywords.length - 1) {
                regexBuilder.append("|");
            }
        }
        regexBuilder.append(")\\b");
        Pattern keywordPattern = Pattern.compile(regexBuilder.toString());

        // Agregar un DocumentListener al JTextArea
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                highlightKeywords(textArea, highlighter, painter, keywordPattern);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                highlightKeywords(textArea, highlighter, painter, keywordPattern);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // No es necesario manejar este evento para documentos simples.
            }
        });
    }

    private void highlightKeywords(JTextArea textArea, Highlighter highlighter, Highlighter.HighlightPainter painter, Pattern keywordPattern) {
        SwingUtilities.invokeLater(() -> {
            // Limpiar los resaltes existentes
            highlighter.removeAllHighlights();
            // Obtener el texto completo
            String text = textArea.getText();
            // Buscar coincidencias de palabras clave
            Matcher matcher = keywordPattern.matcher(text);
            while (matcher.find()) {
                try {
                    // Aplicar resalte a las palabras clave encontradas
                    int start = matcher.start();
                    int end = matcher.end();
                    highlighter.addHighlight(start, end, painter);
                } catch (BadLocationException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public static void synchronizeScrollBars(JScrollPane scrollPane1, JScrollPane scrollPane2) {
        JScrollBar verticalScrollBar1 = scrollPane1.getVerticalScrollBar();
        JScrollBar verticalScrollBar2 = scrollPane2.getVerticalScrollBar();
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        verticalScrollBar1.addAdjustmentListener(e -> {
            if (!verticalScrollBar2.getValueIsAdjusting()) {
                verticalScrollBar2.setValue(e.getValue());
            }
        });

        verticalScrollBar2.addAdjustmentListener(e -> {
            if (!verticalScrollBar1.getValueIsAdjusting()) {
                verticalScrollBar1.setValue(e.getValue());
            }
        });

    }

    public static String traducirMensaje(String msg) {
        String msgTraducido = msg.replace("no viable alternative at input", "ninguna alternativa viable en la entrada");
        msgTraducido = msgTraducido.replace("token recognition error at:","error de reconocimiento de token en:");
        msgTraducido = msgTraducido.replace("extraneous input","entrada ajena");
        msgTraducido = msgTraducido.replace("expecting","se esperaba");
        msgTraducido = msgTraducido.replace("missing","falta");
        msgTraducido = msgTraducido.replace("mismatched input","entrada incorrecta");
        msgTraducido = msgTraducido.replace("at","en");
        msgTraducido = msgTraducido.replace("alterneniva","alternativa");
        return msgTraducido;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
                UIManager.put("nimbusBase", new java.awt.Color(Color.GRAY.getRGB()));
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frame frame = new Frame();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea txtSalida;
    private javax.swing.JMenuItem menuAbrirTxt;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuCompilar;
    private javax.swing.JMenuItem menuGuardarTxt;
    private javax.swing.JTextArea txtCodigo;
    private javax.swing.JTextArea txtNumLinea;
    // End of variables declaration//GEN-END:variables

}
