package com.project.proposta_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public  String sobrenome;

    private String cpf;

   public  String telefone;

   private  Double renda;

   @OneToOne(mappedBy = "usuario")
   private  Proposta proposta;




}
