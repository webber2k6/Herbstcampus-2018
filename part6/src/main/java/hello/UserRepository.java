package hello;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.HashMap;
import java.util.Map;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
//@RestResource(exported = false)
public class UserRepository implements Repository<User, Long> {
    HashMap<Long, User> cache = new HashMap<>();

    public User findOneByLastName(String lastName) {
        for (Map.Entry<Long, User> entry : this.cache.entrySet()) {
            if (entry.getValue().getLastName().equals(lastName))
                return entry.getValue();
        }

        return null;
    }

    public void save(User u) {
        if (!this.cache.containsKey(u.getId())) {
            this.cache.put(u.getId(), u);
        }
    }
}
