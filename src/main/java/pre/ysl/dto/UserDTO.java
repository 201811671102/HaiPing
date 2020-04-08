package pre.ysl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pre.ysl.pojo.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends User {
    public UserDTO(User user){
        this.setUpassword(user.getUpassword());
        this.setUaccount(user.getUaccount());
        this.setUid(user.getUid());
        this.setUemail(user.getUemail());
        this.setUphone(user.getUphone());
        this.setUphoto(user.getUphoto());
    }
    public void setUserDTO(User user){
        this.setUpassword(user.getUpassword());
        this.setUaccount(user.getUaccount());
        this.setUid(user.getUid());
        this.setUemail(user.getUemail());
        this.setUphone(user.getUphone());
        this.setUphoto(user.getUphoto());
    }
    /*角色信息*/
    private Object roleObject;
    /*角色类型id*/
    private Integer role;

}
