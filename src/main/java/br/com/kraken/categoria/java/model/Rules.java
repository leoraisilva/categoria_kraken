package br.com.kraken.categoria.java.model;

public enum Rules {
    ADMIN("admin"),
    USER("user");

    private String rules;
    Rules (String rules) {
        this.rules = rules;
    }
}
