package br.com.lucas.enums;

public enum ClientClassification {
    VIP,
    POTENTIAL,
    REGULAR,
    SILVER,
    GOLD
}


/*
 * Vantagens de usar enum: Evita valores inválidos (ex: "vipp", "Gold",
 * "potencial").
 * 
 * Traz autocompletar no IDE.
 * 
 * Facilita manutenção e leitura do código.
 * 
 * Melhora a segurança de dados (tipagem forte).
 * 
 * Facilita filtros com enums em APIs e bancos.
 */