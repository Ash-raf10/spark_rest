import java.util.List;

public interface UserService {

    public void addUser (User user);
    public List<User> getUsers();


   /* public User getUser(String id);
    public User editUser(User user) throws UserException;
    public void deleteUser(String id);
    */public boolean userExist(String id);


}