package com.pagatodo.financieracontable.domain.models.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Terminal {

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
