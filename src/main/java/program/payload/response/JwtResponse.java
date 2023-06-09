package program.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String email;
  private List<String> roles;
}
