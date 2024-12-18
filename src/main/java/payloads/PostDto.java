package payloads;

import java.util.Date;
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
public class PostDto {

	
	private Integer pId;

	private String title;

	private String content;
		
	private String imagename;

	private Date addedDate;


	private CategoryDto category;


	private UserDto user;
		
	private Set<CommentsDto> comments=new HashSet<>();
	
	
	
	
	
	
	
	
	
	
}
