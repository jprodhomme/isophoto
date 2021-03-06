package co.simplon.titrepro.isophoto.dto;

import co.simplon.titrepro.isophoto.model.Authority;

/**
 * Specific App User DTO to be able to send user data without password through
 * REST responses.
 */
public class PhotographeDto {

	private String pseudoDto;

	private Authority authority;

	public PhotographeDto() {
		
	}

	public PhotographeDto(String pseudoDto) {
		this.pseudoDto = pseudoDto;
	}

	public PhotographeDto(String pseudoDto, Authority authority) {
		this.pseudoDto = pseudoDto;
		this.authority = authority;
	}
	

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public String getPseudoDto() {
		return pseudoDto;
	}

	public void setPseudoDto(String pseudoDto) {
		this.pseudoDto = pseudoDto;
	}

}
