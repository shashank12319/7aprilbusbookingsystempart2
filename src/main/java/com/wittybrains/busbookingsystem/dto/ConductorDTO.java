package com.wittybrains.busbookingsystem.dto;

public class ConductorDTO  {
    private static final long serialVersionUID = 1L; 

   
    private String name;

    private BusDTO busDTO;
    
    private UserDTO userDTO;

    public ConductorDTO() {
    }

    public ConductorDTO(String name, BusDTO busDTO, UserDTO userDTO) {
        this.name = name;
        this.busDTO = busDTO;
        this.userDTO = userDTO;
    }


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public BusDTO getBusDTO() {
		return busDTO;
	}

	public void setBusDTO(BusDTO busDTO) {
		this.busDTO = busDTO;
	}
    
    

    // getters and setters
}
