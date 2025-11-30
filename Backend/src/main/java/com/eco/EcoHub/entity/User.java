    package com.eco.EcoHub.entity;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


    @Entity
    @Table(name = "Usuario")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID idUser;

        @NotBlank(message = "O nome completo é obrigatório")
        private String nomeCompleto;

		@NotBlank(message = "O CPF é obrigatório")
        @Column(unique = true)
        private String cpf;

        private String dataNascimento;

        @Email(message = "E-mail inválido")
        @Column(unique = true)
        private String email;

        @NotBlank(message = "A senha é obrigatória")
        private String senha;

        private String confirmarSenha;
        
        @ManyToMany
        @JoinTable(name = "usuario_perfil",
        		joinColumns = @JoinColumn(name = "idUser"),
				inverseJoinColumns = @JoinColumn(name = "idPerfil")
        )
        @JsonIgnore
        private Set<Perfil> usuarioPerfis = new HashSet<>();

        public Set<Perfil> getUsuarioPerfis() {
			return usuarioPerfis;
		}

		public void setUsuarioPerfis(Set<Perfil> usuarioPerfis) {
			this.usuarioPerfis = usuarioPerfis;
		}

		public UUID getIdUser() {
            return this.idUser;
        }

        public void setIdUser(UUID idUser) {
            this.idUser = idUser;
        }

        public String getNomeCompleto() {
            return this.nomeCompleto;
        }

        public void setNomeCompleto(String nomeCompleto) {
            this.nomeCompleto = nomeCompleto;
        }

        public String getCpf() {
            return this.cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getDataNascimento() {
            return this.dataNascimento;
        }

        public void setDataNascimento(String dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return this.senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getConfirmarSenha() {
            return this.confirmarSenha;
        }

        public void setConfirmarSenha(String senha) {
            this.confirmarSenha = senha;
        }
    }
