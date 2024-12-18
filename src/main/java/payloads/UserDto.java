package payloads;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	
	
	private int  user_id;	
	
	
	private String email;
	
	
	private String password;
	
	
	private String name;
	
	
	private Set<Roles>roles=new HashSet<>();
	
	
	
}
