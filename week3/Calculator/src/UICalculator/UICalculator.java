package UICalculator;

import javax.swing.JButton;
import calculator.Calculator;
import exceptions.Exceptions;
import java.util.HashMap;
/**
 *
 * @author cj
 */
public class UICalculator extends javax.swing.JFrame {

    private final Calculator calculator;
    private final HashMap operatorsButtonsGroup;
    private final HashMap numbersButtonsGroup;
    private final HashMap baseButtonsGroup;
    private String savedANS;
    private String lastOperation;
    private final int displayLength;
    private boolean equalPressed;
    /**
     * Creates new form UICalculator
     */
    public UICalculator() {
        initComponents();
        calculator = new Calculator();
        savedANS = Double.toString(calculator.getANS());
        operatorsButtonsGroup = new HashMap();
        numbersButtonsGroup = new HashMap();
        baseButtonsGroup = new HashMap();
        lastOperation = "setANS";
        displayLength = 14;
        
        operatorsButtonsGroup.put("add",plusButton);
        operatorsButtonsGroup.put("sub",minusButton);
        operatorsButtonsGroup.put("mul",mulButton);
        operatorsButtonsGroup.put("div",divButton);
        operatorsButtonsGroup.put("inv",invButton);
        
        numbersButtonsGroup.put("1",num1Button);
        numbersButtonsGroup.put("2",num2Button);
        numbersButtonsGroup.put("3",num3Button);
        numbersButtonsGroup.put("4",num4Button);
        numbersButtonsGroup.put("5",num5Button);
        numbersButtonsGroup.put("6",num6Button);
        numbersButtonsGroup.put("7",num7Button);
        numbersButtonsGroup.put("8",num8Button);
        numbersButtonsGroup.put("9",num9Button);
        numbersButtonsGroup.put("A",numAButton);
        numbersButtonsGroup.put("B",numBButton);
        numbersButtonsGroup.put("C",numCButton);
        numbersButtonsGroup.put("D",numDButton);
        numbersButtonsGroup.put("E",numEButton);
        numbersButtonsGroup.put("F",numFButton);
        
        baseButtonsGroup.put("dec",decButton);
        baseButtonsGroup.put("bin",binButton);
        baseButtonsGroup.put("oct",octButton);
        baseButtonsGroup.put("hex",hexButton);
        
        java.awt.event.ActionEvent fictionalEvt = null;
        decButtonActionPerformed(fictionalEvt);
    }

    public String getSavedANS() {
        return savedANS;
    }

    public void setSavedANS(String savedANS) {
        this.savedANS = savedANS;
    }
    
    public String getLastOperation() {
        return lastOperation;
    }
    
    public void setLastOperation(String lastOperation) {
        Exceptions.checkLastOperationValue(lastOperation);
        this.lastOperation = lastOperation;
    }

    public boolean getEqualPressed() {
        return equalPressed;
    }

    public void setEqualPressed(boolean equalPressed) {
        this.equalPressed = equalPressed;
    }
    
    private boolean wasEqualPressed() {
        if(getEqualPressed()) {
            setEqualPressed(false);
            return true;
        }
        return false;
    }
    
    private void disableAllOperatorsButtonsExcept(String operator) {
        operatorsButtonsGroup.keySet().forEach((obj) -> {
            String key = (String) obj;
            if(key != operator) {
                javax.swing.JButton button = (javax.swing.JButton) 
                        operatorsButtonsGroup.get(key);
                button.setEnabled(false);
            }
        });
    }
    
    private void enableAllOperatorsButtonsExcept(String operator) {
        operatorsButtonsGroup.keySet().forEach((obj) -> {
            String key = (String) obj;
            if(key != operator) {
                javax.swing.JButton button = (javax.swing.JButton) 
                        operatorsButtonsGroup.get(key);
                button.setEnabled(true);
            }
        });
    }
    
    private void enableAllNumbersButtons() {
        numbersButtonsGroup.keySet().forEach((obj) -> {
            String key = (String) obj;
            javax.swing.JButton button = (javax.swing.JButton)
                numbersButtonsGroup.get(key);
            button.setEnabled(true);
        });
    }
    
    private void disableNumbersButtons(String[] numbersButtonsID) {
        for(String numberButtonID : numbersButtonsID) {
            javax.swing.JButton button = (javax.swing.JButton)
                numbersButtonsGroup.get(numberButtonID);
            button.setEnabled(false);
        }
    }
    
    private void enableAllBaseButtons() {
        baseButtonsGroup.keySet().forEach((obj) -> {
            String key = (String) obj;
            javax.swing.JRadioButton button = (javax.swing.JRadioButton)
                baseButtonsGroup.get(key);
            button.setEnabled(true);
        });
    }
    
    private void disableAllBaseButtons() {
        baseButtonsGroup.keySet().forEach((obj) -> {
            String key = (String) obj;
            javax.swing.JRadioButton button = (javax.swing.JRadioButton)
                baseButtonsGroup.get(key);
            button.setEnabled(false);
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jTextField = new javax.swing.JTextField();
        ccButton = new javax.swing.JButton();
        arrowButton = new javax.swing.JButton();
        num7Button = new javax.swing.JButton();
        num8Button = new javax.swing.JButton();
        num9Button = new javax.swing.JButton();
        numFButton = new javax.swing.JButton();
        num4Button = new javax.swing.JButton();
        num5Button = new javax.swing.JButton();
        num6Button = new javax.swing.JButton();
        numEButton = new javax.swing.JButton();
        num1Button = new javax.swing.JButton();
        num2Button = new javax.swing.JButton();
        num3Button = new javax.swing.JButton();
        numDButton = new javax.swing.JButton();
        num0Button = new javax.swing.JButton();
        numCButton = new javax.swing.JButton();
        numBButton = new javax.swing.JButton();
        numAButton = new javax.swing.JButton();
        dotButton = new javax.swing.JButton();
        ansButton = new javax.swing.JButton();
        equalButton = new javax.swing.JButton();
        plusButton = new javax.swing.JButton();
        minusButton = new javax.swing.JButton();
        mulButton = new javax.swing.JButton();
        divButton = new javax.swing.JButton();
        invButton = new javax.swing.JButton();
        binButton = new javax.swing.JRadioButton();
        octButton = new javax.swing.JRadioButton();
        decButton = new javax.swing.JRadioButton();
        hexButton = new javax.swing.JRadioButton();
        acButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("jButton1"); // NOI18N
        setResizable(false);

        jTextField.setEditable(false);
        jTextField.setFont(new java.awt.Font("DejaVu Serif Condensed", 1, 48)); // NOI18N
        jTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField.setText("0");
        jTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldActionPerformed(evt);
            }
        });

        ccButton.setText("CC");
        ccButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ccButtonActionPerformed(evt);
            }
        });

        arrowButton.setText("<-");
        arrowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrowButtonActionPerformed(evt);
            }
        });

        num7Button.setText("7");
        num7Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        num8Button.setText("8");
        num8Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        num9Button.setText("9");
        num9Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        numFButton.setText("F");
        numFButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        num4Button.setText("4");
        num4Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        num5Button.setText("5");
        num5Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        num6Button.setText("6");
        num6Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        numEButton.setText("E");
        numEButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        num1Button.setText("1");
        num1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        num2Button.setText("2");
        num2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        num3Button.setText("3");
        num3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        numDButton.setText("D");
        numDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        num0Button.setText("0");
        num0Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        numCButton.setText("C");
        numCButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        numBButton.setText("B");
        numBButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        numAButton.setText("A");
        numAButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numButtonsActionPerformed(evt);
            }
        });

        dotButton.setText(".");
        dotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dotButtonActionPerformed(evt);
            }
        });

        ansButton.setText("ANS");
        ansButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ansButtonActionPerformed(evt);
            }
        });

        equalButton.setText("=");
        equalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equalButtonActionPerformed(evt);
            }
        });

        plusButton.setText("+");
        plusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusButtonActionPerformed(evt);
            }
        });

        minusButton.setText("-");
        minusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusButtonActionPerformed(evt);
            }
        });

        mulButton.setText("x");
        mulButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mulButtonActionPerformed(evt);
            }
        });

        divButton.setText("/");
        divButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divButtonActionPerformed(evt);
            }
        });

        invButton.setText("INV");
        invButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invButtonActionPerformed(evt);
            }
        });

        buttonGroup.add(binButton);
        binButton.setText("bin");
        binButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binButtonActionPerformed(evt);
            }
        });

        buttonGroup.add(octButton);
        octButton.setText("oct");
        octButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                octButtonActionPerformed(evt);
            }
        });

        buttonGroup.add(decButton);
        decButton.setSelected(true);
        decButton.setText("dec");
        decButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decButtonActionPerformed(evt);
            }
        });

        buttonGroup.add(hexButton);
        hexButton.setText("hex");
        hexButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hexButtonActionPerformed(evt);
            }
        });

        acButton.setText("AC");
        acButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ccButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(arrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(num7Button, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(num8Button, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(num9Button, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(numFButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(num4Button, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(num5Button, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(num6Button, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(numEButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(dotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(39, 39, 39)
                                            .addComponent(ansButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(equalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(num1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(num2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(num3Button, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(num0Button, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(numAButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(numBButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(numCButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(numDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(minusButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(plusButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(mulButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(divButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(invButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(binButton))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(hexButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(octButton))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(decButton)))
                        .addContainerGap(68, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ccButton)
                    .addComponent(arrowButton)
                    .addComponent(acButton))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num7Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num8Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num9Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numFButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(binButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num4Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num5Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num6Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numEButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(octButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num3Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numDButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mulButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num0Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numCButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numBButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numAButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(divButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hexButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ansButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(invButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldActionPerformed

    private void numButtonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numButtonsActionPerformed
        JButton jbutton = (JButton) evt.getSource();
        String textField = jTextField.getText();
        if(textField.length() == displayLength) return;
        if(textField.charAt(0) == '0' && textField.length() == 1)
            jTextField.setText(jbutton.getText());
        else jTextField.setText(textField + jbutton.getText());
    }//GEN-LAST:event_numButtonsActionPerformed

    private void ccButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ccButtonActionPerformed
        jTextField.setText("0");
        setLastOperation("setANS");
        setEqualPressed(false);
    }//GEN-LAST:event_ccButtonActionPerformed
    
    private void performOperatorAction(String operator) {
        switch(operator) {
        case "add":
            setSavedANS(calculator.add(jTextField.getText()));
            break;
        case "sub":
            setSavedANS(calculator.sub(jTextField.getText()));
            break;
        case "mul":
            setSavedANS(calculator.mul(jTextField.getText()));
            break;
        case "div":
            setSavedANS(calculator.div(jTextField.getText()));
            break;
        }
    }
    
    private void errorMessage(String message) {
        setSavedANS(message);
        jTextField.setText(message);
    }    
    
    private void operatorsActions(String operator) {
        String result = calculator.trimInput(jTextField.getText());
        if(result == "sintax error!" || result == "much int digits!") {
            errorMessage(result);
            return;
        }
        
        result = Exceptions.checkSyntaxInputValue(result,calculator.getBase());
        if(result == "sintax error!") {
            errorMessage(result);
            return;
        }
        
        if(!wasEqualPressed()) performOperatorAction(operator);
        jTextField.setText("0");
        setLastOperation(operator);
        disableAllOperatorsButtonsExcept(operator);
        disableAllBaseButtons();
    }
    
    private void plusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusButtonActionPerformed
        operatorsActions("add");
    }//GEN-LAST:event_plusButtonActionPerformed

    private void minusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusButtonActionPerformed
        operatorsActions("sub");
    }//GEN-LAST:event_minusButtonActionPerformed

    private void mulButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mulButtonActionPerformed
        operatorsActions("mul");
    }//GEN-LAST:event_mulButtonActionPerformed

    private void divButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divButtonActionPerformed
        operatorsActions("div");
    }//GEN-LAST:event_divButtonActionPerformed

    private void invButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invButtonActionPerformed
        jTextField.setText(calculator.inv());
    }//GEN-LAST:event_invButtonActionPerformed

    private void doEqualButtonAction() {
        String result = Exceptions.checkSyntaxInputValue(jTextField.getText(),
        calculator.getBase());
        if(result.equals(jTextField.getText())) {
            calculator.setANS(Double.parseDouble(jTextField.getText()));
            jTextField.setText(Double.toString(calculator.getANS()));
            setLastOperation("setANS");
        }
        else
            jTextField.setText(result);
    }
    
    private void equalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equalButtonActionPerformed
        switch(getLastOperation()) {
            case "setANS":
                doEqualButtonAction();
                break;
            case "add":
                plusButtonActionPerformed(evt);
                jTextField.setText(getSavedANS());
                enableAllOperatorsButtonsExcept(getLastOperation());
                enableAllBaseButtons();
                break;
            case "sub":
                minusButtonActionPerformed(evt);
                jTextField.setText(getSavedANS());
                enableAllOperatorsButtonsExcept(getLastOperation());
                enableAllBaseButtons();
                break;
            case "mul":
                mulButtonActionPerformed(evt);
                jTextField.setText(getSavedANS());
                enableAllOperatorsButtonsExcept(getLastOperation());
                enableAllBaseButtons();
                break;
            case "div":
                divButtonActionPerformed(evt);
                jTextField.setText(getSavedANS());
                enableAllOperatorsButtonsExcept(getLastOperation());
                enableAllBaseButtons();
                break;
        }
        setEqualPressed(true);
    }//GEN-LAST:event_equalButtonActionPerformed

    private void arrowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrowButtonActionPerformed
        String textField = jTextField.getText();
        if(textField.length() > 1)
            jTextField.setText(textField.substring(0,textField.length()-1));
        else if(textField.charAt(0) != '0')
            jTextField.setText("0");
    }//GEN-LAST:event_arrowButtonActionPerformed

    private void ansButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ansButtonActionPerformed
        jTextField.setText(calculator.getANSinStringFormat());
    }//GEN-LAST:event_ansButtonActionPerformed

    private void binButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binButtonActionPerformed
        calculator.setBase(2);
        enableAllNumbersButtons();
        jTextField.setText("0");
        String []numbersButtonsID = {"2","3","4","5","6","7","8","9","A","B",
            "C","D","E","F"};
        disableNumbersButtons(numbersButtonsID);
    }//GEN-LAST:event_binButtonActionPerformed

    private void octButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_octButtonActionPerformed
        calculator.setBase(8);
        enableAllNumbersButtons();
        jTextField.setText("0");
        String []numbersButtonsID = {"8","9","A","B","C","D","E","F"};
        disableNumbersButtons(numbersButtonsID);
    }//GEN-LAST:event_octButtonActionPerformed

    private void decButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decButtonActionPerformed
        calculator.setBase(10);
        enableAllNumbersButtons();
        jTextField.setText("0");
        String []numbersButtonsID = {"A","B","C","D","E","F"};
        disableNumbersButtons(numbersButtonsID);
    }//GEN-LAST:event_decButtonActionPerformed

    private void hexButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hexButtonActionPerformed
        calculator.setBase(16);
        enableAllNumbersButtons();
        jTextField.setText("0");
    }//GEN-LAST:event_hexButtonActionPerformed

    private void dotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dotButtonActionPerformed
        JButton jbutton = (JButton) evt.getSource();
        jTextField.setText(jTextField.getText() + jbutton.getText());
    }//GEN-LAST:event_dotButtonActionPerformed

    private void acButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acButtonActionPerformed
        calculator.setANS(0.0);
    }//GEN-LAST:event_acButtonActionPerformed
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
            java.util.logging.Logger.getLogger(UICalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UICalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UICalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UICalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UICalculator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acButton;
    private javax.swing.JButton ansButton;
    private javax.swing.JButton arrowButton;
    private javax.swing.JRadioButton binButton;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton ccButton;
    private javax.swing.JRadioButton decButton;
    private javax.swing.JButton divButton;
    private javax.swing.JButton dotButton;
    private javax.swing.JButton equalButton;
    private javax.swing.JRadioButton hexButton;
    private javax.swing.JButton invButton;
    private javax.swing.JTextField jTextField;
    private javax.swing.JButton minusButton;
    private javax.swing.JButton mulButton;
    private javax.swing.JButton num0Button;
    private javax.swing.JButton num1Button;
    private javax.swing.JButton num2Button;
    private javax.swing.JButton num3Button;
    private javax.swing.JButton num4Button;
    private javax.swing.JButton num5Button;
    private javax.swing.JButton num6Button;
    private javax.swing.JButton num7Button;
    private javax.swing.JButton num8Button;
    private javax.swing.JButton num9Button;
    private javax.swing.JButton numAButton;
    private javax.swing.JButton numBButton;
    private javax.swing.JButton numCButton;
    private javax.swing.JButton numDButton;
    private javax.swing.JButton numEButton;
    private javax.swing.JButton numFButton;
    private javax.swing.JRadioButton octButton;
    private javax.swing.JButton plusButton;
    // End of variables declaration//GEN-END:variables
}
