package User.response;

import lombok.Builder;
import lombok.Getter;

/* 유저 프로덕션 코드 및 테스트 리팩토링 
 * reference: https://github.com/woowacourse-teams/2021-pick-git/tree/develop/backend/pick-git/src/main/java/com/woowacourse/pickgit/user/application/dto/response */

@Builder
@Getter
public class UserProfileResponseDto {
	private String name; 			// 이름 
    private String imageUrl; 		// 프로필 사진 링크 
    private String description; 	// 설명 
    private int followerCount; 		// 팔로워 카운터 
    private int followingCount; 	// 팔로잉 카운터 
    private int postCount; 			// 게시물 수 
    private String company; 		// 소속 회사
    private String location; 		// 위치 
    private String website; 		// 웹사이트 
    
    private Boolean following;

    /*
    private UserProfileResponseDto() {
    	
    }
    
    public UserProfileResponseDto(
    String name, 			// 이름 
    String imageUrl,		// 프로필 사진 링크 
    String description,	// 설명 
    int followerCount,		// 팔로워 카운터 
    int followingCount,	// 팔로잉 카운터 
    int postCount, 			// 게시물 수 
    String company, 		// 소속 회사
    String location,		// 위치 
    String website
    )
    {
    	this.name = name;
    	this.imageUrl = imageUrl;
    	this.description = description;
    	this.followerCount = followerCount;
    	this.followingCount = followingCount;
    	this.postCount = postCount;
    	this.company = company;
    	this.location = location;
    	this.website = website;
    }
    */
}
