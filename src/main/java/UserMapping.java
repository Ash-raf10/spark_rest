


import com.mongodb.MongoClient;

import org.mongodb.morphia.Datastore;


//import java.util.HashMap;
import java.util.List;

import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;


public class UserMapping implements UserService {



    // private HashMap<String,User> userMap;
    MongoClient client ;

    {
        try {
            client = new MongoClient("localhost", 27017);
        } catch (Exception e) {
           System.out.println(e);
        }
    }
    Datastore datastore = new Morphia().createDatastore(client,"Main");

    Query<User> query = datastore.createQuery(User.class);

   /* public UserMapping() {
        userMap = new HashMap<>();
    }
    */

    @Override
    public void addUser(User user) {

        datastore.save(user.getId(), user);
    }


    /* @Override
     public User getUser(String id) {
         return userMap.get(id);
     }
 */
    @Override
    public List <User> getUsers() {
        List<User> list = datastore.find(User.class).asList();

        if(list != null){
            return list;
        }
        return null;
    }



    @Override
    public boolean userExist(String id) {

        if (datastore.find(User.class).field("id").equals("105"))
        {
            return true;
        } else{
            return false;
        }
    }
/*
    @Override
    public void deleteUser(String id) {
        userMap.remove(id);
    }

    @Override
    public User editUser(User forEdit) throws UserException {
        try {
            if (forEdit.getId() == null)
                throw new UserException("ID cannot be blank");

            User toEdit = userMap.get(forEdit.getId());

            if (toEdit == null)
                throw new UserException("User not found");

            if (forEdit.getEmail() != null) {
                toEdit.setEmail(forEdit.getEmail());
            }
            if (forEdit.getName() != null) {
                toEdit.setName(forEdit.getName());
            }

            if (forEdit.getId() != null) {
                toEdit.setId(forEdit.getId());
            }

            return toEdit;
        } catch (Exception ex) {
            throw new UserException(ex.getMessage());
        }
    }
    */

}



