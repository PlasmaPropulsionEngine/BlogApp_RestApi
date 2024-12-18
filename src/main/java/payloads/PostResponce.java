package payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostResponce {

	
	
	private Integer pId;

	private String title;

	private String content;
		
	private String imagename;

	private Date addedDate;
	
	@JsonIgnore
	private UserDto user;
	
	private CategoryResponce category;

		
	private Set<CommentResponce> comments=new HashSet<>();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
