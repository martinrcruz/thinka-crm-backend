package cl.thinka.clientmicroservice.v1.util;


import lombok.Data;

@Data
public class CustomResponse<T> {
    private T dto;
    private int resCode;
    private String resCodeDesc;

    public CustomResponse(){
    }

    public CustomResponse(int resCode, String resCodeDesc){
        this.resCode = resCode;
        this.resCodeDesc = resCodeDesc;
    }

    public CustomResponse(T dto, int resCode, String resCodeDesc){
        this.dto = dto;
        this.resCode = resCode;
        this.resCodeDesc = resCodeDesc;
    }
}