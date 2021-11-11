package Enums;

import java.awt.*;

public enum StreetCellEnum {
                    NADA("0", new Color(200,200,200),"Nada (célula não usada pela malha)"),
                    CIMA("1", new Color(100,100,200),"Estrada Cima"),
                 DIREITA("2", new Color(100,200,100),"Estrada Direita"),
                   BAIXO("3", new Color(100,100,100),"Estrada Baixo"),
                ESQUERDA("4", new Color(200,100,100),"Estrada Esquerda"),
              CRUZA_CIMA("5", new Color(100,100,100),"Cruzamento Cima"),
           CRUZA_DIREITA("6", new Color(180,180,180),"Cruzamento Direita"),
             CRUZA_BAIXO("7", new Color(180,180,180),"Cruzamento Baixo"),
          CRUZA_ESQUERDA("8", new Color(180,180,180),"Cruzamento Esquerda"),
      CRUZA_CIMA_DIREITA("9", new Color(180,180,180),"Cruzamento Cima e Direita"),
     CRUZA_CIMA_ESQUERDA("10",new Color(180,180,180),"Cruzamento Cima e Esquerda"),
     CRUZA_DIREITA_BAIXO("11",new Color(180,180,180),"Cruzamento Direita e Baixo"),
    CRUZA_BAIXO_ESQUERDA("12",new Color(180,180,180),"Cruzamento Baixo e Esquerda"),
    ;
    String value;
    String description;
    Color color;

    StreetCellEnum(String value, Color color, String description) {
        this.value = value;
        this.description = description;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }
}
