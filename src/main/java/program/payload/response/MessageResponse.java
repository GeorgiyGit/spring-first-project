package program.payload.response;


import lombok.Data;

@Data
public class MessageResponse {
  private String message;
  public MessageResponse(String msg){
    this.message=msg;
  }
}
