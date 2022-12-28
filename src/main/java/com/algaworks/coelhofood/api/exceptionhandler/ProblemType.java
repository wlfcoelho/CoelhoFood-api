package com.algaworks.coelhofood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensível", "Mensagem incompreensível"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontratada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");


    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://coelhofood.com.br" + path;
        this.title = title;
    }
}
