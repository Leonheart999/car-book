package ge.levanchitiashvili.carbook.dtos.security;

import lombok.Data;

@Data
public class AuthorityDTO {
    private Long id;
    private String name;
    private Long parentId;
    private Boolean active;
}
