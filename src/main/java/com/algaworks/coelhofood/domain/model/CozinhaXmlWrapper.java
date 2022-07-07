package com.algaworks.coelhofood.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import lombok.Data;
import lombok.NonNull;

@JsonRootName("cozinhas")
//@JacksonXmlRootElement(localName = "cozinhas")
@Data
public class CozinhaXmlWrapper {

	@JsonProperty("cozinha")
//	@JacksonXmlProperty(localName = "cozinha")
	@JacksonXmlElementWrapper(useWrapping = false)
	@NonNull
	private List<Cozinha> cozinhas;
}
