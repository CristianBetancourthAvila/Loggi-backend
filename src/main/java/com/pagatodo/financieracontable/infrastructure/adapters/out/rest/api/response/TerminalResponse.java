package com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerminalResponse {

	private Long id;

	private String name;

	private String brand;

	private String idTeam;

	private String typeTeamName;

	private String state;

	private String pointSale;

	private Long idPointSale;

	private Long typeTeam;

	private String serial;

	private Long zone;

}
