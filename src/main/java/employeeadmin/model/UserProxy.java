package employeeadmin.model;

import java.util.ArrayList;
import java.util.List;

import employeeadmin.model.vo.UserVO;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/2/2015
 */
public class UserProxy extends Proxy {

    public static final String NAME = "UserProxy";

    public static List<UserVO> USERS = new ArrayList<>();

    public UserProxy() {
        super(NAME, USERS);
    }

    @SuppressWarnings("unchecked")
    public final ArrayList<UserVO> getUsers() {
        return (ArrayList<UserVO>) this.getData();
    }

    public final UserVO getUser(UserVO userVO) {
        for (UserVO user : getUsers()) {
            if (userVO == user) {
                return user;
            }
        }

        return null;
    }

    public final void addUser(final UserVO user) {
        getUsers().add(user);
    }

    public final void updateUser(final UserVO user) {
        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i).username.equals(user.username)) {
                getUsers().set(i, user);
            }
        }
    }

    public final void deleteUser(final UserVO user) {
        if (getUsers().contains(user)) {
            getUsers().remove(user);
        }
    }
}