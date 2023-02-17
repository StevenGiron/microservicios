package com.giron.microservicios.app.examenes.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "preguntas")
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    @JsonIgnoreProperties(value = {"preguntas"}) //para evitar el loop infinito de examen contiene preguntas, preguntas contiene examen y asi sucesivamente, suprime la relacion inversa
    @ManyToOne(fetch = FetchType.LAZY)  //Muchas preguntas un examen
    @JoinColumn(name = "examen_id") //establecer llave foranea, ademas indica que Pregunta es el dueño de la relacion pues contiene la llave foranea
    private Examen examen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Pregunta)){
            return false;
        }

        Pregunta p = (Pregunta) obj;
        return this.id != null && this.id.equals(p.getId());
    }
}
