package com.giron.microservicios.app.cursos.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "examenes")
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createAt;


    @JsonIgnoreProperties(value = {"examen"}, allowSetters = true) //para evitar el loop infinito de examen contiene preguntas, preguntas contiene examen y asi sucesivamente, suprime la relacion inversa
    @OneToMany(mappedBy = "examen",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)//Un examen muchas preguntas,
                                                                    // cascade.all incluye persist y remove porque las preguntas no pueden existir sin el examen
    //orphanRemoval cada vez que se elimine una pregunta de la lista el atributo examen de la clase pregunta queda null es decir que no hace referencia a ningún examen
    // por eso se elimina con esta propiedad, es decir que cualquier pregunta que no esté asignada a ningún examen lo elimina
    //mappedBy indica la relación bidireccional. El valor es el nombre del atributo
    private List<Pregunta> preguntas;

    public Examen() {
        this.preguntas = new ArrayList<>();
    }

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas.clear();
        preguntas.forEach(this::addPregunta);
    }

    public void addPregunta(Pregunta pregunta){
        this.preguntas.add(pregunta);
        pregunta.setExamen(this);
    }

    public void removePregunta(Pregunta pregunta){
        this.preguntas.remove(pregunta);
        pregunta.setExamen(null);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Examen)){
            return false;
        }

        Examen e = (Examen) obj;
        return this.id != null && this.id.equals(e.getId());
    }

}
