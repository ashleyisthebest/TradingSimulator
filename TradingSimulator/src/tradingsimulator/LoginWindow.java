package tradingsimulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class LoginWindow extends javax.swing.JFrame {

    //Global username string to be accessed on other classes. Will be updated once user logs in
    public static userObject user = new userObject(0, null, null);

    //Database public variables
    Connection dbConnection;
    Statement dbStatement;
    ResultSet dbResultSet;

    public LoginWindow() {
        initComponents();
        connectDB();
    }

    public void connectDB() {

        try {

            //Database login details
            String host = "jdbc:derby://localhost:1527/MainDB";
            String username = "root";
            String password = "password";

            //Connect to database
            dbConnection = DriverManager.getConnection(host, username, password);
            System.out.println("Successful Connection");

            //Load resultset and make resultset updatable
            dbStatement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQLQuery = "Select * FROM tblUser";
            dbResultSet = dbStatement.executeQuery(SQLQuery);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.WARNING_MESSAGE);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel1.setText("LOGIN");

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel2.setText("USERNAME:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel3.setText("PASSWORD:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel4.setText("NEW ACCOUNT:");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jCheckBox1.setBorderPaintedFlat(true);
        jCheckBox1.setMargin(new java.awt.Insets(-2, -2, -2, -2));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("LOGIN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addComponent(jCheckBox1)
                    .addComponent(jPasswordField1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(206, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(200, 200, 200))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //Getting user entered data
        String username = jTextField1.getText();
        String password = jPasswordField1.getText();

        //Purposely not using seperare methods as this will only be done once and methods will make it harder
        //If they are creating a new account
        if (jCheckBox1.isSelected()) {

            try {

                //Generate userID
                dbStatement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                //Get userID values in order of descending
                String SQLQuery = "SELECT * FROM tblUser\n"
                        + "ORDER  BY userID DESC\n";

                dbResultSet = dbStatement.executeQuery(SQLQuery);

                //Finding the most recent userID
                int userID = 0;
                while (dbResultSet.first()) {
                    userID = Integer.valueOf(dbResultSet.getString("userID"));
                    System.out.println("Most recent uderID: " + userID);
                    break;
                }

                //Refresh result set
                SQLQuery = "Select * FROM tblUser";
                dbResultSet = dbStatement.executeQuery(SQLQuery);

                //Go to end of resultset
                dbResultSet.moveToInsertRow();

                //Save new details
                dbResultSet.updateInt("userID", userID + 1);
                dbResultSet.updateString("username", username);
                dbResultSet.updateString("password", password);
                dbResultSet.insertRow();

                dbStatement.close();
                dbResultSet.close();

                //Update database
                dbStatement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                dbResultSet = dbStatement.executeQuery(SQLQuery);

                //Add details to object
                user.userID = userID + 1;
                user.username = username;
                user.password = password;

            } catch (SQLException e) {

                //Presenting error message if username has been used
                if (e.toString().contains("duplicate")) {
                    JOptionPane.showMessageDialog(null, "Username already in use", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.WARNING_MESSAGE);
                }

                System.out.println(e);
            }
            //Open main window
            this.setVisible(false);
            MainWindow mw = new MainWindow();
            mw.setVisible(true);
        } else {
            //If they are logging in to an existing account
            try {
                //Refresh result set
                dbStatement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                //Find username in DB
                String SQLQuery = "SELECT * FROM tblUser WHERE username='" + username + "'";
                dbResultSet = dbStatement.executeQuery(SQLQuery);

                String dbUsername = "";
                String dbPassword = "";
                int dbID = 0;

                //Get username and password as string
                while (dbResultSet.next()) {
                    dbUsername = dbResultSet.getString("username");
                    dbPassword = dbResultSet.getString("password");
                    dbID = Integer.valueOf(dbResultSet.getString("userID"));
                }

                //Check the username and password match
                if (username.equals(dbUsername) && password.equals(dbPassword)) {
                    System.out.println("Logging in");
                    //Adding details to user object
                    user.username = dbUsername;
                    user.password = dbPassword;
                    user.userID = dbID;

                    //Open main window
                    this.setVisible(false);
                    MainWindow mw = new MainWindow();
                    mw.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect password.", "Error", JOptionPane.WARNING_MESSAGE);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
