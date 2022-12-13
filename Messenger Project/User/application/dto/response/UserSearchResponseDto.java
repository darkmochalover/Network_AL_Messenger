package User.response;

/* 유저 검색 기능 구현 
* reference: https://github.com/woowacourse-teams/2021-pick-git/tree/develop/backend/pick-git/src/main/java/com/woowacourse/pickgit/user/application/dto/response */

public class UserSearchResponseDto {
	private String imageUrl;
	private String username;
	private Boolean following;
	
	private UserSearchResponseDto()
	{
		
	}
	
	public UserSearchResponseDto(String imageUrl, String username, Boolean following)
	{
		this.imageUrl = imageUrl;
		this.username = username;
		this.following = following;
	}
	
	public String getImageUrl()
	{
		return imageUrl;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public Boolean getFollowing()
	{
		return following;
	}
}
