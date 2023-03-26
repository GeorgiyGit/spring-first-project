package program.DTOs.AccountDTOs;


import lombok.Data;

@Data
public class SignUpDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
