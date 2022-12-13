package User;

import static java.util.stream.Collectors.toList;
import java.util.List;
import java.util.function.Predicate;

import User.response.*;

public class UserData_toAssembler {
	
	private UserData_toAssembler(){
		
	}
	
	public static UserProfileResponseDto userProfileResponseDto(User user, Boolean folloing)
	{
		return UserProfileResponseDto.builder()
				.name(user.)
	}

}
