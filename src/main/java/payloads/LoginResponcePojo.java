package payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponcePojo {

	
	
	
	private String jwtToken;
	
	private int user_id;
	
	
	
	
	
}
