package com.tulaysahinduran.ibb_ecodation_javafx;

// Eğer config.properties dosyasıyla çalışıyorsan yukarıdakini aşağıdakiyle değiştir:
// import com.hamitmizrak.ibb_ecodation_javafx.database.SingletonPropertiesDBConnection;

// Test: usertable tablosunu oluştur ve örnek veri ekle

import com.tulaysahinduran.ibb_ecodation_javafx.database.SingletonPropertiesDBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloApplication extends Application {

    // Proje Açıldığında İlk Açılacak Sayfa
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        // Örnek Veri
        dataSet();

        // PROJE AYAĞA KALKALKEN DATABASE(H2DB) ÇALIŞSIN
       // initializeDatabase();

        // Caused by: java.lang.IllegalStateException: Location is not set.
        // Yukarıdaki hatanın anlamı sayfayı bulamıyor.
       /*
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        */
        // Başlangıçta Login Ekranı Gelsin
        //view/admin.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/login.fxml"));
        Parent parent= fxmlLoader.load();
        stage.setTitle("Kullanıcı Yönetimi Login Sayfası");
        stage.setScene(new Scene(parent));
        stage.show();

    }

    /// //////////////////////////////////////////////////////////////////////////
    /// DATABASE
    // Proje ayağa kalkarken veritabanından örnek veriler eklesin
    // Database Başlangıçtaki değeri
    public static void dataSet() throws SQLException {
        Connection connection  = SingletonPropertiesDBConnection.getInstance().getConnection(); // STATIC BAĞLANTI ALINDI
        // Tablo oluşturma
        // Tablo oluşturma (usertable + kdv_table)
        try (Statement stmt = connection.createStatement()) {
            //Kullanıcı Tablosu
            String createUserTableSQL = """
                    CREATE TABLE IF NOT EXISTS usertable (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(50) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        role VARCHAR(10) DEFAULT "USER"
                    );
                    """;
            stmt.execute(createUserTableSQL);
            // KDV tablosu
            String createKdvTableSQL = """
                    CREATE TABLE IF NOT EXISTS kdv_table (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        amount DOUBLE NOT NULL,
                        kdvRate DOUBLE NOT NULL,
                        kdvAmount DOUBLE NOT NULL,
                        totalAmount DOUBLE NOT NULL,
                        receiptNumber VARCHAR(100) NOT NULL,
                        transactionDate DATE NOT NULL,
                        description VARCHAR(255),
                        exportFormat VARCHAR(50)
                    );
                    """;
            stmt.execute(createKdvTableSQL);
        }
        // Kullanıcı ekleme
        String insertSQL = """
            MERGE INTO usertable (username, password, email, role)
            KEY(username) VALUES (?, ?, ?, ?);
        """;
        try (PreparedStatement ps = connection.prepareStatement(insertSQL)) {
            // 1. kullanıcı
            ps.setString(1, "tulaysahin");
            ps.setString(2, BCrypt.hashpw("root", BCrypt.gensalt()));
            ps.setString(3, "tulaysahin@gmail.com");
            ps.setString(4, "USER");
            ps.executeUpdate();

            // 2. kullanıcı
            ps.setString(1, "admin");
            //ps.setString(2, BCrypt.hashpw("root", BCrypt.gensalt()));
            ps.setString(2, BCrypt.hashpw("root", BCrypt.gensalt()));
            ps.setString(3, "admin@gmail.com");
            ps.setString(4, "ADMIN");
            ps.executeUpdate();

            // 3. kullanıcı
            ps.setString(1, "root");
            //ps.setString(2, BCrypt.hashpw("root", BCrypt.gensalt()));
            ps.setString(2, BCrypt.hashpw("root", BCrypt.gensalt()));
            ps.setString(3, "root");
            ps.setString(4, "ADMIN");
            ps.executeUpdate();
        }

        System.out.println("✅ BCrypt ile şifrelenmiş ve roller atanmış kullanıcılar başarıyla eklendi.");
    }

    /// //////////////////////////////////////////////////////////////////////////
     public static void main(String[] args) {
        launch();
    }
}//end helloApplication