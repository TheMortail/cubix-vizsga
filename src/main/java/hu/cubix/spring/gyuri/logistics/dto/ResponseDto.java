package hu.cubix.spring.gyuri.logistics.dto;

public class ResponseDto {
    private Long id;

    public ResponseDto(Long id) {
        this.id = id;
    }

    public ResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
