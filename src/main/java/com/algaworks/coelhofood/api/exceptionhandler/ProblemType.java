package com.algaworks.coelhofood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensível", "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Entidade não encontratada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PARAMETRO_INVALIDO("/parametro-invalido","Parâmetro inválido" ),

    ERRO_SISTEMA("/erro-sistema", "Erro sistema");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://coelhofood.com.br" + path;
        this.title = title;
    }
}
