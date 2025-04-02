package com.tulaysahinduran.ibb_ecodation_javafx.utils;
/**
 * 📌 Kullanıcı Rollerini Tanımlayan Enum
 */
public enum ERole {
    USER("Kullanıcı"),
    MODERATOR("Moderatör"),
    ADMIN("Yönetici");

    // Field
    private final String description;

    // Parametreli
    ERole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 📌 String’den Enum’a güvenli dönüşüm yapar.
     */
    public static ERole fromString(String role) {
        try {
            return ERole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("❌ Geçersiz rol: " + role);
        }
    }
    @Override
    public String toString() {
        return description; // ComboBox’ta görünen metin
    }
}
